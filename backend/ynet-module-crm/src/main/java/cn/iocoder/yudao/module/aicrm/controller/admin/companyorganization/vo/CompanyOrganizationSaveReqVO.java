package cn.iocoder.yudao.module.aicrm.controller.admin.companyorganization.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - CRM对公客户组织架构信息新增/修改 Request VO")
@Data
public class CompanyOrganizationSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14275")
    private Long id;

    @Schema(description = "客户ID(关联crm_company_customer.customer_id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15359")
    @NotNull(message = "客户ID(关联crm_company_customer.customer_id)不能为空")
    private Long customerId;

    @Schema(description = "父级组织ID(顶级组织为NULL)", example = "10585")
    private Long parentId;

    @Schema(description = "组织编码")
    private String orgCode;

    @Schema(description = "组织名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "组织名称不能为空")
    private String orgName;

    @Schema(description = "组织全称", example = "张三")
    private String orgFullName;

    @Schema(description = "组织层级(1-一级,2-二级,3-三级...)")
    private Integer orgLevel;

    @Schema(description = "组织类型(总部、分公司、事业部、部门等)", example = "2")
    private String orgType;

    @Schema(description = "组织状态(active-启用,inactive-停用,dissolved-解散)", example = "2")
    private String orgStatus;

    @Schema(description = "负责人姓名", example = "王五")
    private String leaderName;

    @Schema(description = "负责人职位")
    private String leaderPosition;

    @Schema(description = "负责人电话")
    private String leaderPhone;

    @Schema(description = "负责人邮箱")
    private String leaderEmail;

    @Schema(description = "员工人数", example = "15622")
    private Integer employeeCount;

    @Schema(description = "成立日期")
    private LocalDate establishedDate;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    private String contactEmail;

    @Schema(description = "办公地址")
    private String contactAddress;

    @Schema(description = "业务范围")
    private String businessScope;

    @Schema(description = "备注说明", example = "随便")
    private String remark;

    @Schema(description = "排序号(同级组织排序)")
    private Integer sortOrder;

}