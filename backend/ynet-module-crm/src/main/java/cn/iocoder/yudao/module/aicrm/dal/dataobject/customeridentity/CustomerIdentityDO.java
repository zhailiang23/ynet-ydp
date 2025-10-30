package cn.iocoder.yudao.module.aicrm.dal.dataobject.customeridentity;

import lombok.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户证件信息表（零售客户特有，1对多关系，支持多证件） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_identity")
@KeySequence("crm_customer_identity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerIdentityDO extends BaseDO {

    /**
     * 证件信息主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 证件类型
     */
    private String identityType;
    /**
     * 证件号码
     */
    private String identityNo;
    /**
     * 证件上的姓名
     */
    private String identityName;
    /**
     * 发证机关
     */
    private String issueAuthority;
    /**
     * 发证日期
     */
    private LocalDate issueDate;
    /**
     * 有效期至
     */
    private LocalDate expiryDate;
    /**
     * 是否主证件
     */
    private Boolean isPrimary;
    /**
     * 证件地址
     */
    private String identityAddress;
    /**
     * 证件正面照片URL
     */
    private String identityFrontUrl;
    /**
     * 证件反面照片URL
     */
    private String identityBackUrl;
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
     * 备注
     */
    private String remark;


}