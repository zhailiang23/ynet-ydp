package com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 社区客户扩展分页 Request VO")
@Data
public class GridCommunityCustomerPageReqVO extends PageParam {

    @Schema(description = "客户姓名", example = "张三")
    private String customerName;

}