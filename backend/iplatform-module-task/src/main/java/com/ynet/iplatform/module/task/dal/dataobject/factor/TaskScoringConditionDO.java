package com.ynet.iplatform.module.task.dal.dataobject.factor;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务评分条件 DO
 *
 * @author ynet
 */
@TableName("aicrm_task_scoring_condition")
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskScoringConditionDO extends BaseDO {

    @TableId
    private Long id;

    /**
     * 所属评分因子ID
     */
    private Long factorId;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 字段/属性名称
     */
    private String fieldName;

    /**
     * 操作符
     */
    private String operator;

    /**
     * 比较值
     */
    private String fieldValue;

    /**
     * 排序
     */
    private Integer sort;
}
