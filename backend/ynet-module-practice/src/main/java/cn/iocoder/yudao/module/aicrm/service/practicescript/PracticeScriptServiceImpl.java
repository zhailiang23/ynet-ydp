package cn.iocoder.yudao.module.aicrm.service.practicescript;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicescript.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

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

    @Override
    public Long createPracticeScript(PracticeScriptSaveReqVO createReqVO) {
        // 插入
        PracticeScriptDO practiceScript = BeanUtils.toBean(createReqVO, PracticeScriptDO.class);
        practiceScriptMapper.insert(practiceScript);

        // 返回
        return practiceScript.getId();
    }

    @Override
    public void updatePracticeScript(PracticeScriptSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeScriptExists(updateReqVO.getId());
        // 更新
        PracticeScriptDO updateObj = BeanUtils.toBean(updateReqVO, PracticeScriptDO.class);
        practiceScriptMapper.updateById(updateObj);
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
    public PageResult<PracticeScriptDO> getPracticeScriptPage(PracticeScriptPageReqVO pageReqVO) {
        return practiceScriptMapper.selectPage(pageReqVO);
    }

}