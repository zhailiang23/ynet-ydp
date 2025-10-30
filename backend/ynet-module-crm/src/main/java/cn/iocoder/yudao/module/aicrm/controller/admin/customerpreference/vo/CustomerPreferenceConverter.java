package cn.iocoder.yudao.module.aicrm.controller.admin.customerpreference.vo;

import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpreference.CustomerPreferenceDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户偏好数据转换器
 * 将数据库的布尔字段转换为前端需要的逗号分隔格式
 *
 * @author 芋道源码
 */
public class CustomerPreferenceConverter {

    /**
     * 将DO转换为前端响应VO
     */
    public static CustomerPreferenceRespVO toRespVO(CustomerPreferenceDO source) {
        if (source == null) {
            return null;
        }

        CustomerPreferenceRespVO vo = new CustomerPreferenceRespVO();
        vo.setId(source.getId());
        vo.setCustomerId(source.getCustomerId());

        // 1. 电子渠道
        vo.setElectronicChannels(convertBooleanFieldsToString(
            new BooleanField("mobile_bank", source.getPreferMobileBanking()),
            new BooleanField("phone_bank", source.getPreferPhoneBanking()),
            new BooleanField("online_bank", source.getPreferOnlineBanking()),
            new BooleanField("wechat_bank", source.getPreferWechatBanking()),
            new BooleanField("sms_service", source.getPreferSmsService()),
            new BooleanField("atm", source.getPreferAtmBanking())
        ));
        vo.setOtherChannel(source.getPreferChannelOther());

        // 2. 投资类型 (字典值: 1-股票, 2-基金, 3-债券, 4-理财产品, 5-保险, 6-贵金属, 7-外汇, 8-期货)
        vo.setInvestmentTypes(convertBooleanFieldsToString(
            new BooleanField("1", source.getPreferInvestStocks()),      // 股票
            new BooleanField("2", source.getPreferInvestFunds()),       // 基金
            new BooleanField("3", source.getPreferInvestBonds()),       // 债券
            new BooleanField("6", source.getPreferInvestGold()),        // 贵金属
            new BooleanField("7", source.getPreferInvestForex()),       // 外汇
            new BooleanField("4", source.getPreferInvestWealthProducts()), // 理财产品
            new BooleanField("trust", source.getPreferInvestTrust()),   // 信托(数据库有但字典没有)
            new BooleanField("5", source.getPreferInvestInsurance())    // 保险
        ));
        vo.setOtherInvestmentType(source.getPreferInvestOther());

        // 3. 品牌类型 (字典值: 1-国际知名品牌, 2-国内知名品牌, 3-本地品牌, 4-高端品牌, 5-性价比品牌)
        vo.setBrandTypes(convertBooleanFieldsToString(
            new BooleanField("luxury", source.getPreferBrandLuxury()),            // 奢侈品牌(数据库有但字典没有)
            new BooleanField("light_luxury", source.getPreferBrandLightLuxury()), // 轻奢品牌(数据库有但字典没有)
            new BooleanField("1", source.getPreferBrandInternational()),          // 国际知名品牌
            new BooleanField("3", source.getPreferBrandLocal()),                  // 本地品牌
            new BooleanField("designer", source.getPreferBrandDesigner())         // 设计师品牌(数据库有但字典没有)
        ));
        vo.setOtherBrandType(source.getPreferBrandOther());

        // 4. 理财服务 (字典值: 1-资产配置建议, 2-投资组合管理, 3-风险评估, 4-市场分析报告, 5-理财规划, 6-税务筹划, 7-保险规划, 8-退休规划)
        vo.setFinancialServices(convertBooleanFieldsToString(
            new BooleanField("1", source.getPreferServiceAssetAllocation()),          // 资产配置建议
            new BooleanField("4", source.getPreferServiceMarketAnalysis()),           // 市场分析报告
            new BooleanField("product_recommendation", source.getPreferServiceProductRecommendation()), // 产品推荐(数据库有但字典没有)
            new BooleanField("3", source.getPreferServiceRiskAssessment()),           // 风险评估
            new BooleanField("6", source.getPreferServiceTaxPlanning()),              // 税务筹划
            new BooleanField("7", source.getPreferServiceInsurancePlanning()),        // 保险规划
            new BooleanField("8", source.getPreferServiceRetirementPlanning())        // 退休规划
        ));
        vo.setOtherFinancialService(source.getPreferServiceOther());

        // 5. 联系方式 (字典值: 1-电话, 2-短信, 3-微信, 4-邮件, 5-面访, 6-QQ)
        vo.setContactMethods(convertBooleanFieldsToString(
            new BooleanField("1", source.getPreferContactPhone()),     // 电话
            new BooleanField("3", source.getPreferContactWechat()),    // 微信
            new BooleanField("4", source.getPreferContactEmail()),     // 邮件
            new BooleanField("2", source.getPreferContactSms()),       // 短信
            new BooleanField("5", source.getPreferContactVisit()),     // 面访
            new BooleanField("video", source.getPreferContactVideo())  // 视频(数据库有但字典没有)
        ));
        vo.setOtherContactMethod(source.getPreferContactOther());

        // 6. 沙龙活动 (字典值: 1-投资策略讲座, 2-理财产品推介会, 3-财富管理论坛, 4-高端客户酒会, 5-文化艺术活动, 6-健康养生讲座, 7-高尔夫活动, 8-亲子活动)
        vo.setSalonActivities(convertBooleanFieldsToString(
            new BooleanField("1", source.getPreferSalonInvestmentSeminar()),  // 投资策略讲座
            new BooleanField("wine_tasting", source.getPreferSalonWineTasting()), // 品酒会(数据库有但字典没有)
            new BooleanField("7", source.getPreferSalonGolf()),                // 高尔夫活动
            new BooleanField("5", source.getPreferSalonArtAppreciation()),     // 文化艺术活动
            new BooleanField("6", source.getPreferSalonHealthWellness()),      // 健康养生讲座
            new BooleanField("8", source.getPreferSalonParentChild())          // 亲子活动
        ));
        vo.setOtherSalonActivity(source.getPreferSalonOther());

        // 7. 兴趣爱好 (字典值: 1-运动健身, 2-旅游, 3-阅读, 4-音乐, 5-书法绘画, 6-收藏, 7-美食, 8-摄影, 9-园艺, 10-棋牌)
        vo.setInterestHobbies(convertBooleanFieldsToString(
            new BooleanField("1", source.getHobbySports()),         // 运动健身
            new BooleanField("3", source.getHobbyReading()),        // 阅读
            new BooleanField("2", source.getHobbyTravel()),         // 旅游
            new BooleanField("4", source.getHobbyMusic()),          // 音乐
            new BooleanField("7", source.getHobbyFood()),           // 美食
            new BooleanField("8", source.getHobbyPhotography()),    // 摄影
            new BooleanField("5", source.getHobbyPainting()),       // 书法绘画
            new BooleanField("6", source.getHobbyCollecting()),     // 收藏
            new BooleanField("9", source.getHobbyGardening()),      // 园艺
            new BooleanField("10", source.getHobbyChess())          // 棋牌
        ));
        vo.setOtherInterestHobby(source.getHobbyOther());

        // 8. 联系时间 (字典值: 1-工作日上午, 2-工作日下午, 3-工作日晚上, 4-周末上午, 5-周末下午, 6-周末晚上, 7-节假日)
        vo.setContactTimes(convertBooleanFieldsToString(
            new BooleanField("1", source.getPreferTimeWorkdayMorning()),      // 工作日上午
            new BooleanField("2", source.getPreferTimeWorkdayAfternoon()),    // 工作日下午
            new BooleanField("3", source.getPreferTimeWorkdayEvening()),      // 工作日晚上
            new BooleanField("4", source.getPreferTimeWeekendMorning()),      // 周末上午
            new BooleanField("5", source.getPreferTimeWeekendAfternoon()),    // 周末下午
            new BooleanField("6", source.getPreferTimeWeekendEvening())       // 周末晚上
        ));
        vo.setOtherContactTime(source.getPreferTimeOther());

        // 9. 投资周期 (字典值: 1-短期(1年以内), 2-中短期(1-3年), 3-中期(3-5年), 4-中长期(5-10年), 5-长期(10年以上))
        vo.setInvestmentPeriods(convertBooleanFieldsToString(
            new BooleanField("1", source.getPreferPeriodShortTerm()),        // 短期
            new BooleanField("2", source.getPreferPeriodMediumTerm()),       // 中短期
            new BooleanField("3", source.getPreferPeriodLongTerm()),         // 中期
            new BooleanField("4", source.getPreferPeriodUltraLongTerm()),    // 中长期
            new BooleanField("flexible", source.getPreferPeriodFlexible())   // 灵活(数据库有但字典没有)
        ));
        vo.setOtherInvestmentPeriod(source.getPreferPeriodOther());

        vo.setCreateTime(source.getCreateTime());
        vo.setUpdateTime(source.getUpdateTime());

        return vo;
    }

    /**
     * 将前端保存VO转换为DO
     */
    public static CustomerPreferenceDO toDO(CustomerPreferenceSaveReqVO source) {
        if (source == null) {
            return null;
        }

        CustomerPreferenceDO.CustomerPreferenceDOBuilder builder = CustomerPreferenceDO.builder();
        builder.id(source.getId());
        builder.customerId(source.getCustomerId());

        // 1. 电子渠道
        List<String> electronicChannels = splitString(source.getElectronicChannels());
        builder.preferMobileBanking(electronicChannels.contains("mobile_bank"));
        builder.preferPhoneBanking(electronicChannels.contains("phone_bank"));
        builder.preferOnlineBanking(electronicChannels.contains("online_bank"));
        builder.preferWechatBanking(electronicChannels.contains("wechat_bank"));
        builder.preferSmsService(electronicChannels.contains("sms_service"));
        builder.preferAtmBanking(electronicChannels.contains("atm"));
        builder.preferChannelOther(source.getOtherChannel());

        // 2. 投资类型 (字典值: 1-股票, 2-基金, 3-债券, 4-理财产品, 5-保险, 6-贵金属, 7-外汇, 8-期货)
        List<String> investmentTypes = splitString(source.getInvestmentTypes());
        builder.preferInvestStocks(investmentTypes.contains("1"));             // 股票
        builder.preferInvestFunds(investmentTypes.contains("2"));              // 基金
        builder.preferInvestBonds(investmentTypes.contains("3"));              // 债券
        builder.preferInvestGold(investmentTypes.contains("6"));               // 贵金属
        builder.preferInvestForex(investmentTypes.contains("7"));              // 外汇
        builder.preferInvestWealthProducts(investmentTypes.contains("4"));     // 理财产品
        builder.preferInvestTrust(investmentTypes.contains("trust"));          // 信托(兼容旧数据)
        builder.preferInvestInsurance(investmentTypes.contains("5"));          // 保险
        builder.preferInvestOther(source.getOtherInvestmentType());

        // 3. 品牌类型 (字典值: 1-国际知名品牌, 2-国内知名品牌, 3-本地品牌, 4-高端品牌, 5-性价比品牌)
        List<String> brandTypes = splitString(source.getBrandTypes());
        builder.preferBrandLuxury(brandTypes.contains("luxury"));              // 奢侈品牌(兼容旧数据)
        builder.preferBrandLightLuxury(brandTypes.contains("light_luxury"));   // 轻奢品牌(兼容旧数据)
        builder.preferBrandInternational(brandTypes.contains("1"));            // 国际知名品牌
        builder.preferBrandLocal(brandTypes.contains("3"));                    // 本地品牌
        builder.preferBrandDesigner(brandTypes.contains("designer"));          // 设计师品牌(兼容旧数据)
        builder.preferBrandOther(source.getOtherBrandType());

        // 4. 理财服务 (字典值: 1-资产配置建议, 2-投资组合管理, 3-风险评估, 4-市场分析报告, 5-理财规划, 6-税务筹划, 7-保险规划, 8-退休规划)
        List<String> financialServices = splitString(source.getFinancialServices());
        builder.preferServiceAssetAllocation(financialServices.contains("1"));          // 资产配置建议
        builder.preferServiceMarketAnalysis(financialServices.contains("4"));           // 市场分析报告
        builder.preferServiceProductRecommendation(financialServices.contains("product_recommendation")); // 产品推荐(兼容旧数据)
        builder.preferServiceRiskAssessment(financialServices.contains("3"));           // 风险评估
        builder.preferServiceTaxPlanning(financialServices.contains("6"));              // 税务筹划
        builder.preferServiceInsurancePlanning(financialServices.contains("7"));        // 保险规划
        builder.preferServiceRetirementPlanning(financialServices.contains("8"));       // 退休规划
        builder.preferServiceOther(source.getOtherFinancialService());

        // 5. 联系方式 (字典值: 1-电话, 2-短信, 3-微信, 4-邮件, 5-面访, 6-QQ)
        List<String> contactMethods = splitString(source.getContactMethods());
        builder.preferContactPhone(contactMethods.contains("1"));              // 电话
        builder.preferContactWechat(contactMethods.contains("3"));             // 微信
        builder.preferContactEmail(contactMethods.contains("4"));              // 邮件
        builder.preferContactSms(contactMethods.contains("2"));                // 短信
        builder.preferContactVisit(contactMethods.contains("5"));              // 面访
        builder.preferContactVideo(contactMethods.contains("video"));          // 视频(兼容旧数据)
        builder.preferContactOther(source.getOtherContactMethod());

        // 6. 沙龙活动 (字典值: 1-投资策略讲座, 2-理财产品推介会, 3-财富管理论坛, 4-高端客户酒会, 5-文化艺术活动, 6-健康养生讲座, 7-高尔夫活动, 8-亲子活动)
        List<String> salonActivities = splitString(source.getSalonActivities());
        builder.preferSalonInvestmentSeminar(salonActivities.contains("1"));   // 投资策略讲座
        builder.preferSalonWineTasting(salonActivities.contains("wine_tasting")); // 品酒会(兼容旧数据)
        builder.preferSalonGolf(salonActivities.contains("7"));                // 高尔夫活动
        builder.preferSalonArtAppreciation(salonActivities.contains("5"));     // 文化艺术活动
        builder.preferSalonHealthWellness(salonActivities.contains("6"));      // 健康养生讲座
        builder.preferSalonParentChild(salonActivities.contains("8"));         // 亲子活动
        builder.preferSalonOther(source.getOtherSalonActivity());

        // 7. 兴趣爱好 (字典值: 1-运动健身, 2-旅游, 3-阅读, 4-音乐, 5-书法绘画, 6-收藏, 7-美食, 8-摄影, 9-园艺, 10-棋牌)
        List<String> interestHobbies = splitString(source.getInterestHobbies());
        builder.hobbySports(interestHobbies.contains("1"));                    // 运动健身
        builder.hobbyReading(interestHobbies.contains("3"));                   // 阅读
        builder.hobbyTravel(interestHobbies.contains("2"));                    // 旅游
        builder.hobbyMusic(interestHobbies.contains("4"));                     // 音乐
        builder.hobbyFood(interestHobbies.contains("7"));                      // 美食
        builder.hobbyPhotography(interestHobbies.contains("8"));               // 摄影
        builder.hobbyPainting(interestHobbies.contains("5"));                  // 书法绘画
        builder.hobbyCollecting(interestHobbies.contains("6"));                // 收藏
        builder.hobbyGardening(interestHobbies.contains("9"));                 // 园艺
        builder.hobbyChess(interestHobbies.contains("10"));                    // 棋牌
        builder.hobbyOther(source.getOtherInterestHobby());

        // 8. 联系时间 (字典值: 1-工作日上午, 2-工作日下午, 3-工作日晚上, 4-周末上午, 5-周末下午, 6-周末晚上, 7-节假日)
        List<String> contactTimes = splitString(source.getContactTimes());
        builder.preferTimeWorkdayMorning(contactTimes.contains("1"));          // 工作日上午
        builder.preferTimeWorkdayAfternoon(contactTimes.contains("2"));        // 工作日下午
        builder.preferTimeWorkdayEvening(contactTimes.contains("3"));          // 工作日晚上
        builder.preferTimeWeekendMorning(contactTimes.contains("4"));          // 周末上午
        builder.preferTimeWeekendAfternoon(contactTimes.contains("5"));        // 周末下午
        builder.preferTimeWeekendEvening(contactTimes.contains("6"));          // 周末晚上
        builder.preferTimeOther(source.getOtherContactTime());

        // 9. 投资周期 (字典值: 1-短期(1年以内), 2-中短期(1-3年), 3-中期(3-5年), 4-中长期(5-10年), 5-长期(10年以上))
        List<String> investmentPeriods = splitString(source.getInvestmentPeriods());
        builder.preferPeriodShortTerm(investmentPeriods.contains("1"));        // 短期
        builder.preferPeriodMediumTerm(investmentPeriods.contains("2"));       // 中短期
        builder.preferPeriodLongTerm(investmentPeriods.contains("3"));         // 中期
        builder.preferPeriodUltraLongTerm(investmentPeriods.contains("4"));    // 中长期
        builder.preferPeriodFlexible(investmentPeriods.contains("flexible"));  // 灵活(兼容旧数据)
        builder.preferPeriodOther(source.getOtherInvestmentPeriod());

        return builder.build();
    }

    /**
     * 将多个布尔字段转换为逗号分隔字符串
     */
    private static String convertBooleanFieldsToString(BooleanField... fields) {
        List<String> values = new ArrayList<>();
        for (BooleanField field : fields) {
            if (Boolean.TRUE.equals(field.value)) {
                values.add(field.key);
            }
        }
        return values.isEmpty() ? null : String.join(",", values);
    }

    /**
     * 将逗号分隔字符串分割为列表
     */
    private static List<String> splitString(String str) {
        if (str == null || str.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (String s : str.split(",")) {
            if (!s.trim().isEmpty()) {
                result.add(s.trim());
            }
        }
        return result;
    }

    /**
     * 布尔字段包装类
     */
    private static class BooleanField {
        String key;
        Boolean value;

        BooleanField(String key, Boolean value) {
            this.key = key;
            this.value = value;
        }
    }
}
