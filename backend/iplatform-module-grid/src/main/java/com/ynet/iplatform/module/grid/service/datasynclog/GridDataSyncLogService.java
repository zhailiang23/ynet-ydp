package com.ynet.iplatform.module.grid.service.datasynclog;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.datasynclog.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.datasynclog.GridDataSyncLogDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 数据同步记录 Service 接口
 *
 * @author 易诚源码
 */
public interface GridDataSyncLogService {

    /**
     * 创建数据同步记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDataSyncLog(@Valid GridDataSyncLogSaveReqVO createReqVO);

    /**
     * 更新数据同步记录
     *
     * @param updateReqVO 更新信息
     */
    void updateDataSyncLog(@Valid GridDataSyncLogSaveReqVO updateReqVO);

    /**
     * 删除数据同步记录
     *
     * @param id 编号
     */
    void deleteDataSyncLog(Long id);

    /**
    * 批量删除数据同步记录
    *
    * @param ids 编号
    */
    void deleteDataSyncLogListByIds(List<Long> ids);

    /**
     * 获得数据同步记录
     *
     * @param id 编号
     * @return 数据同步记录
     */
    GridDataSyncLogDO getDataSyncLog(Long id);

    /**
     * 获得数据同步记录分页
     *
     * @param pageReqVO 分页查询
     * @return 数据同步记录分页
     */
    PageResult<GridDataSyncLogDO> getDataSyncLogPage(GridDataSyncLogPageReqVO pageReqVO);

}