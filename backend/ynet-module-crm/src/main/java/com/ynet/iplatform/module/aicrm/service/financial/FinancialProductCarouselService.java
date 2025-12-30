package com.ynet.iplatform.module.aicrm.service.financial;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselSaveReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductCarouselDO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * CRM 金融产品轮播 Service 接口
 *
 * @author 易诚源码
 */
public interface FinancialProductCarouselService {

    /**
     * 创建轮播
     *
     * @param createReqVO 创建信息
     * @return 轮播编号
     */
    Long createCarousel(@Valid FinancialProductCarouselSaveReqVO createReqVO);

    /**
     * 更新轮播
     *
     * @param updateReqVO 更新信息
     */
    void updateCarousel(@Valid FinancialProductCarouselSaveReqVO updateReqVO);

    /**
     * 删除轮播
     *
     * @param id 轮播编号
     */
    void deleteCarousel(Long id);

    /**
     * 批量删除轮播
     *
     * @param ids 轮播编号数组
     */
    void deleteCarouselList(List<Long> ids);

    /**
     * 获得轮播
     *
     * @param id 轮播编号
     * @return 轮播
     */
    FinancialProductCarouselDO getCarousel(Long id);

    /**
     * 获得轮播（RespVO）
     *
     * @param id 轮播编号
     * @return 轮播 RespVO
     */
    FinancialProductCarouselRespVO getCarouselRespVO(Long id);

    /**
     * 获得轮播分页
     *
     * @param pageReqVO 分页查询
     * @return 轮播分页
     */
    PageResult<FinancialProductCarouselRespVO> getCarouselPage(FinancialProductCarouselPageReqVO pageReqVO);

    /**
     * 校验轮播是否存在
     *
     * @param id 轮播编号
     */
    void validateCarouselExists(Long id);

}
