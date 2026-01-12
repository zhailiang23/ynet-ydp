package com.ynet.iplatform.module.infra.service.chatrobot;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.*;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.infra.enums.ErrorCodeConstants.*;

/**
 * 对话机器人管理 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class ChatRobotServiceImpl implements ChatRobotService {

    @Resource
    private ChatRobotMapper chatRobotMapper;

    @Override
    public Long createChatRobot(ChatRobotSaveReqVO createReqVO) {
        // 插入
        ChatRobotDO chatRobot = BeanUtils.toBean(createReqVO, ChatRobotDO.class);
        chatRobotMapper.insert(chatRobot);

        // 返回
        return chatRobot.getId();
    }

    @Override
    public void updateChatRobot(ChatRobotSaveReqVO updateReqVO) {
        // 校验存在
        validateChatRobotExists(updateReqVO.getId());
        // 更新
        ChatRobotDO updateObj = BeanUtils.toBean(updateReqVO, ChatRobotDO.class);
        chatRobotMapper.updateById(updateObj);
    }

    @Override
    public void deleteChatRobot(Long id) {
        // 校验存在
        validateChatRobotExists(id);
        // 删除
        chatRobotMapper.deleteById(id);
    }

    @Override
        public void deleteChatRobotListByIds(List<Long> ids) {
        // 删除
        chatRobotMapper.deleteByIds(ids);
        }


    private void validateChatRobotExists(Long id) {
        if (chatRobotMapper.selectById(id) == null) {
            throw exception(CHAT_ROBOT_NOT_EXISTS);
        }
    }

    @Override
    public ChatRobotDO getChatRobot(Long id) {
        return chatRobotMapper.selectById(id);
    }

    @Override
    public PageResult<ChatRobotDO> getChatRobotPage(ChatRobotPageReqVO pageReqVO) {
        return chatRobotMapper.selectPage(pageReqVO);
    }

}