package com.ynet.iplatform.module.aicrm.dal.dataobject.retailcustomer;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM零售客户扩展表(零售客户特有基本信息) DO
 *
 * @author 易诚源码
 */
@TableName("crm_retail_customer")
@KeySequence("crm_retail_customer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetailCustomerDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID(外键,关联crm_customer.id,唯一)
     */
    private Long customerId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 曾用名
     */
    private String usedName;
    /**
     * 姓氏
     */
    private String surname;
    /**
     * 拼音全名
     */
    private String pinyinName;
    /**
     * 拼音缩写
     */
    private String pinyinAbbr;
    /**
     * 人员称谓(先生/女士/小姐等)
     *
     * 枚举 {@link TODO crm_person_title 对应的类}
     */
    private String personTitle;
    /**
     * 性别(0=未知, 1=男, 2=女)
     *
     * 枚举 {@link TODO system_user_sex 对应的类}
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private LocalDate birthday;
    /**
     * 出生地
     */
    private String birthLocale;
    /**
     * 证件类型(如:身份证、护照等)
     */
    private String idCardType;
    /**
     * 证件号码
     */
    private String idCardNo;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 年龄(可通过出生日期计算)
     */
    private Integer age;
    /**
     * 年龄段
     */
    private String ageRange;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 公民身份
     */
    private String citizenship;
    /**
     * 民族(如:汉族、回族等)
     */
    private String nation;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 户籍类型(如:居民、非居民)
     */
    private String residenceType;
    /**
     * 居住时长类型
     */
    private String residenceDurationType;
    /**
     * 户口所在地(详细地址)
     */
    private String domicilePlace;
    /**
     * 户口类型(农业/非农业)
     *
     * 枚举 {@link TODO crm_household_type 对应的类}
     */
    private String householdType;
    /**
     * 职业(如:专业技术人员、公务员等)
     */
    private String occupation;
    /**
     * 工作单位名称
     */
    private String employerName;
    /**
     * 单位性质(国企/私企/外企等)
     *
     * 枚举 {@link TODO crm_employer_type 对应的类}
     */
    private String employerType;
    /**
     * 职位
     */
    private String position;
    /**
     * 技术职称
     */
    private String technicalTitle;
    /**
     * 职业类型分类
     */
    private String occupationType;
    /**
     * 职业状态(在职/离职/退休等)
     *
     * 枚举 {@link TODO crm_career_status 对应的类}
     */
    private String careerStatus;
    /**
     * 职业开始日期
     */
    private LocalDate careerStartDate;
    /**
     * 简历
     */
    private String resume;
    /**
     * 婚姻状态(1=未婚, 2=已婚, 3=离异, 4=丧偶)
     */
    private Integer maritalStatus;
    /**
     * 健康状况
     *
     * 枚举 {@link TODO crm_health_status 对应的类}
     */
    private String healthStatus;
    /**
     * 宗教信仰
     */
    private String religion;
    /**
     * 政治面貌
     *
     * 枚举 {@link TODO crm_political_status 对应的类}
     */
    private String politicalStatus;
    /**
     * 受教育程度(如:本科、硕士、博士等)
     */
    private String education;
    /**
     * 最高学位(如:学士、硕士、博士)
     */
    private String degree;
    /**
     * 毕业学校
     */
    private String graduateSchool;
    /**
     * 专业
     */
    private String major;
    /**
     * 毕业日期
     */
    private LocalDate graduationDate;
    /**
     * 是否VIP客户(0=否, 1=是)
     */
    private Boolean isVip;
    /**
     * 是否核心VIP客户(0=否, 1=是)
     */
    private Boolean isCoreVip;
    /**
     * VIP等级(如:金卡、银卡、钻石卡等)
     */
    private String vipLevel;
    /**
     * VIP积分
     */
    private Integer vipPoints;
    /**
     * VIP开始日期
     */
    private LocalDate vipStartDate;
    /**
     * VIP到期日期
     */
    private LocalDate vipEndDate;
    /**
     * 是否高净值客户(0=否, 1=是)
     */
    private Boolean isHighNetWorth;
    /**
     * 净值类型(如:个体经营户、工薪阶层等)
     */
    private String netWorthType;
    /**
     * 收入水平(如:高、中、低)
     */
    private String incomeLevel;
    /**
     * 年收入
     */
    private BigDecimal annualIncome;
    /**
     * 年收入范围
     */
    private String annualIncomeRange;
    /**
     * 月收入
     */
    private BigDecimal monthlyIncome;
    /**
     * 收入来源
     */
    private String sourceOfIncome;
    /**
     * 纳税情况
     */
    private String taxPayment;
    /**
     * 资产水平(如:高、中、低)
     */
    private String assetLevel;
    /**
     * 资产总额
     */
    private BigDecimal assets;
    /**
     * 基金持有金额
     */
    private BigDecimal fundHoldings;
    /**
     * 总投资金额
     */
    private BigDecimal totalInvestment;
    /**
     * 投资性质
     *
     * 枚举 {@link TODO crm_investment_nature 对应的类}
     */
    private String investmentNature;
    /**
     * 持股金额
     */
    private BigDecimal stockHoldings;
    /**
     * 负债总额
     */
    private BigDecimal liabilities;
    /**
     * 是否有房产(0=否, 1=是)
     */
    private Boolean hasHouse;
    /**
     * 是否有车(0=否, 1=是)
     */
    private Boolean hasCar;
    /**
     * 是否有保险(0=否, 1=是)
     */
    private Boolean hasInsurance;
    /**
     * 是否有贷款记录(0=否, 1=是)
     */
    private Boolean hasLoanRecord;
    /**
     * 是否有逾期记录(0=否, 1=是)
     */
    private Boolean hasOverdueRecord;
    /**
     * 黑名单标志(0=否, 1=是)
     */
    private Boolean blacklistFlag;
    /**
     * 信誉状态(如:优秀、良好、一般、较差)
     */
    private String reputationStatus;
    /**
     * 信誉级别
     */
    private String reputationLevel;
    /**
     * 信誉综合评分
     */
    private BigDecimal reputationScore;
    /**
     * 零售客户类型(如:正式客户、潜在客户等)
     */
    private String retailCustomerType;
    /**
     * 个人客户类型(老系统字段)
     */
    private String personCustomerType;
    /**
     * 个人行为评价
     */
    private String personConductEval;
    /**
     * 客户银行关系
     */
    private String customerBankRelation;
    /**
     * 公司关联度
     */
    private String companyRelationDegree;
    /**
     * 个体类型
     */
    private String individualType;
    /**
     * 是否高端户(0=否, 1=是)
     */
    private Boolean isHighEndCustomer;
    /**
     * 是否综合户(0=否, 1=是)
     */
    private Boolean isComprehensiveCustomer;
    /**
     * 客户归属网格编号
     */
    private String customerGridCode;
    /**
     * 资质/风险(如:低、中、高)
     */
    private String qualificationRisk;
    /**
     * 信誉水险等级(如:低、中、高)
     */
    private String creditRiskLevel;
    /**
     * 信誉水险评级
     */
    private String creditRiskRating;
    /**
     * 是否我行代发工资客户(0=否, 1=是)
     */
    private Boolean isPayrollCustomer;
    /**
     * 代发工资单位名称
     */
    private String payrollCompanyName;
    /**
     * 工资账户银行
     */
    private String salaryAccountBank;
    /**
     * 成为我行客户时间(日期)
     */
    private LocalDate becomeCustomerDate;
    /**
     * 开始日期
     */
    private LocalDate startDate;
    /**
     * 登记类型
     *
     * 枚举 {@link TODO crm_registration_type 对应的类}
     */
    private String registrationType;
    /**
     * 登记号
     */
    private String registrationNo;
    /**
     * 登记开始日期
     */
    private LocalDate registrationStartDate;
    /**
     * 登记结束日期
     */
    private LocalDate registrationEndDate;
    /**
     * 在我行建立信任关系时间(日期)
     */
    private LocalDate establishTrustDate;
    /**
     * 是否本行修改(0=否, 1=是)
     */
    private Boolean isBankModified;
    /**
     * 扩展字段1
     */
    private String extField1;
    /**
     * 扩展字段2
     */
    private String extField2;
    /**
     * 扩展字段3
     */
    private String extField3;
    /**
     * ETL导入日期(数据迁移追溯)
     */
    private LocalDate etlDate;
    /**
     * 老系统交易序列号(数据迁移追溯)
     */
    private String oldTxSeqNo;
    /**
     * 老系统客户ID(数据迁移追溯)
     */
    private String oldCustId;
    /**
     * 老系统最后更新系统(数据迁移追溯)
     */
    private String oldLastUpdateSys;


}
