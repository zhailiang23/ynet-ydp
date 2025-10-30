package cn.iocoder.yudao.module.aicrm.service.customerdemand;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdemand.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdemand.CustomerDemandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerdemand.CustomerDemandMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户需求信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerDemandServiceImpl implements CustomerDemandService {

    @Resource
    private CustomerDemandMapper customerDemandMapper;

    @Override
    public Long createCustomerDemand(CustomerDemandSaveReqVO createReqVO) {
        // 插入
        CustomerDemandDO customerDemand = BeanUtils.toBean(createReqVO, CustomerDemandDO.class);
        customerDemandMapper.insert(customerDemand);

        // 返回
        return customerDemand.getId();
    }

    @Override
    public void updateCustomerDemand(CustomerDemandSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerDemandExists(updateReqVO.getId());
        // 更新
        CustomerDemandDO updateObj = BeanUtils.toBean(updateReqVO, CustomerDemandDO.class);
        customerDemandMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerDemand(Long id) {
        // 校验存在
        validateCustomerDemandExists(id);
        // 删除
        customerDemandMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerDemandListByIds(List<Long> ids) {
        // 删除
        customerDemandMapper.deleteByIds(ids);
        }


    private void validateCustomerDemandExists(Long id) {
        if (customerDemandMapper.selectById(id) == null) {
            throw exception(CUSTOMER_DEMAND_NOT_EXISTS);
        }
    }

    @Override
    public CustomerDemandDO getCustomerDemand(Long id) {
        return customerDemandMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDemandDO> getCustomerDemandPage(CustomerDemandPageReqVO pageReqVO) {
        return customerDemandMapper.selectPage(pageReqVO);
    }

}