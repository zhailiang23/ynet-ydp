package com.ynet.iplatform.module.aicrm.service.productcategory;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.productcategory.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.productcategory.ProductCategoryDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.productcategory.ProductCategoryMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 产品类目表（树形结构） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public Long createProductCategory(ProductCategorySaveReqVO createReqVO) {
        // 校验父类目ID（0表示顶级类目）的有效性
        validateParentProductCategory(null, createReqVO.getParentId());
        // 校验类目名称的唯一性
        validateProductCategoryCategoryNameUnique(null, createReqVO.getParentId(), createReqVO.getCategoryName());

        // 插入
        ProductCategoryDO productCategory = BeanUtils.toBean(createReqVO, ProductCategoryDO.class);
        productCategoryMapper.insert(productCategory);

        // 返回
        return productCategory.getId();
    }

    @Override
    public void updateProductCategory(ProductCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateProductCategoryExists(updateReqVO.getId());
        // 校验父类目ID（0表示顶级类目）的有效性
        validateParentProductCategory(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验类目名称的唯一性
        validateProductCategoryCategoryNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getCategoryName());

        // 更新
        ProductCategoryDO updateObj = BeanUtils.toBean(updateReqVO, ProductCategoryDO.class);
        productCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductCategory(Long id) {
        // 校验存在
        validateProductCategoryExists(id);
        // 校验是否有子产品类目表（树形结构）
        if (productCategoryMapper.selectCountByParentId(id) > 0) {
            throw exception(PRODUCT_CATEGORY_EXITS_CHILDREN);
        }
        // 删除
        productCategoryMapper.deleteById(id);
    }


    private void validateProductCategoryExists(Long id) {
        if (productCategoryMapper.selectById(id) == null) {
            throw exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }
    }

    private void validateParentProductCategory(Long id, Long parentId) {
        if (parentId == null || ProductCategoryDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父产品类目表（树形结构）
        if (Objects.equals(id, parentId)) {
            throw exception(PRODUCT_CATEGORY_PARENT_ERROR);
        }
        // 2. 父产品类目表（树形结构）不存在
        ProductCategoryDO parentProductCategory = productCategoryMapper.selectById(parentId);
        if (parentProductCategory == null) {
            throw exception(PRODUCT_CATEGORY_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父产品类目表（树形结构），如果父产品类目表（树形结构）是自己的子产品类目表（树形结构），则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentProductCategory.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(PRODUCT_CATEGORY_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父产品类目表（树形结构）
            if (parentId == null || ProductCategoryDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentProductCategory = productCategoryMapper.selectById(parentId);
            if (parentProductCategory == null) {
                break;
            }
        }
    }

    private void validateProductCategoryCategoryNameUnique(Long id, Long parentId, String categoryName) {
        ProductCategoryDO productCategory = productCategoryMapper.selectByParentIdAndCategoryName(parentId, categoryName);
        if (productCategory == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的产品类目表（树形结构）
        if (id == null) {
            throw exception(PRODUCT_CATEGORY_CATEGORY_NAME_DUPLICATE);
        }
        if (!Objects.equals(productCategory.getId(), id)) {
            throw exception(PRODUCT_CATEGORY_CATEGORY_NAME_DUPLICATE);
        }
    }

    @Override
    public ProductCategoryDO getProductCategory(Long id) {
        return productCategoryMapper.selectById(id);
    }

    @Override
    public List<ProductCategoryDO> getProductCategoryList(ProductCategoryListReqVO listReqVO) {
        return productCategoryMapper.selectList(listReqVO);
    }

}