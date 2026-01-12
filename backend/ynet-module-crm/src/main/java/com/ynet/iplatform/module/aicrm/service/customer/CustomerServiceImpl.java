package com.ynet.iplatform.module.aicrm.service.customer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customer.vo.*;
import com.ynet.iplatform.module.aicrm.controller.app.customer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.retailcustomer.RetailCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customer.CustomerMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.retailcustomer.RetailCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM客户主表(零售+对公共用) Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private RetailCustomerMapper retailCustomerMapper;

    @Override
    public Long createCustomer(CustomerSaveReqVO createReqVO) {
        // 插入
        CustomerDO customer = BeanUtils.toBean(createReqVO, CustomerDO.class);
        customerMapper.insert(customer);

        // 返回
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerExists(updateReqVO.getId());
        // 更新
        CustomerDO updateObj = BeanUtils.toBean(updateReqVO, CustomerDO.class);
        customerMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomer(Long id) {
        // 校验存在
        validateCustomerExists(id);
        // 删除
        customerMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerListByIds(List<Long> ids) {
        // 删除
        customerMapper.deleteByIds(ids);
        }


    private void validateCustomerExists(Long id) {
        if (customerMapper.selectById(id) == null) {
            throw exception(CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public CustomerDO getCustomer(Long id) {
        return customerMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerDO> getCustomerPage(CustomerPageReqVO pageReqVO) {
        return customerMapper.selectPage(pageReqVO);
    }

    // ==================== 移动端接口实现 ====================

    @Override
    public PageResult<AppCustomerRespVO> getAppCustomerPage(AppCustomerPageReqVO pageReqVO) {
        // 转换为管理后台查询参数
        CustomerPageReqVO adminPageReqVO = BeanUtils.toBean(pageReqVO, CustomerPageReqVO.class);

        // 查询数据
        PageResult<CustomerDO> pageResult = customerMapper.selectPage(adminPageReqVO);

        // 转换为移动端响应对象
        return BeanUtils.toBean(pageResult, AppCustomerRespVO.class);
    }

    @Override
    public AppCustomerRespVO getAppCustomer(Long id) {
        CustomerDO customer = customerMapper.selectById(id);
        return BeanUtils.toBean(customer, AppCustomerRespVO.class);
    }

    @Override
    public CustomerDO searchCustomerByMobileOrIdCard(String mobile, String idCardNo) {
        // 参数校验：手机号和证件号至少提供一个
        if (StrUtil.isBlank(mobile) && StrUtil.isBlank(idCardNo)) {
            return null;
        }

        // 构建查询条件
        LambdaQueryWrapper<RetailCustomerDO> queryWrapper = new LambdaQueryWrapper<>();

        // 优先按手机号查询
        if (StrUtil.isNotBlank(mobile)) {
            queryWrapper.eq(RetailCustomerDO::getMobile, mobile);
        }
        // 如果手机号为空，则按证件号查询
        else if (StrUtil.isNotBlank(idCardNo)) {
            queryWrapper.eq(RetailCustomerDO::getIdCardNo, idCardNo);
        }

        // 查询零售客户信息
        RetailCustomerDO retailCustomer = retailCustomerMapper.selectOne(queryWrapper);

        // 如果未找到，返回 null
        if (retailCustomer == null) {
            return null;
        }

        // 通过 customerId 查询主客户表
        CustomerDO customer = customerMapper.selectById(retailCustomer.getCustomerId());

        // 直接返回客户DO对象（admin和app端controller会各自进行VO转换）
        return customer;
    }

    @Override
    public List<CustomerDO> getSimpleCustomerList() {
        // 查询所有客户，按创建时间倒序排序，限制最多返回 1000 条
        LambdaQueryWrapper<CustomerDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(CustomerDO::getCreateTime)
                .last("LIMIT 1000");
        return customerMapper.selectList(queryWrapper);
    }

}