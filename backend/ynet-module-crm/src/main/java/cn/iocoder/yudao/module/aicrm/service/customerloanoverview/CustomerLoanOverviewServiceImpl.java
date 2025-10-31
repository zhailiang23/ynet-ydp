package cn.iocoder.yudao.module.aicrm.service.customerloanoverview;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerloanoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerloanoverview.CustomerLoanOverviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerloanoverview.CustomerLoanOverviewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户贷款业务概览 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerLoanOverviewServiceImpl implements CustomerLoanOverviewService {

    @Resource
    private CustomerLoanOverviewMapper customerLoanOverviewMapper;

    @Override
    public Long createCustomerLoanOverview(CustomerLoanOverviewSaveReqVO createReqVO) {
        // 插入
        CustomerLoanOverviewDO customerLoanOverview = BeanUtils.toBean(createReqVO, CustomerLoanOverviewDO.class);
        customerLoanOverviewMapper.insert(customerLoanOverview);

        // 返回
        return customerLoanOverview.getId();
    }

    @Override
    public void updateCustomerLoanOverview(CustomerLoanOverviewSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerLoanOverviewExists(updateReqVO.getId());
        // 更新
        CustomerLoanOverviewDO updateObj = BeanUtils.toBean(updateReqVO, CustomerLoanOverviewDO.class);
        customerLoanOverviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerLoanOverview(Long id) {
        // 校验存在
        validateCustomerLoanOverviewExists(id);
        // 删除
        customerLoanOverviewMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerLoanOverviewListByIds(List<Long> ids) {
        // 删除
        customerLoanOverviewMapper.deleteByIds(ids);
        }


    private void validateCustomerLoanOverviewExists(Long id) {
        if (customerLoanOverviewMapper.selectById(id) == null) {
            throw exception(CUSTOMER_LOAN_OVERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public CustomerLoanOverviewDO getCustomerLoanOverview(Long id) {
        return customerLoanOverviewMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerLoanOverviewDO> getCustomerLoanOverviewPage(CustomerLoanOverviewPageReqVO pageReqVO) {
        return customerLoanOverviewMapper.selectPage(pageReqVO);
    }

}