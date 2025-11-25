package com.ynet.iplatform.module.aicrm.service.customerclaim;

import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerclaim.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerclaim.CustomerClaimApplicationDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;

/**
 * 客户认领申请表 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerClaimService {

    /**
     * 提交客户认领申请
     *
     * @param userId 当前用户ID
     * @param createReqVO 申请信息
     * @return 申请ID
     */
    Long applyForClaim(Long userId, @Valid CustomerClaimApplicationApplyReqVO createReqVO);

    /**
     * 取消客户认领申请
     *
     * @param userId 当前用户ID
     * @param id 申请ID
     */
    void cancelClaimApplication(Long userId, Long id);

    /**
     * 获得客户认领申请
     *
     * @param id 编号
     * @return 客户认领申请
     */
    CustomerClaimApplicationDO getClaimApplication(Long id);

    /**
     * 获得客户认领申请详情（包含关联信息）
     *
     * @param id 编号
     * @return 客户认领申请详情
     */
    CustomerClaimApplicationRespVO getClaimApplicationDetail(Long id);

    /**
     * 获得客户认领申请分页
     *
     * @param pageReqVO 分页查询
     * @return 客户认领申请分页
     */
    PageResult<CustomerClaimApplicationRespVO> getClaimApplicationPage(CustomerClaimApplicationPageReqVO pageReqVO);

    /**
     * 更新认领申请状态（由BPM流程回调）
     *
     * @param id 申请ID
     * @param status 流程状态
     */
    void updateClaimStatus(Long id, Integer status);

    /**
     * 处理客户认领流程结束事件
     * <p>
     * 当流程审批通过时,自动分配客户给申请人
     * 当流程审批不通过时,不做任何处理
     *
     * @param processInstanceId 流程实例ID
     */
    void handleClaimProcessEnd(String processInstanceId);

}
