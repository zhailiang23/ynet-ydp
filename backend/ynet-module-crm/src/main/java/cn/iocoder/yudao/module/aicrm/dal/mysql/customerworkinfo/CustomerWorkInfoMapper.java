package cn.iocoder.yudao.module.aicrm.dal.mysql.customerworkinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerworkinfo.CustomerWorkInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerworkinfo.vo.*;

/**
 * 客户工作信息表（精简版，只包含工作相关核心字段） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerWorkInfoMapper extends BaseMapperX<CustomerWorkInfoDO> {

    default PageResult<CustomerWorkInfoDO> selectPage(CustomerWorkInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerWorkInfoDO>()
                .eqIfPresent(CustomerWorkInfoDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(CustomerWorkInfoDO::getEmployerName, reqVO.getEmployerName())
                .eqIfPresent(CustomerWorkInfoDO::getEmployerType, reqVO.getEmployerType())
                .eqIfPresent(CustomerWorkInfoDO::getIndustry, reqVO.getIndustry())
                .eqIfPresent(CustomerWorkInfoDO::getPosition, reqVO.getPosition())
                .eqIfPresent(CustomerWorkInfoDO::getPositionLevel, reqVO.getPositionLevel())
                .eqIfPresent(CustomerWorkInfoDO::getDepartment, reqVO.getDepartment())
                .eqIfPresent(CustomerWorkInfoDO::getWorkYears, reqVO.getWorkYears())
                .eqIfPresent(CustomerWorkInfoDO::getAnnualIncome, reqVO.getAnnualIncome())
                .eqIfPresent(CustomerWorkInfoDO::getMonthlyIncome, reqVO.getMonthlyIncome())
                .eqIfPresent(CustomerWorkInfoDO::getHasSocialInsurance, reqVO.getHasSocialInsurance())
                .eqIfPresent(CustomerWorkInfoDO::getHasHousingFund, reqVO.getHasHousingFund())
                .eqIfPresent(CustomerWorkInfoDO::getWorkPhone, reqVO.getWorkPhone())
                .eqIfPresent(CustomerWorkInfoDO::getWorkEmail, reqVO.getWorkEmail())
                .eqIfPresent(CustomerWorkInfoDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerWorkInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerWorkInfoDO::getId));
    }

}