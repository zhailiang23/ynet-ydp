package com.ynet.iplatform.module.aicrm.dal.mysql.financial;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel.FinancialProductCarouselPageReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.financial.FinancialProductCarouselDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CRM 金融产品轮播推荐 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface FinancialProductCarouselMapper extends BaseMapperX<FinancialProductCarouselDO> {

    default List<FinancialProductCarouselDO> selectListByStatus(Integer status) {
        return selectList(new LambdaQueryWrapperX<FinancialProductCarouselDO>()
                .eqIfPresent(FinancialProductCarouselDO::getStatus, status)
                .orderByAsc(FinancialProductCarouselDO::getSort)
                .orderByDesc(FinancialProductCarouselDO::getId));
    }

    default PageResult<FinancialProductCarouselDO> selectPage(FinancialProductCarouselPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FinancialProductCarouselDO>()
                .likeIfPresent(FinancialProductCarouselDO::getTitle, reqVO.getTitle())
                .eqIfPresent(FinancialProductCarouselDO::getStatus, reqVO.getStatus())
                .orderByAsc(FinancialProductCarouselDO::getSort)
                .orderByDesc(FinancialProductCarouselDO::getId));
    }

}
