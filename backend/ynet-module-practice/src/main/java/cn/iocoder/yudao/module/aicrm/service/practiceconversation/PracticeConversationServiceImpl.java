package cn.iocoder.yudao.module.aicrm.service.practiceconversation;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceconversation.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceconversation.PracticeConversationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.practiceconversation.PracticeConversationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-陪练对话 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeConversationServiceImpl implements PracticeConversationService {

    @Resource
    private PracticeConversationMapper practiceConversationMapper;

    @Override
    public Long createPracticeConversation(PracticeConversationSaveReqVO createReqVO) {
        // 插入
        PracticeConversationDO practiceConversation = BeanUtils.toBean(createReqVO, PracticeConversationDO.class);
        practiceConversationMapper.insert(practiceConversation);

        // 返回
        return practiceConversation.getId();
    }

    @Override
    public void updatePracticeConversation(PracticeConversationSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeConversationExists(updateReqVO.getId());
        // 更新
        PracticeConversationDO updateObj = BeanUtils.toBean(updateReqVO, PracticeConversationDO.class);
        practiceConversationMapper.updateById(updateObj);
    }

    @Override
    public void deletePracticeConversation(Long id) {
        // 校验存在
        validatePracticeConversationExists(id);
        // 删除
        practiceConversationMapper.deleteById(id);
    }

    @Override
        public void deletePracticeConversationListByIds(List<Long> ids) {
        // 删除
        practiceConversationMapper.deleteByIds(ids);
        }


    private void validatePracticeConversationExists(Long id) {
        if (practiceConversationMapper.selectById(id) == null) {
            throw exception(PRACTICE_CONVERSATION_NOT_EXISTS);
        }
    }

    @Override
    public PracticeConversationDO getPracticeConversation(Long id) {
        return practiceConversationMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeConversationDO> getPracticeConversationPage(PracticeConversationPageReqVO pageReqVO) {
        return practiceConversationMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PracticeConversationDO> getConversationListByRecordId(Long recordId) {
        return practiceConversationMapper.selectListByRecordId(recordId);
    }

}