package com.ynet.iplatform.module.aicrm.service.customerdepositoverview;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerdepositoverview.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdepositoverview.CustomerDepositOverviewDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户存款业务概览 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerDepositOverviewService {

    /**
     * 创建客户存款业务概览
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerDepositOverview(@Valid CustomerDepositOverviewSaveReqVO createReqVO);

    /**
     * 更新客户存款业务概览
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerDepositOverview(@Valid CustomerDepositOverviewSaveReqVO updateReqVO);

    /**
     * 删除客户存款业务概览
     *
     * @param id 编号
     */
    void deleteCustomerDepositOverview(Long id);

    /**
    * 批量删除客户存款业务概览
    *
    * @param ids 编号
    */
    void deleteCustomerDepositOverviewListByIds(List<Long> ids);

    /**
     * 获得客户存款业务概览
     *
     * @param id 编号
     * @return 客户存款业务概览
     */
    CustomerDepositOverviewDO getCustomerDepositOverview(Long id);

    /**
     * 获得客户存款业务概览分页
     *
     * @param pageReqVO 分页查询
     * @return 客户存款业务概览分页
     */
    PageResult<CustomerDepositOverviewDO> getCustomerDepositOverviewPage(CustomerDepositOverviewPageReqVO pageReqVO);

}