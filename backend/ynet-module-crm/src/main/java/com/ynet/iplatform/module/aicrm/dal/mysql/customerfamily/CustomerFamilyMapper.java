package com.ynet.iplatform.module.aicrm.dal.mysql.customerfamily;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerfamily.CustomerFamilyDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerfamily.vo.*;

/**
 * 客户家庭信息表（零售客户） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerFamilyMapper extends BaseMapperX<CustomerFamilyDO> {

    default PageResult<CustomerFamilyDO> selectPage(CustomerFamilyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerFamilyDO>()
                .eqIfPresent(CustomerFamilyDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerFamilyDO::getFamilyMemberCount, reqVO.getFamilyMemberCount())
                .eqIfPresent(CustomerFamilyDO::getSupportMemberCount, reqVO.getSupportMemberCount())
                .eqIfPresent(CustomerFamilyDO::getLaborMemberCount, reqVO.getLaborMemberCount())
                .eqIfPresent(CustomerFamilyDO::getChildrenCount, reqVO.getChildrenCount())
                .eqIfPresent(CustomerFamilyDO::getFamilyAnnualIncome, reqVO.getFamilyAnnualIncome())
                .eqIfPresent(CustomerFamilyDO::getFamilyAnnualIncomeScope, reqVO.getFamilyAnnualIncomeScope())
                .eqIfPresent(CustomerFamilyDO::getFamilyAnnualExpenditure, reqVO.getFamilyAnnualExpenditure())
                .eqIfPresent(CustomerFamilyDO::getFamilyAnnualExpenditureScope, reqVO.getFamilyAnnualExpenditureScope())
                .eqIfPresent(CustomerFamilyDO::getFamilyDebt, reqVO.getFamilyDebt())
                .eqIfPresent(CustomerFamilyDO::getFamilyTotalAssets, reqVO.getFamilyTotalAssets())
                .eqIfPresent(CustomerFamilyDO::getFamilyAssetsInfo, reqVO.getFamilyAssetsInfo())
                .eqIfPresent(CustomerFamilyDO::getMainIncomeSource, reqVO.getMainIncomeSource())
                .eqIfPresent(CustomerFamilyDO::getResidenceStatus, reqVO.getResidenceStatus())
                .eqIfPresent(CustomerFamilyDO::getHouseStatus, reqVO.getHouseStatus())
                .eqIfPresent(CustomerFamilyDO::getHasHomeCar, reqVO.getHasHomeCar())
                .eqIfPresent(CustomerFamilyDO::getIsHouseHolder, reqVO.getIsHouseHolder())
                .likeIfPresent(CustomerFamilyDO::getHouseHolderName, reqVO.getHouseHolderName())
                .eqIfPresent(CustomerFamilyDO::getResidenceLocation, reqVO.getResidenceLocation())
                .eqIfPresent(CustomerFamilyDO::getFamilyAddress, reqVO.getFamilyAddress())
                .eqIfPresent(CustomerFamilyDO::getHomeTel, reqVO.getHomeTel())
                .eqIfPresent(CustomerFamilyDO::getIsHarmony, reqVO.getIsHarmony())
                .eqIfPresent(CustomerFamilyDO::getIsCreditFamily, reqVO.getIsCreditFamily())
                .eqIfPresent(CustomerFamilyDO::getCreditStatus, reqVO.getCreditStatus())
                .eqIfPresent(CustomerFamilyDO::getCreditAmount, reqVO.getCreditAmount())
                .eqIfPresent(CustomerFamilyDO::getFamilyDebtScope, reqVO.getFamilyDebtScope())
                .eqIfPresent(CustomerFamilyDO::getDebtStatus, reqVO.getDebtStatus())
                .eqIfPresent(CustomerFamilyDO::getFamilyAdverseRecords, reqVO.getFamilyAdverseRecords())
                .eqIfPresent(CustomerFamilyDO::getBusinessAndScale, reqVO.getBusinessAndScale())
                .eqIfPresent(CustomerFamilyDO::getFamilyStrength, reqVO.getFamilyStrength())
                .eqIfPresent(CustomerFamilyDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerFamilyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerFamilyDO::getId));
    }

}