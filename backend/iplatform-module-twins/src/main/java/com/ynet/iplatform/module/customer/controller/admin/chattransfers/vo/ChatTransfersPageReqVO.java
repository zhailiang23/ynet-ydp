package com.ynet.iplatform.module.customer.controller.admin.chattransfers.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 转接记录分页 Request VO")
@Data
public class ChatTransfersPageReqVO extends PageParam {

    @Schema(description = "被转接用户名称", example = "张三")
    private String userName;

    @Schema(description = "转接人名称", example = "客服A")
    private String fromAdminName;

    @Schema(description = "转接给名称", example = "客服B")
    private String toAdminName;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}