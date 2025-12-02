package com.ynet.iplatform.module.twins.service.chatautomessages;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.twins.controller.admin.chatautomessages.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatautomessages.ChatAutoMessagesDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户留资消息 Service 接口
 *
 * @author 易诚源码
 */
public interface ChatAutoMessagesService {

    /**
     * 创建客户留资消息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createChatAutoMessages(@Valid ChatAutoMessagesSaveReqVO createReqVO);

    /**
     * 更新客户留资消息
     *
     * @param updateReqVO 更新信息
     */
    void updateChatAutoMessages(@Valid ChatAutoMessagesSaveReqVO updateReqVO);

    /**
     * 删除客户留资消息
     *
     * @param id 编号
     */
    void deleteChatAutoMessages(Integer id);

    /**
    * 批量删除客户留资消息
    *
    * @param ids 编号
    */
    void deleteChatAutoMessagesListByIds(List<Integer> ids);

    /**
     * 获得客户留资消息
     *
     * @param id 编号
     * @return 客户留资消息
     */
    ChatAutoMessagesDO getChatAutoMessages(Integer id);

    /**
     * 获得客户留资消息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户留资消息分页
     */
    PageResult<ChatAutoMessagesDO> getChatAutoMessagesPage(ChatAutoMessagesPageReqVO pageReqVO);

}