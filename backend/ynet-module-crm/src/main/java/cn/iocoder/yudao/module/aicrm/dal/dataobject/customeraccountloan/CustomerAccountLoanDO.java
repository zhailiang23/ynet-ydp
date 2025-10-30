package cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountloan;

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
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户贷款账户信息表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_account_loan")
@KeySequence("crm_customer_account_loan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountLoanDO extends BaseDO {

    /**
     * 贷款账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 贷款账号
     */
    private String accountNo;
    /**
     * 合同号
     */
    private String contractNo;
    /**
     * 协议号
     */
    private String agrNo;
    /**
     * 贷款产品名称
     */
    private String productName;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 借款人姓名
     */
    private String accountName;
    /**
     * 放款日期（开户日期）
     */
    private LocalDate openDate;
    /**
     * 结清日期（销户日期）
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）
     */
    private String accountStatus;
    /**
     * 合同金额（授信额度）
     */
    private BigDecimal contractAmount;
    /**
     * 贷款金额（实际发放金额）
     */
    private BigDecimal loanAmount;
    /**
     * 贷款余额
     */
    private BigDecimal balance;
    /**
     * 币种（字典: aicrm_currency_type）
     */
    private String currencyType;
    /**
     * 贷款利率（年化%）
     */
    private BigDecimal interestRate;
    /**
     * 贷款期限（月）
     */
    private BigDecimal loanTerm;
    /**
     * 期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）
     */
    private String loanTermUnit;
    /**
     * 到期日
     */
    private LocalDate matureDate;
    /**
     * 还款方式（字典: aicrm_repayment_mode）
     */
    private String repaymentMode;
    /**
     * 贷款用途
     */
    private String loanPurpose;
    /**
     * 贷款类型（字典: aicrm_loan_type）
     */
    private String loanType;
    /**
     * 担保方式（字典: aicrm_guarantee_type）
     */
    private String guaranteeType;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）
     */
    private String fiveLevelClass;
    /**
     * 逾期天数
     */
    private Integer overdueDays;
    /**
     * 逾期本金
     */
    private BigDecimal overduePrincipal;
    /**
     * 逾期利息
     */
    private BigDecimal overdueInterest;
    /**
     * 累计逾期次数
     */
    private Integer overdueTimes;
    /**
     * 放款机构ID（关联 system_dept.id）
     */
    private Long deptId;
    /**
     * 放款机构名称
     */
    private String deptName;
    /**
     * 客户经理用户ID（关联 system_users.id）
     */
    private Long managerUserId;
    /**
     * 月日均余额
     */
    private BigDecimal monthAvgBalance;
    /**
     * 季日均余额
     */
    private BigDecimal quarterAvgBalance;
    /**
     * 年日均余额
     */
    private BigDecimal yearAvgBalance;
    /**
     * 累计还款金额
     */
    private BigDecimal totalRepaidAmount;
    /**
     * 累计还款利息
     */
    private BigDecimal totalRepaidInterest;
    /**
     * 备注
     */
    private String remark;


}
