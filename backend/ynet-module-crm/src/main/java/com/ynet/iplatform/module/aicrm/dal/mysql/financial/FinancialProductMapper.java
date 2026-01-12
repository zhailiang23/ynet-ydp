package com.ynet.iplatform.module.aicrm.dal.mysql.financial;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductPageReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * CRM 金融产品 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface FinancialProductMapper extends BaseMapperX<FinancialProductDO> {

    default PageResult<FinancialProductDO> selectPage(FinancialProductPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FinancialProductDO>()
                .likeIfPresent(FinancialProductDO::getProductName, reqVO.getProductName())
                .likeIfPresent(FinancialProductDO::getProductCode, reqVO.getProductCode())
                .eqIfPresent(FinancialProductDO::getCatalogId, reqVO.getCatalogId())
                .eqIfPresent(FinancialProductDO::getCategory, reqVO.getCategory())
                .eqIfPresent(FinancialProductDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(FinancialProductDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FinancialProductDO::getIsHot, reqVO.getIsHot())
                .eqIfPresent(FinancialProductDO::getIsNew, reqVO.getIsNew())
                .betweenIfPresent(FinancialProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FinancialProductDO::getId));
    }

    default FinancialProductDO selectByProductCode(String productCode) {
        return selectOne(FinancialProductDO::getProductCode, productCode);
    }

}
