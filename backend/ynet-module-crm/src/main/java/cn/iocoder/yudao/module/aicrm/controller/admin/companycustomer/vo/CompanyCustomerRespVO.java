package cn.iocoder.yudao.module.aicrm.controller.admin.companycustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - CRM对公客户扩展表(对公客户特有基本信息) Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompanyCustomerRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29689")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID(外键,关联crm_customer.id,唯一)", requiredMode = Schema.RequiredMode.REQUIRED, example = "26736")
    @ExcelProperty("客户ID(外键,关联crm_customer.id,唯一)")
    private Long customerId;

    @Schema(description = "证件类型(通常为营业执照)", example = "2")
    @ExcelProperty("证件类型(通常为营业执照)")
    private String licenseType;

    @Schema(description = "证件号码(营业执照号)")
    @ExcelProperty("证件号码(营业执照号)")
    private String licenseNo;

    @Schema(description = "统一社会信用代码(18位)")
    @ExcelProperty("统一社会信用代码(18位)")
    private String creditCode;

    @Schema(description = "组织机构代码")
    @ExcelProperty("组织机构代码")
    private String organizationCode;

    @Schema(description = "纳税人识别号")
    @ExcelProperty("纳税人识别号")
    private String taxNo;

    @Schema(description = "贷款卡号")
    @ExcelProperty("贷款卡号")
    private String loanCardNo;

    @Schema(description = "企业类型(如:有限责任公司、股份有限公司等)", example = "2")
    @ExcelProperty("企业类型(如:有限责任公司、股份有限公司等)")
    private String enterpriseType;

    @Schema(description = "企业性质(如:国有企业、民营企业、外资企业等)")
    @ExcelProperty("企业性质(如:国有企业、民营企业、外资企业等)")
    private String enterpriseNature;

    @Schema(description = "企业控股类型(如:国有控股、私人控股、外资控股等)", example = "2")
    @ExcelProperty("企业控股类型(如:国有控股、私人控股、外资控股等)")
    private String ownershipType;

    @Schema(description = "企业经济性质(如:其它企业、国有企业等)", example = "1")
    @ExcelProperty("企业经济性质(如:其它企业、国有企业等)")
    private String economicType;

    @Schema(description = "企业规模(如:大型、中型、小型、微型)")
    @ExcelProperty("企业规模(如:大型、中型、小型、微型)")
    private String enterpriseScale;

    @Schema(description = "注册资本(单位:万元)")
    @ExcelProperty("注册资本(单位:万元)")
    private BigDecimal registeredCapital;

    @Schema(description = "注册资本币种(默认人民币)")
    @ExcelProperty("注册资本币种(默认人民币)")
    private String registeredCapitalCurrency;

    @Schema(description = "企业成立日期")
    @ExcelProperty("企业成立日期")
    private LocalDate establishDate;

    @Schema(description = "营业期限")
    @ExcelProperty("营业期限")
    private String businessTerm;

    @Schema(description = "行业分类一级(如:批发业、制造业等)")
    @ExcelProperty("行业分类一级(如:批发业、制造业等)")
    private String industryCategoryL1;

    @Schema(description = "行业分类二级")
    @ExcelProperty("行业分类二级")
    private String industryCategoryL2;

    @Schema(description = "行业分类三级")
    @ExcelProperty("行业分类三级")
    private String industryCategoryL3;

    @Schema(description = "行业分类四级(如:金属及金属矿批发)")
    @ExcelProperty("行业分类四级(如:金属及金属矿批发)")
    private String industryCategoryL4;

    @Schema(description = "行业代码(国标行业代码)")
    @ExcelProperty("行业代码(国标行业代码)")
    private String industryCode;

    @Schema(description = "是否上市公司(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否上市公司(0=否, 1=是)")
    private Boolean isListed;

    @Schema(description = "是否小微企业(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否小微企业(0=否, 1=是)")
    private Boolean isSmallEnterprise;

    @Schema(description = "是否集团客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否集团客户(0=否, 1=是)")
    private Boolean isGroupCustomer;

    @Schema(description = "是否有进出口权(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否有进出口权(0=否, 1=是)")
    private Boolean isImportExport;

    @Schema(description = "是否我行关联方(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否我行关联方(0=否, 1=是)")
    private Boolean isRelatedParty;

    @Schema(description = "是否网银签约客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否网银签约客户(0=否, 1=是)")
    private Boolean isEbankSigned;

    @Schema(description = "是否涉农企业(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否涉农企业(0=否, 1=是)")
    private Boolean isAgricultureRelated;

    @Schema(description = "基本账户开户行")
    @ExcelProperty("基本账户开户行")
    private String basicAccountBank;

    @Schema(description = "基本账户账号")
    @ExcelProperty("基本账户账号")
    private String basicAccountNo;

    @Schema(description = "法定代表人/负责人姓名", example = "芋艿")
    @ExcelProperty("法定代表人/负责人姓名")
    private String legalPersonName;

    @Schema(description = "法定代表人证件类型", example = "2")
    @ExcelProperty("法定代表人证件类型")
    private String legalPersonIdType;

    @Schema(description = "法定代表人证件号码(加密存储)")
    @ExcelProperty("法定代表人证件号码(加密存储)")
    private String legalPersonIdNo;

    @Schema(description = "法定代表人联系电话(加密存储)")
    @ExcelProperty("法定代表人联系电话(加密存储)")
    private String legalPersonPhone;

    @Schema(description = "企业资质(多个资质用逗号分隔)")
    @ExcelProperty("企业资质(多个资质用逗号分隔)")
    private String enterpriseQualification;

    @Schema(description = "管理部门")
    @ExcelProperty("管理部门")
    private String managementDept;

    @Schema(description = "监管部门")
    @ExcelProperty("监管部门")
    private String superviseDept;

    @Schema(description = "企业评级(如:AAA、AA等)")
    @ExcelProperty("企业评级(如:AAA、AA等)")
    private String companyRating;

    @Schema(description = "评级机构")
    @ExcelProperty("评级机构")
    private String ratingAgency;

    @Schema(description = "评级日期")
    @ExcelProperty("评级日期")
    private LocalDate ratingDate;

    @Schema(description = "扩展字段1")
    @ExcelProperty("扩展字段1")
    private String extField1;

    @Schema(description = "扩展字段2")
    @ExcelProperty("扩展字段2")
    private String extField2;

    @Schema(description = "扩展字段3")
    @ExcelProperty("扩展字段3")
    private String extField3;

    // ========== 财务信息字段 (5个) ==========

    @Schema(description = "资产总额")
    @ExcelProperty("资产总额")
    private BigDecimal totalAssets;

    @Schema(description = "负债总额")
    @ExcelProperty("负债总额")
    private BigDecimal totalDebt;

    @Schema(description = "年收入")
    @ExcelProperty("年收入")
    private BigDecimal annualIncome;

    @Schema(description = "年利润")
    @ExcelProperty("年利润")
    private BigDecimal annualProfit;

    @Schema(description = "财务报表类型")
    @ExcelProperty(value = "财务报表类型", converter = DictConvert.class)
    @DictFormat("crm_fin_report_type")
    private String finReportType;

    // ========== 股权投资字段 (3个) ==========

    @Schema(description = "是否股东(0=否, 1=是)")
    @ExcelProperty("是否股东")
    private Boolean isStockHolder;

    @Schema(description = "持股金额")
    @ExcelProperty("持股金额")
    private BigDecimal holdStockAmt;

    @Schema(description = "投资类型")
    @ExcelProperty(value = "投资类型", converter = DictConvert.class)
    @DictFormat("crm_investment_type")
    private String investmentType;

    // ========== 企业组织字段 (7个) ==========

    @Schema(description = "组织形式")
    @ExcelProperty(value = "组织形式", converter = DictConvert.class)
    @DictFormat("crm_org_form")
    private String orgForm;

    @Schema(description = "治理结构")
    @ExcelProperty(value = "治理结构", converter = DictConvert.class)
    @DictFormat("crm_governance_structure")
    private String governanceStructure;

    @Schema(description = "控股类型")
    @ExcelProperty(value = "控股类型", converter = DictConvert.class)
    @DictFormat("crm_holding_type")
    private String holdingType;

    @Schema(description = "企业归属")
    @ExcelProperty("企业归属")
    private String enterpriseBelong;

    @Schema(description = "上级部门")
    @ExcelProperty("上级部门")
    private String superiorDept;

    @Schema(description = "公司机构")
    @ExcelProperty("公司机构")
    private String companyOrganization;

    @Schema(description = "国家代码")
    @ExcelProperty("国家代码")
    private String nationCode;

    // ========== 业务经营字段 (8个) ==========

    @Schema(description = "主营业务")
    @ExcelProperty("主营业务")
    private String mainBusiness;

    @Schema(description = "辅营业务")
    @ExcelProperty("辅营业务")
    private String minorBusiness;

    @Schema(description = "经营模式")
    @ExcelProperty(value = "经营模式", converter = DictConvert.class)
    @DictFormat("crm_business_mode")
    private String businessMode;

    @Schema(description = "营业开始日期")
    @ExcelProperty("营业开始日期")
    private LocalDate businessStartDate;

    @Schema(description = "行业特征")
    @ExcelProperty(value = "行业特征", converter = DictConvert.class)
    @DictFormat("crm_industry_char")
    private String industryCharacter;

    @Schema(description = "行业发展前景")
    @ExcelProperty(value = "行业发展前景", converter = DictConvert.class)
    @DictFormat("crm_industry_prospect")
    private String industryDevelopmentProspect;

    @Schema(description = "地区代码")
    @ExcelProperty("地区代码")
    private String areaCode;

    @Schema(description = "行业地位")
    @ExcelProperty("行业地位")
    private String industryPosition;

    // ========== 企业规模字段 (4个) ==========

    @Schema(description = "员工规模")
    @ExcelProperty(value = "员工规模", converter = DictConvert.class)
    @DictFormat("crm_employee_scale")
    private String employeeScale;

    @Schema(description = "资产规模")
    @ExcelProperty("资产规模")
    private String assetsScale;

    @Schema(description = "生产能力")
    @ExcelProperty(value = "生产能力", converter = DictConvert.class)
    @DictFormat("crm_production_capacity")
    private String productionCapacity;

    @Schema(description = "企业性质")
    @ExcelProperty("企业性质")
    private String enterpriseProperty;

    // ========== 监管评级字段 (3个) ==========

    @Schema(description = "贷款卡标志(0=否, 1=是)")
    @ExcelProperty("贷款卡标志")
    private Boolean loanCardFlag;

    @Schema(description = "贷款卡状态")
    @ExcelProperty(value = "贷款卡状态", converter = DictConvert.class)
    @DictFormat("crm_loan_card_status")
    private String loanCardStatus;

    @Schema(description = "贷款卡审核日期")
    @ExcelProperty("贷款卡审核日期")
    private LocalDate loanCardAuditDate;

    // ========== 其他特殊字段 (2个) ==========

    @Schema(description = "人行企业规模")
    @ExcelProperty("人行企业规模")
    private String enterpriseScalePboc;

    @Schema(description = "存款企业规模")
    @ExcelProperty("存款企业规模")
    private String enterpriseScaleDeposit;

    // ========== 系统追溯字段 (4个) ==========

    @Schema(description = "ETL导入日期")
    @ExcelProperty("ETL导入日期")
    private LocalDate etlDate;

    @Schema(description = "老系统交易序列号")
    @ExcelProperty("老系统交易序列号")
    private String oldTxSeqNo;

    @Schema(description = "老系统最后更新系统")
    @ExcelProperty("老系统最后更新系统")
    private String oldLastUpdateSys;

    @Schema(description = "老系统客户ID")
    @ExcelProperty("老系统客户ID")
    private String oldCustId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    // ========== 以下为 CustomerDO 公共字段 ==========

    @Schema(description = "客户编号(唯一标识)")
    @ExcelProperty("客户编号")
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客户类型")
    private Integer customerType;

    @Schema(description = "客户名称(企业名称)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道科技有限公司")
    @ExcelProperty("客户名称")
    private String customerName;

    @Schema(description = "客户等级", example = "战略客户")
    @ExcelProperty("客户等级")
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客户状态")
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
    @ExcelProperty("信用等级")
    private String creditLevel;

    @Schema(description = "信用评分")
    @ExcelProperty("信用评分")
    private BigDecimal creditScore;

    @Schema(description = "客户来源(如:网点开发、电话营销、网络营销等)")
    @ExcelProperty("客户来源")
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

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}