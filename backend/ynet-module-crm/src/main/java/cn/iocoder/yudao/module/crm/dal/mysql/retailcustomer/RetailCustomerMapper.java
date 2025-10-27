package cn.iocoder.yudao.module.crm.dal.mysql.retailcustomer;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.crm.dal.dataobject.retailcustomer.RetailCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.crm.controller.admin.retailcustomer.vo.*;

/**
 * CRM零售客户扩展表(零售客户特有基本信息) Mapper
 *
 * @author 芋道源码
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