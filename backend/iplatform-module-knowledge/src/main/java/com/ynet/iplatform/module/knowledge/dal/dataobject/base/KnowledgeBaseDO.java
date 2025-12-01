package com.ynet.iplatform.module.knowledge.dal.dataobject.base;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 知识库 DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_base")
@KeySequence("knowledge_base_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeBaseDO extends BaseDO {

    /**
     * 知识库ID
     */
    @TableId
    private Long id;
    /**
     * 知识库名称
     */
    private String name;
    /**
     * 知识库描述
     */
    private String description;
    /**
     * 知识库所有者ID
     */
    private Long ownerId;
    /**
     * 状态（0正常 1停用）
     */
    private Integer status;


}