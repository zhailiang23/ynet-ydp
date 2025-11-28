package com.ynet.iplatform.module.twins.service.chatautomessages;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.twins.controller.admin.chatautomessages.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatautomessages.ChatAutoMessagesDO;
import com.ynet.iplatform.module.twins.dal.dataobject.chatsettings.ChatSettingsDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.twins.dal.mysql.chatautomessages.ChatAutoMessagesMapper;
import com.ynet.iplatform.module.twins.dal.mysql.chatsettings.ChatSettingsMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.twins.enums.ErrorCodeConstants.*;

/**
 * 客户留资消息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ChatAutoMessagesServiceImpl implements ChatAutoMessagesService {

    private static final String CARD_IMAGE_SETTING_NAME = "card-image";

    @Resource
    private ChatAutoMessagesMapper chatAutoMessagesMapper;

    @Resource
    private ChatSettingsMapper chatSettingsMapper;

    @Override
    public Integer createChatAutoMessages(ChatAutoMessagesSaveReqVO createReqVO) {
        // 处理 navigator 类型，将字段组成 JSON 存到 content
        processNavigatorType(createReqVO);

        // 插入
        ChatAutoMessagesDO chatAutoMessages = BeanUtils.toBean(createReqVO, ChatAutoMessagesDO.class);
        chatAutoMessagesMapper.insert(chatAutoMessages);

        // 返回
        return chatAutoMessages.getId();
    }

    @Override
    public void updateChatAutoMessages(ChatAutoMessagesSaveReqVO updateReqVO) {
        // 校验存在
        validateChatAutoMessagesExists(updateReqVO.getId());

        // 处理 navigator 类型，将字段组成 JSON 存到 content
        processNavigatorType(updateReqVO);

        // 更新
        ChatAutoMessagesDO updateObj = BeanUtils.toBean(updateReqVO, ChatAutoMessagesDO.class);
        chatAutoMessagesMapper.updateById(updateObj);
    }

    /**
     * 处理 navigator 类型消息，将 title、url、image 组成 JSON 存到 content
     */
    private void processNavigatorType(ChatAutoMessagesSaveReqVO reqVO) {
        if (!"navigator".equals(reqVO.getType())) {
            return;
        }

        // 获取默认图片 ID (整数类型)
        Integer imageId = getDefaultCardImage();

        // 构建 JSON 内容
        Map<String, Object> contentMap = new LinkedHashMap<>();
        contentMap.put("image", imageId);
        contentMap.put("title", reqVO.getNavigatorTitle());
        contentMap.put("url", reqVO.getNavigatorUrl());

        reqVO.setContent(JSONUtil.toJsonStr(contentMap));
    }

    /**
     * 获取默认卡片图片 ID
     */
    private Integer getDefaultCardImage() {
        ChatSettingsDO setting = chatSettingsMapper.selectByName(CARD_IMAGE_SETTING_NAME);
        if (setting != null && StrUtil.isNotBlank(setting.getValue())) {
            try {
                return Integer.parseInt(setting.getValue());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public void deleteChatAutoMessages(Integer id) {
        // 校验存在
        validateChatAutoMessagesExists(id);
        // 删除
        chatAutoMessagesMapper.deleteById(id);
    }

    @Override
        public void deleteChatAutoMessagesListByIds(List<Integer> ids) {
        // 删除
        chatAutoMessagesMapper.deleteByIds(ids);
        }


    private void validateChatAutoMessagesExists(Integer id) {
        if (chatAutoMessagesMapper.selectById(id) == null) {
            throw exception(CHAT_AUTO_MESSAGES_NOT_EXISTS);
        }
    }

    @Override
    public ChatAutoMessagesDO getChatAutoMessages(Integer id) {
        return chatAutoMessagesMapper.selectById(id);
    }

    @Override
    public PageResult<ChatAutoMessagesDO> getChatAutoMessagesPage(ChatAutoMessagesPageReqVO pageReqVO) {
        return chatAutoMessagesMapper.selectPage(pageReqVO);
    }

}