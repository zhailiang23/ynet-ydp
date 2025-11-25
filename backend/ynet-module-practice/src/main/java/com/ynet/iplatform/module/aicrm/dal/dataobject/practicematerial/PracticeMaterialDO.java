package com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-培训文件 DO
 *
 * @author 芋道源码
 */
@TableName("crm_practice_material")
@KeySequence("crm_practice_material_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeMaterialDO extends BaseDO {

    /**
     * 文件ID
     */
    @TableId
    private Long id;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件类型
     *
     * 枚举 {@link com.ynet.iplatform.module.aicrm.enums.CrmDictTypeConstants#PRACTICE_MATERIAL_FILE_TYPE}
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
     * 文件内容(纯文本)
     */
    private String content;
    /**
     * 文件内容(富文本)
     */
    private String contentRich;

    /**
     * 关联课程名称(非数据库字段,仅用于返回)
     */
    @TableField(exist = false)
    private String courseName;

    /**
     * 培训人数
     */
    private Integer trainingCount;

}