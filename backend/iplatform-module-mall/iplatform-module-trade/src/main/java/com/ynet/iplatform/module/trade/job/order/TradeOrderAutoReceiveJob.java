package com.ynet.iplatform.module.trade.job.order;

import com.ynet.iplatform.framework.quartz.core.handler.JobHandler;
import com.ynet.iplatform.framework.tenant.core.job.TenantJob;
import com.ynet.iplatform.module.trade.service.order.TradeOrderUpdateService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 交易订单的自动收货 Job
 *
 * @author 易诚源码
 */
@Component
public class TradeOrderAutoReceiveJob implements JobHandler {

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;

    @Override
    @TenantJob
    public String execute(String param) {
        int count = tradeOrderUpdateService.receiveOrderBySystem();
        return String.format("自动收货 %s 个", count);
    }

}
