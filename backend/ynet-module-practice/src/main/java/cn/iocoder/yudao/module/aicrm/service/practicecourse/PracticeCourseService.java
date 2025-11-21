package cn.iocoder.yudao.module.aicrm.service.practicecourse;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-陪练课程 Service 接口
 *
 * @author 芋道源码
 */
public interface PracticeCourseService {

    /**
     * 创建CRM智能陪练-陪练课程
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeCourse(@Valid PracticeCourseSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-陪练课程
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeCourse(@Valid PracticeCourseSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-陪练课程
     *
     * @param id 编号
     */
    void deletePracticeCourse(Long id);

    /**
    * 批量删除CRM智能陪练-陪练课程
    *
    * @param ids 编号
    */
    void deletePracticeCourseListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-陪练课程
     *
     * @param id 编号
     * @return CRM智能陪练-陪练课程
     */
    PracticeCourseDO getPracticeCourse(Long id);

    /**
     * 获得CRM智能陪练-陪练课程分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-陪练课程分页
     */
    PageResult<PracticeCourseRespVO> getPracticeCoursePage(PracticeCoursePageReqVO pageReqVO);

    /**
     * 创建个性化课程
     *
     * @param name 课程名称
     * @param description 课程描述
     * @param scriptId 关联剧本ID
     * @return 创建的课程记录
     */
    PracticeCourseDO createPersonalizedCourse(String name, String description, Long scriptId);

}