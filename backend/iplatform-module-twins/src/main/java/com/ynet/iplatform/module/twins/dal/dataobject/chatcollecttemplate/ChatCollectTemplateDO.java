package com.ynet.iplatform.module.twins.dal.dataobject.chatcollecttemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户留资模板 DO
 *
 * @author 芋道源码
 */
@TableName("customer_chat_collect_template")
@KeySequence("customer_chat_collect_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCollectTemplateDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Integer id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板描述
     */
    private String description;
    /**
     * 模板链接
     */
    private String url;
    /**
     * 客户ID
     */
    private Integer customerId;
    /**
     * 状态：0-禁用，1-启用
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;


}