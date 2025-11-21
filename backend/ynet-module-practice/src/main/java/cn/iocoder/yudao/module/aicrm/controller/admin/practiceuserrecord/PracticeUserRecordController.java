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
import java.util.stream.Collectors;
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
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import cn.iocoder.yudao.module.aicrm.service.practiceuserrecord.PracticeUserRecordService;
import cn.iocoder.yudao.module.aicrm.service.practicecourse.PracticeCourseService;
import cn.iocoder.yudao.module.aicrm.service.practicevirtualcustomer.PracticeVirtualCustomerService;
import cn.iocoder.yudao.module.aicrm.service.practiceevaluation.PracticeEvaluationService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;

@Tag(name = "管理后台 - CRM智能陪练-用户陪练记录")
@RestController
@RequestMapping("/aicrm/practice-user-record")
@Validated
public class PracticeUserRecordController {

    @Resource
    private PracticeUserRecordService practiceUserRecordService;

    @Resource
    private PracticeCourseService practiceCourseService;

    @Resource
    private PracticeVirtualCustomerService practiceVirtualCustomerService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private PracticeEvaluationService practiceEvaluationService;

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
        if (practiceUserRecord == null) {
            return success(null);
        }

        PracticeUserRecordRespVO respVO = BeanUtils.toBean(practiceUserRecord, PracticeUserRecordRespVO.class);

        // 填充关联数据的名称
        enrichRecordNames(Collections.singletonList(respVO));

        return success(respVO);
    }

    @GetMapping("/find-unfinished")
    @Operation(summary = "查询用户未完成的练习记录")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:query')")
    public CommonResult<PracticeUserRecordRespVO> findUnfinishedRecord(
            @RequestParam("courseId") Long courseId,
            @RequestParam("vcustomerId") Long vcustomerId,
            @RequestParam("userId") Long userId) {
        PracticeUserRecordDO record = practiceUserRecordService.findUnfinishedRecord(courseId, vcustomerId, userId);
        if (record == null) {
            return success(null);
        }
        return success(BeanUtils.toBean(record, PracticeUserRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-用户陪练记录分页")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:query')")
    public CommonResult<PageResult<PracticeUserRecordRespVO>> getPracticeUserRecordPage(@Valid PracticeUserRecordPageReqVO pageReqVO) {
        PageResult<PracticeUserRecordDO> pageResult = practiceUserRecordService.getPracticeUserRecordPage(pageReqVO);
        PageResult<PracticeUserRecordRespVO> voPageResult = BeanUtils.toBean(pageResult, PracticeUserRecordRespVO.class);

        // 填充关联数据的名称
        enrichRecordNames(voPageResult.getList());

        return success(voPageResult);
    }

    /**
     * 填充记录的关联名称
     */
    private void enrichRecordNames(List<PracticeUserRecordRespVO> records) {
        if (records == null || records.isEmpty()) {
            return;
        }

        // 收集所有需要查询的ID
        Set<Long> courseIds = records.stream().map(PracticeUserRecordRespVO::getCourseId).collect(Collectors.toSet());
        Set<Long> userIds = records.stream().map(PracticeUserRecordRespVO::getUserId).collect(Collectors.toSet());
        Set<Long> vcustomerIds = records.stream().map(PracticeUserRecordRespVO::getVcustomerId).collect(Collectors.toSet());

        // 批量查询课程
        Map<Long, String> courseNameMap = new HashMap<>();
        Map<Long, Long> courseTypeMap = new HashMap<>();
        for (Long courseId : courseIds) {
            PracticeCourseDO course = practiceCourseService.getPracticeCourse(courseId);
            if (course != null) {
                courseNameMap.put(courseId, course.getName());
                courseTypeMap.put(courseId, course.getStandard());
            }
        }

        // 批量查询虚拟用户
        Map<Long, String> vcustomerNameMap = new HashMap<>();
        for (Long vcustomerId : vcustomerIds) {
            PracticeVirtualCustomerDO vcustomer = practiceVirtualCustomerService.getPracticeVirtualCustomer(vcustomerId);
            if (vcustomer != null) {
                vcustomerNameMap.put(vcustomerId, vcustomer.getName());
            }
        }

        // 批量查询用户
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);

        // 填充名称
        for (PracticeUserRecordRespVO record : records) {
            record.setCourseName(courseNameMap.get(record.getCourseId()));
            record.setCourseType(courseTypeMap.get(record.getCourseId()));
            record.setVcustomerName(vcustomerNameMap.get(record.getVcustomerId()));
            AdminUserRespDTO user = userMap.get(record.getUserId());
            if (user != null) {
                record.setUserName(user.getNickname());
            }
        }
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出CRM智能陪练-用户陪练记录 Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPracticeUserRecordExcel(@Valid PracticeUserRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PracticeUserRecordDO> list = practiceUserRecordService.getPracticeUserRecordPage(pageReqVO).getList();
        List<PracticeUserRecordRespVO> voList = BeanUtils.toBean(list, PracticeUserRecordRespVO.class);

        // 填充关联数据的名称
        enrichRecordNames(voList);

        // 导出 Excel
        ExcelUtils.write(response, "CRM智能陪练-用户陪练记录.xls", "数据", PracticeUserRecordRespVO.class, voList);
    }

    @GetMapping("/evaluate")
    @Operation(summary = "评估练习结果")
    @Parameter(name = "recordId", description = "练习记录ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:practice-user-record:query')")
    public CommonResult<PracticeEvaluationRespVO> evaluatePractice(@RequestParam("recordId") Long recordId) {
        PracticeEvaluationRespVO evaluation = practiceEvaluationService.evaluatePractice(recordId);
        return success(evaluation);
    }

}