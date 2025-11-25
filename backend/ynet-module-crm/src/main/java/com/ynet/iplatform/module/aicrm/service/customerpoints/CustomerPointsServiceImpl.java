package com.ynet.iplatform.module.aicrm.service.customerpoints;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerpoints.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerpoints.CustomerPointsDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerpoints.CustomerPointsMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户积分信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerPointsServiceImpl implements CustomerPointsService {

    @Resource
    private CustomerPointsMapper customerPointsMapper;

    @Override
    public Long createCustomerPoints(CustomerPointsSaveReqVO createReqVO) {
        // 插入
        CustomerPointsDO customerPoints = BeanUtils.toBean(createReqVO, CustomerPointsDO.class);
        customerPointsMapper.insert(customerPoints);

        // 返回
        return customerPoints.getId();
    }

    @Override
    public void updateCustomerPoints(CustomerPointsSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerPointsExists(updateReqVO.getId());
        // 更新
        CustomerPointsDO updateObj = BeanUtils.toBean(updateReqVO, CustomerPointsDO.class);
        customerPointsMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerPoints(Long id) {
        // 校验存在
        validateCustomerPointsExists(id);
        // 删除
        customerPointsMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerPointsListByIds(List<Long> ids) {
        // 删除
        customerPointsMapper.deleteByIds(ids);
        }


    private void validateCustomerPointsExists(Long id) {
        if (customerPointsMapper.selectById(id) == null) {
            throw exception(CUSTOMER_POINTS_NOT_EXISTS);
        }
    }

    @Override
    public CustomerPointsDO getCustomerPoints(Long id) {
        return customerPointsMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerPointsDO> getCustomerPointsPage(CustomerPointsPageReqVO pageReqVO) {
        return customerPointsMapper.selectPage(pageReqVO);
    }

}