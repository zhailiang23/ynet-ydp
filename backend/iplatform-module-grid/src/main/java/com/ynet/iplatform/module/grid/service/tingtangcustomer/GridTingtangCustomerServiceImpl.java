package com.ynet.iplatform.module.grid.service.tingtangcustomer;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.tingtangcustomer.vo.*;
import com.ynet.iplatform.module.grid.controller.admin.customer.vo.GridCustomerSaveReqVO;
import com.ynet.iplatform.module.grid.dal.dataobject.tingtangcustomer.GridTingtangCustomerDO;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.module.grid.service.customer.GridCustomerService;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.tingtangcustomer.GridTingtangCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 厅堂客户 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridTingtangCustomerServiceImpl implements GridTingtangCustomerService {

    @Resource
    private GridTingtangCustomerMapper tingtangCustomerMapper;

    @Resource
    private GridCustomerService customerService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTingtangCustomer(GridTingtangCustomerSaveReqVO createReqVO) {
        // 1. 先创建 grid_customer 记录
        GridCustomerSaveReqVO customerReqVO = new GridCustomerSaveReqVO();
        customerReqVO.setCustomerType("TINGTANG"); // 厅堂客户类型
        customerReqVO.setCustomerName(createReqVO.getCustomerName());
        customerReqVO.setGender(createReqVO.getGender());
        customerReqVO.setCustomerGroup(createReqVO.getCustomerGroup());
        customerReqVO.setIdCard(createReqVO.getIdCard());
        customerReqVO.setPhone(createReqVO.getPhone());
        customerReqVO.setAddress(createReqVO.getAddress());
        customerReqVO.setLongitude(createReqVO.getLongitude().doubleValue());
        customerReqVO.setLatitude(createReqVO.getLatitude().doubleValue());
        customerReqVO.setGridId(1L); // 默认网格ID
        customerReqVO.setSource("MANUAL"); // 手动录入
        customerReqVO.setStatus("NORMAL"); // 正常状态

        Long customerId = customerService.createCustomer(customerReqVO);

        // 2. 再创建 grid_tingtang_customer 记录
        GridTingtangCustomerDO tingtangCustomer = new GridTingtangCustomerDO();
        tingtangCustomer.setCustomerId(customerId);
        tingtangCustomer.setCustomerGroup("GENERAL"); // 默认客户分群：普通客户
        tingtangCustomer.setIsArchived(createReqVO.getIsArchived());
        tingtangCustomer.setCrmCustomerId(createReqVO.getCrmCustomerId());
        tingtangCustomerMapper.insert(tingtangCustomer);

        // 返回厅堂客户扩展ID
        return tingtangCustomer.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTingtangCustomer(GridTingtangCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateTingtangCustomerExists(updateReqVO.getId());

        // 获取厅堂客户扩展信息，找到关联的 customerId
        GridTingtangCustomerDO tingtangCustomer = tingtangCustomerMapper.selectById(updateReqVO.getId());
        Long customerId = tingtangCustomer.getCustomerId();

        // 获取原有的客户信息
        GridCustomerDO existingCustomer = customerService.getCustomer(customerId);

        // 1. 更新 grid_customer 记录
        GridCustomerSaveReqVO customerReqVO = new GridCustomerSaveReqVO();
        customerReqVO.setId(customerId);
        customerReqVO.setCustomerType("TINGTANG"); // 厅堂客户类型
        customerReqVO.setCustomerName(updateReqVO.getCustomerName());
        customerReqVO.setGender(updateReqVO.getGender());
        customerReqVO.setCustomerGroup(updateReqVO.getCustomerGroup());
        customerReqVO.setIdCard(updateReqVO.getIdCard());
        customerReqVO.setPhone(updateReqVO.getPhone());
        customerReqVO.setAddress(updateReqVO.getAddress());
        customerReqVO.setLongitude(updateReqVO.getLongitude().doubleValue());
        customerReqVO.setLatitude(updateReqVO.getLatitude().doubleValue());
        customerReqVO.setGridId(existingCustomer.getGridId()); // 保留原网格ID
        customerReqVO.setSource("MANUAL"); // 手动录入
        customerReqVO.setStatus("NORMAL"); // 正常状态

        customerService.updateCustomer(customerReqVO);

        // 2. 更新 grid_tingtang_customer 记录
        GridTingtangCustomerDO updateObj = new GridTingtangCustomerDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setIsArchived(updateReqVO.getIsArchived());
        updateObj.setCrmCustomerId(updateReqVO.getCrmCustomerId());
        tingtangCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteTingtangCustomer(Long id) {
        // 校验存在
        validateTingtangCustomerExists(id);
        // 删除
        tingtangCustomerMapper.deleteById(id);
    }

    @Override
    public void deleteTingtangCustomerListByIds(List<Long> ids) {
        // 删除
        tingtangCustomerMapper.deleteByIds(ids);
    }

    private void validateTingtangCustomerExists(Long id) {
        if (tingtangCustomerMapper.selectById(id) == null) {
            throw exception(TINGTANG_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public GridTingtangCustomerRespVO getTingtangCustomer(Long id) {
        return tingtangCustomerMapper.selectTingtangCustomerById(id);
    }

    @Override
    public PageResult<GridTingtangCustomerRespVO> getTingtangCustomerPage(GridTingtangCustomerPageReqVO pageReqVO) {
        // 创建 MyBatis Plus 分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<GridTingtangCustomerRespVO> mpPage =
            new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 执行分页查询
        com.baomidou.mybatisplus.core.metadata.IPage<GridTingtangCustomerRespVO> result =
            tingtangCustomerMapper.selectPageWithRelations(mpPage, pageReqVO.getCustomerName(), pageReqVO.getPhone());

        // 转换为项目的 PageResult
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

}
