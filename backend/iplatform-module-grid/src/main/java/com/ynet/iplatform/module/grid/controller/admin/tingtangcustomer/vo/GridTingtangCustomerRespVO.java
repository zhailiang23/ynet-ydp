package com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 厅堂客户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridTingtangCustomerRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11627")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "16898")
    private Long customerId;

    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("客户名称")
    private String customerName;

    @Schema(description = "性别", example = "男")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "客群类型", example = "信贷客户")
    @ExcelProperty("客群类型")
    private String customerGroup;

    @Schema(description = "身份证号", example = "410102199001011234")
    @ExcelProperty("身份证号")
    private String idCard;

    @Schema(description = "联系电话", example = "13800138000")
    @ExcelProperty("联系电话")
    private String phone;

    @Schema(description = "地址", example = "河南省郑州市金水区")
    @ExcelProperty("地址")
    private String address;

    @Schema(description = "经度", example = "113.625368")
    private BigDecimal longitude;

    @Schema(description = "纬度", example = "34.746611")
    private BigDecimal latitude;

    @Schema(description = "是否建档")
    @ExcelProperty("是否建档")
    private Boolean isArchived;

    @Schema(description = "CRM客户号", example = "CRM001")
    @ExcelProperty("CRM客户号")
    private String crmCustomerId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
