package com.ynet.iplatform.module.infra.service.chatrobot.dingtalk;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import com.ynet.iplatform.module.infra.dal.mysql.chatrobot.ChatRobotMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 钉钉 Stream 客户端管理器
 * <p>
 * 负责管理所有启用的钉钉机器人的 Stream 连接
 *
 * @author 易诚源码
 */
@Slf4j
@Component
public class DingTalkStreamManager {

    @Resource
    private ChatRobotMapper chatRobotMapper;

    @Resource
    private DingTalkMessageListener messageListener;

    /**
     * 存储所有活跃的 Stream 客户端
     * key: robotId, value: Stream 客户端
     */
    private final Map<Long, OpenDingTalkClient> streamClients = new ConcurrentHashMap<>();

    /**
     * 应用启动时自动初始化所有钉钉机器人的 Stream 连接
     */
    @PostConstruct
    public void init() {
        log.info("[DingTalkStreamManager] 开始初始化钉钉机器人 Stream 连接");
        try {
            // 查询所有启用的钉钉机器人
            LambdaQueryWrapper<ChatRobotDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ChatRobotDO::getStatus, 1) // status = 1 表示启用
                        .eq(ChatRobotDO::getPlatformType, "dingtalk"); // 平台类型为钉钉
            List<ChatRobotDO> robots = chatRobotMapper.selectList(queryWrapper);

            if (robots.isEmpty()) {
                log.info("[DingTalkStreamManager] 未找到启用的钉钉机器人");
                return;
            }

            // 为每个机器人创建 Stream 连接
            for (ChatRobotDO robot : robots) {
                try {
                    connectRobot(robot);
                } catch (Exception e) {
                    log.error("[DingTalkStreamManager] 机器人 {} 连接失败", robot.getName(), e);
                }
            }

            log.info("[DingTalkStreamManager] 初始化完成，已连接 {} 个钉钉机器人", streamClients.size());
        } catch (Exception e) {
            log.error("[DingTalkStreamManager] 初始化失败", e);
        }
    }

    /**
     * 为单个机器人创建 Stream 连接
     *
     * @param robot 机器人实体
     */
    public void connectRobot(ChatRobotDO robot) {
        if (!"dingtalk".equals(robot.getPlatformType())) {
            log.warn("[DingTalkStreamManager] 机器人 {} 不是钉钉平台，跳过连接", robot.getName());
            return;
        }

        try {
            // 解析 platformConfig JSON 获取 appKey 和 appSecret
            JSONObject config = JSONUtil.parseObj(robot.getPlatformConfig());
            String appKey = config.getStr("appKey");
            String appSecret = config.getStr("appSecret");

            if (appKey == null || appSecret == null) {
                log.error("[DingTalkStreamManager] 机器人 {} 的 platformConfig 缺少 appKey 或 appSecret", robot.getName());
                return;
            }

            // 检查是否已经连接
            if (streamClients.containsKey(robot.getId())) {
                log.warn("[DingTalkStreamManager] 机器人 {} 已经连接，先断开旧连接", robot.getName());
                disconnectRobot(robot.getId());
            }

            // 创建 Stream 客户端
            log.info("[DingTalkStreamManager] 正在连接机器人 {} (ID: {}), appKey: {}", robot.getName(), robot.getId(), appKey);

            OpenDingTalkClient client = OpenDingTalkStreamClientBuilder
                    .custom()
                    .credential(new AuthClientCredential(appKey, appSecret))
                    .registerCallbackListener("/v1.0/im/bot/messages/get", new OpenDingTalkCallbackListener<JSONObject, JSONObject>() {
                        @Override
                        public JSONObject execute(JSONObject message) {
                            log.info("[DingTalkStreamManager] 机器人 {} 收到消息回调", robot.getId());
                            log.debug("[DingTalkStreamManager] 消息内容: {}", message);
                            return messageListener.onRobotMessage(robot.getId(), message);
                        }
                    })
                    .build();

            // 启动客户端
            log.info("[DingTalkStreamManager] 正在启动 Stream 客户端...");
            client.start();
            log.info("[DingTalkStreamManager] Stream 客户端已启动");

            // 保存到连接池
            streamClients.put(robot.getId(), client);

            log.info("[DingTalkStreamManager] 机器人 {} (ID: {}) 连接成功，callback topic: /v1.0/im/bot/messages/get", robot.getName(), robot.getId());
        } catch (Exception e) {
            log.error("[DingTalkStreamManager] 机器人 {} 连接失败", robot.getName(), e);
            throw new RuntimeException("钉钉机器人连接失败: " + e.getMessage(), e);
        }
    }

    /**
     * 断开指定机器人的 Stream 连接
     *
     * @param robotId 机器人 ID
     */
    public void disconnectRobot(Long robotId) {
        OpenDingTalkClient client = streamClients.remove(robotId);
        if (client != null) {
            try {
                client.stop();
                log.info("[DingTalkStreamManager] 机器人 {} 连接已断开", robotId);
            } catch (Exception e) {
                log.error("[DingTalkStreamManager] 断开机器人 {} 连接时出错", robotId, e);
            }
        }
    }

    /**
     * 重新连接指定机器人
     *
     * @param robotId 机器人 ID
     */
    public void reconnectRobot(Long robotId) {
        ChatRobotDO robot = chatRobotMapper.selectById(robotId);
        if (robot == null) {
            log.error("[DingTalkStreamManager] 机器人 {} 不存在", robotId);
            return;
        }

        disconnectRobot(robotId);
        connectRobot(robot);
    }

    /**
     * 应用关闭时断开所有连接
     */
    @PreDestroy
    public void destroy() {
        log.info("[DingTalkStreamManager] 开始断开所有钉钉机器人 Stream 连接");
        for (Map.Entry<Long, OpenDingTalkClient> entry : streamClients.entrySet()) {
            try {
                entry.getValue().stop();
                log.info("[DingTalkStreamManager] 机器人 {} 连接已断开", entry.getKey());
            } catch (Exception e) {
                log.error("[DingTalkStreamManager] 断开机器人 {} 连接时出错", entry.getKey(), e);
            }
        }
        streamClients.clear();
        log.info("[DingTalkStreamManager] 所有连接已断开");
    }

    /**
     * 获取当前活跃的机器人数量
     *
     * @return 活跃机器人数量
     */
    public int getActiveRobotCount() {
        return streamClients.size();
    }

    /**
     * 检查指定机器人是否已连接
     *
     * @param robotId 机器人 ID
     * @return 是否已连接
     */
    public boolean isConnected(Long robotId) {
        return streamClients.containsKey(robotId);
    }
}
