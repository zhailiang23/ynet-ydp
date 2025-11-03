package cn.iocoder.yudao.module.aicrm.dal.mysql.customertransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertransfer.vo.CustomerTransferApplicationPageReqVO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertransfer.CustomerTransferApplicationDO;
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
