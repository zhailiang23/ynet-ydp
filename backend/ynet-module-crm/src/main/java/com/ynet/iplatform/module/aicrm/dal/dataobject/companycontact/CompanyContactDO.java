package com.ynet.iplatform.module.aicrm.dal.dataobject.companycontact;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM对公客户联系人信息 DO
 *
 * @author 芋道源码
 */
@TableName("crm_company_contact")
@KeySequence("crm_company_contact_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContactDO extends BaseDO {

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
     * 联系方式类型(手机、座机、QQ、微信、邮箱等)
     */
    private String contactType;
    /**
     * 联系人姓名
     */
    private String contactPerson;
    /**
     * 联系方式(电话号码、QQ号、微信号等)
     */
    private String contactMethod;
    /**
     * 是否首选项(否、是)
     */
    private Boolean isPrimary;
    /**
     * 来源系统(ECIF、CRM、零售CRM等)
     */
    private String sourceSystem;
    /**
     * 联系方式序号
     */
    private Integer contactSeq;
    /**
     * 联系方式描述
     */
    private String contactDesc;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * ETL导入日期
     */
    private LocalDate etlDate;
    /**
     * 老系统交易序列号
     */
    private String oldTxSeqNo;
    /**
     * 老系统最后更新系统
     */
    private String oldLastUpdateSys;
    /**
     * 老系统最后更新用户
     */
    private String oldLastUpdateUser;
    /**
     * 老系统最后更新时间
     */
    private LocalDateTime oldLastUpdateTm;
    /**
     * 老系统联系方式ID
     */
    private Integer oldContactId;


}