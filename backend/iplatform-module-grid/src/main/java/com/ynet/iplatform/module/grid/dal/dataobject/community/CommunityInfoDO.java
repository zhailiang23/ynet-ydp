package com.ynet.iplatform.module.grid.dal.dataobject.community;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;

/**
 * 社区信息 DO
 *
 * @author 易诚源码
 */
@TableName("grid_community_info")
@KeySequence("grid_community_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 社区编号
     */
    private String communityCode;
    /**
     * 社区名称
     */
    private String communityName;
    /**
     * 团队人数
     */
    private Integer teamCount;
    /**
     * 责任人工号（关联system_users.id）
     */
    private Long managerUserId;
    /**
     * 责任人姓名
     */
    private String managerUserName;
    /**
     * 人口（户数）
     */
    private Integer householdCount;
    /**
     * 个体工商户数
     */
    private Integer individualBusinessCount;
    /**
     * 小微企业数
     */
    private Integer smallEnterpriseCount;
    /**
     * 商业综合体数
     */
    private Integer commercialComplexCount;
    /**
     * 优质单位数
     */
    private Integer qualityUnitCount;
    /**
     * 评分
     */
    private BigDecimal score;
    /**
     * 校正评分
     */
    private BigDecimal adjustedScore;
    /**
     * 校正原因
     */
    private String adjustmentReason;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 社区地址
     */
    private String address;
    /**
     * 状态（ACTIVE=正常，INACTIVE=无效）
     */
    private String status;

}
