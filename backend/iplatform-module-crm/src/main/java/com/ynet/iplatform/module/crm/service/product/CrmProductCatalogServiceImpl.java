package com.ynet.iplatform.module.crm.service.product;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ynet.iplatform.framework.common.enums.CommonStatusEnum;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogListReqVO;
import com.ynet.iplatform.module.crm.controller.admin.product.vo.catalog.CrmProductCatalogSaveReqVO;
import com.ynet.iplatform.module.crm.dal.dataobject.product.CrmProductCatalogDO;
import com.ynet.iplatform.module.crm.dal.mysql.product.CrmProductCatalogMapper;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.*;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertSet;
import static com.ynet.iplatform.module.crm.enums.ErrorCodeConstants.*;

/**
 * CRM 产品目录 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
@Slf4j
public class CrmProductCatalogServiceImpl implements CrmProductCatalogService {

    @Resource
    private CrmProductCatalogMapper productCatalogMapper;

    @Override
    public Long createProductCatalog(CrmProductCatalogSaveReqVO createReqVO) {
        if (createReqVO.getParentId() == null) {
            createReqVO.setParentId(CrmProductCatalogDO.PARENT_ID_ROOT);
        }
        // 校验父目录的有效性
        validateParentCatalog(null, createReqVO.getParentId());
        // 校验目录名的唯一性
        validateCatalogNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

        // 插入目录
        CrmProductCatalogDO catalog = BeanUtils.toBean(createReqVO, CrmProductCatalogDO.class);

        // 自动计算 categoryLevel 和 categoryPath
        calculateCategoryFields(catalog);

        productCatalogMapper.insert(catalog);
        return catalog.getId();
    }

    @Override
    public void updateProductCatalog(CrmProductCatalogSaveReqVO updateReqVO) {
        if (updateReqVO.getParentId() == null) {
            updateReqVO.setParentId(CrmProductCatalogDO.PARENT_ID_ROOT);
        }
        // 校验自己存在
        validateCatalogExists(updateReqVO.getId());

        // 获取原目录信息
        CrmProductCatalogDO oldCatalog = productCatalogMapper.selectById(updateReqVO.getId());

        // 校验父目录的有效性
        validateParentCatalog(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验目录名的唯一性
        validateCatalogNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新目录
        CrmProductCatalogDO updateObj = BeanUtils.toBean(updateReqVO, CrmProductCatalogDO.class);

        // 自动计算 categoryLevel 和 categoryPath
        calculateCategoryFields(updateObj);

        productCatalogMapper.updateById(updateObj);

        // 如果父目录发生变化，需要级联更新所有子目录的 categoryLevel 和 categoryPath
        if (!Objects.equals(oldCatalog.getParentId(), updateReqVO.getParentId())) {
            updateChildrenCategoryFields(updateObj.getId());
        }
    }

    @Override
    public void deleteProductCatalog(Long id) {
        // 校验是否存在
        validateCatalogExists(id);
        // 校验是否有子目录
        if (productCatalogMapper.selectCountByParentId(id) > 0) {
            throw exception(PRODUCT_CATALOG_EXISTS_CHILDREN);
        }
        // 删除目录
        productCatalogMapper.deleteById(id);
    }

    @Override
    public void deleteProductCatalogList(List<Long> ids) {
        // 校验是否有子目录
        for (Long id : ids) {
            if (productCatalogMapper.selectCountByParentId(id) > 0) {
                throw exception(PRODUCT_CATALOG_EXISTS_CHILDREN);
            }
        }

        // 批量删除目录
        productCatalogMapper.deleteByIds(ids);
    }

    /**
     * 自动计算 categoryLevel 和 categoryPath
     *
     * @param catalog 产品目录
     */
    private void calculateCategoryFields(CrmProductCatalogDO catalog) {
        if (CrmProductCatalogDO.PARENT_ID_ROOT.equals(catalog.getParentId())) {
            // 顶级目录
            catalog.setCategoryLevel(1);
            catalog.setCategoryPath("/" + catalog.getId() + "/");
        } else {
            // 子目录
            CrmProductCatalogDO parent = productCatalogMapper.selectById(catalog.getParentId());
            catalog.setCategoryLevel(parent.getCategoryLevel() + 1);
            catalog.setCategoryPath(parent.getCategoryPath() + catalog.getId() + "/");
        }
    }

    /**
     * 级联更新所有子目录的 categoryLevel 和 categoryPath
     *
     * @param parentId 父目录 ID
     */
    private void updateChildrenCategoryFields(Long parentId) {
        // 获取所有子目录
        List<CrmProductCatalogDO> children = getChildCatalogList(parentId);

        for (CrmProductCatalogDO child : children) {
            // 重新计算每个子目录的 categoryLevel 和 categoryPath
            CrmProductCatalogDO parent = productCatalogMapper.selectById(child.getParentId());
            if (parent != null) {
                child.setCategoryLevel(parent.getCategoryLevel() + 1);
                child.setCategoryPath(parent.getCategoryPath() + child.getId() + "/");
                productCatalogMapper.updateById(child);
            }
        }
    }

    @VisibleForTesting
    void validateCatalogExists(Long id) {
        if (id == null) {
            return;
        }
        CrmProductCatalogDO catalog = productCatalogMapper.selectById(id);
        if (catalog == null) {
            throw exception(PRODUCT_CATALOG_NOT_EXISTS);
        }
    }

    @VisibleForTesting
    void validateParentCatalog(Long id, Long parentId) {
        if (parentId == null || CrmProductCatalogDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父目录
        if (Objects.equals(id, parentId)) {
            throw exception(PRODUCT_CATALOG_PARENT_ERROR);
        }
        // 2. 父目录不存在
        CrmProductCatalogDO parentCatalog = productCatalogMapper.selectById(parentId);
        if (parentCatalog == null) {
            throw exception(PRODUCT_CATALOG_PARENT_NOT_EXISTS);
        }
        // 3. 递归校验父目录，如果父目录是自己的子目录，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentCatalog.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(PRODUCT_CATALOG_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父目录
            if (parentId == null || CrmProductCatalogDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentCatalog = productCatalogMapper.selectById(parentId);
            if (parentCatalog == null) {
                break;
            }
        }
    }

    @VisibleForTesting
    void validateCatalogNameUnique(Long id, Long parentId, String name) {
        CrmProductCatalogDO catalog = productCatalogMapper.selectByParentIdAndName(parentId, name);
        if (catalog == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的目录
        if (id == null) {
            throw exception(PRODUCT_CATALOG_NAME_DUPLICATE);
        }
        if (ObjectUtil.notEqual(catalog.getId(), id)) {
            throw exception(PRODUCT_CATALOG_NAME_DUPLICATE);
        }
    }

    @Override
    public CrmProductCatalogDO getProductCatalog(Long id) {
        return productCatalogMapper.selectById(id);
    }

    @Override
    public List<CrmProductCatalogDO> getProductCatalogList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return productCatalogMapper.selectByIds(ids);
    }

    @Override
    public List<CrmProductCatalogDO> getProductCatalogList(CrmProductCatalogListReqVO reqVO) {
        List<CrmProductCatalogDO> list = productCatalogMapper.selectList(reqVO);
        list.sort(Comparator.comparing(CrmProductCatalogDO::getSort));
        return list;
    }

    @Override
    public List<CrmProductCatalogDO> getChildCatalogList(Collection<Long> ids) {
        List<CrmProductCatalogDO> children = new LinkedList<>();
        // 遍历每一层
        Collection<Long> parentIds = ids;
        for (int i = 0; i < Short.MAX_VALUE; i++) { // 使用 Short.MAX_VALUE 避免 bug 场景下，存在死循环
            // 查询当前层，所有的子目录
            List<CrmProductCatalogDO> catalogs = productCatalogMapper.selectListByParentId(parentIds);
            // 1. 如果没有子目录，则结束遍历
            if (CollUtil.isEmpty(catalogs)) {
                break;
            }
            // 2. 如果有子目录，继续遍历
            children.addAll(catalogs);
            parentIds = convertSet(catalogs, CrmProductCatalogDO::getId);
        }
        return children;
    }

    @Override
    public void validateProductCatalogList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得目录信息
        Map<Long, CrmProductCatalogDO> catalogMap = getProductCatalogMap(ids);
        // 校验
        ids.forEach(id -> {
            CrmProductCatalogDO catalog = catalogMap.get(id);
            if (catalog == null) {
                throw exception(PRODUCT_CATALOG_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(catalog.getStatus())) {
                throw exception(PRODUCT_CATALOG_NOT_ENABLE, catalog.getName());
            }
        });
    }

}
