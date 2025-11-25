package com.ynet.iplatform.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "管理后台 - 我的客户 Response VO")
@Data
public class MyCustomerRespVO {

    @Schema(description = "归属关系ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long assignmentId;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long customerId;

    @Schema(description = "客户编号", example = "C20001")
    private String customerNo;

    @Schema(description = "客户名称", example = "张三")
    private String customerName;

    @Schema(description = "客户类型(1-零售 2-对公)", example = "1")
    private Integer customerType;

    @Schema(description = "客户状态", example = "1")
    private Integer customerStatus;

    @Schema(description = "归属类型(1-主办 2-协办)", example = "1")
    private Integer assignmentType;

    @Schema(description = "归属部门ID", example = "1")
    private Long deptId;

    @Schema(description = "归属部门名称", example = "销售部")
    private String deptName;

    @Schema(description = "分配日期")
    private LocalDate assignDate;

    @Schema(description = "分配备注", example = "重点客户")
    private String remark;

    @Schema(description = "托管用户名称", example = "李四")
    private String delegateUserName;

    @Schema(description = "托管开始日期")
    private LocalDate delegateStartDate;

    @Schema(description = "托管结束日期")
    private LocalDate delegateEndDate;

}
