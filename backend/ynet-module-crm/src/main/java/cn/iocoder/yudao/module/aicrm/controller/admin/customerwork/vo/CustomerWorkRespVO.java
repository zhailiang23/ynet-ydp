package cn.iocoder.yudao.module.aicrm.controller.admin.customerwork.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户工作或经营信息表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerWorkRespVO {

    @Schema(description = "工作信息主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "7843")
    @ExcelProperty("工作信息主键")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18589")
    @ExcelProperty("客户ID")
    private Long customerId;

    @Schema(description = "工作类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("工作类型")
    private String workType;

    @Schema(description = "工作单位名称/经营主体名称", example = "王五")
    @ExcelProperty("工作单位名称/经营主体名称")
    private String employerName;

    @Schema(description = "单位性质", example = "2")
    @ExcelProperty("单位性质")
    private String employerType;

    @Schema(description = "所属行业")
    @ExcelProperty("所属行业")
    private String industry;

    @Schema(description = "职位/职务")
    @ExcelProperty("职位/职务")
    private String position;

    @Schema(description = "工作年限/经营年限（单位:年）")
    @ExcelProperty("工作年限/经营年限（单位:年）")
    private Integer workYears;

    @Schema(description = "入职日期/开始经营日期")
    @ExcelProperty("入职日期/开始经营日期")
    private LocalDate startDate;

    @Schema(description = "离职日期/结束经营日期（NULL表示在职）")
    @ExcelProperty("离职日期/结束经营日期（NULL表示在职）")
    private LocalDate endDate;

    @Schema(description = "是否当前工作", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否当前工作")
    private Boolean isCurrent;

    @Schema(description = "工作地址-省")
    @ExcelProperty("工作地址-省")
    private String workAddressProvince;

    @Schema(description = "工作地址-市")
    @ExcelProperty("工作地址-市")
    private String workAddressCity;

    @Schema(description = "工作地址-区")
    @ExcelProperty("工作地址-区")
    private String workAddressDistrict;

    @Schema(description = "工作地址-详细地址")
    @ExcelProperty("工作地址-详细地址")
    private String workAddressDetail;

    @Schema(description = "年收入（单位:元）")
    @ExcelProperty("年收入（单位:元）")
    private BigDecimal annualIncome;

    @Schema(description = "月收入（单位:元）")
    @ExcelProperty("月收入（单位:元）")
    private BigDecimal monthlyIncome;

    @Schema(description = "收入来源说明")
    @ExcelProperty("收入来源说明")
    private String incomeSource;

    @Schema(description = "经营规模")
    @ExcelProperty("经营规模")
    private String businessScale;

    @Schema(description = "经营状态", example = "1")
    @ExcelProperty("经营状态")
    private String businessStatus;

    @Schema(description = "生产能力")
    @ExcelProperty("生产能力")
    private String productionCapacity;

    @Schema(description = "营业执照号/经营许可证号")
    @ExcelProperty("营业执照号/经营许可证号")
    private String businessLicenseNo;

    @Schema(description = "工作电话/单位电话")
    @ExcelProperty("工作电话/单位电话")
    private String workPhone;

    @Schema(description = "单位联系人")
    @ExcelProperty("单位联系人")
    private String contactPerson;

    @Schema(description = "联系人电话")
    @ExcelProperty("联系人电话")
    private String contactPhone;

    @Schema(description = "核验状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("核验状态")
    private Integer verificationStatus;

    @Schema(description = "核验时间")
    @ExcelProperty("核验时间")
    private LocalDateTime verificationTime;

    @Schema(description = "核验备注", example = "随便")
    @ExcelProperty("核验备注")
    private String verificationRemark;

    @Schema(description = "附件URL列表（JSON格式，如营业执照、工作证明等）")
    @ExcelProperty("附件URL列表（JSON格式，如营业执照、工作证明等）")
    private String attachmentUrls;

    @Schema(description = "备注信息", example = "随便")
    @ExcelProperty("备注信息")
    private String remark;

    @Schema(description = "扩展数据")
    @ExcelProperty("扩展数据")
    private String extraData;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
