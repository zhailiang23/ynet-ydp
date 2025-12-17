package com.ynet.iplatform.module.grid.service.zerodaimarketdescription;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaimarketdescription.GridZerodaiMarketDescriptionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 零贷市场描述 Service 接口
 *
 * @author 易诚源码
 */
public interface GridZerodaiMarketDescriptionService {

    /**
     * 创建零贷市场描述
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createZerodaiMarketDescription(@Valid GridZerodaiMarketDescriptionSaveReqVO createReqVO);

    /**
     * 更新零贷市场描述
     *
     * @param updateReqVO 更新信息
     */
    void updateZerodaiMarketDescription(@Valid GridZerodaiMarketDescriptionSaveReqVO updateReqVO);

    /**
     * 删除零贷市场描述
     *
     * @param id 编号
     */
    void deleteZerodaiMarketDescription(Long id);

    /**
    * 批量删除零贷市场描述
    *
    * @param ids 编号
    */
    void deleteZerodaiMarketDescriptionListByIds(List<Long> ids);

    /**
     * 获得零贷市场描述
     *
     * @param id 编号
     * @return 零贷市场描述
     */
    GridZerodaiMarketDescriptionDO getZerodaiMarketDescription(Long id);

    /**
     * 获得零贷市场描述分页
     *
     * @param pageReqVO 分页查询
     * @return 零贷市场描述分页
     */
    PageResult<GridZerodaiMarketDescriptionDO> getZerodaiMarketDescriptionPage(GridZerodaiMarketDescriptionPageReqVO pageReqVO);

}