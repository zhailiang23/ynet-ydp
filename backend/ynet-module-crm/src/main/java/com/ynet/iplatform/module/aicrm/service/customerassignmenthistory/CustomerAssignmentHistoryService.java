package com.ynet.iplatform.module.aicrm.service.customerassignmenthistory;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerassignmenthistory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerassignmenthistory.CustomerAssignmentHistoryDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户归属调整历史表（零售+对公共用，记录所有归属变更历史） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAssignmentHistoryService {

    /**
     * 创建客户归属调整历史表（零售+对公共用，记录所有归属变更历史）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAssignmentHistory(@Valid CustomerAssignmentHistorySaveReqVO createReqVO);

    /**
     * 更新客户归属调整历史表（零售+对公共用，记录所有归属变更历史）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAssignmentHistory(@Valid CustomerAssignmentHistorySaveReqVO updateReqVO);

    /**
     * 删除客户归属调整历史表（零售+对公共用，记录所有归属变更历史）
     *
     * @param id 编号
     */
    void deleteCustomerAssignmentHistory(Long id);

    /**
    * 批量删除客户归属调整历史表（零售+对公共用，记录所有归属变更历史）
    *
    * @param ids 编号
    */
    void deleteCustomerAssignmentHistoryListByIds(List<Long> ids);

    /**
     * 获得客户归属调整历史表（零售+对公共用，记录所有归属变更历史）
     *
     * @param id 编号
     * @return 客户归属调整历史表（零售+对公共用，记录所有归属变更历史）
     */
    CustomerAssignmentHistoryDO getCustomerAssignmentHistory(Long id);

    /**
     * 获得客户归属调整历史表（零售+对公共用，记录所有归属变更历史）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户归属调整历史表（零售+对公共用，记录所有归属变更历史）分页
     */
    PageResult<CustomerAssignmentHistoryDO> getCustomerAssignmentHistoryPage(CustomerAssignmentHistoryPageReqVO pageReqVO);

}