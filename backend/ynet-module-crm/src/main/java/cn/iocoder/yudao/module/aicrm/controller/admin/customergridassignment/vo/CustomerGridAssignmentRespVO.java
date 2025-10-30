package cn.iocoder.yudao.module.aicrm.controller.admin.customergridassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户归属网格关系表（只记录关系，网格信息通过关联查询） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerGridAssignmentRespVO {

    @Schema(description = "归属网格关系主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27401")
    @ExcelProperty("归属网格关系主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "14506")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "网格ID（关联 crm_grid_info.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "10408")
    @ExcelProperty("网格ID（关联 crm_grid_info.id）")
    private Long gridId;

    @Schema(description = "网格编号", example = "GRID-001")
    @ExcelProperty("网格编号")
    private String gridCode;

    @Schema(description = "网格名称", example = "营业部网格1")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "网格类型", example = "retail")
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "网格管理员姓名", example = "张三")
    @ExcelProperty("网格管理员姓名")
    private String gridManagerName;

    @Schema(description = "分配日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配日期")
    private LocalDate assignDate;

    @Schema(description = "分配操作人用户ID（关联 system_users.id）", example = "20744")
    @ExcelProperty("分配操作人用户ID（关联 system_users.id）")
    private Long assignOperatorId;

    @Schema(description = "分配操作人姓名", example = "李四")
    @ExcelProperty("分配操作人姓名")
    private String assignOperatorName;

    @Schema(description = "归属状态（0=已失效，1=生效中）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("归属状态（0=已失效，1=生效中）")
    private Integer status;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}