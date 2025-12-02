package com.ynet.iplatform.module.aicrm.service.practicescript;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import com.ynet.iplatform.module.aicrm.controller.admin.practicescript.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;

import com.ynet.iplatform.module.aicrm.dal.mysql.practicescript.PracticeScriptMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-陪练剧本表（支持版本控制） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
@Slf4j
public class PracticeScriptServiceImpl implements PracticeScriptService {

    @Resource
    private PracticeScriptMapper practiceScriptMapper;

    @Resource
    private com.ynet.iplatform.module.aicrm.dal.mysql.practicecase.PracticeCaseMapper practiceCaseMapper;

    @Resource
    private com.ynet.iplatform.module.aicrm.dal.mysql.practiceskill.PracticeSkillMapper practiceSkillMapper;

    @Resource
    private com.ynet.iplatform.module.aicrm.dal.mysql.practicevirtualcustomer.PracticeVirtualCustomerMapper practiceVirtualCustomerMapper;

    @Resource
    private com.ynet.iplatform.module.aicrm.dal.mysql.practicematerial.PracticeMaterialMapper practiceMaterialMapper;

    @Resource
    private DifyScriptGeneratorClient difyScriptGeneratorClient;

    @Override
    public Long createPracticeScript(PracticeScriptSaveReqVO createReqVO) {
        // 转换为 DO
        PracticeScriptDO practiceScript = BeanUtils.toBean(createReqVO, PracticeScriptDO.class);

        // 自动填充必填字段
        // 生成剧本编号（使用随机数）
        if (practiceScript.getScriptNo() == null) {
            practiceScript.setScriptNo(String.valueOf(System.currentTimeMillis()));
        }
        // 设置初始版本号为 1
        if (practiceScript.getVersion() == null) {
            practiceScript.setVersion("1");
        }
        // 设置为最新版本
        if (practiceScript.getIsLatest() == null) {
            practiceScript.setIsLatest(true);
        }
        // 设置状态为草稿
        if (practiceScript.getStatus() == null) {
            practiceScript.setStatus("draft");
        }
        // 设置生成状态为待生成
        if (practiceScript.getGenerationStatus() == null) {
            practiceScript.setGenerationStatus("pending");
        }

        // 插入数据库记录
        practiceScriptMapper.insert(practiceScript);

        // 同步调用 AI 服务生成剧本内容
        // 如果有材料ID和营销环节,难度等级,则触发生成
        if (practiceScript.getMaterialIds() != null && !practiceScript.getMaterialIds().isEmpty()
                && practiceScript.getMarketingStep() != null && !practiceScript.getMarketingStep().isEmpty()
                && practiceScript.getDifficultyLevel() != null && !practiceScript.getDifficultyLevel().isEmpty()) {

            log.info("同步生成剧本内容, 剧本ID: {}", practiceScript.getId());
            generateScriptContentSync(practiceScript.getId(), practiceScript.getCaseId(),
                    practiceScript.getMaterialIds(), practiceScript.getSkillId(),
                    practiceScript.getMarketingStep(), practiceScript.getDifficultyLevel(),
                    practiceScript.getDescription());
        }

        // 返回
        return practiceScript.getId();
    }

    /**
     * 同步生成剧本内容
     *
     * @param scriptId 剧本ID
     * @param caseId 案例ID (暂未使用,仅为向后兼容保留)
     * @param materialIds 材料IDs（逗号分隔,暂未使用)
     * @param skillId 技能ID (暂未使用,仅为向后兼容保留)
     * @param marketingStep 营销环节 (暂未使用,仅为向后兼容保留)
     * @param difficultyLevel 难度等级 (暂未使用,仅为向后兼容保留)
     * @param scriptDescription 剧本描述 (暂未使用,仅为向后兼容保留)
     */
    public void generateScriptContentSync(Long scriptId, Long caseId, String materialIds,
                                           Long skillId, String marketingStep,
                                           String difficultyLevel, String scriptDescription) {
        try {
            // 更新状态为生成中
            PracticeScriptDO updateScript = new PracticeScriptDO();
            updateScript.setId(scriptId);
            updateScript.setGenerationStatus("generating");
            practiceScriptMapper.updateById(updateScript);

            log.info("开始生成剧本内容, 剧本ID: {}", scriptId);

            // 调用 Dify 服务生成剧本(只传剧本ID,Dify服务会自己查询所有信息)
            String scriptContent = difyScriptGeneratorClient.generateScript(scriptId);

            // 更新剧本内容和状态
            updateScript = new PracticeScriptDO();
            updateScript.setId(scriptId);
            updateScript.setContent(scriptContent);
            updateScript.setGenerationStatus("completed");
            updateScript.setContentSource("ai_generated");
            practiceScriptMapper.updateById(updateScript);

            log.info("剧本内容生成成功, 剧本ID: {}, 内容长度: {}", scriptId, scriptContent.length());

        } catch (Exception e) {
            log.error("生成剧本内容失败, 剧本ID: {}", scriptId, e);

            // 更新状态为失败
            PracticeScriptDO updateScript = new PracticeScriptDO();
            updateScript.setId(scriptId);
            updateScript.setGenerationStatus("failed");
            practiceScriptMapper.updateById(updateScript);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePracticeScript(PracticeScriptSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeScriptExists(updateReqVO.getId());

        // 获取原剧本版本
        PracticeScriptDO oldScript = practiceScriptMapper.selectById(updateReqVO.getId());

        // 将原版本的 isLatest 设置为 false
        PracticeScriptDO updateOld = new PracticeScriptDO();
        updateOld.setId(oldScript.getId());
        updateOld.setIsLatest(false);
        practiceScriptMapper.updateById(updateOld);

        // 创建新版本
        PracticeScriptDO newScript = BeanUtils.toBean(updateReqVO, PracticeScriptDO.class);
        newScript.setId(null); // 清空 ID,让数据库自动生成新 ID
        newScript.setScriptNo(oldScript.getScriptNo()); // 保持剧本编号不变
        newScript.setParentVersionId(oldScript.getId()); // 设置父版本 ID
        newScript.setIsLatest(true); // 设置为最新版本

        // 版本号递增
        String oldVersion = oldScript.getVersion();
        Integer versionNum;
        try {
            versionNum = Integer.parseInt(oldVersion);
        } catch (NumberFormatException e) {
            // 如果版本号不是纯数字,默认从 1 开始
            versionNum = 1;
        }
        newScript.setVersion(String.valueOf(versionNum + 1));

        // 插入新版本
        practiceScriptMapper.insert(newScript);
    }

    @Override
    public void deletePracticeScript(Long id) {
        // 校验存在
        validatePracticeScriptExists(id);
        // 删除
        practiceScriptMapper.deleteById(id);
    }

    @Override
        public void deletePracticeScriptListByIds(List<Long> ids) {
        // 删除
        practiceScriptMapper.deleteByIds(ids);
        }


    private void validatePracticeScriptExists(Long id) {
        if (practiceScriptMapper.selectById(id) == null) {
            throw exception(PRACTICE_SCRIPT_NOT_EXISTS);
        }
    }

    @Override
    public PracticeScriptDO getPracticeScript(Long id) {
        return practiceScriptMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeScriptRespVO> getPracticeScriptPage(PracticeScriptPageReqVO pageReqVO) {
        // 查询分页数据
        PageResult<PracticeScriptDO> pageResult = practiceScriptMapper.selectPage(pageReqVO);

        // 转换为 VO
        PageResult<PracticeScriptRespVO> voPageResult = BeanUtils.toBean(pageResult, PracticeScriptRespVO.class);

        // 收集所有的关联 ID
        Set<Long> caseIds = new HashSet<>();
        Set<Long> skillIds = new HashSet<>();
        Set<Long> virtualCustomerIds = new HashSet<>();
        Set<Long> materialIds = new HashSet<>();

        for (PracticeScriptRespVO vo : voPageResult.getList()) {
            if (vo.getCaseId() != null) {
                caseIds.add(vo.getCaseId());
            }
            if (vo.getSkillId() != null) {
                skillIds.add(vo.getSkillId());
            }
            if (vo.getVirtualCustomerId() != null) {
                virtualCustomerIds.add(vo.getVirtualCustomerId());
            }
            // materialIds 是逗号分隔的字符串，需要拆分
            if (vo.getMaterialIds() != null && !vo.getMaterialIds().isEmpty()) {
                String[] ids = vo.getMaterialIds().split(",");
                for (String id : ids) {
                    try {
                        materialIds.add(Long.parseLong(id.trim()));
                    } catch (NumberFormatException e) {
                        // 忽略无效的 ID
                    }
                }
            }
        }

        // 批量查询关联对象
        Map<Long, String> caseNameMap = new HashMap<>();
        if (!caseIds.isEmpty()) {
            List<com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO> cases =
                practiceCaseMapper.selectBatchIds(caseIds);
            for (com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO caseObj : cases) {
                caseNameMap.put(caseObj.getId(), caseObj.getTitle());
            }
        }

        Map<Long, String> skillNameMap = new HashMap<>();
        if (!skillIds.isEmpty()) {
            List<com.ynet.iplatform.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO> skills =
                practiceSkillMapper.selectBatchIds(skillIds);
            for (com.ynet.iplatform.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO skill : skills) {
                skillNameMap.put(skill.getId(), skill.getName());
            }
        }

        Map<Long, String> virtualCustomerNameMap = new HashMap<>();
        if (!virtualCustomerIds.isEmpty()) {
            List<com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO> virtualCustomers =
                practiceVirtualCustomerMapper.selectBatchIds(virtualCustomerIds);
            for (com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO customer : virtualCustomers) {
                virtualCustomerNameMap.put(customer.getId(), customer.getName());
            }
        }

        Map<Long, String> materialNameMap = new HashMap<>();
        if (!materialIds.isEmpty()) {
            List<com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO> materials =
                practiceMaterialMapper.selectBatchIds(materialIds);
            for (com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO material : materials) {
                materialNameMap.put(material.getId(), material.getName());
            }
        }

        // 填充关联对象名称
        for (PracticeScriptRespVO vo : voPageResult.getList()) {
            // 填充案例名称
            if (vo.getCaseId() != null) {
                vo.setCaseName(caseNameMap.get(vo.getCaseId()));
            }
            // 填充技巧名称
            if (vo.getSkillId() != null) {
                vo.setSkillName(skillNameMap.get(vo.getSkillId()));
            }
            // 填充虚拟客户名称
            if (vo.getVirtualCustomerId() != null) {
                vo.setVirtualCustomerName(virtualCustomerNameMap.get(vo.getVirtualCustomerId()));
            }
            // 填充培训文件名称（多个文件名用逗号连接）
            if (vo.getMaterialIds() != null && !vo.getMaterialIds().isEmpty()) {
                String[] ids = vo.getMaterialIds().split(",");
                List<String> names = new ArrayList<>();
                for (String id : ids) {
                    try {
                        Long materialId = Long.parseLong(id.trim());
                        String name = materialNameMap.get(materialId);
                        if (name != null) {
                            names.add(name);
                        }
                    } catch (NumberFormatException e) {
                        // 忽略无效的 ID
                    }
                }
                vo.setMaterialNames(String.join(", ", names));
            }
        }

        return voPageResult;
    }

    @Override
    public List<PracticeScriptRespVO> getVersionHistory(String scriptNo) {
        // 查询该剧本编号的所有版本,按版本号倒序排列
        List<PracticeScriptDO> scriptList = practiceScriptMapper.selectList(
            new LambdaQueryWrapperX<PracticeScriptDO>()
                .eq(PracticeScriptDO::getScriptNo, scriptNo)
                .orderByDesc(PracticeScriptDO::getCreateTime)
        );

        // 转换为 VO 并填充关联信息
        List<PracticeScriptRespVO> result = BeanUtils.toBean(scriptList, PracticeScriptRespVO.class);

        // 填充关联的案例、技巧、虚拟客户名称
        Set<Long> caseIds = new HashSet<>();
        Set<Long> skillIds = new HashSet<>();
        Set<Long> vcustomerIds = new HashSet<>();

        for (PracticeScriptDO script : scriptList) {
            if (script.getCaseId() != null) {
                caseIds.add(script.getCaseId());
            }
            if (script.getSkillId() != null) {
                skillIds.add(script.getSkillId());
            }
            if (script.getVirtualCustomerId() != null) {
                vcustomerIds.add(script.getVirtualCustomerId());
            }
        }

        // 批量查询关联数据
        Map<Long, String> caseNameMap = new HashMap<>();
        if (!caseIds.isEmpty()) {
            List<PracticeCaseDO> caseList = practiceCaseMapper.selectBatchIds(caseIds);
            caseNameMap = caseList.stream()
                .collect(Collectors.toMap(PracticeCaseDO::getId, PracticeCaseDO::getTitle));
        }

        Map<Long, String> skillNameMap = new HashMap<>();
        if (!skillIds.isEmpty()) {
            List<PracticeSkillDO> skillList = practiceSkillMapper.selectBatchIds(skillIds);
            skillNameMap = skillList.stream()
                .collect(Collectors.toMap(PracticeSkillDO::getId, PracticeSkillDO::getName));
        }

        Map<Long, String> vcustomerNameMap = new HashMap<>();
        if (!vcustomerIds.isEmpty()) {
            List<PracticeVirtualCustomerDO> vcustomerList = practiceVirtualCustomerMapper.selectBatchIds(vcustomerIds);
            vcustomerNameMap = vcustomerList.stream()
                .collect(Collectors.toMap(PracticeVirtualCustomerDO::getId, PracticeVirtualCustomerDO::getName));
        }

        // 设置关联名称
        for (int i = 0; i < result.size(); i++) {
            PracticeScriptRespVO vo = result.get(i);
            PracticeScriptDO script = scriptList.get(i);

            if (script.getCaseId() != null) {
                vo.setCaseName(caseNameMap.get(script.getCaseId()));
            }
            if (script.getSkillId() != null) {
                vo.setSkillName(skillNameMap.get(script.getSkillId()));
            }
            if (script.getVirtualCustomerId() != null) {
                vo.setVirtualCustomerName(vcustomerNameMap.get(script.getVirtualCustomerId()));
            }
        }

        return result;
    }

    @Override
    public PracticeScriptDO createPersonalizedScript(String name, String description, String difficultyLevel,
                                                       String marketingStep, Long virtualCustomerId,
                                                       String materialIds, Long caseId, Long skillId,
                                                       String content) {
        // 创建剧本记录
        PracticeScriptDO script = new PracticeScriptDO();
        script.setName(name);
        script.setDescription(description);
        script.setDifficultyLevel(difficultyLevel);
        script.setMarketingStep(marketingStep);
        script.setVirtualCustomerId(virtualCustomerId);
        script.setMaterialIds(materialIds);
        script.setCaseId(caseId);
        script.setSkillId(skillId);

        // 如果提供了 content 则直接使用(向后兼容)
        // 否则将通过异步生成
        if (content != null && !content.isEmpty()) {
            script.setContent(content);
            script.setGenerationStatus("completed");
            script.setContentSource("manual");
        } else {
            script.setGenerationStatus("pending");
        }

        // 自动填充版本信息
        script.setScriptNo(String.valueOf(System.currentTimeMillis()));
        script.setVersion("1");
        script.setIsLatest(true);
        script.setStatus("draft");  // 改为 draft 状态,与 createPracticeScript 保持一致

        // 插入数据库
        practiceScriptMapper.insert(script);

        // 如果没有提供内容且满足生成条件,则同步生成剧本内容
        if ((content == null || content.isEmpty())
                && materialIds != null && !materialIds.isEmpty()
                && marketingStep != null && !marketingStep.isEmpty()
                && difficultyLevel != null && !difficultyLevel.isEmpty()) {

            log.info("个性化剧本同步生成, 剧本ID: {}", script.getId());
            generateScriptContentSync(script.getId(), caseId, materialIds, skillId,
                    marketingStep, difficultyLevel, description);
        }

        return script;
    }

}