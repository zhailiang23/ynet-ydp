package com.ynet.iplatform.module.aicrm.dal.dataobject.practicescript;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-陪练剧本表（支持版本控制） DO
 *
 * @author 易诚源码
 */
@TableName("crm_practice_script")
@KeySequence("crm_practice_script_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeScriptDO extends BaseDO {

    /**
     * 剧本版本ID（主键）
     */
    @TableId
    private Long id;
    /**
     * 剧本编号（标识同一个剧本的不同版本）
     */
    private String scriptNo;
    /**
     * 版本号（如 v1.0, v1.1, v2.0）
     */
    private String version;
    /**
     * 是否最新版本
     */
    private Boolean isLatest;
    /**
     * 父版本ID（用于追溯版本历史）
     */
    private Long parentVersionId;
    /**
     * 版本说明（本次修改的内容）
     */
    private String versionDescription;
    /**
     * 版本状态：字典 aicrm_script_status
     */
    private String status;
    /**
     * 剧本名称
     */
    private String name;
    /**
     * 剧本描述
     */
    private String description;
    /**
     * 难度等级：字典 aicrm_difficulty_level
     */
    private String difficultyLevel;
    /**
     * 营销环节：字典 aicrm_marketing_step
     */
    private String marketingStep;
    /**
     * 关联销售案例ID
     */
    private Long caseId;
    /**
     * 关联销售技巧ID
     */
    private Long skillId;
    /**
     * 关联虚拟客户ID
     */
    private Long virtualCustomerId;
    /**
     * 关联培训文件ID列表（多个ID逗号分隔）
     */
    private String materialIds;
    /**
     * 剧本内容（AI生成）
     */
    private String content;
    /**
     * 手工调整内容（用户编辑）
     */
    private String contentEdit;
    /**
     * 剧本来源
     */
    private String contentSource;
    /**
     * 生成状态: pending-待生成, generating-生成中, completed-已完成, failed-失败
     */
    private String generationStatus;
    /**
     * 使用次数（该版本被使用的次数）
     */
    private Integer usageCount;

    /**
     * 培训人数
     */
    private Integer trainingCount;

    // 标准getter/setter方法（为了确保编译通过）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}