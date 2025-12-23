package com.ynet.iplatform.module.grid.controller.admin.customer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 网格客户档案新增/修改 Request VO")
@Data
public class GridCustomerSaveReqVO {

    @Schema(description = "客户档案ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19662")
    private Long id;

    @Schema(description = "所属网格ID", example = "15668")
    private Long gridId;

    @Schema(description = "站点ID (用于自动获取网格ID)", example = "12345")
    private Long stationId;

    @Schema(description = "客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotBlank(message = "客户类型不能为空")
    private String customerType;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotBlank(message = "客户姓名不能为空")
    private String customerName;

    @Schema(description = "性别（男、女）", example = "男")
    private String gender;

    @Schema(description = "客群类型", example = "信贷客户")
    private String customerGroup;

    @Schema(description = "身份证号 (脱敏: 前3后3)")
    private String idCard;

    @Schema(description = "联系电话 (脱敏: 前3后4)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "手机号是否已二次验证")
    private Boolean phoneVerified;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "客户位置 (经纬度)")
    private byte[] location;

    @Schema(description = "经度 (用于前端传递，后端会转换为 location POINT)", example = "113.625368")
    private Double longitude;

    @Schema(description = "纬度 (用于前端传递，后端会转换为 location POINT)", example = "34.746611")
    private Double latitude;

    @Schema(description = "客户经理ID (关联 system_users)", example = "3156")
    private Long managerId;

    @Schema(description = "客户来源", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "客户来源不能为空")
    private String source;

    @Schema(description = "状态: NORMAL/INACTIVE/BLACKLIST", example = "2")
    private String status;

    @Schema(description = "创建人ID", example = "28285")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "25142")
    private Long updaterId;

}