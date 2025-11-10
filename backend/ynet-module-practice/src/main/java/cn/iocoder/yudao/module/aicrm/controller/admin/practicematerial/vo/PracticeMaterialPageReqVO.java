package cn.iocoder.yudao.module.aicrm.controller.admin.practicematerial.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-培训文件分页 Request VO")
@Data
public class PracticeMaterialPageReqVO extends PageParam {

    @Schema(description = "文件名称", example = "李四")
    private String name;

    @Schema(description = "文件URL", example = "https://www.iocoder.cn")
    private String fileUrl;

    @Schema(description = "文件大小（字节）")
    private Long fileSize;

    @Schema(description = "文件内容(纯文本)")
    private String content;

    @Schema(description = "文件内容(富文本)")
    private String contentRich;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}