package cn.iocoder.yudao.module.aicrm.dal.mysql.practiceskill;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceskill.vo.*;

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