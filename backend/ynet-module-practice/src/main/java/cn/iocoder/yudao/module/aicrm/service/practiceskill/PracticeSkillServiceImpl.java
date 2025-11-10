package cn.iocoder.yudao.module.aicrm.service.practiceskill;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceskill.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.practiceskill.PracticeSkillMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-销售技巧 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeSkillServiceImpl implements PracticeSkillService {

    @Resource
    private PracticeSkillMapper practiceSkillMapper;

    @Override
    public Long createPracticeSkill(PracticeSkillSaveReqVO createReqVO) {
        // 插入
        PracticeSkillDO practiceSkill = BeanUtils.toBean(createReqVO, PracticeSkillDO.class);
        practiceSkillMapper.insert(practiceSkill);

        // 返回
        return practiceSkill.getId();
    }

    @Override
    public void updatePracticeSkill(PracticeSkillSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeSkillExists(updateReqVO.getId());
        // 更新
        PracticeSkillDO updateObj = BeanUtils.toBean(updateReqVO, PracticeSkillDO.class);
        practiceSkillMapper.updateById(updateObj);
    }

    @Override
    public void deletePracticeSkill(Long id) {
        // 校验存在
        validatePracticeSkillExists(id);
        // 删除
        practiceSkillMapper.deleteById(id);
    }

    @Override
        public void deletePracticeSkillListByIds(List<Long> ids) {
        // 删除
        practiceSkillMapper.deleteByIds(ids);
        }


    private void validatePracticeSkillExists(Long id) {
        if (practiceSkillMapper.selectById(id) == null) {
            throw exception(PRACTICE_SKILL_NOT_EXISTS);
        }
    }

    @Override
    public PracticeSkillDO getPracticeSkill(Long id) {
        return practiceSkillMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeSkillDO> getPracticeSkillPage(PracticeSkillPageReqVO pageReqVO) {
        return practiceSkillMapper.selectPage(pageReqVO);
    }

}