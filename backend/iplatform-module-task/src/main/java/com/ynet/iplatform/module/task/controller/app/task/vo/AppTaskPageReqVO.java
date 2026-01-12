package com.ynet.iplatform.module.task.controller.app.task.vo;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "移动端 - AI智能任务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppTaskPageReqVO extends PageParam {

    @Schema(description = "任务状态（0=待办 1=进行中 2=已完成 3=已取消）", example = "0")
    private Integer status;

    @Schema(description = "优先级", example = "P0")
    private String priority;

    @Schema(description = "是否AI生成（0=否 1=是）", example = "1")
    private Integer aiGenerated;

    @Schema(description = "客户姓名（模糊匹配）", example = "张三")
    private String customerName;

    @Schema(description = "最小业务价值评分", example = "8.0")
    private Double minBusinessValue;

    @Schema(description = "任务负责人ID", example = "1")
    private Long responsibleUserId;

}
