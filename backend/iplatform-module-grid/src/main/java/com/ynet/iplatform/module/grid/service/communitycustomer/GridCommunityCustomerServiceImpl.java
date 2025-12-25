package com.ynet.iplatform.module.grid.service.communitycustomer;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.dal.dataobject.customergridrelation.GridCustomerGridRelationDO;
import com.ynet.iplatform.module.grid.dal.mysql.customer.GridCustomerMapper;
import com.ynet.iplatform.module.grid.dal.mysql.info.GridInfoMapper;
import com.ynet.iplatform.module.grid.dal.mysql.customergridrelation.GridCustomerGridRelationMapper;
import com.ynet.iplatform.module.grid.dal.mysql.communitycustomer.GridCommunityCustomerMapper;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 社区客户 Service 实现类
 *
 * 重构说明：
 * 1. 去掉对 grid_community_customer 表的操作
 * 2. 社区客户字段（family_members, housing_type, monthly_income）已合并到 grid_customer 表
 * 3. 根据客户坐标自动查找所属的 COMMUNITY 类型网格
 * 4. 通过 grid_customer_grid_relation 表维护客户-网格多对多关系
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridCommunityCustomerServiceImpl implements GridCommunityCustomerService {

    @Resource(name = "gridCustomerMapper")
    private GridCustomerMapper customerMapper;

    @Resource(name = "gridInfoMapper")
    private GridInfoMapper gridInfoMapper;

    @Resource(name = "gridCustomerGridRelationMapper")
    private GridCustomerGridRelationMapper customerGridRelationMapper;

    @Resource(name = "gridCommunityCustomerMapper")
    private GridCommunityCustomerMapper communityCustomerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCommunityCustomer(GridCommunityCustomerSaveReqVO createReqVO) {
        // 1. 创建客户记录（不使用 Builder，直接 new 对象让 MyBatis Plus 自动填充 tenant_id）
        GridCustomerDO customer = new GridCustomerDO();
        customer.setCustomerType("COMMUNITY");
        customer.setCustomerName(createReqVO.getCustomerName());
        customer.setPhone(createReqVO.getPhone());
        customer.setIdCard(createReqVO.getIdNumber());
        customer.setAddress(createReqVO.getAddress());
        customer.setManagerId(createReqVO.getManagerId());
        customer.setSource("MANUAL");
        customer.setStatus("NORMAL");
        // 社区客户专属字段
        customer.setFamilyMembers(createReqVO.getFamilyMembers());
        customer.setHousingType(createReqVO.getHousingType());
        customer.setMonthlyIncome(createReqVO.getMonthlyIncome());

        // 手动设置 BaseDO 和 TenantBaseDO 的字段（insertWithLocation 是自定义 XML 方法，绕过了 MyBatis Plus auto-fill）
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        String currentUserIdStr = currentUserId != null ? String.valueOf(currentUserId) : "1";
        java.time.LocalDateTime now = java.time.LocalDateTime.now();

        customer.setCreator(currentUserIdStr);
        customer.setCreateTime(now);
        customer.setUpdater(currentUserIdStr);
        customer.setUpdateTime(now);
        customer.setDeleted(false);
        customer.setTenantId(com.ynet.iplatform.framework.tenant.core.context.TenantContextHolder.getTenantId());

        // 插入客户（带地理位置）
        if (createReqVO.getLongitude() != null && createReqVO.getLatitude() != null) {
            customerMapper.insertWithLocation(customer,
                createReqVO.getLongitude(),
                createReqVO.getLatitude());
        } else {
            customerMapper.insert(customer);
        }

        // 2. 根据坐标自动查找所属的 COMMUNITY 网格并创建关系
        if (createReqVO.getLongitude() != null && createReqVO.getLatitude() != null) {
            findAndCreateCustomerGridRelations(customer.getId(),
                createReqVO.getLongitude(),
                createReqVO.getLatitude());
        }

        return customer.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCommunityCustomer(GridCommunityCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCommunityCustomerExists(updateReqVO.getId());

        // 获取原客户信息，用于判断坐标是否变化
        GridCustomerDO existingCustomer = customerMapper.selectById(updateReqVO.getId());

        // 1. 更新客户记录（不使用 Builder，直接 new 对象让 MyBatis Plus 自动填充）
        GridCustomerDO customer = new GridCustomerDO();
        customer.setId(updateReqVO.getId());
        customer.setCustomerType("COMMUNITY");
        customer.setCustomerName(updateReqVO.getCustomerName());
        customer.setPhone(updateReqVO.getPhone());
        customer.setIdCard(updateReqVO.getIdNumber());
        customer.setAddress(updateReqVO.getAddress());
        customer.setManagerId(updateReqVO.getManagerId());
        customer.setSource("MANUAL");
        customer.setStatus("NORMAL");
        // 社区客户专属字段
        customer.setFamilyMembers(updateReqVO.getFamilyMembers());
        customer.setHousingType(updateReqVO.getHousingType());
        customer.setMonthlyIncome(updateReqVO.getMonthlyIncome());

        // 更新客户（如果有地理位置，使用 updateWithLocation）
        if (updateReqVO.getLongitude() != null && updateReqVO.getLatitude() != null) {
            // 手动设置 updater 和 updateTime（updateWithLocation 绕过了 MyBatis Plus auto-fill）
            Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
            String currentUserIdStr = currentUserId != null ? String.valueOf(currentUserId) : "1";
            customer.setUpdater(currentUserIdStr);
            customer.setUpdateTime(java.time.LocalDateTime.now());

            customerMapper.updateWithLocation(customer,
                updateReqVO.getLongitude(),
                updateReqVO.getLatitude());
        } else {
            customerMapper.updateById(customer);
        }

        // 2. 如果坐标发生变化，重新查找网格并更新关系
        if (updateReqVO.getLongitude() != null && updateReqVO.getLatitude() != null) {
            boolean locationChanged = isLocationChanged(existingCustomer,
                updateReqVO.getLongitude(),
                updateReqVO.getLatitude());

            if (locationChanged) {
                // 删除原有的 COMMUNITY 类型网格关系
                deleteCustomerCommunityGridRelations(updateReqVO.getId());

                // 根据新坐标重新创建关系
                findAndCreateCustomerGridRelations(updateReqVO.getId(),
                    updateReqVO.getLongitude(),
                    updateReqVO.getLatitude());
            }
        }
    }

    @Override
    public void deleteCommunityCustomer(Long id) {
        // 校验存在
        validateCommunityCustomerExists(id);

        // 删除客户（会自动级联删除关联关系，如果配置了级联）
        customerMapper.deleteById(id);
    }

    @Override
    public void deleteCommunityCustomerListByIds(List<Long> ids) {
        // 批量删除
        for (Long id : ids) {
            customerMapper.deleteById(id);
        }
    }

    private void validateCommunityCustomerExists(Long id) {
        GridCustomerDO customer = customerMapper.selectById(id);
        if (customer == null || !"COMMUNITY".equals(customer.getCustomerType())) {
            throw exception(COMMUNITY_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    @Deprecated
    public GridCommunityCustomerRespVO getCommunityCustomer(Long id) {
        // 注意：此方法已废弃，Controller 层直接调用 Mapper 查询
        // 实际上现在社区客户数据都在 grid_customer 表中
        return null;
    }

    @Override
    @Deprecated
    public PageResult<GridCommunityCustomerRespVO> getCommunityCustomerPage(GridCommunityCustomerPageReqVO pageReqVO) {
        // 注意：此方法已废弃，Controller 层直接调用 Mapper 的 selectPageWithRelations 方法
        // 返回空结果以保持接口兼容性
        return new PageResult<>(new ArrayList<>(), 0L);
    }

    // ==================== 私有方法 ====================

    /**
     * 根据坐标查找包含该点的 COMMUNITY 网格并创建客户-网格关系
     */
    private void findAndCreateCustomerGridRelations(Long customerId, Double longitude, Double latitude) {
        // 查找包含该坐标的 COMMUNITY 网格
        List<GridInfoDO> grids = gridInfoMapper.selectCommunityGridsByLocation(longitude, latitude);

        if (grids == null || grids.isEmpty()) {
            // 如果没有找到匹配的网格，记录日志但不抛异常
            System.out.println("警告：客户坐标 [" + longitude + ", " + latitude + "] 未找到匹配的COMMUNITY网格");
            return;
        }

        // 为每个匹配的网格创建关系记录
        for (GridInfoDO grid : grids) {
            createCustomerGridRelation(customerId, grid);
        }

        // 设置第一个网格为主网格
        if (!grids.isEmpty()) {
            GridCustomerDO updateCustomer = new GridCustomerDO();
            updateCustomer.setId(customerId);
            updateCustomer.setGridId(grids.get(0).getId());
            customerMapper.updateById(updateCustomer);
        }
    }

    /**
     * 创建客户-网格关系记录
     */
    private void createCustomerGridRelation(Long customerId, GridInfoDO grid) {
        // 检查关系是否已存在
        List<GridCustomerGridRelationDO> existingRelations =
            customerGridRelationMapper.selectListByCustomerId(customerId);

        boolean relationExists = existingRelations.stream()
                .anyMatch(r -> r.getGridId().equals(grid.getId()));

        if (relationExists) {
            return; // 关系已存在，跳过
        }

        // 创建关系记录（不使用 Builder，直接 new 对象让 MyBatis Plus 自动填充）
        GridCustomerGridRelationDO relation = new GridCustomerGridRelationDO();
        relation.setCustomerId(customerId);
        relation.setGridId(grid.getId());
        relation.setGridCode(grid.getGridCode());
        relation.setGridName(grid.getGridName());
        relation.setGridType(grid.getGridType());
        relation.setAssignDate(LocalDate.now());
        relation.setAssignOperatorId(SecurityFrameworkUtils.getLoginUserId());

        customerGridRelationMapper.insert(relation);
    }

    /**
     * 删除客户的所有 COMMUNITY 类型网格关系（物理删除）
     *
     * 说明：使用物理删除而不是逻辑删除，原因：
     * 1. 唯一索引 uk_customer_grid(customer_id, grid_id, deleted) 不允许多条相同的已删除记录
     * 2. 如果使用逻辑删除，多次修改客户坐标会导致重复的 (customer_id, grid_id, deleted=1) 记录
     * 3. 网格关系是业务数据，可以安全地物理删除
     */
    private void deleteCustomerCommunityGridRelations(Long customerId) {
        // 物理删除所有 COMMUNITY 类型的网格关系
        customerGridRelationMapper.physicalDeleteCommunityRelationsByCustomerId(customerId);
    }

    /**
     * 判断客户位置是否发生变化
     */
    private boolean isLocationChanged(GridCustomerDO existingCustomer, Double newLongitude, Double newLatitude) {
        if (existingCustomer.getLocation() == null) {
            return true; // 原来没有位置，现在有了
        }

        // 从 location 字段（POINT类型）中提取经纬度并比较
        // 简化处理：如果新坐标不为空，就认为可能发生了变化
        // 实际应该解析 location 字段来精确比较，这里为了简化暂时这样处理
        return true;
    }

}
