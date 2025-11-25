package com.ynet.iplatform.module.aicrm.service.retailcustomer;

import java.time.LocalDate;

import com.ynet.iplatform.module.aicrm.controller.admin.retailcustomer.vo.RetailCustomerOverviewRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountcreditcard.CustomerAccountCreditcardDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountdeposit.CustomerAccountDepositDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountfund.CustomerAccountFundDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountinsurance.CustomerAccountInsuranceDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountloan.CustomerAccountLoanDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountwealth.CustomerAccountWealthDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customercontribution.CustomerContributionDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerimportantevent.CustomerImportantEventDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerrating.CustomerRatingDO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 零售客户概况数据组装辅助类
 */
@Component
public class RetailCustomerOverviewHelper {

    /**
     * 计算财务指标
     */
    public RetailCustomerOverviewRespVO.FinancialMetricsVO calculateFinancialMetrics(
            List<CustomerAccountDepositDO> deposits,
            List<CustomerAccountWealthDO> wealths,
            List<CustomerAccountLoanDO> loans) {

        RetailCustomerOverviewRespVO.FinancialMetricsVO metrics = new RetailCustomerOverviewRespVO.FinancialMetricsVO();

        // 计算存款总额
        BigDecimal depositBalance = deposits.stream()
                .map(CustomerAccountDepositDO::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 计算理财总额
        BigDecimal wealthBalance = wealths.stream()
                .map(CustomerAccountWealthDO::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 计算贷款余额
        BigDecimal loanBalance = loans.stream()
                .map(CustomerAccountLoanDO::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 总资产 = 存款 + 理财
        BigDecimal totalAssets = depositBalance.add(wealthBalance);

        // 总负债 = 贷款
        BigDecimal totalLiabilities = loanBalance;

        // 净资产 = 总资产 - 总负债
        BigDecimal netAssets = totalAssets.subtract(totalLiabilities);

        metrics.setTotalAssets(totalAssets);
        metrics.setTotalLiabilities(totalLiabilities);
        metrics.setNetAssets(netAssets);
        metrics.setDepositBalance(depositBalance);
        metrics.setLoanBalance(loanBalance);
        metrics.setWealthBalance(wealthBalance);

        // TODO: 增长率需要对比历史数据计算，暂时设置为null
        metrics.setTotalAssetsGrowth(null);
        metrics.setDepositGrowth(null);
        metrics.setWealthGrowth(null);
        metrics.setAvailableBalance(null);
        metrics.setCreditLimit(null);

        return metrics;
    }

    /**
     * 计算资产结构
     */
    public RetailCustomerOverviewRespVO.AssetStructureVO calculateAssetStructure(
            List<CustomerAccountDepositDO> deposits,
            List<CustomerAccountWealthDO> wealths,
            List<CustomerAccountFundDO> funds,
            List<CustomerAccountInsuranceDO> insurances) {

        RetailCustomerOverviewRespVO.AssetStructureVO structure = new RetailCustomerOverviewRespVO.AssetStructureVO();

        BigDecimal depositAmount = deposits.stream()
                .map(CustomerAccountDepositDO::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal wealthAmount = wealths.stream()
                .map(CustomerAccountWealthDO::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal fundAmount = funds.stream()
                .map(CustomerAccountFundDO::getCurrentValue)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal insuranceAmount = insurances.stream()
                .map(CustomerAccountInsuranceDO::getBalance)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        structure.setDepositAmount(depositAmount);
        structure.setWealthAmount(wealthAmount);
        structure.setFundAmount(fundAmount);
        structure.setInsuranceAmount(insuranceAmount);
        structure.setMetalAmount(BigDecimal.ZERO);  // 暂无贵金属数据
        structure.setTrustAmount(BigDecimal.ZERO);  // 暂无信托数据
        structure.setOtherAmount(BigDecimal.ZERO);

        return structure;
    }

    /**
     * 转换客户评级数据
     */
    public RetailCustomerOverviewRespVO.CustomerRatingVO convertCustomerRating(CustomerRatingDO rating) {
        if (rating == null) {
            return null;
        }

        RetailCustomerOverviewRespVO.CustomerRatingVO ratingVO = new RetailCustomerOverviewRespVO.CustomerRatingVO();
        ratingVO.setValueLevel(rating.getValueLevel());
        ratingVO.setServiceLevel(rating.getServiceLevel());
        ratingVO.setRiskLevel(rating.getRiskLevel());

        // 将 BigDecimal 评分转为整数
        if (rating.getRatingScore() != null) {
            ratingVO.setRatingScore(rating.getRatingScore().intValue());
        } else {
            ratingVO.setRatingScore(0);
        }

        return ratingVO;
    }

    /**
     * 转换客户贡献度数据
     */
    public RetailCustomerOverviewRespVO.CustomerContributionVO convertCustomerContribution(CustomerContributionDO contribution) {
        if (contribution == null) {
            return null;
        }

        RetailCustomerOverviewRespVO.CustomerContributionVO contributionVO = new RetailCustomerOverviewRespVO.CustomerContributionVO();
        contributionVO.setOverallLevel(contribution.getContributionLevel());

        // 将贡献金额转换为评分（简化处理：每1000元贡献 = 1分）
        BigDecimal depositContribution = contribution.getDepositContribution();
        BigDecimal loanContribution = contribution.getLoanContribution();
        BigDecimal intermediateContribution = contribution.getIntermediateContribution();

        contributionVO.setDepositScore(depositContribution != null ? depositContribution.divide(new BigDecimal(1000)).intValue() : 0);
        contributionVO.setLoanScore(loanContribution != null ? loanContribution.divide(new BigDecimal(1000)).intValue() : 0);
        contributionVO.setMiddleBusinessScore(intermediateContribution != null ? intermediateContribution.divide(new BigDecimal(1000)).intValue() : 0);

        return contributionVO;
    }

    /**
     * 计算产品持有统计
     */
    public RetailCustomerOverviewRespVO.ProductHoldingStatVO calculateProductHoldingStat(
            List<CustomerAccountDepositDO> deposits,
            List<CustomerAccountWealthDO> wealths,
            List<CustomerAccountFundDO> funds,
            List<CustomerAccountCreditcardDO> creditcards,
            List<CustomerAccountInsuranceDO> insurances) {

        RetailCustomerOverviewRespVO.ProductHoldingStatVO productStat = new RetailCustomerOverviewRespVO.ProductHoldingStatVO();

        productStat.setDepositAccountCount(deposits.size());
        productStat.setWealthProductCount(wealths.size());
        productStat.setFundCount(funds.size());
        productStat.setCreditcardCount(creditcards.size());
        productStat.setTrustCount(0);  // 暂无信托账户
        productStat.setInsuranceCount(insurances.size());

        return productStat;
    }

    /**
     * 转换客户重要事件
     */
    public List<RetailCustomerOverviewRespVO.CustomerEventVO> convertCustomerEvents(List<CustomerImportantEventDO> events) {
        if (events == null || events.isEmpty()) {
            return new ArrayList<>();
        }

        List<RetailCustomerOverviewRespVO.CustomerEventVO> eventVOs = new ArrayList<>();
        for (CustomerImportantEventDO event : events) {
            RetailCustomerOverviewRespVO.CustomerEventVO eventVO = new RetailCustomerOverviewRespVO.CustomerEventVO();
            eventVO.setId(event.getId());
            eventVO.setEventName(event.getEventName());
            eventVO.setEventType(event.getEventType());
            eventVO.setEventDate(event.getEventDate() != null ? event.getEventDate().toString() : "");
            eventVO.setEventDescription(event.getEventContent());
            eventVOs.add(eventVO);
        }

        return eventVOs;
    }

    /**
     * 计算产品持有趋势
     */
    public List<RetailCustomerOverviewRespVO.ProductHoldingTrendVO> calculateProductHoldingTrend(
            int depositCount, int wealthCount, int fundCount,
            int creditcardCount, int trustCount, int insuranceCount) {

        List<RetailCustomerOverviewRespVO.ProductHoldingTrendVO> holdingTrend = new ArrayList<>();

        holdingTrend.add(createHoldingTrend("存款账户", depositCount));
        holdingTrend.add(createHoldingTrend("理财产品", wealthCount));
        holdingTrend.add(createHoldingTrend("基金", fundCount));
        holdingTrend.add(createHoldingTrend("信用卡", creditcardCount));
        holdingTrend.add(createHoldingTrend("信托", trustCount));
        holdingTrend.add(createHoldingTrend("保险", insuranceCount));

        return holdingTrend;
    }

    private RetailCustomerOverviewRespVO.ProductHoldingTrendVO createHoldingTrend(String productType, int count) {
        RetailCustomerOverviewRespVO.ProductHoldingTrendVO trend = new RetailCustomerOverviewRespVO.ProductHoldingTrendVO();
        trend.setProductType(productType);
        trend.setProductCount(count);
        return trend;
    }

    /**
     * 转换资产快照为趋势数据
     */
    public List<RetailCustomerOverviewRespVO.AssetTrendVO> convertAssetSnapshots(List<?> snapshots) {
        if (snapshots == null || snapshots.isEmpty()) {
            return new ArrayList<>();
        }

        List<RetailCustomerOverviewRespVO.AssetTrendVO> trendList = new ArrayList<>();

        // 使用反射处理不同类型的快照对象
        for (Object snapshot : snapshots) {
            try {
                RetailCustomerOverviewRespVO.AssetTrendVO trend = new RetailCustomerOverviewRespVO.AssetTrendVO();

                // 获取快照日期并格式化为 YYYY-MM
                java.lang.reflect.Method getSnapshotDate = snapshot.getClass().getMethod("getSnapshotDate");
                Object dateObj = getSnapshotDate.invoke(snapshot);
                if (dateObj instanceof java.time.LocalDate) {
                    java.time.LocalDate date = (java.time.LocalDate) dateObj;
                    trend.setMonth(date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM")));
                }

                // 获取资产数据
                java.lang.reflect.Method getTotalAssets = snapshot.getClass().getMethod("getTotalAssets");
                java.lang.reflect.Method getDepositBalance = snapshot.getClass().getMethod("getDepositBalance");
                java.lang.reflect.Method getWealthBalance = snapshot.getClass().getMethod("getWealthBalance");
                java.lang.reflect.Method getLoanBalance = snapshot.getClass().getMethod("getLoanBalance");

                trend.setTotalAssets((BigDecimal) getTotalAssets.invoke(snapshot));
                trend.setDeposits((BigDecimal) getDepositBalance.invoke(snapshot));
                trend.setWealth((BigDecimal) getWealthBalance.invoke(snapshot));
                trend.setLoans((BigDecimal) getLoanBalance.invoke(snapshot));

                trendList.add(trend);
            } catch (Exception e) {
                // 忽略转换错误，继续处理下一个
            }
        }

        return trendList;
    }

    /**
     * 统计月度交易数据
     */
    public List<RetailCustomerOverviewRespVO.MonthlyTransactionVO> calculateMonthlyTransactions(List<?> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return new ArrayList<>();
        }

        // 按月分组统计
        java.util.Map<String, RetailCustomerOverviewRespVO.MonthlyTransactionVO> monthlyMap = new java.util.LinkedHashMap<>();

        for (Object transaction : transactions) {
            try {
                // 获取交易日期
                java.lang.reflect.Method getTransactionDate = transaction.getClass().getMethod("getTransactionDate");
                Object dateObj = getTransactionDate.invoke(transaction);

                if (dateObj instanceof java.time.LocalDate) {
                    java.time.LocalDate date = (java.time.LocalDate) dateObj;
                    String month = date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM"));

                    // 获取交易金额
                    java.lang.reflect.Method getTradMoney = transaction.getClass().getMethod("getTradMoney");
                    BigDecimal amount = (BigDecimal) getTradMoney.invoke(transaction);

                    // 累加到对应月份
                    monthlyMap.computeIfAbsent(month, k -> {
                        RetailCustomerOverviewRespVO.MonthlyTransactionVO vo = new RetailCustomerOverviewRespVO.MonthlyTransactionVO();
                        vo.setMonth(k);
                        vo.setTransactionCount(0);
                        vo.setTransactionAmount(BigDecimal.ZERO);
                        return vo;
                    });

                    RetailCustomerOverviewRespVO.MonthlyTransactionVO monthlyVO = monthlyMap.get(month);
                    monthlyVO.setTransactionCount(monthlyVO.getTransactionCount() + 1);
                    monthlyVO.setTransactionAmount(monthlyVO.getTransactionAmount().add(amount != null ? amount : BigDecimal.ZERO));
                }
            } catch (Exception e) {
                // 忽略转换错误
            }
        }

        return new ArrayList<>(monthlyMap.values());
    }
}
