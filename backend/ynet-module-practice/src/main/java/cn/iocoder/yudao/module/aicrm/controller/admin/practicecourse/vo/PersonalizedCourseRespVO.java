package cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - CRM智能陪练-个性化课程响应 VO")
@Data
public class PersonalizedCourseRespVO {

    @Schema(description = "课程ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30143")
    private Long courseId;

    @Schema(description = "课程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "客户沟通技巧培训")
    private String courseName;

    @Schema(description = "课程描述", example = "针对大客户的沟通技巧培训课程")
    private String courseDescription;

    @Schema(description = "课程类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "个性化课程")
    private String courseType;

    @Schema(description = "复杂度", requiredMode = Schema.RequiredMode.REQUIRED, example = "中级")
    private String complexityLevel;

    @Schema(description = "课程状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "未开始")
    private String status;

    @Schema(description = "剧本ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30144")
    private Long scriptId;

    @Schema(description = "剧本名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "客户沟通技巧培训剧本")
    private String scriptName;

    @Schema(description = "剧本版本", requiredMode = Schema.RequiredMode.REQUIRED, example = "v1.0")
    private String scriptVersion;

    @Schema(description = "虚拟客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30145")
    private Long virtualCustomerId;

    @Schema(description = "虚拟客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张先生")
    private String virtualCustomerName;

    @Schema(description = "培训材料ID", example = "30146")
    private Long materialId;

    @Schema(description = "培训材料名称", example = "沟通技巧培训手册.pdf")
    private String materialName;

    @Schema(description = "培训材料URL", example = "http://localhost:48080/admin-api/infra/file/get/30146")
    private String materialUrl;

    @Schema(description = "是否成功抽取文件内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean contentExtracted;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "文件处理警告信息", example = "文件文本抽取失败，但课程创建成功")
    private String warningMessage;
}