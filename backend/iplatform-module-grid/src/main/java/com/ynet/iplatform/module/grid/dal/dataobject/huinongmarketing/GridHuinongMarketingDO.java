package com.ynet.iplatform.module.grid.dal.dataobject.huinongmarketing;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 惠农营销 DO
 *
 * @author 易诚源码
 */
@TableName("grid_huinong_marketing")
@KeySequence("grid_huinong_marketing_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridHuinongMarketingDO extends BaseDO {

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
     * 站点ID
     */
    private Long stationId;
    /**
     * 村名称
     */
    private String villageName;
    /**
     * 村位置坐标（格式：经度,纬度）
     */
    private String villageLocation;
    /**
     * 村地址
     */
    private String villageAddress;
    /**
     * 户籍人口
     */
    private Integer registeredPopulation;
    /**
     * 常住人口
     */
    private Integer residentPopulation;
    /**
     * 产业情况
     */
    private String industrySituation;
    /**
     * 主要产业
     */
    private String mainIndustries;
    /**
     * 需求分析
     */
    private String demandAnalysis;
    /**
     * 营销计划
     */
    private String marketingPlan;
    /**
     * 客户类型（JSON数组，存储多个客户类型）
     */
    private String customerType;
    /**
     * 客户数量（JSON对象，存储每种客户类型对应的数量）
     */
    private String customerCount;
    /**
     * 惠农专员ID
     */
    private Long specialistId;
    /**
     * 员工工号
     */
    private String employeeNo;

    /**
     * 网格营销标识（从关联的站点表中查询）
     * 该字段不对应数据库表字段，仅用于接收关联查询结果
     */
    @TableField(exist = false)
    private String gridMarketingFlag;

}
