package com.ynet.iplatform.module.aicrm.service.practiceskill;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practiceskill.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.practiceskill.PracticeSkillMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicematerial.PracticeMaterialMapper;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

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

    @Resource
    private PracticeMaterialMapper practiceMaterialMapper;

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
    public PageResult<PracticeSkillRespVO> getPracticeSkillPage(PracticeSkillPageReqVO pageReqVO) {
        // 查询分页数据
        PageResult<PracticeSkillDO> pageResult = practiceSkillMapper.selectPage(pageReqVO);

        // 转换为 VO
        PageResult<PracticeSkillRespVO> voPageResult = BeanUtils.toBean(pageResult, PracticeSkillRespVO.class);

        // 收集所有的文件 ID
        Set<Long> materialIds = new HashSet<>();
        for (PracticeSkillRespVO vo : voPageResult.getList()) {
            if (vo.getComplianceRules() != null) {
                materialIds.add(vo.getComplianceRules());
            }
            if (vo.getRelatedProducts() != null) {
                materialIds.add(vo.getRelatedProducts());
            }
        }

        // 批量查询培训文件
        Map<Long, String> materialNameMap = new HashMap<>();
        if (!materialIds.isEmpty()) {
            List<PracticeMaterialDO> materials = practiceMaterialMapper.selectBatchIds(materialIds);
            for (PracticeMaterialDO material : materials) {
                materialNameMap.put(material.getId(), material.getName());
            }
        }

        // 填充文件名称
        for (PracticeSkillRespVO vo : voPageResult.getList()) {
            if (vo.getComplianceRules() != null) {
                vo.setComplianceRulesName(materialNameMap.get(vo.getComplianceRules()));
            }
            if (vo.getRelatedProducts() != null) {
                vo.setRelatedProductsName(materialNameMap.get(vo.getRelatedProducts()));
            }
        }

        return voPageResult;
    }

}