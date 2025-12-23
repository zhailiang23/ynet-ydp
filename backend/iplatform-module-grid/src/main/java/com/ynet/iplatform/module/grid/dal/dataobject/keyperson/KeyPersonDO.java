package com.ynet.iplatform.module.grid.dal.dataobject.keyperson;

import lombok.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 关键人信息 DO
 *
 * @author 易诚原生智能平台
 */
@TableName("grid_key_person")
@KeySequence("grid_key_person_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyPersonDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 姓名
     */
    private String personName;
    /**
     * 单位/小区
     */
    private String organization;
    /**
     * 职务
     */
    private String position;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 建立联系日期
     */
    private LocalDate establishDate;
    /**
     * 最新维护日期
     */
    private LocalDate lastMaintainDate;
    /**
     * 员工工号
     */
    private String employeeCode;
    /**
     * 员工姓名
     */
    private String employeeName;


}