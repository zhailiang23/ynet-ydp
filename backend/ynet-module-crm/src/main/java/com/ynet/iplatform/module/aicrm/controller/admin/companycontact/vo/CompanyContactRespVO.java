package com.ynet.iplatform.module.aicrm.controller.admin.companycontact.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - CRM对公客户联系人信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompanyContactRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1215")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID(关联crm_company_customer.customer_id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "8383")
    @ExcelProperty("客户ID(关联crm_company_customer.customer_id)")
    private Long customerId;

    @Schema(description = "联系方式类型(手机、座机、QQ、微信、邮箱等)", example = "1")
    @ExcelProperty("联系方式类型(手机、座机、QQ、微信、邮箱等)")
    private String contactType;

    @Schema(description = "联系人姓名")
    @ExcelProperty("联系人姓名")
    private String contactPerson;

    @Schema(description = "联系方式(电话号码、QQ号、微信号等)")
    @ExcelProperty("联系方式(电话号码、QQ号、微信号等)")
    private String contactMethod;

    @Schema(description = "是否首选项(否、是)")
    @ExcelProperty("是否首选项(否、是)")
    private Boolean isPrimary;

    @Schema(description = "来源系统(ECIF、CRM、零售CRM等)")
    @ExcelProperty("来源系统(ECIF、CRM、零售CRM等)")
    private String sourceSystem;

    @Schema(description = "联系方式序号")
    @ExcelProperty("联系方式序号")
    private Integer contactSeq;

    @Schema(description = "联系方式描述")
    @ExcelProperty("联系方式描述")
    private String contactDesc;

    @Schema(description = "状态", example = "2")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "备注说明", example = "你猜")
    @ExcelProperty("备注说明")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "ETL导入日期")
    @ExcelProperty("ETL导入日期")
    private LocalDate etlDate;

    @Schema(description = "老系统交易序列号")
    @ExcelProperty("老系统交易序列号")
    private String oldTxSeqNo;

    @Schema(description = "老系统最后更新系统")
    @ExcelProperty("老系统最后更新系统")
    private String oldLastUpdateSys;

    @Schema(description = "老系统最后更新用户")
    @ExcelProperty("老系统最后更新用户")
    private String oldLastUpdateUser;

    @Schema(description = "老系统最后更新时间")
    @ExcelProperty("老系统最后更新时间")
    private LocalDateTime oldLastUpdateTm;

    @Schema(description = "老系统联系方式ID", example = "3457")
    @ExcelProperty("老系统联系方式ID")
    private Integer oldContactId;

}