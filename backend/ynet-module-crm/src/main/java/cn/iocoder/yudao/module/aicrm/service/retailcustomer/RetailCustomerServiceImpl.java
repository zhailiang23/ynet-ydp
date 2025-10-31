package cn.iocoder.yudao.module.aicrm.service.retailcustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.retailcustomer.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.retailcustomer.RetailCustomerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.retailcustomer.RetailCustomerMapper;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customer.CustomerMapper;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customer.CustomerDO;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM零售客户扩展表(零售客户特有基本信息) Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RetailCustomerServiceImpl implements RetailCustomerService {

    @Resource
    private RetailCustomerMapper retailCustomerMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountdeposit.CustomerAccountDepositMapper customerAccountDepositMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountwealth.CustomerAccountWealthMapper customerAccountWealthMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountloan.CustomerAccountLoanMapper customerAccountLoanMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountfund.CustomerAccountFundMapper customerAccountFundMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountinsurance.CustomerAccountInsuranceMapper customerAccountInsuranceMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customeraccountcreditcard.CustomerAccountCreditcardMapper customerAccountCreditcardMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customerrating.CustomerRatingMapper customerRatingMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customercontribution.CustomerContributionMapper customerContributionMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customerimportantevent.CustomerImportantEventMapper customerImportantEventMapper;

    @Resource
    private RetailCustomerOverviewHelper overviewHelper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customerassetsnapshot.CustomerAssetSnapshotMapper customerAssetSnapshotMapper;

    @Resource
    private cn.iocoder.yudao.module.aicrm.dal.mysql.customertransactionmock.CustomerTransactionMockMapper customerTransactionMockMapper;

    @Override
    public Long createRetailCustomer(RetailCustomerSaveReqVO createReqVO) {
        // 插入
        RetailCustomerDO retailCustomer = BeanUtils.toBean(createReqVO, RetailCustomerDO.class);
        retailCustomerMapper.insert(retailCustomer);

        // 返回
        return retailCustomer.getId();
    }

    @Override
    public void updateRetailCustomer(RetailCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateRetailCustomerExists(updateReqVO.getId());
        // 更新
        RetailCustomerDO updateObj = BeanUtils.toBean(updateReqVO, RetailCustomerDO.class);
        retailCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteRetailCustomer(Long id) {
        // 校验存在
        validateRetailCustomerExists(id);
        // 删除
        retailCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteRetailCustomerListByIds(List<Long> ids) {
        // 删除
        retailCustomerMapper.deleteByIds(ids);
        }


    private void validateRetailCustomerExists(Long id) {
        if (retailCustomerMapper.selectById(id) == null) {
            throw exception(RETAIL_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public RetailCustomerRespVO getRetailCustomer(Long id) {
        // 1. 查询零售客户扩展信息
        RetailCustomerDO retailCustomerDO = retailCustomerMapper.selectById(id);
        if (retailCustomerDO == null) {
            return null;
        }

        // 2. 转换为 VO
        RetailCustomerRespVO respVO = BeanUtils.toBean(retailCustomerDO, RetailCustomerRespVO.class);

        // 3. 查询客户共有信息
        Long customerId = retailCustomerDO.getCustomerId();
        if (customerId != null) {
            CustomerDO customerDO = customerMapper.selectById(customerId);
            if (customerDO != null) {
                // 4. 手动组装共有字段到 VO
                respVO.setCustomerNo(customerDO.getCustomerNo());
                respVO.setCustomerType(customerDO.getCustomerType());
                respVO.setCustomerName(customerDO.getCustomerName());
                respVO.setCustomerLevel(customerDO.getCustomerLevel());
                respVO.setCustomerStatus(customerDO.getCustomerStatus());
                respVO.setIsHighQuality(customerDO.getIsHighQuality());
                respVO.setIsImportant(customerDO.getIsImportant());
                respVO.setCreditStatus(customerDO.getCreditStatus());
                respVO.setCreditLevel(customerDO.getCreditLevel());
                respVO.setCreditScore(customerDO.getCreditScore());
                respVO.setCustomerSource(customerDO.getCustomerSource());
                respVO.setCustomerTag(customerDO.getCustomerTag());
                respVO.setRemark(customerDO.getRemark());
                respVO.setDeptId(customerDO.getDeptId());
            }
        }

        return respVO;
    }

    @Override
    public PageResult<RetailCustomerDO> getRetailCustomerPage(RetailCustomerPageReqVO pageReqVO) {
        return retailCustomerMapper.selectPage(pageReqVO);
    }

    @Override
    public RetailCustomerOverviewRespVO getCustomerOverview(Long customerId, String startDate, String endDate) {
        RetailCustomerOverviewRespVO overview = new RetailCustomerOverviewRespVO();

        // 1. 查询客户各类账户数据
        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountdeposit.CustomerAccountDepositDO> deposits =
                customerAccountDepositMapper.selectList("customer_id", customerId);

        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountwealth.CustomerAccountWealthDO> wealths =
                customerAccountWealthMapper.selectList("customer_id", customerId);

        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountloan.CustomerAccountLoanDO> loans =
                customerAccountLoanMapper.selectList("customer_id", customerId);

        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountfund.CustomerAccountFundDO> funds =
                customerAccountFundMapper.selectList("customer_id", customerId);

        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountinsurance.CustomerAccountInsuranceDO> insurances =
                customerAccountInsuranceMapper.selectList("customer_id", customerId);

        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountcreditcard.CustomerAccountCreditcardDO> creditcards =
                customerAccountCreditcardMapper.selectList("customer_id", customerId);

        // 2. 计算财务指标
        RetailCustomerOverviewRespVO.FinancialMetricsVO metrics = overviewHelper.calculateFinancialMetrics(deposits, wealths, loans);
        overview.setFinancialMetrics(metrics);

        // 3. 查询资产快照数据（最近12个月）
        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassetsnapshot.CustomerAssetSnapshotDO> snapshots =
                customerAssetSnapshotMapper.selectRecentMonths(customerId, 12);
        // 反转列表，使其按时间正序排列
        java.util.Collections.reverse(snapshots);
        List<RetailCustomerOverviewRespVO.AssetTrendVO> assetTrend = overviewHelper.convertAssetSnapshots(snapshots);
        overview.setAssetTrend(assetTrend);

        // 4. 计算资产结构
        RetailCustomerOverviewRespVO.AssetStructureVO structure = overviewHelper.calculateAssetStructure(deposits, wealths, funds, insurances);
        overview.setAssetStructure(structure);

        // 5. 存款类型分布 - 暂时返回空对象 (需要按存款类型汇总)
        RetailCustomerOverviewRespVO.DepositTypeDistributionVO depositDist = new RetailCustomerOverviewRespVO.DepositTypeDistributionVO();
        depositDist.setCurrentDepositAmount(java.math.BigDecimal.ZERO);
        depositDist.setTimeDepositAmount(java.math.BigDecimal.ZERO);
        depositDist.setNoticeDepositAmount(java.math.BigDecimal.ZERO);
        depositDist.setOtherDepositAmount(java.math.BigDecimal.ZERO);
        overview.setDepositDistribution(depositDist);

        // 6. 查询客户评级
        cn.iocoder.yudao.module.aicrm.dal.dataobject.customerrating.CustomerRatingDO ratingDO =
                customerRatingMapper.selectOne("customer_id", customerId);
        RetailCustomerOverviewRespVO.CustomerRatingVO rating = overviewHelper.convertCustomerRating(ratingDO);
        overview.setRating(rating);

        // 7. 查询客户贡献度
        cn.iocoder.yudao.module.aicrm.dal.dataobject.customercontribution.CustomerContributionDO contributionDO =
                customerContributionMapper.selectOne("customer_id", customerId);
        RetailCustomerOverviewRespVO.CustomerContributionVO contribution = overviewHelper.convertCustomerContribution(contributionDO);
        overview.setContribution(contribution);

        // 8. 计算产品持有统计
        RetailCustomerOverviewRespVO.ProductHoldingStatVO productStat = overviewHelper.calculateProductHoldingStat(
                deposits, wealths, funds, creditcards, insurances);
        overview.setProductStat(productStat);

        // 9. 查询最近重要事件 (最多10条)
        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO> eventDOs =
                customerImportantEventMapper.selectList(
                        new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<cn.iocoder.yudao.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO>()
                                .eq(cn.iocoder.yudao.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO::getCustomerId, customerId)
                                .orderByDesc(cn.iocoder.yudao.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO::getEventDate)
                                .last("LIMIT 10")
                );
        List<RetailCustomerOverviewRespVO.CustomerEventVO> events = overviewHelper.convertCustomerEvents(eventDOs);
        overview.setRecentEvents(events);

        // 10. 产品持有趋势
        List<RetailCustomerOverviewRespVO.ProductHoldingTrendVO> holdingTrend = overviewHelper.calculateProductHoldingTrend(
                deposits.size(), wealths.size(), funds.size(), creditcards.size(), 0, insurances.size());
        overview.setProductHoldingTrend(holdingTrend);

        // 11. 查询交易流水并统计月度交易
        List<cn.iocoder.yudao.module.aicrm.dal.dataobject.customertransactionmock.CustomerTransactionMockDO> transactions =
                customerTransactionMockMapper.selectList("customer_id", customerId);
        List<RetailCustomerOverviewRespVO.MonthlyTransactionVO> monthlyTransactions = overviewHelper.calculateMonthlyTransactions(transactions);
        overview.setMonthlyTransactions(monthlyTransactions);

        // 12. 客户标签 - 从 customer 表的 customer_tag 字段解析
        CustomerDO customerDO = customerMapper.selectById(customerId);
        List<String> tags = new ArrayList<>();
        if (customerDO != null && customerDO.getCustomerTag() != null && !customerDO.getCustomerTag().isEmpty()) {
            // 按逗号分割标签字符串
            String[] tagArray = customerDO.getCustomerTag().split(",");
            for (String tag : tagArray) {
                String trimmedTag = tag.trim();
                if (!trimmedTag.isEmpty()) {
                    tags.add(trimmedTag);
                }
            }
        }
        overview.setTags(tags);

        return overview;
    }

}