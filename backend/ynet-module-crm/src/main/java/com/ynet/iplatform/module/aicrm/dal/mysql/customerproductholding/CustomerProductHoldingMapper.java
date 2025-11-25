package com.ynet.iplatform.module.aicrm.dal.mysql.customerproductholding;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerproductholding.CustomerProductHoldingDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerproductholding.vo.*;

/**
 * 客户产品持有情况表（客户与产品的多对多关系） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerProductHoldingMapper extends BaseMapperX<CustomerProductHoldingDO> {

    default PageResult<CustomerProductHoldingDO> selectPage(CustomerProductHoldingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerProductHoldingDO>()
                .eqIfPresent(CustomerProductHoldingDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerProductHoldingDO::getProductId, reqVO.getProductId())
                .eqIfPresent(CustomerProductHoldingDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerProductHoldingDO::getReceiptNo, reqVO.getReceiptNo())
                .eqIfPresent(CustomerProductHoldingDO::getContractNo, reqVO.getContractNo())
                .eqIfPresent(CustomerProductHoldingDO::getCurrencyCode, reqVO.getCurrencyCode())
                .betweenIfPresent(CustomerProductHoldingDO::getLoanDate, reqVO.getLoanDate())
                .betweenIfPresent(CustomerProductHoldingDO::getOpenDate, reqVO.getOpenDate())
                .betweenIfPresent(CustomerProductHoldingDO::getMatureDate, reqVO.getMatureDate())
                .betweenIfPresent(CustomerProductHoldingDO::getContractDate, reqVO.getContractDate())
                .likeIfPresent(CustomerProductHoldingDO::getBranchName, reqVO.getBranchName())
                .eqIfPresent(CustomerProductHoldingDO::getBranchId, reqVO.getBranchId())
                .likeIfPresent(CustomerProductHoldingDO::getProductName, reqVO.getProductName())
                .eqIfPresent(CustomerProductHoldingDO::getHoldingAmount, reqVO.getHoldingAmount())
                .eqIfPresent(CustomerProductHoldingDO::getOriginalAmount, reqVO.getOriginalAmount())
                .eqIfPresent(CustomerProductHoldingDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerProductHoldingDO::getHoldingStatus, reqVO.getHoldingStatus())
                .eqIfPresent(CustomerProductHoldingDO::getRelatedAccountType, reqVO.getRelatedAccountType())
                .eqIfPresent(CustomerProductHoldingDO::getRelatedAccountId, reqVO.getRelatedAccountId())
                .eqIfPresent(CustomerProductHoldingDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerProductHoldingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerProductHoldingDO::getId));
    }

}