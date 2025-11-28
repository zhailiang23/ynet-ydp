package com.ynet.iplatform.module.twins.dal.mysql.customerAdmin;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.twins.dal.dataobject.customerAdmin.CustomerAdminDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.twins.controller.admin.customerAdmin.vo.*;

/**
 * 客服信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerAdminMapper extends BaseMapperX<CustomerAdminDO> {

    default PageResult<CustomerAdminDO> selectPage(CustomerAdminPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerAdminDO>()
                .likeIfPresent(CustomerAdminDO::getUsername, reqVO.getUsername())
                .eqIfPresent(CustomerAdminDO::getPassword, reqVO.getPassword())
                .eqIfPresent(CustomerAdminDO::getCreatedAt, reqVO.getCreatedAt())
                .eqIfPresent(CustomerAdminDO::getUpdatedAt, reqVO.getUpdatedAt())
                .orderByDesc(CustomerAdminDO::getId));
    }

}