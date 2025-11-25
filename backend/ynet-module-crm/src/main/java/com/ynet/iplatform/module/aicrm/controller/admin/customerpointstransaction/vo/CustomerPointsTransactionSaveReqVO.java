package com.ynet.iplatform.module.aicrm.controller.admin.customerpointstransaction.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户积分消费明细表（积分交易流水表）新增/修改 Request VO")
@Data
public class CustomerPointsTransactionSaveReqVO {

    @Schema(description = "交易主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7792")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "16467")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "积分账户ID（关联 crm_customer_points 表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1188")
    @NotNull(message = "积分账户ID（关联 crm_customer_points 表）不能为空")
    private Long pointsAccountId;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订单编号不能为空")
    private String orderNo;

    @Schema(description = "交易日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "交易日期不能为空")
    private LocalDate transactionDate;

    @Schema(description = "兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）不能为空")
    private String exchangeChannel;

    @Schema(description = "礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）")
    private String giftExchangeMethod;

    @Schema(description = "订单消耗积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单消耗积分不能为空")
    private Long orderConsumedPoints;

    @Schema(description = "订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）不能为空")
    private String orderStatus;

    @Schema(description = "订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）不能为空")
    private String orderApprovalStatus;

    @Schema(description = "兑换用户")
    private String exchangeUser;

    @Schema(description = "交易流水号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "交易流水号不能为空")
    private String transactionNo;

    @Schema(description = "交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）不能为空")
    private String transactionType;

    @Schema(description = "交易时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "交易时间不能为空")
    private LocalDateTime transactionTime;

    @Schema(description = "积分变动（正数为增加，负数为减少）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "积分变动（正数为增加，负数为减少）不能为空")
    private Long pointsChange;

    @Schema(description = "交易前积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "交易前积分不能为空")
    private Long pointsBefore;

    @Schema(description = "交易后积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "交易后积分不能为空")
    private Long pointsAfter;

    @Schema(description = "交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）不能为空")
    private String transactionStatus;

    @Schema(description = "交易描述")
    private String transactionDesc;

    @Schema(description = "业务类型（字典: aicrm_points_business_type）", example = "1")
    private String businessType;

    @Schema(description = "业务单号")
    private String businessNo;

    @Schema(description = "礼品编码")
    private String giftCode;

    @Schema(description = "礼品名称", example = "王五")
    private String giftName;

    @Schema(description = "礼品数量")
    private Integer giftQuantity;

    @Schema(description = "礼品价值")
    private BigDecimal giftValue;

    @Schema(description = "配送地址")
    private String deliveryAddress;

    @Schema(description = "配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）", example = "1")
    private String deliveryStatus;

    @Schema(description = "配送时间")
    private LocalDateTime deliveryTime;

    @Schema(description = "收货人姓名", example = "王五")
    private String receiverName;

    @Schema(description = "收货人电话")
    private String receiverPhone;

    @Schema(description = "过期日期（对于有效期的积分）")
    private LocalDate expireDate;

    @Schema(description = "退款原因", example = "不香")
    private String refundReason;

    @Schema(description = "退款时间")
    private LocalDateTime refundTime;

    @Schema(description = "退款积分")
    private Long refundPoints;

    @Schema(description = "审批人ID", example = "942")
    private Long approvalUserId;

    @Schema(description = "审批人姓名", example = "李四")
    private String approvalUserName;

    @Schema(description = "审批时间")
    private LocalDateTime approvalTime;

    @Schema(description = "审批备注", example = "你说的对")
    private String approvalRemark;

    @Schema(description = "操作人ID", example = "4432")
    private Long operatorUserId;

    @Schema(description = "操作人姓名", example = "李四")
    private String operatorUserName;

    @Schema(description = "操作部门ID", example = "26332")
    private Long operatorDeptId;

    @Schema(description = "操作部门名称", example = "李四")
    private String operatorDeptName;

    @Schema(description = "是否已冲正", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否已冲正不能为空")
    private Boolean isReversed;

    @Schema(description = "冲正交易ID", example = "17174")
    private Long reverseTransactionId;

    @Schema(description = "冲正时间")
    private LocalDateTime reverseTime;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}