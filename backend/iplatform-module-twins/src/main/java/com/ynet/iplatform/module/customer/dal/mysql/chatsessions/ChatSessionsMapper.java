package com.ynet.iplatform.module.customer.dal.mysql.chatsessions;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.customer.dal.dataobject.chatsessions.ChatSessionsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo.*;

/**
 * 会话信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ChatSessionsMapper extends BaseMapperX<ChatSessionsDO> {

    default PageResult<ChatSessionsDO> selectPage(ChatSessionsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatSessionsDO>()
                .eqIfPresent(ChatSessionsDO::getQueriedAt, reqVO.getQueriedAt())
                .eqIfPresent(ChatSessionsDO::getAcceptedAt, reqVO.getAcceptedAt())
                .eqIfPresent(ChatSessionsDO::getCanceledAt, reqVO.getCanceledAt())
                .eqIfPresent(ChatSessionsDO::getBrokenAt, reqVO.getBrokenAt())
                .eqIfPresent(ChatSessionsDO::getType, reqVO.getType())
                .eqIfPresent(ChatSessionsDO::getRate, reqVO.getRate())
                .orderByDesc(ChatSessionsDO::getId));
    }

    /**
     * 连表查询会话列表（XML 实现），包含用户名和客服名，支持按名字模糊查询
     */
    IPage<ChatSessionsRespVO> selectPageWithNames(IPage<ChatSessionsRespVO> page, @Param("userName") String userName,
                                                   @Param("adminName") String adminName, @Param("type") Integer type);

    /**
     * 统计会话数量（XML 实现）
     */
    Long selectCountWithNames(@Param("userName") String userName, @Param("adminName") String adminName,
                              @Param("type") Integer type);

}