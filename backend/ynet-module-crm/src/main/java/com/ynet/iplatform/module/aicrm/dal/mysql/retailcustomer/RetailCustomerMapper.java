package com.ynet.iplatform.module.aicrm.dal.mysql.retailcustomer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.retailcustomer.RetailCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.retailcustomer.vo.*;

/**
 * CRM零售客户扩展表(零售客户特有基本信息) Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface RetailCustomerMapper extends BaseMapperX<RetailCustomerDO> {

    default PageResult<RetailCustomerDO> selectPage(RetailCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RetailCustomerDO>()
                .eqIfPresent(RetailCustomerDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(RetailCustomerDO::getNickname, reqVO.getNickname())
                .eqIfPresent(RetailCustomerDO::getGender, reqVO.getGender())
                .eqIfPresent(RetailCustomerDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(RetailCustomerDO::getAge, reqVO.getAge())
                .orderByDesc(RetailCustomerDO::getId));
    }

}