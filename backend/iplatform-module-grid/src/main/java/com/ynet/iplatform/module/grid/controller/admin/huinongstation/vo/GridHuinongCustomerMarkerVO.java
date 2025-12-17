package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 惠农贷款客户地图标记 VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 惠农贷款客户地图标记 Response VO")
@Data
public class GridHuinongCustomerMarkerVO {

    @Schema(description = "贷款客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Long customerId;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String customerName;

    @Schema(description = "手机号码", example = "13800138000")
    private String phone;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal latitude;

    @Schema(description = "客户大类", example = "种植户")
    private String customerCategory;

    @Schema(description = "细分类型", example = "粮食种植")
    private String subdivisionType;

    @Schema(description = "经营地址", example = "金水区张村镇李庄")
    private String businessAddress;

    @Schema(description = "贷款金额（元）", example = "300000")
    private Long loanAmount;

    @Schema(description = "逾期状态", example = "正常")
    private String overdueStatus;
}
