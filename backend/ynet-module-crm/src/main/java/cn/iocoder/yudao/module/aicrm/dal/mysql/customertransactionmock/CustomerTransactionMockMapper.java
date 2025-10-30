package cn.iocoder.yudao.module.aicrm.dal.mysql.customertransactionmock;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertransactionmock.CustomerTransactionMockDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertransactionmock.vo.*;

/**
 * 客户交易明细信息表（Mock数据） Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerTransactionMockMapper extends BaseMapperX<CustomerTransactionMockDO> {

    default PageResult<CustomerTransactionMockDO> selectPage(CustomerTransactionMockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerTransactionMockDO>()
                .eqIfPresent(CustomerTransactionMockDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerTransactionMockDO::getAccountId, reqVO.getAccountId())
                .betweenIfPresent(CustomerTransactionMockDO::getTransactionDate, reqVO.getTransactionDate())
                .betweenIfPresent(CustomerTransactionMockDO::getTransactionTime, reqVO.getTransactionTime())
                .eqIfPresent(CustomerTransactionMockDO::getBranchNo, reqVO.getBranchNo())
                .likeIfPresent(CustomerTransactionMockDO::getBranchName, reqVO.getBranchName())
                .eqIfPresent(CustomerTransactionMockDO::getOriginalTranCode, reqVO.getOriginalTranCode())
                .eqIfPresent(CustomerTransactionMockDO::getCashFlag, reqVO.getCashFlag())
                .eqIfPresent(CustomerTransactionMockDO::getSubAccountNo, reqVO.getSubAccountNo())
                .eqIfPresent(CustomerTransactionMockDO::getAccountNo, reqVO.getAccountNo())
                .eqIfPresent(CustomerTransactionMockDO::getCurrencyCode, reqVO.getCurrencyCode())
                .eqIfPresent(CustomerTransactionMockDO::getTansNo, reqVO.getTansNo())
                .eqIfPresent(CustomerTransactionMockDO::getTradType, reqVO.getTradType())
                .eqIfPresent(CustomerTransactionMockDO::getTradMoney, reqVO.getTradMoney())
                .eqIfPresent(CustomerTransactionMockDO::getAcctBal, reqVO.getAcctBal())
                .eqIfPresent(CustomerTransactionMockDO::getTradAbs, reqVO.getTradAbs())
                .eqIfPresent(CustomerTransactionMockDO::getReview, reqVO.getReview())
                .eqIfPresent(CustomerTransactionMockDO::getTradChn, reqVO.getTradChn())
                .eqIfPresent(CustomerTransactionMockDO::getTradTeller, reqVO.getTradTeller())
                .eqIfPresent(CustomerTransactionMockDO::getHandler, reqVO.getHandler())
                .eqIfPresent(CustomerTransactionMockDO::getAdvsAcct, reqVO.getAdvsAcct())
                .likeIfPresent(CustomerTransactionMockDO::getAdvsAcctName, reqVO.getAdvsAcctName())
                .eqIfPresent(CustomerTransactionMockDO::getContactType, reqVO.getContactType())
                .eqIfPresent(CustomerTransactionMockDO::getCurrTranFlag, reqVO.getCurrTranFlag())
                .eqIfPresent(CustomerTransactionMockDO::getLoanFlag, reqVO.getLoanFlag())
                .eqIfPresent(CustomerTransactionMockDO::getCost, reqVO.getCost())
                .betweenIfPresent(CustomerTransactionMockDO::getAccountinDate, reqVO.getAccountinDate())
                .eqIfPresent(CustomerTransactionMockDO::getTransactionStatus, reqVO.getTransactionStatus())
                .eqIfPresent(CustomerTransactionMockDO::getDirection, reqVO.getDirection())
                .eqIfPresent(CustomerTransactionMockDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CustomerTransactionMockDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerTransactionMockDO::getId));
    }

}