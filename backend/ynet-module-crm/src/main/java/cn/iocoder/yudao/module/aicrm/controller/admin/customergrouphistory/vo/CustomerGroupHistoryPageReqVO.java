package cn.iocoder.yudao.module.aicrm.controller.admin.customergrouphistory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户归属客群调整历史表（记录调整当时的客群信息快照）分页 Request VO")
@Data
public class CustomerGroupHistoryPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "9371")
    private Long customerId;

    @Schema(description = "调整日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] adjustDate;

    @Schema(description = "调整原因", example = "不喜欢")
    private String adjustReason;

    @Schema(description = "调整前客群ID", example = "4086")
    private Long beforeGroupId;

    @Schema(description = "调整前客户群编号")
    private String beforeGroupCode;

    @Schema(description = "调整前客户群名称", example = "张三")
    private String beforeGroupName;

    @Schema(description = "调整前客户群分类")
    private String beforeGroupCategory;

    @Schema(description = "调整前客群管理员用户ID", example = "8057")
    private Long beforeManagerUserId;

    @Schema(description = "调整后客群ID", example = "20827")
    private Long afterGroupId;

    @Schema(description = "调整后客户群编号")
    private String afterGroupCode;

    @Schema(description = "调整后客户群名称", example = "李四")
    private String afterGroupName;

    @Schema(description = "调整后客户群分类")
    private String afterGroupCategory;

    @Schema(description = "调整后客群管理员用户ID", example = "21638")
    private Long afterManagerUserId;

    @Schema(description = "调整操作人用户ID（关联 system_users.id）", example = "6685")
    private Long adjustOperatorId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}