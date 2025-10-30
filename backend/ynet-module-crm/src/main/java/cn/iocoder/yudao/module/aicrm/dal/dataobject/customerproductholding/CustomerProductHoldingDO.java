package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerproductholding;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户产品持有情况表（客户与产品的多对多关系） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_product_holding")
@KeySequence("crm_customer_product_holding_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProductHoldingDO extends BaseDO {

    /**
     * 产品持有主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 产品ID（关联 crm_product_category.id，必须是叶子节点）
     */
    private Long productId;
    /**
     * 贷款账号/账号
     */
    private String accountNo;
    /**
     * 借据编号
     */
    private String receiptNo;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 币种代码
     */
    private String currencyCode;
    /**
     * 放款日期（贷款产品）
     */
    private LocalDate loanDate;
    /**
     * 开户日期（存款、理财等产品）
     */
    private LocalDate openDate;
    /**
     * 到期日期
     */
    private LocalDate matureDate;
    /**
     * 合同日期
     */
    private LocalDate contractDate;
    /**
     * 开户网点名称
     */
    private String branchName;
    /**
     * 开户网点ID（关联 system_dept.id）
     */
    private Long branchId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 持有金额/余额
     */
    private BigDecimal holdingAmount;
    /**
     * 原始金额（初始投资/贷款金额）
     */
    private BigDecimal originalAmount;
    /**
     * 利率/收益率（%）
     */
    private BigDecimal interestRate;
    /**
     * 持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）
     */
    private String holdingStatus;
    /**
     * 关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）
     */
    private String relatedAccountType;
    /**
     * 关联账户ID（关联对应的账户表主键）
     */
    private Long relatedAccountId;
    /**
     * 备注
     */
    private String remark;


}