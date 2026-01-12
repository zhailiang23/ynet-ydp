package com.ynet.iplatform.module.infra.dal.dataobject.chatrobot;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 对话机器人管理 DO
 *
 * @author 易诚源码
 */
@TableName("infra_chat_robot")
@KeySequence("infra_chat_robot_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRobotDO extends BaseDO {

    /**
     * 机器人ID
     */
    @TableId
    private Long id;
    /**
     * 机器人名称
     */
    private String name;
    /**
     * 机器人描述
     */
    private String description;
    /**
     * 对接平台类型
     */
    private String platformType;
    /**
     * 平台配置（JSON格式，存储webhook、appKey、appSecret等平台特定配置）
     */
    private String platformConfig;
    /**
     * 绑定的智能体
     */
    private Long agentId;
    /**
     * 是否启用（0=禁用，1=启用）
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 租户编号
     */
    @TableField("tenant_id")
    private Long tenantId;


}