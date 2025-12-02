package com.ynet.iplatform.module.aicrm.dal.mysql.productcategory;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.productcategory.ProductCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.productcategory.vo.*;

/**
 * 产品类目表（树形结构） Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapperX<ProductCategoryDO> {

    default List<ProductCategoryDO> selectList(ProductCategoryListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductCategoryDO>()
                .eqIfPresent(ProductCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .likeIfPresent(ProductCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(ProductCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(ProductCategoryDO::getCategoryLevel, reqVO.getCategoryLevel())
                .eqIfPresent(ProductCategoryDO::getCategoryPath, reqVO.getCategoryPath())
                .eqIfPresent(ProductCategoryDO::getIsLeaf, reqVO.getIsLeaf())
                .eqIfPresent(ProductCategoryDO::getProductType, reqVO.getProductType())
                .eqIfPresent(ProductCategoryDO::getProductCode, reqVO.getProductCode())
                .eqIfPresent(ProductCategoryDO::getProductDesc, reqVO.getProductDesc())
                .eqIfPresent(ProductCategoryDO::getCurrencyType, reqVO.getCurrencyType())
                .eqIfPresent(ProductCategoryDO::getInterestRateMin, reqVO.getInterestRateMin())
                .eqIfPresent(ProductCategoryDO::getInterestRateMax, reqVO.getInterestRateMax())
                .eqIfPresent(ProductCategoryDO::getTermMin, reqVO.getTermMin())
                .eqIfPresent(ProductCategoryDO::getTermMax, reqVO.getTermMax())
                .eqIfPresent(ProductCategoryDO::getMinAmount, reqVO.getMinAmount())
                .eqIfPresent(ProductCategoryDO::getMaxAmount, reqVO.getMaxAmount())
                .eqIfPresent(ProductCategoryDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(ProductCategoryDO::getCustomerType, reqVO.getCustomerType())
                .eqIfPresent(ProductCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProductCategoryDO::getSortOrder, reqVO.getSortOrder())
                .eqIfPresent(ProductCategoryDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ProductCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductCategoryDO::getId));
    }

	default ProductCategoryDO selectByParentIdAndCategoryName(Long parentId, String categoryName) {
	    return selectOne(ProductCategoryDO::getParentId, parentId, ProductCategoryDO::getCategoryName, categoryName);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(ProductCategoryDO::getParentId, parentId);
    }

}