package cn.iocoder.yudao.module.aicrm.controller.admin.customerimportantevent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户重要事件表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerImportantEventRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31684")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID（关联客户主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30978")
    @ExcelProperty("客户ID（关联客户主表）")
    private Long customerId;

    @Schema(description = "事件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("事件名称")
    private String eventName;

    @Schema(description = "事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）")
    private String eventStatus;

    @Schema(description = "事件发生日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件发生日期")
    private LocalDate eventDate;

    @Schema(description = "事件内容")
    @ExcelProperty("事件内容")
    private String eventContent;

    @Schema(description = "维护人ID（关联用户表）", example = "30671")
    @ExcelProperty("维护人ID（关联用户表）")
    private Long maintainerId;

    @Schema(description = "维护人姓名", example = "赵六")
    @ExcelProperty("维护人姓名")
    private String maintainerName;

    @Schema(description = "最近维护日期")
    @ExcelProperty("最近维护日期")
    private LocalDateTime maintainTime;

    @Schema(description = "事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）", example = "1")
    @ExcelProperty("事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）")
    private String eventType;

    @Schema(description = "事件级别（字典：aicrm_event_level，如：重要、一般、普通）")
    @ExcelProperty("事件级别（字典：aicrm_event_level，如：重要、一般、普通）")
    private String eventLevel;

    @Schema(description = "事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）")
    @ExcelProperty("事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）")
    private String eventSource;

    @Schema(description = "是否提醒（0-否，1-是）")
    @ExcelProperty("是否提醒（0-否，1-是）")
    private Boolean remindFlag;

    @Schema(description = "提醒时间")
    @ExcelProperty("提醒时间")
    private LocalDateTime remindTime;

    @Schema(description = "附件地址", example = "https://www.iocoder.cn")
    @ExcelProperty("附件地址")
    private String attachmentUrl;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}