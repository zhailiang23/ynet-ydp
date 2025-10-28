package cn.iocoder.yudao.module.aicrm.controller.admin.customeridentity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.module.aicrm.enums.CrmDictTypeConstants;

@Schema(description = "管理后台 - 客户证件信息表（零售客户特有，1对多关系，支持多证件） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerIdentityRespVO {

    @Schema(description = "证件信息主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "2363")
    @ExcelProperty("证件信息主键")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2052")
    @ExcelProperty("客户ID")
    private Long customerId;

    @Schema(description = "证件类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("证件类型")
    @DictFormat(CrmDictTypeConstants.CUSTOMER_IDENTITY_TYPE)
    private String identityType;

    @Schema(description = "证件号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("证件号码")
    private String identityNo;

    @Schema(description = "证件上的姓名", example = "李四")
    @ExcelProperty("证件上的姓名")
    private String identityName;

    @Schema(description = "发证机关")
    @ExcelProperty("发证机关")
    private String issueAuthority;

    @Schema(description = "发证日期")
    @ExcelProperty("发证日期")
    private LocalDate issueDate;

    @Schema(description = "有效期至")
    @ExcelProperty("有效期至")
    private LocalDate expiryDate;

    @Schema(description = "是否主证件", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否主证件")
    private Boolean isPrimary;

    @Schema(description = "证件地址")
    @ExcelProperty("证件地址")
    private String identityAddress;

    @Schema(description = "证件正面照片URL", example = "https://www.iocoder.cn")
    @ExcelProperty("证件正面照片URL")
    private String identityFrontUrl;

    @Schema(description = "证件反面照片URL", example = "https://www.iocoder.cn")
    @ExcelProperty("证件反面照片URL")
    private String identityBackUrl;

    @Schema(description = "核验状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("核验状态")
    private Integer verificationStatus;

    @Schema(description = "核验时间")
    @ExcelProperty("核验时间")
    private LocalDateTime verificationTime;

    @Schema(description = "核验备注", example = "你猜")
    @ExcelProperty("核验备注")
    private String verificationRemark;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updater;

}