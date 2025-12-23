package com.ynet.iplatform.module.grid.controller.admin.competitorinfo.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 同业信息分页 Request VO")
@Data
public class CompetitorInfoPageReqVO extends PageParam {

    @Schema(description = "同业网点名称（模糊搜索）", example = "工商银行")
    private String competitorName;

    @Schema(description = "详细地址（模糊搜索）", example = "金水区")
    private String address;

}