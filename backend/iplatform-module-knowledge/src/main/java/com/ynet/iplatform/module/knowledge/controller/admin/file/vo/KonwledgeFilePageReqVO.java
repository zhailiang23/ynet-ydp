package com.ynet.iplatform.module.knowledge.controller.admin.file.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 知识库文件分页 Request VO")
@Data
public class KonwledgeFilePageReqVO extends PageParam {

    @Schema(description = "知识库ID", example = "1")
    private Long baseId;

    @Schema(description = "文件名称", example = "芋艿")
    private String fileName;

    @Schema(description = "文件类型", example = "2")
    private String fileType;

    @Schema(description = "文件状态（0处理中 1处理完成 2处理失败）", example = "1")
    private Integer status;

}