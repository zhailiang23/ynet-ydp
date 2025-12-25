package com.ynet.iplatform.module.grid.service.communitycustomer;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 社区客户 Service 接口
 *
 * 重构说明：
 * 1. 去掉对 grid_community_customer 表的操作
 * 2. 社区客户字段（family_members, housing_type, monthly_income）已合并到 grid_customer 表
 * 3. 根据客户坐标自动查找所属的 COMMUNITY 类型网格
 * 4. 通过 grid_customer_grid_relation 表维护客户-网格多对多关系
 *
 * @author 易诚源码
 */
public interface GridCommunityCustomerService {

    /**
     * 创建社区客户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommunityCustomer(@Valid GridCommunityCustomerSaveReqVO createReqVO);

    /**
     * 更新社区客户
     *
     * @param updateReqVO 更新信息
     */
    void updateCommunityCustomer(@Valid GridCommunityCustomerSaveReqVO updateReqVO);

    /**
     * 删除社区客户
     *
     * @param id 编号
     */
    void deleteCommunityCustomer(Long id);

    /**
    * 批量删除社区客户
    *
    * @param ids 编号
    */
    void deleteCommunityCustomerListByIds(List<Long> ids);

    /**
     * 获得社区客户
     *
     * @param id 编号
     * @return 社区客户
     * @deprecated 此方法已废弃，Controller 层直接调用 Mapper 查询
     */
    @Deprecated
    GridCommunityCustomerRespVO getCommunityCustomer(Long id);

    /**
     * 获得社区客户分页
     *
     * @param pageReqVO 分页查询
     * @return 社区客户分页
     * @deprecated 此方法已废弃，Controller 层直接调用 Mapper 的 selectPageWithRelations 方法
     */
    @Deprecated
    PageResult<GridCommunityCustomerRespVO> getCommunityCustomerPage(GridCommunityCustomerPageReqVO pageReqVO);

}