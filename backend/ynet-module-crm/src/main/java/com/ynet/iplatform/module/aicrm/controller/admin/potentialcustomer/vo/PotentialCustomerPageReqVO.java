package com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer.vo;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 潜客分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PotentialCustomerPageReqVO extends PageParam {

    @Schema(description = "客户姓名", example = "张三")
    private String customerName;

    @Schema(description = "风险等级", example = "AGGRESSIVE")
    private String riskLevel;

    @Schema(description = "客户等级", example = "A")
    private String customerLevel;

    @Schema(description = "最小AI匹配度", example = "80")
    private Integer minAiMatchScore;

    @Schema(description = "潜客状态", example = "NEW")
    private String status;

    @Schema(description = "来源", example = "AI_RECOMMENDATION")
    private String source;

    @Schema(description = "洞察ID", example = "1")
    private Long insightId;

    @Schema(description = "分配给的客户经理ID", example = "1")
    private Long assignedUserId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
