package com.ynet.iplatform.module.aicrm.dal.mysql.companycontact;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companycontact.CompanyContactDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.companycontact.vo.*;

/**
 * CRM对公客户联系人信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CompanyContactMapper extends BaseMapperX<CompanyContactDO> {

    default PageResult<CompanyContactDO> selectPage(CompanyContactPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyContactDO>()
                .eqIfPresent(CompanyContactDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CompanyContactDO::getContactType, reqVO.getContactType())
                .eqIfPresent(CompanyContactDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(CompanyContactDO::getContactMethod, reqVO.getContactMethod())
                .eqIfPresent(CompanyContactDO::getIsPrimary, reqVO.getIsPrimary())
                .eqIfPresent(CompanyContactDO::getSourceSystem, reqVO.getSourceSystem())
                .eqIfPresent(CompanyContactDO::getContactSeq, reqVO.getContactSeq())
                .eqIfPresent(CompanyContactDO::getContactDesc, reqVO.getContactDesc())
                .eqIfPresent(CompanyContactDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CompanyContactDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CompanyContactDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CompanyContactDO::getEtlDate, reqVO.getEtlDate())
                .eqIfPresent(CompanyContactDO::getOldTxSeqNo, reqVO.getOldTxSeqNo())
                .eqIfPresent(CompanyContactDO::getOldLastUpdateSys, reqVO.getOldLastUpdateSys())
                .eqIfPresent(CompanyContactDO::getOldLastUpdateUser, reqVO.getOldLastUpdateUser())
                .eqIfPresent(CompanyContactDO::getOldLastUpdateTm, reqVO.getOldLastUpdateTm())
                .eqIfPresent(CompanyContactDO::getOldContactId, reqVO.getOldContactId())
                .orderByDesc(CompanyContactDO::getId));
    }

}