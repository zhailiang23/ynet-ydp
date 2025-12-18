package com.ynet.iplatform.module.grid.controller.admin.community;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunityPageReqVO;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunityRespVO;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunitySaveReqVO;
import com.ynet.iplatform.module.grid.service.community.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 社区信息")
@RestController
@RequestMapping("/grid/community")
@Validated
public class CommunityController {

    @Resource
    private CommunityService communityService;

    @PostMapping("/create")
    @Operation(summary = "创建社区信息")
    @PreAuthorize("@ss.hasPermission('grid:community:create')")
    public CommonResult<Long> createCommunity(@Valid @RequestBody CommunitySaveReqVO createReqVO) {
        return success(communityService.createCommunity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新社区信息")
    @PreAuthorize("@ss.hasPermission('grid:community:update')")
    public CommonResult<Boolean> updateCommunity(@Valid @RequestBody CommunitySaveReqVO updateReqVO) {
        communityService.updateCommunity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除社区信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:community:delete')")
    public CommonResult<Boolean> deleteCommunity(@RequestParam("id") Long id) {
        communityService.deleteCommunity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得社区信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:community:query')")
    public CommonResult<CommunityRespVO> getCommunity(@RequestParam("id") Long id) {
        CommunityRespVO community = communityService.getCommunity(id);
        return success(community);
    }

    @GetMapping("/page")
    @Operation(summary = "获得社区信息分页")
    @PreAuthorize("@ss.hasPermission('grid:community:query')")
    public CommonResult<PageResult<CommunityRespVO>> getCommunityPage(@Valid CommunityPageReqVO pageReqVO) {
        PageResult<CommunityRespVO> pageResult = communityService.getCommunityPage(pageReqVO);
        return success(pageResult);
    }

}
