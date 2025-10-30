package cn.iocoder.yudao.module.aicrm.dal.dataobject.customercoupon;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户卡券信息表（零售客户特有） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_coupon")
@KeySequence("crm_customer_coupon_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCouponDO extends BaseDO {

    /**
     * 卡券主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表，仅限零售客户）
     */
    private Long customerId;
    /**
     * 卡券编号（唯一）
     */
    private String couponNo;
    /**
     * 卡券代码（券模板编码）
     */
    private String couponCode;
    /**
     * 卡券名称
     */
    private String couponName;
    /**
     * 卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）
     */
    private String couponType;
    /**
     * 卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）
     */
    private String couponCategory;
    /**
     * 折扣率（如8.5表示8.5折，仅折扣券有效）
     */
    private BigDecimal discountRate;
    /**
     * 优惠金额（代金券金额或减免金额）
     */
    private BigDecimal discountAmount;
    /**
     * 礼品名称（仅礼品券有效）
     */
    private String giftName;
    /**
     * 利率优惠（如0.5表示利率下调0.5%，仅利率券有效）
     */
    private BigDecimal rateDiscount;
    /**
     * 使用门槛金额（满多少可用，0表示无门槛）
     */
    private BigDecimal thresholdAmount;
    /**
     * 最高优惠金额（封顶金额）
     */
    private BigDecimal maxDiscountAmount;
    /**
     * 卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）
     */
    private String couponStatus;
    /**
     * 发放日期
     */
    private LocalDate issueDate;
    /**
     * 发放时间
     */
    private LocalDateTime issueTime;
    /**
     * 生效日期
     */
    private LocalDate effectiveDate;
    /**
     * 过期日期
     */
    private LocalDate expiryDate;
    /**
     * 使用日期
     */
    private LocalDate useDate;
    /**
     * 使用时间
     */
    private LocalDateTime useTime;
    /**
     * 作废日期
     */
    private LocalDate cancelDate;
    /**
     * 作废原因
     */
    private String cancelReason;
    /**
     * 发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）
     */
    private String issueSource;
    /**
     * 发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）
     */
    private String issueChannel;
    /**
     * 关联活动ID（如果来源于营销活动）
     */
    private Long issueActivityId;
    /**
     * 关联活动名称
     */
    private String issueActivityName;
    /**
     * 使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）
     */
    private String useScenario;
    /**
     * 使用渠道（字典: aicrm_coupon_channel）
     */
    private String useChannel;
    /**
     * 使用产品代码
     */
    private String useProductCode;
    /**
     * 使用产品名称
     */
    private String useProductName;
    /**
     * 使用订单号
     */
    private String useOrderNo;
    /**
     * 交易金额（使用卡券时的交易金额）
     */
    private BigDecimal useTransactionAmount;
    /**
     * 实际优惠金额
     */
    private BigDecimal actualDiscountAmount;
    /**
     * 适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）
     */
    private String applicableProducts;
    /**
     * 适用渠道（JSON数组，null表示通用）
     */
    private String applicableChannels;
    /**
     * 可使用次数（1=单次使用，>1=可多次使用）
     */
    private Integer useLimitTimes;
    /**
     * 已使用次数
     */
    private Integer usedTimes;
    /**
     * 是否可转赠
     */
    private Boolean isTransferable;
    /**
     * 是否可叠加使用
     */
    private Boolean isStackable;
    /**
     * 过期提醒天数（提前几天提醒）
     */
    private Integer expireRemindDays;
    /**
     * 是否已提醒
     */
    private Boolean isReminded;
    /**
     * 提醒时间
     */
    private LocalDateTime remindTime;
    /**
     * 卡券描述（使用说明）
     */
    private String description;
    /**
     * 备注
     */
    private String remark;


}