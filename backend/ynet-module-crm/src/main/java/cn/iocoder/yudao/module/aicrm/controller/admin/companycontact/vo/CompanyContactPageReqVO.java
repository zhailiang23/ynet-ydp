package cn.iocoder.yudao.module.aicrm.controller.admin.companycontact.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM对公客户联系人信息分页 Request VO")
@Data
public class CompanyContactPageReqVO extends PageParam {

    @Schema(description = "客户ID(关联crm_company_customer.customer_id)", example = "8383")
    private Long customerId;

    @Schema(description = "联系方式类型(手机、座机、QQ、微信、邮箱等)", example = "1")
    private String contactType;

    @Schema(description = "联系人姓名")
    private String contactPerson;

    @Schema(description = "联系方式(电话号码、QQ号、微信号等)")
    private String contactMethod;

    @Schema(description = "是否首选项(否、是)")
    private Boolean isPrimary;

    @Schema(description = "来源系统(ECIF、CRM、零售CRM等)")
    private String sourceSystem;

    @Schema(description = "联系方式序号")
    private Integer contactSeq;

    @Schema(description = "联系方式描述")
    private String contactDesc;

    @Schema(description = "状态", example = "2")
    private String status;

    @Schema(description = "备注说明", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "ETL导入日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] etlDate;

    @Schema(description = "老系统交易序列号")
    private String oldTxSeqNo;

    @Schema(description = "老系统最后更新系统")
    private String oldLastUpdateSys;

    @Schema(description = "老系统最后更新用户")
    private String oldLastUpdateUser;

    @Schema(description = "老系统最后更新时间")
    private LocalDateTime oldLastUpdateTm;

    @Schema(description = "老系统联系方式ID", example = "3457")
    private Integer oldContactId;

}