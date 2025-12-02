package com.ynet.iplatform.module.aicrm.dal.dataobject.companyorganization;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM对公客户组织架构信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_company_organization")
@KeySequence("crm_company_organization_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOrganizationDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID(关联crm_company_customer.customer_id)
     */
    private Long customerId;
    /**
     * 父级组织ID(顶级组织为NULL)
     */
    private Long parentId;
    /**
     * 组织编码
     */
    private String orgCode;
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 组织全称
     */
    private String orgFullName;
    /**
     * 组织层级(1-一级,2-二级,3-三级...)
     */
    private Integer orgLevel;
    /**
     * 组织类型(总部、分公司、事业部、部门等)
     */
    private String orgType;
    /**
     * 组织状态(active-启用,inactive-停用,dissolved-解散)
     */
    private String orgStatus;
    /**
     * 负责人姓名
     */
    private String leaderName;
    /**
     * 负责人职位
     */
    private String leaderPosition;
    /**
     * 负责人电话
     */
    private String leaderPhone;
    /**
     * 负责人邮箱
     */
    private String leaderEmail;
    /**
     * 员工人数
     */
    private Integer employeeCount;
    /**
     * 成立日期
     */
    private LocalDate establishedDate;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 联系邮箱
     */
    private String contactEmail;
    /**
     * 办公地址
     */
    private String contactAddress;
    /**
     * 业务范围
     */
    private String businessScope;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 排序号(同级组织排序)
     */
    private Integer sortOrder;


}