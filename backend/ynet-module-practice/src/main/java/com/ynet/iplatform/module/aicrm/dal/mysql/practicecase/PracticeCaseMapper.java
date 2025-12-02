package com.ynet.iplatform.module.aicrm.dal.mysql.practicecase;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.practicecase.vo.*;

/**
 * CRM智能陪练-销售案例 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface PracticeCaseMapper extends BaseMapperX<PracticeCaseDO> {

    default PageResult<PracticeCaseDO> selectPage(PracticeCasePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeCaseDO>()
                .eqIfPresent(PracticeCaseDO::getTitle, reqVO.getTitle())
                .eqIfPresent(PracticeCaseDO::getContent, reqVO.getContent())
                .eqIfPresent(PracticeCaseDO::getTags, reqVO.getTags())
                .betweenIfPresent(PracticeCaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeCaseDO::getId));
    }

}