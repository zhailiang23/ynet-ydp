package com.ynet.iplatform.module.aicrm.controller.admin.customergroupassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户归属客群关系表（只记录关系，客群信息通过关联查询） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerGroupAssignmentRespVO {

    @Schema(description = "归属客群关系主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15059")
    @ExcelProperty("归属客群关系主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "28157")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "客群ID（关联 crm_customer_group_info.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23256")
    @ExcelProperty("客群ID（关联 crm_customer_group_info.id）")
    private Long groupId;

    @Schema(description = "分配日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分配日期")
    private LocalDate assignDate;

    @Schema(description = "分配操作人用户ID（关联 system_users.id）", example = "24800")
    @ExcelProperty("分配操作人用户ID（关联 system_users.id）")
    private Long assignOperatorId;

    @Schema(description = "归属状态（0=已失效，1=生效中）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("归属状态（0=已失效，1=生效中）")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    // ==================== 客户群信息 (联表查询字段) ====================

    @Schema(description = "客户群编号", example = "RG001")
    @ExcelProperty("客户群编号")
    private String groupCode;

    @Schema(description = "客户群名称", example = "高净值零售客群")
    @ExcelProperty("客户群名称")
    private String groupName;

    @Schema(description = "客户群分类 (字典: aicrm_customer_group_category)", example = "high_net_worth")
    @ExcelProperty("客户群分类")
    private String groupCategory;

    @Schema(description = "群成员类型 (字典: aicrm_group_member_type)", example = "retail")
    @ExcelProperty("群成员类型")
    private String memberType;

    @Schema(description = "客户来源 (字典: aicrm_customer_source)", example = "offline_marketing")
    @ExcelProperty("客户来源")
    private String customerSource;

    @Schema(description = "客户群成员数", example = "10")
    @ExcelProperty("客户群成员数")
    private Integer memberCount;

    // ==================== 关联用户和部门信息 (联表查询字段) ====================

    @Schema(description = "创建人姓名 (联表 system_users)", example = "张三")
    @ExcelProperty("创建人")
    private String creatorName;

    @Schema(description = "最近修改人姓名 (联表 system_users)", example = "李四")
    @ExcelProperty("最近修改人")
    private String updaterName;

    @Schema(description = "所属部门名称 (联表 system_dept)", example = "研发部")
    @ExcelProperty("所属部门")
    private String deptName;

    @Schema(description = "客户群创建时间", example = "2025-10-29 12:00:00")
    @ExcelProperty("客户群创建时间")
    private LocalDateTime groupCreateTime;

    @Schema(description = "客户群最近修改时间", example = "2025-10-29 18:00:00")
    @ExcelProperty("客户群最近修改时间")
    private LocalDateTime groupUpdateTime;

}
