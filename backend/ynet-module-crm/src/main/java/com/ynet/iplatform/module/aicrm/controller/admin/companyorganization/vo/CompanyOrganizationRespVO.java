package com.ynet.iplatform.module.aicrm.controller.admin.companyorganization.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM对公客户组织架构信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompanyOrganizationRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14275")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID(关联crm_company_customer.customer_id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15359")
    @ExcelProperty("客户ID(关联crm_company_customer.customer_id)")
    private Long customerId;

    @Schema(description = "父级组织ID(顶级组织为NULL)", example = "10585")
    @ExcelProperty("父级组织ID(顶级组织为NULL)")
    private Long parentId;

    @Schema(description = "组织编码")
    @ExcelProperty("组织编码")
    private String orgCode;

    @Schema(description = "组织名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("组织名称")
    private String orgName;

    @Schema(description = "组织全称", example = "张三")
    @ExcelProperty("组织全称")
    private String orgFullName;

    @Schema(description = "组织层级(1-一级,2-二级,3-三级...)")
    @ExcelProperty("组织层级(1-一级,2-二级,3-三级...)")
    private Integer orgLevel;

    @Schema(description = "组织类型(总部、分公司、事业部、部门等)", example = "2")
    @ExcelProperty("组织类型(总部、分公司、事业部、部门等)")
    private String orgType;

    @Schema(description = "组织状态(active-启用,inactive-停用,dissolved-解散)", example = "2")
    @ExcelProperty("组织状态(active-启用,inactive-停用,dissolved-解散)")
    private String orgStatus;

    @Schema(description = "负责人姓名", example = "王五")
    @ExcelProperty("负责人姓名")
    private String leaderName;

    @Schema(description = "负责人职位")
    @ExcelProperty("负责人职位")
    private String leaderPosition;

    @Schema(description = "负责人电话")
    @ExcelProperty("负责人电话")
    private String leaderPhone;

    @Schema(description = "负责人邮箱")
    @ExcelProperty("负责人邮箱")
    private String leaderEmail;

    @Schema(description = "员工人数", example = "15622")
    @ExcelProperty("员工人数")
    private Integer employeeCount;

    @Schema(description = "成立日期")
    @ExcelProperty("成立日期")
    private LocalDate establishedDate;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    @ExcelProperty("联系邮箱")
    private String contactEmail;

    @Schema(description = "办公地址")
    @ExcelProperty("办公地址")
    private String contactAddress;

    @Schema(description = "业务范围")
    @ExcelProperty("业务范围")
    private String businessScope;

    @Schema(description = "备注说明", example = "随便")
    @ExcelProperty("备注说明")
    private String remark;

    @Schema(description = "排序号(同级组织排序)")
    @ExcelProperty("排序号(同级组织排序)")
    private Integer sortOrder;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}