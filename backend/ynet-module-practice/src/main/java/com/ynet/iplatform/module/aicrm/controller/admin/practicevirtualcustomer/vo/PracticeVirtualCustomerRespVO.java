package com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM智能陪练-虚拟客户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PracticeVirtualCustomerRespVO {

    @Schema(description = "虚拟客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30143")
    @ExcelProperty("虚拟客户ID")
    private Long id;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("客户姓名")
    private String name;

    @Schema(description = "性别", example = "male")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "年龄")
    @ExcelProperty("年龄")
    private Integer age;

    @Schema(description = "职业", example = "executive")
    @ExcelProperty("职业")
    private String occupation;

    @Schema(description = "所属行业", example = "finance")
    @ExcelProperty("所属行业")
    private String industry;

    @Schema(description = "性格类型", example = "rational")
    @ExcelProperty("性格类型")
    private String personalityType;

    @Schema(description = "风险偏好", example = "conservative")
    @ExcelProperty("风险偏好")
    private String riskPreference;

    @Schema(description = "自定义参数（JSON格式）", example = "你猜")
    @ExcelProperty("自定义参数（JSON格式）")
    private String memo;

    @Schema(description = "是否公开", example = "true")
    @ExcelProperty("是否公开")
    private Boolean isPublic;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}