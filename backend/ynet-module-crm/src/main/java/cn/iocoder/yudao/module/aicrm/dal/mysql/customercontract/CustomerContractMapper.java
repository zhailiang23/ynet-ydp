package cn.iocoder.yudao.module.aicrm.dal.mysql.customercontract;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercontract.CustomerContractDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customercontract.vo.*;

/**
 * 客户签约信息表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerContractMapper extends BaseMapperX<CustomerContractDO> {

    default PageResult<CustomerContractDO> selectPage(CustomerContractPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerContractDO>()
                .eqIfPresent(CustomerContractDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerContractDO::getContractTypeId, reqVO.getContractTypeId())
                .eqIfPresent(CustomerContractDO::getContractNo, reqVO.getContractNo())
                .betweenIfPresent(CustomerContractDO::getContractDate, reqVO.getContractDate())
                .eqIfPresent(CustomerContractDO::getBranchId, reqVO.getBranchId())
                .likeIfPresent(CustomerContractDO::getBranchName, reqVO.getBranchName())
                .eqIfPresent(CustomerContractDO::getContractStatus, reqVO.getContractStatus())
                .eqIfPresent(CustomerContractDO::getContractSituation, reqVO.getContractSituation())
                .eqIfPresent(CustomerContractDO::getIdentityType, reqVO.getIdentityType())
                .eqIfPresent(CustomerContractDO::getIdentityNo, reqVO.getIdentityNo())
                .eqIfPresent(CustomerContractDO::getAccountNo, reqVO.getAccountNo())
                .betweenIfPresent(CustomerContractDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(CustomerContractDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(CustomerContractDO::getSignChannel, reqVO.getSignChannel())
                .betweenIfPresent(CustomerContractDO::getCancelDate, reqVO.getCancelDate())
                .eqIfPresent(CustomerContractDO::getCancelReason, reqVO.getCancelReason())
                .eqIfPresent(CustomerContractDO::getManagerUserId, reqVO.getManagerUserId())
                .eqIfPresent(CustomerContractDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerContractDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerContractDO::getId));
    }

}