package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 惠农贷款客户扩展分页 Request VO")
@Data
public class GridHuinongCustomerLoanPageReqVO extends PageParam {

    @Schema(description = "客户ID (关联 grid_customer)", example = "22175")
    private Long customerId;

    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "客户大类 (关联字典 aicrm_customer_category)")
    private String customerCategory;

    @Schema(description = "细分类型", example = "1")
    private String subdivisionType;

    @Schema(description = "经营地址")
    private String businessAddress;

    @Schema(description = "性别: 男/女")
    private String gender;

    @Schema(description = "客户情况")
    private String customerSituation;

    @Schema(description = "经营年限")
    private Integer businessYears;

    @Schema(description = "当前融资情况")
    private String currentFinancing;

    @Schema(description = "信贷产品需求")
    private String creditDemand;

    @Schema(description = "需求月份 (1-12)")
    private String demandMonth;

    @Schema(description = "需求旬 (关联字典 aicrm_demand_period: 上旬/中旬/下旬)")
    private String demandPeriod;

    @Schema(description = "业务进展")
    private String businessProgress;

    @Schema(description = "客户来源 (关联字典 aicrm_customer_source: 行内客户/外拓客户)")
    private String customerSource;

    @Schema(description = "是否进件")
    private Boolean isApplied;

    @Schema(description = "进件时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] applyTime;

    @Schema(description = "是否审批通过")
    private Boolean isApproved;

    @Schema(description = "审批通过时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] approveTime;

    @Schema(description = "贷款产品名称", example = "赵六")
    private String loanProductName;

    @Schema(description = "贷款金额 (元)")
    private BigDecimal loanAmount;

    @Schema(description = "授信额度 (元)")
    private BigDecimal creditLimit;

    @Schema(description = "贷款余额 (元)")
    private BigDecimal loanBalance;

    @Schema(description = "逾期状态", example = "2")
    private String overdueStatus;

    @Schema(description = "创建人ID", example = "15328")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "29525")
    private Long updaterId;

}