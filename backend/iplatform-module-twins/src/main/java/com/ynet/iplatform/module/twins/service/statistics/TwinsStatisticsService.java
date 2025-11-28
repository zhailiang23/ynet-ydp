package com.ynet.iplatform.module.twins.service.statistics;

import com.ynet.iplatform.module.twins.controller.admin.statistics.vo.TwinsOverviewRespVO;

/**
 * 数字分身统计服务接口
 */
public interface TwinsStatisticsService {

    /**
     * 获取统计概览数据
     *
     * @return 统计概览数据
     */
    TwinsOverviewRespVO getOverview();

}
