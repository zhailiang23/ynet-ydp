package cn.iocoder.yudao.module.aicrm.service.customerreturnapplication;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerreturnapplication.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerreturnapplication.CustomerReturnApplicationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户退回申请 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerReturnApplicationService {

    /**
     * 创建客户退回申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerReturnApplication(@Valid CustomerReturnApplicationSaveReqVO createReqVO);

    /**
     * 更新客户退回申请
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerReturnApplication(@Valid CustomerReturnApplicationSaveReqVO updateReqVO);

    /**
     * 删除客户退回申请
     *
     * @param id 编号
     */
    void deleteCustomerReturnApplication(Long id);

    /**
    * 批量删除客户退回申请
    *
    * @param ids 编号
    */
    void deleteCustomerReturnApplicationListByIds(List<Long> ids);

    /**
     * 获得客户退回申请
     *
     * @param id 编号
     * @return 客户退回申请
     */
    CustomerReturnApplicationDO getCustomerReturnApplication(Long id);

    /**
     * 获得客户退回申请分页
     *
     * @param pageReqVO 分页查询
     * @return 客户退回申请分页
     */
    PageResult<CustomerReturnApplicationDO> getCustomerReturnApplicationPage(CustomerReturnApplicationPageReqVO pageReqVO);

}