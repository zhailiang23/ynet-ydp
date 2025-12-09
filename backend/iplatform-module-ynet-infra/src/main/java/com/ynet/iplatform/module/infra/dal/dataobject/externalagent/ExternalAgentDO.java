package com.ynet.iplatform.module.infra.dal.dataobject.externalagent;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 外部智能体管理 DO
 *
 * @author 易诚源码
 */
@TableName("infra_external_agent")
@KeySequence("infra_external_agent_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalAgentDO extends BaseDO {

    /**
     * 智能体ID
     */
    @TableId
    private Long id;
    /**
     * 智能体编码
     */
    private String code;
    /**
     * 智能体名称
     */
    private String name;
    /**
     * 智能体描述
     */
    private String description;
    /**
     * 平台类型（dify, hiagent, coze等）
     */
    private String platformType;
    /**
     * 访问URL
     */
    private String accessUrl;
    /**
     * API密钥
     */
    private String apiKey;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;


}