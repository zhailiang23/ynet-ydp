package cn.iocoder.yudao.module.aicrm.dal.mysql.companystock;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companystock.CompanyStockDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.companystock.vo.*;

/**
 * 对公客户股票发行人信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyStockMapper extends BaseMapperX<CompanyStockDO> {

    default PageResult<CompanyStockDO> selectPage(CompanyStockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyStockDO>()
                .eqIfPresent(CompanyStockDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CompanyStockDO::getStockCode, reqVO.getStockCode())
                .likeIfPresent(CompanyStockDO::getStockName, reqVO.getStockName())
                .eqIfPresent(CompanyStockDO::getStockType, reqVO.getStockType())
                .eqIfPresent(CompanyStockDO::getListingExchange, reqVO.getListingExchange())
                .betweenIfPresent(CompanyStockDO::getListingDate, reqVO.getListingDate())
                .eqIfPresent(CompanyStockDO::getListingStatus, reqVO.getListingStatus())
                .eqIfPresent(CompanyStockDO::getIssuePrice, reqVO.getIssuePrice())
                .eqIfPresent(CompanyStockDO::getIssueQuantity, reqVO.getIssueQuantity())
                .eqIfPresent(CompanyStockDO::getIssueAmount, reqVO.getIssueAmount())
                .eqIfPresent(CompanyStockDO::getIssuePeRatio, reqVO.getIssuePeRatio())
                .eqIfPresent(CompanyStockDO::getCurrentPrice, reqVO.getCurrentPrice())
                .eqIfPresent(CompanyStockDO::getMarketValue, reqVO.getMarketValue())
                .eqIfPresent(CompanyStockDO::getCirculatingMarketValue, reqVO.getCirculatingMarketValue())
                .eqIfPresent(CompanyStockDO::getTotalShares, reqVO.getTotalShares())
                .eqIfPresent(CompanyStockDO::getCirculatingShares, reqVO.getCirculatingShares())
                .eqIfPresent(CompanyStockDO::getIndustryCategory, reqVO.getIndustryCategory())
                .eqIfPresent(CompanyStockDO::getSector, reqVO.getSector())
                .eqIfPresent(CompanyStockDO::getIsSt, reqVO.getIsSt())
                .eqIfPresent(CompanyStockDO::getIsStar, reqVO.getIsStar())
                .eqIfPresent(CompanyStockDO::getStockRating, reqVO.getStockRating())
                .betweenIfPresent(CompanyStockDO::getPriceUpdateTime, reqVO.getPriceUpdateTime())
                .eqIfPresent(CompanyStockDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(CompanyStockDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CompanyStockDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyStockDO::getId));
    }

}