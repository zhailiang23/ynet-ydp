package com.ynet.iplatform.module.aicrm.controller.admin.customerpointstransaction.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户积分消费明细表（积分交易流水表） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerPointsTransactionRespVO {

    @Schema(description = "交易主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7792")
    @ExcelProperty("交易主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "16467")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "积分账户ID（关联 crm_customer_points 表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1188")
    @ExcelProperty("积分账户ID（关联 crm_customer_points 表）")
    private Long pointsAccountId;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单编号")
    private String orderNo;

    @Schema(description = "交易日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("交易日期")
    private LocalDate transactionDate;

    @Schema(description = "兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）")
    private String exchangeChannel;

    @Schema(description = "礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）")
    @ExcelProperty("礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）")
    private String giftExchangeMethod;

    @Schema(description = "订单消耗积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单消耗积分")
    private Long orderConsumedPoints;

    @Schema(description = "订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）")
    private String orderStatus;

    @Schema(description = "订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）")
    private String orderApprovalStatus;

    @Schema(description = "兑换用户")
    @ExcelProperty("兑换用户")
    private String exchangeUser;

    @Schema(description = "交易流水号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("交易流水号")
    private String transactionNo;

    @Schema(description = "交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）")
    private String transactionType;

    @Schema(description = "交易时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("交易时间")
    private LocalDateTime transactionTime;

    @Schema(description = "积分变动（正数为增加，负数为减少）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("积分变动（正数为增加，负数为减少）")
    private Long pointsChange;

    @Schema(description = "交易前积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("交易前积分")
    private Long pointsBefore;

    @Schema(description = "交易后积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("交易后积分")
    private Long pointsAfter;

    @Schema(description = "交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）")
    private String transactionStatus;

    @Schema(description = "交易描述")
    @ExcelProperty("交易描述")
    private String transactionDesc;

    @Schema(description = "业务类型（字典: aicrm_points_business_type）", example = "1")
    @ExcelProperty("业务类型（字典: aicrm_points_business_type）")
    private String businessType;

    @Schema(description = "业务单号")
    @ExcelProperty("业务单号")
    private String businessNo;

    @Schema(description = "礼品编码")
    @ExcelProperty("礼品编码")
    private String giftCode;

    @Schema(description = "礼品名称", example = "王五")
    @ExcelProperty("礼品名称")
    private String giftName;

    @Schema(description = "礼品数量")
    @ExcelProperty("礼品数量")
    private Integer giftQuantity;

    @Schema(description = "礼品价值")
    @ExcelProperty("礼品价值")
    private BigDecimal giftValue;

    @Schema(description = "配送地址")
    @ExcelProperty("配送地址")
    private String deliveryAddress;

    @Schema(description = "配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）", example = "1")
    @ExcelProperty("配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）")
    private String deliveryStatus;

    @Schema(description = "配送时间")
    @ExcelProperty("配送时间")
    private LocalDateTime deliveryTime;

    @Schema(description = "收货人姓名", example = "王五")
    @ExcelProperty("收货人姓名")
    private String receiverName;

    @Schema(description = "收货人电话")
    @ExcelProperty("收货人电话")
    private String receiverPhone;

    @Schema(description = "过期日期（对于有效期的积分）")
    @ExcelProperty("过期日期（对于有效期的积分）")
    private LocalDate expireDate;

    @Schema(description = "退款原因", example = "不香")
    @ExcelProperty("退款原因")
    private String refundReason;

    @Schema(description = "退款时间")
    @ExcelProperty("退款时间")
    private LocalDateTime refundTime;

    @Schema(description = "退款积分")
    @ExcelProperty("退款积分")
    private Long refundPoints;

    @Schema(description = "审批人ID", example = "942")
    @ExcelProperty("审批人ID")
    private Long approvalUserId;

    @Schema(description = "审批人姓名", example = "李四")
    @ExcelProperty("审批人姓名")
    private String approvalUserName;

    @Schema(description = "审批时间")
    @ExcelProperty("审批时间")
    private LocalDateTime approvalTime;

    @Schema(description = "审批备注", example = "你说的对")
    @ExcelProperty("审批备注")
    private String approvalRemark;

    @Schema(description = "操作人ID", example = "4432")
    @ExcelProperty("操作人ID")
    private Long operatorUserId;

    @Schema(description = "操作人姓名", example = "李四")
    @ExcelProperty("操作人姓名")
    private String operatorUserName;

    @Schema(description = "操作部门ID", example = "26332")
    @ExcelProperty("操作部门ID")
    private Long operatorDeptId;

    @Schema(description = "操作部门名称", example = "李四")
    @ExcelProperty("操作部门名称")
    private String operatorDeptName;

    @Schema(description = "是否已冲正", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否已冲正")
    private Boolean isReversed;

    @Schema(description = "冲正交易ID", example = "17174")
    @ExcelProperty("冲正交易ID")
    private Long reverseTransactionId;

    @Schema(description = "冲正时间")
    @ExcelProperty("冲正时间")
    private LocalDateTime reverseTime;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}