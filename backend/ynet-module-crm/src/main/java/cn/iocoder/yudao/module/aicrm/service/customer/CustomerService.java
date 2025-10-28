package cn.iocoder.yudao.module.aicrm.service.customer;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customer.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customer.CustomerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM客户主表(零售+对公共用) Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerService {

    /**
     * 创建CRM客户主表(零售+对公共用)
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomer(@Valid CustomerSaveReqVO createReqVO);

    /**
     * 更新CRM客户主表(零售+对公共用)
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomer(@Valid CustomerSaveReqVO updateReqVO);

    /**
     * 删除CRM客户主表(零售+对公共用)
     *
     * @param id 编号
     */
    void deleteCustomer(Long id);

    /**
    * 批量删除CRM客户主表(零售+对公共用)
    *
    * @param ids 编号
    */
    void deleteCustomerListByIds(List<Long> ids);

    /**
     * 获得CRM客户主表(零售+对公共用)
     *
     * @param id 编号
     * @return CRM客户主表(零售+对公共用)
     */
    CustomerDO getCustomer(Long id);

    /**
     * 获得CRM客户主表(零售+对公共用)分页
     *
     * @param pageReqVO 分页查询
     * @return CRM客户主表(零售+对公共用)分页
     */
    PageResult<CustomerDO> getCustomerPage(CustomerPageReqVO pageReqVO);

}