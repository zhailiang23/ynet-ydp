package com.ynet.iplatform.module.infra.service.externalagent;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.infra.controller.admin.externalagent.vo.*;
import com.ynet.iplatform.module.infra.dal.dataobject.externalagent.ExternalAgentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 外部智能体管理 Service 接口
 *
 * @author 易诚源码
 */
public interface ExternalAgentService {

    /**
     * 创建外部智能体管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExternalAgent(@Valid ExternalAgentSaveReqVO createReqVO);

    /**
     * 更新外部智能体管理
     *
     * @param updateReqVO 更新信息
     */
    void updateExternalAgent(@Valid ExternalAgentSaveReqVO updateReqVO);

    /**
     * 删除外部智能体管理
     *
     * @param id 编号
     */
    void deleteExternalAgent(Long id);

    /**
    * 批量删除外部智能体管理
    *
    * @param ids 编号
    */
    void deleteExternalAgentListByIds(List<Long> ids);

    /**
     * 获得外部智能体管理
     *
     * @param id 编号
     * @return 外部智能体管理
     */
    ExternalAgentDO getExternalAgent(Long id);

    /**
     * 获得外部智能体管理分页
     *
     * @param pageReqVO 分页查询
     * @return 外部智能体管理分页
     */
    PageResult<ExternalAgentDO> getExternalAgentPage(ExternalAgentPageReqVO pageReqVO);

    /**
     * 根据智能体编码获取外部智能体
     *
     * @param code 智能体编码
     * @return 外部智能体管理
     */
    ExternalAgentDO getExternalAgentByCode(String code);

    /**
     * 获取外部智能体精简信息列表
     *
     * @return 外部智能体精简信息列表
     */
    List<ExternalAgentDO> getExternalAgentList();

}