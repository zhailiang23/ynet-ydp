package com.ynet.iplatform.module.aicrm.controller.admin.practicecase;

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

import com.ynet.iplatform.module.aicrm.controller.admin.practicecase.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO;
import com.ynet.iplatform.module.aicrm.service.practicecase.PracticeCaseService;

@Tag(name = "管理后台 - CRM智能陪练-销售案例")
@RestController
@RequestMapping("/aicrm/practice-case")
@Validated
public class PracticeCaseController {

    @Resource
    private PracticeCaseService practiceCaseService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM智能陪练-销售案例")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-case:create')")
    public CommonResult<Long> createPracticeCase(@Valid @RequestBody PracticeCaseSaveReqVO createReqVO) {
        return success(practiceCaseService.createPracticeCase(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM智能陪练-销售案例")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-case:update')")
    public CommonResult<Boolean> updatePracticeCase(@Valid @RequestBody PracticeCaseSaveReqVO updateReqVO) {
        practiceCaseService.updatePracticeCase(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM智能陪练-销售案例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:practice-case:delete')")
    public CommonResult<Boolean> deletePracticeCase(@RequestParam("id") Long id) {
        practiceCaseService.deletePracticeCase(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM智能陪练-销售案例")
                @PreAuthorize("@ss.hasPermission('aicrm:practice-case:delete')")
    public CommonResult<Boolean> deletePracticeCaseList(@RequestParam("ids") List<Long> ids) {
        practiceCaseService.deletePracticeCaseListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-销售案例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-case:query')")
    public CommonResult<PracticeCaseRespVO> getPracticeCase(@RequestParam("id") Long id) {
        PracticeCaseDO practiceCase = practiceCaseService.getPracticeCase(id);
        return success(BeanUtils.toBean(practiceCase, PracticeCaseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-销售案例分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-case:query')")
    public CommonResult<PageResult<PracticeCaseRespVO>> getPracticeCasePage(@Valid PracticeCasePageReqVO pageReqVO) {
        PageResult<PracticeCaseDO> pageResult = practiceCaseService.getPracticeCasePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PracticeCaseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-销售案例 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-case:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeCaseExcel(@Valid PracticeCasePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeCaseDO> list = practiceCaseService.getPracticeCasePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-销售案例.xls", "数据", PracticeCaseRespVO.class,
                        BeanUtils.toBean(list, PracticeCaseRespVO.class));
    }

}