package com.ynet.iplatform.module.aicrm.dal.mysql.practicematerial;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.practicematerial.vo.*;

/**
 * CRM智能陪练-培训文件 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeMaterialMapper extends BaseMapperX<PracticeMaterialDO> {

    default PageResult<PracticeMaterialDO> selectPage(PracticeMaterialPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeMaterialDO>()
                .likeIfPresent(PracticeMaterialDO::getName, reqVO.getName())
                .eqIfPresent(PracticeMaterialDO::getFileType, reqVO.getFileType())
                .eqIfPresent(PracticeMaterialDO::getFileUrl, reqVO.getFileUrl())
                .eqIfPresent(PracticeMaterialDO::getFileSize, reqVO.getFileSize())
                .eqIfPresent(PracticeMaterialDO::getContent, reqVO.getContent())
                .eqIfPresent(PracticeMaterialDO::getContentRich, reqVO.getContentRich())
                .betweenIfPresent(PracticeMaterialDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PracticeMaterialDO::getCreator, reqVO.getCreator()) // 过滤创建者
                .orderByDesc(PracticeMaterialDO::getId));
    }

}