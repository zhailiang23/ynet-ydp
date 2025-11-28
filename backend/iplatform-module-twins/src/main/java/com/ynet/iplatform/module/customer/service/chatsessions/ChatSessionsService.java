package com.ynet.iplatform.module.customer.service.chatsessions;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.customer.controller.admin.chatsessions.vo.*;
import com.ynet.iplatform.module.customer.dal.dataobject.chatsessions.ChatSessionsDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 会话信息 Service 接口
 *
 * @author 芋道源码
 */
public interface ChatSessionsService {

    /**
     * 创建会话信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createChatSessions(@Valid ChatSessionsSaveReqVO createReqVO);

    /**
     * 更新会话信息
     *
     * @param updateReqVO 更新信息
     */
    void updateChatSessions(@Valid ChatSessionsSaveReqVO updateReqVO);

    /**
     * 删除会话信息
     *
     * @param id 编号
     */
    void deleteChatSessions(Integer id);

    /**
    * 批量删除会话信息
    *
    * @param ids 编号
    */
    void deleteChatSessionsListByIds(List<Integer> ids);

    /**
     * 获得会话信息
     *
     * @param id 编号
     * @return 会话信息
     */
    ChatSessionsDO getChatSessions(Integer id);

    /**
     * 获得会话信息分页
     *
     * @param pageReqVO 分页查询
     * @return 会话信息分页
     */
    PageResult<ChatSessionsDO> getChatSessionsPage(ChatSessionsPageReqVO pageReqVO);

    /**
     * 获得会话信息分页（包含用户名和客服名）
     *
     * @param pageReqVO 分页查询
     * @return 会话信息分页
     */
    PageResult<ChatSessionsRespVO> getChatSessionsPageWithNames(ChatSessionsPageReqVO pageReqVO);

}