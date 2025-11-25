package com.ynet.iplatform.module.aicrm.service.customerreturn;

import com.ynet.iplatform.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import com.ynet.iplatform.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 客户退回流程监听器
 *
 * @author 芋道源码
 */
@Component
public class CustomerReturnProcessListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private CustomerReturnService customerReturnService;

    @Override
    protected String getProcessDefinitionKey() {
        return CustomerReturnServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        // 流程结束时更新申请状态
        Long applicationId = Long.valueOf(event.getBusinessKey());
        customerReturnService.updateReturnStatus(applicationId, event.getStatus());
    }

}
