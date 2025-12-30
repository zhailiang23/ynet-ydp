package com.ynet.iplatform.module.crm.dal.mysql.product;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogListReqVO;
import com.ynet.iplatform.module.crm.dal.dataobject.product.CrmProductCatalogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * CRM 产品目录 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CrmProductCatalogMapper extends BaseMapperX<CrmProductCatalogDO> {

    default List<CrmProductCatalogDO> selectList(CrmProductCatalogListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CrmProductCatalogDO>()
                .likeIfPresent(CrmProductCatalogDO::getName, reqVO.getName())
                .eqIfPresent(CrmProductCatalogDO::getStatus, reqVO.getStatus()));
    }

    default CrmProductCatalogDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(CrmProductCatalogDO::getParentId, parentId, CrmProductCatalogDO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(CrmProductCatalogDO::getParentId, parentId);
    }

    default List<CrmProductCatalogDO> selectListByParentId(Collection<Long> parentIds) {
        return selectList(CrmProductCatalogDO::getParentId, parentIds);
    }

}
