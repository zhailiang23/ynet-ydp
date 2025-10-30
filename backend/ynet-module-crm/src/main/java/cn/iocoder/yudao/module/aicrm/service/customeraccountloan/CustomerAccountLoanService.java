package cn.iocoder.yudao.module.aicrm.service.customeraccountloan;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountloan.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountloan.CustomerAccountLoanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户贷款账户信息表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAccountLoanService {

    /**
     * 创建客户贷款账户信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAccountLoan(@Valid CustomerAccountLoanSaveReqVO createReqVO);

    /**
     * 更新客户贷款账户信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAccountLoan(@Valid CustomerAccountLoanSaveReqVO updateReqVO);

    /**
     * 删除客户贷款账户信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerAccountLoan(Long id);

    /**
    * 批量删除客户贷款账户信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerAccountLoanListByIds(List<Long> ids);

    /**
     * 获得客户贷款账户信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户贷款账户信息表（零售+对公共用）
     */
    CustomerAccountLoanDO getCustomerAccountLoan(Long id);

    /**
     * 获得客户贷款账户信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户贷款账户信息表（零售+对公共用）分页
     */
    PageResult<CustomerAccountLoanDO> getCustomerAccountLoanPage(CustomerAccountLoanPageReqVO pageReqVO);

}