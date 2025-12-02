package com.ynet.iplatform.module.aicrm.dal.mysql.practicecourse;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.practicecourse.vo.*;

/**
 * CRM智能陪练-陪练课程 Mapper
 *
 * @author 易诚源码
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