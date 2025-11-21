package cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM智能陪练-创建个性化课程 Request VO")
@Data
public class CreatePersonalizedCourseReqVO {

    @Schema(description = "课程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "客户沟通技巧培训")
    @NotEmpty(message = "课程名称不能为空")
    private String courseName;

    @Schema(description = "课程描述", example = "针对大客户的沟通技巧培训课程")
    private String courseDescription;

    @Schema(description = "营销环节", requiredMode = Schema.RequiredMode.REQUIRED, example = "initial_contact")
    @NotEmpty(message = "营销环节不能为空")
    private String marketingLink;

    @Schema(description = "是否创建新虚拟客户", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    @NotNull(message = "是否创建新虚拟客户不能为空")
    private Boolean createNewCustomer;

    @Schema(description = "现有虚拟客户ID（当createNewCustomer=false时必填）", example = "30143")
    private Long existingCustomerId;

    @Schema(description = "虚拟客户信息（当createNewCustomer=true时必填）")
    private VirtualCustomerCreateReqVO customerInfo;

    @Schema(description = "培训文件（支持Word、PDF、TXT格式）")
    private MultipartFile trainingFile;

    @Schema(description = "培训要求", example = "针对产品A的销售培训，重点关注核心优势和客户异议处理")
    private String trainingRequirements;

    @Schema(description = "难度等级", example = "medium")
    private String difficultyLevel;

    @Schema(description = "关联案例ID", example = "1001")
    private Long caseId;

    @Schema(description = "关联销售技巧ID", example = "2001")
    private Long skillId;

    @Schema(description = "虚拟客户创建请求")
    @Data
    public static class VirtualCustomerCreateReqVO {

        @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张先生")
        @NotEmpty(message = "客户姓名不能为空")
        private String name;

        @Schema(description = "性别", example = "male")
        private String gender;

        @Schema(description = "年龄", example = "35")
        private Integer age;

        @Schema(description = "职业", example = "executive")
        private String occupation;

        @Schema(description = "所属行业", example = "finance")
        private String industry;

        @Schema(description = "性格类型", example = "rational")
        private String personalityType;

        @Schema(description = "风险偏好", example = "conservative")
        private String riskPreference;

        @Schema(description = "自定义参数", example = "关注收益，厌恶风险")
        private String memo;
    }
}