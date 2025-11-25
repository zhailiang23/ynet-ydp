package com.ynet.iplatform.module.aicrm.dal.mysql.customertransfer;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.aicrm.controller.admin.customertransfer.vo.CustomerTransferApplicationPageReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customertransfer.CustomerTransferApplicationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户移交申请表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerTransferApplicationMapper extends BaseMapperX<CustomerTransferApplicationDO> {

    default PageResult<CustomerTransferApplicationDO> selectPage(CustomerTransferApplicationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerTransferApplicationDO>()
                .eqIfPresent(CustomerTransferApplicationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerTransferApplicationDO::getApplicantUserId, reqVO.getApplicantUserId())
                .eqIfPresent(CustomerTransferApplicationDO::getToUserId, reqVO.getToUserId())
                .eqIfPresent(CustomerTransferApplicationDO::getProcessStatus, reqVO.getProcessStatus())
                .betweenIfPresent(CustomerTransferApplicationDO::getApplyDate, reqVO.getApplyDate())
                .orderByDesc(CustomerTransferApplicationDO::getId));
    }

}
