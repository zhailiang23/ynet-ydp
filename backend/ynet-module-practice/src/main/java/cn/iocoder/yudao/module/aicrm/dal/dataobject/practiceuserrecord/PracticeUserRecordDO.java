package cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceuserrecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-用户陪练记录 DO
 *
 * @author 芋道源码
 */
@TableName("crm_practice_user_record")
@KeySequence("crm_practice_user_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeUserRecordDO extends BaseDO {

    /**
     * 记录ID
     */
    @TableId
    private Long id;
    /**
     * 关联陪练课程ID
     */
    private Long courseId;
    /**
     * 参与用户ID
     */
    private Long userId;
    /**
     * 虚拟用户ID
     */
    private Long vcustomerId;
    /**
     * 记录编号（唯一）
     */
    private String recordNo;
    /**
     * 记录状态：字典 aicrm_record_status
     */
    private String status;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 实际时长（分钟）
     */
    private Integer duration;
    /**
     * 总评分
     */
    private BigDecimal totalScore;
    /**
     * 各维度得分（JSON格式）
     */
    private String dimensionScores;
    /**
     * 完成进度（%）
     */
    private BigDecimal completionRate;
    /**
     * AI 总结评价
     */
    private String aiSummary;
    /**
     * 优点总结（JSON格式）
     */
    private String strengths;
    /**
     * 待改进点（JSON格式）
     */
    private String weaknesses;
    /**
     * 改进建议（JSON格式）
     */
    private String recommendations;


}