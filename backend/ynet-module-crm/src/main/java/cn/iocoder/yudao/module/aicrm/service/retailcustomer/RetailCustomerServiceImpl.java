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
        // TODO: 后续需要查询真实数据，当前返回模拟数据用于前端开发

        RetailCustomerOverviewRespVO overview = new RetailCustomerOverviewRespVO();

        // 1. 财务指标 (模拟数据)
        RetailCustomerOverviewRespVO.FinancialMetricsVO metrics = new RetailCustomerOverviewRespVO.FinancialMetricsVO();
        metrics.setTotalAssets(java.math.BigDecimal.valueOf(1_200_000));
        metrics.setTotalLiabilities(java.math.BigDecimal.valueOf(130_000));
        metrics.setNetAssets(java.math.BigDecimal.valueOf(1_070_000));
        metrics.setDepositBalance(java.math.BigDecimal.valueOf(800_000));
        metrics.setLoanBalance(java.math.BigDecimal.valueOf(50_000));
        metrics.setWealthBalance(java.math.BigDecimal.valueOf(400_000));
        metrics.setTotalAssetsGrowth(java.math.BigDecimal.valueOf(0.052));
        metrics.setDepositGrowth(java.math.BigDecimal.valueOf(0.031));
        metrics.setWealthGrowth(java.math.BigDecimal.valueOf(0.085));
        overview.setFinancialMetrics(metrics);

        // 2. 资产趋势数据 (模拟12个月的数据)
        List<RetailCustomerOverviewRespVO.AssetTrendVO> trendList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            RetailCustomerOverviewRespVO.AssetTrendVO trend = new RetailCustomerOverviewRespVO.AssetTrendVO();
            trend.setMonth("2024-" + String.format("%02d", i));
            trend.setTotalAssets(java.math.BigDecimal.valueOf(1_000_000 + i * 10_000));
            trend.setDeposits(java.math.BigDecimal.valueOf(700_000 + i * 5_000));
            trend.setWealth(java.math.BigDecimal.valueOf(300_000 + i * 3_000));
            trend.setLoans(java.math.BigDecimal.valueOf(50_000 - i * 500));
            trendList.add(trend);
        }
        overview.setAssetTrend(trendList);

        // 3. 资产结构
        RetailCustomerOverviewRespVO.AssetStructureVO structure = new RetailCustomerOverviewRespVO.AssetStructureVO();
        structure.setDepositAmount(java.math.BigDecimal.valueOf(800_000));
        structure.setWealthAmount(java.math.BigDecimal.valueOf(350_000));
        structure.setFundAmount(java.math.BigDecimal.valueOf(180_000));
        structure.setInsuranceAmount(java.math.BigDecimal.valueOf(50_000));
        structure.setMetalAmount(java.math.BigDecimal.valueOf(30_000));
        structure.setTrustAmount(java.math.BigDecimal.valueOf(20_000));
        structure.setOtherAmount(java.math.BigDecimal.valueOf(10_000));
        overview.setAssetStructure(structure);

        // 4. 存款类型分布
        RetailCustomerOverviewRespVO.DepositTypeDistributionVO depositDist = new RetailCustomerOverviewRespVO.DepositTypeDistributionVO();
        depositDist.setCurrentDepositAmount(java.math.BigDecimal.valueOf(240_000));
        depositDist.setTimeDepositAmount(java.math.BigDecimal.valueOf(400_000));
        depositDist.setNoticeDepositAmount(java.math.BigDecimal.valueOf(100_000));
        depositDist.setOtherDepositAmount(java.math.BigDecimal.valueOf(60_000));
        overview.setDepositDistribution(depositDist);

        // 5. 客户评级
        RetailCustomerOverviewRespVO.CustomerRatingVO rating = new RetailCustomerOverviewRespVO.CustomerRatingVO();
        rating.setValueLevel("A+");
        rating.setServiceLevel("金卡");
        rating.setRiskLevel("低");
        rating.setRatingScore(95);
        overview.setRating(rating);

        // 6. 客户贡献度
        RetailCustomerOverviewRespVO.CustomerContributionVO contribution = new RetailCustomerOverviewRespVO.CustomerContributionVO();
        contribution.setOverallLevel("高");
        contribution.setDepositScore(85);
        contribution.setLoanScore(30);
        contribution.setMiddleBusinessScore(60);
        overview.setContribution(contribution);

        // 7. 产品持有统计
        RetailCustomerOverviewRespVO.ProductHoldingStatVO productStat = new RetailCustomerOverviewRespVO.ProductHoldingStatVO();
        productStat.setDepositAccountCount(3);
        productStat.setWealthProductCount(5);
        productStat.setFundCount(4);
        productStat.setCreditcardCount(2);
        productStat.setTrustCount(0);
        productStat.setInsuranceCount(1);
        overview.setProductStat(productStat);

        // 8. 最近事件
        List<RetailCustomerOverviewRespVO.CustomerEventVO> events = new ArrayList<>();

        RetailCustomerOverviewRespVO.CustomerEventVO event1 = new RetailCustomerOverviewRespVO.CustomerEventVO();
        event1.setId(1L);
        event1.setEventName("定期存款到期提醒");
        event1.setEventType("到期提醒");
        event1.setEventDate("2024-10-28");
        event1.setEventDescription("账号: 6217****8901 | 金额: 50万");
        events.add(event1);

        RetailCustomerOverviewRespVO.CustomerEventVO event2 = new RetailCustomerOverviewRespVO.CustomerEventVO();
        event2.setId(2L);
        event2.setEventName("理财产品赎回");
        event2.setEventType("产品交易");
        event2.setEventDate("2024-10-27");
        event2.setEventDescription("产品: XX稳健型理财 | 金额: 10万");
        events.add(event2);

        RetailCustomerOverviewRespVO.CustomerEventVO event3 = new RetailCustomerOverviewRespVO.CustomerEventVO();
        event3.setId(3L);
        event3.setEventName("参与营销活动");
        event3.setEventType("营销活动");
        event3.setEventDate("2024-10-26");
        event3.setEventDescription("活动: 国庆理财促销活动");
        events.add(event3);

        overview.setRecentEvents(events);

        // 9. 产品持有趋势
        List<RetailCustomerOverviewRespVO.ProductHoldingTrendVO> holdingTrend = new ArrayList<>();

        RetailCustomerOverviewRespVO.ProductHoldingTrendVO holding1 = new RetailCustomerOverviewRespVO.ProductHoldingTrendVO();
        holding1.setProductType("存款账户");
        holding1.setProductCount(3);
        holdingTrend.add(holding1);

        RetailCustomerOverviewRespVO.ProductHoldingTrendVO holding2 = new RetailCustomerOverviewRespVO.ProductHoldingTrendVO();
        holding2.setProductType("理财产品");
        holding2.setProductCount(5);
        holdingTrend.add(holding2);

        RetailCustomerOverviewRespVO.ProductHoldingTrendVO holding3 = new RetailCustomerOverviewRespVO.ProductHoldingTrendVO();
        holding3.setProductType("基金");
        holding3.setProductCount(4);
        holdingTrend.add(holding3);

        RetailCustomerOverviewRespVO.ProductHoldingTrendVO holding4 = new RetailCustomerOverviewRespVO.ProductHoldingTrendVO();
        holding4.setProductType("信用卡");
        holding4.setProductCount(2);
        holdingTrend.add(holding4);

        RetailCustomerOverviewRespVO.ProductHoldingTrendVO holding5 = new RetailCustomerOverviewRespVO.ProductHoldingTrendVO();
        holding5.setProductType("信托");
        holding5.setProductCount(0);
        holdingTrend.add(holding5);

        RetailCustomerOverviewRespVO.ProductHoldingTrendVO holding6 = new RetailCustomerOverviewRespVO.ProductHoldingTrendVO();
        holding6.setProductType("保险");
        holding6.setProductCount(1);
        holdingTrend.add(holding6);

        overview.setProductHoldingTrend(holdingTrend);

        // 10. 月度交易统计 (最近6个月)
        List<RetailCustomerOverviewRespVO.MonthlyTransactionVO> monthlyTransactions = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            RetailCustomerOverviewRespVO.MonthlyTransactionVO transaction = new RetailCustomerOverviewRespVO.MonthlyTransactionVO();
            transaction.setMonth("2024-" + String.format("%02d", 12 - i));
            transaction.setTransactionCount(15 + i * 3);
            transaction.setTransactionAmount(java.math.BigDecimal.valueOf(250_000 + i * 30_000));
            monthlyTransactions.add(transaction);
        }
        overview.setMonthlyTransactions(monthlyTransactions);

        // 11. 客户标签
        List<String> tags = Arrays.asList("VIP", "高净值", "优质客户", "活跃用户");
        overview.setTags(tags);

        return overview;
    }

}