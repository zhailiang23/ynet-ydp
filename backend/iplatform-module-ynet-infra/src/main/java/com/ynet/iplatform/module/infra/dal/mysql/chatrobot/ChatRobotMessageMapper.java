package com.ynet.iplatform.module.infra.dal.mysql.chatrobot;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.message.ChatRobotMessagePageReqVO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotMessageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钉钉机器人消息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ChatRobotMessageMapper extends BaseMapperX<ChatRobotMessageDO> {

    default PageResult<ChatRobotMessageDO> selectPage(ChatRobotMessagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatRobotMessageDO>()
                .eqIfPresent(ChatRobotMessageDO::getConversationId, reqVO.getConversationId())
                .eqIfPresent(ChatRobotMessageDO::getRobotId, reqVO.getRobotId())
                .eqIfPresent(ChatRobotMessageDO::getMessageType, reqVO.getMessageType())
                .eqIfPresent(ChatRobotMessageDO::getSource, reqVO.getSource())
                .orderByAsc(ChatRobotMessageDO::getSendTime));
    }

}
