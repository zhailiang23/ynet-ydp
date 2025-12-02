package com.ynet.iplatform.module.aicrm.dal.dataobject.practicecourse;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-陪练课程 DO
 *
 * @author 易诚源码
 */
@TableName("crm_practice_course")
@KeySequence("crm_practice_course_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeCourseDO extends BaseDO {

    /**
     * 课程ID
     */
    @TableId
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程描述
     */
    private String description;
    /**
     * 关联陪练剧本ID
     */
    private Long scriptId;
    /**
     * 课程类型 1.标准.0.个人
     */
    private Long standard;
    /**
     * 课程复杂度 1.复杂.0.简单
     */
    private Long hard;
    /**
     * 课程状态：字典 aicrm_course_status
     */
    private String status;

    /**
     * 培训人数
     */
    private Integer trainingCount;

    // 标准getter/setter方法（为了确保编译通过）
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public Long getStandard() {
        return standard;
    }

    public void setStandard(Long standard) {
        this.standard = standard;
    }

    public Long getHard() {
        return hard;
    }

    public void setHard(Long hard) {
        this.hard = hard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}