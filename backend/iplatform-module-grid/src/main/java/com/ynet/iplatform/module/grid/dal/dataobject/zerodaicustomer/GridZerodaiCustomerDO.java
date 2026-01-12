package com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 零贷客户 DO
 *
 * @author 易诚源码
 */
@TableName("grid_zerodai_customer")
@KeySequence("grid_zerodai_customer_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridZerodaiCustomerDO extends BaseDO {

    /**
     * 扩展ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID (关联 grid_customer)
     */
    private Long customerId;
    /**
     * 负责人姓名
     */
    private String principalName;
    /**
     * 性别（男/女）
     */
    private String gender;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 经营情况
     */
    private String businessSituation;
    /**
     * 当前融资情况
     */
    private String financingSituation;
    /**
     * 对我行信贷等产品需求
     */
    private String creditDemand;
    /**
     * 客户号（信贷系统客户号）
     */
    private String customerNo;
    /**
     * 客户分类（潜力客户/月标客户/存量客户）
     */
    private String customerClassification;
    /**
     * 授信金额（万元）
     */
    private BigDecimal creditAmount;
    /**
     * 在贷金额（万元）
     */
    private BigDecimal loanAmount;
    /**
     * 状态（营销中/二次营销/待分支行审批/待总行审批/已审批/被拒）
     */
    private String status;
    /**
     * 上传照片（JSON数组）
     */
    private String photos;
    /**
     * 员工号
     */
    private String employeeNo;
    /**
     * 员工姓名
     */
    private String employeeName;
    /**
     * 是否进件（保留原有字段用于后续逻辑）
     */
    private Boolean isApplied;
    /**
     * 是否完件（保留原有字段用于后续逻辑）
     */
    private Boolean isCompleted;

}
