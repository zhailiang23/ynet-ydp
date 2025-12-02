package com.ynet.iplatform.module.knowledge.dal.mysql.file;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.knowledge.dal.dataobject.file.KonwledgeFileDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.knowledge.controller.admin.file.vo.*;

/**
 * 知识库文件 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface KonwledgeFileMapper extends BaseMapperX<KonwledgeFileDO> {

    default PageResult<KonwledgeFileDO> selectPage(KonwledgeFilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<KonwledgeFileDO>()
                .eqIfPresent(KonwledgeFileDO::getKnowledgeBaseId, reqVO.getBaseId())
                .likeIfPresent(KonwledgeFileDO::getFileName, reqVO.getFileName())
                .eqIfPresent(KonwledgeFileDO::getFileType, reqVO.getFileType())
                .eqIfPresent(KonwledgeFileDO::getStatus, reqVO.getStatus())
                .orderByDesc(KonwledgeFileDO::getId));
    }

}