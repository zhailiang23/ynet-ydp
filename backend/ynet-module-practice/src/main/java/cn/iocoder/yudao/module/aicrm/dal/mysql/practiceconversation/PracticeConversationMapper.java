package cn.iocoder.yudao.module.aicrm.dal.mysql.practiceconversation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceconversation.PracticeConversationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceconversation.vo.*;

/**
 * CRM智能陪练-陪练对话 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeConversationMapper extends BaseMapperX<PracticeConversationDO> {

    default PageResult<PracticeConversationDO> selectPage(PracticeConversationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeConversationDO>()
                .eqIfPresent(PracticeConversationDO::getRecordId, reqVO.getRecordId())
                .eqIfPresent(PracticeConversationDO::getSequenceNo, reqVO.getSequenceNo())
                .eqIfPresent(PracticeConversationDO::getRole, reqVO.getRole())
                .eqIfPresent(PracticeConversationDO::getMessageContent, reqVO.getMessageContent())
                .betweenIfPresent(PracticeConversationDO::getMessageTime, reqVO.getMessageTime())
                .eqIfPresent(PracticeConversationDO::getInstantScore, reqVO.getInstantScore())
                .eqIfPresent(PracticeConversationDO::getSpeechAnalysis, reqVO.getSpeechAnalysis())
                .eqIfPresent(PracticeConversationDO::getSkillEvaluation, reqVO.getSkillEvaluation())
                .eqIfPresent(PracticeConversationDO::getEmotionTag, reqVO.getEmotionTag())
                .eqIfPresent(PracticeConversationDO::getSalesIntent, reqVO.getSalesIntent())
                .eqIfPresent(PracticeConversationDO::getCustomerReaction, reqVO.getCustomerReaction())
                .eqIfPresent(PracticeConversationDO::getImprovementSuggestions, reqVO.getImprovementSuggestions())
                .eqIfPresent(PracticeConversationDO::getRecommendedScripts, reqVO.getRecommendedScripts())
                .betweenIfPresent(PracticeConversationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeConversationDO::getId));
    }

    /**
     * 根据陪练记录ID查询对话列表,按序号升序排序
     */
    default List<PracticeConversationDO> selectListByRecordId(Long recordId) {
        return selectList(new LambdaQueryWrapperX<PracticeConversationDO>()
                .eq(PracticeConversationDO::getRecordId, recordId)
                .orderByAsc(PracticeConversationDO::getSequenceNo));
    }

}