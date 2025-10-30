package cn.iocoder.yudao.module.aicrm.service.customerdemand;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerdemand.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerdemand.CustomerDemandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户需求信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerDemandService {

    /**
     * 创建客户需求信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerDemand(@Valid CustomerDemandSaveReqVO createReqVO);

    /**
     * 更新客户需求信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerDemand(@Valid CustomerDemandSaveReqVO updateReqVO);

    /**
     * 删除客户需求信息
     *
     * @param id 编号
     */
    void deleteCustomerDemand(Long id);

    /**
    * 批量删除客户需求信息
    *
    * @param ids 编号
    */
    void deleteCustomerDemandListByIds(List<Long> ids);

    /**
     * 获得客户需求信息
     *
     * @param id 编号
     * @return 客户需求信息
     */
    CustomerDemandDO getCustomerDemand(Long id);

    /**
     * 获得客户需求信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户需求信息分页
     */
    PageResult<CustomerDemandDO> getCustomerDemandPage(CustomerDemandPageReqVO pageReqVO);

}