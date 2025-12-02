package com.ynet.iplatform.module.aicrm.service.customertransactionmock;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customertransactionmock.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customertransactionmock.CustomerTransactionMockDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户交易明细信息表（Mock数据） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerTransactionMockService {

    /**
     * 创建客户交易明细信息表（Mock数据）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerTransactionMock(@Valid CustomerTransactionMockSaveReqVO createReqVO);

    /**
     * 更新客户交易明细信息表（Mock数据）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerTransactionMock(@Valid CustomerTransactionMockSaveReqVO updateReqVO);

    /**
     * 删除客户交易明细信息表（Mock数据）
     *
     * @param id 编号
     */
    void deleteCustomerTransactionMock(Long id);

    /**
    * 批量删除客户交易明细信息表（Mock数据）
    *
    * @param ids 编号
    */
    void deleteCustomerTransactionMockListByIds(List<Long> ids);

    /**
     * 获得客户交易明细信息表（Mock数据）
     *
     * @param id 编号
     * @return 客户交易明细信息表（Mock数据）
     */
    CustomerTransactionMockDO getCustomerTransactionMock(Long id);

    /**
     * 获得客户交易明细信息表（Mock数据）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户交易明细信息表（Mock数据）分页
     */
    PageResult<CustomerTransactionMockDO> getCustomerTransactionMockPage(CustomerTransactionMockPageReqVO pageReqVO);

}