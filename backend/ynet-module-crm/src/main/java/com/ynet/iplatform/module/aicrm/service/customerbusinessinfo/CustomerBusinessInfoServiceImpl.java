package com.ynet.iplatform.module.aicrm.service.customerbusinessinfo;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerbusinessinfo.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerbusinessinfo.CustomerBusinessInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerbusinessinfo.CustomerBusinessInfoMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户经营信息表（精简版，只包含经营相关核心字段） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerBusinessInfoServiceImpl implements CustomerBusinessInfoService {

    @Resource
    private CustomerBusinessInfoMapper customerBusinessInfoMapper;

    @Override
    public Long createCustomerBusinessInfo(CustomerBusinessInfoSaveReqVO createReqVO) {
        // 插入
        CustomerBusinessInfoDO customerBusinessInfo = BeanUtils.toBean(createReqVO, CustomerBusinessInfoDO.class);
        customerBusinessInfoMapper.insert(customerBusinessInfo);

        // 返回
        return customerBusinessInfo.getId();
    }

    @Override
    public void updateCustomerBusinessInfo(CustomerBusinessInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerBusinessInfoExists(updateReqVO.getId());
        // 更新
        CustomerBusinessInfoDO updateObj = BeanUtils.toBean(updateReqVO, CustomerBusinessInfoDO.class);
        customerBusinessInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerBusinessInfo(Long id) {
        // 校验存在
        validateCustomerBusinessInfoExists(id);
        // 删除
        customerBusinessInfoMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerBusinessInfoListByIds(List<Long> ids) {
        // 删除
        customerBusinessInfoMapper.deleteByIds(ids);
        }


    private void validateCustomerBusinessInfoExists(Long id) {
        if (customerBusinessInfoMapper.selectById(id) == null) {
            throw exception(CUSTOMER_BUSINESS_INFO_NOT_EXISTS);
        }
    }

    @Override
    public CustomerBusinessInfoDO getCustomerBusinessInfo(Long id) {
        return customerBusinessInfoMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerBusinessInfoDO> getCustomerBusinessInfoPage(CustomerBusinessInfoPageReqVO pageReqVO) {
        return customerBusinessInfoMapper.selectPage(pageReqVO);
    }

}