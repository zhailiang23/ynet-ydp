package com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户留资信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChatCollectInfoRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15399")
    @ExcelProperty("主键")
    private Integer id;

    @Schema(description = "留资模板ID，关联customer_chat_collect_template表", requiredMode = Schema.RequiredMode.REQUIRED, example = "6852")
    @ExcelProperty("留资模板ID，关联customer_chat_collect_template表")
    private Integer templateId;

    @Schema(description = "留资状态：0-未处理，1-处理中，2-处理完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("留资状态：0-未处理，1-处理中，2-处理完成")
    private Integer status;

    @Schema(description = "受理时间，开始处理的时间")
    @ExcelProperty("受理时间，开始处理的时间")
    private LocalDateTime acceptTime;

    @Schema(description = "完成时间，处理完成的时间")
    @ExcelProperty("完成时间，处理完成的时间")
    private LocalDateTime finishTime;

    @Schema(description = "提交用户ID", example = "17220")
    @ExcelProperty("提交用户ID")
    private Integer userId;

    @Schema(description = "处理客服ID", example = "716")
    @ExcelProperty("处理客服ID")
    private Integer adminId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "留资模板名称", example = "产品咨询")
    @ExcelProperty("留资模板")
    private String templateName;

    @Schema(description = "留资状态文本", example = "未处理")
    @ExcelProperty("留资状态")
    private String statusText;

    @Schema(description = "提交用户名", example = "张三")
    @ExcelProperty("提交用户")
    private String username;

    @Schema(description = "处理客服名", example = "客服001")
    @ExcelProperty("处理客服")
    private String adminName;

    @Schema(description = "留资内容（JSON格式）", example = "{\"姓名\":\"张三\",\"手机号\":\"13800138000\"}")
    private String content;

    @Schema(description = "备注", example = "特殊需求说明")
    private String remark;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "来源", example = "weapp")
    private String source;

    @Schema(description = "留资模板描述", example = "用于收集客户基本信息")
    private String templateDescription;

}