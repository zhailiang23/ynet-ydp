package cn.iocoder.yudao.module.crm.dal.mysql.companycustomer;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.crm.dal.dataobject.companycustomer.CompanyCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.crm.controller.admin.companycustomer.vo.*;

/**
 * CRM对公客户扩展表(对公客户特有基本信息) Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyCustomerMapper extends BaseMapperX<CompanyCustomerDO> {

    default PageResult<CompanyCustomerDO> selectPage(CompanyCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyCustomerDO>()
                .eqIfPresent(CompanyCustomerDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CompanyCustomerDO::getLicenseType, reqVO.getLicenseType())
                .eqIfPresent(CompanyCustomerDO::getLicenseNo, reqVO.getLicenseNo())
                .eqIfPresent(CompanyCustomerDO::getCreditCode, reqVO.getCreditCode())
                .eqIfPresent(CompanyCustomerDO::getOrganizationCode, reqVO.getOrganizationCode())
                .eqIfPresent(CompanyCustomerDO::getTaxNo, reqVO.getTaxNo())
                .eqIfPresent(CompanyCustomerDO::getLoanCardNo, reqVO.getLoanCardNo())
                .eqIfPresent(CompanyCustomerDO::getEnterpriseType, reqVO.getEnterpriseType())
                .eqIfPresent(CompanyCustomerDO::getEnterpriseNature, reqVO.getEnterpriseNature())
                .eqIfPresent(CompanyCustomerDO::getOwnershipType, reqVO.getOwnershipType())
                .eqIfPresent(CompanyCustomerDO::getEconomicType, reqVO.getEconomicType())
                .eqIfPresent(CompanyCustomerDO::getEnterpriseScale, reqVO.getEnterpriseScale())
                .eqIfPresent(CompanyCustomerDO::getRegisteredCapital, reqVO.getRegisteredCapital())
                .eqIfPresent(CompanyCustomerDO::getRegisteredCapitalCurrency, reqVO.getRegisteredCapitalCurrency())
                .betweenIfPresent(CompanyCustomerDO::getEstablishDate, reqVO.getEstablishDate())
                .eqIfPresent(CompanyCustomerDO::getBusinessTerm, reqVO.getBusinessTerm())
                .eqIfPresent(CompanyCustomerDO::getIndustryCategoryL1, reqVO.getIndustryCategoryL1())
                .eqIfPresent(CompanyCustomerDO::getIndustryCategoryL2, reqVO.getIndustryCategoryL2())
                .eqIfPresent(CompanyCustomerDO::getIndustryCategoryL3, reqVO.getIndustryCategoryL3())
                .eqIfPresent(CompanyCustomerDO::getIndustryCategoryL4, reqVO.getIndustryCategoryL4())
                .eqIfPresent(CompanyCustomerDO::getIndustryCode, reqVO.getIndustryCode())
                .eqIfPresent(CompanyCustomerDO::getIsListed, reqVO.getIsListed())
                .eqIfPresent(CompanyCustomerDO::getIsSmallEnterprise, reqVO.getIsSmallEnterprise())
                .eqIfPresent(CompanyCustomerDO::getIsGroupCustomer, reqVO.getIsGroupCustomer())
                .eqIfPresent(CompanyCustomerDO::getIsImportExport, reqVO.getIsImportExport())
                .eqIfPresent(CompanyCustomerDO::getIsRelatedParty, reqVO.getIsRelatedParty())
                .eqIfPresent(CompanyCustomerDO::getIsEbankSigned, reqVO.getIsEbankSigned())
                .eqIfPresent(CompanyCustomerDO::getIsAgricultureRelated, reqVO.getIsAgricultureRelated())
                .eqIfPresent(CompanyCustomerDO::getBasicAccountBank, reqVO.getBasicAccountBank())
                .eqIfPresent(CompanyCustomerDO::getBasicAccountNo, reqVO.getBasicAccountNo())
                .likeIfPresent(CompanyCustomerDO::getLegalPersonName, reqVO.getLegalPersonName())
                .eqIfPresent(CompanyCustomerDO::getLegalPersonIdType, reqVO.getLegalPersonIdType())
                .eqIfPresent(CompanyCustomerDO::getLegalPersonIdNo, reqVO.getLegalPersonIdNo())
                .eqIfPresent(CompanyCustomerDO::getLegalPersonPhone, reqVO.getLegalPersonPhone())
                .eqIfPresent(CompanyCustomerDO::getEnterpriseQualification, reqVO.getEnterpriseQualification())
                .eqIfPresent(CompanyCustomerDO::getManagementDept, reqVO.getManagementDept())
                .eqIfPresent(CompanyCustomerDO::getSuperviseDept, reqVO.getSuperviseDept())
                .eqIfPresent(CompanyCustomerDO::getCompanyRating, reqVO.getCompanyRating())
                .eqIfPresent(CompanyCustomerDO::getRatingAgency, reqVO.getRatingAgency())
                .betweenIfPresent(CompanyCustomerDO::getRatingDate, reqVO.getRatingDate())
                .eqIfPresent(CompanyCustomerDO::getExtField1, reqVO.getExtField1())
                .eqIfPresent(CompanyCustomerDO::getExtField2, reqVO.getExtField2())
                .eqIfPresent(CompanyCustomerDO::getExtField3, reqVO.getExtField3())
                .betweenIfPresent(CompanyCustomerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyCustomerDO::getId));
    }

}