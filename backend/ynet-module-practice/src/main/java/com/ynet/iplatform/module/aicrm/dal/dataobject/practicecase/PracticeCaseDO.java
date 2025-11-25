package com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-销售案例 DO
 *
 * @author 芋道源码
 */
@TableName("crm_practice_case")
@KeySequence("crm_practice_case_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeCaseDO extends BaseDO {

    /**
     * 案例ID
     */
    @TableId
    private Long id;
    /**
     * 案例标题
     */
    private String title;
    /**
     * 案例详细内容（正文）
     */
    private String content;
    /**
     * 标签（多个标签逗号分隔）
     */
    private String tags;


}