package com.ynet.iplatform.module.customer.service.chatsessions;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo.*;
import com.ynet.iplatform.module.customer.dal.dataobject.chatsessions.ChatSessionsDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.customer.dal.mysql.chatsessions.ChatSessionsMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.customer.enums.ErrorCodeConstants.*;

/**
 * 会话信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ChatSessionsServiceImpl implements ChatSessionsService {

    @Resource
    private ChatSessionsMapper chatSessionsMapper;

    @Override
    public Integer createChatSessions(ChatSessionsSaveReqVO createReqVO) {
        // 插入
        ChatSessionsDO chatSessions = BeanUtils.toBean(createReqVO, ChatSessionsDO.class);
        chatSessionsMapper.insert(chatSessions);

        // 返回
        return chatSessions.getId();
    }

    @Override
    public void updateChatSessions(ChatSessionsSaveReqVO updateReqVO) {
        // 校验存在
        validateChatSessionsExists(updateReqVO.getId());
        // 更新
        ChatSessionsDO updateObj = BeanUtils.toBean(updateReqVO, ChatSessionsDO.class);
        chatSessionsMapper.updateById(updateObj);
    }

    @Override
    public void deleteChatSessions(Integer id) {
        // 校验存在
        validateChatSessionsExists(id);
        // 删除
        chatSessionsMapper.deleteById(id);
    }

    @Override
        public void deleteChatSessionsListByIds(List<Integer> ids) {
        // 删除
        chatSessionsMapper.deleteByIds(ids);
        }


    private void validateChatSessionsExists(Integer id) {
        if (chatSessionsMapper.selectById(id) == null) {
            throw exception(CHAT_SESSIONS_NOT_EXISTS);
        }
    }

    @Override
    public ChatSessionsDO getChatSessions(Integer id) {
        return chatSessionsMapper.selectById(id);
    }

    @Override
    public PageResult<ChatSessionsDO> getChatSessionsPage(ChatSessionsPageReqVO pageReqVO) {
        return chatSessionsMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ChatSessionsRespVO> getChatSessionsPageWithNames(ChatSessionsPageReqVO pageReqVO) {
        // 构建分页参数
        Page<ChatSessionsRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 查询数据
        IPage<ChatSessionsRespVO> result = chatSessionsMapper.selectPageWithNames(
                page, pageReqVO.getUserName(), pageReqVO.getAdminName(), pageReqVO.getType());
        // 转换为 PageResult
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

}