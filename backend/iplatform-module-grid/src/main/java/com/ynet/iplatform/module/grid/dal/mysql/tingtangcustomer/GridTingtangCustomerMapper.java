package com.ynet.iplatform.module.grid.dal.mysql.tingtangcustomer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.tingtangcustomer.GridTingtangCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo.*;

/**
 * 厅堂客户扩展 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridTingtangCustomerMapper extends BaseMapperX<GridTingtangCustomerDO> {

    default PageResult<GridTingtangCustomerDO> selectPage(GridTingtangCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridTingtangCustomerDO>()
                .eqIfPresent(GridTingtangCustomerDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(GridTingtangCustomerDO::getGender, reqVO.getGender())
                .eqIfPresent(GridTingtangCustomerDO::getCustomerGroup, reqVO.getCustomerGroup())
                .eqIfPresent(GridTingtangCustomerDO::getHasCrmNumber, reqVO.getHasCrmNumber())
                .eqIfPresent(GridTingtangCustomerDO::getCrmCustomerId, reqVO.getCrmCustomerId())
                .eqIfPresent(GridTingtangCustomerDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridTingtangCustomerDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridTingtangCustomerDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridTingtangCustomerDO::getId));
    }

}