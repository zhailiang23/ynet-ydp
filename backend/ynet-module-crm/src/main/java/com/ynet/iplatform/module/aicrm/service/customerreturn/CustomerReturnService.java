package com.ynet.iplatform.module.aicrm.service.customerreturn;

import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerreturn.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerreturn.CustomerReturnApplicationDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 客户退回申请表 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerReturnService {

    /**
     * 提交客户退回申请
     *
     * @param userId 当前用户ID
     * @param createReqVO 申请信息
     * @return 申请ID
     */
    Long applyForReturn(Long userId, @Valid CustomerReturnApplicationApplyReqVO createReqVO);

    /**
     * 取消客户退回申请
     *
     * @param userId 当前用户ID
     * @param id 申请ID
     */
    void cancelReturnApplication(Long userId, Long id);

    /**
     * 获得客户退回申请
     *
     * @param id 编号
     * @return 客户退回申请
     */
    CustomerReturnApplicationDO getReturnApplication(Long id);

    /**
     * 获得客户退回申请分页
     *
     * @param pageReqVO 分页查询
     * @return 客户退回申请分页
     */
    PageResult<CustomerReturnApplicationDO> getReturnApplicationPage(CustomerReturnApplicationPageReqVO pageReqVO);

    /**
     * 更新退回申请状态（由BPM流程回调）
     *
     * @param id 申请ID
     * @param status 流程状态
     */
    void updateReturnStatus(Long id, Integer status);

}
