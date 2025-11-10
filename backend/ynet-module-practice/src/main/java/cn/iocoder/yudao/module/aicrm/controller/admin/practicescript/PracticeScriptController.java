package cn.iocoder.yudao.module.aicrm.controller.admin.practicescript;

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

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.aicrm.controller.admin.practicescript.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import cn.iocoder.yudao.module.aicrm.service.practicescript.PracticeScriptService;

@Tag(name = "管理后台 - CRM智能陪练-陪练剧本表（支持版本控制）")
@RestController
@RequestMapping("/aicrm/practice-script")
@Validated
public class PracticeScriptController {

    @Resource
    private PracticeScriptService practiceScriptService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM智能陪练-陪练剧本表（支持版本控制）")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-script:create')")
    public CommonResult<Long> createPracticeScript(@Valid @RequestBody PracticeScriptSaveReqVO createReqVO) {
        return success(practiceScriptService.createPracticeScript(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM智能陪练-陪练剧本表（支持版本控制）")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-script:update')")
    public CommonResult<Boolean> updatePracticeScript(@Valid @RequestBody PracticeScriptSaveReqVO updateReqVO) {
        practiceScriptService.updatePracticeScript(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM智能陪练-陪练剧本表（支持版本控制）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:practice-script:delete')")
    public CommonResult<Boolean> deletePracticeScript(@RequestParam("id") Long id) {
        practiceScriptService.deletePracticeScript(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM智能陪练-陪练剧本表（支持版本控制）")
                @PreAuthorize("@ss.hasPermission('aicrm:practice-script:delete')")
    public CommonResult<Boolean> deletePracticeScriptList(@RequestParam("ids") List<Long> ids) {
        practiceScriptService.deletePracticeScriptListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-陪练剧本表（支持版本控制）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-script:query')")
    public CommonResult<PracticeScriptRespVO> getPracticeScript(@RequestParam("id") Long id) {
        PracticeScriptDO practiceScript = practiceScriptService.getPracticeScript(id);
        return success(BeanUtils.toBean(practiceScript, PracticeScriptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-陪练剧本表（支持版本控制）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-script:query')")
    public CommonResult<PageResult<PracticeScriptRespVO>> getPracticeScriptPage(@Valid PracticeScriptPageReqVO pageReqVO) {
        PageResult<PracticeScriptDO> pageResult = practiceScriptService.getPracticeScriptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PracticeScriptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-陪练剧本表（支持版本控制） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-script:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeScriptExcel(@Valid PracticeScriptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeScriptDO> list = practiceScriptService.getPracticeScriptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-陪练剧本表（支持版本控制）.xls", "数据", PracticeScriptRespVO.class,
                        BeanUtils.toBean(list, PracticeScriptRespVO.class));
    }

}