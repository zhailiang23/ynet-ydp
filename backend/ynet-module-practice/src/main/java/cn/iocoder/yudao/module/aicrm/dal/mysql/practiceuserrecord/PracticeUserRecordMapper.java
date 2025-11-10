package cn.iocoder.yudao.module.aicrm.dal.mysql.practiceuserrecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo.*;

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

}