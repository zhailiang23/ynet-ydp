package com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 惠农网格营销信息分页 Request VO")
@Data
public class GridHuinongMarketingPageReqVO extends PageParam {

    @Schema(description = "站点名称", example = "金水路惠农服务站")
    private String stationName;

    @Schema(description = "站点编号", example = "HN0001")
    private String stationCode;

    @Schema(description = "行政村名称", example = "王五")
    private String villageName;

}