package com.ynet.iplatform.module.grid.service.zerodaicustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer.GridZerodaiCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.zerodaicustomer.GridZerodaiCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 零贷客户扩展 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridZerodaiCustomerServiceImpl implements GridZerodaiCustomerService {

    @Resource
    private GridZerodaiCustomerMapper zerodaiCustomerMapper;

    @Override
    public Long createZerodaiCustomer(GridZerodaiCustomerSaveReqVO createReqVO) {
        // 插入
        GridZerodaiCustomerDO zerodaiCustomer = BeanUtils.toBean(createReqVO, GridZerodaiCustomerDO.class);
        zerodaiCustomerMapper.insert(zerodaiCustomer);

        // 返回
        return zerodaiCustomer.getId();
    }

    @Override
    public void updateZerodaiCustomer(GridZerodaiCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateZerodaiCustomerExists(updateReqVO.getId());
        // 更新
        GridZerodaiCustomerDO updateObj = BeanUtils.toBean(updateReqVO, GridZerodaiCustomerDO.class);
        zerodaiCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteZerodaiCustomer(Long id) {
        // 校验存在
        validateZerodaiCustomerExists(id);
        // 删除
        zerodaiCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteZerodaiCustomerListByIds(List<Long> ids) {
        // 删除
        zerodaiCustomerMapper.deleteByIds(ids);
        }


    private void validateZerodaiCustomerExists(Long id) {
        if (zerodaiCustomerMapper.selectById(id) == null) {
            throw exception(ZERODAI_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public GridZerodaiCustomerDO getZerodaiCustomer(Long id) {
        return zerodaiCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<GridZerodaiCustomerDO> getZerodaiCustomerPage(GridZerodaiCustomerPageReqVO pageReqVO) {
        return zerodaiCustomerMapper.selectPage(pageReqVO);
    }

}