package cn.iocoder.yudao.module.aicrm.controller.admin.companyaddress.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Schema(description = "管理后台 - CRM对公客户地址信息新增/修改 Request VO")
@Data
public class CompanyAddressSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11416")
    private Long id;

    @Schema(description = "客户ID(关联crm_company_customer.customer_id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "31864")
    @NotNull(message = "客户ID(关联crm_company_customer.customer_id)不能为空")
    private Long customerId;

    @Schema(description = "地址类型(公司地址、家庭地址、其他地址)", example = "2")
    private String addressType;

    @Schema(description = "是否首选项(否、是)")
    private Boolean isPrimary;

    @Schema(description = "详细地址")
    private String addressDetail;

    @Schema(description = "邮编")
    private String postalCode;

    @Schema(description = "来源系统(ECIF、CRM、零售CRM等)")
    private String sourceSystem;

    @Schema(description = "国家或地区")
    private String countryOrRegion;

    @Schema(description = "省份代码")
    private String provinceCode;

    @Schema(description = "城市代码")
    private String cityCode;

    @Schema(description = "区县代码")
    private String countyCode;

    @Schema(description = "乡镇代码")
    private String townCode;

    @Schema(description = "乡镇名称", example = "李四")
    private String townName;

    @Schema(description = "街道名称", example = "赵六")
    private String streetName;

    @Schema(description = "村组编号")
    private String villageNo;

    @Schema(description = "村组名称", example = "张三")
    private String villageName;

    @Schema(description = "地区代码")
    private String areaCode;

    @Schema(description = "行政区域")
    private String adminZone;

    @Schema(description = "英文地址")
    private String enAddress;

    @Schema(description = "联系方式类型")
    private String contactMethod;

    @Schema(description = "地址级别")
    private String addressLevel;

    @Schema(description = "备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "ETL导入日期")
    private LocalDate etlDate;

    @Schema(description = "老系统交易序列号")
    private String oldTxSeqNo;

    @Schema(description = "老系统最后更新系统")
    private String oldLastUpdateSys;

    @Schema(description = "老系统最后更新用户")
    private String oldLastUpdateUser;

    @Schema(description = "老系统最后更新时间")
    private LocalDateTime oldLastUpdateTm;

    @Schema(description = "老系统地址ID", example = "5573")
    private String oldAddressId;

}