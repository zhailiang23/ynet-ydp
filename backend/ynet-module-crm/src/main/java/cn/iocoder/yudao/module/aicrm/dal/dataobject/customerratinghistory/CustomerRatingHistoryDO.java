package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerratinghistory;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户评级调整历史 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_rating_history")
@KeySequence("crm_customer_rating_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRatingHistoryDO extends BaseDO {

    /**
     * 历史主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 评级ID（关联当前评级表）
     */
    private Long ratingId;
    /**
     * 序号
     */
    private Integer sequenceNo;
    /**
     * 审批状态（字典: aicrm_rating_approval_status）
     */
    private String approvalStatus;
    /**
     * 评级日期
     */
    private LocalDate ratingDate;
    /**
     * 价值等级（字典: aicrm_value_level）
     */
    private String valueLevel;
    /**
     * 服务等级（字典: aicrm_service_level）
     */
    private String serviceLevel;
    /**
     * 剔除风险前服务等级（字典: aicrm_service_level）
     */
    private String serviceLevelBeforeRisk;
    /**
     * 风险影响因子内容
     */
    private String riskFactors;
    /**
     * 评级方式（字典: aicrm_rating_method）
     */
    private String ratingMethod;
    /**
     * 评级ID（老系统）
     */
    private String custGradeId;
    /**
     * 生效日期
     */
    private LocalDate effectiveDate;
    /**
     * 失效日期
     */
    private LocalDate expiredDate;
    /**
     * 评估日期
     */
    private LocalDate evaluateDate;
    /**
     * 客户等级（老系统字段）
     */
    private String custGrade;
    /**
     * 客户等级类型
     */
    private String custGradeType;
    /**
     * 机构编码
     */
    private String orgCode;
    /**
     * 机构名称
     */
    private String orgName;
    /**
     * 客户数量
     */
    private Integer custCnt;
    /**
     * 评级分数
     */
    private BigDecimal ratingScore;
    /**
     * 评级模型版本
     */
    private String ratingModelVersion;
    /**
     * 风险等级（字典: aicrm_risk_level）
     */
    private String riskLevel;
    /**
     * 调整原因
     */
    private String changeReason;
    /**
     * 调整类型（字典: aicrm_rating_change_type，upgrade=升级，downgrade=降级，maintain=维持）
     */
    private String changeType;
    /**
     * 调整前价值等级
     */
    private String previousValueLevel;
    /**
     * 调整前服务等级
     */
    private String previousServiceLevel;
    /**
     * 备注
     */
    private String remark;


}