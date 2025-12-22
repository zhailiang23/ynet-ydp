package com.ynet.iplatform.module.grid.service.zerodaicustomer;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo.*;
import com.ynet.iplatform.module.grid.controller.admin.customer.vo.GridCustomerSaveReqVO;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaicustomer.GridZerodaiCustomerDO;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.module.grid.service.customer.GridCustomerService;
import com.ynet.iplatform.module.system.service.user.AdminUserService;
import com.ynet.iplatform.module.system.dal.dataobject.user.AdminUserDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;

import com.ynet.iplatform.module.grid.dal.mysql.zerodaicustomer.GridZerodaiCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 零贷客户扩展 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridZerodaiCustomerServiceImpl implements GridZerodaiCustomerService {

    @Resource
    private GridZerodaiCustomerMapper zerodaiCustomerMapper;

    @Resource
    private GridCustomerService customerService;

    @Resource
    private AdminUserService adminUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createZerodaiCustomer(GridZerodaiCustomerSaveReqVO createReqVO) {
        // 1. 获取当前登录用户信息
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserDO currentUser = adminUserService.getUser(currentUserId);

        // 2. 先创建 grid_customer 记录
        GridCustomerSaveReqVO customerReqVO = new GridCustomerSaveReqVO();
        customerReqVO.setCustomerType("ZERODAI"); // 零贷客户类型
        customerReqVO.setCustomerName(createReqVO.getCustomerName());
        customerReqVO.setPhone(createReqVO.getPhone());
        customerReqVO.setAddress(createReqVO.getAddress());
        customerReqVO.setLongitude(createReqVO.getLongitude());
        customerReqVO.setLatitude(createReqVO.getLatitude());
        customerReqVO.setManagerId(createReqVO.getManagerId());
        customerReqVO.setGridId(createReqVO.getGridId() != null ? createReqVO.getGridId() : 1L); // 网格ID，默认为1
        customerReqVO.setSource("MANUAL"); // 手动录入
        customerReqVO.setStatus("NORMAL"); // 正常状态

        Long customerId = customerService.createCustomer(customerReqVO);

        // 3. 设置 Mock 数据和员工信息
        createReqVO.setEmployeeNo(currentUser != null ? currentUser.getUsername() : "");
        createReqVO.setEmployeeName(currentUser != null ? currentUser.getNickname() : "");

        // Mock 数据：客户号、授信金额、在贷金额
        if (createReqVO.getCustomerNo() == null || createReqVO.getCustomerNo().isEmpty()) {
            createReqVO.setCustomerNo("CUS" + LocalDate.now().toString().replace("-", "") + String.format("%04d", customerId % 10000));
        }
        if (createReqVO.getCreditAmount() == null) {
            createReqVO.setCreditAmount(new BigDecimal("50.00")); // 默认授信金额 50 万元
        }
        if (createReqVO.getLoanAmount() == null) {
            createReqVO.setLoanAmount(new BigDecimal("30.00")); // 默认在贷金额 30 万元
        }

        // 4. 再创建 grid_zerodai_customer 记录
        GridZerodaiCustomerDO zerodaiCustomer = BeanUtils.toBean(createReqVO, GridZerodaiCustomerDO.class);
        zerodaiCustomer.setCustomerId(customerId);
        zerodaiCustomerMapper.insert(zerodaiCustomer);

        // 返回零贷客户扩展ID
        return zerodaiCustomer.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZerodaiCustomer(GridZerodaiCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateZerodaiCustomerExists(updateReqVO.getId());

        // 获取零贷客户扩展信息，找到关联的 customerId
        GridZerodaiCustomerDO zerodaiCustomer = zerodaiCustomerMapper.selectById(updateReqVO.getId());
        Long customerId = zerodaiCustomer.getCustomerId();

        // 获取原有的客户信息
        GridCustomerDO existingCustomer = customerService.getCustomer(customerId);

        // 1. 更新 grid_customer 记录
        GridCustomerSaveReqVO customerReqVO = new GridCustomerSaveReqVO();
        customerReqVO.setId(customerId);
        customerReqVO.setCustomerType("ZERODAI"); // 零贷客户类型
        customerReqVO.setCustomerName(updateReqVO.getCustomerName());
        customerReqVO.setPhone(updateReqVO.getPhone());
        customerReqVO.setAddress(updateReqVO.getAddress());
        customerReqVO.setLongitude(updateReqVO.getLongitude());
        customerReqVO.setLatitude(updateReqVO.getLatitude());
        customerReqVO.setManagerId(updateReqVO.getManagerId());
        // 网格ID：如果前端传递了就使用新值，否则保留原值
        customerReqVO.setGridId(updateReqVO.getGridId() != null ? updateReqVO.getGridId() : existingCustomer.getGridId());
        customerReqVO.setSource("MANUAL"); // 手动录入
        customerReqVO.setStatus("NORMAL"); // 正常状态

        customerService.updateCustomer(customerReqVO);

        // 2. 更新 grid_zerodai_customer 记录
        GridZerodaiCustomerDO updateObj = BeanUtils.toBean(updateReqVO, GridZerodaiCustomerDO.class);
        zerodaiCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteZerodaiCustomer(Long id) {
        // 校验存在
        validateZerodaiCustomerExists(id);
        // 删除
        zerodaiCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteZerodaiCustomerListByIds(List<Long> ids) {
        // 删除
        zerodaiCustomerMapper.deleteByIds(ids);
        }


    private void validateZerodaiCustomerExists(Long id) {
        if (zerodaiCustomerMapper.selectById(id) == null) {
            throw exception(ZERODAI_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public GridZerodaiCustomerDO getZerodaiCustomer(Long id) {
        return zerodaiCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<GridZerodaiCustomerDO> getZerodaiCustomerPage(GridZerodaiCustomerPageReqVO pageReqVO) {
        // 注意：此方法已不再使用，Controller 层直接调用 Mapper 的 selectPageWithRelations 方法
        // 返回空结果以保持接口兼容性
        return new PageResult<>(new ArrayList<>(), 0L);
    }

}