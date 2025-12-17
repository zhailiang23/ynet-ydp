package com.ynet.iplatform.module.grid.dal.mysql.huinongcustomerloan;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongcustomerloan.GridHuinongCustomerLoanDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo.GridHuinongCustomerMarkerVO;

/**
 * 惠农贷款客户扩展 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridHuinongCustomerLoanMapper extends BaseMapperX<GridHuinongCustomerLoanDO> {

    default PageResult<GridHuinongCustomerLoanDO> selectPage(GridHuinongCustomerLoanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridHuinongCustomerLoanDO>()
                .eqIfPresent(GridHuinongCustomerLoanDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(GridHuinongCustomerLoanDO::getCustomerCategory, reqVO.getCustomerCategory())
                .eqIfPresent(GridHuinongCustomerLoanDO::getSubdivisionType, reqVO.getSubdivisionType())
                .eqIfPresent(GridHuinongCustomerLoanDO::getBusinessAddress, reqVO.getBusinessAddress())
                .eqIfPresent(GridHuinongCustomerLoanDO::getGender, reqVO.getGender())
                .eqIfPresent(GridHuinongCustomerLoanDO::getCustomerSituation, reqVO.getCustomerSituation())
                .eqIfPresent(GridHuinongCustomerLoanDO::getBusinessYears, reqVO.getBusinessYears())
                .eqIfPresent(GridHuinongCustomerLoanDO::getCurrentFinancing, reqVO.getCurrentFinancing())
                .eqIfPresent(GridHuinongCustomerLoanDO::getCreditDemand, reqVO.getCreditDemand())
                .eqIfPresent(GridHuinongCustomerLoanDO::getDemandMonth, reqVO.getDemandMonth())
                .eqIfPresent(GridHuinongCustomerLoanDO::getDemandPeriod, reqVO.getDemandPeriod())
                .eqIfPresent(GridHuinongCustomerLoanDO::getBusinessProgress, reqVO.getBusinessProgress())
                .eqIfPresent(GridHuinongCustomerLoanDO::getCustomerSource, reqVO.getCustomerSource())
                .eqIfPresent(GridHuinongCustomerLoanDO::getIsApplied, reqVO.getIsApplied())
                .betweenIfPresent(GridHuinongCustomerLoanDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(GridHuinongCustomerLoanDO::getIsApproved, reqVO.getIsApproved())
                .betweenIfPresent(GridHuinongCustomerLoanDO::getApproveTime, reqVO.getApproveTime())
                .likeIfPresent(GridHuinongCustomerLoanDO::getLoanProductName, reqVO.getLoanProductName())
                .eqIfPresent(GridHuinongCustomerLoanDO::getLoanAmount, reqVO.getLoanAmount())
                .eqIfPresent(GridHuinongCustomerLoanDO::getCreditLimit, reqVO.getCreditLimit())
                .eqIfPresent(GridHuinongCustomerLoanDO::getLoanBalance, reqVO.getLoanBalance())
                .eqIfPresent(GridHuinongCustomerLoanDO::getOverdueStatus, reqVO.getOverdueStatus())
                .eqIfPresent(GridHuinongCustomerLoanDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridHuinongCustomerLoanDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridHuinongCustomerLoanDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridHuinongCustomerLoanDO::getId));
    }

    /**
     * 根据ID查询带关联信息的惠农贷款客户扩展
     * @param id 扩展ID
     * @return 惠农贷款客户扩展信息（包含客户姓名、手机号、站点ID）
     */
    GridHuinongCustomerLoanRespVO selectByIdWithRelations(@Param("id") Long id);

    /**
     * 查询带关联信息的惠农贷款客户扩展分页列表
     * @param page MyBatis Plus 分页对象
     * @param reqVO 查询参数
     * @return 分页结果
     */
    IPage<GridHuinongCustomerLoanRespVO> selectPageWithRelations(
            @Param("page") IPage<GridHuinongCustomerLoanRespVO> page,
            @Param("reqVO") GridHuinongCustomerLoanPageReqVO reqVO);

    /**
     * 查询站点下的客户地图标记列表
     * @param stationId 站点ID
     * @return 客户标记列表
     */
    List<GridHuinongCustomerMarkerVO> selectMarkersByStationId(@Param("stationId") Long stationId);

}