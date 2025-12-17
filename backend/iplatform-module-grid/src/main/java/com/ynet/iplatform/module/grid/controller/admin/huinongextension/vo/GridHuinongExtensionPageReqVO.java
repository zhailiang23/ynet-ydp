package com.ynet.iplatform.module.grid.controller.admin.huinongextension.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 惠农网格扩展分页 Request VO")
@Data
public class GridHuinongExtensionPageReqVO extends PageParam {

    @Schema(description = "网格ID (关联 grid_info)", example = "4292")
    private Long gridId;

    @Schema(description = "是否为网格营销站点")
    private Boolean isMarketingSite;

    @Schema(description = "是否为必选站点")
    private Boolean isRequired;

    @Schema(description = "创建人ID", example = "15127")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "17699")
    private Long updaterId;

}