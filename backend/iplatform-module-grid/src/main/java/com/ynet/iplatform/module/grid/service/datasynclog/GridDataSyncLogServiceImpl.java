package com.ynet.iplatform.module.grid.service.datasynclog;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.datasynclog.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.datasynclog.GridDataSyncLogDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.datasynclog.GridDataSyncLogMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 数据同步记录 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridDataSyncLogServiceImpl implements GridDataSyncLogService {

    @Resource
    private GridDataSyncLogMapper dataSyncLogMapper;

    @Override
    public Long createDataSyncLog(GridDataSyncLogSaveReqVO createReqVO) {
        // 插入
        GridDataSyncLogDO dataSyncLog = BeanUtils.toBean(createReqVO, GridDataSyncLogDO.class);
        dataSyncLogMapper.insert(dataSyncLog);

        // 返回
        return dataSyncLog.getId();
    }

    @Override
    public void updateDataSyncLog(GridDataSyncLogSaveReqVO updateReqVO) {
        // 校验存在
        validateDataSyncLogExists(updateReqVO.getId());
        // 更新
        GridDataSyncLogDO updateObj = BeanUtils.toBean(updateReqVO, GridDataSyncLogDO.class);
        dataSyncLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteDataSyncLog(Long id) {
        // 校验存在
        validateDataSyncLogExists(id);
        // 删除
        dataSyncLogMapper.deleteById(id);
    }

    @Override
        public void deleteDataSyncLogListByIds(List<Long> ids) {
        // 删除
        dataSyncLogMapper.deleteByIds(ids);
        }


    private void validateDataSyncLogExists(Long id) {
        if (dataSyncLogMapper.selectById(id) == null) {
            throw exception(DATA_SYNC_LOG_NOT_EXISTS);
        }
    }

    @Override
    public GridDataSyncLogDO getDataSyncLog(Long id) {
        return dataSyncLogMapper.selectById(id);
    }

    @Override
    public PageResult<GridDataSyncLogDO> getDataSyncLogPage(GridDataSyncLogPageReqVO pageReqVO) {
        return dataSyncLogMapper.selectPage(pageReqVO);
    }

}