package cn.iocoder.yudao.module.aicrm.service.customermiddlebusinessoverview;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customermiddlebusinessoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customermiddlebusinessoverview.CustomerMiddleBusinessOverviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户中间业务概览 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerMiddleBusinessOverviewService {

    /**
     * 创建客户中间业务概览
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerMiddleBusinessOverview(@Valid CustomerMiddleBusinessOverviewSaveReqVO createReqVO);

    /**
     * 更新客户中间业务概览
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerMiddleBusinessOverview(@Valid CustomerMiddleBusinessOverviewSaveReqVO updateReqVO);

    /**
     * 删除客户中间业务概览
     *
     * @param id 编号
     */
    void deleteCustomerMiddleBusinessOverview(Long id);

    /**
    * 批量删除客户中间业务概览
    *
    * @param ids 编号
    */
    void deleteCustomerMiddleBusinessOverviewListByIds(List<Long> ids);

    /**
     * 获得客户中间业务概览
     *
     * @param id 编号
     * @return 客户中间业务概览
     */
    CustomerMiddleBusinessOverviewDO getCustomerMiddleBusinessOverview(Long id);

    /**
     * 获得客户中间业务概览分页
     *
     * @param pageReqVO 分页查询
     * @return 客户中间业务概览分页
     */
    PageResult<CustomerMiddleBusinessOverviewDO> getCustomerMiddleBusinessOverviewPage(CustomerMiddleBusinessOverviewPageReqVO pageReqVO);

}