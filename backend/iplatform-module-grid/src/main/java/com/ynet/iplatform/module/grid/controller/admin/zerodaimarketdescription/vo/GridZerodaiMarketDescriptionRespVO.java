package com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 零贷市场描述 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridZerodaiMarketDescriptionRespVO {

    @Schema(description = "市场描述ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29655")
    @ExcelProperty("市场描述ID")
    private Long id;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20527")
    @ExcelProperty("网格ID")
    private Long gridId;

    @Schema(description = "市场名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("市场名称")
    private String marketName;

    @Schema(description = "市场类型 (关联字典 aicrm_market_type)", example = "2")
    @ExcelProperty("市场类型 (关联字典 aicrm_market_type)")
    private String marketType;

    @Schema(description = "市场位置坐标")
    @ExcelProperty("市场位置坐标")
    private byte[] location;

    @Schema(description = "市场地址")
    @ExcelProperty("市场地址")
    private String address;

    @Schema(description = "经营范围")
    @ExcelProperty("经营范围")
    private String businessScope;

    @Schema(description = "商户数量", example = "9341")
    @ExcelProperty("商户数量")
    private Integer merchantCount;

    @Schema(description = "日均客流量")
    @ExcelProperty("日均客流量")
    private Integer dailyTraffic;

    @Schema(description = "市场特点")
    @ExcelProperty("市场特点")
    private String marketFeatures;

    @Schema(description = "目标客户群体")
    @ExcelProperty("目标客户群体")
    private String targetCustomers;

    @Schema(description = "潜力分析")
    @ExcelProperty("潜力分析")
    private String potentialAnalysis;

    @Schema(description = "客户经理ID", example = "18547")
    @ExcelProperty("客户经理ID")
    private Long managerId;

    @Schema(description = "创建人ID", example = "11787")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "16604")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}