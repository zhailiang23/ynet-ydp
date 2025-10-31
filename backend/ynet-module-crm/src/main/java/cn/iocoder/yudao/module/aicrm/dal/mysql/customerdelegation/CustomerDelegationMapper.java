package cn.iocoder.yudao.module.aicrm.dal.mysql.customerdelegation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdelegation.CustomerDelegationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo.*;

/**
 * 客户托管记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerDelegationMapper extends BaseMapperX<CustomerDelegationDO> {

    default PageResult<CustomerDelegationDO> selectPage(CustomerDelegationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerDelegationDO>()
                .eqIfPresent(CustomerDelegationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerDelegationDO::getFromUserId, reqVO.getFromUserId())
                .eqIfPresent(CustomerDelegationDO::getToUserId, reqVO.getToUserId())
                .betweenIfPresent(CustomerDelegationDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(CustomerDelegationDO::getEndDate, reqVO.getEndDate())
                .betweenIfPresent(CustomerDelegationDO::getActualEndDate, reqVO.getActualEndDate())
                .eqIfPresent(CustomerDelegationDO::getDelegationReason, reqVO.getDelegationReason())
                .eqIfPresent(CustomerDelegationDO::getDelegationStatus, reqVO.getDelegationStatus())
                .betweenIfPresent(CustomerDelegationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerDelegationDO::getId));
    }

}