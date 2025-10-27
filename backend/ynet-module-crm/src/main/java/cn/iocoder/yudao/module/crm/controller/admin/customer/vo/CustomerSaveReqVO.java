package cn.iocoder.yudao.module.crm.controller.admin.customer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - CRM客户主表(零售+对公共用)新增/修改 Request VO")
@Data
public class CustomerSaveReqVO {

    @Schema(description = "客户ID(主键)", requiredMode = Schema.RequiredMode.REQUIRED, example = "5592")
    private Long id;

    @Schema(description = "客户编号(唯一标识)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户编号(唯一标识)不能为空")
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "客户类型(1=零售客户, 2=对公客户)不能为空")
    private Integer customerType;

    @Schema(description = "客户名称(零售为姓名,对公为企业名称)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "客户名称(零售为姓名,对公为企业名称)不能为空")
    private String customerName;

    @Schema(description = "客户等级(零售:普通/VIP/金卡/钻石卡等, 对公:普通/重点/战略客户等)")
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "客户状态(1=正常, 2=冻结, 3=注销)不能为空")
    private Integer customerStatus;

    @Schema(description = "是否优质客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否优质客户(0=否, 1=是)不能为空")
    private Boolean isHighQuality;

    @Schema(description = "是否重要客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否重要客户(0=否, 1=是)不能为空")
    private Boolean isImportant;

    @Schema(description = "信用状态(如:良好、一般、较差)", example = "2")
    private String creditStatus;

    @Schema(description = "信用等级(如:AAA、AA、A、BBB等)")
    private String creditLevel;

    @Schema(description = "信用评分")
    private BigDecimal creditScore;

    @Schema(description = "客户来源(如:网点开发、电话营销、网络营销等)")
    private String customerSource;

    @Schema(description = "客户标签(多个标签用逗号分隔)")
    private String customerTag;

    @Schema(description = "备注信息", example = "随便")
    private String remark;

    @Schema(description = "所属部门ID(数据权限)", example = "27475")
    private Long deptId;

}