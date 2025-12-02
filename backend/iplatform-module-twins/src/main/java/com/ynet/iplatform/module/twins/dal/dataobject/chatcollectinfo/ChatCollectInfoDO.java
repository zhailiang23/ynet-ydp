package com.ynet.iplatform.module.twins.dal.dataobject.chatcollectinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户留资信息 DO
 *
 * @author 易诚源码
 */
@TableName("customer_chat_collect_info")
@KeySequence("customer_chat_collect_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCollectInfoDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 留资模板ID，关联customer_chat_collect_template表
     */
    private Integer templateId;
    /**
     * 留资内容，JSON格式
     */
    private String content;
    /**
     * 留资状态：0-未处理，1-处理中，2-处理完成
     */
    private Integer status;
    /**
     * 受理时间，开始处理的时间
     */
    private LocalDateTime acceptTime;
    /**
     * 完成时间，处理完成的时间
     */
    private LocalDateTime finishTime;
    /**
     * 客户ID
     */
    private Integer customerId;
    /**
     * 提交用户ID
     */
    private Integer userId;
    /**
     * 处理客服ID
     */
    private Integer adminId;
    /**
     * 处理备注
     */
    private String remark;


}