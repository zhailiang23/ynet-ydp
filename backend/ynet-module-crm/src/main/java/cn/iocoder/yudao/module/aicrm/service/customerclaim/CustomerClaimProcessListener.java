package cn.iocoder.yudao.module.aicrm.service.customerclaim;

import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.yudao.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 客户认领流程监听器
 *
 * @author 芋道源码
 */
@Component
public class CustomerClaimProcessListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private CustomerClaimService customerClaimService;

    @Override
    protected String getProcessDefinitionKey() {
        return CustomerClaimServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        // 流程结束时更新申请状态
        Long applicationId = Long.valueOf(event.getBusinessKey());
        customerClaimService.updateClaimStatus(applicationId, event.getStatus());
    }

}
