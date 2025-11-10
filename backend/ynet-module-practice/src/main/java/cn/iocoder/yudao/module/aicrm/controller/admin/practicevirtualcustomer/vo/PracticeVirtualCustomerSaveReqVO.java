package cn.iocoder.yudao.module.aicrm.controller.admin.practicevirtualcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM智能陪练-虚拟客户新增/修改 Request VO")
@Data
public class PracticeVirtualCustomerSaveReqVO {

    @Schema(description = "虚拟客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30143")
    private Long id;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "客户姓名不能为空")
    private String name;

    @Schema(description = "性别：字典 aicrm_gender")
    private String gender;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "职业")
    private String occupation;

    @Schema(description = "所属行业")
    private String industry;

    @Schema(description = "性格类型：字典 aicrm_personality_type（如理性型/感性型）", example = "1")
    private String personalityType;

    @Schema(description = "风险偏好")
    private String riskPreference;

    @Schema(description = "自定义参数（JSON格式）", example = "你猜")
    private String memo;

}