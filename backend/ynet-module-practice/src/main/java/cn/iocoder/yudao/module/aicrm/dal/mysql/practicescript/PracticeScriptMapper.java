package cn.iocoder.yudao.module.aicrm.dal.mysql.practicescript;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicescript.vo.*;

/**
 * CRM智能陪练-陪练剧本表（支持版本控制） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeScriptMapper extends BaseMapperX<PracticeScriptDO> {

    default PageResult<PracticeScriptDO> selectPage(PracticeScriptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeScriptDO>()
                .eqIfPresent(PracticeScriptDO::getScriptNo, reqVO.getScriptNo())
                .eqIfPresent(PracticeScriptDO::getVersion, reqVO.getVersion())
                .eqIfPresent(PracticeScriptDO::getIsLatest, reqVO.getIsLatest())
                .eqIfPresent(PracticeScriptDO::getParentVersionId, reqVO.getParentVersionId())
                .eqIfPresent(PracticeScriptDO::getVersionDescription, reqVO.getVersionDescription())
                .eqIfPresent(PracticeScriptDO::getStatus, reqVO.getStatus())
                .likeIfPresent(PracticeScriptDO::getName, reqVO.getName())
                .eqIfPresent(PracticeScriptDO::getDescription, reqVO.getDescription())
                .eqIfPresent(PracticeScriptDO::getDifficultyLevel, reqVO.getDifficultyLevel())
                .eqIfPresent(PracticeScriptDO::getMarketingStep, reqVO.getMarketingStep())
                .eqIfPresent(PracticeScriptDO::getCaseId, reqVO.getCaseId())
                .eqIfPresent(PracticeScriptDO::getSkillId, reqVO.getSkillId())
                .eqIfPresent(PracticeScriptDO::getVirtualCustomerId, reqVO.getVirtualCustomerId())
                .eqIfPresent(PracticeScriptDO::getMaterialIds, reqVO.getMaterialIds())
                .eqIfPresent(PracticeScriptDO::getContent, reqVO.getContent())
                .eqIfPresent(PracticeScriptDO::getContentEdit, reqVO.getContentEdit())
                .eqIfPresent(PracticeScriptDO::getUsageCount, reqVO.getUsageCount())
                .betweenIfPresent(PracticeScriptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeScriptDO::getId));
    }

}