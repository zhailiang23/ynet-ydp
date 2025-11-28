package com.ynet.iplatform.module.twins.controller.admin.statistics;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.module.twins.controller.admin.statistics.vo.TwinsOverviewRespVO;
import com.ynet.iplatform.module.twins.service.statistics.TwinsStatisticsService;

@Tag(name = "管理后台 - 数字分身统计")
@RestController
@RequestMapping("/twins/statistics")
@Validated
public class TwinsStatisticsController {

    @Resource
    private TwinsStatisticsService twinsStatisticsService;

    @GetMapping("/overview")
    @Operation(summary = "获取统计概览")
    @PreAuthorize("@ss.hasPermission('twins:statistics:query')")
    public CommonResult<TwinsOverviewRespVO> getOverview() {
        return success(twinsStatisticsService.getOverview());
    }

}
