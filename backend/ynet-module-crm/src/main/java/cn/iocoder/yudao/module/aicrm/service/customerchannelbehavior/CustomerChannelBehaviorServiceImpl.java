package cn.iocoder.yudao.module.aicrm.service.customerchannelbehavior;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerchannelbehavior.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerchannelbehavior.CustomerChannelBehaviorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerchannelbehavior.CustomerChannelBehaviorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户渠道行为信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerChannelBehaviorServiceImpl implements CustomerChannelBehaviorService {

    @Resource
    private CustomerChannelBehaviorMapper customerChannelBehaviorMapper;

    @Override
    public Long createCustomerChannelBehavior(CustomerChannelBehaviorSaveReqVO createReqVO) {
        // 插入
        CustomerChannelBehaviorDO customerChannelBehavior = BeanUtils.toBean(createReqVO, CustomerChannelBehaviorDO.class);
        customerChannelBehaviorMapper.insert(customerChannelBehavior);

        // 返回
        return customerChannelBehavior.getId();
    }

    @Override
    public void updateCustomerChannelBehavior(CustomerChannelBehaviorSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerChannelBehaviorExists(updateReqVO.getId());
        // 更新
        CustomerChannelBehaviorDO updateObj = BeanUtils.toBean(updateReqVO, CustomerChannelBehaviorDO.class);
        customerChannelBehaviorMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerChannelBehavior(Long id) {
        // 校验存在
        validateCustomerChannelBehaviorExists(id);
        // 删除
        customerChannelBehaviorMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerChannelBehaviorListByIds(List<Long> ids) {
        // 删除
        customerChannelBehaviorMapper.deleteByIds(ids);
        }


    private void validateCustomerChannelBehaviorExists(Long id) {
        if (customerChannelBehaviorMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CHANNEL_BEHAVIOR_NOT_EXISTS);
        }
    }

    @Override
    public CustomerChannelBehaviorDO getCustomerChannelBehavior(Long id) {
        return customerChannelBehaviorMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerChannelBehaviorDO> getCustomerChannelBehaviorPage(CustomerChannelBehaviorPageReqVO pageReqVO) {
        return customerChannelBehaviorMapper.selectPage(pageReqVO);
    }

}