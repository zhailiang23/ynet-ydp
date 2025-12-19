package com.ynet.iplatform.module.grid.controller.admin.communitycustomer;

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
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;

import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.*;

import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;
import com.ynet.iplatform.module.grid.service.communitycustomer.GridCommunityCustomerService;

@Tag(name = "管理后台 - 社区客户扩展")
@RestController
@RequestMapping("/grid/community-customer")
@Validated
public class GridCommunityCustomerController {

    @Resource
    private GridCommunityCustomerService communityCustomerService;

    @Resource
    private com.ynet.iplatform.module.grid.dal.mysql.communitycustomer.GridCommunityCustomerMapper communityCustomerMapper;

    @PostMapping("/create")
    @Operation(summary = "创建社区客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:community-customer:create')")
    public CommonResult<Long> createCommunityCustomer(@Valid @RequestBody GridCommunityCustomerSaveReqVO createReqVO) {
        return success(communityCustomerService.createCommunityCustomer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新社区客户扩展")
    @PreAuthorize("@ss.hasPermission('grid:community-customer:update')")
    public CommonResult<Boolean> updateCommunityCustomer(@Valid @RequestBody GridCommunityCustomerSaveReqVO updateReqVO) {
        communityCustomerService.updateCommunityCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除社区客户扩展")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:community-customer:delete')")
    public CommonResult<Boolean> deleteCommunityCustomer(@RequestParam("id") Long id) {
        communityCustomerService.deleteCommunityCustomer(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除社区客户扩展")
                @PreAuthorize("@ss.hasPermission('grid:community-customer:delete')")
    public CommonResult<Boolean> deleteCommunityCustomerList(@RequestParam("ids") List<Long> ids) {
        communityCustomerService.deleteCommunityCustomerListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得社区客户扩展")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:community-customer:query')")
    public CommonResult<GridCommunityCustomerRespVO> getCommunityCustomer(@RequestParam("id") Long id) {
        // 使用关联查询获取完整信息（包含客户姓名、手机号、地址、经纬度等）
        GridCommunityCustomerRespVO respVO = communityCustomerMapper.selectByIdWithRelations(id);
        if (respVO == null) {
            return success(null);
        }
        // 设置默认证件类型为"身份证"
        if (respVO.getIdNumber() != null && !respVO.getIdNumber().isEmpty()) {
            respVO.setIdType("身份证");
        }
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得社区客户扩展分页")
    @PreAuthorize("@ss.hasPermission('grid:community-customer:query')")
    public CommonResult<PageResult<GridCommunityCustomerRespVO>> getCommunityCustomerPage(@Valid GridCommunityCustomerPageReqVO pageReqVO) {
        // 创建 MyBatis Plus 分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<GridCommunityCustomerRespVO> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 使用 Mapper 的关联查询方法，获取完整的客户信息（包含客户姓名、手机号、机构、客户经理等）
        com.baomidou.mybatisplus.core.metadata.IPage<GridCommunityCustomerRespVO> result =
                communityCustomerMapper.selectPageWithRelations(mpPage, pageReqVO.getCustomerName());

        // 转换为 PageResult
        PageResult<GridCommunityCustomerRespVO> pageResult = new PageResult<>(result.getRecords(), result.getTotal());

        // 为每条记录设置默认证件类型为"身份证"（数据库中没有该字段）
        pageResult.getList().forEach(vo -> {
            if (vo.getIdNumber() != null && !vo.getIdNumber().isEmpty()) {
                vo.setIdType("身份证");
            }
        });
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出社区客户扩展 Excel")
    @PreAuthorize("@ss.hasPermission('grid:community-customer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCommunityCustomerExcel(@Valid GridCommunityCustomerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        // 创建一个超大分页对象来获取所有数据
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<GridCommunityCustomerRespVO> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(1, PageParam.PAGE_SIZE_NONE);

        // 使用关联查询获取完整信息
        com.baomidou.mybatisplus.core.metadata.IPage<GridCommunityCustomerRespVO> result =
                communityCustomerMapper.selectPageWithRelations(mpPage, pageReqVO.getCustomerName());
        List<GridCommunityCustomerRespVO> list = result.getRecords();

        // 设置默认证件类型
        list.forEach(vo -> {
            if (vo.getIdNumber() != null && !vo.getIdNumber().isEmpty()) {
                vo.setIdType("身份证");
            }
        });
        // 导出 Excel
        ExcelUtils.write(response, "社区客户扩展.xls", "数据", GridCommunityCustomerRespVO.class, list);
    }

}