package cn.iocoder.yudao.module.aicrm.controller.admin.practicecase.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-销售案例分页 Request VO")
@Data
public class PracticeCasePageReqVO extends PageParam {

    @Schema(description = "案例标题")
    private String title;

    @Schema(description = "案例详细内容（正文）")
    private String content;

    @Schema(description = "标签（多个标签逗号分隔）")
    private String tags;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}