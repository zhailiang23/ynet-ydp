package cn.iocoder.yudao.module.aicrm.service.customercontribution;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customercontribution.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercontribution.CustomerContributionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customercontribution.CustomerContributionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户贡献度信息表（与客户主表1对1关系） Service 实现类
 *
 * @author 芋道源码
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