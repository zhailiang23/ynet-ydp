package com.ynet.iplatform.module.grid.service.statistics;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.statistics.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.statistics.GridStatisticsDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.statistics.GridStatisticsMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 网格统计报表 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridStatisticsServiceImpl implements GridStatisticsService {

    @Resource
    private GridStatisticsMapper statisticsMapper;

    @Override
    public Long createStatistics(GridStatisticsSaveReqVO createReqVO) {
        // 插入
        GridStatisticsDO statistics = BeanUtils.toBean(createReqVO, GridStatisticsDO.class);
        statisticsMapper.insert(statistics);

        // 返回
        return statistics.getId();
    }

    @Override
    public void updateStatistics(GridStatisticsSaveReqVO updateReqVO) {
        // 校验存在
        validateStatisticsExists(updateReqVO.getId());
        // 更新
        GridStatisticsDO updateObj = BeanUtils.toBean(updateReqVO, GridStatisticsDO.class);
        statisticsMapper.updateById(updateObj);
    }

    @Override
    public void deleteStatistics(Long id) {
        // 校验存在
        validateStatisticsExists(id);
        // 删除
        statisticsMapper.deleteById(id);
    }

    @Override
        public void deleteStatisticsListByIds(List<Long> ids) {
        // 删除
        statisticsMapper.deleteByIds(ids);
        }


    private void validateStatisticsExists(Long id) {
        if (statisticsMapper.selectById(id) == null) {
            throw exception(STATISTICS_NOT_EXISTS);
        }
    }

    @Override
    public GridStatisticsDO getStatistics(Long id) {
        return statisticsMapper.selectById(id);
    }

    @Override
    public PageResult<GridStatisticsDO> getStatisticsPage(GridStatisticsPageReqVO pageReqVO) {
        return statisticsMapper.selectPage(pageReqVO);
    }

}