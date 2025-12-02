package com.ynet.iplatform.module.aicrm.service.customerguaranteepledge;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguaranteepledge.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguaranteepledge.CustomerGuaranteePledgeDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户质押物信息表（零售+对公共用） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerGuaranteePledgeService {

    /**
     * 创建客户质押物信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerGuaranteePledge(@Valid CustomerGuaranteePledgeSaveReqVO createReqVO);

    /**
     * 更新客户质押物信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerGuaranteePledge(@Valid CustomerGuaranteePledgeSaveReqVO updateReqVO);

    /**
     * 删除客户质押物信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerGuaranteePledge(Long id);

    /**
    * 批量删除客户质押物信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerGuaranteePledgeListByIds(List<Long> ids);

    /**
     * 获得客户质押物信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户质押物信息表（零售+对公共用）
     */
    CustomerGuaranteePledgeDO getCustomerGuaranteePledge(Long id);

    /**
     * 获得客户质押物信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户质押物信息表（零售+对公共用）分页
     */
    PageResult<CustomerGuaranteePledgeDO> getCustomerGuaranteePledgePage(CustomerGuaranteePledgePageReqVO pageReqVO);

}