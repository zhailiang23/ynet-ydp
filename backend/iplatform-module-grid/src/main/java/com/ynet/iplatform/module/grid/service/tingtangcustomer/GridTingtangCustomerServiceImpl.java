package com.ynet.iplatform.module.grid.service.tingtangcustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.tingtangcustomer.GridTingtangCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.tingtangcustomer.GridTingtangCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 厅堂客户扩展 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridTingtangCustomerServiceImpl implements GridTingtangCustomerService {

    @Resource
    private GridTingtangCustomerMapper tingtangCustomerMapper;

    @Override
    public Long createTingtangCustomer(GridTingtangCustomerSaveReqVO createReqVO) {
        // 插入
        GridTingtangCustomerDO tingtangCustomer = BeanUtils.toBean(createReqVO, GridTingtangCustomerDO.class);
        tingtangCustomerMapper.insert(tingtangCustomer);

        // 返回
        return tingtangCustomer.getId();
    }

    @Override
    public void updateTingtangCustomer(GridTingtangCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateTingtangCustomerExists(updateReqVO.getId());
        // 更新
        GridTingtangCustomerDO updateObj = BeanUtils.toBean(updateReqVO, GridTingtangCustomerDO.class);
        tingtangCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteTingtangCustomer(Long id) {
        // 校验存在
        validateTingtangCustomerExists(id);
        // 删除
        tingtangCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteTingtangCustomerListByIds(List<Long> ids) {
        // 删除
        tingtangCustomerMapper.deleteByIds(ids);
        }


    private void validateTingtangCustomerExists(Long id) {
        if (tingtangCustomerMapper.selectById(id) == null) {
            throw exception(TINGTANG_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public GridTingtangCustomerDO getTingtangCustomer(Long id) {
        return tingtangCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<GridTingtangCustomerDO> getTingtangCustomerPage(GridTingtangCustomerPageReqVO pageReqVO) {
        return tingtangCustomerMapper.selectPage(pageReqVO);
    }

}