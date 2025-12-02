package com.ynet.iplatform.module.aicrm.service.customercomplaint;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercomplaint.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercomplaint.CustomerComplaintDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户投诉信息 Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerComplaintService {

    /**
     * 创建客户投诉信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerComplaint(@Valid CustomerComplaintSaveReqVO createReqVO);

    /**
     * 更新客户投诉信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerComplaint(@Valid CustomerComplaintSaveReqVO updateReqVO);

    /**
     * 删除客户投诉信息
     *
     * @param id 编号
     */
    void deleteCustomerComplaint(Long id);

    /**
    * 批量删除客户投诉信息
    *
    * @param ids 编号
    */
    void deleteCustomerComplaintListByIds(List<Long> ids);

    /**
     * 获得客户投诉信息
     *
     * @param id 编号
     * @return 客户投诉信息
     */
    CustomerComplaintDO getCustomerComplaint(Long id);

    /**
     * 获得客户投诉信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户投诉信息分页
     */
    PageResult<CustomerComplaintDO> getCustomerComplaintPage(CustomerComplaintPageReqVO pageReqVO);

}