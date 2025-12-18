package com.ynet.iplatform.module.grid.dal.mysql.communitycustomer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.communitycustomer.GridCommunityCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;

import java.util.List;

/**
 * 社区客户扩展 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridCommunityCustomerMapper extends BaseMapperX<GridCommunityCustomerDO> {

    /**
     * 查询社区客户扩展分页（带关联信息）
     */
    IPage<GridCommunityCustomerRespVO> selectPageWithRelations(IPage<GridCommunityCustomerRespVO> page, @Param("customerName") String customerName);

    // 注：原 selectPage 方法已废弃，因查询条件已简化，现统一使用 selectPageWithRelations

}