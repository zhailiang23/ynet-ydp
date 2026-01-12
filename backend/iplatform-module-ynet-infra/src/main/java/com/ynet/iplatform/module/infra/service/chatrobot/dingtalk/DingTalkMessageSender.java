package com.ynet.iplatform.module.infra.service.chatrobot.dingtalk;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.dingtalkrobot_1_0.Client;
import com.aliyun.dingtalkrobot_1_0.models.OrgGroupSendHeaders;
import com.aliyun.dingtalkrobot_1_0.models.OrgGroupSendRequest;
import com.aliyun.dingtalkrobot_1_0.models.OrgGroupSendResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 钉钉消息发送器
 * <p>
 * 负责调用钉钉 API 发送消息
 *
 * @author 易诚源码
 */
@Slf4j
@Component
public class DingTalkMessageSender {

    @Resource
    private ChatRobotMapper chatRobotMapper;

    /**
     * 发送消息到钉钉对话
     *
     * @param robotId        机器人 ID
     * @param conversationId 对话 ID
     * @param content        消息内容
     * @param messageType    消息类型 (text, markdown, image, etc.)
     * @return 是否发送成功
     */
    public boolean sendMessage(Long robotId, String conversationId, String content, String messageType) {
        try {
            // 1. 获取机器人配置
            ChatRobotDO robot = chatRobotMapper.selectById(robotId);
            if (robot == null) {
                log.error("[DingTalkMessageSender] 机器人 {} 不存在", robotId);
                return false;
            }

            JSONObject config = JSONUtil.parseObj(robot.getPlatformConfig());
            String appKey = config.getStr("appKey");
            String appSecret = config.getStr("appSecret");
            String robotCode = appKey; // robotCode 就是 appKey

            if (appKey == null || appSecret == null) {
                log.error("[DingTalkMessageSender] 机器人 {} 配置缺少 appKey 或 appSecret", robotId);
                return false;
            }

            // 2. 获取 accessToken
            String accessToken = getAccessToken(appKey, appSecret);
            if (accessToken == null) {
                log.error("[DingTalkMessageSender] 获取 accessToken 失败");
                return false;
            }

            // 3. 构建消息体
            String msgKey;
            String msgParam;

            switch (messageType) {
                case "markdown":
                    msgKey = "sampleMarkdown";
                    msgParam = JSONUtil.createObj()
                            .set("text", content)
                            .set("title", "消息")
                            .toString();
                    break;
                case "text":
                default:
                    msgKey = "sampleText";
                    msgParam = JSONUtil.createObj()
                            .set("content", content)
                            .toString();
                    break;
            }

            // 4. 调用钉钉 API 发送消息
            Client client = createClient();
            OrgGroupSendHeaders headers = new OrgGroupSendHeaders();
            headers.xAcsDingtalkAccessToken = accessToken;

            OrgGroupSendRequest request = new OrgGroupSendRequest()
                    .setRobotCode(robotCode)
                    .setMsgKey(msgKey)
                    .setMsgParam(msgParam)
                    .setOpenConversationId(conversationId);

            RuntimeOptions runtime = new RuntimeOptions();
            OrgGroupSendResponse response = client.orgGroupSendWithOptions(request, headers, runtime);

            log.info("[DingTalkMessageSender] 发送消息成功: conversationId={}, processQueryKey={}",
                    conversationId, response.getBody().getProcessQueryKey());

            return true;
        } catch (Exception e) {
            log.error("[DingTalkMessageSender] 发送消息失败: robotId={}, conversationId={}", robotId, conversationId, e);
            return false;
        }
    }

    /**
     * 获取钉钉 AccessToken
     * <p>
     * 使用 Spring Cache 缓存 1 小时（钉钉 Token 有效期 2 小时）
     *
     * @param appKey    应用 Key
     * @param appSecret 应用 Secret
     * @return AccessToken
     */
    @Cacheable(value = "dingTalkAccessToken", key = "#appKey", unless = "#result == null")
    public String getAccessToken(String appKey, String appSecret) {
        try {
            // 使用钉钉 SDK 获取 Token
            com.aliyun.dingtalkoauth2_1_0.Client authClient = createAuthClient();
            com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest request =
                    new com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest()
                            .setAppKey(appKey)
                            .setAppSecret(appSecret);

            com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse response =
                    authClient.getAccessToken(request);

            String accessToken = response.getBody().getAccessToken();
            log.info("[DingTalkMessageSender] 获取 accessToken 成功: appKey={}", appKey);
            return accessToken;
        } catch (Exception e) {
            log.error("[DingTalkMessageSender] 获取 accessToken 失败: appKey={}", appKey, e);
            return null;
        }
    }

    /**
     * 创建钉钉机器人客户端
     *
     * @return 客户端实例
     */
    private Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new Client(config);
    }

    /**
     * 创建钉钉认证客户端
     *
     * @return 认证客户端实例
     */
    private com.aliyun.dingtalkoauth2_1_0.Client createAuthClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }
}
