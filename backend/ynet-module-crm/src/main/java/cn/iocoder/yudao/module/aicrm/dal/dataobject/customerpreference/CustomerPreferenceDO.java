package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpreference;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户偏好表 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_preference")
@KeySequence("crm_customer_preference_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPreferenceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    // ========== 1. 喜好的电子渠道 ==========
    /**
     * 偏好手机银行
     */
    private Boolean preferMobileBanking;
    /**
     * 偏好电话银行
     */
    private Boolean preferPhoneBanking;
    /**
     * 偏好网上银行
     */
    private Boolean preferOnlineBanking;
    /**
     * 偏好微信银行
     */
    private Boolean preferWechatBanking;
    /**
     * 偏好短信服务
     */
    private Boolean preferSmsService;
    /**
     * 偏好自助银行(ATM)
     */
    private Boolean preferAtmBanking;
    /**
     * 其他电子渠道
     */
    private String preferChannelOther;

    // ========== 2. 喜好投资类型 ==========
    /**
     * 偏好投资股票
     */
    private Boolean preferInvestStocks;
    /**
     * 偏好投资基金
     */
    private Boolean preferInvestFunds;
    /**
     * 偏好投资债券
     */
    private Boolean preferInvestBonds;
    /**
     * 偏好投资黄金
     */
    private Boolean preferInvestGold;
    /**
     * 偏好投资外汇
     */
    private Boolean preferInvestForex;
    /**
     * 偏好投资理财产品
     */
    private Boolean preferInvestWealthProducts;
    /**
     * 偏好投资信托
     */
    private Boolean preferInvestTrust;
    /**
     * 偏好投资保险
     */
    private Boolean preferInvestInsurance;
    /**
     * 其他投资类型
     */
    private String preferInvestOther;

    // ========== 3. 喜好品牌类型 ==========
    /**
     * 偏好奢侈品牌
     */
    private Boolean preferBrandLuxury;
    /**
     * 偏好轻奢品牌
     */
    private Boolean preferBrandLightLuxury;
    /**
     * 偏好国际品牌
     */
    private Boolean preferBrandInternational;
    /**
     * 偏好本地品牌
     */
    private Boolean preferBrandLocal;
    /**
     * 偏好设计师品牌
     */
    private Boolean preferBrandDesigner;
    /**
     * 其他品牌类型
     */
    private String preferBrandOther;

    // ========== 4. 希望得到的理财服务 ==========
    /**
     * 偏好资产配置服务
     */
    private Boolean preferServiceAssetAllocation;
    /**
     * 偏好市场分析服务
     */
    private Boolean preferServiceMarketAnalysis;
    /**
     * 偏好产品推荐服务
     */
    private Boolean preferServiceProductRecommendation;
    /**
     * 偏好风险评估服务
     */
    private Boolean preferServiceRiskAssessment;
    /**
     * 偏好税务筹划服务
     */
    private Boolean preferServiceTaxPlanning;
    /**
     * 偏好保险规划服务
     */
    private Boolean preferServiceInsurancePlanning;
    /**
     * 偏好退休规划服务
     */
    private Boolean preferServiceRetirementPlanning;
    /**
     * 其他理财服务
     */
    private String preferServiceOther;

    // ========== 5. 希望理财经理的联系方式 ==========
    /**
     * 偏好电话联系
     */
    private Boolean preferContactPhone;
    /**
     * 偏好微信联系
     */
    private Boolean preferContactWechat;
    /**
     * 偏好邮件联系
     */
    private Boolean preferContactEmail;
    /**
     * 偏好短信联系
     */
    private Boolean preferContactSms;
    /**
     * 偏好上门拜访
     */
    private Boolean preferContactVisit;
    /**
     * 偏好视频联系
     */
    private Boolean preferContactVideo;
    /**
     * 其他联系方式
     */
    private String preferContactOther;

    // ========== 6. 希望参加的沙龙活动 ==========
    /**
     * 偏好投资研讨会
     */
    private Boolean preferSalonInvestmentSeminar;
    /**
     * 偏好品酒会
     */
    private Boolean preferSalonWineTasting;
    /**
     * 偏好高尔夫活动
     */
    private Boolean preferSalonGolf;
    /**
     * 偏好艺术鉴赏活动
     */
    private Boolean preferSalonArtAppreciation;
    /**
     * 偏好健康养生活动
     */
    private Boolean preferSalonHealthWellness;
    /**
     * 偏好亲子活动
     */
    private Boolean preferSalonParentChild;
    /**
     * 其他沙龙活动
     */
    private String preferSalonOther;

    // ========== 7. 个人兴趣爱好 ==========
    /**
     * 兴趣爱好-运动
     */
    private Boolean hobbySports;
    /**
     * 兴趣爱好-阅读
     */
    private Boolean hobbyReading;
    /**
     * 兴趣爱好-旅游
     */
    private Boolean hobbyTravel;
    /**
     * 兴趣爱好-音乐
     */
    private Boolean hobbyMusic;
    /**
     * 兴趣爱好-美食
     */
    private Boolean hobbyFood;
    /**
     * 兴趣爱好-摄影
     */
    private Boolean hobbyPhotography;
    /**
     * 兴趣爱好-绘画
     */
    private Boolean hobbyPainting;
    /**
     * 兴趣爱好-收藏
     */
    private Boolean hobbyCollecting;
    /**
     * 兴趣爱好-园艺
     */
    private Boolean hobbyGardening;
    /**
     * 兴趣爱好-棋牌
     */
    private Boolean hobbyChess;
    /**
     * 其他兴趣爱好
     */
    private String hobbyOther;

    // ========== 8. 希望联系的时间 ==========
    /**
     * 偏好工作日上午联系
     */
    private Boolean preferTimeWorkdayMorning;
    /**
     * 偏好工作日下午联系
     */
    private Boolean preferTimeWorkdayAfternoon;
    /**
     * 偏好工作日晚上联系
     */
    private Boolean preferTimeWorkdayEvening;
    /**
     * 偏好周末上午联系
     */
    private Boolean preferTimeWeekendMorning;
    /**
     * 偏好周末下午联系
     */
    private Boolean preferTimeWeekendAfternoon;
    /**
     * 偏好周末晚上联系
     */
    private Boolean preferTimeWeekendEvening;
    /**
     * 其他联系时间
     */
    private String preferTimeOther;

    // ========== 9. 投资周期偏好 ==========
    /**
     * 偏好短期投资
     */
    private Boolean preferPeriodShortTerm;
    /**
     * 偏好中期投资
     */
    private Boolean preferPeriodMediumTerm;
    /**
     * 偏好长期投资
     */
    private Boolean preferPeriodLongTerm;
    /**
     * 偏好超长期投资
     */
    private Boolean preferPeriodUltraLongTerm;
    /**
     * 偏好灵活投资周期
     */
    private Boolean preferPeriodFlexible;
    /**
     * 其他投资周期
     */
    private String preferPeriodOther;

}
