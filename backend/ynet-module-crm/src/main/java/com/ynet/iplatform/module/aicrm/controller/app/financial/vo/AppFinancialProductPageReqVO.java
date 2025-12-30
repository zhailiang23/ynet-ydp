package com.ynet.iplatform.module.aicrm.controller.app.financial.vo;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Schema(description = "移动端 - 金融产品分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppFinancialProductPageReqVO extends PageParam {

    @Schema(description = "产品分类（理财、基金、保险、债券、贵金属）", example = "理财")
    private String category;

    @Schema(description = "标签列表", example = "[\"高收益\", \"低风险\"]")
    private List<String> tags;

    @Schema(description = "风险等级", example = "R2")
    private String riskLevel;

    @Schema(description = "产品状态（0=停售 1=在售）", example = "1")
    private Integer status;

    @Schema(description = "是否热销", example = "1")
    private Integer isHot;

    @Schema(description = "是否新品", example = "0")
    private Integer isNew;

    @Schema(description = "搜索关键词（产品名称或代码）", example = "富盈")
    private String keyword;

    @Schema(description = "排序方式（hot=热度 return=收益 duration=期限 investment=起购金额）", example = "hot")
    private String sortBy;

}
