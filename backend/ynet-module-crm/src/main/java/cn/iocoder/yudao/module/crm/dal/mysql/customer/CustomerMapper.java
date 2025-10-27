package cn.iocoder.yudao.module.crm.dal.mysql.customer;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.crm.dal.dataobject.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.crm.controller.admin.customer.vo.*;

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
                .eqIfPresent(CustomerDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(CustomerDO::getId));
    }

}