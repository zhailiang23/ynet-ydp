package com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 零贷市场描述分页 Request VO")
@Data
public class GridZerodaiMarketDescriptionPageReqVO extends PageParam {

    @Schema(description = "网格ID", example = "20527")
    private Long gridId;

    @Schema(description = "市场名称", example = "张三")
    private String marketName;

    @Schema(description = "市场类型 (关联字典 aicrm_market_type)", example = "2")
    private String marketType;

    @Schema(description = "市场位置坐标")
    private byte[] location;

    @Schema(description = "市场地址")
    private String address;

    @Schema(description = "经营范围")
    private String businessScope;

    @Schema(description = "商户数量", example = "9341")
    private Integer merchantCount;

    @Schema(description = "日均客流量")
    private Integer dailyTraffic;

    @Schema(description = "市场特点")
    private String marketFeatures;

    @Schema(description = "目标客户群体")
    private String targetCustomers;

    @Schema(description = "潜力分析")
    private String potentialAnalysis;

    @Schema(description = "客户经理ID", example = "18547")
    private Long managerId;

    @Schema(description = "创建人ID", example = "11787")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "16604")
    private Long updaterId;

}