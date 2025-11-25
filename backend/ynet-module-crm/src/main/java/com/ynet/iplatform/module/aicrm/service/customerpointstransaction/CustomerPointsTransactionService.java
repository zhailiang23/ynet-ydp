package com.ynet.iplatform.module.aicrm.service.customerpointstransaction;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerpointstransaction.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerpointstransaction.CustomerPointsTransactionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户积分消费明细表（积分交易流水表） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerPointsTransactionService {

    /**
     * 创建客户积分消费明细表（积分交易流水表）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerPointsTransaction(@Valid CustomerPointsTransactionSaveReqVO createReqVO);

    /**
     * 更新客户积分消费明细表（积分交易流水表）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerPointsTransaction(@Valid CustomerPointsTransactionSaveReqVO updateReqVO);

    /**
     * 删除客户积分消费明细表（积分交易流水表）
     *
     * @param id 编号
     */
    void deleteCustomerPointsTransaction(Long id);

    /**
    * 批量删除客户积分消费明细表（积分交易流水表）
    *
    * @param ids 编号
    */
    void deleteCustomerPointsTransactionListByIds(List<Long> ids);

    /**
     * 获得客户积分消费明细表（积分交易流水表）
     *
     * @param id 编号
     * @return 客户积分消费明细表（积分交易流水表）
     */
    CustomerPointsTransactionDO getCustomerPointsTransaction(Long id);

    /**
     * 获得客户积分消费明细表（积分交易流水表）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户积分消费明细表（积分交易流水表）分页
     */
    PageResult<CustomerPointsTransactionDO> getCustomerPointsTransactionPage(CustomerPointsTransactionPageReqVO pageReqVO);

}