package com.ynet.iplatform.module.aicrm.service.financial;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselSaveReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductCarouselDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.financial.FinancialProductCarouselMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.FINANCIAL_PRODUCT_CAROUSEL_NOT_EXISTS;

/**
 * CRM 金融产品轮播 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
@Slf4j
public class FinancialProductCarouselServiceImpl implements FinancialProductCarouselService {

    @Resource
    private FinancialProductCarouselMapper carouselMapper;

    @Override
    public Long createCarousel(FinancialProductCarouselSaveReqVO createReqVO) {
        // 插入轮播
        FinancialProductCarouselDO carousel = BeanUtils.toBean(createReqVO, FinancialProductCarouselDO.class);
        carouselMapper.insert(carousel);
        return carousel.getId();
    }

    @Override
    public void updateCarousel(FinancialProductCarouselSaveReqVO updateReqVO) {
        // 校验轮播存在性
        validateCarouselExists(updateReqVO.getId());

        // 更新轮播
        FinancialProductCarouselDO updateObj = BeanUtils.toBean(updateReqVO, FinancialProductCarouselDO.class);
        carouselMapper.updateById(updateObj);
    }

    @Override
    public void deleteCarousel(Long id) {
        // 校验轮播存在性
        validateCarouselExists(id);

        // 删除轮播
        carouselMapper.deleteById(id);
    }

    @Override
    public void deleteCarouselList(List<Long> ids) {
        // 校验轮播存在性
        for (Long id : ids) {
            validateCarouselExists(id);
        }

        // 批量删除
        carouselMapper.deleteByIds(ids);
    }

    @Override
    public FinancialProductCarouselDO getCarousel(Long id) {
        return carouselMapper.selectById(id);
    }

    @Override
    public FinancialProductCarouselRespVO getCarouselRespVO(Long id) {
        FinancialProductCarouselDO carousel = carouselMapper.selectById(id);
        if (carousel == null) {
            throw exception(FINANCIAL_PRODUCT_CAROUSEL_NOT_EXISTS);
        }
        return BeanUtils.toBean(carousel, FinancialProductCarouselRespVO.class);
    }

    @Override
    public PageResult<FinancialProductCarouselRespVO> getCarouselPage(FinancialProductCarouselPageReqVO pageReqVO) {
        PageResult<FinancialProductCarouselDO> pageResult = carouselMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, FinancialProductCarouselRespVO.class);
    }

    @Override
    public void validateCarouselExists(Long id) {
        if (id == null) {
            return;
        }
        FinancialProductCarouselDO carousel = carouselMapper.selectById(id);
        if (carousel == null) {
            throw exception(FINANCIAL_PRODUCT_CAROUSEL_NOT_EXISTS);
        }
    }

}
