package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo;

import lombok.*;
import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户归属关系表（零售+对公共用，支持主协办模式）分页 Request VO")
@Data
public class CustomerAssignmentPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "27081")
    private Long customerId;

    @Schema(description = "归属类型（1=主办，2=协办）", example = "1")
    private Integer assignmentType;

    @Schema(description = "归属部门ID（关联 system_dept.id）", example = "29683")
    private Long deptId;

    @Schema(description = "客户经理用户ID（关联 system_users.id）", example = "9405")
    private Long userId;

    @Schema(description = "是否有查看权限")
    private Boolean hasViewRight;

    @Schema(description = "是否有维护权限")
    private Boolean hasMaintainRight;

    @Schema(description = "分配日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] assignDate;

    @Schema(description = "生效日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] effectiveDate;

    @Schema(description = "失效日期（NULL表示长期有效）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] expiryDate;

    @Schema(description = "分配操作人用户ID（关联 system_users.id）", example = "20346")
    private Long assignOperatorId;

    @Schema(description = "归属状态（0=已失效，1=生效中，2=待生效）", example = "2")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}