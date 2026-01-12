package com.ynet.iplatform.module.grid.controller.admin.communitygrid.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;

@Schema(description = "管理后台 - 社区网格 Response VO")
@Data
public class CommunityGridRespVO implements TransPojo {

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "网格编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String gridCode;

    @Schema(description = "网格名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String gridName;

    @Schema(description = "网格类型")
    private String gridType;

    @Schema(description = "所属机构ID")
    private Long orgId;

    @Schema(description = "所属机构名称")
    @Trans(type = TransType.SIMPLE, target = Dept.class, fields = "name", ref = "orgName")
    private String orgName;

    @Schema(description = "责任人用户ID")
    private Long managerUserId;

    @Schema(description = "责任人姓名")
    @Trans(type = TransType.SIMPLE, target = User.class, fields = "nickname", ref = "managerUserName")
    private String managerUserName;

    @Schema(description = "团队人数")
    private Integer teamCount;

    @Schema(description = "网格状态")
    private String status;

    @Schema(description = "网格边界(GeoJSON格式)")
    private String boundaryGeometry;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    /**
     * 部门 TransPojo
     */
    @Schema(description = "部门 TransPojo", hidden = true)
    public static class Dept implements TransPojo {
        private Long id;
        private String name;
    }

    /**
     * 用户 TransPojo
     */
    @Schema(description = "用户 TransPojo", hidden = true)
    public static class User implements TransPojo {
        private Long id;
        private String nickname;
    }

}
