package cn.iocoder.yudao.module.aicrm.service.customeroffbalanceoverview;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeroffbalanceoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeroffbalanceoverview.CustomerOffbalanceOverviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户表外业务概览 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerOffbalanceOverviewService {

    /**
     * 创建客户表外业务概览
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerOffbalanceOverview(@Valid CustomerOffbalanceOverviewSaveReqVO createReqVO);

    /**
     * 更新客户表外业务概览
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerOffbalanceOverview(@Valid CustomerOffbalanceOverviewSaveReqVO updateReqVO);

    /**
     * 删除客户表外业务概览
     *
     * @param id 编号
     */
    void deleteCustomerOffbalanceOverview(Long id);

    /**
    * 批量删除客户表外业务概览
    *
    * @param ids 编号
    */
    void deleteCustomerOffbalanceOverviewListByIds(List<Long> ids);

    /**
     * 获得客户表外业务概览
     *
     * @param id 编号
     * @return 客户表外业务概览
     */
    CustomerOffbalanceOverviewDO getCustomerOffbalanceOverview(Long id);

    /**
     * 获得客户表外业务概览分页
     *
     * @param pageReqVO 分页查询
     * @return 客户表外业务概览分页
     */
    PageResult<CustomerOffbalanceOverviewDO> getCustomerOffbalanceOverviewPage(CustomerOffbalanceOverviewPageReqVO pageReqVO);

}