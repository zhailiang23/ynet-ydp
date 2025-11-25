package com.ynet.iplatform.module.aicrm.dal.mysql.practicevirtualcustomer;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer.vo.*;

/**
 * CRM智能陪练-虚拟客户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeVirtualCustomerMapper extends BaseMapperX<PracticeVirtualCustomerDO> {

    default PageResult<PracticeVirtualCustomerDO> selectPage(PracticeVirtualCustomerPageReqVO reqVO) {
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeVirtualCustomerDO>()
                // 暂时简化查询条件，只保留基本字段
                .likeIfPresent(PracticeVirtualCustomerDO::getName, reqVO.getName())
                // 只返回公开的虚拟客户或用户自己创建的虚拟客户
                .and(wrapper -> wrapper
                    .eq(PracticeVirtualCustomerDO::getIsPublic, true)
                    .or()
                    .eq(PracticeVirtualCustomerDO::getCreator, String.valueOf(currentUserId))
                )
                .orderByDesc(PracticeVirtualCustomerDO::getId));
    }

}