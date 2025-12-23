package com.ynet.iplatform.module.grid.controller.admin.keyperson.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 关键人信息分页 Request VO")
@Data
public class KeyPersonPageReqVO extends PageParam {

    @Schema(description = "姓名（模糊搜索）", example = "赵六")
    private String personName;

    @Schema(description = "单位/小区（模糊搜索）")
    private String organization;

}