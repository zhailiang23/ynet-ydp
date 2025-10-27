package cn.iocoder.yudao.module.crm.dal.dataobject.companycustomer;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM对公客户扩展表(对公客户特有基本信息) DO
 *
 * @author 芋道源码
 */
@TableName("crm_company_customer")
@KeySequence("crm_company_customer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCustomerDO extends BaseDO {

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
     * 证件类型(通常为营业执照)
     */
    private String licenseType;
    /**
     * 证件号码(营业执照号)
     */
    private String licenseNo;
    /**
     * 统一社会信用代码(18位)
     */
    private String creditCode;
    /**
     * 组织机构代码
     */
    private String organizationCode;
    /**
     * 纳税人识别号
     */
    private String taxNo;
    /**
     * 贷款卡号
     */
    private String loanCardNo;
    /**
     * 企业类型(如:有限责任公司、股份有限公司等)
     */
    private String enterpriseType;
    /**
     * 企业性质(如:国有企业、民营企业、外资企业等)
     */
    private String enterpriseNature;
    /**
     * 企业控股类型(如:国有控股、私人控股、外资控股等)
     */
    private String ownershipType;
    /**
     * 企业经济性质(如:其它企业、国有企业等)
     */
    private String economicType;
    /**
     * 企业规模(如:大型、中型、小型、微型)
     */
    private String enterpriseScale;
    /**
     * 注册资本(单位:万元)
     */
    private BigDecimal registeredCapital;
    /**
     * 注册资本币种(默认人民币)
     */
    private String registeredCapitalCurrency;
    /**
     * 企业成立日期
     */
    private LocalDate establishDate;
    /**
     * 营业期限
     */
    private String businessTerm;
    /**
     * 行业分类一级(如:批发业、制造业等)
     */
    private String industryCategoryL1;
    /**
     * 行业分类二级
     */
    private String industryCategoryL2;
    /**
     * 行业分类三级
     */
    private String industryCategoryL3;
    /**
     * 行业分类四级(如:金属及金属矿批发)
     */
    private String industryCategoryL4;
    /**
     * 行业代码(国标行业代码)
     */
    private String industryCode;
    /**
     * 是否上市公司(0=否, 1=是)
     */
    private Boolean isListed;
    /**
     * 是否小微企业(0=否, 1=是)
     */
    private Boolean isSmallEnterprise;
    /**
     * 是否集团客户(0=否, 1=是)
     */
    private Boolean isGroupCustomer;
    /**
     * 是否有进出口权(0=否, 1=是)
     */
    private Boolean isImportExport;
    /**
     * 是否我行关联方(0=否, 1=是)
     */
    private Boolean isRelatedParty;
    /**
     * 是否网银签约客户(0=否, 1=是)
     */
    private Boolean isEbankSigned;
    /**
     * 是否涉农企业(0=否, 1=是)
     */
    private Boolean isAgricultureRelated;
    /**
     * 基本账户开户行
     */
    private String basicAccountBank;
    /**
     * 基本账户账号
     */
    private String basicAccountNo;
    /**
     * 法定代表人/负责人姓名
     */
    private String legalPersonName;
    /**
     * 法定代表人证件类型
     */
    private String legalPersonIdType;
    /**
     * 法定代表人证件号码(加密存储)
     */
    private String legalPersonIdNo;
    /**
     * 法定代表人联系电话(加密存储)
     */
    private String legalPersonPhone;
    /**
     * 企业资质(多个资质用逗号分隔)
     */
    private String enterpriseQualification;
    /**
     * 管理部门
     */
    private String managementDept;
    /**
     * 监管部门
     */
    private String superviseDept;
    /**
     * 企业评级(如:AAA、AA等)
     */
    private String companyRating;
    /**
     * 评级机构
     */
    private String ratingAgency;
    /**
     * 评级日期
     */
    private LocalDate ratingDate;
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


}