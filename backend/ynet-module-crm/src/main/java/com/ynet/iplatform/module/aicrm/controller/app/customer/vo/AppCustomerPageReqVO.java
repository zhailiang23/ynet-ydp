package com.ynet.iplatform.module.aicrm.controller.app.customer.vo;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "移动端 - CRM客户主表分页 Request VO")
@Data
public class AppCustomerPageReqVO extends PageParam {

    @Schema(description = "客户编号")
    private String customerNo;

    @Schema(description = "客户类型(1=零售客户, 2=对公客户)", example = "1")
    private Integer customerType;

    @Schema(description = "客户名称", example = "张三")
    private String customerName;

    @Schema(description = "客户等级")
    private String customerLevel;

    @Schema(description = "客户状态(1=正常, 2=冻结, 3=注销)", example = "1")
    private Integer customerStatus;

    @Schema(description = "是否优质客户")
    private Boolean isHighQuality;

    @Schema(description = "是否重要客户")
    private Boolean isImportant;

    @Schema(description = "信用等级")
    private String creditLevel;

    @Schema(description = "客户来源")
    private String customerSource;

    @Schema(description = "客户标签")
    private String customerTag;

}
