package com.ynet.iplatform.module.aicrm.service.customertransfer;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.aicrm.controller.admin.customertransfer.vo.CustomerTransferApplicationApplyReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.customertransfer.vo.CustomerTransferApplicationPageReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customertransfer.CustomerTransferApplicationDO;

/**
 * 客户移交申请表 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerTransferService {

    /**
     * 提交客户移交申请
     *
     * @param userId 当前用户ID
     * @param createReqVO 创建信息
     * @return 申请ID
     */
    Long applyForTransfer(Long userId, CustomerTransferApplicationApplyReqVO createReqVO);

    /**
     * 取消客户移交申请
     *
     * @param userId 当前用户ID
     * @param id 申请ID
     */
    void cancelTransferApplication(Long userId, Long id);

    /**
     * 获得客户移交申请
     *
     * @param id 申请ID
     * @return 客户移交申请
     */
    CustomerTransferApplicationDO getTransferApplication(Long id);

    /**
     * 获得客户移交申请分页
     *
     * @param pageReqVO 分页查询
     * @return 客户移交申请分页
     */
    PageResult<CustomerTransferApplicationDO> getTransferApplicationPage(CustomerTransferApplicationPageReqVO pageReqVO);

    /**
     * 更新移交申请状态（BPM 回调）
     *
     * @param id 申请ID
     * @param status 状态
     */
    void updateTransferStatus(Long id, Integer status);

}
