package cn.iocoder.yudao.module.aicrm.controller.admin.customeridentity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户证件信息表（零售客户特有，1对多关系，支持多证件）新增/修改 Request VO")
@Data
public class CustomerIdentitySaveReqVO {

    @Schema(description = "证件信息主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "2363")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2052")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "证件类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "证件类型不能为空")
    private String identityType;

    @Schema(description = "证件号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "证件号码不能为空")
    private String identityNo;

    @Schema(description = "证件上的姓名", example = "李四")
    private String identityName;

    @Schema(description = "发证机关")
    private String issueAuthority;

    @Schema(description = "发证日期")
    private LocalDate issueDate;

    @Schema(description = "有效期至")
    private LocalDate expiryDate;

    @Schema(description = "是否主证件", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否主证件不能为空")
    private Boolean isPrimary;

    @Schema(description = "证件地址")
    private String identityAddress;

    @Schema(description = "证件正面照片URL", example = "https://www.iocoder.cn")
    private String identityFrontUrl;

    @Schema(description = "证件反面照片URL", example = "https://www.iocoder.cn")
    private String identityBackUrl;

    @Schema(description = "核验状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "核验状态不能为空")
    private Integer verificationStatus;

    @Schema(description = "核验时间")
    private LocalDateTime verificationTime;

    @Schema(description = "核验备注", example = "你猜")
    private String verificationRemark;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}
