package com.ynet.iplatform.module.aicrm.service.customerguarantor;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerguarantor.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerguarantor.CustomerGuarantorDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户担保人信息表（零售+对公共用） Service 接口
 *
 * @author 易诚源码
 */
public interface CustomerGuarantorService {

    /**
     * 创建客户担保人信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerGuarantor(@Valid CustomerGuarantorSaveReqVO createReqVO);

    /**
     * 更新客户担保人信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerGuarantor(@Valid CustomerGuarantorSaveReqVO updateReqVO);

    /**
     * 删除客户担保人信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerGuarantor(Long id);

    /**
    * 批量删除客户担保人信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerGuarantorListByIds(List<Long> ids);

    /**
     * 获得客户担保人信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户担保人信息表（零售+对公共用）
     */
    CustomerGuarantorDO getCustomerGuarantor(Long id);

    /**
     * 获得客户担保人信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户担保人信息表（零售+对公共用）分页
     */
    PageResult<CustomerGuarantorDO> getCustomerGuarantorPage(CustomerGuarantorPageReqVO pageReqVO);

}