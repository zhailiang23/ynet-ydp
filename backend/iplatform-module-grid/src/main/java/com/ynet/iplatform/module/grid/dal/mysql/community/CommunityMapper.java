package com.ynet.iplatform.module.grid.dal.mysql.community;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunityPageReqVO;
import com.ynet.iplatform.module.grid.dal.dataobject.community.CommunityInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 社区信息 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface CommunityMapper extends BaseMapperX<CommunityInfoDO> {

    default PageResult<CommunityInfoDO> selectPage(CommunityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommunityInfoDO>()
                .likeIfPresent(CommunityInfoDO::getCommunityCode, reqVO.getCommunityCode())
                .likeIfPresent(CommunityInfoDO::getCommunityName, reqVO.getCommunityName())
                .eqIfPresent(CommunityInfoDO::getStatus, reqVO.getStatus())
                .orderByDesc(CommunityInfoDO::getId));
    }

}
