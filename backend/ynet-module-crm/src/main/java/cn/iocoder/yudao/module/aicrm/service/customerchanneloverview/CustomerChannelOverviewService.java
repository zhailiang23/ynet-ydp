package cn.iocoder.yudao.module.aicrm.service.customerchanneloverview;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerchanneloverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerchanneloverview.CustomerChannelOverviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户渠道业务概览 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerChannelOverviewService {

    /**
     * 创建客户渠道业务概览
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerChannelOverview(@Valid CustomerChannelOverviewSaveReqVO createReqVO);

    /**
     * 更新客户渠道业务概览
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerChannelOverview(@Valid CustomerChannelOverviewSaveReqVO updateReqVO);

    /**
     * 删除客户渠道业务概览
     *
     * @param id 编号
     */
    void deleteCustomerChannelOverview(Long id);

    /**
    * 批量删除客户渠道业务概览
    *
    * @param ids 编号
    */
    void deleteCustomerChannelOverviewListByIds(List<Long> ids);

    /**
     * 获得客户渠道业务概览
     *
     * @param id 编号
     * @return 客户渠道业务概览
     */
    CustomerChannelOverviewDO getCustomerChannelOverview(Long id);

    /**
     * 获得客户渠道业务概览分页
     *
     * @param pageReqVO 分页查询
     * @return 客户渠道业务概览分页
     */
    PageResult<CustomerChannelOverviewDO> getCustomerChannelOverviewPage(CustomerChannelOverviewPageReqVO pageReqVO);

}