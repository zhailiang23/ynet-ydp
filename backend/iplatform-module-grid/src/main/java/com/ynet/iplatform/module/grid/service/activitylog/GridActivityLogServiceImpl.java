package com.ynet.iplatform.module.grid.service.activitylog;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.activitylog.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.activitylog.GridActivityLogDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.activitylog.GridActivityLogMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 网格活动记录 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridActivityLogServiceImpl implements GridActivityLogService {

    @Resource
    private GridActivityLogMapper activityLogMapper;

    @Override
    public Long createActivityLog(GridActivityLogSaveReqVO createReqVO) {
        // 插入
        GridActivityLogDO activityLog = BeanUtils.toBean(createReqVO, GridActivityLogDO.class);
        activityLogMapper.insert(activityLog);

        // 返回
        return activityLog.getId();
    }

    @Override
    public void updateActivityLog(GridActivityLogSaveReqVO updateReqVO) {
        // 校验存在
        validateActivityLogExists(updateReqVO.getId());
        // 更新
        GridActivityLogDO updateObj = BeanUtils.toBean(updateReqVO, GridActivityLogDO.class);
        activityLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteActivityLog(Long id) {
        // 校验存在
        validateActivityLogExists(id);
        // 删除
        activityLogMapper.deleteById(id);
    }

    @Override
        public void deleteActivityLogListByIds(List<Long> ids) {
        // 删除
        activityLogMapper.deleteByIds(ids);
        }


    private void validateActivityLogExists(Long id) {
        if (activityLogMapper.selectById(id) == null) {
            throw exception(ACTIVITY_LOG_NOT_EXISTS);
        }
    }

    @Override
    public GridActivityLogDO getActivityLog(Long id) {
        return activityLogMapper.selectById(id);
    }

    @Override
    public PageResult<GridActivityLogDO> getActivityLogPage(GridActivityLogPageReqVO pageReqVO) {
        return activityLogMapper.selectPage(pageReqVO);
    }

}