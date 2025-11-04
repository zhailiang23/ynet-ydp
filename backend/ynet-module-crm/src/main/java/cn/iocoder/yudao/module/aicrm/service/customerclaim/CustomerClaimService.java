package cn.iocoder.yudao.module.aicrm.service.customerclaim;

import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerclaim.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerclaim.CustomerClaimApplicationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

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
    PageResult<CustomerClaimApplicationDO> getClaimApplicationPage(CustomerClaimApplicationPageReqVO pageReqVO);

    /**
     * 更新认领申请状态（由BPM流程回调）
     *
     * @param id 申请ID
     * @param status 流程状态
     */
    void updateClaimStatus(Long id, Integer status);

}
