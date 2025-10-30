package cn.iocoder.yudao.module.aicrm.controller.admin.customergridhistory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户归属网格调整历史表（记录调整当时的网格信息快照）分页 Request VO")
@Data
public class CustomerGridHistoryPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "20140")
    private Long customerId;

    @Schema(description = "调整日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] adjustDate;

    @Schema(description = "调整原因", example = "不喜欢")
    private String adjustReason;

    @Schema(description = "调整前网格ID", example = "3915")
    private Long beforeGridId;

    @Schema(description = "调整前网格编号")
    private String beforeGridCode;

    @Schema(description = "调整前网格名称", example = "张三")
    private String beforeGridName;

    @Schema(description = "调整前网格类型", example = "2")
    private String beforeGridType;

    @Schema(description = "调整前网格管理员用户ID", example = "10407")
    private Long beforeGridManagerUserId;

    @Schema(description = "调整后网格ID", example = "17421")
    private Long afterGridId;

    @Schema(description = "调整后网格编号")
    private String afterGridCode;

    @Schema(description = "调整后网格名称", example = "王五")
    private String afterGridName;

    @Schema(description = "调整后网格类型", example = "2")
    private String afterGridType;

    @Schema(description = "调整后网格管理员用户ID", example = "4332")
    private Long afterGridManagerUserId;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "15494")
    private Long adjustOperatorId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
