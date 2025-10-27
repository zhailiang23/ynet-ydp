package cn.iocoder.yudao.module.crm.controller.admin.retailcustomer.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - CRM零售客户扩展表(零售客户特有基本信息) Response VO")
@Data
@ExcelIgnoreUnannotated
public class RetailCustomerRespVO {

    @Schema(description = "客户ID(外键,关联crm_customer.id,唯一)", requiredMode = Schema.RequiredMode.REQUIRED, example = "24261")
    @ExcelProperty("客户ID(外键,关联crm_customer.id,唯一)")
    private Long customerId;

    @Schema(description = "昵称", example = "芋艿")
    @ExcelProperty("昵称")
    private String nickname;

    @Schema(description = "性别(0=未知, 1=男, 2=女)")
    @ExcelProperty(value = "性别(0=未知, 1=男, 2=女)", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer gender;

    @Schema(description = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDate birthday;

    @Schema(description = "年龄(可通过出生日期计算)")
    @ExcelProperty("年龄(可通过出生日期计算)")
    private Integer age;

}