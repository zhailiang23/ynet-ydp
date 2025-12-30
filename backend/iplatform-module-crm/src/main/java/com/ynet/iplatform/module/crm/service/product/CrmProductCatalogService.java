package com.ynet.iplatform.module.crm.service.product;

import com.ynet.iplatform.framework.common.util.collection.CollectionUtils;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogListReqVO;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogSaveReqVO;
import com.ynet.iplatform.module.crm.dal.dataobject.product.CrmProductCatalogDO;

import java.util.*;

/**
 * CRM 产品目录 Service 接口
 *
 * @author 易诚源码
 */
public interface CrmProductCatalogService {

    /**
     * 创建产品目录
     *
     * @param createReqVO 产品目录信息
     * @return 目录编号
     */
    Long createProductCatalog(CrmProductCatalogSaveReqVO createReqVO);

    /**
     * 更新产品目录
     *
     * @param updateReqVO 产品目录信息
     */
    void updateProductCatalog(CrmProductCatalogSaveReqVO updateReqVO);

    /**
     * 删除产品目录
     *
     * @param id 目录编号
     */
    void deleteProductCatalog(Long id);

    /**
     * 批量删除产品目录
     *
     * @param ids 目录编号数组
     */
    void deleteProductCatalogList(List<Long> ids);

    /**
     * 获得产品目录信息
     *
     * @param id 目录编号
     * @return 产品目录信息
     */
    CrmProductCatalogDO getProductCatalog(Long id);

    /**
     * 获得产品目录信息数组
     *
     * @param ids 目录编号数组
     * @return 产品目录信息数组
     */
    List<CrmProductCatalogDO> getProductCatalogList(Collection<Long> ids);

    /**
     * 筛选产品目录列表
     *
     * @param reqVO 筛选条件请求 VO
     * @return 产品目录列表
     */
    List<CrmProductCatalogDO> getProductCatalogList(CrmProductCatalogListReqVO reqVO);

    /**
     * 获得指定编号的产品目录 Map
     *
     * @param ids 目录编号数组
     * @return 产品目录 Map
     */
    default Map<Long, CrmProductCatalogDO> getProductCatalogMap(Collection<Long> ids) {
        List<CrmProductCatalogDO> list = getProductCatalogList(ids);
        return CollectionUtils.convertMap(list, CrmProductCatalogDO::getId);
    }

    /**
     * 获得指定目录的所有子目录
     *
     * @param id 目录编号
     * @return 子目录列表
     */
    default List<CrmProductCatalogDO> getChildCatalogList(Long id) {
        return getChildCatalogList(Collections.singleton(id));
    }

    /**
     * 获得指定目录的所有子目录
     *
     * @param ids 目录编号数组
     * @return 子目录列表
     */
    List<CrmProductCatalogDO> getChildCatalogList(Collection<Long> ids);

    /**
     * 校验产品目录们是否有效。如下情况，视为无效：
     * 1. 目录编号不存在
     * 2. 目录被禁用
     *
     * @param ids 目录编号数组
     */
    void validateProductCatalogList(Collection<Long> ids);

}
