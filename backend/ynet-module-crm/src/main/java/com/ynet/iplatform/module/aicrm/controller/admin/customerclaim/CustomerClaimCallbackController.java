package com.ynet.iplatform.module.aicrm.controller.admin.customerclaim;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.module.aicrm.service.customerclaim.CustomerClaimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * 客户认领流程回调 Controller
 * <p>
 * 用于接收 BPM 流程引擎的后置通知
 *
 * @author 芋道源码
 */
@Tag(name = "管理后台 - 客户认领流程回调")
@RestController
@RequestMapping("/aicrm/customer-claim/callback")
@Slf4j
public class CustomerClaimCallbackController {

    @Resource
    private CustomerClaimService customerClaimService;

    @PostMapping("/process-end")
    @Operation(summary = "流程结束回调")
    @Parameter(name = "processInstanceId", description = "流程实例ID", required = true)
    public CommonResult<Boolean> processEndCallback(@RequestParam("processInstanceId") String processInstanceId) {
        log.info("[processEndCallback][接收到客户认领流程结束回调，流程实例ID: {}]", processInstanceId);

        try {
            // 处理流程结束后的业务逻辑
            customerClaimService.handleClaimProcessEnd(processInstanceId);
            return success(true);
        } catch (Exception e) {
            log.error("[processEndCallback][处理客户认领流程结束回调失败，流程实例ID: {}]", processInstanceId, e);
            // 即使失败也返回成功，避免影响流程引擎
            return success(false);
        }
    }

}
