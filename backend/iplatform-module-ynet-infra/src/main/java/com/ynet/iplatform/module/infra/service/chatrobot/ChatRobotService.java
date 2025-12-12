package com.ynet.iplatform.module.infra.service.chatrobot;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.*;
import com.ynet.iplatform.module.infra.dal.dataobject.chatrobot.ChatRobotDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 对话机器人管理 Service 接口
 *
 * @author 易诚源码
 */
public interface ChatRobotService {

    /**
     * 创建对话机器人管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChatRobot(@Valid ChatRobotSaveReqVO createReqVO);

    /**
     * 更新对话机器人管理
     *
     * @param updateReqVO 更新信息
     */
    void updateChatRobot(@Valid ChatRobotSaveReqVO updateReqVO);

    /**
     * 删除对话机器人管理
     *
     * @param id 编号
     */
    void deleteChatRobot(Long id);

    /**
    * 批量删除对话机器人管理
    *
    * @param ids 编号
    */
    void deleteChatRobotListByIds(List<Long> ids);

    /**
     * 获得对话机器人管理
     *
     * @param id 编号
     * @return 对话机器人管理
     */
    ChatRobotDO getChatRobot(Long id);

    /**
     * 获得对话机器人管理分页
     *
     * @param pageReqVO 分页查询
     * @return 对话机器人管理分页
     */
    PageResult<ChatRobotDO> getChatRobotPage(ChatRobotPageReqVO pageReqVO);

}