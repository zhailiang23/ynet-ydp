package cn.iocoder.yudao.module.aicrm.service.customerdelegation;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdelegation.CustomerDelegationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户托管记录 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerDelegationService {

    /**
     * 创建客户托管记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerDelegation(@Valid CustomerDelegationSaveReqVO createReqVO);

    /**
     * 更新客户托管记录
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerDelegation(@Valid CustomerDelegationSaveReqVO updateReqVO);

    /**
     * 删除客户托管记录
     *
     * @param id 编号
     */
    void deleteCustomerDelegation(Long id);

    /**
    * 批量删除客户托管记录
    *
    * @param ids 编号
    */
    void deleteCustomerDelegationListByIds(List<Long> ids);

    /**
     * 获得客户托管记录
     *
     * @param id 编号
     * @return 客户托管记录
     */
    CustomerDelegationDO getCustomerDelegation(Long id);

    /**
     * 获得客户托管记录分页
     *
     * @param pageReqVO 分页查询
     * @return 客户托管记录分页
     */
    PageResult<CustomerDelegationDO> getCustomerDelegationPage(CustomerDelegationPageReqVO pageReqVO);

}