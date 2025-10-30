package cn.iocoder.yudao.module.aicrm.controller.admin.customerimportantevent.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户重要事件表（零售+对公共用）分页 Request VO")
@Data
public class CustomerImportantEventPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联客户主表）", example = "30978")
    private Long customerId;

    @Schema(description = "事件名称", example = "芋艿")
    private String eventName;

    @Schema(description = "事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）", example = "1")
    private String eventStatus;

    @Schema(description = "事件发生日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] eventDate;

    @Schema(description = "事件内容")
    private String eventContent;

    @Schema(description = "维护人ID（关联用户表）", example = "30671")
    private Long maintainerId;

    @Schema(description = "维护人姓名", example = "赵六")
    private String maintainerName;

    @Schema(description = "最近维护日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] maintainTime;

    @Schema(description = "事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）", example = "1")
    private String eventType;

    @Schema(description = "事件级别（字典：aicrm_event_level，如：重要、一般、普通）")
    private String eventLevel;

    @Schema(description = "事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）")
    private String eventSource;

    @Schema(description = "是否提醒（0-否，1-是）")
    private Boolean remindFlag;

    @Schema(description = "提醒时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] remindTime;

    @Schema(description = "附件地址", example = "https://www.iocoder.cn")
    private String attachmentUrl;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}