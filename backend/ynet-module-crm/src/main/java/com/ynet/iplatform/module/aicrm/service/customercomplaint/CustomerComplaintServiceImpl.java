package com.ynet.iplatform.module.aicrm.service.customercomplaint;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercomplaint.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercomplaint.CustomerComplaintDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customercomplaint.CustomerComplaintMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户投诉信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerComplaintServiceImpl implements CustomerComplaintService {

    @Resource
    private CustomerComplaintMapper customerComplaintMapper;

    @Override
    public Long createCustomerComplaint(CustomerComplaintSaveReqVO createReqVO) {
        // 插入
        CustomerComplaintDO customerComplaint = BeanUtils.toBean(createReqVO, CustomerComplaintDO.class);
        customerComplaintMapper.insert(customerComplaint);

        // 返回
        return customerComplaint.getId();
    }

    @Override
    public void updateCustomerComplaint(CustomerComplaintSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerComplaintExists(updateReqVO.getId());
        // 更新
        CustomerComplaintDO updateObj = BeanUtils.toBean(updateReqVO, CustomerComplaintDO.class);
        customerComplaintMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerComplaint(Long id) {
        // 校验存在
        validateCustomerComplaintExists(id);
        // 删除
        customerComplaintMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerComplaintListByIds(List<Long> ids) {
        // 删除
        customerComplaintMapper.deleteByIds(ids);
        }


    private void validateCustomerComplaintExists(Long id) {
        if (customerComplaintMapper.selectById(id) == null) {
            throw exception(CUSTOMER_COMPLAINT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerComplaintDO getCustomerComplaint(Long id) {
        return customerComplaintMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerComplaintDO> getCustomerComplaintPage(CustomerComplaintPageReqVO pageReqVO) {
        return customerComplaintMapper.selectPage(pageReqVO);
    }

}