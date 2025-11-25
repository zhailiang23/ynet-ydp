package com.ynet.iplatform.module.aicrm.service.customeraccountcreditcard;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customeraccountcreditcard.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountcreditcard.CustomerAccountCreditcardDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户信用卡账户信息表（仅限零售客户） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAccountCreditcardService {

    /**
     * 创建客户信用卡账户信息表（仅限零售客户）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAccountCreditcard(@Valid CustomerAccountCreditcardSaveReqVO createReqVO);

    /**
     * 更新客户信用卡账户信息表（仅限零售客户）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAccountCreditcard(@Valid CustomerAccountCreditcardSaveReqVO updateReqVO);

    /**
     * 删除客户信用卡账户信息表（仅限零售客户）
     *
     * @param id 编号
     */
    void deleteCustomerAccountCreditcard(Long id);

    /**
    * 批量删除客户信用卡账户信息表（仅限零售客户）
    *
    * @param ids 编号
    */
    void deleteCustomerAccountCreditcardListByIds(List<Long> ids);

    /**
     * 获得客户信用卡账户信息表（仅限零售客户）
     *
     * @param id 编号
     * @return 客户信用卡账户信息表（仅限零售客户）
     */
    CustomerAccountCreditcardDO getCustomerAccountCreditcard(Long id);

    /**
     * 获得客户信用卡账户信息表（仅限零售客户）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户信用卡账户信息表（仅限零售客户）分页
     */
    PageResult<CustomerAccountCreditcardDO> getCustomerAccountCreditcardPage(CustomerAccountCreditcardPageReqVO pageReqVO);

}