package cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccounttrust;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
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
 * 客户信托账户信息表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_account_trust")
@KeySequence("crm_customer_account_trust_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountTrustDO extends BaseDO {

    /**
     * 信托账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 信托账号
     */
    private String accountNo;
    /**
     * 信托合同号
     */
    private String trustContractNo;
    /**
     * 信托产品名称
     */
    private String productName;
    /**
     * 委托人姓名
     */
    private String accountName;
    /**
     * 成立日期（开户日期）
     */
    private LocalDate openDate;
    /**
     * 终止日期（销户日期）
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）
     */
    private String accountStatus;
    /**
     * 信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）
     */
    private String trustType;
    /**
     * 信托公司
     */
    private String trustCompany;
    /**
     * 信托目的
     */
    private String trustPurpose;
    /**
     * 预期收益率（年化%）
     */
    private BigDecimal expectedReturnRate;
    /**
     * 信托期限（如：2年、3年、5年）
     */
    private String trustTerm;
    /**
     * 币种
     */
    private String currencyType;
    /**
     * 信托金额
     */
    private BigDecimal trustAmount;
    /**
     * 已支付金额
     */
    private BigDecimal paidAmount;
    /**
     * 当前价值
     */
    private BigDecimal currentValue;
    /**
     * 累计收益
     */
    private BigDecimal accumulatedIncome;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 受益人姓名
     */
    private String beneficiaryName;
    /**
     * 与委托人关系
     */
    private String beneficiaryRelation;
    /**
     * 生效日期
     */
    private LocalDate effectiveDate;
    /**
     * 到期日
     */
    private LocalDate matureDate;
    /**
     * 下次分配日
     */
    private LocalDate nextDistributionDate;
    /**
     * 销售机构ID
     */
    private Long deptId;
    /**
     * 销售机构名称
     */
    private String deptName;
    /**
     * 信托顾问用户ID
     */
    private Long managerUserId;
    /**
     * 备注
     */
    private String remark;


}
