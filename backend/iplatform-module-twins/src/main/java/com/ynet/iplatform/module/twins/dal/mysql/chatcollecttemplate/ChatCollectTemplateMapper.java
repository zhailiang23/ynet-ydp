package com.ynet.iplatform.module.twins.dal.mysql.chatcollecttemplate;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollecttemplate.ChatCollectTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.twins.controller.admin.chatcollecttemplate.vo.*;

/**
 * 客户留资模板 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ChatCollectTemplateMapper extends BaseMapperX<ChatCollectTemplateDO> {

    default PageResult<ChatCollectTemplateDO> selectPage(ChatCollectTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatCollectTemplateDO>()
                .likeIfPresent(ChatCollectTemplateDO::getName, reqVO.getName())
                .eqIfPresent(ChatCollectTemplateDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ChatCollectTemplateDO::getStatus, reqVO.getStatus())
                .orderByDesc(ChatCollectTemplateDO::getId));
    }

}