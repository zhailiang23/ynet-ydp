package cn.iocoder.yudao.module.aicrm.service.customercredit;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customercredit.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercredit.CustomerCreditDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customercredit.CustomerCreditMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户授信信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerCreditServiceImpl implements CustomerCreditService {

    @Resource
    private CustomerCreditMapper customerCreditMapper;

    @Override
    public Long createCustomerCredit(CustomerCreditSaveReqVO createReqVO) {
        // 插入
        CustomerCreditDO customerCredit = BeanUtils.toBean(createReqVO, CustomerCreditDO.class);
        customerCreditMapper.insert(customerCredit);

        // 返回
        return customerCredit.getId();
    }

    @Override
    public void updateCustomerCredit(CustomerCreditSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerCreditExists(updateReqVO.getId());
        // 更新
        CustomerCreditDO updateObj = BeanUtils.toBean(updateReqVO, CustomerCreditDO.class);
        customerCreditMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerCredit(Long id) {
        // 校验存在
        validateCustomerCreditExists(id);
        // 删除
        customerCreditMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerCreditListByIds(List<Long> ids) {
        // 删除
        customerCreditMapper.deleteByIds(ids);
        }


    private void validateCustomerCreditExists(Long id) {
        if (customerCreditMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CREDIT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerCreditDO getCustomerCredit(Long id) {
        return customerCreditMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerCreditDO> getCustomerCreditPage(CustomerCreditPageReqVO pageReqVO) {
        return customerCreditMapper.selectPage(pageReqVO);
    }

}