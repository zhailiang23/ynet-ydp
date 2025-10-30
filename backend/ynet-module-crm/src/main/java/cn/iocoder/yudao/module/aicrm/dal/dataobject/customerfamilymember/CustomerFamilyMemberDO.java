package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerfamilymember;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户家庭成员信息表（零售客户） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_family_member")
@KeySequence("crm_customer_family_member_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFamilyMemberDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联家庭信息表）
     */
    private Long customerId;
    /**
     * 成员姓名
     */
    private String memberName;
    /**
     * 家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）
     */
    private String relationType;
    /**
     * 性别（1-男，2-女）
     */
    private Integer gender;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）
     */
    private String identityType;
    /**
     * 证件号码（加密存储）
     */
    private String identityNo;
    /**
     * 学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）
     */
    private String educationLevel;
    /**
     * 工作单位
     */
    private String company;
    /**
     * 职务
     */
    private String position;
    /**
     * 家庭地址
     */
    private String address;
    /**
     * 联系方式（手机号）
     */
    private String mobile;
    /**
     * 固定电话
     */
    private String tel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 是否主要成员（0-否，1-是）
     */
    private Boolean isMainMember;
    /**
     * 备注
     */
    private String remark;
    /**
     * 成员ID（老系统）
     */
    private String memberId;
    /**
     * 客户经理ID（老系统）
     */
    private String managerId;
    /**
     * 老系统客户ID
     */
    private String oldCustId;


}
