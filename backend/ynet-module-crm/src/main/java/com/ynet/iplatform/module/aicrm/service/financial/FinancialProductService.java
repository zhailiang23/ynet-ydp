package com.ynet.iplatform.module.aicrm.service.financial;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.product.FinancialProductSaveReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppFinancialProductPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppFinancialProductRespVO;
import com.ynet.iplatform.module.aicrm.controller.app.financial.vo.AppCarouselRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductDO;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * CRM 金融产品 Service 接口
 *
 * @author 易诚源码
 */
public interface FinancialProductService {

    /**
     * 创建金融产品
     *
     * @param createReqVO 创建信息
     * @return 产品编号
     */
    Long createFinancialProduct(@Valid FinancialProductSaveReqVO createReqVO);

    /**
     * 更新金融产品
     *
     * @param updateReqVO 更新信息
     */
    void updateFinancialProduct(@Valid FinancialProductSaveReqVO updateReqVO);

    /**
     * 删除金融产品
     *
     * @param id 产品编号
     */
    void deleteFinancialProduct(Long id);

    /**
     * 批量删除金融产品
     *
     * @param ids 产品编号数组
     */
    void deleteFinancialProductList(List<Long> ids);

    /**
     * 获得金融产品
     *
     * @param id 产品编号
     * @return 金融产品
     */
    FinancialProductDO getFinancialProduct(Long id);

    /**
     * 获得金融产品（RespVO）
     *
     * @param id 产品编号
     * @return 金融产品 RespVO
     */
    FinancialProductRespVO getFinancialProductRespVO(Long id);

    /**
     * 获得金融产品列表
     *
     * @param ids 产品编号数组
     * @return 金融产品列表
     */
    List<FinancialProductDO> getFinancialProductList(Collection<Long> ids);

    /**
     * 获得金融产品分页（管理端）
     *
     * @param pageReqVO 分页查询
     * @return 金融产品分页
     */
    PageResult<FinancialProductRespVO> getFinancialProductPage(FinancialProductPageReqVO pageReqVO);

    /**
     * 获得金融产品分页（移动端）
     *
     * @param pageReqVO 分页查询
     * @return 金融产品分页
     */
    PageResult<AppFinancialProductRespVO> getAppFinancialProductPage(AppFinancialProductPageReqVO pageReqVO);

    /**
     * 获得轮播推荐列表（移动端）
     *
     * @return 轮播推荐列表
     */
    List<AppCarouselRespVO> getCarouselList();

    /**
     * 校验金融产品是否存在
     *
     * @param id 产品编号
     */
    void validateFinancialProductExists(Long id);

}
