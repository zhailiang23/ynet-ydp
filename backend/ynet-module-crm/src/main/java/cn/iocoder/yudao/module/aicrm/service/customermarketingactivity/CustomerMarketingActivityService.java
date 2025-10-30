package cn.iocoder.yudao.module.aicrm.service.customermarketingactivity;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customermarketingactivity.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customermarketingactivity.CustomerMarketingActivityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户营销活动信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerMarketingActivityService {

    /**
     * 创建客户营销活动信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerMarketingActivity(@Valid CustomerMarketingActivitySaveReqVO createReqVO);

    /**
     * 更新客户营销活动信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerMarketingActivity(@Valid CustomerMarketingActivitySaveReqVO updateReqVO);

    /**
     * 删除客户营销活动信息
     *
     * @param id 编号
     */
    void deleteCustomerMarketingActivity(Long id);

    /**
    * 批量删除客户营销活动信息
    *
    * @param ids 编号
    */
    void deleteCustomerMarketingActivityListByIds(List<Long> ids);

    /**
     * 获得客户营销活动信息
     *
     * @param id 编号
     * @return 客户营销活动信息
     */
    CustomerMarketingActivityDO getCustomerMarketingActivity(Long id);

    /**
     * 获得客户营销活动信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户营销活动信息分页
     */
    PageResult<CustomerMarketingActivityDO> getCustomerMarketingActivityPage(CustomerMarketingActivityPageReqVO pageReqVO);

}