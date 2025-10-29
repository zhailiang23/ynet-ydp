package cn.iocoder.yudao.module.aicrm.dal.mysql.customerfamilymember;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerfamilymember.CustomerFamilyMemberDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerfamilymember.vo.*;

/**
 * 客户家庭成员信息表（零售客户） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerFamilyMemberMapper extends BaseMapperX<CustomerFamilyMemberDO> {

    default PageResult<CustomerFamilyMemberDO> selectPage(CustomerFamilyMemberPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerFamilyMemberDO>()
                .eqIfPresent(CustomerFamilyMemberDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(CustomerFamilyMemberDO::getMemberName, reqVO.getMemberName())
                .eqIfPresent(CustomerFamilyMemberDO::getRelationType, reqVO.getRelationType())
                .eqIfPresent(CustomerFamilyMemberDO::getGender, reqVO.getGender())
                .eqIfPresent(CustomerFamilyMemberDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(CustomerFamilyMemberDO::getAge, reqVO.getAge())
                .eqIfPresent(CustomerFamilyMemberDO::getIdentityType, reqVO.getIdentityType())
                .eqIfPresent(CustomerFamilyMemberDO::getIdentityNo, reqVO.getIdentityNo())
                .eqIfPresent(CustomerFamilyMemberDO::getEducationLevel, reqVO.getEducationLevel())
                .eqIfPresent(CustomerFamilyMemberDO::getCompany, reqVO.getCompany())
                .eqIfPresent(CustomerFamilyMemberDO::getPosition, reqVO.getPosition())
                .eqIfPresent(CustomerFamilyMemberDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CustomerFamilyMemberDO::getMobile, reqVO.getMobile())
                .eqIfPresent(CustomerFamilyMemberDO::getTel, reqVO.getTel())
                .eqIfPresent(CustomerFamilyMemberDO::getEmail, reqVO.getEmail())
                .eqIfPresent(CustomerFamilyMemberDO::getIsMainMember, reqVO.getIsMainMember())
                .eqIfPresent(CustomerFamilyMemberDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CustomerFamilyMemberDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(CustomerFamilyMemberDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(CustomerFamilyMemberDO::getOldCustId, reqVO.getOldCustId())
                .betweenIfPresent(CustomerFamilyMemberDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerFamilyMemberDO::getId));
    }

}