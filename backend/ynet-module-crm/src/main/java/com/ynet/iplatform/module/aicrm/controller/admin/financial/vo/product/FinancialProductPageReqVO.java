package com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM 金融产品分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FinancialProductPageReqVO extends PageParam {

    @Schema(description = "产品代码", example = "FY001")
    private String productCode;

    @Schema(description = "产品名称", example = "富盈")
    private String productName;

    @Schema(description = "产品目录ID", example = "1")
    private Long catalogId;

    @Schema(description = "产品分类", example = "理财")
    private String category;

    @Schema(description = "风险等级", example = "R2")
    private String riskLevel;

    @Schema(description = "产品状态（0=停售 1=在售）", example = "1")
    private Integer status;

    @Schema(description = "是否热销", example = "1")
    private Integer isHot;

    @Schema(description = "是否新品", example = "0")
    private Integer isNew;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
