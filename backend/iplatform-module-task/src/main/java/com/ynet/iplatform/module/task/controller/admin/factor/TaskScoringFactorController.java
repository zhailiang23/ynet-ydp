package com.ynet.iplatform.module.task.controller.admin.factor;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorBatchUpdateWeightReqVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorListReqVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorPageReqVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorRespVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorSaveReqVO;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringFactorDO;
import com.ynet.iplatform.module.task.service.factor.TaskScoringFactorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 任务评分因子
 *
 * @author ynet
 */
@Tag(name = "管理后台 - 任务评分因子")
@RestController
@RequestMapping("/task/scoring-factor")
@Validated
public class TaskScoringFactorController {

    @Resource
    private TaskScoringFactorService taskScoringFactorService;

    @PostMapping("/create")
    @Operation(summary = "创建评分因子")
    @PreAuthorize("@ss.hasPermission('task:scoring-factor:create')")
    public CommonResult<Long> createScoringFactor(@Valid @RequestBody TaskScoringFactorSaveReqVO createReqVO) {
        return success(taskScoringFactorService.createScoringFactor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评分因子")
    @PreAuthorize("@ss.hasPermission('task:scoring-factor:update')")
    public CommonResult<Boolean> updateScoringFactor(@Valid @RequestBody TaskScoringFactorSaveReqVO updateReqVO) {
        taskScoringFactorService.updateScoringFactor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评分因子")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('task:scoring-factor:delete')")
    public CommonResult<Boolean> deleteScoringFactor(@RequestParam("id") Long id) {
        taskScoringFactorService.deleteScoringFactor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获取评分因子详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('task:scoring-factor:query')")
    public CommonResult<TaskScoringFactorRespVO> getScoringFactor(@RequestParam("id") Long id) {
        // 调用新的获取详情方法，包含条件列表
        TaskScoringFactorRespVO factor = taskScoringFactorService.getScoringFactorDetail(id);
        return success(factor);
    }

    @GetMapping("/page")
    @Operation(summary = "获得评分因子分页")
    @PreAuthorize("@ss.hasPermission('task:scoring-factor:query')")
    public CommonResult<PageResult<TaskScoringFactorRespVO>> getScoringFactorPage(@Valid TaskScoringFactorPageReqVO pageReqVO) {
        PageResult<TaskScoringFactorDO> pageResult = taskScoringFactorService.getScoringFactorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskScoringFactorRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得评分因子列表")
    @PreAuthorize("@ss.hasPermission('task:scoring-factor:query')")
    public CommonResult<List<TaskScoringFactorRespVO>> getScoringFactorList(@Valid TaskScoringFactorListReqVO listReqVO) {
        List<TaskScoringFactorDO> list = taskScoringFactorService.getScoringFactorList(listReqVO);
        return success(BeanUtils.toBean(list, TaskScoringFactorRespVO.class));
    }

    @PutMapping("/batch-update-weight")
    @Operation(summary = "批量更新因子权重")
    @PreAuthorize("@ss.hasPermission('task:scoring-factor:update')")
    public CommonResult<Boolean> batchUpdateWeight(@Valid @RequestBody TaskScoringFactorBatchUpdateWeightReqVO batchUpdateReqVO) {
        taskScoringFactorService.batchUpdateWeight(batchUpdateReqVO);
        return success(true);
    }
}
