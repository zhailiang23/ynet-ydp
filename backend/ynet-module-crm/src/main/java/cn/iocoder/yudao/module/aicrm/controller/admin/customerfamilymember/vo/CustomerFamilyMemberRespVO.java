package cn.iocoder.yudao.module.aicrm.controller.admin.customerfamilymember.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户家庭成员信息表（零售客户） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerFamilyMemberRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10330")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID（关联家庭信息表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29790")
    @ExcelProperty("客户ID（关联家庭信息表）")
    private Long customerId;

    @Schema(description = "成员姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("成员姓名")
    private String memberName;

    @Schema(description = "家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）")
    private String relationType;

    @Schema(description = "性别（1-男，2-女）")
    @ExcelProperty("性别（1-男，2-女）")
    private Integer gender;

    @Schema(description = "生日")
    @ExcelProperty("生日")
    private LocalDate birthday;

    @Schema(description = "年龄")
    @ExcelProperty("年龄")
    private Integer age;

    @Schema(description = "证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）", example = "1")
    @ExcelProperty("证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）")
    private String identityType;

    @Schema(description = "证件号码（加密存储）")
    @ExcelProperty("证件号码（加密存储）")
    private String identityNo;

    @Schema(description = "学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）")
    @ExcelProperty("学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）")
    private String educationLevel;

    @Schema(description = "工作单位")
    @ExcelProperty("工作单位")
    private String company;

    @Schema(description = "职务")
    @ExcelProperty("职务")
    private String position;

    @Schema(description = "家庭地址")
    @ExcelProperty("家庭地址")
    private String address;

    @Schema(description = "联系方式（手机号）")
    @ExcelProperty("联系方式（手机号）")
    private String mobile;

    @Schema(description = "固定电话")
    @ExcelProperty("固定电话")
    private String tel;

    @Schema(description = "邮箱")
    @ExcelProperty("邮箱")
    private String email;

    @Schema(description = "是否主要成员（0-否，1-是）")
    @ExcelProperty("是否主要成员（0-否，1-是）")
    private Boolean isMainMember;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "成员ID（老系统）", example = "20997")
    @ExcelProperty("成员ID（老系统）")
    private String memberId;

    @Schema(description = "客户经理ID（老系统）", example = "10511")
    @ExcelProperty("客户经理ID（老系统）")
    private String managerId;

    @Schema(description = "老系统客户ID", example = "20989")
    @ExcelProperty("老系统客户ID")
    private String oldCustId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}