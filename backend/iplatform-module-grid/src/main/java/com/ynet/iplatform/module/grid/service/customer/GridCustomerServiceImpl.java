package com.ynet.iplatform.module.grid.service.customer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.tenant.core.context.TenantContextHolder;

import com.ynet.iplatform.module.grid.dal.mysql.customer.GridCustomerMapper;
import com.ynet.iplatform.module.grid.dal.mysql.huinongstation.GridHuinongStationMapper;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongstation.GridHuinongStationDO;

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
    private GridHuinongStationMapper huinongStationMapper;

    @Override
    public Long createCustomer(GridCustomerSaveReqVO createReqVO) {
        // 插入
        GridCustomerDO customer = BeanUtils.toBean(createReqVO, GridCustomerDO.class);

        // 设置租户 ID
        customer.setTenantId(TenantContextHolder.getTenantId());

        // 设置逻辑删除标记（使用自定义 XML 插入时需要手动设置）
        customer.setDeleted(false);

        // 如果传了 stationId，根据站点查询 gridId
        if (createReqVO.getStationId() != null && customer.getGridId() == null) {
            GridHuinongStationDO station = huinongStationMapper.selectById(createReqVO.getStationId());
            if (station != null && station.getGridId() != null) {
                customer.setGridId(station.getGridId());
            }
        }

        // 如果提供了经纬度，使用 insertWithLocation 方法处理位置信息
        if (createReqVO.getLongitude() != null && createReqVO.getLatitude() != null) {
            gridCustomerMapper.insertWithLocation(customer, createReqVO.getLongitude(), createReqVO.getLatitude());
        } else {
            gridCustomerMapper.insert(customer);
        }

        // 返回
        return customer.getId();
    }

    @Override
    public void updateCustomer(GridCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerExists(updateReqVO.getId());
        // 更新
        GridCustomerDO updateObj = BeanUtils.toBean(updateReqVO, GridCustomerDO.class);

        // 如果传了 stationId，根据站点查询 gridId
        if (updateReqVO.getStationId() != null && updateObj.getGridId() == null) {
            GridHuinongStationDO station = huinongStationMapper.selectById(updateReqVO.getStationId());
            if (station != null && station.getGridId() != null) {
                updateObj.setGridId(station.getGridId());
            }
        }

        // 如果提供了经纬度，使用 updateWithLocation 方法处理位置信息
        if (updateReqVO.getLongitude() != null && updateReqVO.getLatitude() != null) {
            gridCustomerMapper.updateWithLocation(updateObj, updateReqVO.getLongitude(), updateReqVO.getLatitude());
        } else {
            gridCustomerMapper.updateById(updateObj);
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

}