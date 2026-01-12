package com.ynet.iplatform.module.task.service.scoring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 客户评分数据 DTO
 * 用于任务动态评分，避免直接依赖 CRM 模块导致循环依赖
 *
 * @author ynet
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerScoreData {

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 信用评分（用于代替账户余额）
     */
    private BigDecimal creditScore;

    /**
     * 客户等级
     */
    private String customerLevel;

    /**
     * 信用等级（风险评级）
     */
    private String creditLevel;

    /**
     * 创建时间（用于计算开户时长）
     */
    private LocalDateTime createTime;
}
