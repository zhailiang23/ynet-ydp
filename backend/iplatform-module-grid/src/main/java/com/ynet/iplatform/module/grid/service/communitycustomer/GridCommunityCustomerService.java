package com.ynet.iplatform.module.grid.service.communitycustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.communitycustomer.GridCommunityCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 社区客户扩展 Service 接口
 *
 * @author 易诚源码
 */
public interface GridCommunityCustomerService {

    /**
     * 创建社区客户扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommunityCustomer(@Valid GridCommunityCustomerSaveReqVO createReqVO);

    /**
     * 更新社区客户扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateCommunityCustomer(@Valid GridCommunityCustomerSaveReqVO updateReqVO);

    /**
     * 删除社区客户扩展
     *
     * @param id 编号
     */
    void deleteCommunityCustomer(Long id);

    /**
    * 批量删除社区客户扩展
    *
    * @param ids 编号
    */
    void deleteCommunityCustomerListByIds(List<Long> ids);

    /**
     * 获得社区客户扩展
     *
     * @param id 编号
     * @return 社区客户扩展
     */
    GridCommunityCustomerDO getCommunityCustomer(Long id);

    /**
     * 获得社区客户扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 社区客户扩展分页
     */
    PageResult<GridCommunityCustomerDO> getCommunityCustomerPage(GridCommunityCustomerPageReqVO pageReqVO);

}