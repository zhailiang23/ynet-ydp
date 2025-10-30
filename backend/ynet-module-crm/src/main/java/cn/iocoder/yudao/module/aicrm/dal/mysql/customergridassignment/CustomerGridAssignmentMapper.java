package cn.iocoder.yudao.module.aicrm.dal.mysql.customergridassignment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customergridassignment.CustomerGridAssignmentDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.iocoder.yudao.module.aicrm.controller.admin.customergridassignment.vo.*;

/**
 * 客户归属网格关系表（只记录关系，网格信息通过关联查询） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerGridAssignmentMapper extends BaseMapperX<CustomerGridAssignmentDO> {

    default PageResult<CustomerGridAssignmentDO> selectPage(CustomerGridAssignmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerGridAssignmentDO>()
                .eqIfPresent(CustomerGridAssignmentDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerGridAssignmentDO::getGridId, reqVO.getGridId())
                .betweenIfPresent(CustomerGridAssignmentDO::getAssignDate, reqVO.getAssignDate())
                .eqIfPresent(CustomerGridAssignmentDO::getAssignOperatorId, reqVO.getAssignOperatorId())
                .eqIfPresent(CustomerGridAssignmentDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CustomerGridAssignmentDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerGridAssignmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerGridAssignmentDO::getId));
    }

    /**
     * 分页查询（联表查询用户信息）
     *
     * @param page  分页对象
     * @param reqVO 查询条件
     * @return 分页结果
     */
    IPage<CustomerGridAssignmentRespVO> selectPageWithJoin(IPage<CustomerGridAssignmentRespVO> page, @Param("reqVO") CustomerGridAssignmentPageReqVO reqVO);

}