package com.ynet.iplatform.module.aicrm.dal.mysql.customercreditdetail;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercreditdetail.CustomerCreditDetailDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customercreditdetail.vo.*;

/**
 * 客户授信使用明细表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerCreditDetailMapper extends BaseMapperX<CustomerCreditDetailDO> {

    default PageResult<CustomerCreditDetailDO> selectPage(CustomerCreditDetailPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerCreditDetailDO>()
                .eqIfPresent(CustomerCreditDetailDO::getCreditId, reqVO.getCreditId())
                .eqIfPresent(CustomerCreditDetailDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerCreditDetailDO::getLoanNo, reqVO.getLoanNo())
                .eqIfPresent(CustomerCreditDetailDO::getContractNo, reqVO.getContractNo())
                .betweenIfPresent(CustomerCreditDetailDO::getLoanDate, reqVO.getLoanDate())
                .betweenIfPresent(CustomerCreditDetailDO::getMatureDate, reqVO.getMatureDate())
                .eqIfPresent(CustomerCreditDetailDO::getLoanAmount, reqVO.getLoanAmount())
                .eqIfPresent(CustomerCreditDetailDO::getBalance, reqVO.getBalance())
                .eqIfPresent(CustomerCreditDetailDO::getInterestRate, reqVO.getInterestRate())
                .eqIfPresent(CustomerCreditDetailDO::getLoanStatus, reqVO.getLoanStatus())
                .betweenIfPresent(CustomerCreditDetailDO::getSettleDate, reqVO.getSettleDate())
                .likeIfPresent(CustomerCreditDetailDO::getProductName, reqVO.getProductName())
                .eqIfPresent(CustomerCreditDetailDO::getProductCode, reqVO.getProductCode())
                .eqIfPresent(CustomerCreditDetailDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerCreditDetailDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerCreditDetailDO::getId));
    }

}