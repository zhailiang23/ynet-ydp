package com.ynet.iplatform.module.twins.controller.admin.statistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 数字分身统计概览 Response VO")
@Data
public class TwinsOverviewRespVO {

    @Schema(description = "总员工数", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    private Integer totalEmployees;

    @Schema(description = "活跃 AI 数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer activeAiCount;

    @Schema(description = "总对话数", requiredMode = Schema.RequiredMode.REQUIRED, example = "3571")
    private Integer totalSessions;

    @Schema(description = "本月对话数", requiredMode = Schema.RequiredMode.REQUIRED, example = "212")
    private Integer monthSessions;

    @Schema(description = "总留资数", requiredMode = Schema.RequiredMode.REQUIRED, example = "479")
    private Integer totalCollectInfos;

    @Schema(description = "本月留资数", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
    private Integer monthCollectInfos;

    @Schema(description = "客户满意度（评分均值）", requiredMode = Schema.RequiredMode.REQUIRED, example = "4.8")
    private BigDecimal avgSatisfaction;

    @Schema(description = "平均响应时间（秒）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2.3")
    private BigDecimal avgResponseTime;

    @Schema(description = "留资类型统计")
    private List<CollectInfoTypeStats> collectInfoTypeStats;

    @Schema(description = "员工使用排行")
    private List<EmployeeRankItem> employeeRankList;

    @Schema(description = "留资类型统计项")
    @Data
    public static class CollectInfoTypeStats {
        @Schema(description = "模板 ID", example = "1")
        private Integer templateId;

        @Schema(description = "类型名称", example = "预约咨询")
        private String name;

        @Schema(description = "总数", example = "156")
        private Integer total;

        @Schema(description = "本月数", example = "23")
        private Integer monthCount;

        @Schema(description = "上月数", example = "18")
        private Integer lastMonthCount;
    }

    @Schema(description = "员工排行项")
    @Data
    public static class EmployeeRankItem {
        @Schema(description = "员工 ID", example = "1")
        private Integer adminId;

        @Schema(description = "员工姓名", example = "陈经理")
        private String name;

        @Schema(description = "部门", example = "理财部")
        private String department;

        @Schema(description = "本月对话数", example = "123")
        private Integer monthSessions;

        @Schema(description = "本月留资数", example = "18")
        private Integer monthCollectInfos;
    }
}
