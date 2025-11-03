package cn.iocoder.yudao.module.aicrm.controller.admin.customer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.math.BigDecimal;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.aicrm.enums.CrmDictTypeConstants;

@Schema(description = "管理后台 - CRM客户主表(零售+对公共用) Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerRespVO {

    @Schema(description = "客户ID(主键)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "客户编号(唯一标识)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客户编号")
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "客户类型", converter = DictConvert.class)
    @DictFormat(CrmDictTypeConstants.CUSTOMER_TYPE)
    private Integer customerType;

    @Schema(description = "客户名称(零售为姓名,对公为企业名称)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("客户名称")
    private String customerName;

    @Schema(description = "客户等级", example = "vip")
    @ExcelProperty(value = "客户等级", converter = DictConvert.class)
    @DictFormat(CrmDictTypeConstants.CUSTOMER_LEVEL)  // 仅用于Excel导出
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "客户状态", converter = DictConvert.class)
    @DictFormat(CrmDictTypeConstants.CUSTOMER_STATUS)
    private Integer customerStatus;

    @Schema(description = "分配状态(0=未分配, 1=已分配)", example = "1")
    @ExcelProperty("分配状态")
    private Integer assignmentStatus;

    @Schema(description = "是否有效客户")
    @ExcelProperty("是否有效客户")
    private Boolean isValid;

    @Schema(description = "是否优质客户")
    @ExcelProperty("是否优质客户")
    private Boolean isHighQuality;

    @Schema(description = "是否重要客户")
    @ExcelProperty("是否重要客户")
    private Boolean isImportant;

    @Schema(description = "信用状态")
    @ExcelProperty("信用状态")
    private String creditStatus;

    @Schema(description = "信用等级")
    @ExcelProperty(value = "信用等级", converter = DictConvert.class)
    @DictFormat(CrmDictTypeConstants.CREDIT_LEVEL)
    private String creditLevel;

    @Schema(description = "信用评分")
    @ExcelProperty("信用评分")
    private BigDecimal creditScore;

    @Schema(description = "客户来源")
    @ExcelProperty(value = "客户来源", converter = DictConvert.class)
    @DictFormat(CrmDictTypeConstants.CUSTOMER_SOURCE)
    private String customerSource;

    @Schema(description = "客户标签(多个标签用逗号分隔)")
    @ExcelProperty("客户标签")
    private String customerTag;

    @Schema(description = "备注信息")
    @ExcelProperty("备注信息")
    private String remark;

    @Schema(description = "所属部门ID(数据权限)", example = "27475")
    @ExcelProperty("所属部门ID")
    private Long deptId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}