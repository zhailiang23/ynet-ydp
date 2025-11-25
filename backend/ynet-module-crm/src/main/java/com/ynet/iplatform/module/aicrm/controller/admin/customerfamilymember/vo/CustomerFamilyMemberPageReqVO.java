package com.ynet.iplatform.module.aicrm.controller.admin.customerfamilymember.vo;

import lombok.*;
import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户家庭成员信息表（零售客户）分页 Request VO")
@Data
public class CustomerFamilyMemberPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联家庭信息表）", example = "29790")
    private Long customerId;

    @Schema(description = "成员姓名", example = "张三")
    private String memberName;

    @Schema(description = "家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）", example = "2")
    private String relationType;

    @Schema(description = "性别（1-男，2-女）")
    private Integer gender;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）", example = "1")
    private String identityType;

    @Schema(description = "证件号码（加密存储）")
    private String identityNo;

    @Schema(description = "学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）")
    private String educationLevel;

    @Schema(description = "工作单位")
    private String company;

    @Schema(description = "职务")
    private String position;

    @Schema(description = "家庭地址")
    private String address;

    @Schema(description = "联系方式（手机号）")
    private String mobile;

    @Schema(description = "固定电话")
    private String tel;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "是否主要成员（0-否，1-是）")
    private Boolean isMainMember;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "成员ID（老系统）", example = "20997")
    private String memberId;

    @Schema(description = "客户经理ID（老系统）", example = "10511")
    private String managerId;

    @Schema(description = "老系统客户ID", example = "20989")
    private String oldCustId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
