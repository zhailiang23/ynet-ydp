package cn.iocoder.yudao.module.aicrm.service.customeraccountinsurance;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountinsurance.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountinsurance.CustomerAccountInsuranceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户保险账户信息表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAccountInsuranceService {

    /**
     * 创建客户保险账户信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAccountInsurance(@Valid CustomerAccountInsuranceSaveReqVO createReqVO);

    /**
     * 更新客户保险账户信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAccountInsurance(@Valid CustomerAccountInsuranceSaveReqVO updateReqVO);

    /**
     * 删除客户保险账户信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerAccountInsurance(Long id);

    /**
    * 批量删除客户保险账户信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerAccountInsuranceListByIds(List<Long> ids);

    /**
     * 获得客户保险账户信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户保险账户信息表（零售+对公共用）
     */
    CustomerAccountInsuranceDO getCustomerAccountInsurance(Long id);

    /**
     * 获得客户保险账户信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户保险账户信息表（零售+对公共用）分页
     */
    PageResult<CustomerAccountInsuranceDO> getCustomerAccountInsurancePage(CustomerAccountInsurancePageReqVO pageReqVO);

}