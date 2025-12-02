package com.ynet.iplatform.module.aicrm.dal.mysql.customergroupassignment;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergroupassignment.CustomerGroupAssignmentDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.aicrm.controller.admin.customergroupassignment.vo.*;

/**
 * 客户归属客群关系表（只记录关系，客群信息通过关联查询） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CustomerGroupAssignmentMapper extends BaseMapperX<CustomerGroupAssignmentDO> {

    default PageResult<CustomerGroupAssignmentDO> selectPage(CustomerGroupAssignmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerGroupAssignmentDO>()
                .eqIfPresent(CustomerGroupAssignmentDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerGroupAssignmentDO::getGroupId, reqVO.getGroupId())
                .betweenIfPresent(CustomerGroupAssignmentDO::getAssignDate, reqVO.getAssignDate())
                .eqIfPresent(CustomerGroupAssignmentDO::getAssignOperatorId, reqVO.getAssignOperatorId())
                .eqIfPresent(CustomerGroupAssignmentDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CustomerGroupAssignmentDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerGroupAssignmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerGroupAssignmentDO::getId));
    }

    /**
     * 分页查询客户群归属关系（联表查询）
     *
     * @param page  分页对象
     * @param reqVO 查询条件
     * @return 分页结果
     */
    IPage<CustomerGroupAssignmentRespVO> selectPageWithJoin(IPage<CustomerGroupAssignmentRespVO> page, @Param("reqVO") CustomerGroupAssignmentPageReqVO reqVO);

}