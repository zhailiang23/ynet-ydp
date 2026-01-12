package com.ynet.iplatform.module.grid.dal.mysql.huinongextension;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongextension.GridHuinongExtensionDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.huinongextension.vo.*;

/**
 * 惠农网格扩展 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface GridHuinongExtensionMapper extends BaseMapperX<GridHuinongExtensionDO> {

    default PageResult<GridHuinongExtensionDO> selectPage(GridHuinongExtensionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GridHuinongExtensionDO>()
                .eqIfPresent(GridHuinongExtensionDO::getGridId, reqVO.getGridId())
                .eqIfPresent(GridHuinongExtensionDO::getIsMarketingSite, reqVO.getIsMarketingSite())
                .eqIfPresent(GridHuinongExtensionDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(GridHuinongExtensionDO::getCreator, reqVO.getCreatorId())
                .betweenIfPresent(GridHuinongExtensionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(GridHuinongExtensionDO::getUpdater, reqVO.getUpdaterId())
                .orderByDesc(GridHuinongExtensionDO::getId));
    }

}