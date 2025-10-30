package cn.iocoder.yudao.module.aicrm.controller.admin.customercontract.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 客户签约信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerContractSaveReqVO {

    @Schema(description = "签约主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15858")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20186")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "签约类型ID（关联 crm_contract_type.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "517")
    @NotNull(message = "签约类型ID（关联 crm_contract_type.id）不能为空")
    private Long contractTypeId;

    @Schema(description = "签约账号/签约编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "签约账号/签约编号不能为空")
    private String contractNo;

    @Schema(description = "签约日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "签约日期不能为空")
    private LocalDate contractDate;

    @Schema(description = "签约机构ID（关联 system_dept.id）", example = "28110")
    private Long branchId;

    @Schema(description = "签约机构名称", example = "赵六")
    private String branchName;

    @Schema(description = "签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）不能为空")
    private String contractStatus;

    @Schema(description = "签约情况（具体的签约渠道或产品名称）")
    private String contractSituation;

    @Schema(description = "证件类型（字典: aicrm_identity_type）", example = "2")
    private String identityType;

    @Schema(description = "证件号码（加密存储）")
    private String identityNo;

    @Schema(description = "关联账号")
    private String accountNo;

    @Schema(description = "生效日期")
    private LocalDate startDate;

    @Schema(description = "失效日期")
    private LocalDate endDate;

    @Schema(description = "签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）")
    private String signChannel;

    @Schema(description = "解约日期")
    private LocalDate cancelDate;

    @Schema(description = "解约原因", example = "不香")
    private String cancelReason;

    @Schema(description = "客户经理ID（关联 system_users.id）", example = "20408")
    private Long managerUserId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}