package cn.iocoder.yudao.module.aicrm.dal.dataobject.companyaddress;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * CRM对公客户地址信息 DO
 *
 * @author 芋道源码
 */
@TableName("crm_company_address")
@KeySequence("crm_company_address_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAddressDO extends BaseDO {

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
     * 地址类型(公司地址、家庭地址、其他地址)
     */
    private String addressType;
    /**
     * 是否首选项(否、是)
     */
    private Boolean isPrimary;
    /**
     * 详细地址
     */
    private String addressDetail;
    /**
     * 邮编
     */
    private String postalCode;
    /**
     * 来源系统(ECIF、CRM、零售CRM等)
     */
    private String sourceSystem;
    /**
     * 国家或地区
     */
    private String countryOrRegion;
    /**
     * 省份代码
     */
    private String provinceCode;
    /**
     * 城市代码
     */
    private String cityCode;
    /**
     * 区县代码
     */
    private String countyCode;
    /**
     * 乡镇代码
     */
    private String townCode;
    /**
     * 乡镇名称
     */
    private String townName;
    /**
     * 街道名称
     */
    private String streetName;
    /**
     * 村组编号
     */
    private String villageNo;
    /**
     * 村组名称
     */
    private String villageName;
    /**
     * 地区代码
     */
    private String areaCode;
    /**
     * 行政区域
     */
    private String adminZone;
    /**
     * 英文地址
     */
    private String enAddress;
    /**
     * 联系方式类型
     */
    private String contactMethod;
    /**
     * 地址级别
     */
    private String addressLevel;
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
     * 老系统地址ID
     */
    private String oldAddressId;


}