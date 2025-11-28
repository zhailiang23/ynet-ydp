package com.ynet.iplatform.module.twins.controller.admin.customerUser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 接入用户分页 Request VO")
@Data
public class CustomerUserPageReqVO extends PageParam {

    @Schema(description = "用户名", example = "芋艿")
    private String username;

}