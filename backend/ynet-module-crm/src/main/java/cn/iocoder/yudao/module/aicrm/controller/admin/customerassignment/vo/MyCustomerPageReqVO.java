package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 我的客户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MyCustomerPageReqVO extends PageParam {

    @Schema(description = "客户编号", example = "C20001")
    private String customerNo;

    @Schema(description = "客户名称", example = "张三")
    private String customerName;

    @Schema(description = "客户类型", example = "1")
    private Integer customerType;

    @Schema(description = "归属类型(1-主办 2-协办)", example = "1")
    private Integer assignmentType;

    @Schema(description = "客户状态", example = "1")
    private Integer customerStatus;

}
