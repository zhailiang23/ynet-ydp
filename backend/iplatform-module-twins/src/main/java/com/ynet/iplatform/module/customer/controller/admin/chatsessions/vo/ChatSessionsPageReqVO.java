package com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会话信息分页 Request VO")
@Data
public class ChatSessionsPageReqVO extends PageParam {

    @Schema(description = "用户名", example = "张三")
    private String userName;

    @Schema(description = "会话发起时间")
    private LocalDateTime queriedAt;

    @Schema(description = "客服接入时间")
    private LocalDateTime acceptedAt;

    @Schema(description = "会话取消时间")
    private LocalDateTime canceledAt;

    @Schema(description = "中断时间")
    private LocalDateTime brokenAt;

    @Schema(description = "客服名", example = "客服小李")
    private String adminName;

    @Schema(description = "会话类型", example = "2")
    private Integer type;

    @Schema(description = "频率")
    private Short rate;

}