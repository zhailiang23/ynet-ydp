package com.ynet.iplatform.module.grid.service.info;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.info.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 网格信息 Service 接口
 *
 * @author 易诚源码
 */
public interface GridInfoService {

    /**
     * 创建网格信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInfo(@Valid GridInfoSaveReqVO createReqVO);

    /**
     * 更新网格信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInfo(@Valid GridInfoSaveReqVO updateReqVO);

    /**
     * 删除网格信息
     *
     * @param id 编号
     */
    void deleteInfo(Long id);

    /**
    * 批量删除网格信息
    *
    * @param ids 编号
    */
    void deleteInfoListByIds(List<Long> ids);

    /**
     * 获得网格信息
     *
     * @param id 编号
     * @return 网格信息
     */
    GridInfoDO getInfo(Long id);

    /**
     * 获得网格信息分页
     *
     * @param pageReqVO 分页查询
     * @return 网格信息分页
     */
    PageResult<GridInfoDO> getInfoPage(GridInfoPageReqVO pageReqVO);

}