package cn.iocoder.yudao.module.aicrm.service.productcategory;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.productcategory.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.productcategory.ProductCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 产品类目表（树形结构） Service 接口
 *
 * @author 芋道源码
 */
public interface ProductCategoryService {

    /**
     * 创建产品类目表（树形结构）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductCategory(@Valid ProductCategorySaveReqVO createReqVO);

    /**
     * 更新产品类目表（树形结构）
     *
     * @param updateReqVO 更新信息
     */
    void updateProductCategory(@Valid ProductCategorySaveReqVO updateReqVO);

    /**
     * 删除产品类目表（树形结构）
     *
     * @param id 编号
     */
    void deleteProductCategory(Long id);


    /**
     * 获得产品类目表（树形结构）
     *
     * @param id 编号
     * @return 产品类目表（树形结构）
     */
    ProductCategoryDO getProductCategory(Long id);

    /**
     * 获得产品类目表（树形结构）列表
     *
     * @param listReqVO 查询条件
     * @return 产品类目表（树形结构）列表
     */
    List<ProductCategoryDO> getProductCategoryList(ProductCategoryListReqVO listReqVO);

}