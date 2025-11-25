package com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - CRM智能陪练-虚拟客户分页 Request VO")
@Data
public class PracticeVirtualCustomerPageReqVO extends PageParam {

    @Schema(description = "客户姓名", example = "芋艿")
    private String name;

    @Schema(description = "性别", example = "male")
    private String gender;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "职业", example = "executive")
    private String occupation;

    @Schema(description = "所属行业", example = "finance")
    private String industry;

    @Schema(description = "性格类型", example = "rational")
    private String personalityType;

    @Schema(description = "风险偏好", example = "conservative")
    private String riskPreference;

    @Schema(description = "自定义参数（JSON格式）", example = "你猜")
    private String memo;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}