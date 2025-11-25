package com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountcreditcard;

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
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户信用卡账户信息表（仅限零售客户） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_account_creditcard")
@KeySequence("crm_customer_account_creditcard_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountCreditcardDO extends BaseDO {

    /**
     * 信用卡账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表，仅限零售客户）
     */
    private Long customerId;
    /**
     * 信用卡账号
     */
    private String accountNo;
    /**
     * 信用卡号（加密存储）
     */
    private String cardNo;
    /**
     * 信用卡产品名称
     */
    private String productName;
    /**
     * 持卡人姓名
     */
    private String accountName;
    /**
     * 开卡日期
     */
    private LocalDate openDate;
    /**
     * 销卡日期
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）
     */
    private String accountStatus;
    /**
     * 卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）
     */
    private String cardType;
    /**
     * 卡等级
     */
    private String cardLevel;
    /**
     * 卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）
     */
    private String cardBrand;
    /**
     * 是否主卡（0=附属卡，1=主卡）
     */
    private Boolean isMainCard;
    /**
     * 主卡卡号（附属卡关联）
     */
    private String mainCardNo;
    /**
     * 币种
     */
    private String currencyType;
    /**
     * 信用额度
     */
    private BigDecimal creditLimit;
    /**
     * 可用额度
     */
    private BigDecimal availableLimit;
    /**
     * 临时额度
     */
    private BigDecimal temporaryLimit;
    /**
     * 取现额度
     */
    private BigDecimal cashLimit;
    /**
     * 已用额度
     */
    private BigDecimal usedAmount;
    /**
     * 当前欠款余额
     */
    private BigDecimal balance;
    /**
     * 账单日（每月几号）
     */
    private Integer billingDay;
    /**
     * 还款日（每月几号）
     */
    private Integer paymentDueDay;
    /**
     * 本期账单金额
     */
    private BigDecimal currentBillAmount;
    /**
     * 最低还款额
     */
    private BigDecimal minPaymentAmount;
    /**
     * 未还金额
     */
    private BigDecimal unpaidAmount;
    /**
     * 上次还款日期
     */
    private LocalDate lastPaymentDate;
    /**
     * 上次还款金额
     */
    private BigDecimal lastPaymentAmount;
    /**
     * 逾期天数
     */
    private Integer overdueDays;
    /**
     * 逾期金额
     */
    private BigDecimal overdueAmount;
    /**
     * 逾期利息
     */
    private BigDecimal overdueInterest;
    /**
     * 累计逾期次数
     */
    private Integer overdueTimes;
    /**
     * 累计积分
     */
    private Long totalPoints;
    /**
     * 可用积分
     */
    private Long availablePoints;
    /**
     * 积分到期日
     */
    private LocalDate pointsExpireDate;
    /**
     * 卡片有效期
     */
    private LocalDate expireDate;
    /**
     * 激活日期
     */
    private LocalDate activateDate;
    /**
     * 最后交易日期
     */
    private LocalDate lastTransactionDate;
    /**
     * 发卡机构ID
     */
    private Long deptId;
    /**
     * 发卡机构名称
     */
    private String deptName;
    /**
     * 客户经理用户ID
     */
    private Long managerUserId;
    /**
     * 备注
     */
    private String remark;


}
