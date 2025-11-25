package com.ynet.iplatform.module.aicrm.controller.admin.customerfamilymember;

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

import com.ynet.iplatform.module.aicrm.controller.admin.customerfamilymember.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerfamilymember.CustomerFamilyMemberDO;
import com.ynet.iplatform.module.aicrm.service.customerfamilymember.CustomerFamilyMemberService;

@Tag(name = "管理后台 - 客户家庭成员信息表（零售客户）")
@RestController
@RequestMapping("/aicrm/customer-family-member")
@Validated
public class CustomerFamilyMemberController {

    @Resource
    private CustomerFamilyMemberService customerFamilyMemberService;

    @PostMapping("/create")
    @Operation(summary = "创建客户家庭成员信息表（零售客户）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family-member:create')")
    public CommonResult<Long> createCustomerFamilyMember(@Valid @RequestBody CustomerFamilyMemberSaveReqVO createReqVO) {
        return success(customerFamilyMemberService.createCustomerFamilyMember(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户家庭成员信息表（零售客户）")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family-member:update')")
    public CommonResult<Boolean> updateCustomerFamilyMember(@Valid @RequestBody CustomerFamilyMemberSaveReqVO updateReqVO) {
        customerFamilyMemberService.updateCustomerFamilyMember(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户家庭成员信息表（零售客户）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family-member:delete')")
    public CommonResult<Boolean> deleteCustomerFamilyMember(@RequestParam("id") Long id) {
        customerFamilyMemberService.deleteCustomerFamilyMember(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除客户家庭成员信息表（零售客户）")
                @PreAuthorize("@ss.hasPermission('aicrm:customer-family-member:delete')")
    public CommonResult<Boolean> deleteCustomerFamilyMemberList(@RequestParam("ids") List<Long> ids) {
        customerFamilyMemberService.deleteCustomerFamilyMemberListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户家庭成员信息表（零售客户）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family-member:query')")
    public CommonResult<CustomerFamilyMemberRespVO> getCustomerFamilyMember(@RequestParam("id") Long id) {
        CustomerFamilyMemberDO customerFamilyMember = customerFamilyMemberService.getCustomerFamilyMember(id);
        return success(BeanUtils.toBean(customerFamilyMember, CustomerFamilyMemberRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户家庭成员信息表（零售客户）分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family-member:query')")
    public CommonResult<PageResult<CustomerFamilyMemberRespVO>> getCustomerFamilyMemberPage(@Valid CustomerFamilyMemberPageReqVO pageReqVO) {
        PageResult<CustomerFamilyMemberDO> pageResult = customerFamilyMemberService.getCustomerFamilyMemberPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CustomerFamilyMemberRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客户家庭成员信息表（零售客户） Excel")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-family-member:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCustomerFamilyMemberExcel(@Valid CustomerFamilyMemberPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CustomerFamilyMemberDO> list = customerFamilyMemberService.getCustomerFamilyMemberPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "客户家庭成员信息表（零售客户）.xls", "数据", CustomerFamilyMemberRespVO.class,
                        BeanUtils.toBean(list, CustomerFamilyMemberRespVO.class));
    }

}