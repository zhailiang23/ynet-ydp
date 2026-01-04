package com.ynet.iplatform.module.aicrm.controller.app.customer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Schema(description = "移动端 - CRM客户主表 Response VO")
@Data
public class AppCustomerRespVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "客户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer customerType;

    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String customerName;

    @Schema(description = "客户等级", example = "vip")
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer customerStatus;

    @Schema(description = "分配状态(0=未分配, 1=已分配)", example = "1")
    private Integer assignmentStatus;

    @Schema(description = "是否有效客户")
    private Boolean isValid;

    @Schema(description = "是否优质客户")
    private Boolean isHighQuality;

    @Schema(description = "是否重要客户")
    private Boolean isImportant;

    @Schema(description = "信用状态")
    private String creditStatus;

    @Schema(description = "信用等级")
    private String creditLevel;

    @Schema(description = "信用评分")
    private BigDecimal creditScore;

    @Schema(description = "客户来源")
    private String customerSource;

    @Schema(description = "客户标签")
    private String customerTag;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
