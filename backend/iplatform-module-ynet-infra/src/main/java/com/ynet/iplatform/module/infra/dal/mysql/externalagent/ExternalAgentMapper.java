package com.ynet.iplatform.module.infra.dal.mysql.externalagent;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.infra.dal.dataobject.externalagent.ExternalAgentDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.infra.controller.admin.externalagent.vo.*;

/**
 * 外部智能体管理 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ExternalAgentMapper extends BaseMapperX<ExternalAgentDO> {

    default PageResult<ExternalAgentDO> selectPage(ExternalAgentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExternalAgentDO>()
                .likeIfPresent(ExternalAgentDO::getCode, reqVO.getCode())
                .likeIfPresent(ExternalAgentDO::getName, reqVO.getName())
                .eqIfPresent(ExternalAgentDO::getPlatformType, reqVO.getPlatformType())
                .eqIfPresent(ExternalAgentDO::getStatus, reqVO.getStatus())
                .orderByDesc(ExternalAgentDO::getId));
    }

    /**
     * 根据智能体编码查询
     *
     * @param code 智能体编码
     * @return 外部智能体
     */
    default ExternalAgentDO selectByCode(String code) {
        return selectOne(ExternalAgentDO::getCode, code);
    }

}