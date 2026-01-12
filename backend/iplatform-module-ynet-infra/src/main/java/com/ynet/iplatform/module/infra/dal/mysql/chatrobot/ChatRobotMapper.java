package com.ynet.iplatform.module.infra.dal.mysql.chatrobot;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.*;

/**
 * 对话机器人管理 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ChatRobotMapper extends BaseMapperX<ChatRobotDO> {

    default PageResult<ChatRobotDO> selectPage(ChatRobotPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatRobotDO>()
                .likeIfPresent(ChatRobotDO::getName, reqVO.getName())
                .eqIfPresent(ChatRobotDO::getPlatformType, reqVO.getPlatformType())
                .eqIfPresent(ChatRobotDO::getStatus, reqVO.getStatus())
                .orderByDesc(ChatRobotDO::getId));
    }

}