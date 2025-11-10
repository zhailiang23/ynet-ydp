package cn.iocoder.yudao.module.aicrm.service.practicecourse;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.practicecourse.PracticeCourseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-陪练课程 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeCourseServiceImpl implements PracticeCourseService {

    @Resource
    private PracticeCourseMapper practiceCourseMapper;

    @Override
    public Long createPracticeCourse(PracticeCourseSaveReqVO createReqVO) {
        // 插入
        PracticeCourseDO practiceCourse = BeanUtils.toBean(createReqVO, PracticeCourseDO.class);
        practiceCourseMapper.insert(practiceCourse);

        // 返回
        return practiceCourse.getId();
    }

    @Override
    public void updatePracticeCourse(PracticeCourseSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeCourseExists(updateReqVO.getId());
        // 更新
        PracticeCourseDO updateObj = BeanUtils.toBean(updateReqVO, PracticeCourseDO.class);
        practiceCourseMapper.updateById(updateObj);
    }

    @Override
    public void deletePracticeCourse(Long id) {
        // 校验存在
        validatePracticeCourseExists(id);
        // 删除
        practiceCourseMapper.deleteById(id);
    }

    @Override
        public void deletePracticeCourseListByIds(List<Long> ids) {
        // 删除
        practiceCourseMapper.deleteByIds(ids);
        }


    private void validatePracticeCourseExists(Long id) {
        if (practiceCourseMapper.selectById(id) == null) {
            throw exception(PRACTICE_COURSE_NOT_EXISTS);
        }
    }

    @Override
    public PracticeCourseDO getPracticeCourse(Long id) {
        return practiceCourseMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeCourseDO> getPracticeCoursePage(PracticeCoursePageReqVO pageReqVO) {
        return practiceCourseMapper.selectPage(pageReqVO);
    }

}