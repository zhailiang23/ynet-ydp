package com.ynet.iplatform.module.aicrm.dal.mysql.customergridhistory;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergridhistory.CustomerGridHistoryDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.aicrm.controller.admin.customergridhistory.vo.*;

/**
 * 客户归属网格调整历史表（记录调整当时的网格信息快照） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerGridHistoryMapper extends BaseMapperX<CustomerGridHistoryDO> {

    default PageResult<CustomerGridHistoryDO> selectPage(CustomerGridHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerGridHistoryDO>()
                .eqIfPresent(CustomerGridHistoryDO::getCustomerId, reqVO.getCustomerId())
                .betweenIfPresent(CustomerGridHistoryDO::getAdjustDate, reqVO.getAdjustDate())
                .eqIfPresent(CustomerGridHistoryDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(CustomerGridHistoryDO::getBeforeGridId, reqVO.getBeforeGridId())
                .eqIfPresent(CustomerGridHistoryDO::getBeforeGridCode, reqVO.getBeforeGridCode())
                .likeIfPresent(CustomerGridHistoryDO::getBeforeGridName, reqVO.getBeforeGridName())
                .eqIfPresent(CustomerGridHistoryDO::getBeforeGridType, reqVO.getBeforeGridType())
                .eqIfPresent(CustomerGridHistoryDO::getBeforeGridManagerUserId, reqVO.getBeforeGridManagerUserId())
                .eqIfPresent(CustomerGridHistoryDO::getAfterGridId, reqVO.getAfterGridId())
                .eqIfPresent(CustomerGridHistoryDO::getAfterGridCode, reqVO.getAfterGridCode())
                .likeIfPresent(CustomerGridHistoryDO::getAfterGridName, reqVO.getAfterGridName())
                .eqIfPresent(CustomerGridHistoryDO::getAfterGridType, reqVO.getAfterGridType())
                .eqIfPresent(CustomerGridHistoryDO::getAfterGridManagerUserId, reqVO.getAfterGridManagerUserId())
                .eqIfPresent(CustomerGridHistoryDO::getAdjustOperatorId, reqVO.getAdjustOperatorId())
                .eqIfPresent(CustomerGridHistoryDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerGridHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerGridHistoryDO::getId));
    }

    /**
     * 分页查询（联表查询用户信息）
     *
     * @param page  分页对象
     * @param reqVO 查询条件
     * @return 分页结果
     */
    IPage<CustomerGridHistoryRespVO> selectPageWithJoin(IPage<CustomerGridHistoryRespVO> page, @Param("reqVO") CustomerGridHistoryPageReqVO reqVO);

}