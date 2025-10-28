package cn.iocoder.yudao.module.aicrm.controller.admin.retailcustomer.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - CRM零售客户扩展表(零售客户特有基本信息) Response VO")
@Data
@ExcelIgnoreUnannotated
public class RetailCustomerRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "客户ID(外键,关联crm_customer.id,唯一)", requiredMode = Schema.RequiredMode.REQUIRED, example = "24261")
    @ExcelProperty("客户ID(外键,关联crm_customer.id,唯一)")
    private Long customerId;

    @Schema(description = "昵称", example = "芋艿")
    @ExcelProperty("昵称")
    private String nickname;

    @Schema(description = "性别(0=未知, 1=男, 2=女)")
    @ExcelProperty(value = "性别(0=未知, 1=男, 2=女)", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer gender;

    @Schema(description = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDate birthday;

    @Schema(description = "证件类型(如:身份证、护照等)")
    @ExcelProperty("证件类型")
    private String idCardType;

    @Schema(description = "证件号码")
    @ExcelProperty("证件号码")
    private String idCardNo;

    @Schema(description = "年龄(可通过出生日期计算)")
    @ExcelProperty("年龄(可通过出生日期计算)")
    private Integer age;

    @Schema(description = "国籍")
    @ExcelProperty("国籍")
    private String nationality;

    @Schema(description = "民族(如:汉族、回族等)")
    @ExcelProperty("民族")
    private String nation;

    @Schema(description = "籍贯")
    @ExcelProperty("籍贯")
    private String nativePlace;

    @Schema(description = "户籍类型(如:居民、非居民)")
    @ExcelProperty("户籍类型")
    private String residenceType;

    @Schema(description = "户口所在地(详细地址)")
    @ExcelProperty("户口所在地")
    private String domicilePlace;

    @Schema(description = "职业(如:专业技术人员、公务员等)")
    @ExcelProperty("职业")
    private String occupation;

    @Schema(description = "工作单位名称")
    @ExcelProperty("工作单位名称")
    private String employerName;

    @Schema(description = "职位")
    @ExcelProperty("职位")
    private String position;

    @Schema(description = "职业类型分类")
    @ExcelProperty("职业类型分类")
    private String occupationType;

    @Schema(description = "婚姻状态(1=未婚, 2=已婚, 3=离异, 4=丧偶)")
    @ExcelProperty("婚姻状态")
    private Integer maritalStatus;

    @Schema(description = "宗教信仰")
    @ExcelProperty("宗教信仰")
    private String religion;

    @Schema(description = "受教育程度(如:本科、硕士、博士等)")
    @ExcelProperty("受教育程度")
    private String education;

    @Schema(description = "最高学位(如:学士、硕士、博士)")
    @ExcelProperty("最高学位")
    private String degree;

    @Schema(description = "是否VIP客户")
    @ExcelProperty("是否VIP客户")
    private Boolean isVip;

    @Schema(description = "是否核心VIP客户")
    @ExcelProperty("是否核心VIP客户")
    private Boolean isCoreVip;

    @Schema(description = "VIP等级(如:金卡、银卡、钻石卡等)")
    @ExcelProperty("VIP等级")
    private String vipLevel;

    @Schema(description = "VIP积分")
    @ExcelProperty("VIP积分")
    private Integer vipPoints;

    @Schema(description = "VIP开始日期")
    @ExcelProperty("VIP开始日期")
    private LocalDate vipStartDate;

    @Schema(description = "VIP到期日期")
    @ExcelProperty("VIP到期日期")
    private LocalDate vipEndDate;

    @Schema(description = "是否高净值客户")
    @ExcelProperty("是否高净值客户")
    private Boolean isHighNetWorth;

    @Schema(description = "净值类型(如:个体经营户、工薪阶层等)")
    @ExcelProperty("净值类型")
    private String netWorthType;

    @Schema(description = "收入水平(如:高、中、低)")
    @ExcelProperty("收入水平")
    private String incomeLevel;

    @Schema(description = "年收入")
    @ExcelProperty("年收入")
    private BigDecimal annualIncome;

    @Schema(description = "月收入")
    @ExcelProperty("月收入")
    private BigDecimal monthlyIncome;

    @Schema(description = "收入来源")
    @ExcelProperty("收入来源")
    private String sourceOfIncome;

    @Schema(description = "资产水平(如:高、中、低)")
    @ExcelProperty("资产水平")
    private String assetLevel;

    @Schema(description = "资产总额")
    @ExcelProperty("资产总额")
    private BigDecimal assets;

    @Schema(description = "负债总额")
    @ExcelProperty("负债总额")
    private BigDecimal liabilities;

    @Schema(description = "是否有房产")
    @ExcelProperty("是否有房产")
    private Boolean hasHouse;

    @Schema(description = "是否有车")
    @ExcelProperty("是否有车")
    private Boolean hasCar;

    @Schema(description = "是否有保险")
    @ExcelProperty("是否有保险")
    private Boolean hasInsurance;

    @Schema(description = "是否有贷款记录")
    @ExcelProperty("是否有贷款记录")
    private Boolean hasLoanRecord;

    @Schema(description = "是否有逾期记录")
    @ExcelProperty("是否有逾期记录")
    private Boolean hasOverdueRecord;

    @Schema(description = "黑名单标志")
    @ExcelProperty("黑名单标志")
    private Boolean blacklistFlag;

    @Schema(description = "信誉状态(如:优秀、良好、一般、较差)")
    @ExcelProperty("信誉状态")
    private String reputationStatus;

    @Schema(description = "信誉级别")
    @ExcelProperty("信誉级别")
    private String reputationLevel;

    @Schema(description = "信誉综合评分")
    @ExcelProperty("信誉综合评分")
    private BigDecimal reputationScore;

    @Schema(description = "零售客户类型(如:正式客户、潜在客户等)")
    @ExcelProperty("零售客户类型")
    private String retailCustomerType;

    @Schema(description = "是否高端户")
    @ExcelProperty("是否高端户")
    private Boolean isHighEndCustomer;

    @Schema(description = "是否综合户")
    @ExcelProperty("是否综合户")
    private Boolean isComprehensiveCustomer;

    @Schema(description = "客户归属网格编号")
    @ExcelProperty("客户归属网格编号")
    private String customerGridCode;

    @Schema(description = "资质/风险(如:低、中、高)")
    @ExcelProperty("资质/风险")
    private String qualificationRisk;

    @Schema(description = "信誉风险等级(如:低、中、高)")
    @ExcelProperty("信誉风险等级")
    private String creditRiskLevel;

    @Schema(description = "信誉风险评级")
    @ExcelProperty("信誉风险评级")
    private String creditRiskRating;

    @Schema(description = "是否我行代发工资客户")
    @ExcelProperty("是否我行代发工资客户")
    private Boolean isPayrollCustomer;

    @Schema(description = "代发工资单位名称")
    @ExcelProperty("代发工资单位名称")
    private String payrollCompanyName;

    @Schema(description = "成为我行客户时间(日期)")
    @ExcelProperty("成为我行客户时间")
    private LocalDate becomeCustomerDate;

    @Schema(description = "在我行建立信任关系时间(日期)")
    @ExcelProperty("在我行建立信任关系时间")
    private LocalDate establishTrustDate;

    @Schema(description = "是否本行修改")
    @ExcelProperty("是否本行修改")
    private Boolean isBankModified;

    @Schema(description = "扩展字段1")
    @ExcelProperty("扩展字段1")
    private String extField1;

    @Schema(description = "扩展字段2")
    @ExcelProperty("扩展字段2")
    private String extField2;

    @Schema(description = "扩展字段3")
    @ExcelProperty("扩展字段3")
    private String extField3;

    // ========== 以下为 CustomerDO 公共字段 ==========

    @Schema(description = "客户编号(唯一标识)")
    @ExcelProperty("客户编号")
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "客户类型", converter = DictConvert.class)
    @DictFormat("crm_customer_type")
    private Integer customerType;

    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("客户名称")
    private String customerName;

    @Schema(description = "客户等级", example = "VIP")
    @ExcelProperty(value = "客户等级", converter = DictConvert.class)
    @DictFormat("crm_customer_level")
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "客户状态", converter = DictConvert.class)
    @DictFormat("crm_customer_status")
    private Integer customerStatus;

    @Schema(description = "是否优质客户")
    @ExcelProperty("是否优质客户")
    private Boolean isHighQuality;

    @Schema(description = "是否重要客户")
    @ExcelProperty("是否重要客户")
    private Boolean isImportant;

    @Schema(description = "信用状态(如:良好、一般、较差)")
    @ExcelProperty("信用状态")
    private String creditStatus;

    @Schema(description = "信用等级(如:AAA、AA、A、BBB等)")
    @ExcelProperty(value = "信用等级", converter = DictConvert.class)
    @DictFormat("crm_credit_level")
    private String creditLevel;

    @Schema(description = "信用评分")
    @ExcelProperty("信用评分")
    private BigDecimal creditScore;

    @Schema(description = "客户来源(如:网点开发、电话营销、网络营销等)")
    @ExcelProperty(value = "客户来源", converter = DictConvert.class)
    @DictFormat("crm_customer_source")
    private String customerSource;

    @Schema(description = "客户标签(多个标签用逗号分隔)")
    @ExcelProperty("客户标签")
    private String customerTag;

    @Schema(description = "备注信息")
    @ExcelProperty("备注信息")
    private String remark;

    @Schema(description = "所属部门ID(数据权限)")
    @ExcelProperty("所属部门ID")
    private Long deptId;

    // ==================== 以下为新增字段 ====================

    @Schema(description = "曾用名")
    @ExcelProperty("曾用名")
    private String usedName;

    @Schema(description = "姓氏")
    @ExcelProperty("姓氏")
    private String surname;

    @Schema(description = "拼音全名")
    @ExcelProperty("拼音全名")
    private String pinyinName;

    @Schema(description = "拼音缩写")
    @ExcelProperty("拼音缩写")
    private String pinyinAbbr;

    @Schema(description = "人员称谓(先生/女士/小姐等)")
    @ExcelProperty(value = "人员称谓", converter = DictConvert.class)
    @DictFormat("crm_person_title")
    private String personTitle;

    @Schema(description = "出生地")
    @ExcelProperty("出生地")
    private String birthLocale;

    @Schema(description = "年龄段")
    @ExcelProperty("年龄段")
    private String ageRange;

    @Schema(description = "公民身份")
    @ExcelProperty("公民身份")
    private String citizenship;

    @Schema(description = "居住时长类型")
    @ExcelProperty("居住时长类型")
    private String residenceDurationType;

    @Schema(description = "户口类型(农业/非农业)")
    @ExcelProperty(value = "户口类型", converter = DictConvert.class)
    @DictFormat("crm_household_type")
    private String householdType;

    @Schema(description = "单位性质(国企/私企/外企等)")
    @ExcelProperty(value = "单位性质", converter = DictConvert.class)
    @DictFormat("crm_employer_type")
    private String employerType;

    @Schema(description = "技术职称")
    @ExcelProperty("技术职称")
    private String technicalTitle;

    @Schema(description = "职业状态(在职/离职/退休等)")
    @ExcelProperty(value = "职业状态", converter = DictConvert.class)
    @DictFormat("crm_career_status")
    private String careerStatus;

    @Schema(description = "职业开始日期")
    @ExcelProperty("职业开始日期")
    private LocalDate careerStartDate;

    @Schema(description = "简历")
    @ExcelProperty("简历")
    private String resume;

    @Schema(description = "健康状况")
    @ExcelProperty(value = "健康状况", converter = DictConvert.class)
    @DictFormat("crm_health_status")
    private String healthStatus;

    @Schema(description = "政治面貌")
    @ExcelProperty(value = "政治面貌", converter = DictConvert.class)
    @DictFormat("crm_political_status")
    private String politicalStatus;

    @Schema(description = "毕业学校")
    @ExcelProperty("毕业学校")
    private String graduateSchool;

    @Schema(description = "专业")
    @ExcelProperty("专业")
    private String major;

    @Schema(description = "毕业日期")
    @ExcelProperty("毕业日期")
    private LocalDate graduationDate;

    @Schema(description = "年收入范围")
    @ExcelProperty("年收入范围")
    private String annualIncomeRange;

    @Schema(description = "纳税情况")
    @ExcelProperty("纳税情况")
    private String taxPayment;

    @Schema(description = "基金持有金额")
    @ExcelProperty("基金持有金额")
    private BigDecimal fundHoldings;

    @Schema(description = "总投资金额")
    @ExcelProperty("总投资金额")
    private BigDecimal totalInvestment;

    @Schema(description = "投资性质")
    @ExcelProperty(value = "投资性质", converter = DictConvert.class)
    @DictFormat("crm_investment_nature")
    private String investmentNature;

    @Schema(description = "持股金额")
    @ExcelProperty("持股金额")
    private BigDecimal stockHoldings;

    @Schema(description = "个人客户类型(老系统字段)")
    @ExcelProperty("个人客户类型")
    private String personCustomerType;

    @Schema(description = "个人行为评价")
    @ExcelProperty("个人行为评价")
    private String personConductEval;

    @Schema(description = "客户银行关系")
    @ExcelProperty("客户银行关系")
    private String customerBankRelation;

    @Schema(description = "公司关联度")
    @ExcelProperty("公司关联度")
    private String companyRelationDegree;

    @Schema(description = "个体类型")
    @ExcelProperty("个体类型")
    private String individualType;

    @Schema(description = "工资账户银行")
    @ExcelProperty("工资账户银行")
    private String salaryAccountBank;

    @Schema(description = "开始日期")
    @ExcelProperty("开始日期")
    private LocalDate startDate;

    @Schema(description = "登记类型")
    @ExcelProperty(value = "登记类型", converter = DictConvert.class)
    @DictFormat("crm_registration_type")
    private String registrationType;

    @Schema(description = "登记号")
    @ExcelProperty("登记号")
    private String registrationNo;

    @Schema(description = "登记开始日期")
    @ExcelProperty("登记开始日期")
    private LocalDate registrationStartDate;

    @Schema(description = "登记结束日期")
    @ExcelProperty("登记结束日期")
    private LocalDate registrationEndDate;

    @Schema(description = "ETL导入日期(数据迁移追溯)")
    @ExcelProperty("ETL导入日期")
    private LocalDate etlDate;

    @Schema(description = "老系统交易序列号(数据迁移追溯)")
    @ExcelProperty("老系统交易序列号")
    private String oldTxSeqNo;

    @Schema(description = "老系统客户ID(数据迁移追溯)")
    @ExcelProperty("老系统客户ID")
    private String oldCustId;

    @Schema(description = "老系统最后更新系统(数据迁移追溯)")
    @ExcelProperty("老系统最后更新系统")
    private String oldLastUpdateSys;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}