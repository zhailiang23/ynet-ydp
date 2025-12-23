package com.ynet.iplatform.module.grid.dal.mysql.tingtangcustomer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.tingtangcustomer.GridTingtangCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo.*;

/**
 * 厅堂客户 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridTingtangCustomerMapper extends BaseMapperX<GridTingtangCustomerDO> {

    default PageResult<GridTingtangCustomerDO> selectPage(GridTingtangCustomerPageReqVO reqVO) {
        // 注意：此方法已不再使用，Service 层直接调用 selectPageWithRelations 方法
        // 返回空结果以保持接口兼容性
        return new PageResult<>(new ArrayList<>(), 0L);
    }

    /**
     * 根据ID查询厅堂客户(关联 grid_customer 表)
     */
    GridTingtangCustomerRespVO selectTingtangCustomerById(@Param("id") Long id);

    /**
     * 分页查询厅堂客户(关联 grid_customer 表)
     */
    com.baomidou.mybatisplus.core.metadata.IPage<GridTingtangCustomerRespVO> selectPageWithRelations(
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<GridTingtangCustomerRespVO> page,
            @Param("customerName") String customerName,
            @Param("phone") String phone);

}
