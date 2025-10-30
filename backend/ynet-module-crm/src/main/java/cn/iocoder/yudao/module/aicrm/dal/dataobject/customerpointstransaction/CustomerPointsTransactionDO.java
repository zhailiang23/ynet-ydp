package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpointstransaction;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户积分消费明细表（积分交易流水表） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_points_transaction")
@KeySequence("crm_customer_points_transaction_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPointsTransactionDO extends BaseDO {

    /**
     * 交易主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 积分账户ID（关联 crm_customer_points 表）
     */
    private Long pointsAccountId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 交易日期
     */
    private LocalDate transactionDate;
    /**
     * 兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）
     */
    private String exchangeChannel;
    /**
     * 礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）
     */
    private String giftExchangeMethod;
    /**
     * 订单消耗积分
     */
    private Long orderConsumedPoints;
    /**
     * 订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）
     */
    private String orderStatus;
    /**
     * 订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）
     */
    private String orderApprovalStatus;
    /**
     * 兑换用户
     */
    private String exchangeUser;
    /**
     * 交易流水号
     */
    private String transactionNo;
    /**
     * 交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）
     */
    private String transactionType;
    /**
     * 交易时间
     */
    private LocalDateTime transactionTime;
    /**
     * 积分变动（正数为增加，负数为减少）
     */
    private Long pointsChange;
    /**
     * 交易前积分
     */
    private Long pointsBefore;
    /**
     * 交易后积分
     */
    private Long pointsAfter;
    /**
     * 交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）
     */
    private String transactionStatus;
    /**
     * 交易描述
     */
    private String transactionDesc;
    /**
     * 业务类型（字典: aicrm_points_business_type）
     */
    private String businessType;
    /**
     * 业务单号
     */
    private String businessNo;
    /**
     * 礼品编码
     */
    private String giftCode;
    /**
     * 礼品名称
     */
    private String giftName;
    /**
     * 礼品数量
     */
    private Integer giftQuantity;
    /**
     * 礼品价值
     */
    private BigDecimal giftValue;
    /**
     * 配送地址
     */
    private String deliveryAddress;
    /**
     * 配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）
     */
    private String deliveryStatus;
    /**
     * 配送时间
     */
    private LocalDateTime deliveryTime;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 过期日期（对于有效期的积分）
     */
    private LocalDate expireDate;
    /**
     * 退款原因
     */
    private String refundReason;
    /**
     * 退款时间
     */
    private LocalDateTime refundTime;
    /**
     * 退款积分
     */
    private Long refundPoints;
    /**
     * 审批人ID
     */
    private Long approvalUserId;
    /**
     * 审批人姓名
     */
    private String approvalUserName;
    /**
     * 审批时间
     */
    private LocalDateTime approvalTime;
    /**
     * 审批备注
     */
    private String approvalRemark;
    /**
     * 操作人ID
     */
    private Long operatorUserId;
    /**
     * 操作人姓名
     */
    private String operatorUserName;
    /**
     * 操作部门ID
     */
    private Long operatorDeptId;
    /**
     * 操作部门名称
     */
    private String operatorDeptName;
    /**
     * 是否已冲正
     */
    private Boolean isReversed;
    /**
     * 冲正交易ID
     */
    private Long reverseTransactionId;
    /**
     * 冲正时间
     */
    private LocalDateTime reverseTime;
    /**
     * 备注
     */
    private String remark;


}