package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 对话机器人管理分页 Request VO")
@Data
public class ChatRobotPageReqVO extends PageParam {

    @Schema(description = "机器人名称", example = "张三")
    private String name;

    @Schema(description = "对接平台类型", example = "1")
    private String platformType;

    @Schema(description = "是否启用（0=禁用，1=启用）", example = "1")
    private Integer status;

}