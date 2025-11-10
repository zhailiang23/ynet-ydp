package cn.iocoder.yudao.module.aicrm.dal.dataobject.practicevirtualcustomer;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM智能陪练-虚拟客户 DO
 *
 * @author 芋道源码
 */
@TableName("crm_practice_virtual_customer")
@KeySequence("crm_practice_virtual_customer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeVirtualCustomerDO extends BaseDO {

    /**
     * 虚拟客户ID
     */
    @TableId
    private Long id;
    /**
     * 客户姓名
     */
    private String name;
    /**
     * 性别：字典 aicrm_gender
     */
    private String gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 性格类型：字典 aicrm_personality_type（如理性型/感性型）
     */
    private String personalityType;
    /**
     * 风险偏好
     */
    private String riskPreference;
    /**
     * 自定义参数（JSON格式）
     */
    private String memo;


}