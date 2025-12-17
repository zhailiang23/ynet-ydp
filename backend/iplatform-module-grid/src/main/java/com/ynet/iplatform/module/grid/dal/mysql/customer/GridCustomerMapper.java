package com.ynet.iplatform.module.grid.dal.mysql.customer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.customer.vo.*;

/**
 * 网格客户档案 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridCustomerMapper extends BaseMapperX<GridCustomerDO> {

    default PageResult<GridCustomerDO> selectPage(GridCustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridCustomerDO>()
                .eqIfPresent(GridCustomerDO::getGridId, reqVO.getGridId())
                .eqIfPresent(GridCustomerDO::getCustomerType, reqVO.getCustomerType())
                .likeIfPresent(GridCustomerDO::getCustomerName, reqVO.getCustomerName())
                .eqIfPresent(GridCustomerDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(GridCustomerDO::getPhone, reqVO.getPhone())
                .eqIfPresent(GridCustomerDO::getPhoneVerified, reqVO.getPhoneVerified())
                .eqIfPresent(GridCustomerDO::getAddress, reqVO.getAddress())
                .eqIfPresent(GridCustomerDO::getLocation, reqVO.getLocation())
                .eqIfPresent(GridCustomerDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(GridCustomerDO::getSource, reqVO.getSource())
                .eqIfPresent(GridCustomerDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GridCustomerDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridCustomerDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridCustomerDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridCustomerDO::getId));
    }

    /**
     * 插入客户并设置位置坐标
     * @param customer 客户信息
     * @param longitude 经度
     * @param latitude 纬度
     * @return 影响行数
     */
    int insertWithLocation(@Param("customer") GridCustomerDO customer,
                          @Param("longitude") Double longitude,
                          @Param("latitude") Double latitude);

    /**
     * 更新客户并设置位置坐标
     * @param customer 客户信息
     * @param longitude 经度
     * @param latitude 纬度
     * @return 影响行数
     */
    int updateWithLocation(@Param("customer") GridCustomerDO customer,
                          @Param("longitude") Double longitude,
                          @Param("latitude") Double latitude);

}