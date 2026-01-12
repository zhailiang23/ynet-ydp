package com.ynet.iplatform.module.grid.controller.admin.customer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 网格客户档案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridCustomerRespVO {

    @Schema(description = "客户档案ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19662")
    @ExcelProperty("客户档案ID")
    private Long id;

    @Schema(description = "所属网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15668")
    @ExcelProperty("所属网格ID")
    private Long gridId;

    @Schema(description = "客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI")
    private String customerType;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("客户姓名")
    private String customerName;

    @Schema(description = "身份证号 (脱敏: 前3后3)")
    @ExcelProperty("身份证号 (脱敏: 前3后3)")
    private String idCard;

    @Schema(description = "联系电话 (脱敏: 前3后4)")
    @ExcelProperty("联系电话 (脱敏: 前3后4)")
    private String phone;

    @Schema(description = "手机号是否已二次验证")
    @ExcelProperty("手机号是否已二次验证")
    private Boolean phoneVerified;

    @Schema(description = "详细地址")
    @ExcelProperty("详细地址")
    private String address;

    @Schema(description = "客户位置 (经纬度)")
    @ExcelProperty("客户位置 (经纬度)")
    private byte[] location;

    @Schema(description = "客户经理ID (关联 system_users)", example = "3156")
    @ExcelProperty("客户经理ID (关联 system_users)")
    private Long managerId;

    @Schema(description = "客户来源")
    @ExcelProperty("客户来源")
    private String source;

    @Schema(description = "状态: NORMAL/INACTIVE/BLACKLIST", example = "2")
    @ExcelProperty("状态: NORMAL/INACTIVE/BLACKLIST")
    private String status;

    @Schema(description = "创建人ID", example = "28285")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "25142")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}