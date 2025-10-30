package cn.iocoder.yudao.module.aicrm.service.customergrouphistory;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customergrouphistory.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customergrouphistory.CustomerGroupHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户归属客群调整历史表（记录调整当时的客群信息快照） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerGroupHistoryService {

    /**
     * 创建客户归属客群调整历史表（记录调整当时的客群信息快照）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerGroupHistory(@Valid CustomerGroupHistorySaveReqVO createReqVO);

    /**
     * 更新客户归属客群调整历史表（记录调整当时的客群信息快照）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerGroupHistory(@Valid CustomerGroupHistorySaveReqVO updateReqVO);

    /**
     * 删除客户归属客群调整历史表（记录调整当时的客群信息快照）
     *
     * @param id 编号
     */
    void deleteCustomerGroupHistory(Long id);

    /**
    * 批量删除客户归属客群调整历史表（记录调整当时的客群信息快照）
    *
    * @param ids 编号
    */
    void deleteCustomerGroupHistoryListByIds(List<Long> ids);

    /**
     * 获得客户归属客群调整历史表（记录调整当时的客群信息快照）
     *
     * @param id 编号
     * @return 客户归属客群调整历史表（记录调整当时的客群信息快照）
     */
    CustomerGroupHistoryDO getCustomerGroupHistory(Long id);

    /**
     * 获得客户归属客群调整历史表（记录调整当时的客群信息快照）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户归属客群调整历史表（记录调整当时的客群信息快照）分页
     */
    PageResult<CustomerGroupHistoryDO> getCustomerGroupHistoryPage(CustomerGroupHistoryPageReqVO pageReqVO);

}