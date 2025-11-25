package com.ynet.iplatform.module.aicrm.dal.mysql.practiceuserrecord;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.practiceuserrecord.vo.*;

/**
 * CRM智能陪练-用户陪练记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeUserRecordMapper extends BaseMapperX<PracticeUserRecordDO> {

    default PageResult<PracticeUserRecordDO> selectPage(PracticeUserRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeUserRecordDO>()
                .eqIfPresent(PracticeUserRecordDO::getCourseId, reqVO.getCourseId())
                .eqIfPresent(PracticeUserRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PracticeUserRecordDO::getVcustomerId, reqVO.getVcustomerId())
                .eqIfPresent(PracticeUserRecordDO::getRecordNo, reqVO.getRecordNo())
                .eqIfPresent(PracticeUserRecordDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PracticeUserRecordDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(PracticeUserRecordDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(PracticeUserRecordDO::getDuration, reqVO.getDuration())
                .eqIfPresent(PracticeUserRecordDO::getTotalScore, reqVO.getTotalScore())
                .eqIfPresent(PracticeUserRecordDO::getDimensionScores, reqVO.getDimensionScores())
                .eqIfPresent(PracticeUserRecordDO::getCompletionRate, reqVO.getCompletionRate())
                .eqIfPresent(PracticeUserRecordDO::getAiSummary, reqVO.getAiSummary())
                .eqIfPresent(PracticeUserRecordDO::getStrengths, reqVO.getStrengths())
                .eqIfPresent(PracticeUserRecordDO::getWeaknesses, reqVO.getWeaknesses())
                .eqIfPresent(PracticeUserRecordDO::getRecommendations, reqVO.getRecommendations())
                .betweenIfPresent(PracticeUserRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeUserRecordDO::getId));
    }

    /**
     * 查询用户未完成的练习记录
     *
     * @param courseId 课程ID
     * @param vcustomerId 虚拟客户ID
     * @param userId 用户ID
     * @return 未完成的练习记录，如果不存在返回 null
     */
    default PracticeUserRecordDO findUnfinishedRecord(Long courseId, Long vcustomerId, Long userId) {
        return selectOne(new LambdaQueryWrapperX<PracticeUserRecordDO>()
                .eq(PracticeUserRecordDO::getCourseId, courseId)
                .eq(PracticeUserRecordDO::getVcustomerId, vcustomerId)
                .eq(PracticeUserRecordDO::getUserId, userId)
                .eq(PracticeUserRecordDO::getStatus, "in_progress")
                .orderByDesc(PracticeUserRecordDO::getId)
                .last("LIMIT 1"));
    }

}