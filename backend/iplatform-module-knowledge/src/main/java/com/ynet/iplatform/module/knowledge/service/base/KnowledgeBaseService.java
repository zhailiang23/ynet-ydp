package com.ynet.iplatform.module.knowledge.service.base;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.knowledge.controller.admin.base.vo.*;
import com.ynet.iplatform.module.knowledge.dal.dataobject.base.KnowledgeBaseDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 知识库 Service 接口
 *
 * @author 芋道源码
 */
public interface KnowledgeBaseService {

    /**
     * 创建知识库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBase(@Valid KnowledgeBaseSaveReqVO createReqVO);

    /**
     * 更新知识库
     *
     * @param updateReqVO 更新信息
     */
    void updateBase(@Valid KnowledgeBaseSaveReqVO updateReqVO);

    /**
     * 删除知识库
     *
     * @param id 编号
     */
    void deleteBase(Long id);

    /**
    * 批量删除知识库
    *
    * @param ids 编号
    */
    void deleteBaseListByIds(List<Long> ids);

    /**
     * 获得知识库
     *
     * @param id 编号
     * @return 知识库
     */
    KnowledgeBaseDO getBase(Long id);

    /**
     * 获得知识库分页
     *
     * @param pageReqVO 分页查询
     * @return 知识库分页
     */
    PageResult<KnowledgeBaseDO> getBasePage(KnowledgeBasePageReqVO pageReqVO);

    /**
     * 获取或创建个人知识库
     * 如果用户的个人知识库不存在，则自动创建一个
     *
     * @return 个人知识库
     */
    KnowledgeBaseDO getOrCreatePersonalBase();

}