package com.ynet.iplatform.module.twins.dal.dataobject.chatsettings;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 聊天设置 DO
 */
@TableName("customer_chat_settings")
@KeySequence("customer_chat_settings_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatSettingsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 设置名称
     */
    private String name;
    /**
     * 设置标题
     */
    private String title;
    /**
     * 客户 id
     */
    private Integer customerId;
    /**
     * 设置值
     */
    private String value;
    /**
     * 选项
     */
    private String options;
    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
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
