package com.ynet.iplatform.module.aicrm.service.customercoupon;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercoupon.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercoupon.CustomerCouponDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customercoupon.CustomerCouponMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户卡券信息表（零售客户特有） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerCouponServiceImpl implements CustomerCouponService {

    @Resource
    private CustomerCouponMapper customerCouponMapper;

    @Override
    public Long createCustomerCoupon(CustomerCouponSaveReqVO createReqVO) {
        // 插入
        CustomerCouponDO customerCoupon = BeanUtils.toBean(createReqVO, CustomerCouponDO.class);
        customerCouponMapper.insert(customerCoupon);

        // 返回
        return customerCoupon.getId();
    }

    @Override
    public void updateCustomerCoupon(CustomerCouponSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerCouponExists(updateReqVO.getId());
        // 更新
        CustomerCouponDO updateObj = BeanUtils.toBean(updateReqVO, CustomerCouponDO.class);
        customerCouponMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerCoupon(Long id) {
        // 校验存在
        validateCustomerCouponExists(id);
        // 删除
        customerCouponMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerCouponListByIds(List<Long> ids) {
        // 删除
        customerCouponMapper.deleteByIds(ids);
        }


    private void validateCustomerCouponExists(Long id) {
        if (customerCouponMapper.selectById(id) == null) {
            throw exception(CUSTOMER_COUPON_NOT_EXISTS);
        }
    }

    @Override
    public CustomerCouponDO getCustomerCoupon(Long id) {
        return customerCouponMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerCouponDO> getCustomerCouponPage(CustomerCouponPageReqVO pageReqVO) {
        return customerCouponMapper.selectPage(pageReqVO);
    }

}