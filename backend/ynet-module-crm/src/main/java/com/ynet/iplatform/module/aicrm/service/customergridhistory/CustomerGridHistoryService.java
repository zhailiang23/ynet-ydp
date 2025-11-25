package com.ynet.iplatform.module.aicrm.service.customergridhistory;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customergridhistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergridhistory.CustomerGridHistoryDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户归属网格调整历史表（记录调整当时的网格信息快照） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerGridHistoryService {

    /**
     * 创建客户归属网格调整历史表（记录调整当时的网格信息快照）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerGridHistory(@Valid CustomerGridHistorySaveReqVO createReqVO);

    /**
     * 更新客户归属网格调整历史表（记录调整当时的网格信息快照）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerGridHistory(@Valid CustomerGridHistorySaveReqVO updateReqVO);

    /**
     * 删除客户归属网格调整历史表（记录调整当时的网格信息快照）
     *
     * @param id 编号
     */
    void deleteCustomerGridHistory(Long id);

    /**
    * 批量删除客户归属网格调整历史表（记录调整当时的网格信息快照）
    *
    * @param ids 编号
    */
    void deleteCustomerGridHistoryListByIds(List<Long> ids);

    /**
     * 获得客户归属网格调整历史表（记录调整当时的网格信息快照）
     *
     * @param id 编号
     * @return 客户归属网格调整历史表（记录调整当时的网格信息快照）
     */
    CustomerGridHistoryDO getCustomerGridHistory(Long id);

    /**
     * 获得客户归属网格调整历史表（记录调整当时的网格信息快照）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户归属网格调整历史表（记录调整当时的网格信息快照）分页
     */
    PageResult<CustomerGridHistoryDO> getCustomerGridHistoryPage(CustomerGridHistoryPageReqVO pageReqVO);

    /**
     * 获得客户归属网格调整历史表分页（包含联表查询用户信息）
     *
     * @param pageReqVO 分页查询
     * @return 客户归属网格调整历史表分页（VO）
     */
    PageResult<CustomerGridHistoryRespVO> getCustomerGridHistoryPageWithJoin(CustomerGridHistoryPageReqVO pageReqVO);

}