package cn.iocoder.yudao.module.aicrm.service.customerloanoverview;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerloanoverview.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerloanoverview.CustomerLoanOverviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户贷款业务概览 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerLoanOverviewService {

    /**
     * 创建客户贷款业务概览
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerLoanOverview(@Valid CustomerLoanOverviewSaveReqVO createReqVO);

    /**
     * 更新客户贷款业务概览
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerLoanOverview(@Valid CustomerLoanOverviewSaveReqVO updateReqVO);

    /**
     * 删除客户贷款业务概览
     *
     * @param id 编号
     */
    void deleteCustomerLoanOverview(Long id);

    /**
    * 批量删除客户贷款业务概览
    *
    * @param ids 编号
    */
    void deleteCustomerLoanOverviewListByIds(List<Long> ids);

    /**
     * 获得客户贷款业务概览
     *
     * @param id 编号
     * @return 客户贷款业务概览
     */
    CustomerLoanOverviewDO getCustomerLoanOverview(Long id);

    /**
     * 获得客户贷款业务概览分页
     *
     * @param pageReqVO 分页查询
     * @return 客户贷款业务概览分页
     */
    PageResult<CustomerLoanOverviewDO> getCustomerLoanOverviewPage(CustomerLoanOverviewPageReqVO pageReqVO);

}