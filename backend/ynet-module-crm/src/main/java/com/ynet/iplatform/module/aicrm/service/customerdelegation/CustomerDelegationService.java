package com.ynet.iplatform.module.aicrm.service.customerdelegation;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerdelegation.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerdelegation.CustomerDelegationDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 客户托管记录表 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerDelegationService {

    /**
     * 创建客户托管
     *
     * @param userId 当前用户ID
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDelegation(Long userId, @Valid CustomerDelegationCreateReqVO createReqVO);

    /**
     * 结束客户托管
     *
     * @param userId 当前用户ID
     * @param endReqVO 结束信息
     */
    void endDelegation(Long userId, @Valid CustomerDelegationEndReqVO endReqVO);

    /**
     * 取消客户托管
     *
     * @param userId 当前用户ID
     * @param id 托管ID
     */
    void cancelDelegation(Long userId, Long id);

    /**
     * 获得客户托管记录
     *
     * @param id 编号
     * @return 客户托管记录
     */
    CustomerDelegationDO getDelegation(Long id);

    /**
     * 获得客户托管记录分页
     *
     * @param pageReqVO 分页查询
     * @return 客户托管记录分页
     */
    PageResult<CustomerDelegationDO> getDelegationPage(CustomerDelegationPageReqVO pageReqVO);

    /**
     * 获得指定客户的进行中的托管记录
     *
     * @param customerId 客户ID
     * @return 托管记录，如果没有则返回null
     */
    CustomerDelegationDO getActiveDelegationByCustomerId(Long customerId);

}
