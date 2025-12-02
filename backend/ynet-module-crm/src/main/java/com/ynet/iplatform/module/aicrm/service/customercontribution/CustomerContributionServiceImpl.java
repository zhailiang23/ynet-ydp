package com.ynet.iplatform.module.aicrm.service.customercontribution;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercontribution.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontribution.CustomerContributionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customercontribution.CustomerContributionMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户贡献度信息表（与客户主表1对1关系） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerContributionServiceImpl implements CustomerContributionService {

    @Resource
    private CustomerContributionMapper customerContributionMapper;

    @Override
    public Long createCustomerContribution(CustomerContributionSaveReqVO createReqVO) {
        // 插入
        CustomerContributionDO customerContribution = BeanUtils.toBean(createReqVO, CustomerContributionDO.class);
        customerContributionMapper.insert(customerContribution);

        // 返回
        return customerContribution.getId();
    }

    @Override
    public void updateCustomerContribution(CustomerContributionSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerContributionExists(updateReqVO.getId());
        // 更新
        CustomerContributionDO updateObj = BeanUtils.toBean(updateReqVO, CustomerContributionDO.class);
        customerContributionMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerContribution(Long id) {
        // 校验存在
        validateCustomerContributionExists(id);
        // 删除
        customerContributionMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerContributionListByIds(List<Long> ids) {
        // 删除
        customerContributionMapper.deleteByIds(ids);
        }


    private void validateCustomerContributionExists(Long id) {
        if (customerContributionMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CONTRIBUTION_NOT_EXISTS);
        }
    }

    @Override
    public CustomerContributionDO getCustomerContribution(Long id) {
        return customerContributionMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerContributionDO> getCustomerContributionPage(CustomerContributionPageReqVO pageReqVO) {
        return customerContributionMapper.selectPage(pageReqVO);
    }

}