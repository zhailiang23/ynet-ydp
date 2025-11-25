package com.ynet.iplatform.module.aicrm.service.customerfamily;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerfamily.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerfamily.CustomerFamilyDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户家庭信息表（零售客户） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerFamilyService {

    /**
     * 创建客户家庭信息表（零售客户）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerFamily(@Valid CustomerFamilySaveReqVO createReqVO);

    /**
     * 更新客户家庭信息表（零售客户）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerFamily(@Valid CustomerFamilySaveReqVO updateReqVO);

    /**
     * 删除客户家庭信息表（零售客户）
     *
     * @param id 编号
     */
    void deleteCustomerFamily(Long id);

    /**
    * 批量删除客户家庭信息表（零售客户）
    *
    * @param ids 编号
    */
    void deleteCustomerFamilyListByIds(List<Long> ids);

    /**
     * 获得客户家庭信息表（零售客户）
     *
     * @param id 编号
     * @return 客户家庭信息表（零售客户）
     */
    CustomerFamilyDO getCustomerFamily(Long id);

    /**
     * 获得客户家庭信息表（零售客户）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户家庭信息表（零售客户）分页
     */
    PageResult<CustomerFamilyDO> getCustomerFamilyPage(CustomerFamilyPageReqVO pageReqVO);

}