package com.ynet.iplatform.module.grid.dal.dataobject.zerodaimarketdescription;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 零贷市场描述 DO
 *
 * @author 易诚源码
 */
@TableName("grid_zerodai_market_description")
@KeySequence("grid_zerodai_market_description_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridZerodaiMarketDescriptionDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 网格ID
     */
    private Long gridId;
    /**
     * 市场名称
     */
    private String marketName;
    /**
     * 市场类型
     */
    private String marketType;
    /**
     * 位置坐标
     */
    private byte[] location;
    /**
     * 地址
     */
    private String address;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 商户数量
     */
    private Integer merchantCount;
    /**
     * 日均客流量
     */
    private Integer dailyTraffic;
    /**
     * 市场特点
     */
    private String marketFeatures;
    /**
     * 目标客户群体
     */
    private String targetCustomers;
    /**
     * 潜力分析
     */
    private String potentialAnalysis;
    /**
     * 负责人ID
     */
    private Long managerId;

}
