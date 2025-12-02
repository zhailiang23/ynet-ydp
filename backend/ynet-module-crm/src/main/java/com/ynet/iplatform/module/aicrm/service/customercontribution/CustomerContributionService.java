package com.ynet.iplatform.module.aicrm.service.customercontribution;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customercontribution.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontribution.CustomerContributionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户贡献度信息表（与客户主表1对1关系） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerContributionService {

    /**
     * 创建客户贡献度信息表（与客户主表1对1关系）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerContribution(@Valid CustomerContributionSaveReqVO createReqVO);

    /**
     * 更新客户贡献度信息表（与客户主表1对1关系）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerContribution(@Valid CustomerContributionSaveReqVO updateReqVO);

    /**
     * 删除客户贡献度信息表（与客户主表1对1关系）
     *
     * @param id 编号
     */
    void deleteCustomerContribution(Long id);

    /**
    * 批量删除客户贡献度信息表（与客户主表1对1关系）
    *
    * @param ids 编号
    */
    void deleteCustomerContributionListByIds(List<Long> ids);

    /**
     * 获得客户贡献度信息表（与客户主表1对1关系）
     *
     * @param id 编号
     * @return 客户贡献度信息表（与客户主表1对1关系）
     */
    CustomerContributionDO getCustomerContribution(Long id);

    /**
     * 获得客户贡献度信息表（与客户主表1对1关系）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户贡献度信息表（与客户主表1对1关系）分页
     */
    PageResult<CustomerContributionDO> getCustomerContributionPage(CustomerContributionPageReqVO pageReqVO);

}