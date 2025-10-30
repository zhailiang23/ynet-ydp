package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountcreditcard.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户信用卡账户信息表（仅限零售客户）分页 Request VO")
@Data
public class CustomerAccountCreditcardPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表，仅限零售客户）", example = "6373")
    private Long customerId;

    @Schema(description = "信用卡账号")
    private String accountNo;

    @Schema(description = "信用卡号（加密存储）")
    private String cardNo;

    @Schema(description = "信用卡产品名称", example = "李四")
    private String productName;

    @Schema(description = "持卡人姓名", example = "赵六")
    private String accountName;

    @Schema(description = "开卡日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] openDate;

    @Schema(description = "销卡日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] closeDate;

    @Schema(description = "账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）", example = "1")
    private String accountStatus;

    @Schema(description = "卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）", example = "1")
    private String cardType;

    @Schema(description = "卡等级")
    private String cardLevel;

    @Schema(description = "卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）")
    private String cardBrand;

    @Schema(description = "是否主卡（0=附属卡，1=主卡）")
    private Boolean isMainCard;

    @Schema(description = "主卡卡号（附属卡关联）")
    private String mainCardNo;

    @Schema(description = "币种", example = "2")
    private String currencyType;

    @Schema(description = "信用额度")
    private BigDecimal creditLimit;

    @Schema(description = "可用额度")
    private BigDecimal availableLimit;

    @Schema(description = "临时额度")
    private BigDecimal temporaryLimit;

    @Schema(description = "取现额度")
    private BigDecimal cashLimit;

    @Schema(description = "已用额度")
    private BigDecimal usedAmount;

    @Schema(description = "当前欠款余额")
    private BigDecimal balance;

    @Schema(description = "账单日（每月几号）")
    private Integer billingDay;

    @Schema(description = "还款日（每月几号）")
    private Integer paymentDueDay;

    @Schema(description = "本期账单金额")
    private BigDecimal currentBillAmount;

    @Schema(description = "最低还款额")
    private BigDecimal minPaymentAmount;

    @Schema(description = "未还金额")
    private BigDecimal unpaidAmount;

    @Schema(description = "上次还款日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] lastPaymentDate;

    @Schema(description = "上次还款金额")
    private BigDecimal lastPaymentAmount;

    @Schema(description = "逾期天数")
    private Integer overdueDays;

    @Schema(description = "逾期金额")
    private BigDecimal overdueAmount;

    @Schema(description = "逾期利息")
    private BigDecimal overdueInterest;

    @Schema(description = "累计逾期次数")
    private Integer overdueTimes;

    @Schema(description = "累计积分")
    private Long totalPoints;

    @Schema(description = "可用积分")
    private Long availablePoints;

    @Schema(description = "积分到期日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] pointsExpireDate;

    @Schema(description = "卡片有效期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] expireDate;

    @Schema(description = "激活日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] activateDate;

    @Schema(description = "最后交易日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] lastTransactionDate;

    @Schema(description = "发卡机构ID", example = "4937")
    private Long deptId;

    @Schema(description = "发卡机构名称", example = "赵六")
    private String deptName;

    @Schema(description = "客户经理用户ID", example = "4761")
    private Long managerUserId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}