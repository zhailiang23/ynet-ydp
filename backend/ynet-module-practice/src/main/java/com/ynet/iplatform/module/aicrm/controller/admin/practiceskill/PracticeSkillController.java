package com.ynet.iplatform.module.aicrm.controller.admin.practiceskill;

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

import com.ynet.iplatform.module.aicrm.controller.admin.practiceskill.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import com.ynet.iplatform.module.aicrm.service.practiceskill.PracticeSkillService;

@Tag(name = "管理后台 - CRM智能陪练-销售技巧")
@RestController
@RequestMapping("/aicrm/practice-skill")
@Validated
public class PracticeSkillController {

    @Resource
    private PracticeSkillService practiceSkillService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM智能陪练-销售技巧")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-skill:create')")
    public CommonResult<Long> createPracticeSkill(@Valid @RequestBody PracticeSkillSaveReqVO createReqVO) {
        return success(practiceSkillService.createPracticeSkill(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM智能陪练-销售技巧")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-skill:update')")
    public CommonResult<Boolean> updatePracticeSkill(@Valid @RequestBody PracticeSkillSaveReqVO updateReqVO) {
        practiceSkillService.updatePracticeSkill(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM智能陪练-销售技巧")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:practice-skill:delete')")
    public CommonResult<Boolean> deletePracticeSkill(@RequestParam("id") Long id) {
        practiceSkillService.deletePracticeSkill(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM智能陪练-销售技巧")
                @PreAuthorize("@ss.hasPermission('aicrm:practice-skill:delete')")
    public CommonResult<Boolean> deletePracticeSkillList(@RequestParam("ids") List<Long> ids) {
        practiceSkillService.deletePracticeSkillListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-销售技巧")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-skill:query')")
    public CommonResult<PracticeSkillRespVO> getPracticeSkill(@RequestParam("id") Long id) {
        PracticeSkillDO practiceSkill = practiceSkillService.getPracticeSkill(id);
        return success(BeanUtils.toBean(practiceSkill, PracticeSkillRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-销售技巧分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-skill:query')")
    public CommonResult<PageResult<PracticeSkillRespVO>> getPracticeSkillPage(@Valid PracticeSkillPageReqVO pageReqVO) {
        PageResult<PracticeSkillRespVO> pageResult = practiceSkillService.getPracticeSkillPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-销售技巧 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-skill:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeSkillExcel(@Valid PracticeSkillPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeSkillRespVO> list = practiceSkillService.getPracticeSkillPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-销售技巧.xls", "数据", PracticeSkillRespVO.class, list);
    }

}