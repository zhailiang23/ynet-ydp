package com.ynet.iplatform.module.grid.controller.admin.competitorinfo;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;

import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.*;

import com.ynet.iplatform.module.grid.controller.admin.competitorinfo.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.competitorinfo.CompetitorInfoDO;
import com.ynet.iplatform.module.grid.service.competitorinfo.CompetitorInfoService;

@Tag(name = "管理后台 - 同业信息")
@RestController
@RequestMapping("/grid/competitor-info")
@Validated
public class CompetitorInfoController {

    @Resource
    private CompetitorInfoService competitorInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建同业信息")
    @PreAuthorize("@ss.hasPermission('grid:competitor-info:create')")
    public CommonResult<Long> createCompetitorInfo(@Valid @RequestBody CompetitorInfoSaveReqVO createReqVO) {
        return success(competitorInfoService.createCompetitorInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新同业信息")
    @PreAuthorize("@ss.hasPermission('grid:competitor-info:update')")
    public CommonResult<Boolean> updateCompetitorInfo(@Valid @RequestBody CompetitorInfoSaveReqVO updateReqVO) {
        competitorInfoService.updateCompetitorInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除同业信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:competitor-info:delete')")
    public CommonResult<Boolean> deleteCompetitorInfo(@RequestParam("id") Long id) {
        competitorInfoService.deleteCompetitorInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除同业信息")
                @PreAuthorize("@ss.hasPermission('grid:competitor-info:delete')")
    public CommonResult<Boolean> deleteCompetitorInfoList(@RequestParam("ids") List<Long> ids) {
        competitorInfoService.deleteCompetitorInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得同业信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:competitor-info:query')")
    public CommonResult<CompetitorInfoRespVO> getCompetitorInfo(@RequestParam("id") Long id) {
        CompetitorInfoDO competitorInfo = competitorInfoService.getCompetitorInfo(id);
        return success(BeanUtils.toBean(competitorInfo, CompetitorInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得同业信息分页")
    @PreAuthorize("@ss.hasPermission('grid:competitor-info:query')")
    public CommonResult<PageResult<CompetitorInfoRespVO>> getCompetitorInfoPage(@Valid CompetitorInfoPageReqVO pageReqVO) {
        PageResult<CompetitorInfoDO> pageResult = competitorInfoService.getCompetitorInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompetitorInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出同业信息 Excel")
    @PreAuthorize("@ss.hasPermission('grid:competitor-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompetitorInfoExcel(@Valid CompetitorInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompetitorInfoDO> list = competitorInfoService.getCompetitorInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "同业信息.xls", "数据", CompetitorInfoRespVO.class,
                        BeanUtils.toBean(list, CompetitorInfoRespVO.class));
    }

}