package com.ynet.iplatform.module.aicrm.dal.mysql.customerimportantevent;

import java.util.*;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO;
import org.apache.ibatis.annotations.Mapper;
import com.ynet.iplatform.module.aicrm.controller.admin.customerimportantevent.vo.*;

/**
 * 客户重要事件表（零售+对公共用） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerImportantEventMapper extends BaseMapperX<CustomerImportantEventDO> {

    default PageResult<CustomerImportantEventDO> selectPage(CustomerImportantEventPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerImportantEventDO>()
                .eqIfPresent(CustomerImportantEventDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(CustomerImportantEventDO::getEventName, reqVO.getEventName())
                .eqIfPresent(CustomerImportantEventDO::getEventStatus, reqVO.getEventStatus())
                .betweenIfPresent(CustomerImportantEventDO::getEventDate, reqVO.getEventDate())
                .eqIfPresent(CustomerImportantEventDO::getEventContent, reqVO.getEventContent())
                .eqIfPresent(CustomerImportantEventDO::getMaintainerId, reqVO.getMaintainerId())
                .likeIfPresent(CustomerImportantEventDO::getMaintainerName, reqVO.getMaintainerName())
                .betweenIfPresent(CustomerImportantEventDO::getMaintainTime, reqVO.getMaintainTime())
                .eqIfPresent(CustomerImportantEventDO::getEventType, reqVO.getEventType())
                .eqIfPresent(CustomerImportantEventDO::getEventLevel, reqVO.getEventLevel())
                .eqIfPresent(CustomerImportantEventDO::getEventSource, reqVO.getEventSource())
                .eqIfPresent(CustomerImportantEventDO::getRemindFlag, reqVO.getRemindFlag())
                .betweenIfPresent(CustomerImportantEventDO::getRemindTime, reqVO.getRemindTime())
                .eqIfPresent(CustomerImportantEventDO::getAttachmentUrl, reqVO.getAttachmentUrl())
                .eqIfPresent(CustomerImportantEventDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerImportantEventDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerImportantEventDO::getId));
    }

}