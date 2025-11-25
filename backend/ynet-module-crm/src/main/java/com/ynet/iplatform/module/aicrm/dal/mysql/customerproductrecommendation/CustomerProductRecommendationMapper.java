package com.ynet.iplatform.module.aicrm.dal.mysql.customerproductrecommendation;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductrecommendation.CustomerProductRecommendationDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerproductrecommendation.vo.*;

/**
 * 客户产品推荐 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerProductRecommendationMapper extends BaseMapperX<CustomerProductRecommendationDO> {

    default PageResult<CustomerProductRecommendationDO> selectPage(CustomerProductRecommendationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerProductRecommendationDO>()
                .eqIfPresent(CustomerProductRecommendationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerProductRecommendationDO::getSequenceNo, reqVO.getSequenceNo())
                .eqIfPresent(CustomerProductRecommendationDO::getProductCode, reqVO.getProductCode())
                .likeIfPresent(CustomerProductRecommendationDO::getProductName, reqVO.getProductName())
                .eqIfPresent(CustomerProductRecommendationDO::getProductCategoryId, reqVO.getProductCategoryId())
                .eqIfPresent(CustomerProductRecommendationDO::getProductType, reqVO.getProductType())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommendationNo, reqVO.getRecommendationNo())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommendationType, reqVO.getRecommendationType())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommendationSource, reqVO.getRecommendationSource())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommendationReason, reqVO.getRecommendationReason())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommendationScore, reqVO.getRecommendationScore())
                .betweenIfPresent(CustomerProductRecommendationDO::getRecommendationDate, reqVO.getRecommendationDate())
                .betweenIfPresent(CustomerProductRecommendationDO::getRecommendationTime, reqVO.getRecommendationTime())
                .betweenIfPresent(CustomerProductRecommendationDO::getValidStartDate, reqVO.getValidStartDate())
                .betweenIfPresent(CustomerProductRecommendationDO::getValidEndDate, reqVO.getValidEndDate())
                .eqIfPresent(CustomerProductRecommendationDO::getIsHotSale, reqVO.getIsHotSale())
                .eqIfPresent(CustomerProductRecommendationDO::getIsMatchCustomer, reqVO.getIsMatchCustomer())
                .eqIfPresent(CustomerProductRecommendationDO::getMatchDegree, reqVO.getMatchDegree())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommendationStatus, reqVO.getRecommendationStatus())
                .betweenIfPresent(CustomerProductRecommendationDO::getPushTime, reqVO.getPushTime())
                .eqIfPresent(CustomerProductRecommendationDO::getPushChannel, reqVO.getPushChannel())
                .betweenIfPresent(CustomerProductRecommendationDO::getViewTime, reqVO.getViewTime())
                .eqIfPresent(CustomerProductRecommendationDO::getViewCount, reqVO.getViewCount())
                .betweenIfPresent(CustomerProductRecommendationDO::getClickTime, reqVO.getClickTime())
                .eqIfPresent(CustomerProductRecommendationDO::getClickCount, reqVO.getClickCount())
                .betweenIfPresent(CustomerProductRecommendationDO::getApplyTime, reqVO.getApplyTime())
                .betweenIfPresent(CustomerProductRecommendationDO::getPurchaseTime, reqVO.getPurchaseTime())
                .eqIfPresent(CustomerProductRecommendationDO::getPurchaseAmount, reqVO.getPurchaseAmount())
                .betweenIfPresent(CustomerProductRecommendationDO::getRejectTime, reqVO.getRejectTime())
                .eqIfPresent(CustomerProductRecommendationDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommenderUserId, reqVO.getRecommenderUserId())
                .likeIfPresent(CustomerProductRecommendationDO::getRecommenderUserName, reqVO.getRecommenderUserName())
                .eqIfPresent(CustomerProductRecommendationDO::getRecommenderDeptId, reqVO.getRecommenderDeptId())
                .likeIfPresent(CustomerProductRecommendationDO::getRecommenderDeptName, reqVO.getRecommenderDeptName())
                .eqIfPresent(CustomerProductRecommendationDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerProductRecommendationDO::getTermDays, reqVO.getTermDays())
                .eqIfPresent(CustomerProductRecommendationDO::getMinAmount, reqVO.getMinAmount())
                .eqIfPresent(CustomerProductRecommendationDO::getMaxAmount, reqVO.getMaxAmount())
                .eqIfPresent(CustomerProductRecommendationDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(CustomerProductRecommendationDO::getProductFeatures, reqVO.getProductFeatures())
                .eqIfPresent(CustomerProductRecommendationDO::getProductAdvantage, reqVO.getProductAdvantage())
                .eqIfPresent(CustomerProductRecommendationDO::getMarketingTheme, reqVO.getMarketingTheme())
                .eqIfPresent(CustomerProductRecommendationDO::getMarketingContent, reqVO.getMarketingContent())
                .eqIfPresent(CustomerProductRecommendationDO::getPromotionType, reqVO.getPromotionType())
                .eqIfPresent(CustomerProductRecommendationDO::getPromotionContent, reqVO.getPromotionContent())
                .eqIfPresent(CustomerProductRecommendationDO::getConversionRate, reqVO.getConversionRate())
                .eqIfPresent(CustomerProductRecommendationDO::getIsEffective, reqVO.getIsEffective())
                .eqIfPresent(CustomerProductRecommendationDO::getEffectivenessScore, reqVO.getEffectivenessScore())
                .eqIfPresent(CustomerProductRecommendationDO::getFeedbackContent, reqVO.getFeedbackContent())
                .betweenIfPresent(CustomerProductRecommendationDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(CustomerProductRecommendationDO::getPriority, reqVO.getPriority())
                .eqIfPresent(CustomerProductRecommendationDO::getDisplayOrder, reqVO.getDisplayOrder())
                .eqIfPresent(CustomerProductRecommendationDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerProductRecommendationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerProductRecommendationDO::getId));
    }

}