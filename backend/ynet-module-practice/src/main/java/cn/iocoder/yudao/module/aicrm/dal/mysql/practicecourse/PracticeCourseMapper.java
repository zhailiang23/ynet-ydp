package cn.iocoder.yudao.module.aicrm.dal.mysql.practicecourse;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicecourse.vo.*;

/**
 * CRM智能陪练-陪练课程 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeCourseMapper extends BaseMapperX<PracticeCourseDO> {

    default PageResult<PracticeCourseDO> selectPage(PracticeCoursePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeCourseDO>()
                .likeIfPresent(PracticeCourseDO::getName, reqVO.getName())
                .eqIfPresent(PracticeCourseDO::getDescription, reqVO.getDescription())
                .eqIfPresent(PracticeCourseDO::getScriptId, reqVO.getScriptId())
                .eqIfPresent(PracticeCourseDO::getStandard, reqVO.getStandard())
                .eqIfPresent(PracticeCourseDO::getHard, reqVO.getHard())
                .eqIfPresent(PracticeCourseDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PracticeCourseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeCourseDO::getId));
    }

}