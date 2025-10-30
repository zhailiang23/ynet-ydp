package cn.iocoder.yudao.module.aicrm.controller.admin.customergroupassignment.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户归属客群关系表（只记录关系，客群信息通过关联查询）分页 Request VO")
@Data
public class CustomerGroupAssignmentPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "28157")
    private Long customerId;

    @Schema(description = "客群ID（关联 crm_customer_group_info.id）", example = "23256")
    private Long groupId;

    @Schema(description = "分配日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] assignDate;

    @Schema(description = "分配操作人用户ID（关联 system_users.id）", example = "24800")
    private Long assignOperatorId;

    @Schema(description = "归属状态（0=已失效，1=生效中）", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}