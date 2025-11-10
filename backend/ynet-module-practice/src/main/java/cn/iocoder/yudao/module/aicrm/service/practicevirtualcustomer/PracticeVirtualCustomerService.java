package cn.iocoder.yudao.module.aicrm.service.practicevirtualcustomer;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicevirtualcustomer.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-虚拟客户 Service 接口
 *
 * @author 芋道源码
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

}