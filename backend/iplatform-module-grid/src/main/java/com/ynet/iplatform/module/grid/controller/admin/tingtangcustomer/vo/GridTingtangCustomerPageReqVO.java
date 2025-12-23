package com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 厅堂客户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class GridTingtangCustomerPageReqVO extends PageParam {

    @Schema(description = "客户名称", example = "张三")
    private String customerName;

    @Schema(description = "联系电话", example = "13800138000")
    private String phone;

}
