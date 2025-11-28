package com.ynet.iplatform.module.twins.dal.mysql.customerUser;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.twins.dal.dataobject.customerUser.CustomerUserDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.twins.controller.admin.customerUser.vo.*;

/**
 * 接入用户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerUserMapper extends BaseMapperX<CustomerUserDO> {

    default PageResult<CustomerUserDO> selectPage(CustomerUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerUserDO>()
                .likeIfPresent(CustomerUserDO::getUsername, reqVO.getUsername())
                .orderByDesc(CustomerUserDO::getId));
    }

}