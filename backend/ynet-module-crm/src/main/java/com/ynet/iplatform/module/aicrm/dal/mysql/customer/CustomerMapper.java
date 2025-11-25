package com.ynet.iplatform.module.aicrm.dal.mysql.customer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customer.vo.*;

/**
 * CRM客户主表(零售+对公共用) Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerMapper extends BaseMapperX<CustomerDO> {

    default PageResult<CustomerDO> selectPage(CustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerDO>()
                .eqIfPresent(CustomerDO::getCustomerNo, reqVO.getCustomerNo())
                .eqIfPresent(CustomerDO::getCustomerType, reqVO.getCustomerType())
                .likeIfPresent(CustomerDO::getCustomerName, reqVO.getCustomerName())
                .eqIfPresent(CustomerDO::getCustomerLevel, reqVO.getCustomerLevel())
                .eqIfPresent(CustomerDO::getCustomerStatus, reqVO.getCustomerStatus())
                .eqIfPresent(CustomerDO::getAssignmentStatus, reqVO.getAssignmentStatus())
                .eqIfPresent(CustomerDO::getIsValid, reqVO.getIsValid())
                .eqIfPresent(CustomerDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(CustomerDO::getIsHighQuality, reqVO.getIsHighQuality())
                .eqIfPresent(CustomerDO::getIsImportant, reqVO.getIsImportant())
                .eqIfPresent(CustomerDO::getCreditLevel, reqVO.getCreditLevel())
                .eqIfPresent(CustomerDO::getCustomerSource, reqVO.getCustomerSource())
                .likeIfPresent(CustomerDO::getCustomerTag, reqVO.getCustomerTag())
                .betweenIfPresent(CustomerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerDO::getId));
    }

}