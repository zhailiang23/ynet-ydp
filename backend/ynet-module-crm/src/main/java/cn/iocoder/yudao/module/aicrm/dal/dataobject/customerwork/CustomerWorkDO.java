package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerwork;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户工作或经营信息表 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_work")
@KeySequence("crm_customer_work_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWorkDO extends BaseDO {

    /**
     * 工作信息主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 工作类型
     */
    private String workType;
    /**
     * 工作单位名称/经营主体名称
     */
    private String employerName;
    /**
     * 单位性质
     */
    private String employerType;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 职位/职务
     */
    private String position;
    /**
     * 工作年限/经营年限（单位:年）
     */
    private Integer workYears;
    /**
     * 入职日期/开始经营日期
     */
    private LocalDate startDate;
    /**
     * 离职日期/结束经营日期（NULL表示在职）
     */
    private LocalDate endDate;
    /**
     * 是否当前工作
     */
    private Boolean isCurrent;
    /**
     * 工作地址-省
     */
    private String workAddressProvince;
    /**
     * 工作地址-市
     */
    private String workAddressCity;
    /**
     * 工作地址-区
     */
    private String workAddressDistrict;
    /**
     * 工作地址-详细地址
     */
    private String workAddressDetail;
    /**
     * 年收入（单位:元）
     */
    private BigDecimal annualIncome;
    /**
     * 月收入（单位:元）
     */
    private BigDecimal monthlyIncome;
    /**
     * 收入来源说明
     */
    private String incomeSource;
    /**
     * 经营规模
     */
    private String businessScale;
    /**
     * 经营状态
     */
    private String businessStatus;
    /**
     * 生产能力
     */
    private String productionCapacity;
    /**
     * 营业执照号/经营许可证号
     */
    private String businessLicenseNo;
    /**
     * 工作电话/单位电话
     */
    private String workPhone;
    /**
     * 单位联系人
     */
    private String contactPerson;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 核验状态
     */
    private Integer verificationStatus;
    /**
     * 核验时间
     */
    private LocalDateTime verificationTime;
    /**
     * 核验备注
     */
    private String verificationRemark;
    /**
     * 附件URL列表（JSON格式，如营业执照、工作证明等）
     */
    private String attachmentUrls;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 扩展数据
     */
    private String extraData;


}
