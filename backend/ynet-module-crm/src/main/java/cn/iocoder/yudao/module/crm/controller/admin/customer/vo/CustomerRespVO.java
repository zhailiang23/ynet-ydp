package cn.iocoder.yudao.module.crm.controller.admin.customer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM客户主表(零售+对公共用) Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerRespVO {

    @Schema(description = "客户ID(主键)", requiredMode = Schema.RequiredMode.REQUIRED, example = "5592")
    @ExcelProperty("客户ID(主键)")
    private Long id;

    @Schema(description = "客户编号(唯一标识)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客户编号(唯一标识)")
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("客户类型(1=零售客户, 2=对公客户)")
    private Integer customerType;

    @Schema(description = "客户名称(零售为姓名,对公为企业名称)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("客户名称(零售为姓名,对公为企业名称)")
    private String customerName;

    @Schema(description = "客户等级(零售:普通/VIP/金卡/钻石卡等, 对公:普通/重点/战略客户等)")
    @ExcelProperty("客户等级(零售:普通/VIP/金卡/钻石卡等, 对公:普通/重点/战略客户等)")
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("客户状态(1=正常, 2=冻结, 3=注销)")
    private Integer customerStatus;

    @Schema(description = "所属部门ID(数据权限)", example = "27475")
    @ExcelProperty("所属部门ID(数据权限)")
    private Long deptId;

}