package cn.iocoder.yudao.module.aicrm.service.customerdiscountoverview;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdiscountoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdiscountoverview.CustomerDiscountOverviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户贴现业务概览 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerDiscountOverviewService {

    /**
     * 创建客户贴现业务概览
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerDiscountOverview(@Valid CustomerDiscountOverviewSaveReqVO createReqVO);

    /**
     * 更新客户贴现业务概览
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerDiscountOverview(@Valid CustomerDiscountOverviewSaveReqVO updateReqVO);

    /**
     * 删除客户贴现业务概览
     *
     * @param id 编号
     */
    void deleteCustomerDiscountOverview(Long id);

    /**
    * 批量删除客户贴现业务概览
    *
    * @param ids 编号
    */
    void deleteCustomerDiscountOverviewListByIds(List<Long> ids);

    /**
     * 获得客户贴现业务概览
     *
     * @param id 编号
     * @return 客户贴现业务概览
     */
    CustomerDiscountOverviewDO getCustomerDiscountOverview(Long id);

    /**
     * 获得客户贴现业务概览分页
     *
     * @param pageReqVO 分页查询
     * @return 客户贴现业务概览分页
     */
    PageResult<CustomerDiscountOverviewDO> getCustomerDiscountOverviewPage(CustomerDiscountOverviewPageReqVO pageReqVO);

}