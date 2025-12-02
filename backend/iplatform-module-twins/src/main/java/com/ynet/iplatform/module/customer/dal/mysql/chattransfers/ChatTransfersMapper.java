package com.ynet.iplatform.module.customer.dal.mysql.chattransfers;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.customer.dal.dataobject.chattransfers.ChatTransfersDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ynet.iplatform.module.customer.controller.admin.chattransfers.vo.*;

/**
 * 转接记录 Mapper
 *
 * @author 易诚源码
 */
@Mapper
public interface ChatTransfersMapper extends BaseMapperX<ChatTransfersDO> {

    default PageResult<ChatTransfersDO> selectPage(ChatTransfersPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChatTransfersDO>()
                .eqIfPresent(ChatTransfersDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ChatTransfersDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ChatTransfersDO::getId));
    }

    /**
     * 连表查询转接记录列表（XML 实现），包含用户名和客服名，支持按名字模糊查询
     */
    IPage<ChatTransfersRespVO> selectPageWithNames(IPage<ChatTransfersRespVO> page,
                                                    @Param("userName") String userName,
                                                    @Param("fromAdminName") String fromAdminName,
                                                    @Param("toAdminName") String toAdminName,
                                                    @Param("remark") String remark);

    /**
     * 统计转接记录数量（XML 实现）
     */
    Long selectCountWithNames(@Param("userName") String userName,
                              @Param("fromAdminName") String fromAdminName,
                              @Param("toAdminName") String toAdminName,
                              @Param("remark") String remark);

}