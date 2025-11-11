package cn.iocoder.yudao.module.aicrm.dal.mysql.practicematerial;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicematerial.vo.*;

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
                .orderByDesc(PracticeMaterialDO::getId));
    }

}