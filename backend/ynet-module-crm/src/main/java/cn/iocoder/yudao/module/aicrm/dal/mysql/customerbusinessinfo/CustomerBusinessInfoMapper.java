package cn.iocoder.yudao.module.aicrm.dal.mysql.customerbusinessinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerbusinessinfo.CustomerBusinessInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerbusinessinfo.vo.*;

/**
 * 客户经营信息表（精简版，只包含经营相关核心字段） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerBusinessInfoMapper extends BaseMapperX<CustomerBusinessInfoDO> {

    default PageResult<CustomerBusinessInfoDO> selectPage(CustomerBusinessInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerBusinessInfoDO>()
                .eqIfPresent(CustomerBusinessInfoDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(CustomerBusinessInfoDO::getBusinessName, reqVO.getBusinessName())
                .eqIfPresent(CustomerBusinessInfoDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(CustomerBusinessInfoDO::getBusinessLicenseNo, reqVO.getBusinessLicenseNo())
                .eqIfPresent(CustomerBusinessInfoDO::getBusinessScope, reqVO.getBusinessScope())
                .eqIfPresent(CustomerBusinessInfoDO::getIndustry, reqVO.getIndustry())
                .eqIfPresent(CustomerBusinessInfoDO::getBusinessScale, reqVO.getBusinessScale())
                .eqIfPresent(CustomerBusinessInfoDO::getBusinessStatus, reqVO.getBusinessStatus())
                .eqIfPresent(CustomerBusinessInfoDO::getRegisteredCapital, reqVO.getRegisteredCapital())
                .eqIfPresent(CustomerBusinessInfoDO::getEmployeeCount, reqVO.getEmployeeCount())
                .eqIfPresent(CustomerBusinessInfoDO::getAnnualRevenue, reqVO.getAnnualRevenue())
                .eqIfPresent(CustomerBusinessInfoDO::getMonthlyRevenue, reqVO.getMonthlyRevenue())
                .eqIfPresent(CustomerBusinessInfoDO::getAnnualProfit, reqVO.getAnnualProfit())
                .eqIfPresent(CustomerBusinessInfoDO::getTaxRegistrationNo, reqVO.getTaxRegistrationNo())
                .eqIfPresent(CustomerBusinessInfoDO::getIsGeneralTaxpayer, reqVO.getIsGeneralTaxpayer())
                .eqIfPresent(CustomerBusinessInfoDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerBusinessInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerBusinessInfoDO::getId));
    }

}