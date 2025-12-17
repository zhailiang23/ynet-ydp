package com.ynet.iplatform.module.grid.controller.admin.info;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;

import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.*;

import com.ynet.iplatform.module.grid.controller.admin.info.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.service.info.GridInfoService;

@Tag(name = "管理后台 - 网格信息")
@RestController
@RequestMapping("/grid/info")
@Validated
public class GridInfoController {

    @Resource
    private GridInfoService infoService;

    @PostMapping("/create")
    @Operation(summary = "创建网格信息")
    @PreAuthorize("@ss.hasPermission('grid:info:create')")
    public CommonResult<Long> createInfo(@Valid @RequestBody GridInfoSaveReqVO createReqVO) {
        return success(infoService.createInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格信息")
    @PreAuthorize("@ss.hasPermission('grid:info:update')")
    public CommonResult<Boolean> updateInfo(@Valid @RequestBody GridInfoSaveReqVO updateReqVO) {
        infoService.updateInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:info:delete')")
    public CommonResult<Boolean> deleteInfo(@RequestParam("id") Long id) {
        infoService.deleteInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除网格信息")
                @PreAuthorize("@ss.hasPermission('grid:info:delete')")
    public CommonResult<Boolean> deleteInfoList(@RequestParam("ids") List<Long> ids) {
        infoService.deleteInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:info:query')")
    public CommonResult<GridInfoRespVO> getInfo(@RequestParam("id") Long id) {
        GridInfoDO info = infoService.getInfo(id);
        return success(BeanUtils.toBean(info, GridInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格信息分页")
    @PreAuthorize("@ss.hasPermission('grid:info:query')")
    public CommonResult<PageResult<GridInfoRespVO>> getInfoPage(@Valid GridInfoPageReqVO pageReqVO) {
        PageResult<GridInfoDO> pageResult = infoService.getInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格信息 Excel")
    @PreAuthorize("@ss.hasPermission('grid:info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInfoExcel(@Valid GridInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridInfoDO> list = infoService.getInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格信息.xls", "数据", GridInfoRespVO.class,
                        BeanUtils.toBean(list, GridInfoRespVO.class));
    }

}