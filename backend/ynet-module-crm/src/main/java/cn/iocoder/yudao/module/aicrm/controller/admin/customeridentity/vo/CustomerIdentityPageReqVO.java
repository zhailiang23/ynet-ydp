package cn.iocoder.yudao.module.aicrm.controller.admin.customeridentity.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户证件信息表（零售客户特有，1对多关系，支持多证件）分页 Request VO")
@Data
public class CustomerIdentityPageReqVO extends PageParam {

    @Schema(description = "客户ID", example = "2052")
    private Long customerId;

    @Schema(description = "证件类型", example = "1")
    private String identityType;

    @Schema(description = "证件号码")
    private String identityNo;

    @Schema(description = "证件上的姓名", example = "李四")
    private String identityName;

    @Schema(description = "发证机关")
    private String issueAuthority;

    @Schema(description = "发证日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] issueDate;

    @Schema(description = "有效期至")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] expiryDate;

    @Schema(description = "是否主证件")
    private Boolean isPrimary;

    @Schema(description = "证件地址")
    private String identityAddress;

    @Schema(description = "证件正面照片URL", example = "https://www.iocoder.cn")
    private String identityFrontUrl;

    @Schema(description = "证件反面照片URL", example = "https://www.iocoder.cn")
    private String identityBackUrl;

    @Schema(description = "核验状态", example = "1")
    private Integer verificationStatus;

    @Schema(description = "核验时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] verificationTime;

    @Schema(description = "核验备注", example = "你猜")
    private String verificationRemark;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}