package com.ynet.iplatform.module.grid.dal.mysql.keyperson;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.keyperson.KeyPersonDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.grid.controller.admin.keyperson.vo.*;

/**
 * 关键人信息 Mapper
 *
 * @author 易诚原生智能平台
 */
@Mapper
public interface KeyPersonMapper extends BaseMapperX<KeyPersonDO> {

    default PageResult<KeyPersonDO> selectPage(KeyPersonPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<KeyPersonDO>()
                .likeIfPresent(KeyPersonDO::getPersonName, reqVO.getPersonName())
                .likeIfPresent(KeyPersonDO::getOrganization, reqVO.getOrganization())
                .orderByDesc(KeyPersonDO::getCreateTime));
    }

}