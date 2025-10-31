package cn.iocoder.yudao.module.aicrm.service.customerdiscountoverview;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdiscountoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdiscountoverview.CustomerDiscountOverviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerdiscountoverview.CustomerDiscountOverviewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户贴现业务概览 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerDiscountOverviewServiceImpl implements CustomerDiscountOverviewService {

    @Resource
    private CustomerDiscountOverviewMapper customerDiscountOverviewMapper;

    @Override
    public Long createCustomerDiscountOverview(CustomerDiscountOverviewSaveReqVO createReqVO) {
        // 插入
        CustomerDiscountOverviewDO customerDiscountOverview = BeanUtils.toBean(createReqVO, CustomerDiscountOverviewDO.class);
        customerDiscountOverviewMapper.insert(customerDiscountOverview);

        // 返回
        return customerDiscountOverview.getId();
    }

    @Override
    public void updateCustomerDiscountOverview(CustomerDiscountOverviewSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerDiscountOverviewExists(updateReqVO.getId());
        // 更新
        CustomerDiscountOverviewDO updateObj = BeanUtils.toBean(updateReqVO, CustomerDiscountOverviewDO.class);
        customerDiscountOverviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerDiscountOverview(Long id) {
        // 校验存在
        validateCustomerDiscountOverviewExists(id);
        // 删除
        customerDiscountOverviewMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerDiscountOverviewListByIds(List<Long> ids) {
        // 删除
        customerDiscountOverviewMapper.deleteByIds(ids);
        }


    private void validateCustomerDiscountOverviewExists(Long id) {
        if (customerDiscountOverviewMapper.selectById(id) == null) {
            throw exception(CUSTOMER_DISCOUNT_OVERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public CustomerDiscountOverviewDO getCustomerDiscountOverview(Long id) {
        return customerDiscountOverviewMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDiscountOverviewDO> getCustomerDiscountOverviewPage(CustomerDiscountOverviewPageReqVO pageReqVO) {
        return customerDiscountOverviewMapper.selectPage(pageReqVO);
    }

}