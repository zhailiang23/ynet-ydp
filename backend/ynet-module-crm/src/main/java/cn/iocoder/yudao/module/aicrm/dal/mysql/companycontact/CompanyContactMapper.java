package cn.iocoder.yudao.module.aicrm.dal.mysql.companycontact;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companycontact.CompanyContactDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.companycontact.vo.*;

/**
 * CRM对公客户联系人信息 Mapper
 *
 * @author 芋道源码
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