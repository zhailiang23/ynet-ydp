package com.ynet.iplatform.module.task.dal.dataobject.factor;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务评分因子 DO
 *
 * @author ynet
 */
@TableName("aicrm_task_scoring_factor")
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskScoringFactorDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 因子名称（中文）
     */
    private String factorName;

    /**
     * 因子英文名
     */
    private String factorNameEn;

    /**
     * Material Symbols图标名
     */
    private String icon;

    /**
     * 权重(%)
     */
    private Integer weight;

    /**
     * 因子说明
     */
    private String description;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 条件逻辑关系: AND/OR
     */
    private String logicType;

    /**
     * 影响方式: direct-直接设定评分, weight-调整评分权重, score-评分值加减
     */
    private String impactType;

    /**
     * 增加/减少分值 (-100到100)
     */
    private Integer scoreAdjustment;

    /**
     * 排序（升序）
     */
    private Integer sort;
}
