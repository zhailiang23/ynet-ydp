package com.ynet.iplatform.module.twins.dal.mysql.chatcollectinfo;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollectinfo.ChatCollectInfoDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo.vo.*;

/**
 * 客户留资信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ChatCollectInfoMapper extends BaseMapperX<ChatCollectInfoDO> {

    default PageResult<ChatCollectInfoDO> selectPage(ChatCollectInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatCollectInfoDO>()
                .eqIfPresent(ChatCollectInfoDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(ChatCollectInfoDO::getStatus, reqVO.getStatus())
                .orderByDesc(ChatCollectInfoDO::getId));
    }

}