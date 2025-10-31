package cn.iocoder.yudao.module.aicrm.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * CRM 错误码枚举类
 *
 * crm 系统，使用 1-020-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 客户主表 1-020-000-000 ==========
    ErrorCode CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_000_000, "客户不存在");

    // ========== 零售客户 1-020-001-000 ==========
    ErrorCode RETAIL_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_001_000, "零售客户不存在");

    // ========== 对公客户 1-020-002-000 ==========
    ErrorCode COMPANY_CUSTOMER_NOT_EXISTS = new ErrorCode(1_020_002_000, "对公客户不存在");

    // ========== 客户证件信息 1-020-003-000 ==========
    ErrorCode CUSTOMER_IDENTITY_NOT_EXISTS = new ErrorCode(1_020_003_000, "客户证件信息不存在");

    // ========== 客户工作或经营信息 1-020-004-000 ==========
    ErrorCode CUSTOMER_WORK_NOT_EXISTS = new ErrorCode(1_020_004_000, "客户工作或经营信息不存在");

    // ========== 客户家庭信息 1-020-005-000 ==========
    ErrorCode CUSTOMER_FAMILY_NOT_EXISTS = new ErrorCode(1_020_005_000, "客户家庭信息不存在");

    // ========== 客户家庭成员信息 1-020-006-000 ==========
    ErrorCode CUSTOMER_FAMILY_MEMBER_NOT_EXISTS = new ErrorCode(1_020_006_000, "客户家庭成员信息不存在");

    // ========== 客户归属关系表 1-020-007-000 ==========
    ErrorCode CUSTOMER_ASSIGNMENT_NOT_EXISTS = new ErrorCode(1_020_007_000, "客户归属关系不存在");

    // ========== 客户归属调整历史表 1-020-008-000 ==========
    ErrorCode CUSTOMER_ASSIGNMENT_HISTORY_NOT_EXISTS = new ErrorCode(1_020_008_000, "客户归属调整历史不存在");

    // ========== 客户大事记 1-020-009-000 ==========
    ErrorCode CUSTOMER_IMPORTANT_EVENT_NOT_EXISTS = new ErrorCode(1_020_009_000, "客户大事记不存在");

    // ========== 客户偏好 1-020-010-000 ==========
    ErrorCode CUSTOMER_PREFERENCE_NOT_EXISTS = new ErrorCode(1_020_010_000, "客户偏好不存在");

    // ========== 客户网格归属 1-020-011-000 ==========
    ErrorCode CUSTOMER_GRID_ASSIGNMENT_NOT_EXISTS = new ErrorCode(1_020_011_000, "客户网格归属不存在");

    // ========== 客户网格归属历史 1-020-012-000 ==========
    ErrorCode CUSTOMER_GRID_HISTORY_NOT_EXISTS = new ErrorCode(1_020_012_000, "客户网格归属历史不存在");

    // ========== 客户群组归属 1-020-013-000 ==========
    ErrorCode CUSTOMER_GROUP_ASSIGNMENT_NOT_EXISTS = new ErrorCode(1_020_013_000, "客户群组归属不存在");

    // ========== 客户群组归属历史 1-020-014-000 ==========
    ErrorCode CUSTOMER_GROUP_HISTORY_NOT_EXISTS = new ErrorCode(1_020_014_000, "客户群组归属历史不存在");

    // ========== 客户存款账户 1-020-020-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_DEPOSIT_NOT_EXISTS = new ErrorCode(1_020_020_000, "客户存款账户不存在");

    // ========== 客户信用卡账户 1-020-021-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_CREDITCARD_NOT_EXISTS = new ErrorCode(1_020_021_000, "客户信用卡账户不存在");

    // ========== 客户贷款账户 1-020-022-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_LOAN_NOT_EXISTS = new ErrorCode(1_020_022_000, "客户贷款账户不存在");

    // ========== 客户理财账户 1-020-023-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_WEALTH_NOT_EXISTS = new ErrorCode(1_020_023_000, "客户理财账户不存在");

    // ========== 客户基金账户 1-020-024-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_FUND_NOT_EXISTS = new ErrorCode(1_020_024_000, "客户基金账户不存在");

    // ========== 客户保险账户 1-020-025-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_INSURANCE_NOT_EXISTS = new ErrorCode(1_020_025_000, "客户保险账户不存在");

    // ========== 客户信托账户 1-020-026-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_TRUST_NOT_EXISTS = new ErrorCode(1_020_026_000, "客户信托账户不存在");

    // ========== 客户贵金属账户 1-020-027-000 ==========
    ErrorCode CUSTOMER_ACCOUNT_METAL_NOT_EXISTS = new ErrorCode(1_020_027_000, "客户贵金属账户不存在");

    // ========== 客户合同信息 1-020-030-000 ==========
    ErrorCode CUSTOMER_CONTRACT_NOT_EXISTS = new ErrorCode(1_020_030_000, "客户合同信息不存在");

    // ========== 客户评级信息 1-020-031-000 ==========
    ErrorCode CUSTOMER_RATING_NOT_EXISTS = new ErrorCode(1_020_031_000, "客户评级信息不存在");

    // ========== 客户评级历史 1-020-032-000 ==========
    ErrorCode CUSTOMER_RATING_HISTORY_NOT_EXISTS = new ErrorCode(1_020_032_000, "客户评级历史不存在");

    // ========== 客户渠道行为 1-020-033-000 ==========
    ErrorCode CUSTOMER_CHANNEL_BEHAVIOR_NOT_EXISTS = new ErrorCode(1_020_033_000, "客户渠道行为不存在");

    // ========== 客户提醒信息 1-020-034-000 ==========
    ErrorCode CUSTOMER_REMINDER_NOT_EXISTS = new ErrorCode(1_020_034_000, "客户提醒信息不存在");

    // ========== 客户担保抵押信息 1-020-035-000 ==========
    ErrorCode CUSTOMER_GUARANTEE_MORTGAGE_NOT_EXISTS = new ErrorCode(1_020_035_000, "客户担保抵押信息不存在");

    // ========== 客户担保质押信息 1-020-036-000 ==========
    ErrorCode CUSTOMER_GUARANTEE_PLEDGE_NOT_EXISTS = new ErrorCode(1_020_036_000, "客户担保质押信息不存在");

    // ========== 客户担保人信息 1-020-037-000 ==========
    ErrorCode CUSTOMER_GUARANTOR_NOT_EXISTS = new ErrorCode(1_020_037_000, "客户担保人信息不存在");

    // ========== 客户积分信息 1-020-038-000 ==========
    ErrorCode CUSTOMER_POINTS_NOT_EXISTS = new ErrorCode(1_020_038_000, "客户积分信息不存在");

    // ========== 客户积分交易 1-020-039-000 ==========
    ErrorCode CUSTOMER_POINTS_TRANSACTION_NOT_EXISTS = new ErrorCode(1_020_039_000, "客户积分交易不存在");

    // ========== 客户接触信息 1-020-040-000 ==========
    ErrorCode CUSTOMER_CONTACT_NOT_EXISTS = new ErrorCode(1_020_040_000, "客户接触信息不存在");

    // ========== 客户营销活动 1-020-041-000 ==========
    ErrorCode CUSTOMER_MARKETING_ACTIVITY_NOT_EXISTS = new ErrorCode(1_020_041_000, "客户营销活动不存在");

    // ========== 客户产品推荐 1-020-042-000 ==========
    ErrorCode CUSTOMER_PRODUCT_RECOMMENDATION_NOT_EXISTS = new ErrorCode(1_020_042_000, "客户产品推荐不存在");

    // ========== 客户贡献度 1-020-043-000 ==========
    ErrorCode CUSTOMER_CONTRIBUTION_NOT_EXISTS = new ErrorCode(1_020_043_000, "客户贡献度不存在");

    // ========== 客户交易明细(模拟) 1-020-044-000 ==========
    ErrorCode CUSTOMER_TRANSACTION_MOCK_NOT_EXISTS = new ErrorCode(1_020_044_000, "客户交易明细不存在");

    // ========== 客户需求信息 1-020-045-000 ==========
    ErrorCode CUSTOMER_DEMAND_NOT_EXISTS = new ErrorCode(1_020_045_000, "客户需求信息不存在");

    // ========== 客户投诉信息 1-020-046-000 ==========
    ErrorCode CUSTOMER_COMPLAINT_NOT_EXISTS = new ErrorCode(1_020_046_000, "客户投诉信息不存在");

    // ========== 客户产品持有信息 1-020-047-000 ==========
    ErrorCode CUSTOMER_PRODUCT_HOLDING_NOT_EXISTS = new ErrorCode(1_020_047_000, "客户产品持有信息不存在");

    // ========== 客户优惠券信息 1-020-048-000 ==========
    ErrorCode CUSTOMER_COUPON_NOT_EXISTS = new ErrorCode(1_020_048_000, "客户优惠券信息不存在");

    // ========== 客户授信信息 1-020-049-000 ==========
    ErrorCode CUSTOMER_CREDIT_NOT_EXISTS = new ErrorCode(1_020_049_000, "客户授信信息不存在");

    // ========== 客户授信明细 1-020-050-000 ==========
    ErrorCode CUSTOMER_CREDIT_DETAIL_NOT_EXISTS = new ErrorCode(1_020_050_000, "客户授信明细不存在");

    // ========== 产品分类 1-020-060-000 ==========
    ErrorCode PRODUCT_CATEGORY_NOT_EXISTS = new ErrorCode(1_020_060_000, "产品分类不存在");
    ErrorCode PRODUCT_CATEGORY_EXITS_CHILDREN = new ErrorCode(1_020_060_001, "产品分类存在子分类，无法删除");
    ErrorCode PRODUCT_CATEGORY_PARENT_ERROR = new ErrorCode(1_020_060_002, "不能设置自己为父分类");
    ErrorCode PRODUCT_CATEGORY_PARENT_NOT_EXITS = new ErrorCode(1_020_060_003, "父产品分类不存在");
    ErrorCode PRODUCT_CATEGORY_PARENT_IS_CHILD = new ErrorCode(1_020_060_004, "父产品分类不能是自己的子分类");
    ErrorCode PRODUCT_CATEGORY_CATEGORY_NAME_DUPLICATE = new ErrorCode(1_020_060_005, "同一父分类下已存在该分类名称");

    // ========== 对公客户补充信息 1-020-070-000 ==========
    ErrorCode COMPANY_ADDRESS_NOT_EXISTS = new ErrorCode(1_020_070_000, "对公客户地址不存在");
    ErrorCode COMPANY_BOND_NOT_EXISTS = new ErrorCode(1_020_071_000, "对公客户债券不存在");
    ErrorCode COMPANY_CONTACT_NOT_EXISTS = new ErrorCode(1_020_072_000, "对公客户联系人不存在");
    ErrorCode COMPANY_ORGANIZATION_NOT_EXISTS = new ErrorCode(1_020_073_000, "对公客户组织架构不存在");
    ErrorCode COMPANY_OTHER_BANK_NOT_EXISTS = new ErrorCode(1_020_074_000, "对公客户他行信息不存在");
    ErrorCode COMPANY_PROJECT_NOT_EXISTS = new ErrorCode(1_020_075_000, "对公客户项目信息不存在");
    ErrorCode COMPANY_STOCK_NOT_EXISTS = new ErrorCode(1_020_076_000, "对公客户股票不存在");

    // ========== 客户工作信息 1-020-080-000 ==========
    ErrorCode CUSTOMER_WORK_INFO_NOT_EXISTS = new ErrorCode(1_020_080_000, "客户工作信息不存在");

    // ========== 客户经营信息 1-020-081-000 ==========
    ErrorCode CUSTOMER_BUSINESS_INFO_NOT_EXISTS = new ErrorCode(1_020_081_000, "客户经营信息不存在");

    // ========== 客户渠道业务概览 1-020-090-000 ==========
    ErrorCode CUSTOMER_CHANNEL_OVERVIEW_NOT_EXISTS = new ErrorCode(1_020_090_000, "客户渠道业务概览不存在");

    // ========== 客户存款业务概览 1-020-091-000 ==========
    ErrorCode CUSTOMER_DEPOSIT_OVERVIEW_NOT_EXISTS = new ErrorCode(1_020_091_000, "客户存款业务概览不存在");

    // ========== 客户贴现业务概览 1-020-092-000 ==========
    ErrorCode CUSTOMER_DISCOUNT_OVERVIEW_NOT_EXISTS = new ErrorCode(1_020_092_000, "客户贴现业务概览不存在");

    // ========== 客户贷款业务概览 1-020-093-000 ==========
    ErrorCode CUSTOMER_LOAN_OVERVIEW_NOT_EXISTS = new ErrorCode(1_020_093_000, "客户贷款业务概览不存在");

    // ========== 客户中间业务概览 1-020-094-000 ==========
    ErrorCode CUSTOMER_MIDDLE_BUSINESS_OVERVIEW_NOT_EXISTS = new ErrorCode(1_020_094_000, "客户中间业务概览不存在");

    // ========== 客户表外业务概览 1-020-095-000 ==========
    ErrorCode CUSTOMER_OFFBALANCE_OVERVIEW_NOT_EXISTS = new ErrorCode(1_020_095_000, "客户表外业务概览不存在");

}
