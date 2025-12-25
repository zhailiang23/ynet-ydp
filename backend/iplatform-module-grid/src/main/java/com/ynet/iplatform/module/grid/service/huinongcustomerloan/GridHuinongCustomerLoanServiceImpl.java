package com.ynet.iplatform.module.grid.service.huinongcustomerloan;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.customer.GridCustomerMapper;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.module.grid.dal.mysql.customergridrelation.GridCustomerGridRelationMapper;
import com.ynet.iplatform.module.grid.dal.dataobject.customergridrelation.GridCustomerGridRelationDO;
import com.ynet.iplatform.module.grid.dal.mysql.info.GridInfoMapper;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ynet.iplatform.framework.mybatis.core.util.MyBatisUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 惠农贷款客户扩展 Service 实现类
 *
 * 重构说明：已将 grid_huinong_customer_loan 表合并到 grid_customer 表
 * 通过 customerType='HUINONG_LOAN' 过滤惠农贷款客户
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridHuinongCustomerLoanServiceImpl implements GridHuinongCustomerLoanService {

    @Resource(name = "gridCustomerMapper")
    private GridCustomerMapper gridCustomerMapper;

    @Resource
    private GridCustomerGridRelationMapper customerGridRelationMapper;

    @Resource
    private GridInfoMapper gridInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createHuinongCustomerLoan(GridHuinongCustomerLoanSaveReqVO createReqVO) {
        // 重构说明：前端已经通过 /grid/customer/create 创建了基础客户记录
        // 这里需要：1) 更新扩展字段  2) 根据坐标自动查找 ZERODAI 网格并创建关系

        Long customerId = createReqVO.getCustomerId();

        // 1. 更新客户的扩展字段（使用 lambda update 选择性更新）
        LambdaUpdateWrapper<GridCustomerDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(GridCustomerDO::getId, customerId);

        // 更新惠农贷款客户专属字段
        if (createReqVO.getGender() != null) {
            updateWrapper.set(GridCustomerDO::getGender, createReqVO.getGender());
        }
        if (createReqVO.getCustomerCategory() != null) {
            updateWrapper.set(GridCustomerDO::getCustomerCategory, createReqVO.getCustomerCategory());
        }
        if (createReqVO.getSubdivisionType() != null) {
            updateWrapper.set(GridCustomerDO::getSubdivisionType, createReqVO.getSubdivisionType());
        }
        if (createReqVO.getBusinessAddress() != null) {
            updateWrapper.set(GridCustomerDO::getBusinessAddress, createReqVO.getBusinessAddress());
        }
        if (createReqVO.getCustomerSituation() != null) {
            updateWrapper.set(GridCustomerDO::getCustomerSituation, createReqVO.getCustomerSituation());
        }
        if (createReqVO.getBusinessYears() != null) {
            updateWrapper.set(GridCustomerDO::getBusinessYears, createReqVO.getBusinessYears());
        }
        if (createReqVO.getCurrentFinancing() != null) {
            updateWrapper.set(GridCustomerDO::getCurrentFinancing, createReqVO.getCurrentFinancing());
        }
        if (createReqVO.getCreditDemand() != null) {
            updateWrapper.set(GridCustomerDO::getCreditDemand, createReqVO.getCreditDemand());
        }
        if (createReqVO.getDemandMonth() != null) {
            updateWrapper.set(GridCustomerDO::getDemandMonth, createReqVO.getDemandMonth());
        }
        if (createReqVO.getDemandPeriod() != null) {
            updateWrapper.set(GridCustomerDO::getDemandPeriod, createReqVO.getDemandPeriod());
        }
        if (createReqVO.getBusinessProgress() != null) {
            updateWrapper.set(GridCustomerDO::getBusinessProgress, createReqVO.getBusinessProgress());
        }
        if (createReqVO.getCustomerSource() != null) {
            updateWrapper.set(GridCustomerDO::getCustomerSource, createReqVO.getCustomerSource());
        }
        if (createReqVO.getIsApplied() != null) {
            updateWrapper.set(GridCustomerDO::getIsApplied, createReqVO.getIsApplied());
        }
        if (createReqVO.getApplyTime() != null) {
            updateWrapper.set(GridCustomerDO::getApplyTime, createReqVO.getApplyTime());
        }
        if (createReqVO.getIsApproved() != null) {
            updateWrapper.set(GridCustomerDO::getIsApproved, createReqVO.getIsApproved());
        }
        if (createReqVO.getApproveTime() != null) {
            updateWrapper.set(GridCustomerDO::getApproveTime, createReqVO.getApproveTime());
        }
        if (createReqVO.getLoanProductName() != null) {
            updateWrapper.set(GridCustomerDO::getLoanProductName, createReqVO.getLoanProductName());
        }
        if (createReqVO.getLoanAmount() != null) {
            updateWrapper.set(GridCustomerDO::getLoanAmount, createReqVO.getLoanAmount());
        }
        if (createReqVO.getCreditLimit() != null) {
            updateWrapper.set(GridCustomerDO::getCreditLimit, createReqVO.getCreditLimit());
        }
        if (createReqVO.getLoanBalance() != null) {
            updateWrapper.set(GridCustomerDO::getLoanBalance, createReqVO.getLoanBalance());
        }
        if (createReqVO.getOverdueStatus() != null) {
            updateWrapper.set(GridCustomerDO::getOverdueStatus, createReqVO.getOverdueStatus());
        }
        if (createReqVO.getIsFormalCustomer() != null) {
            updateWrapper.set(GridCustomerDO::getIsFormalCustomer, createReqVO.getIsFormalCustomer());
        }

        // 执行更新
        gridCustomerMapper.update(null, updateWrapper);

        // 2. 根据坐标自动查找所属的 ZERODAI 网格并创建关系
        if (createReqVO.getLongitude() != null && createReqVO.getLatitude() != null) {
            findAndCreateCustomerGridRelations(customerId,
                createReqVO.getLongitude(),
                createReqVO.getLatitude());
        }

        return customerId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHuinongCustomerLoan(GridHuinongCustomerLoanSaveReqVO updateReqVO) {
        // 重构说明：更新客户信息，如果坐标变化则重新查找 ZERODAI 网格并更新关系

        // 校验存在
        validateHuinongCustomerLoanExists(updateReqVO.getId());

        // 获取原客户信息，用于判断坐标是否变化
        GridCustomerDO existingCustomer = gridCustomerMapper.selectById(updateReqVO.getId());

        // 1. 更新客户记录（只设置需要更新的字段，让动态SQL跳过其他字段）
        GridCustomerDO customer = new GridCustomerDO();
        customer.setId(updateReqVO.getId());

        // 只更新惠农贷款客户专属字段，不设置基础字段（如customerName、phone等）
        // 这样 updateWithLocation 的动态SQL会跳过这些字段，保持数据库原值
        customer.setGender(updateReqVO.getGender());
        customer.setCustomerCategory(updateReqVO.getCustomerCategory());
        customer.setSubdivisionType(updateReqVO.getSubdivisionType());
        customer.setBusinessAddress(updateReqVO.getBusinessAddress());
        customer.setCustomerSituation(updateReqVO.getCustomerSituation());
        customer.setBusinessYears(updateReqVO.getBusinessYears());
        customer.setCurrentFinancing(updateReqVO.getCurrentFinancing());
        customer.setCreditDemand(updateReqVO.getCreditDemand());
        customer.setDemandMonth(updateReqVO.getDemandMonth());
        customer.setDemandPeriod(updateReqVO.getDemandPeriod());
        customer.setBusinessProgress(updateReqVO.getBusinessProgress());
        customer.setCustomerSource(updateReqVO.getCustomerSource());
        customer.setIsApplied(updateReqVO.getIsApplied());
        customer.setApplyTime(updateReqVO.getApplyTime());
        customer.setIsApproved(updateReqVO.getIsApproved());
        customer.setApproveTime(updateReqVO.getApproveTime());
        customer.setLoanProductName(updateReqVO.getLoanProductName());
        customer.setLoanAmount(updateReqVO.getLoanAmount());
        customer.setCreditLimit(updateReqVO.getCreditLimit());
        customer.setLoanBalance(updateReqVO.getLoanBalance());
        customer.setOverdueStatus(updateReqVO.getOverdueStatus());
        customer.setIsFormalCustomer(updateReqVO.getIsFormalCustomer());

        // 更新客户（如果有坐标，使用 updateWithLocation）
        if (updateReqVO.getLongitude() != null && updateReqVO.getLatitude() != null) {
            // 手动设置 updater 和 updateTime（updateWithLocation 绕过了 MyBatis Plus auto-fill）
            Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
            String currentUserIdStr = currentUserId != null ? String.valueOf(currentUserId) : "1";
            customer.setUpdater(currentUserIdStr);
            customer.setUpdateTime(java.time.LocalDateTime.now());

            gridCustomerMapper.updateWithLocation(customer,
                updateReqVO.getLongitude(),
                updateReqVO.getLatitude());
        } else {
            gridCustomerMapper.updateById(customer);
        }

        // 2. 如果坐标发生变化，重新查找网格并更新关系
        if (updateReqVO.getLongitude() != null && updateReqVO.getLatitude() != null) {
            boolean locationChanged = isLocationChanged(existingCustomer,
                updateReqVO.getLongitude(),
                updateReqVO.getLatitude());

            if (locationChanged) {
                // 删除原有的 ZERODAI 类型网格关系
                deleteCustomerZerodaiGridRelations(updateReqVO.getId());

                // 根据新坐标重新创建关系
                findAndCreateCustomerGridRelations(updateReqVO.getId(),
                    updateReqVO.getLongitude(),
                    updateReqVO.getLatitude());
            }
        }
    }

    // ==================== 私有方法 ====================

    /**
     * 根据坐标查找包含该点的 ZERODAI 网格并创建客户-网格关系
     */
    private void findAndCreateCustomerGridRelations(Long customerId, Double longitude, Double latitude) {
        // 查找包含该坐标的 ZERODAI 网格
        List<GridInfoDO> grids = gridInfoMapper.selectZerodaiGridsByLocation(longitude, latitude);

        if (grids == null || grids.isEmpty()) {
            // 如果没有找到匹配的网格，记录日志但不抛异常
            System.out.println("警告：客户坐标 [" + longitude + ", " + latitude + "] 未找到匹配的ZERODAI网格");
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
            gridCustomerMapper.updateById(updateCustomer);
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

        // 创建关系记录
        GridCustomerGridRelationDO relation = GridCustomerGridRelationDO.builder()
                .customerId(customerId)
                .gridId(grid.getId())
                .gridCode(grid.getGridCode())
                .gridName(grid.getGridName())
                .gridType(grid.getGridType())
                .assignDate(LocalDate.now())
                .assignOperatorId(SecurityFrameworkUtils.getLoginUserId())
                .build();

        customerGridRelationMapper.insert(relation);
    }

    /**
     * 删除客户的所有 ZERODAI 类型网格关系（物理删除）
     *
     * 说明：使用物理删除而不是逻辑删除，原因：
     * 1. 唯一索引 uk_customer_grid(customer_id, grid_id, deleted) 不允许多条相同的已删除记录
     * 2. 如果使用逻辑删除，多次修改客户坐标会导致重复的 (customer_id, grid_id, deleted=1) 记录
     * 3. 网格关系是业务数据，可以安全地物理删除
     */
    private void deleteCustomerZerodaiGridRelations(Long customerId) {
        // 物理删除所有 ZERODAI 类型的网格关系
        customerGridRelationMapper.physicalDeleteZerodaiRelationsByCustomerId(customerId);
    }

    /**
     * 判断客户位置是否发生变化
     */
    private boolean isLocationChanged(GridCustomerDO existingCustomer, Double newLongitude, Double newLatitude) {
        if (existingCustomer.getLocation() == null) {
            return true; // 原来没有位置，现在有了
        }

        // 简化处理：如果新坐标不为空，就认为可能发生了变化
        // TODO: 可以通过 SELECT ST_X(location), ST_Y(location) 精确比较坐标
        return true;
    }

    @Override
    public void deleteHuinongCustomerLoan(Long id) {
        // 校验存在
        validateHuinongCustomerLoanExists(id);
        // 删除（逻辑删除）
        gridCustomerMapper.deleteById(id);
    }

    @Override
    public void deleteHuinongCustomerLoanListByIds(List<Long> ids) {
        // 批量删除（逻辑删除）
        gridCustomerMapper.deleteByIds(ids);
    }

    private void validateHuinongCustomerLoanExists(Long id) {
        GridCustomerDO customer = gridCustomerMapper.selectById(id);
        if (customer == null || !"HUINONG_LOAN".equals(customer.getCustomerType())) {
            throw exception(HUINONG_CUSTOMER_LOAN_NOT_EXISTS);
        }
    }

    @Override
    public GridHuinongCustomerLoanRespVO getHuinongCustomerLoan(Long id) {
        // 使用关联查询，直接返回 RespVO（包含客户姓名、手机号和站点ID）
        return gridCustomerMapper.selectHuinongLoanByIdWithRelations(id);
    }

    @Override
    public PageResult<GridHuinongCustomerLoanRespVO> getHuinongCustomerLoanPage(GridHuinongCustomerLoanPageReqVO pageReqVO) {
        // 1. 构建 MyBatis Plus 分页对象
        IPage<GridHuinongCustomerLoanRespVO> mpPage = MyBatisUtils.buildPage(pageReqVO);

        // 2. 执行分页查询（从 grid_customer 表查询，customerType='HUINONG_LOAN'）
        mpPage = gridCustomerMapper.selectHuinongLoanPageWithRelations(mpPage, pageReqVO);

        // 3. 转换为框架的 PageResult
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    @Override
    public List<GridHuinongCustomerLoanHeatmapDataVO> getHeatmapData(GridHuinongCustomerLoanHeatmapReqVO reqVO) {
        // 直接查询热力图数据（从 grid_customer 表按网格分组，根据 metricType 计算热力值）
        return gridCustomerMapper.selectHuinongLoanHeatmapData(reqVO);
    }

    @Override
    public List<GridHuinongCustomerLoanCustomerMarkerVO> getCustomerMarkers() {
        // 查询所有客户标记数据（从 grid_customer 表）
        return gridCustomerMapper.selectHuinongLoanCustomerMarkers();
    }

}