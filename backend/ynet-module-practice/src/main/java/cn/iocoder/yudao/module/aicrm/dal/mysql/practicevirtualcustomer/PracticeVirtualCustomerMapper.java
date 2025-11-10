package cn.iocoder.yudao.module.aicrm.dal.mysql.practicevirtualcustomer;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicevirtualcustomer.vo.*;

/**
 * CRM智能陪练-虚拟客户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeVirtualCustomerMapper extends BaseMapperX<PracticeVirtualCustomerDO> {

    default PageResult<PracticeVirtualCustomerDO> selectPage(PracticeVirtualCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeVirtualCustomerDO>()
                .likeIfPresent(PracticeVirtualCustomerDO::getName, reqVO.getName())
                .eqIfPresent(PracticeVirtualCustomerDO::getGender, reqVO.getGender())
                .eqIfPresent(PracticeVirtualCustomerDO::getAge, reqVO.getAge())
                .eqIfPresent(PracticeVirtualCustomerDO::getOccupation, reqVO.getOccupation())
                .eqIfPresent(PracticeVirtualCustomerDO::getIndustry, reqVO.getIndustry())
                .eqIfPresent(PracticeVirtualCustomerDO::getPersonalityType, reqVO.getPersonalityType())
                .eqIfPresent(PracticeVirtualCustomerDO::getRiskPreference, reqVO.getRiskPreference())
                .eqIfPresent(PracticeVirtualCustomerDO::getMemo, reqVO.getMemo())
                .betweenIfPresent(PracticeVirtualCustomerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeVirtualCustomerDO::getId));
    }

}