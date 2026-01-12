package com.ynet.iplatform.module.grid.dal.mysql.zerodaicustomer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer.GridZerodaiCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo.*;

/**
 * 零贷客户扩展 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridZerodaiCustomerMapper extends BaseMapperX<GridZerodaiCustomerDO> {

    /**
     * 查询零贷客户扩展分页（带关联信息）
     */
    IPage<GridZerodaiCustomerRespVO> selectPageWithRelations(IPage<GridZerodaiCustomerRespVO> page,
                                                              @Param("customerName") String customerName,
                                                              @Param("principalName") String principalName);

    /**
     * 查询零贷客户扩展详情（带关联信息）
     */
    GridZerodaiCustomerRespVO selectByIdWithRelations(@Param("id") Long id);

    // 注：原 selectPage 方法已废弃，因查询条件已简化，现统一使用 selectPageWithRelations

}