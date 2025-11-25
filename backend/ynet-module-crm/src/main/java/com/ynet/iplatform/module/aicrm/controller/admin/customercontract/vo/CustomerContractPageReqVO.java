package com.ynet.iplatform.module.aicrm.controller.admin.customercontract.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户签约信息表（零售+对公共用）分页 Request VO")
@Data
public class CustomerContractPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "20186")
    private Long customerId;

    @Schema(description = "签约类型ID（关联 crm_contract_type.id）", example = "517")
    private Long contractTypeId;

    @Schema(description = "签约账号/签约编号")
    private String contractNo;

    @Schema(description = "签约日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] contractDate;

    @Schema(description = "签约机构ID（关联 system_dept.id）", example = "28110")
    private Long branchId;

    @Schema(description = "签约机构名称", example = "赵六")
    private String branchName;

    @Schema(description = "签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）", example = "1")
    private String contractStatus;

    @Schema(description = "签约情况（具体的签约渠道或产品名称）")
    private String contractSituation;

    @Schema(description = "证件类型（字典: aicrm_identity_type）", example = "2")
    private String identityType;

    @Schema(description = "证件号码（加密存储）")
    private String identityNo;

    @Schema(description = "关联账号")
    private String accountNo;

    @Schema(description = "生效日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] startDate;

    @Schema(description = "失效日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] endDate;

    @Schema(description = "签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）")
    private String signChannel;

    @Schema(description = "解约日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] cancelDate;

    @Schema(description = "解约原因", example = "不香")
    private String cancelReason;

    @Schema(description = "客户经理ID（关联 system_users.id）", example = "20408")
    private Long managerUserId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}