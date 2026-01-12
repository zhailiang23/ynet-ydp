package com.ynet.iplatform.module.infra.dal.mysql.chatrobot;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation.ChatRobotConversationPageReqVO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotConversationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钉钉机器人对话 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ChatRobotConversationMapper extends BaseMapperX<ChatRobotConversationDO> {

    default PageResult<ChatRobotConversationDO> selectPage(ChatRobotConversationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatRobotConversationDO>()
                .eqIfPresent(ChatRobotConversationDO::getRobotId, reqVO.getRobotId())
                .eqIfPresent(ChatRobotConversationDO::getConversationType, reqVO.getConversationType())
                .eqIfPresent(ChatRobotConversationDO::getStatus, reqVO.getStatus())
                .likeIfPresent(ChatRobotConversationDO::getConversationTitle, reqVO.getConversationTitle())
                .orderByDesc(ChatRobotConversationDO::getLastMessageTime));
    }

}
