package com.ynet.iplatform.module.aicrm.service.customeraccountfund;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountfund.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountfund.CustomerAccountFundDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户基金账户信息表（零售+对公共用） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerAccountFundService {

    /**
     * 创建客户基金账户信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAccountFund(@Valid CustomerAccountFundSaveReqVO createReqVO);

    /**
     * 更新客户基金账户信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAccountFund(@Valid CustomerAccountFundSaveReqVO updateReqVO);

    /**
     * 删除客户基金账户信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerAccountFund(Long id);

    /**
    * 批量删除客户基金账户信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerAccountFundListByIds(List<Long> ids);

    /**
     * 获得客户基金账户信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户基金账户信息表（零售+对公共用）
     */
    CustomerAccountFundDO getCustomerAccountFund(Long id);

    /**
     * 获得客户基金账户信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户基金账户信息表（零售+对公共用）分页
     */
    PageResult<CustomerAccountFundDO> getCustomerAccountFundPage(CustomerAccountFundPageReqVO pageReqVO);

}