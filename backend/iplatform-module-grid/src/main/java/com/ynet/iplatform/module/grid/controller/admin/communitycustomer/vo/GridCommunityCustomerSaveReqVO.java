package com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 社区客户新增/修改 Request VO")
@Data
public class GridCommunityCustomerSaveReqVO {

    @Schema(description = "客户ID（编辑时必填）", requiredMode = Schema.RequiredMode.REQUIRED, example = "859")
    private Long id;

    // ==================== 客户基本信息 (grid_customer 表字段) ====================

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotBlank(message = "客户姓名不能为空")
    private String customerName;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13800138000")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @Schema(description = "身份证号", example = "110101199001011234")
    private String idNumber;

    @Schema(description = "详细地址", example = "北京市朝阳区XXX")
    private String address;

    @Schema(description = "经度", example = "116.404")
    private Double longitude;

    @Schema(description = "纬度", example = "39.915")
    private Double latitude;

    @Schema(description = "客户经理ID (关联 system_users)", example = "1")
    private Long managerId;

    @Schema(description = "所属网格ID (关联 grid_info，根据坐标自动查找)", example = "1")
    private Long gridId;

    // ==================== 社区客户专属字段 (grid_customer 表字段) ====================

    @Schema(description = "家庭成员数", example = "4")
    private Integer familyMembers;

    @Schema(description = "住房类型（字典: community_housing_type）", example = "OWNED")
    private String housingType;

    @Schema(description = "月收入（元）", example = "8000.00")
    private BigDecimal monthlyIncome;

}