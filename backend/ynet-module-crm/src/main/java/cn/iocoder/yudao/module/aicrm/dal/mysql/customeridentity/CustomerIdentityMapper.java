package cn.iocoder.yudao.module.aicrm.dal.mysql.customeridentity;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeridentity.CustomerIdentityDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeridentity.vo.*;

/**
 * 客户证件信息表（零售客户特有，1对多关系，支持多证件） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerIdentityMapper extends BaseMapperX<CustomerIdentityDO> {

    default PageResult<CustomerIdentityDO> selectPage(CustomerIdentityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerIdentityDO>()
                .eqIfPresent(CustomerIdentityDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerIdentityDO::getIdentityType, reqVO.getIdentityType())
                .eqIfPresent(CustomerIdentityDO::getIdentityNo, reqVO.getIdentityNo())
                .likeIfPresent(CustomerIdentityDO::getIdentityName, reqVO.getIdentityName())
                .eqIfPresent(CustomerIdentityDO::getIssueAuthority, reqVO.getIssueAuthority())
                .betweenIfPresent(CustomerIdentityDO::getIssueDate, reqVO.getIssueDate())
                .betweenIfPresent(CustomerIdentityDO::getExpiryDate, reqVO.getExpiryDate())
                .eqIfPresent(CustomerIdentityDO::getIsPrimary, reqVO.getIsPrimary())
                .eqIfPresent(CustomerIdentityDO::getIdentityAddress, reqVO.getIdentityAddress())
                .eqIfPresent(CustomerIdentityDO::getIdentityFrontUrl, reqVO.getIdentityFrontUrl())
                .eqIfPresent(CustomerIdentityDO::getIdentityBackUrl, reqVO.getIdentityBackUrl())
                .eqIfPresent(CustomerIdentityDO::getVerificationStatus, reqVO.getVerificationStatus())
                .betweenIfPresent(CustomerIdentityDO::getVerificationTime, reqVO.getVerificationTime())
                .eqIfPresent(CustomerIdentityDO::getVerificationRemark, reqVO.getVerificationRemark())
                .eqIfPresent(CustomerIdentityDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerIdentityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerIdentityDO::getId));
    }

}