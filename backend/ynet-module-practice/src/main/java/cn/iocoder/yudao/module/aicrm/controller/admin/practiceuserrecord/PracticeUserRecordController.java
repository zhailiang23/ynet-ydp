package cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord;

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

import cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import cn.iocoder.yudao.module.aicrm.service.practiceuserrecord.PracticeUserRecordService;

@Tag(name = "管理后台 - CRM智能陪练-用户陪练记录")
@RestController
@RequestMapping("/aicrm/practice-user-record")
@Validated
public class PracticeUserRecordController {

    @Resource
    private PracticeUserRecordService practiceUserRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建CRM智能陪练-用户陪练记录")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:create')")
    public CommonResult<Long> createPracticeUserRecord(@Valid @RequestBody PracticeUserRecordSaveReqVO createReqVO) {
        return success(practiceUserRecordService.createPracticeUserRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新CRM智能陪练-用户陪练记录")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:update')")
    public CommonResult<Boolean> updatePracticeUserRecord(@Valid @RequestBody PracticeUserRecordSaveReqVO updateReqVO) {
        practiceUserRecordService.updatePracticeUserRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除CRM智能陪练-用户陪练记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:delete')")
    public CommonResult<Boolean> deletePracticeUserRecord(@RequestParam("id") Long id) {
        practiceUserRecordService.deletePracticeUserRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除CRM智能陪练-用户陪练记录")
                @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:delete')")
    public CommonResult<Boolean> deletePracticeUserRecordList(@RequestParam("ids") List<Long> ids) {
        practiceUserRecordService.deletePracticeUserRecordListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-用户陪练记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:query')")
    public CommonResult<PracticeUserRecordRespVO> getPracticeUserRecord(@RequestParam("id") Long id) {
        PracticeUserRecordDO practiceUserRecord = practiceUserRecordService.getPracticeUserRecord(id);
        return success(BeanUtils.toBean(practiceUserRecord, PracticeUserRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-用户陪练记录分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:query')")
    public CommonResult<PageResult<PracticeUserRecordRespVO>> getPracticeUserRecordPage(@Valid PracticeUserRecordPageReqVO pageReqVO) {
        PageResult<PracticeUserRecordDO> pageResult = practiceUserRecordService.getPracticeUserRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PracticeUserRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-用户陪练记录 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeUserRecordExcel(@Valid PracticeUserRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeUserRecordDO> list = practiceUserRecordService.getPracticeUserRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-用户陪练记录.xls", "数据", PracticeUserRecordRespVO.class,
                        BeanUtils.toBean(list, PracticeUserRecordRespVO.class));
    }

}