package cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountwealth;

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
 * 客户理财账户信息表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_account_wealth")
@KeySequence("crm_customer_account_wealth_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountWealthDO extends BaseDO {

    /**
     * 理财账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 理财账号
     */
    private String accountNo;
    /**
     * 理财产品代码
     */
    private String productCode;
    /**
     * 理财产品名称
     */
    private String productName;
    /**
     * 户名
     */
    private String accountName;
    /**
     * 开户日期（购买日期）
     */
    private LocalDate openDate;
    /**
     * 销户日期（赎回/到期日期）
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）
     */
    private String accountStatus;
    /**
     * 理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）
     */
    private String productType;
    /**
     * 风险等级（字典: aicrm_risk_level，R1-R5）
     */
    private String riskLevel;
    /**
     * 预期收益率（年化%）
     */
    private BigDecimal expectedReturnRate;
    /**
     * 实际收益率（年化%）
     */
    private BigDecimal actualReturnRate;
    /**
     * 理财期限（如：90天、180天、1年）
     */
    private String wealthTerm;
    /**
     * 币种
     */
    private String currencyType;
    /**
     * 购买金额
     */
    private BigDecimal purchaseAmount;
    /**
     * 当前市值
     */
    private BigDecimal currentValue;
    /**
     * 累计收益
     */
    private BigDecimal accumulatedIncome;
    /**
     * 持有份额/余额
     */
    private BigDecimal balance;
    /**
     * 起息日
     */
    private LocalDate valueDate;
    /**
     * 到期日
     */
    private LocalDate matureDate;
    /**
     * 下次开放日（开放式理财）
     */
    private LocalDate nextOpenDate;
    /**
     * 销售机构ID
     */
    private Long deptId;
    /**
     * 销售机构名称
     */
    private String deptName;
    /**
     * 理财顾问用户ID
     */
    private Long managerUserId;
    /**
     * 备注
     */
    private String remark;


}
