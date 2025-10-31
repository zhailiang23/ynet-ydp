package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassetsnapshot;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户资产快照表 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_asset_snapshot")
@KeySequence("crm_customer_asset_snapshot_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAssetSnapshotDO extends BaseDO {

    /**
     * 快照主键
     */
    @TableId
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 快照日期
     */
    private LocalDate snapshotDate;

    /**
     * 快照类型
     */
    private String snapshotType;

    // 资产类数据
    /**
     * 总资产
     */
    private BigDecimal totalAssets;

    /**
     * 存款余额
     */
    private BigDecimal depositBalance;

    /**
     * 理财余额
     */
    private BigDecimal wealthBalance;

    /**
     * 基金市值
     */
    private BigDecimal fundBalance;

    /**
     * 保险价值
     */
    private BigDecimal insuranceBalance;

    /**
     * 贵金属余额
     */
    private BigDecimal metalBalance;

    /**
     * 信托余额
     */
    private BigDecimal trustBalance;

    /**
     * 其他资产
     */
    private BigDecimal otherBalance;

    // 负债类数据
    /**
     * 总负债
     */
    private BigDecimal totalLiabilities;

    /**
     * 贷款余额
     */
    private BigDecimal loanBalance;

    /**
     * 信用卡欠款
     */
    private BigDecimal creditcardBalance;

    /**
     * 净资产
     */
    private BigDecimal netAssets;

    // 增长率
    /**
     * 总资产环比增长率
     */
    private BigDecimal totalAssetsGrowth;

    /**
     * 存款环比增长率
     */
    private BigDecimal depositGrowth;

    /**
     * 理财环比增长率
     */
    private BigDecimal wealthGrowth;

    // 账户数量
    /**
     * 存款账户数量
     */
    private Integer depositAccountCount;

    /**
     * 理财产品数量
     */
    private Integer wealthAccountCount;

    /**
     * 基金数量
     */
    private Integer fundAccountCount;

    /**
     * 贷款数量
     */
    private Integer loanAccountCount;

    /**
     * 信用卡数量
     */
    private Integer creditcardCount;

    /**
     * 保险数量
     */
    private Integer insuranceCount;

    /**
     * 备注
     */
    private String remark;

}
