package com.ynet.iplatform.module.grid.service.huinongcustomerloan;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo.*;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.customer.GridCustomerMapper;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.mybatis.core.util.MyBatisUtils;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 惠农贷款客户扩展 Service 实现类
 *
 * 重构说明：已将 grid_huinong_customer_loan 表合并到 grid_customer 表
 * 通过 customerType='HUINONG_LOAN' 过滤惠农贷款客户
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridHuinongCustomerLoanServiceImpl implements GridHuinongCustomerLoanService {

    @Resource(name = "gridCustomerMapper")
    private GridCustomerMapper gridCustomerMapper;

    @Override
    public Long createHuinongCustomerLoan(GridHuinongCustomerLoanSaveReqVO createReqVO) {
        // 重构说明：原有设计中，grid_customer 和 grid_huinong_customer_loan 分开存储
        // 现在合并后，customerId 指向要更新的 grid_customer 记录
        // 我们需要更新这条 grid_customer 记录，设置 customerType='HUINONG_LOAN' 并填充贷款字段

        // 1. 查询现有的 grid_customer 记录
        GridCustomerDO customer = gridCustomerMapper.selectById(createReqVO.getCustomerId());
        if (customer == null) {
            throw exception(HUINONG_CUSTOMER_LOAN_NOT_EXISTS);
        }

        // 2. 更新贷款相关字段
        BeanUtils.copyProperties(createReqVO, customer);
        customer.setId(createReqVO.getCustomerId()); // 确保 ID 正确
        customer.setCustomerType("HUINONG_LOAN"); // 设置客户类型为惠农贷款

        // 3. 更新数据库
        gridCustomerMapper.updateById(customer);

        // 4. 返回客户ID（即 customerId）
        return customer.getId();
    }

    @Override
    public void updateHuinongCustomerLoan(GridHuinongCustomerLoanSaveReqVO updateReqVO) {
        // 重构说明：updateReqVO.id 在旧设计中是 huinongCustomerLoan 的 ID
        // 现在应该理解为 grid_customer 的 ID
        // 但是 SaveReqVO 中 id 和 customerId 都有，id 是扩展表的主键，customerId 是客户表的主键
        // 合并后，这两者应该相等，我们使用 id 作为主键

        // 校验存在
        validateHuinongCustomerLoanExists(updateReqVO.getId());

        // 查询现有记录
        GridCustomerDO customer = gridCustomerMapper.selectById(updateReqVO.getId());
        if (customer == null) {
            throw exception(HUINONG_CUSTOMER_LOAN_NOT_EXISTS);
        }

        // 更新贷款相关字段
        BeanUtils.copyProperties(updateReqVO, customer);
        customer.setCustomerType("HUINONG_LOAN"); // 确保客户类型不变

        // 更新数据库
        gridCustomerMapper.updateById(customer);
    }

    @Override
    public void deleteHuinongCustomerLoan(Long id) {
        // 校验存在
        validateHuinongCustomerLoanExists(id);
        // 删除（逻辑删除）
        gridCustomerMapper.deleteById(id);
    }

    @Override
    public void deleteHuinongCustomerLoanListByIds(List<Long> ids) {
        // 批量删除（逻辑删除）
        gridCustomerMapper.deleteByIds(ids);
    }

    private void validateHuinongCustomerLoanExists(Long id) {
        GridCustomerDO customer = gridCustomerMapper.selectById(id);
        if (customer == null || !"HUINONG_LOAN".equals(customer.getCustomerType())) {
            throw exception(HUINONG_CUSTOMER_LOAN_NOT_EXISTS);
        }
    }

    @Override
    public GridHuinongCustomerLoanRespVO getHuinongCustomerLoan(Long id) {
        // 使用关联查询，直接返回 RespVO（包含客户姓名、手机号和站点ID）
        return gridCustomerMapper.selectHuinongLoanByIdWithRelations(id);
    }

    @Override
    public PageResult<GridHuinongCustomerLoanRespVO> getHuinongCustomerLoanPage(GridHuinongCustomerLoanPageReqVO pageReqVO) {
        // 1. 构建 MyBatis Plus 分页对象
        IPage<GridHuinongCustomerLoanRespVO> mpPage = MyBatisUtils.buildPage(pageReqVO);

        // 2. 执行分页查询（从 grid_customer 表查询，customerType='HUINONG_LOAN'）
        mpPage = gridCustomerMapper.selectHuinongLoanPageWithRelations(mpPage, pageReqVO);

        // 3. 转换为框架的 PageResult
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    @Override
    public List<GridHuinongCustomerLoanHeatmapDataVO> getHeatmapData(GridHuinongCustomerLoanHeatmapReqVO reqVO) {
        // 直接查询热力图数据（从 grid_customer 表按网格分组，根据 metricType 计算热力值）
        return gridCustomerMapper.selectHuinongLoanHeatmapData(reqVO);
    }

    @Override
    public List<GridHuinongCustomerLoanCustomerMarkerVO> getCustomerMarkers() {
        // 查询所有客户标记数据（从 grid_customer 表）
        return gridCustomerMapper.selectHuinongLoanCustomerMarkers();
    }

}