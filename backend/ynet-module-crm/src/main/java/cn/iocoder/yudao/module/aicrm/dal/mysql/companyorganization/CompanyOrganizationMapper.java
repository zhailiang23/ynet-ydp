package cn.iocoder.yudao.module.aicrm.dal.mysql.companyorganization;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyorganization.CompanyOrganizationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyorganization.vo.*;

/**
 * CRM对公客户组织架构信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyOrganizationMapper extends BaseMapperX<CompanyOrganizationDO> {

    default PageResult<CompanyOrganizationDO> selectPage(CompanyOrganizationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyOrganizationDO>()
                .eqIfPresent(CompanyOrganizationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CompanyOrganizationDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CompanyOrganizationDO::getOrgCode, reqVO.getOrgCode())
                .likeIfPresent(CompanyOrganizationDO::getOrgName, reqVO.getOrgName())
                .likeIfPresent(CompanyOrganizationDO::getOrgFullName, reqVO.getOrgFullName())
                .eqIfPresent(CompanyOrganizationDO::getOrgLevel, reqVO.getOrgLevel())
                .eqIfPresent(CompanyOrganizationDO::getOrgType, reqVO.getOrgType())
                .eqIfPresent(CompanyOrganizationDO::getOrgStatus, reqVO.getOrgStatus())
                .likeIfPresent(CompanyOrganizationDO::getLeaderName, reqVO.getLeaderName())
                .eqIfPresent(CompanyOrganizationDO::getLeaderPosition, reqVO.getLeaderPosition())
                .eqIfPresent(CompanyOrganizationDO::getLeaderPhone, reqVO.getLeaderPhone())
                .eqIfPresent(CompanyOrganizationDO::getLeaderEmail, reqVO.getLeaderEmail())
                .eqIfPresent(CompanyOrganizationDO::getEmployeeCount, reqVO.getEmployeeCount())
                .betweenIfPresent(CompanyOrganizationDO::getEstablishedDate, reqVO.getEstablishedDate())
                .eqIfPresent(CompanyOrganizationDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(CompanyOrganizationDO::getContactEmail, reqVO.getContactEmail())
                .eqIfPresent(CompanyOrganizationDO::getContactAddress, reqVO.getContactAddress())
                .eqIfPresent(CompanyOrganizationDO::getBusinessScope, reqVO.getBusinessScope())
                .eqIfPresent(CompanyOrganizationDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CompanyOrganizationDO::getSortOrder, reqVO.getSortOrder())
                .betweenIfPresent(CompanyOrganizationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyOrganizationDO::getId));
    }

}