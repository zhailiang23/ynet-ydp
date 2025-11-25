package com.ynet.iplatform.module.aicrm.service.customeraccountdeposit;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountdeposit.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountdeposit.CustomerAccountDepositDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户存款账户信息表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAccountDepositService {

    /**
     * 创建客户存款账户信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAccountDeposit(@Valid CustomerAccountDepositSaveReqVO createReqVO);

    /**
     * 更新客户存款账户信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAccountDeposit(@Valid CustomerAccountDepositSaveReqVO updateReqVO);

    /**
     * 删除客户存款账户信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerAccountDeposit(Long id);

    /**
    * 批量删除客户存款账户信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerAccountDepositListByIds(List<Long> ids);

    /**
     * 获得客户存款账户信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户存款账户信息表（零售+对公共用）
     */
    CustomerAccountDepositDO getCustomerAccountDeposit(Long id);

    /**
     * 获得客户存款账户信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户存款账户信息表（零售+对公共用）分页
     */
    PageResult<CustomerAccountDepositDO> getCustomerAccountDepositPage(CustomerAccountDepositPageReqVO pageReqVO);

}