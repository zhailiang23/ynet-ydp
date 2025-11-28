package com.ynet.iplatform.module.twins.service.chatcollecttemplate;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.twins.controller.admin.chatcollecttemplate.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollecttemplate.ChatCollectTemplateDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.twins.dal.mysql.chatcollecttemplate.ChatCollectTemplateMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.twins.enums.ErrorCodeConstants.*;

/**
 * 客户留资模板 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ChatCollectTemplateServiceImpl implements ChatCollectTemplateService {

    @Resource
    private ChatCollectTemplateMapper chatCollectTemplateMapper;

    @Override
    public Integer createChatCollectTemplate(ChatCollectTemplateSaveReqVO createReqVO) {
        // 插入
        ChatCollectTemplateDO chatCollectTemplate = BeanUtils.toBean(createReqVO, ChatCollectTemplateDO.class);
        chatCollectTemplateMapper.insert(chatCollectTemplate);

        // 返回
        return chatCollectTemplate.getId();
    }

    @Override
    public void updateChatCollectTemplate(ChatCollectTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateChatCollectTemplateExists(updateReqVO.getId());
        // 更新
        ChatCollectTemplateDO updateObj = BeanUtils.toBean(updateReqVO, ChatCollectTemplateDO.class);
        chatCollectTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteChatCollectTemplate(Integer id) {
        // 校验存在
        validateChatCollectTemplateExists(id);
        // 删除
        chatCollectTemplateMapper.deleteById(id);
    }

    @Override
        public void deleteChatCollectTemplateListByIds(List<Integer> ids) {
        // 删除
        chatCollectTemplateMapper.deleteByIds(ids);
        }


    private void validateChatCollectTemplateExists(Integer id) {
        if (chatCollectTemplateMapper.selectById(id) == null) {
            throw exception(CHAT_COLLECT_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public ChatCollectTemplateDO getChatCollectTemplate(Integer id) {
        return chatCollectTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<ChatCollectTemplateDO> getChatCollectTemplatePage(ChatCollectTemplatePageReqVO pageReqVO) {
        return chatCollectTemplateMapper.selectPage(pageReqVO);
    }

}