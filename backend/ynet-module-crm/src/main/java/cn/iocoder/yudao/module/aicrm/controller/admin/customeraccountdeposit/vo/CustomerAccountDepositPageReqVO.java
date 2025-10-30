package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountdeposit.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户存款账户信息表（零售+对公共用）分页 Request VO")
@Data
public class CustomerAccountDepositPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "25492")
    private Long customerId;

    @Schema(description = "账号")
    private String accountNo;

    @Schema(description = "产品名称", example = "张三")
    private String productName;

    @Schema(description = "户名", example = "张三")
    private String accountName;

    @Schema(description = "开户日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] openDate;

    @Schema(description = "销户日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] closeDate;

    @Schema(description = "账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）", example = "1")
    private String accountStatus;

    @Schema(description = "利率（%）")
    private BigDecimal interestRate;

    @Schema(description = "存期（如：1年、6个月、3个月、活期）")
    private String depositTerm;

    @Schema(description = "余额")
    private BigDecimal balance;

    @Schema(description = "协议号")
    private String agrNo;

    @Schema(description = "产品ID", example = "30718")
    private String productId;

    @Schema(description = "卡号")
    private String cardNo;

    @Schema(description = "存款类型（1=活期，2=定期）", example = "2")
    private String depositType;

    @Schema(description = "币种（字典: aicrm_currency_type）", example = "1")
    private String currencyType;

    @Schema(description = "原始金额（开户金额）")
    private BigDecimal originalAmount;

    @Schema(description = "到期日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] matureDate;

    @Schema(description = "起息日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] startInterestDate;

    @Schema(description = "开户机构ID（关联 system_dept.id）", example = "32726")
    private Long deptId;

    @Schema(description = "开户机构名称", example = "赵六")
    private String deptName;

    @Schema(description = "客户经理用户ID（关联 system_users.id）", example = "20539")
    private Long managerUserId;

    @Schema(description = "月日均余额")
    private BigDecimal monthAvgBalance;

    @Schema(description = "季日均余额")
    private BigDecimal quarterAvgBalance;

    @Schema(description = "年日均余额")
    private BigDecimal yearAvgBalance;

    @Schema(description = "月累计转入")
    private BigDecimal monthTotalIn;

    @Schema(description = "月累计转出")
    private BigDecimal monthTotalOut;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}