package cn.iocoder.yudao.module.aicrm.service.customercoupon;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customercoupon.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercoupon.CustomerCouponDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customercoupon.CustomerCouponMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户卡券信息表（零售客户特有） Service 实现类
 *
 * @author 芋道源码
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