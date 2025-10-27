package cn.iocoder.yudao.module.crm.controller.admin.customer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM客户主表(零售+对公共用)分页 Request VO")
@Data
public class CustomerPageReqVO extends PageParam {

    @Schema(description = "客户编号(唯一标识)")
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", example = "1")
    private Integer customerType;

    @Schema(description = "客户名称(零售为姓名,对公为企业名称)", example = "芋艿")
    private String customerName;

    @Schema(description = "客户等级(零售:普通/VIP/金卡/钻石卡等, 对公:普通/重点/战略客户等)")
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", example = "2")
    private Integer customerStatus;

    @Schema(description = "所属部门ID(数据权限)", example = "27475")
    private Long deptId;

}