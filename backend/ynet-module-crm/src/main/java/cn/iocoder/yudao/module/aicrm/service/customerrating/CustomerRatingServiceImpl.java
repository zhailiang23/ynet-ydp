package cn.iocoder.yudao.module.aicrm.service.customerrating;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerrating.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerrating.CustomerRatingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerrating.CustomerRatingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户评级信息表（与客户主表1对1关系） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerRatingServiceImpl implements CustomerRatingService {

    @Resource
    private CustomerRatingMapper customerRatingMapper;

    @Override
    public Long createCustomerRating(CustomerRatingSaveReqVO createReqVO) {
        // 插入
        CustomerRatingDO customerRating = BeanUtils.toBean(createReqVO, CustomerRatingDO.class);
        customerRatingMapper.insert(customerRating);

        // 返回
        return customerRating.getId();
    }

    @Override
    public void updateCustomerRating(CustomerRatingSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerRatingExists(updateReqVO.getId());
        // 更新
        CustomerRatingDO updateObj = BeanUtils.toBean(updateReqVO, CustomerRatingDO.class);
        customerRatingMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerRating(Long id) {
        // 校验存在
        validateCustomerRatingExists(id);
        // 删除
        customerRatingMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerRatingListByIds(List<Long> ids) {
        // 删除
        customerRatingMapper.deleteByIds(ids);
        }


    private void validateCustomerRatingExists(Long id) {
        if (customerRatingMapper.selectById(id) == null) {
            throw exception(CUSTOMER_RATING_NOT_EXISTS);
        }
    }

    @Override
    public CustomerRatingDO getCustomerRating(Long id) {
        return customerRatingMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerRatingDO> getCustomerRatingPage(CustomerRatingPageReqVO pageReqVO) {
        return customerRatingMapper.selectPage(pageReqVO);
    }

}