package com.ynet.iplatform.module.knowledge.dal.mysql.base;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.knowledge.dal.dataobject.base.KnowledgeBaseDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.knowledge.controller.admin.base.vo.*;

/**
 * 知识库 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface KnowledgeBaseMapper extends BaseMapperX<KnowledgeBaseDO> {

    default PageResult<KnowledgeBaseDO> selectPage(KnowledgeBasePageReqVO reqVO) {
        LambdaQueryWrapperX<KnowledgeBaseDO> query = new LambdaQueryWrapperX<KnowledgeBaseDO>()
                .likeIfPresent(KnowledgeBaseDO::getName, reqVO.getName())
                .eqIfPresent(KnowledgeBaseDO::getDescription, reqVO.getDescription())
                .eqIfPresent(KnowledgeBaseDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(KnowledgeBaseDO::getCreateTime, reqVO.getCreateTime());

        // 根据 isPublic 过滤知识库类型
        if (reqVO.getIsPublic() != null) {
            if (Boolean.TRUE.equals(reqVO.getIsPublic())) {
                // 公共知识库：ownerId 为 null
                query.isNull(KnowledgeBaseDO::getOwnerId);
            } else {
                // 个人知识库：ownerId 不为 null
                query.isNotNull(KnowledgeBaseDO::getOwnerId);
            }
        }
        // isPublic 为 null 时不过滤，查询所有知识库

        return selectPage(reqVO, query.orderByDesc(KnowledgeBaseDO::getId));
    }

    default KnowledgeBaseDO selectOneByOwnerId(Long ownerId) {
        return selectOne(new LambdaQueryWrapperX<KnowledgeBaseDO>()
                .eq(KnowledgeBaseDO::getOwnerId, ownerId));
    }

}