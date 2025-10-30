package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpoints;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户积分信息 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_points")
@KeySequence("crm_customer_points_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPointsDO extends BaseDO {

    /**
     * 积分主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 可用积分
     */
    private Long availablePoints;
    /**
     * 历史累计赠送积分
     */
    private Long historyTotalGiftPoints;
    /**
     * 历史累计扣减积分
     */
    private Long historyTotalDeductPoints;
    /**
     * 历史累计失效积分
     */
    private Long historyTotalExpirePoints;
    /**
     * 即将失效积分
     */
    private Long upcomingExpirePoints;
    /**
     * 即将失效积分日期
     */
    private LocalDate upcomingExpireDate;
    /**
     * 积分账户编号
     */
    private String pointsAccountNo;
    /**
     * 积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）
     */
    private String pointsLevel;
    /**
     * 累计获得积分（包含赠送和交易）
     */
    private Long totalEarnedPoints;
    /**
     * 累计使用积分
     */
    private Long totalUsedPoints;
    /**
     * 冻结积分
     */
    private Long frozenPoints;
    /**
     * 历史累计交易积分
     */
    private Long historyTotalTransactionPoints;
    /**
     * 历史累计兑换积分
     */
    private Long historyTotalRedeemPoints;
    /**
     * 积分余额（可用+冻结）
     */
    private Long pointsBalance;
    /**
     * 账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）
     */
    private String accountStatus;
    /**
     * 开户日期
     */
    private LocalDate openDate;
    /**
     * 最后交易日期
     */
    private LocalDate lastTransactionDate;
    /**
     * 最后交易时间
     */
    private LocalDateTime lastTransactionTime;
    /**
     * 最后赠送日期
     */
    private LocalDate lastGiftDate;
    /**
     * 最后兑换日期
     */
    private LocalDate lastRedeemDate;
    /**
     * 积分有效期（月）
     */
    private Integer pointsValidMonths;
    /**
     * 是否自动失效
     */
    private Boolean isAutoExpire;
    /**
     * 自动失效提醒天数
     */
    private Integer autoExpireRemindDays;
    /**
     * 本年度获得积分
     */
    private Long yearEarnedPoints;
    /**
     * 本年度使用积分
     */
    private Long yearUsedPoints;
    /**
     * 本月获得积分
     */
    private Long monthEarnedPoints;
    /**
     * 本月使用积分
     */
    private Long monthUsedPoints;
    /**
     * 备注
     */
    private String remark;


}