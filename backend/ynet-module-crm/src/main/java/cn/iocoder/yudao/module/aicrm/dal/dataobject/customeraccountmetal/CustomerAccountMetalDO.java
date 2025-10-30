package cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountmetal;

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
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户贵金属账户信息表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_account_metal")
@KeySequence("crm_customer_account_metal_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountMetalDO extends BaseDO {

    /**
     * 贵金属账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 贵金属账号
     */
    private String accountNo;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 户名
     */
    private String accountName;
    /**
     * 开户日期
     */
    private LocalDate openDate;
    /**
     * 销户日期
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）
     */
    private String accountStatus;
    /**
     * 贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）
     */
    private String metalType;
    /**
     * 产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）
     */
    private String metalCategory;
    /**
     * 产品代码
     */
    private String productCode;
    /**
     * 持有重量（克）
     */
    private BigDecimal holdingWeight;
    /**
     * 平均成本（元/克）
     */
    private BigDecimal averageCost;
    /**
     * 当前价格（元/克）
     */
    private BigDecimal currentPrice;
    /**
     * 当前市值（元）
     */
    private BigDecimal currentValue;
    /**
     * 累计收益（元）
     */
    private BigDecimal accumulatedIncome;
    /**
     * 收益率（%）
     */
    private BigDecimal profitRate;
    /**
     * 账户余额（元）
     */
    private BigDecimal balance;
    /**
     * 累计买入重量（克）
     */
    private BigDecimal totalBuyWeight;
    /**
     * 累计买入金额（元）
     */
    private BigDecimal totalBuyAmount;
    /**
     * 累计卖出重量（克）
     */
    private BigDecimal totalSellWeight;
    /**
     * 累计卖出金额（元）
     */
    private BigDecimal totalSellAmount;
    /**
     * 币种
     */
    private String currencyType;
    /**
     * 开户机构ID
     */
    private Long deptId;
    /**
     * 开户机构名称
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