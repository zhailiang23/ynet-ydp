package com.ynet.iplatform.module.twins.dal.dataobject.customeradminchatsettings;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客服聊天设置 DO
 */
@TableName("customer_admin_chat_settings")
@KeySequence("customer_admin_chat_settings_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAdminChatSettingsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 客服 ID
     */
    private Integer adminId;
    /**
     * 背景设置
     */
    private Integer background;
    /**
     * 是否自动接入
     */
    private Integer isAutoAccept;
    /**
     * 欢迎语
     */
    private String welcomeContent;
    /**
     * 离线提示语
     */
    private String offlineContent;
    /**
     * 客服名称
     */
    private String name;
    /**
     * 最后在线时间
     */
    private LocalDateTime lastOnline;
    /**
     * 头像
     */
    private Integer avatar;
    /**
     * 是否启用 AI
     */
    private Integer isAiEnabled;
    /**
     * 创建时间（原始字段）
     */
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createdAt;
    /**
     * 修改时间（原始字段）
     */
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updatedAt;

}
