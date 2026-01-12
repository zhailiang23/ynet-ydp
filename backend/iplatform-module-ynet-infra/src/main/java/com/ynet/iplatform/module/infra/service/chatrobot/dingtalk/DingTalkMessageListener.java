package com.ynet.iplatform.module.infra.service.chatrobot.dingtalk;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ynet.iplatform.framework.tenant.core.util.TenantUtils;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotConversationDO;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotMessageDO;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotMapper;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotConversationMapper;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 钉钉消息监听器
 * <p>
 * 处理钉钉 Stream 推送的消息
 *
 * @author 易诚源码
 */
@Slf4j
@Component
public class DingTalkMessageListener {

    @Resource
    private ChatRobotMapper chatRobotMapper;

    @Resource
    private ChatRobotConversationMapper conversationMapper;

    @Resource
    private ChatRobotMessageMapper messageMapper;

    /**
     * 处理钉钉推送的机器人消息
     *
     * @param robotId 机器人 ID
     * @param message 钉钉推送的消息体
     * @return 响应结果
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject onRobotMessage(Long robotId, JSONObject message) {
        try {
            log.info("[DingTalkMessageListener] 收到机器人 {} 的消息: {}", robotId, message);

            // 查询机器人信息，获取租户 ID（忽略租户限制，因为此时还没有租户上下文）
            ChatRobotDO robot = TenantUtils.executeIgnore(() -> chatRobotMapper.selectById(robotId));
            if (robot == null) {
                log.error("[DingTalkMessageListener] 机器人 {} 不存在", robotId);
                return buildErrorResponse("机器人不存在");
            }
            Long tenantId = robot.getTenantId();
            log.info("[DingTalkMessageListener] 机器人租户 ID: {}", tenantId);

            // 解析消息数据
            String conversationId = message.getStr("conversationId");
            String messageId = message.getStr("msgId");
            String senderId = message.getStr("senderStaffId");
            String senderName = message.getStr("senderNick");
            String conversationTitle = message.getStr("conversationTitle"); // 群聊标题（钉钉可能提供）

            // 获取消息内容 - text 是一个对象，需要获取其 content 字段
            String content = null;
            JSONObject textObj = message.getJSONObject("text");
            if (textObj != null) {
                content = textObj.getStr("content");
            }

            String messageType = message.getStr("msgtype", "text");
            Integer conversationType = message.getInt("conversationType", 1); // 1=单聊，2=群聊

            if (conversationId == null || content == null) {
                log.warn("[DingTalkMessageListener] 消息缺少必要字段，跳过处理");
                return buildSuccessResponse();
            }

            // 使用 TenantUtils 设置租户上下文，确保 MyBatis Plus 拦截器能正确填充 tenant_id
            final String finalContent = content;
            final String finalConversationTitle = conversationTitle;
            TenantUtils.execute(tenantId, () -> {
                // 1. 处理对话记录
                ChatRobotConversationDO conversation = getOrCreateConversation(
                        robotId, conversationId, conversationType, senderId, senderName, finalConversationTitle);

                // 2. 保存消息
                ChatRobotMessageDO messageDO = ChatRobotMessageDO.builder()
                        .robotId(robotId)
                        .conversationId(conversationId)
                        .messageId(messageId)
                        .senderId(senderId)
                        .senderName(senderName)
                        .content(finalContent)
                        .messageType(messageType)
                        .source(0) // 0=钉钉用户
                        .isRead(false)
                        .sendTime(LocalDateTime.now())
                        .build();
                messageMapper.insert(messageDO);

                // 3. 更新对话统计
                conversation.setLastMessageTime(LocalDateTime.now());
                conversation.setLastMessageContent(finalContent.length() > 500 ? finalContent.substring(0, 500) : finalContent);
                conversation.setTotalMessageCount(conversation.getTotalMessageCount() + 1);
                conversation.setUnreadCount(conversation.getUnreadCount() + 1);
                conversationMapper.updateById(conversation);
            });

            log.info("[DingTalkMessageListener] 消息处理成功: conversationId={}, messageId={}", conversationId, messageId);

            return buildSuccessResponse();
        } catch (Exception e) {
            log.error("[DingTalkMessageListener] 处理消息失败", e);
            return buildErrorResponse(e.getMessage());
        }
    }

    /**
     * 获取或创建对话记录
     *
     * @param robotId           机器人 ID
     * @param conversationId    钉钉对话 ID
     * @param conversationType  对话类型
     * @param senderId          发送者 ID
     * @param senderName        发送者昵称
     * @param conversationTitle 对话标题（群聊名称）
     * @return 对话记录
     */
    private ChatRobotConversationDO getOrCreateConversation(
            Long robotId, String conversationId, Integer conversationType,
            String senderId, String senderName, String conversationTitle) {

        // 查询是否已存在
        LambdaQueryWrapper<ChatRobotConversationDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatRobotConversationDO::getRobotId, robotId)
                .eq(ChatRobotConversationDO::getConversationId, conversationId);

        ChatRobotConversationDO conversation = conversationMapper.selectOne(wrapper);

        if (conversation == null) {
            // 确定对话标题
            String title;
            if (conversationType == 1) {
                // 单聊：使用发送者昵称
                title = senderName;
            } else {
                // 群聊：优先使用钉钉提供的群聊标题，如果没有则使用默认格式
                title = (conversationTitle != null && !conversationTitle.isEmpty())
                        ? conversationTitle
                        : "群聊-" + conversationId;
            }

            // 创建新对话
            conversation = ChatRobotConversationDO.builder()
                    .robotId(robotId)
                    .conversationId(conversationId)
                    .conversationType(conversationType)
                    .conversationTitle(title)
                    .senderId(senderId)
                    .senderName(senderName)
                    .lastMessageTime(LocalDateTime.now())
                    .lastMessageContent("")
                    .totalMessageCount(0)
                    .unreadCount(0)
                    .status(0) // 0=进行中
                    .build();
            conversationMapper.insert(conversation);
            log.info("[DingTalkMessageListener] 创建新对话: {}, 标题: {}", conversationId, title);
        } else {
            // 如果已存在对话，且是群聊，检查是否需要更新标题
            if (conversationType == 2 && conversationTitle != null && !conversationTitle.isEmpty()) {
                String currentTitle = conversation.getConversationTitle();
                // 如果当前标题是默认格式（包含conversationId），则更新为真实群聊名称
                if (currentTitle != null && currentTitle.startsWith("群聊-")) {
                    conversation.setConversationTitle(conversationTitle);
                    conversationMapper.updateById(conversation);
                    log.info("[DingTalkMessageListener] 更新群聊标题: {} -> {}", currentTitle, conversationTitle);
                }
            }
        }

        return conversation;
    }

    /**
     * 构建成功响应
     *
     * @return 响应 JSON
     */
    private JSONObject buildSuccessResponse() {
        JSONObject response = new JSONObject();
        response.set("success", true);
        return response;
    }

    /**
     * 构建错误响应
     *
     * @param errorMessage 错误消息
     * @return 响应 JSON
     */
    private JSONObject buildErrorResponse(String errorMessage) {
        JSONObject response = new JSONObject();
        response.set("success", false);
        response.set("errorMessage", errorMessage);
        return response;
    }
}
