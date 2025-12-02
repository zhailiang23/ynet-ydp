package com.ynet.iplatform.module.customer.service.chattransfers;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.customer.controller.admin.chattransfers.vo.*;
import com.ynet.iplatform.module.customer.dal.dataobject.chattransfers.ChatTransfersDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 转接记录 Service 接口
 *
 * @author 易诚源码
 */
public interface ChatTransfersService {

    /**
     * 创建转接记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createChatTransfers(@Valid ChatTransfersSaveReqVO createReqVO);

    /**
     * 更新转接记录
     *
     * @param updateReqVO 更新信息
     */
    void updateChatTransfers(@Valid ChatTransfersSaveReqVO updateReqVO);

    /**
     * 删除转接记录
     *
     * @param id 编号
     */
    void deleteChatTransfers(Integer id);

    /**
    * 批量删除转接记录
    *
    * @param ids 编号
    */
    void deleteChatTransfersListByIds(List<Integer> ids);

    /**
     * 获得转接记录
     *
     * @param id 编号
     * @return 转接记录
     */
    ChatTransfersDO getChatTransfers(Integer id);

    /**
     * 获得转接记录分页
     *
     * @param pageReqVO 分页查询
     * @return 转接记录分页
     */
    PageResult<ChatTransfersDO> getChatTransfersPage(ChatTransfersPageReqVO pageReqVO);

    /**
     * 获得转接记录分页（包含用户名和客服名）
     *
     * @param pageReqVO 分页查询
     * @return 转接记录分页
     */
    PageResult<ChatTransfersRespVO> getChatTransfersPageWithNames(ChatTransfersPageReqVO pageReqVO);

}