package cn.iocoder.yudao.module.aicrm.controller.admin.retailcustomer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM零售客户扩展表(零售客户特有基本信息)分页 Request VO")
@Data
public class RetailCustomerPageReqVO extends PageParam {

    @Schema(description = "客户ID(外键,关联crm_customer.id,唯一)", example = "24261")
    private Long customerId;

    @Schema(description = "昵称", example = "芋艿")
    private String nickname;

    @Schema(description = "性别(0=未知, 1=男, 2=女)")
    private Integer gender;

    @Schema(description = "出生日期")
    private LocalDate birthday;

    @Schema(description = "年龄(可通过出生日期计算)")
    private Integer age;

}