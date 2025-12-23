package com.ynet.iplatform.module.grid.controller.admin.keyperson;

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

import com.ynet.iplatform.module.grid.controller.admin.keyperson.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.keyperson.KeyPersonDO;
import com.ynet.iplatform.module.grid.service.keyperson.KeyPersonService;

@Tag(name = "管理后台 - 关键人信息")
@RestController
@RequestMapping("/grid/key-person")
@Validated
public class KeyPersonController {

    @Resource
    private KeyPersonService keyPersonService;

    @PostMapping("/create")
    @Operation(summary = "创建关键人信息")
    @PreAuthorize("@ss.hasPermission('grid:key-person:create')")
    public CommonResult<Long> createKeyPerson(@Valid @RequestBody KeyPersonSaveReqVO createReqVO) {
        return success(keyPersonService.createKeyPerson(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新关键人信息")
    @PreAuthorize("@ss.hasPermission('grid:key-person:update')")
    public CommonResult<Boolean> updateKeyPerson(@Valid @RequestBody KeyPersonSaveReqVO updateReqVO) {
        keyPersonService.updateKeyPerson(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除关键人信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('grid:key-person:delete')")
    public CommonResult<Boolean> deleteKeyPerson(@RequestParam("id") Long id) {
        keyPersonService.deleteKeyPerson(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除关键人信息")
                @PreAuthorize("@ss.hasPermission('grid:key-person:delete')")
    public CommonResult<Boolean> deleteKeyPersonList(@RequestParam("ids") List<Long> ids) {
        keyPersonService.deleteKeyPersonListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得关键人信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('grid:key-person:query')")
    public CommonResult<KeyPersonRespVO> getKeyPerson(@RequestParam("id") Long id) {
        KeyPersonDO keyPerson = keyPersonService.getKeyPerson(id);
        return success(BeanUtils.toBean(keyPerson, KeyPersonRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得关键人信息分页")
    @PreAuthorize("@ss.hasPermission('grid:key-person:query')")
    public CommonResult<PageResult<KeyPersonRespVO>> getKeyPersonPage(@Valid KeyPersonPageReqVO pageReqVO) {
        PageResult<KeyPersonDO> pageResult = keyPersonService.getKeyPersonPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KeyPersonRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出关键人信息 Excel")
    @PreAuthorize("@ss.hasPermission('grid:key-person:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportKeyPersonExcel(@Valid KeyPersonPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<KeyPersonDO> list = keyPersonService.getKeyPersonPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "关键人信息.xls", "数据", KeyPersonRespVO.class,
                        BeanUtils.toBean(list, KeyPersonRespVO.class));
    }

}