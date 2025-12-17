package com.ynet.iplatform.module.grid.controller.admin.customer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格客户档案分页 Request VO")
@Data
public class GridCustomerPageReqVO extends PageParam {

    @Schema(description = "所属网格ID", example = "15668")
    private Long gridId;

    @Schema(description = "客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI", example = "1")
    private String customerType;

    @Schema(description = "客户姓名", example = "张三")
    private String customerName;

    @Schema(description = "身份证号 (脱敏: 前3后3)")
    private String idCard;

    @Schema(description = "联系电话 (脱敏: 前3后4)")
    private String phone;

    @Schema(description = "手机号是否已二次验证")
    private Boolean phoneVerified;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "客户位置 (经纬度)")
    private byte[] location;

    @Schema(description = "客户经理ID (关联 system_users)", example = "3156")
    private Long managerId;

    @Schema(description = "客户来源")
    private String source;

    @Schema(description = "状态: NORMAL/INACTIVE/BLACKLIST", example = "2")
    private String status;

    @Schema(description = "创建人ID", example = "28285")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "25142")
    private Long updaterId;

}