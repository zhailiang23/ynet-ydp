package cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceconversation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-陪练对话 DO
 *
 * @author 芋道源码
 */
@TableName("crm_practice_conversation")
@KeySequence("crm_practice_conversation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeConversationDO extends BaseDO {

    /**
     * 对话ID
     */
    @TableId
    private Long id;
    /**
     * 关联用户陪练记录ID
     */
    private Long recordId;
    /**
     * 对话序号（从1开始）
     */
    private Integer sequenceNo;
    /**
     * 发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）
     */
    private String role;
    /**
     * 消息内容
     */
    private String messageContent;
    /**
     * 消息时间
     */
    private LocalDateTime messageTime;
    /**
     * 即时评分（针对学员发言）
     */
    private BigDecimal instantScore;
    /**
     * 话术分析（JSON格式）
     */
    private String speechAnalysis;
    /**
     * 技巧运用评价（JSON格式）
     */
    private String skillEvaluation;
    /**
     * 对话情绪标签：字典 aicrm_emotion_tag
     */
    private String emotionTag;
    /**
     * 销售意图识别：字典 aicrm_sales_intent
     */
    private String salesIntent;
    /**
     * 客户反应：字典 aicrm_customer_reaction
     */
    private String customerReaction;
    /**
     * AI改进建议（JSON格式）
     */
    private String improvementSuggestions;
    /**
     * 推荐话术（JSON格式）
     */
    private String recommendedScripts;


}