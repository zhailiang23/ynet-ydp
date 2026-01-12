package com.ynet.iplatform.module.grid.service.huinongextension;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongextension.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongextension.GridHuinongExtensionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 惠农网格扩展 Service 接口
 *
 * @author 易诚源码
 */
public interface GridHuinongExtensionService {

    /**
     * 创建惠农网格扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHuinongExtension(@Valid GridHuinongExtensionSaveReqVO createReqVO);

    /**
     * 更新惠农网格扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateHuinongExtension(@Valid GridHuinongExtensionSaveReqVO updateReqVO);

    /**
     * 删除惠农网格扩展
     *
     * @param id 编号
     */
    void deleteHuinongExtension(Long id);

    /**
    * 批量删除惠农网格扩展
    *
    * @param ids 编号
    */
    void deleteHuinongExtensionListByIds(List<Long> ids);

    /**
     * 获得惠农网格扩展
     *
     * @param id 编号
     * @return 惠农网格扩展
     */
    GridHuinongExtensionDO getHuinongExtension(Long id);

    /**
     * 获得惠农网格扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 惠农网格扩展分页
     */
    PageResult<GridHuinongExtensionDO> getHuinongExtensionPage(GridHuinongExtensionPageReqVO pageReqVO);

}