package com.ynet.iplatform.module.aicrm.service.financial;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ynet.iplatform.framework.common.enums.CommonStatusEnum;
import com.ynet.iplatform.framework.common.util.json.JsonUtils;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductSaveReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppCarouselRespVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppFinancialProductPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppFinancialProductRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductCarouselDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.financial.FinancialProductCarouselMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.financial.FinancialProductMapper;
import com.ynet.iplatform.module.aicrm.service.product.ProductCatalogService;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM 金融产品 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
@Slf4j
public class FinancialProductServiceImpl implements FinancialProductService {

    @Resource
    private FinancialProductMapper financialProductMapper;

    @Resource
    private FinancialProductCarouselMapper financialProductCarouselMapper;

    @Resource
    private ProductCatalogService productCatalogService;

    @Override
    public Long createFinancialProduct(FinancialProductSaveReqVO createReqVO) {
        // 校验产品代码唯一性
        validateProductCodeUnique(null, createReqVO.getProductCode());

        // 校验产品目录存在性
        productCatalogService.validateProductCatalogList(Collections.singletonList(createReqVO.getCatalogId()));

        // 插入产品
        FinancialProductDO product = BeanUtils.toBean(createReqVO, FinancialProductDO.class);

        // 处理 tags 字段：List<String> -> JSON String
        if (CollUtil.isNotEmpty(createReqVO.getTags())) {
            product.setTags(JsonUtils.toJsonString(createReqVO.getTags()));
        }

        financialProductMapper.insert(product);
        return product.getId();
    }

    @Override
    public void updateFinancialProduct(FinancialProductSaveReqVO updateReqVO) {
        // 校验产品存在性
        validateFinancialProductExists(updateReqVO.getId());

        // 校验产品代码唯一性
        validateProductCodeUnique(updateReqVO.getId(), updateReqVO.getProductCode());

        // 校验产品目录存在性
        productCatalogService.validateProductCatalogList(Collections.singletonList(updateReqVO.getCatalogId()));

        // 更新产品
        FinancialProductDO updateObj = BeanUtils.toBean(updateReqVO, FinancialProductDO.class);

        // 处理 tags 字段：List<String> -> JSON String
        if (CollUtil.isNotEmpty(updateReqVO.getTags())) {
            updateObj.setTags(JsonUtils.toJsonString(updateReqVO.getTags()));
        } else {
            updateObj.setTags(null);
        }

        financialProductMapper.updateById(updateObj);
    }

    @Override
    public void deleteFinancialProduct(Long id) {
        // 校验产品存在性
        validateFinancialProductExists(id);

        // 删除产品
        financialProductMapper.deleteById(id);
    }

    @Override
    public void deleteFinancialProductList(List<Long> ids) {
        // 校验产品存在性
        for (Long id : ids) {
            validateFinancialProductExists(id);
        }

        // 批量删除
        financialProductMapper.deleteByIds(ids);
    }

    @Override
    public FinancialProductDO getFinancialProduct(Long id) {
        return financialProductMapper.selectById(id);
    }

    @Override
    public FinancialProductRespVO getFinancialProductRespVO(Long id) {
        FinancialProductDO product = financialProductMapper.selectById(id);
        if (product == null) {
            throw exception(FINANCIAL_PRODUCT_NOT_EXISTS);
        }
        return convertToRespVO(product);
    }

    @Override
    public List<FinancialProductDO> getFinancialProductList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return financialProductMapper.selectByIds(ids);
    }

    @Override
    public PageResult<FinancialProductRespVO> getFinancialProductPage(FinancialProductPageReqVO pageReqVO) {
        PageResult<FinancialProductDO> pageResult = financialProductMapper.selectPage(pageReqVO);

        // 转换为 RespVO，并处理 tags 字段
        List<FinancialProductRespVO> respList = pageResult.getList().stream()
                .map(this::convertToRespVO)
                .collect(Collectors.toList());

        return new PageResult<>(respList, pageResult.getTotal());
    }

    @Override
    public PageResult<AppFinancialProductRespVO> getAppFinancialProductPage(AppFinancialProductPageReqVO pageReqVO) {
        // 构建查询条件
        PageResult<FinancialProductDO> pageResult = financialProductMapper.selectPage(
                convertToAdminPageReqVO(pageReqVO));

        // 转换为移动端 VO
        List<AppFinancialProductRespVO> respList = pageResult.getList().stream()
                .map(this::convertToAppRespVO)
                .collect(Collectors.toList());

        return new PageResult<>(respList, pageResult.getTotal());
    }

    @Override
    public List<AppCarouselRespVO> getCarouselList() {
        // 查询启用状态的轮播列表
        List<FinancialProductCarouselDO> carouselList =
                financialProductCarouselMapper.selectListByStatus(CommonStatusEnum.ENABLE.getStatus());

        return BeanUtils.toBean(carouselList, AppCarouselRespVO.class);
    }

    @Override
    @VisibleForTesting
    public void validateFinancialProductExists(Long id) {
        if (id == null) {
            return;
        }
        FinancialProductDO product = financialProductMapper.selectById(id);
        if (product == null) {
            throw exception(FINANCIAL_PRODUCT_NOT_EXISTS);
        }
    }

    /**
     * 校验产品代码唯一性
     *
     * @param id 产品 ID（更新时传入，新增时为 null）
     * @param productCode 产品代码
     */
    @VisibleForTesting
    void validateProductCodeUnique(Long id, String productCode) {
        if (StrUtil.isBlank(productCode)) {
            return;
        }
        FinancialProductDO product = financialProductMapper.selectByProductCode(productCode);
        if (product == null) {
            return;
        }
        // 如果 id 为空，说明是新增，直接报错
        if (id == null) {
            throw exception(FINANCIAL_PRODUCT_CODE_DUPLICATE);
        }
        // 如果 id 不相同，说明产品代码被其他产品占用
        if (!product.getId().equals(id)) {
            throw exception(FINANCIAL_PRODUCT_CODE_DUPLICATE);
        }
    }

    /**
     * 将 DO 转换为管理端 RespVO，处理 tags JSON 字段
     *
     * @param product 产品 DO
     * @return RespVO
     */
    private FinancialProductRespVO convertToRespVO(FinancialProductDO product) {
        FinancialProductRespVO respVO = BeanUtils.toBean(product, FinancialProductRespVO.class);

        // 处理 tags 字段：JSON String -> List<String>
        if (StrUtil.isNotBlank(product.getTags())) {
            try {
                respVO.setTags(JsonUtils.parseArray(product.getTags(), String.class));
            } catch (Exception e) {
                log.warn("解析产品标签失败，产品ID: {}, tags: {}", product.getId(), product.getTags(), e);
                respVO.setTags(Collections.emptyList());
            }
        } else {
            respVO.setTags(Collections.emptyList());
        }

        return respVO;
    }

    /**
     * 将 DO 转换为移动端 AppRespVO
     *
     * @param product 产品 DO
     * @return AppRespVO
     */
    private AppFinancialProductRespVO convertToAppRespVO(FinancialProductDO product) {
        AppFinancialProductRespVO respVO = BeanUtils.toBean(product, AppFinancialProductRespVO.class);

        // 处理 tags 字段：JSON String -> List<String>
        if (StrUtil.isNotBlank(product.getTags())) {
            try {
                respVO.setTags(JsonUtils.parseArray(product.getTags(), String.class));
            } catch (Exception e) {
                log.warn("解析产品标签失败，产品ID: {}, tags: {}", product.getId(), product.getTags(), e);
                respVO.setTags(Collections.emptyList());
            }
        } else {
            respVO.setTags(Collections.emptyList());
        }

        return respVO;
    }

    /**
     * 将移动端分页请求转换为管理端分页请求（复用 Mapper 查询方法）
     *
     * @param appPageReqVO 移动端分页请求
     * @return 管理端分页请求
     */
    private FinancialProductPageReqVO convertToAdminPageReqVO(AppFinancialProductPageReqVO appPageReqVO) {
        FinancialProductPageReqVO adminPageReqVO = new FinancialProductPageReqVO();
        adminPageReqVO.setPageNo(appPageReqVO.getPageNo());
        adminPageReqVO.setPageSize(appPageReqVO.getPageSize());
        adminPageReqVO.setProductName(appPageReqVO.getKeyword()); // 搜索关键词映射到产品名称
        adminPageReqVO.setCategory(appPageReqVO.getCategory());
        adminPageReqVO.setRiskLevel(appPageReqVO.getRiskLevel());
        adminPageReqVO.setStatus(appPageReqVO.getStatus());
        adminPageReqVO.setIsHot(appPageReqVO.getIsHot());
        adminPageReqVO.setIsNew(appPageReqVO.getIsNew());

        // TODO: 移动端的 tags 筛选和 sortBy 排序需要在 Mapper 中单独处理
        // 暂时先使用管理端的查询方法，后续优化时可在 Mapper 中添加移动端专用方法

        return adminPageReqVO;
    }

}
