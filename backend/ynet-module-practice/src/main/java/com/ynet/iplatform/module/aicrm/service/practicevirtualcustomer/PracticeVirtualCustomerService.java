package com.ynet.iplatform.module.aicrm.service.practicevirtualcustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-虚拟客户 Service 接口
 *
 * @author 易诚源码
 */
public interface PracticeVirtualCustomerService {

    /**
     * 创建CRM智能陪练-虚拟客户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeVirtualCustomer(@Valid PracticeVirtualCustomerSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-虚拟客户
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeVirtualCustomer(@Valid PracticeVirtualCustomerSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-虚拟客户
     *
     * @param id 编号
     */
    void deletePracticeVirtualCustomer(Long id);

    /**
    * 批量删除CRM智能陪练-虚拟客户
    *
    * @param ids 编号
    */
    void deletePracticeVirtualCustomerListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-虚拟客户
     *
     * @param id 编号
     * @return CRM智能陪练-虚拟客户
     */
    PracticeVirtualCustomerDO getPracticeVirtualCustomer(Long id);

    /**
     * 获得CRM智能陪练-虚拟客户分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-虚拟客户分页
     */
    PageResult<PracticeVirtualCustomerDO> getPracticeVirtualCustomerPage(PracticeVirtualCustomerPageReqVO pageReqVO);

    /**
     * 快速创建虚拟客户（用于个性化课程创建）
     *
     * @param name 客户姓名
     * @param gender 性别
     * @param age 年龄
     * @param occupation 职业
     * @param industry 所属行业
     * @param personalityType 性格类型
     * @param riskPreference 风险偏好
     * @param memo 自定义参数
     * @return 创建的虚拟客户记录
     */
    PracticeVirtualCustomerDO quickCreateVirtualCustomer(String name, String gender, Integer age,
                                                          String occupation, String industry,
                                                          String personalityType, String riskPreference,
                                                          String memo);

}