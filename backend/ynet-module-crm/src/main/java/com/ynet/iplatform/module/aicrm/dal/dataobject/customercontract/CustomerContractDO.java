package com.ynet.iplatform.module.aicrm.dal.dataobject.customercontract;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户签约信息表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_contract")
@KeySequence("crm_customer_contract_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContractDO extends BaseDO {

    /**
     * 签约主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 签约类型ID（关联 crm_contract_type.id）
     */
    private Long contractTypeId;
    /**
     * 签约账号/签约编号
     */
    private String contractNo;
    /**
     * 签约日期
     */
    private LocalDate contractDate;
    /**
     * 签约机构ID（关联 system_dept.id）
     */
    private Long branchId;
    /**
     * 签约机构名称
     */
    private String branchName;
    /**
     * 签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）
     */
    private String contractStatus;
    /**
     * 签约情况（具体的签约渠道或产品名称）
     */
    private String contractSituation;
    /**
     * 证件类型（字典: aicrm_identity_type）
     */
    private String identityType;
    /**
     * 证件号码（加密存储）
     */
    private String identityNo;
    /**
     * 关联账号
     */
    private String accountNo;
    /**
     * 生效日期
     */
    private LocalDate startDate;
    /**
     * 失效日期
     */
    private LocalDate endDate;
    /**
     * 签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）
     */
    private String signChannel;
    /**
     * 解约日期
     */
    private LocalDate cancelDate;
    /**
     * 解约原因
     */
    private String cancelReason;
    /**
     * 客户经理ID（关联 system_users.id）
     */
    private Long managerUserId;
    /**
     * 备注
     */
    private String remark;


}