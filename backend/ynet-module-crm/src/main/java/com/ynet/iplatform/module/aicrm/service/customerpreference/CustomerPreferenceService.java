package com.ynet.iplatform.module.aicrm.service.customerpreference;

import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerpreference.vo.*;

/**
 * 客户偏好 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerPreferenceService {

    /**
     * 创建或更新客户偏好
     *
     * @param saveReqVO 保存信息
     * @return 编号
     */
    Long saveCustomerPreference(@Valid CustomerPreferenceSaveReqVO saveReqVO);

    /**
     * 根据客户ID获取客户偏好
     *
     * @param customerId 客户ID
     * @return 客户偏好
     */
    CustomerPreferenceRespVO getCustomerPreferenceByCustomerId(Long customerId);

    /**
     * 删除客户偏好
     *
     * @param id 编号
     */
    void deleteCustomerPreference(Long id);

}
