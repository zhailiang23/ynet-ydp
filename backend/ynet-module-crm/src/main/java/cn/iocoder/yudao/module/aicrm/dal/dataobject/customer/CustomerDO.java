package cn.iocoder.yudao.module.aicrm.dal.dataobject.customer;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM客户主表(零售+对公共用) DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer")
@KeySequence("crm_customer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDO extends BaseDO {

    /**
     * 客户ID(主键)
     */
    @TableId
    private Long id;
    /**
     * 客户编号(唯一标识)
     */
    private String customerNo;
    /**
     * 客户类型(1=零售客户, 2=对公客户)
     */
    private Integer customerType;
    /**
     * 客户名称(零售为姓名,对公为企业名称)
     */
    private String customerName;
    /**
     * 客户等级(零售:普通/VIP/金卡/钻石卡等, 对公:普通/重点/战略客户等)
     */
    private String customerLevel;
    /**
     * 客户状态(1=正常, 2=冻结, 3=注销)
     */
    private Integer customerStatus;
    /**
     * 是否优质客户(0=否, 1=是)
     */
    private Boolean isHighQuality;
    /**
     * 是否重要客户(0=否, 1=是)
     */
    private Boolean isImportant;
    /**
     * 信用状态(如:良好、一般、较差)
     */
    private String creditStatus;
    /**
     * 信用等级(如:AAA、AA、A、BBB等)
     */
    private String creditLevel;
    /**
     * 信用评分
     */
    private BigDecimal creditScore;
    /**
     * 客户来源(如:网点开发、电话营销、网络营销等)
     */
    private String customerSource;
    /**
     * 客户标签(多个标签用逗号分隔)
     */
    private String customerTag;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 所属部门ID(数据权限)
     */
    private Long deptId;


}
