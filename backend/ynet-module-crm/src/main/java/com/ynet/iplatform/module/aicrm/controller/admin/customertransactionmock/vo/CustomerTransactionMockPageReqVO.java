package com.ynet.iplatform.module.aicrm.controller.admin.customertransactionmock.vo;

import java.time.LocalTime;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户交易明细信息表（Mock数据）分页 Request VO")
@Data
public class CustomerTransactionMockPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "14542")
    private Long customerId;

    @Schema(description = "账户ID（关联账户表）", example = "22678")
    private Long accountId;

    @Schema(description = "交易日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] transactionDate;

    @Schema(description = "交易时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] transactionTime;

    @Schema(description = "交易机构编号")
    private String branchNo;

    @Schema(description = "交易机构名称", example = "张三")
    private String branchName;

    @Schema(description = "原交易代码")
    private String originalTranCode;

    @Schema(description = "现转标志（字典: aicrm_cash_flag）")
    private String cashFlag;

    @Schema(description = "子账户编号")
    private String subAccountNo;

    @Schema(description = "账户编号")
    private String accountNo;

    @Schema(description = "币种（字典: aicrm_currency_type）")
    private String currencyCode;

    @Schema(description = "交易流水号")
    private String tansNo;

    @Schema(description = "交易类型（字典: aicrm_transaction_type）", example = "2")
    private String tradType;

    @Schema(description = "交易金额")
    private BigDecimal tradMoney;

    @Schema(description = "账户余额")
    private BigDecimal acctBal;

    @Schema(description = "交易摘要")
    private String tradAbs;

    @Schema(description = "审核标志")
    private String review;

    @Schema(description = "交易渠道（字典: aicrm_transaction_channel）")
    private String tradChn;

    @Schema(description = "交易柜员")
    private String tradTeller;

    @Schema(description = "经办人")
    private String handler;

    @Schema(description = "对方账号")
    private String advsAcct;

    @Schema(description = "对方户名", example = "王五")
    private String advsAcctName;

    @Schema(description = "往来类型", example = "2")
    private String contactType;

    @Schema(description = "钞汇标志")
    private String currTranFlag;

    @Schema(description = "贷款标志")
    private String loanFlag;

    @Schema(description = "手续费")
    private BigDecimal cost;

    @Schema(description = "记账日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] accountinDate;

    @Schema(description = "交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）", example = "1")
    private String transactionStatus;

    @Schema(description = "交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）")
    private String direction;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}