package cn.iocoder.yudao.module.aicrm.controller.admin.customercoupon.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户卡券信息表（零售客户特有）新增/修改 Request VO")
@Data
public class CustomerCouponSaveReqVO {

    @Schema(description = "卡券主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "26655")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表，仅限零售客户）", requiredMode = Schema.RequiredMode.REQUIRED, example = "10437")
    @NotNull(message = "客户ID（关联 crm_customer 主表，仅限零售客户）不能为空")
    private Long customerId;

    @Schema(description = "卡券编号（唯一）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "卡券编号（唯一）不能为空")
    private String couponNo;

    @Schema(description = "卡券代码（券模板编码）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "卡券代码（券模板编码）不能为空")
    private String couponCode;

    @Schema(description = "卡券名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "卡券名称不能为空")
    private String couponName;

    @Schema(description = "卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）不能为空")
    private String couponType;

    @Schema(description = "卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）不能为空")
    private String couponCategory;

    @Schema(description = "折扣率（如8.5表示8.5折，仅折扣券有效）")
    private BigDecimal discountRate;

    @Schema(description = "优惠金额（代金券金额或减免金额）")
    private BigDecimal discountAmount;

    @Schema(description = "礼品名称（仅礼品券有效）", example = "赵六")
    private String giftName;

    @Schema(description = "利率优惠（如0.5表示利率下调0.5%，仅利率券有效）", example = "20196")
    private BigDecimal rateDiscount;

    @Schema(description = "使用门槛金额（满多少可用，0表示无门槛）")
    private BigDecimal thresholdAmount;

    @Schema(description = "最高优惠金额（封顶金额）")
    private BigDecimal maxDiscountAmount;

    @Schema(description = "卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）不能为空")
    private String couponStatus;

    @Schema(description = "发放日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发放日期不能为空")
    private LocalDate issueDate;

    @Schema(description = "发放时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发放时间不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "生效日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效日期不能为空")
    private LocalDate effectiveDate;

    @Schema(description = "过期日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "过期日期不能为空")
    private LocalDate expiryDate;

    @Schema(description = "使用日期")
    private LocalDate useDate;

    @Schema(description = "使用时间")
    private LocalDateTime useTime;

    @Schema(description = "作废日期")
    private LocalDate cancelDate;

    @Schema(description = "作废原因", example = "不好")
    private String cancelReason;

    @Schema(description = "发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）不能为空")
    private String issueSource;

    @Schema(description = "发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）不能为空")
    private String issueChannel;

    @Schema(description = "关联活动ID（如果来源于营销活动）", example = "17381")
    private Long issueActivityId;

    @Schema(description = "关联活动名称", example = "张三")
    private String issueActivityName;

    @Schema(description = "使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）")
    private String useScenario;

    @Schema(description = "使用渠道（字典: aicrm_coupon_channel）")
    private String useChannel;

    @Schema(description = "使用产品代码")
    private String useProductCode;

    @Schema(description = "使用产品名称", example = "芋艿")
    private String useProductName;

    @Schema(description = "使用订单号")
    private String useOrderNo;

    @Schema(description = "交易金额（使用卡券时的交易金额）")
    private BigDecimal useTransactionAmount;

    @Schema(description = "实际优惠金额")
    private BigDecimal actualDiscountAmount;

    @Schema(description = "适用产品（JSON数组，如[\\\"PROD001\\\",\\\"PROD002\\\"]，null表示通用）")
    private String applicableProducts;

    @Schema(description = "适用渠道（JSON数组，null表示通用）")
    private String applicableChannels;

    @Schema(description = "可使用次数（1=单次使用，>1=可多次使用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "可使用次数（1=单次使用，>1=可多次使用）不能为空")
    private Integer useLimitTimes;

    @Schema(description = "已使用次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已使用次数不能为空")
    private Integer usedTimes;

    @Schema(description = "是否可转赠", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否可转赠不能为空")
    private Boolean isTransferable;

    @Schema(description = "是否可叠加使用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否可叠加使用不能为空")
    private Boolean isStackable;

    @Schema(description = "过期提醒天数（提前几天提醒）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "过期提醒天数（提前几天提醒）不能为空")
    private Integer expireRemindDays;

    @Schema(description = "是否已提醒", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否已提醒不能为空")
    private Boolean isReminded;

    @Schema(description = "提醒时间")
    private LocalDateTime remindTime;

    @Schema(description = "卡券描述（使用说明）", example = "随便")
    private String description;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}