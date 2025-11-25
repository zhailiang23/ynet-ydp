package com.ynet.iplatform.module.aicrm.service.customercreditdetail;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercreditdetail.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercreditdetail.CustomerCreditDetailDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customercreditdetail.CustomerCreditDetailMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户授信使用明细表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerCreditDetailServiceImpl implements CustomerCreditDetailService {

    @Resource
    private CustomerCreditDetailMapper customerCreditDetailMapper;

    @Override
    public Long createCustomerCreditDetail(CustomerCreditDetailSaveReqVO createReqVO) {
        // 插入
        CustomerCreditDetailDO customerCreditDetail = BeanUtils.toBean(createReqVO, CustomerCreditDetailDO.class);
        customerCreditDetailMapper.insert(customerCreditDetail);

        // 返回
        return customerCreditDetail.getId();
    }

    @Override
    public void updateCustomerCreditDetail(CustomerCreditDetailSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerCreditDetailExists(updateReqVO.getId());
        // 更新
        CustomerCreditDetailDO updateObj = BeanUtils.toBean(updateReqVO, CustomerCreditDetailDO.class);
        customerCreditDetailMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerCreditDetail(Long id) {
        // 校验存在
        validateCustomerCreditDetailExists(id);
        // 删除
        customerCreditDetailMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerCreditDetailListByIds(List<Long> ids) {
        // 删除
        customerCreditDetailMapper.deleteByIds(ids);
        }


    private void validateCustomerCreditDetailExists(Long id) {
        if (customerCreditDetailMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CREDIT_DETAIL_NOT_EXISTS);
        }
    }

    @Override
    public CustomerCreditDetailDO getCustomerCreditDetail(Long id) {
        return customerCreditDetailMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerCreditDetailDO> getCustomerCreditDetailPage(CustomerCreditDetailPageReqVO pageReqVO) {
        return customerCreditDetailMapper.selectPage(pageReqVO);
    }

}