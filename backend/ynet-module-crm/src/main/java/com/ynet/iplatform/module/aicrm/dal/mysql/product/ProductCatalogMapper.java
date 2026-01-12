package com.ynet.iplatform.module.aicrm.dal.mysql.product;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.aicrm.controller.admin.product.vo.catalog.ProductCatalogListReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.product.ProductCatalogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * CRM 产品目录 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ProductCatalogMapper extends BaseMapperX<ProductCatalogDO> {

    default List<ProductCatalogDO> selectList(ProductCatalogListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductCatalogDO>()
                .likeIfPresent(ProductCatalogDO::getName, reqVO.getName())
                .eqIfPresent(ProductCatalogDO::getStatus, reqVO.getStatus()));
    }

    default ProductCatalogDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(ProductCatalogDO::getParentId, parentId, ProductCatalogDO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(ProductCatalogDO::getParentId, parentId);
    }

    default List<ProductCatalogDO> selectListByParentId(Collection<Long> parentIds) {
        return selectList(ProductCatalogDO::getParentId, parentIds);
    }

}
