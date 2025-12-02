package com.ynet.iplatform.module.aicrm.service.customercredit;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercredit.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercredit.CustomerCreditDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户授信信息表（零售+对公共用） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerCreditService {

    /**
     * 创建客户授信信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerCredit(@Valid CustomerCreditSaveReqVO createReqVO);

    /**
     * 更新客户授信信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerCredit(@Valid CustomerCreditSaveReqVO updateReqVO);

    /**
     * 删除客户授信信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerCredit(Long id);

    /**
    * 批量删除客户授信信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerCreditListByIds(List<Long> ids);

    /**
     * 获得客户授信信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户授信信息表（零售+对公共用）
     */
    CustomerCreditDO getCustomerCredit(Long id);

    /**
     * 获得客户授信信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户授信信息表（零售+对公共用）分页
     */
    PageResult<CustomerCreditDO> getCustomerCreditPage(CustomerCreditPageReqVO pageReqVO);

}