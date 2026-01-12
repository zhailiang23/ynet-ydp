package com.ynet.iplatform.module.grid.service.customer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.dal.dataobject.customergridrelation.GridCustomerGridRelationDO;
import com.ynet.iplatform.module.grid.dal.mysql.info.GridInfoMapper;
import com.ynet.iplatform.module.grid.dal.mysql.customergridrelation.GridCustomerGridRelationMapper;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.tenant.core.context.TenantContextHolder;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;

import com.ynet.iplatform.module.grid.dal.mysql.customer.GridCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 网格客户档案 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridCustomerServiceImpl implements GridCustomerService {

    @Resource
    private GridCustomerMapper gridCustomerMapper;

    @Resource
    private GridInfoMapper gridInfoMapper;

    @Resource
    private GridCustomerGridRelationMapper customerGridRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCustomer(GridCustomerSaveReqVO createReqVO) {
        // 插入
        GridCustomerDO customer = BeanUtils.toBean(createReqVO, GridCustomerDO.class);

        // 设置租户 ID
        customer.setTenantId(TenantContextHolder.getTenantId());

        // 设置逻辑删除标记（使用自定义 XML 插入时需要手动设置）
        customer.setDeleted(false);

        // 惠农站点现在存储在 grid_info 表中，stationId 就是 gridId
        if (createReqVO.getStationId() != null && customer.getGridId() == null) {
            customer.setGridId(createReqVO.getStationId());
        }

        // 如果提供了经纬度，使用 insertWithLocation 方法处理位置信息
        if (createReqVO.getLongitude() != null && createReqVO.getLatitude() != null) {
            gridCustomerMapper.insertWithLocation(customer, createReqVO.getLongitude(), createReqVO.getLatitude());
        } else {
            gridCustomerMapper.insert(customer);
        }

        // 自动创建客户-网格关系（根据坐标和客户类型）
        if (createReqVO.getLongitude() != null && createReqVO.getLatitude() != null) {
            String customerType = customer.getCustomerType();
            if ("ZERODAI".equals(customerType) || "HUINONG_LOAN".equals(customerType) ||
                "COMMUNITY".equals(customerType) || "TINGTANG".equals(customerType)) {
                autoCreateCustomerGridRelations(customer.getId(), createReqVO.getLongitude(),
                    createReqVO.getLatitude(), customerType);
            }
        }

        // 返回
        return customer.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomer(GridCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerExists(updateReqVO.getId());

        // 获取原客户信息，用于判断坐标是否变化
        GridCustomerDO existingCustomer = gridCustomerMapper.selectById(updateReqVO.getId());

        // 更新
        GridCustomerDO updateObj = BeanUtils.toBean(updateReqVO, GridCustomerDO.class);

        // 惠农站点现在存储在 grid_info 表中，stationId 就是 gridId
        if (updateReqVO.getStationId() != null && updateObj.getGridId() == null) {
            updateObj.setGridId(updateReqVO.getStationId());
        }

        // 如果提供了经纬度，使用 updateWithLocation 方法处理位置信息
        if (updateReqVO.getLongitude() != null && updateReqVO.getLatitude() != null) {
            gridCustomerMapper.updateWithLocation(updateObj, updateReqVO.getLongitude(), updateReqVO.getLatitude());
        } else {
            gridCustomerMapper.updateById(updateObj);
        }

        // 自动更新客户-网格关系（如果坐标发生变化）
        if (updateReqVO.getLongitude() != null && updateReqVO.getLatitude() != null) {
            String customerType = updateObj.getCustomerType() != null ? updateObj.getCustomerType() : existingCustomer.getCustomerType();

            // 只有这几种类型的客户才需要自动更新网格关系
            if ("ZERODAI".equals(customerType) || "HUINONG_LOAN".equals(customerType) ||
                "COMMUNITY".equals(customerType) || "TINGTANG".equals(customerType)) {

                boolean locationChanged = isLocationChanged(existingCustomer,
                    updateReqVO.getLongitude(), updateReqVO.getLatitude());

                if (locationChanged) {
                    // 删除旧的网格关系
                    deleteCustomerGridRelationsByType(updateReqVO.getId(), customerType);

                    // 根据新坐标创建新的网格关系
                    autoCreateCustomerGridRelations(updateReqVO.getId(),
                        updateReqVO.getLongitude(),
                        updateReqVO.getLatitude(),
                        customerType);
                }
            }
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        // 校验存在
        validateCustomerExists(id);
        // 删除
        gridCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerListByIds(List<Long> ids) {
        // 删除
        gridCustomerMapper.deleteByIds(ids);
        }


    private void validateCustomerExists(Long id) {
        if (gridCustomerMapper.selectById(id) == null) {
            throw exception(CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public GridCustomerDO getCustomer(Long id) {
        return gridCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<GridCustomerDO> getCustomerPage(GridCustomerPageReqVO pageReqVO) {
        return gridCustomerMapper.selectPage(pageReqVO);
    }

    /**
     * 根据坐标和客户类型自动创建客户-网格关系
     *
     * @param customerId 客户ID
     * @param longitude 经度
     * @param latitude 纬度
     * @param customerType 客户类型（ZERODAI、HUINONG_LOAN、COMMUNITY、TINGTANG）
     */
    private void autoCreateCustomerGridRelations(Long customerId, Double longitude, Double latitude, String customerType) {
        List<GridInfoDO> grids = null;

        // 根据客户类型查找相应的网格
        if ("ZERODAI".equals(customerType)) {
            // 零贷客户 -> 查找 ZERODAI 网格
            grids = gridInfoMapper.selectZerodaiGridsByLocation(longitude, latitude);
        } else if ("HUINONG_LOAN".equals(customerType)) {
            // 惠农贷款客户 -> 查找 HUINONG 网格
            grids = gridInfoMapper.selectHuinongGridsByLocation(longitude, latitude);
        } else if ("COMMUNITY".equals(customerType)) {
            // 社区客户 -> 查找 COMMUNITY 网格
            grids = gridInfoMapper.selectCommunityGridsByLocation(longitude, latitude);
        } else if ("TINGTANG".equals(customerType)) {
            // 厅堂客户 -> 查找 LOBBY 网格
            grids = gridInfoMapper.selectLobbyGridsByLocation(longitude, latitude);
        }

        // 如果没有找到匹配的网格，记录警告
        if (grids == null || grids.isEmpty()) {
            System.out.println("警告：客户坐标 [" + longitude + ", " + latitude +
                "] 未找到匹配的" + customerType + "网格");
            return;
        }

        // 为每个找到的网格创建客户-网格关系
        for (GridInfoDO grid : grids) {
            createCustomerGridRelation(customerId, grid);
        }

        // 如果找到了网格，将第一个网格设置为主网格
        if (!grids.isEmpty()) {
            GridCustomerDO updateCustomer = new GridCustomerDO();
            updateCustomer.setId(customerId);
            updateCustomer.setGridId(grids.get(0).getId());
            gridCustomerMapper.updateById(updateCustomer);
        }
    }

    /**
     * 创建单个客户-网格关系记录
     *
     * @param customerId 客户ID
     * @param grid 网格信息
     */
    private void createCustomerGridRelation(Long customerId, GridInfoDO grid) {
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
     * 根据客户类型删除客户的网格关系（物理删除）
     *
     * @param customerId 客户ID
     * @param customerType 客户类型（ZERODAI, HUINONG_LOAN, COMMUNITY, TINGTANG）
     */
    private void deleteCustomerGridRelationsByType(Long customerId, String customerType) {
        String gridType = getGridTypeByCustomerType(customerType);
        if (gridType == null) {
            return;
        }

        // 根据网格类型物理删除关系
        if ("ZERODAI".equals(gridType)) {
            customerGridRelationMapper.physicalDeleteZerodaiRelationsByCustomerId(customerId);
        } else if ("HUINONG".equals(gridType)) {
            customerGridRelationMapper.physicalDeleteHuinongRelationsByCustomerId(customerId);
        } else if ("COMMUNITY".equals(gridType)) {
            customerGridRelationMapper.physicalDeleteCommunityRelationsByCustomerId(customerId);
        } else if ("LOBBY".equals(gridType)) {
            customerGridRelationMapper.physicalDeleteLobbyRelationsByCustomerId(customerId);
        }
    }

    /**
     * 根据客户类型获取对应的网格类型
     *
     * @param customerType 客户类型
     * @return 网格类型
     */
    private String getGridTypeByCustomerType(String customerType) {
        switch (customerType) {
            case "ZERODAI":
                return "ZERODAI";
            case "HUINONG_LOAN":
                return "HUINONG";
            case "COMMUNITY":
                return "COMMUNITY";
            case "TINGTANG":
                return "LOBBY";  // 厅堂客户类型映射到 LOBBY 网格类型
            default:
                return null;
        }
    }

    /**
     * 判断客户位置是否发生变化
     *
     * @param existingCustomer 原客户信息
     * @param newLongitude 新经度
     * @param newLatitude 新纬度
     * @return 是否变化
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

    /**
     * 网格创建后自动关联包含在网格内的客户
     * 用于网格创建/更新后，查找所有坐标在网格边界内的客户，并建立客户-网格关系
     *
     * @param gridId 网格ID
     * @param gridType 网格类型（ZERODAI, HUINONG, COMMUNITY, LOBBY）
     * @return 关联的客户数量
     */
    public int autoLinkCustomersToGrid(Long gridId, String gridType) {
        // 1. 根据网格类型确定对应的客户类型
        String customerType = getCustomerTypeByGridType(gridType);
        if (customerType == null) {
            System.out.println("[网格关联客户] 未知的网格类型: " + gridType);
            return 0;
        }

        // 2. 查找包含在网格内的客户
        List<GridCustomerDO> customers = gridCustomerMapper.selectCustomersWithinGrid(gridId, customerType);
        if (customers.isEmpty()) {
            System.out.println("[网格关联客户] 网格 [" + gridId + "] 内未找到 " + customerType + " 类型的客户");
            return 0;
        }

        System.out.println("[网格关联客户] 网格 [" + gridId + "] 内找到 " + customers.size() + " 个 " + customerType + " 类型的客户");

        // 3. 获取网格信息（用于冗余字段）
        GridInfoDO gridInfo = gridInfoMapper.selectById(gridId);
        if (gridInfo == null) {
            System.out.println("[网格关联客户] 网格 [" + gridId + "] 不存在");
            return 0;
        }

        // 4. 为每个客户创建客户-网格关系
        int createdCount = 0;
        for (GridCustomerDO customer : customers) {
            try {
                // 检查关系是否已存在（避免重复创建）
                List<GridCustomerGridRelationDO> existingRelations =
                    customerGridRelationMapper.selectListByCustomerId(customer.getId());

                // 检查是否已经有与该网格的关系
                boolean relationExists = existingRelations.stream()
                    .anyMatch(r -> r.getGridId().equals(gridId));

                if (!relationExists) {
                    // 创建新关系
                    createCustomerGridRelation(customer.getId(), gridInfo);
                    createdCount++;
                    System.out.println("[网格关联客户] 创建关系: 客户 [" + customer.getId() + "] -> 网格 [" + gridId + "]");
                } else {
                    System.out.println("[网格关联客户] 关系已存在: 客户 [" + customer.getId() + "] -> 网格 [" + gridId + "]");
                }
            } catch (Exception e) {
                System.out.println("[网格关联客户] 创建关系失败: 客户 [" + customer.getId() + "] -> 网格 [" + gridId + "], 错误: " + e.getMessage());
            }
        }

        System.out.println("[网格关联客户] 网格 [" + gridId + "] 成功关联 " + createdCount + " 个客户");
        return createdCount;
    }

    /**
     * 根据网格类型获取对应的客户类型
     *
     * @param gridType 网格类型（ZERODAI, HUINONG, COMMUNITY, LOBBY）
     * @return 客户类型（ZERODAI, HUINONG_LOAN, COMMUNITY, TINGTANG）
     */
    private String getCustomerTypeByGridType(String gridType) {
        switch (gridType) {
            case "ZERODAI":
                return "ZERODAI";
            case "HUINONG":
                return "HUINONG_LOAN";
            case "COMMUNITY":
                return "COMMUNITY";
            case "LOBBY":
                return "TINGTANG";  // LOBBY 网格类型映射到 TINGTANG 客户类型
            default:
                return null;
        }
    }

}