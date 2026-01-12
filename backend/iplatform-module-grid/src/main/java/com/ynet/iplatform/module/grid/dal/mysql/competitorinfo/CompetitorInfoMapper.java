package com.ynet.iplatform.module.grid.dal.mysql.competitorinfo;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.competitorinfo.CompetitorInfoDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.competitorinfo.vo.*;

/**
 * 同业信息 Mapper
 *
 * @author 易诚原生智能平台
 */
@Mapper
public interface CompetitorInfoMapper extends BaseMapperX<CompetitorInfoDO> {

    default PageResult<CompetitorInfoDO> selectPage(CompetitorInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompetitorInfoDO>()
                .likeIfPresent(CompetitorInfoDO::getCompetitorName, reqVO.getCompetitorName())
                .likeIfPresent(CompetitorInfoDO::getAddress, reqVO.getAddress())
                .orderByDesc(CompetitorInfoDO::getCreateTime));
    }

}