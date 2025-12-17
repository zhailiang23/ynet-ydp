package com.ynet.iplatform.module.grid.service.activitylog;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.activitylog.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.activitylog.GridActivityLogDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 网格活动记录 Service 接口
 *
 * @author 易诚源码
 */
public interface GridActivityLogService {

    /**
     * 创建网格活动记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createActivityLog(@Valid GridActivityLogSaveReqVO createReqVO);

    /**
     * 更新网格活动记录
     *
     * @param updateReqVO 更新信息
     */
    void updateActivityLog(@Valid GridActivityLogSaveReqVO updateReqVO);

    /**
     * 删除网格活动记录
     *
     * @param id 编号
     */
    void deleteActivityLog(Long id);

    /**
    * 批量删除网格活动记录
    *
    * @param ids 编号
    */
    void deleteActivityLogListByIds(List<Long> ids);

    /**
     * 获得网格活动记录
     *
     * @param id 编号
     * @return 网格活动记录
     */
    GridActivityLogDO getActivityLog(Long id);

    /**
     * 获得网格活动记录分页
     *
     * @param pageReqVO 分页查询
     * @return 网格活动记录分页
     */
    PageResult<GridActivityLogDO> getActivityLogPage(GridActivityLogPageReqVO pageReqVO);

}