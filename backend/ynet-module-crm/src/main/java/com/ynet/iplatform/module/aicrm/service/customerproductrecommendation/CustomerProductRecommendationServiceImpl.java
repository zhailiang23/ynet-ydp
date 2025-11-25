package com.ynet.iplatform.module.aicrm.service.customerproductrecommendation;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerproductrecommendation.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductrecommendation.CustomerProductRecommendationDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customerproductrecommendation.CustomerProductRecommendationMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户产品推荐 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerProductRecommendationServiceImpl implements CustomerProductRecommendationService {

    @Resource
    private CustomerProductRecommendationMapper customerProductRecommendationMapper;

    @Override
    public Long createCustomerProductRecommendation(CustomerProductRecommendationSaveReqVO createReqVO) {
        // 插入
        CustomerProductRecommendationDO customerProductRecommendation = BeanUtils.toBean(createReqVO, CustomerProductRecommendationDO.class);
        customerProductRecommendationMapper.insert(customerProductRecommendation);

        // 返回
        return customerProductRecommendation.getId();
    }

    @Override
    public void updateCustomerProductRecommendation(CustomerProductRecommendationSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerProductRecommendationExists(updateReqVO.getId());
        // 更新
        CustomerProductRecommendationDO updateObj = BeanUtils.toBean(updateReqVO, CustomerProductRecommendationDO.class);
        customerProductRecommendationMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerProductRecommendation(Long id) {
        // 校验存在
        validateCustomerProductRecommendationExists(id);
        // 删除
        customerProductRecommendationMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerProductRecommendationListByIds(List<Long> ids) {
        // 删除
        customerProductRecommendationMapper.deleteByIds(ids);
        }


    private void validateCustomerProductRecommendationExists(Long id) {
        if (customerProductRecommendationMapper.selectById(id) == null) {
            throw exception(CUSTOMER_PRODUCT_RECOMMENDATION_NOT_EXISTS);
        }
    }

    @Override
    public CustomerProductRecommendationDO getCustomerProductRecommendation(Long id) {
        return customerProductRecommendationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerProductRecommendationDO> getCustomerProductRecommendationPage(CustomerProductRecommendationPageReqVO pageReqVO) {
        return customerProductRecommendationMapper.selectPage(pageReqVO);
    }

}