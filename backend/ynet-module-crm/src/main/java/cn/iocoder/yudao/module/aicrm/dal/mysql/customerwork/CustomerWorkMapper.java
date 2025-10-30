package cn.iocoder.yudao.module.aicrm.dal.mysql.customerwork;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerwork.CustomerWorkDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerwork.vo.*;

/**
 * 客户工作或经营信息表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerWorkMapper extends BaseMapperX<CustomerWorkDO> {

    default PageResult<CustomerWorkDO> selectPage(CustomerWorkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerWorkDO>()
                .eqIfPresent(CustomerWorkDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerWorkDO::getWorkType, reqVO.getWorkType())
                .likeIfPresent(CustomerWorkDO::getEmployerName, reqVO.getEmployerName())
                .eqIfPresent(CustomerWorkDO::getEmployerType, reqVO.getEmployerType())
                .eqIfPresent(CustomerWorkDO::getIndustry, reqVO.getIndustry())
                .eqIfPresent(CustomerWorkDO::getPosition, reqVO.getPosition())
                .eqIfPresent(CustomerWorkDO::getWorkYears, reqVO.getWorkYears())
                .betweenIfPresent(CustomerWorkDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(CustomerWorkDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(CustomerWorkDO::getIsCurrent, reqVO.getIsCurrent())
                .eqIfPresent(CustomerWorkDO::getWorkAddressProvince, reqVO.getWorkAddressProvince())
                .eqIfPresent(CustomerWorkDO::getWorkAddressCity, reqVO.getWorkAddressCity())
                .eqIfPresent(CustomerWorkDO::getWorkAddressDistrict, reqVO.getWorkAddressDistrict())
                .eqIfPresent(CustomerWorkDO::getWorkAddressDetail, reqVO.getWorkAddressDetail())
                .eqIfPresent(CustomerWorkDO::getAnnualIncome, reqVO.getAnnualIncome())
                .eqIfPresent(CustomerWorkDO::getMonthlyIncome, reqVO.getMonthlyIncome())
                .eqIfPresent(CustomerWorkDO::getIncomeSource, reqVO.getIncomeSource())
                .eqIfPresent(CustomerWorkDO::getBusinessScale, reqVO.getBusinessScale())
                .eqIfPresent(CustomerWorkDO::getBusinessStatus, reqVO.getBusinessStatus())
                .eqIfPresent(CustomerWorkDO::getProductionCapacity, reqVO.getProductionCapacity())
                .eqIfPresent(CustomerWorkDO::getBusinessLicenseNo, reqVO.getBusinessLicenseNo())
                .eqIfPresent(CustomerWorkDO::getWorkPhone, reqVO.getWorkPhone())
                .eqIfPresent(CustomerWorkDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(CustomerWorkDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(CustomerWorkDO::getVerificationStatus, reqVO.getVerificationStatus())
                .betweenIfPresent(CustomerWorkDO::getVerificationTime, reqVO.getVerificationTime())
                .eqIfPresent(CustomerWorkDO::getVerificationRemark, reqVO.getVerificationRemark())
                .eqIfPresent(CustomerWorkDO::getAttachmentUrls, reqVO.getAttachmentUrls())
                .eqIfPresent(CustomerWorkDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CustomerWorkDO::getExtraData, reqVO.getExtraData())
                .betweenIfPresent(CustomerWorkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerWorkDO::getId));
    }

}