package com.ynet.iplatform.module.aicrm.service.customerchannelbehavior;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerchannelbehavior.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerchannelbehavior.CustomerChannelBehaviorDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户渠道行为信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerChannelBehaviorService {

    /**
     * 创建客户渠道行为信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerChannelBehavior(@Valid CustomerChannelBehaviorSaveReqVO createReqVO);

    /**
     * 更新客户渠道行为信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerChannelBehavior(@Valid CustomerChannelBehaviorSaveReqVO updateReqVO);

    /**
     * 删除客户渠道行为信息
     *
     * @param id 编号
     */
    void deleteCustomerChannelBehavior(Long id);

    /**
    * 批量删除客户渠道行为信息
    *
    * @param ids 编号
    */
    void deleteCustomerChannelBehaviorListByIds(List<Long> ids);

    /**
     * 获得客户渠道行为信息
     *
     * @param id 编号
     * @return 客户渠道行为信息
     */
    CustomerChannelBehaviorDO getCustomerChannelBehavior(Long id);

    /**
     * 获得客户渠道行为信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户渠道行为信息分页
     */
    PageResult<CustomerChannelBehaviorDO> getCustomerChannelBehaviorPage(CustomerChannelBehaviorPageReqVO pageReqVO);

}