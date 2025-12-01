package com.ynet.iplatform.module.knowledge.dal.dataobject.file;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 知识库文件 DO
 *
 * @author 芋道源码
 */
@TableName("knowledge_file")
@KeySequence("knowledge_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KonwledgeFileDO extends BaseDO {

    /**
     * 文件ID
     */
    @TableId
    private Long id;
    /**
     * 知识库ID
     */
    private Long knowledgeBaseId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件URL
     */
    private String fileUrl;
    /**
     * 文件大小（字节）
     */
    private Long fileSize;
    /**
     * 文件状态（0处理中 1处理完成 2处理失败）
     */
    private Integer status;


}