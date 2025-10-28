package cn.iocoder.yudao.module.crm.controller.admin.companycustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

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