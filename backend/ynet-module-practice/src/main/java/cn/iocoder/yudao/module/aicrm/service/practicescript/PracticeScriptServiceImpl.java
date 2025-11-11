package cn.iocoder.yudao.module.aicrm.service.practicescript;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicescript.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;

import cn.iocoder.yudao.module.aicrm.dal.mysql.practicescript.PracticeScriptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-陪练剧本表（支持版本控制） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeScriptServiceImpl implements PracticeScriptService {

    @Resource
    private PracticeScriptMapper practiceScriptMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.practicecase.PracticeCaseMapper practiceCaseMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.practiceskill.PracticeSkillMapper practiceSkillMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.practicevirtualcustomer.PracticeVirtualCustomerMapper practiceVirtualCustomerMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.practicematerial.PracticeMaterialMapper practiceMaterialMapper;

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

        // 插入
        practiceScriptMapper.insert(practiceScript);

        // 返回
        return practiceScript.getId();
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
            List<cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO> cases =
                practiceCaseMapper.selectBatchIds(caseIds);
            for (cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO caseObj : cases) {
                caseNameMap.put(caseObj.getId(), caseObj.getTitle());
            }
        }

        Map<Long, String> skillNameMap = new HashMap<>();
        if (!skillIds.isEmpty()) {
            List<cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO> skills =
                practiceSkillMapper.selectBatchIds(skillIds);
            for (cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO skill : skills) {
                skillNameMap.put(skill.getId(), skill.getName());
            }
        }

        Map<Long, String> virtualCustomerNameMap = new HashMap<>();
        if (!virtualCustomerIds.isEmpty()) {
            List<cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO> virtualCustomers =
                practiceVirtualCustomerMapper.selectBatchIds(virtualCustomerIds);
            for (cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO customer : virtualCustomers) {
                virtualCustomerNameMap.put(customer.getId(), customer.getName());
            }
        }

        Map<Long, String> materialNameMap = new HashMap<>();
        if (!materialIds.isEmpty()) {
            List<cn.iocoder.yudao.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO> materials =
                practiceMaterialMapper.selectBatchIds(materialIds);
            for (cn.iocoder.yudao.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO material : materials) {
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

}