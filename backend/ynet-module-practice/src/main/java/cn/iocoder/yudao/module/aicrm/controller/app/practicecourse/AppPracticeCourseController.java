package cn.iocoder.yudao.module.aicrm.controller.app.practicecourse;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import cn.iocoder.yudao.module.aicrm.service.practicecourse.PracticeCourseService;

@Tag(name = "APP - CRM智能陪练-陪练课程")
@RestController
@RequestMapping("/app-api/aicrm/practice-course")
@Validated
public class AppPracticeCourseController {

    @Resource
    private PracticeCourseService practiceCourseService;

    @GetMapping("/get")
    @Operation(summary = "获得CRM智能陪练-陪练课程")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<PracticeCourseRespVO> getPracticeCourse(@RequestParam("id") Long id) {
        PracticeCourseDO practiceCourse = practiceCourseService.getPracticeCourse(id);
        return success(BeanUtils.toBean(practiceCourse, PracticeCourseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得CRM智能陪练-陪练课程分页")
    @PermitAll
    public CommonResult<PageResult<PracticeCourseRespVO>> getPracticeCoursePage(@Valid PracticeCoursePageReqVO pageReqVO) {
        PageResult<PracticeCourseRespVO> pageResult = practiceCourseService.getPracticeCoursePage(pageReqVO);
        return success(pageResult);
    }

}
