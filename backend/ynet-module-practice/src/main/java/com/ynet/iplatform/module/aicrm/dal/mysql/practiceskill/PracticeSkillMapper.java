package com.ynet.iplatform.module.aicrm.dal.mysql.practiceskill;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.practiceskill.vo.*;

/**
 * CRM智能陪练-销售技巧 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeSkillMapper extends BaseMapperX<PracticeSkillDO> {

    default PageResult<PracticeSkillDO> selectPage(PracticeSkillPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeSkillDO>()
                .likeIfPresent(PracticeSkillDO::getName, reqVO.getName())
                .eqIfPresent(PracticeSkillDO::getCategory, reqVO.getCategory())
                .eqIfPresent(PracticeSkillDO::getScriptTemplate, reqVO.getScriptTemplate())
                .eqIfPresent(PracticeSkillDO::getComplianceRules, reqVO.getComplianceRules())
                .eqIfPresent(PracticeSkillDO::getRelatedProducts, reqVO.getRelatedProducts())
                .betweenIfPresent(PracticeSkillDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeSkillDO::getId));
    }

}