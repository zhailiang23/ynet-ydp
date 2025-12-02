package com.ynet.iplatform.module.twins.service.chatcollectinfo;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollectinfo.ChatCollectInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户留资信息 Service 接口
 *
 * @author 易诚源码
 */
public interface ChatCollectInfoService {

    /**
     * 创建客户留资信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createChatCollectInfo(@Valid ChatCollectInfoSaveReqVO createReqVO);

    /**
     * 更新客户留资信息
     *
     * @param updateReqVO 更新信息
     */
    void updateChatCollectInfo(@Valid ChatCollectInfoSaveReqVO updateReqVO);

    /**
     * 删除客户留资信息
     *
     * @param id 编号
     */
    void deleteChatCollectInfo(Integer id);

    /**
    * 批量删除客户留资信息
    *
    * @param ids 编号
    */
    void deleteChatCollectInfoListByIds(List<Integer> ids);

    /**
     * 获得客户留资信息
     *
     * @param id 编号
     * @return 客户留资信息
     */
    ChatCollectInfoDO getChatCollectInfo(Integer id);

    /**
     * 获得客户留资信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户留资信息分页
     */
    PageResult<ChatCollectInfoDO> getChatCollectInfoPage(ChatCollectInfoPageReqVO pageReqVO);

}