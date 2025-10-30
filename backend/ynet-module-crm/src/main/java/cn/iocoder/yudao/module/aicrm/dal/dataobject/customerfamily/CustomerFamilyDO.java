package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerfamily;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户家庭信息表（零售客户） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_family")
@KeySequence("crm_customer_family_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFamilyDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联零售客户，唯一）
     */
    private Long customerId;
    /**
     * 家庭人口数
     */
    private Integer familyMemberCount;
    /**
     * 供养人口数
     */
    private Integer supportMemberCount;
    /**
     * 劳动力人口数
     */
    private Integer laborMemberCount;
    /**
     * 子女数量
     */
    private Integer childrenCount;
    /**
     * 家庭年收入（万元）
     */
    private BigDecimal familyAnnualIncome;
    /**
     * 家庭年收入范围（字典：aicrm_family_income_scope）
     */
    private String familyAnnualIncomeScope;
    /**
     * 家庭年支出（万元）
     */
    private BigDecimal familyAnnualExpenditure;
    /**
     * 家庭年支出范围（字典：aicrm_family_expenditure_scope）
     */
    private String familyAnnualExpenditureScope;
    /**
     * 家庭负债（万元）
     */
    private BigDecimal familyDebt;
    /**
     * 家庭总资产（万元）
     */
    private BigDecimal familyTotalAssets;
    /**
     * 家庭资产信息
     */
    private String familyAssetsInfo;
    /**
     * 主要收入来源（字典：aicrm_income_source）
     */
    private String mainIncomeSource;
    /**
     * 居住状况（字典：aicrm_residence_status）
     */
    private String residenceStatus;
    /**
     * 住房状况（字典：aicrm_house_status）
     */
    private String houseStatus;
    /**
     * 是否有房有车（0-否，1-是）
     */
    private Boolean hasHomeCar;
    /**
     * 是否户主（0-否，1-是）
     */
    private Boolean isHouseHolder;
    /**
     * 户主姓名
     */
    private String houseHolderName;
    /**
     * 所居住宅多(值)，即居住地点描述
     */
    private String residenceLocation;
    /**
     * 家庭地址
     */
    private String familyAddress;
    /**
     * 家庭电话
     */
    private String homeTel;
    /**
     * 家庭是否和睦（0-否，1-是）
     */
    private Boolean isHarmony;
    /**
     * 是否信用家庭（0-否，1-是）
     */
    private Boolean isCreditFamily;
    /**
     * 信用状况（字典：aicrm_credit_status）
     */
    private String creditStatus;
    /**
     * 授信额度
     */
    private BigDecimal creditAmount;
    /**
     * 家庭负债范围（字典：aicrm_debt_scope）
     */
    private String familyDebtScope;
    /**
     * 负债状况（字典：aicrm_debt_status）
     */
    private String debtStatus;
    /**
     * 家庭不良记录
     */
    private String familyAdverseRecords;
    /**
     * 经营及规模
     */
    private String businessAndScale;
    /**
     * 家庭实力（字典：aicrm_family_strength）
     */
    private String familyStrength;
    /**
     * 备注
     */
    private String remark;


}
