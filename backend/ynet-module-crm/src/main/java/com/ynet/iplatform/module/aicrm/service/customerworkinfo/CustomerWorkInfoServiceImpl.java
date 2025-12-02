package com.ynet.iplatform.module.aicrm.service.customerworkinfo;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerworkinfo.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerworkinfo.CustomerWorkInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerworkinfo.CustomerWorkInfoMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户工作信息表（精简版，只包含工作相关核心字段） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerWorkInfoServiceImpl implements CustomerWorkInfoService {

    @Resource
    private CustomerWorkInfoMapper customerWorkInfoMapper;

    @Override
    public Long createCustomerWorkInfo(CustomerWorkInfoSaveReqVO createReqVO) {
        // 插入
        CustomerWorkInfoDO customerWorkInfo = BeanUtils.toBean(createReqVO, CustomerWorkInfoDO.class);
        customerWorkInfoMapper.insert(customerWorkInfo);

        // 返回
        return customerWorkInfo.getId();
    }

    @Override
    public void updateCustomerWorkInfo(CustomerWorkInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerWorkInfoExists(updateReqVO.getId());
        // 更新
        CustomerWorkInfoDO updateObj = BeanUtils.toBean(updateReqVO, CustomerWorkInfoDO.class);
        customerWorkInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerWorkInfo(Long id) {
        // 校验存在
        validateCustomerWorkInfoExists(id);
        // 删除
        customerWorkInfoMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerWorkInfoListByIds(List<Long> ids) {
        // 删除
        customerWorkInfoMapper.deleteByIds(ids);
        }


    private void validateCustomerWorkInfoExists(Long id) {
        if (customerWorkInfoMapper.selectById(id) == null) {
            throw exception(CUSTOMER_WORK_INFO_NOT_EXISTS);
        }
    }

    @Override
    public CustomerWorkInfoDO getCustomerWorkInfo(Long id) {
        return customerWorkInfoMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerWorkInfoDO> getCustomerWorkInfoPage(CustomerWorkInfoPageReqVO pageReqVO) {
        return customerWorkInfoMapper.selectPage(pageReqVO);
    }

}