package cn.iocoder.yudao.module.aicrm.dal.dataobject.companyotherbank;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 对公客户他行信息 DO
 *
 * @author 芋道源码
 */
@TableName("crm_company_other_bank")
@KeySequence("crm_company_other_bank_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOtherBankDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联crm_customer表）
     */
    private Long customerId;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）
     */
    private String bankType;
    /**
     * 开户支行名称
     */
    private String branchName;
    /**
     * 他行客户经理姓名
     */
    private String relationshipManager;
    /**
     * 他行客户经理电话
     */
    private String managerPhone;
    /**
     * 他行客户经理邮箱
     */
    private String managerEmail;
    /**
     * 合作类型（主办行、协办行、一般合作）
     */
    private String cooperationType;
    /**
     * 合作开始日期
     */
    private LocalDate cooperationStartDate;
    /**
     * 合作年限
     */
    private Integer relationshipDuration;
    /**
     * 合作状态（1=合作中 2=已终止 3=暂停合作）
     */
    private Integer cooperationStatus;
    /**
     * 是否有结算账户（0=否 1=是）
     */
    private Integer hasSettlementAccount;
    /**
     * 结算账户账号
     */
    private String settlementAccountNo;
    /**
     * 账户余额（元）
     */
    private BigDecimal accountBalance;
    /**
     * 是否主结算行（0=否 1=是）
     */
    private Integer isMainSettlementBank;
    /**
     * 日均存款（元）
     */
    private BigDecimal dailyAverageBalance;
    /**
     * 授信总额度（元）
     */
    private BigDecimal totalCreditLimit;
    /**
     * 已用授信额度（元）
     */
    private BigDecimal usedCreditLimit;
    /**
     * 贷款余额（元）
     */
    private BigDecimal loanBalance;
    /**
     * 存款余额（元）
     */
    private BigDecimal depositBalance;
    /**
     * 理财余额（元）
     */
    private BigDecimal wealthManagementBalance;
    /**
     * 业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）
     */
    private String businessTypes;
    /**
     * 主要业务
     */
    private String mainBusiness;
    /**
     * 贷款产品名称
     */
    private String loanProductName;
    /**
     * 贷款金额（元）
     */
    private BigDecimal loanAmount;
    /**
     * 贷款利率（%）
     */
    private BigDecimal loanRate;
    /**
     * 贷款起始日
     */
    private LocalDate loanStartDate;
    /**
     * 贷款到期日
     */
    private LocalDate loanMaturityDate;
    /**
     * 担保方式（信用、抵押、质押、保证等）
     */
    private String guaranteeType;
    /**
     * 抵押物信息
     */
    private String collateralInfo;
    /**
     * 服务满意度（1-5星）
     */
    private Integer serviceSatisfaction;
    /**
     * 价格水平（优惠、市场价、偏高）
     */
    private String pricingLevel;
    /**
     * 响应速度（快、一般、慢）
     */
    private String responseSpeed;
    /**
     * 客户评价
     */
    private String customerComment;
    /**
     * 他行优势（为什么客户选择他行）
     */
    private String competitorAdvantage;
    /**
     * 他行劣势（客户不满意的地方）
     */
    private String competitorDisadvantage;
    /**
     * 我行机会点（可以从哪些方面切入）
     */
    private String ourOpportunity;
    /**
     * 竞争策略（如何争取客户份额）
     */
    private String competitiveStrategy;
    /**
     * 目标业务（希望从他行抢占的业务）
     */
    private String targetBusiness;
    /**
     * 营销优先级（1=高 2=中 3=低）
     */
    private Integer marketingPriority;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 合同到期日
     */
    private LocalDate contractExpiryDate;
    /**
     * 是否即将到期（0=否 1=是，3个月内到期）
     */
    private Integer isDueSoon;
    /**
     * 跟进计划
     */
    private String followUpPlan;
    /**
     * 风险提示（如他行抽贷风险、担保风险等）
     */
    private String riskWarning;
    /**
     * 信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）
     */
    private String infoSource;
    /**
     * 信息可靠性（1=高 2=中 3=低）
     */
    private Integer infoReliability;
    /**
     * 信息最后更新日期
     */
    private LocalDate lastUpdateDate;
    /**
     * 备注
     */
    private String remark;


}