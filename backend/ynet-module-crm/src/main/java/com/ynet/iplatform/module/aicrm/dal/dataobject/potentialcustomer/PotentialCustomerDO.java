package com.ynet.iplatform.module.aicrm.dal.dataobject.potentialcustomer;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * AI CRM 潜客管理 DO
 *
 * @author AI CRM Team
 */
@TableName("aicrm_potential_customer")
@KeySequence("aicrm_potential_customer_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class PotentialCustomerDO extends TenantBaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 关联客户ID（如果已是正式客户）
     */
    private Long customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户头像URL
     */
    private String avatar;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 风险等级
     *
     * 枚举值：AGGRESSIVE=激进型, BALANCED=稳健型, CONSERVATIVE=保守型
     */
    private String riskLevel;

    /**
     * 客户等级
     *
     * 如：A/B/C/D
     */
    private String customerLevel;

    /**
     * 资产管理规模（Asset Under Management）
     */
    private BigDecimal aum;

    /**
     * 潜在价值（预计可撬动资产）
     */
    private BigDecimal potentialValue;

    /**
     * AI 匹配度（0-100）
     */
    private Integer aiMatchScore;

    /**
     * AI 分析结论
     */
    private String analysisConclusion;

    /**
     * 关联的洞察ID
     */
    private Long insightId;

    /**
     * 洞察标题（冗余字段，便于查询）
     */
    private String insightTitle;

    /**
     * 潜客状态
     *
     * 枚举值：NEW=新建, FOLLOWING=跟进中, CONVERTED=已转化, LOST=已流失
     */
    private String status;

    /**
     * 来源
     *
     * 枚举值：AI_RECOMMENDATION=AI推荐, MANUAL=手动添加, IMPORT=导入
     */
    private String source;

    /**
     * 分配给的客户经理ID
     */
    private Long assignedUserId;

    /**
     * 客户经理姓名（冗余字段）
     */
    private String assignedUserName;

    /**
     * 最后联系时间
     */
    private LocalDateTime lastContactTime;

    /**
     * 下次跟进时间
     */
    private LocalDateTime nextFollowupTime;

    /**
     * 跟进次数
     */
    private Integer followupCount;

    /**
     * 备注
     */
    private String remark;

}
