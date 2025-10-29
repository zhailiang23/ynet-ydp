package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户归属关系表（零售+对公共用，支持主协办模式） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAssignmentRespVO {

    @Schema(description = "归属关系主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9881")
    @ExcelProperty("归属关系主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27081")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "归属类型（1=主办，2=协办）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("归属类型（1=主办，2=协办）")
    private Integer assignmentType;

    @Schema(description = "归属部门ID（关联 system_dept.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29683")
    @ExcelProperty("归属部门ID（关联 system_dept.id）")
    private Long deptId;

    @Schema(description = "归属部门名称", example = "营业部")
    @ExcelProperty("归属部门名称")
    private String deptName;

    @Schema(description = "客户经理用户ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "9405")
    @ExcelProperty("客户经理用户ID（关联 system_users.id）")
    private Long userId;

    @Schema(description = "客户经理姓名", example = "张三")
    @ExcelProperty("客户经理姓名")
    private String userName;

    @Schema(description = "是否有查看权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否有查看权限")
    private Boolean hasViewRight;

    @Schema(description = "是否有维护权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否有维护权限")
    private Boolean hasMaintainRight;

    @Schema(description = "分配日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配日期")
    private LocalDate assignDate;

    @Schema(description = "生效日期")
    @ExcelProperty("生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "失效日期（NULL表示长期有效）")
    @ExcelProperty("失效日期（NULL表示长期有效）")
    private LocalDate expiryDate;

    @Schema(description = "分配操作人用户ID（关联 system_users.id）", example = "20346")
    @ExcelProperty("分配操作人用户ID（关联 system_users.id）")
    private Long assignOperatorId;

    @Schema(description = "归属状态（0=已失效，1=生效中，2=待生效）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("归属状态（0=已失效，1=生效中，2=待生效）")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}