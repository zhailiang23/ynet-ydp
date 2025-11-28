package com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户留资信息分页 Request VO")
@Data
public class ChatCollectInfoPageReqVO extends PageParam {

    @Schema(description = "留资模板ID，关联customer_chat_collect_template表", example = "6852")
    private Integer templateId;

    @Schema(description = "留资状态：0-未处理，1-处理中，2-处理完成", example = "1")
    private Integer status;

}