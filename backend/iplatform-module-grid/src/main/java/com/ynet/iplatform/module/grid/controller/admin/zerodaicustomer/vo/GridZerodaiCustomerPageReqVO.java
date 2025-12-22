package com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 零贷客户扩展分页 Request VO")
@Data
public class GridZerodaiCustomerPageReqVO extends PageParam {

    @Schema(description = "商户名称", example = "张三超市")
    private String customerName;

    @Schema(description = "负责人姓名", example = "张三")
    private String principalName;

}