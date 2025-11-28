package com.ynet.iplatform.module.twins.dal.mysql.chatautomessages;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.twins.dal.dataobject.chatautomessages.ChatAutoMessagesDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.twins.controller.admin.chatautomessages.vo.*;

/**
 * 客户留资消息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ChatAutoMessagesMapper extends BaseMapperX<ChatAutoMessagesDO> {

    default PageResult<ChatAutoMessagesDO> selectPage(ChatAutoMessagesPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatAutoMessagesDO>()
                .likeIfPresent(ChatAutoMessagesDO::getName, reqVO.getName())
                .eqIfPresent(ChatAutoMessagesDO::getType, reqVO.getType())
                .orderByDesc(ChatAutoMessagesDO::getId));
    }

}