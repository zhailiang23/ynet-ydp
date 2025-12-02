package com.ynet.iplatform.module.aicrm.dal.dataobject.practiceskill;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-销售技巧 DO
 *
 * @author 易诚源码
 */
@TableName("crm_practice_skill")
@KeySequence("crm_practice_skill_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeSkillDO extends BaseDO {

    /**
     * 技巧ID
     */
    @TableId
    private Long id;
    /**
     * 技巧名称
     */
    private String name;
    /**
     * 技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧
     */
    private String category;
    /**
     * 话术模板
     */
    private String scriptTemplate;
    /**
     * 合规规则（培训文件ID）
     */
    private Long complianceRules;
    /**
     * 产品知识（培训文件ID）
     */
    private Long relatedProducts;


}