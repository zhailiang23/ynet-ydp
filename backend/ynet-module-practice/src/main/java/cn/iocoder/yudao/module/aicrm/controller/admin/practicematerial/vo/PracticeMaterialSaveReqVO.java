package cn.iocoder.yudao.module.aicrm.controller.admin.practicematerial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM智能陪练-培训文件新增/修改 Request VO")
@Data
public class PracticeMaterialSaveReqVO {

    @Schema(description = "文件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9877")
    private Long id;

    @Schema(description = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "文件名称不能为空")
    private String name;

    @Schema(description = "文件类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "training_manual")
    @NotEmpty(message = "文件类型不能为空")
    private String fileType;

    @Schema(description = "文件URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotEmpty(message = "文件URL不能为空")
    private String fileUrl;

    @Schema(description = "文件大小（字节）")
    private Long fileSize;

    @Schema(description = "文件内容(纯文本)")
    private String content;

    @Schema(description = "文件内容(富文本)")
    private String contentRich;

}