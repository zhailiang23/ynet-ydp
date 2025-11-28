package com.ynet.iplatform.module.twins.service.chatcollecttemplate;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.twins.controller.admin.chatcollecttemplate.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollecttemplate.ChatCollectTemplateDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户留资模板 Service 接口
 *
 * @author 芋道源码
 */
public interface ChatCollectTemplateService {

    /**
     * 创建客户留资模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createChatCollectTemplate(@Valid ChatCollectTemplateSaveReqVO createReqVO);

    /**
     * 更新客户留资模板
     *
     * @param updateReqVO 更新信息
     */
    void updateChatCollectTemplate(@Valid ChatCollectTemplateSaveReqVO updateReqVO);

    /**
     * 删除客户留资模板
     *
     * @param id 编号
     */
    void deleteChatCollectTemplate(Integer id);

    /**
    * 批量删除客户留资模板
    *
    * @param ids 编号
    */
    void deleteChatCollectTemplateListByIds(List<Integer> ids);

    /**
     * 获得客户留资模板
     *
     * @param id 编号
     * @return 客户留资模板
     */
    ChatCollectTemplateDO getChatCollectTemplate(Integer id);

    /**
     * 获得客户留资模板分页
     *
     * @param pageReqVO 分页查询
     * @return 客户留资模板分页
     */
    PageResult<ChatCollectTemplateDO> getChatCollectTemplatePage(ChatCollectTemplatePageReqVO pageReqVO);

}