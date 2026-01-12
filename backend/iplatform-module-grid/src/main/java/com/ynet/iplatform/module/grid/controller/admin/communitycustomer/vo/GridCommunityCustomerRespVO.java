package com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 社区客户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridCommunityCustomerRespVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "859")
    @ExcelProperty("客户ID")
    private Long id;

    @Schema(description = "客户姓名", example = "张三")
    @ExcelProperty("客户姓名")
    private String customerName;

    @Schema(description = "手机号", example = "13800138000")
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "证件类型", example = "身份证")
    @ExcelProperty("证件类型")
    private String idType;

    @Schema(description = "证件号", example = "110101199001011234")
    @ExcelProperty("证件号")
    private String idNumber;

    @Schema(description = "机构名称", example = "研发部门")
    @ExcelProperty("机构名称")
    private String orgName;

    @Schema(description = "客户经理姓名", example = "李四")
    @ExcelProperty("客户经理姓名")
    private String managerName;

    @Schema(description = "客户经理ID", example = "1")
    private Long managerId;

    @Schema(description = "地址", example = "北京市朝阳区xxx路xxx号")
    private String address;

    @Schema(description = "经度", example = "116.397128")
    private Double longitude;

    @Schema(description = "纬度", example = "39.916527")
    private Double latitude;

    @Schema(description = "家庭成员数")
    @ExcelProperty("家庭成员数")
    private Integer familyMembers;

    @Schema(description = "住房类型", example = "1")
    @ExcelProperty("住房类型")
    private String housingType;

    @Schema(description = "月收入 (元)")
    @ExcelProperty("月收入 (元)")
    private BigDecimal monthlyIncome;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}