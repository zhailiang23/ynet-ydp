package com.ynet.iplatform.module.grid.service.huinongcustomerloan;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongcustomerloan.GridHuinongCustomerLoanDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.huinongcustomerloan.GridHuinongCustomerLoanMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.iplatform.framework.mybatis.core.util.MyBatisUtils;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 惠农贷款客户扩展 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridHuinongCustomerLoanServiceImpl implements GridHuinongCustomerLoanService {

    @Resource
    private GridHuinongCustomerLoanMapper huinongCustomerLoanMapper;

    @Override
    public Long createHuinongCustomerLoan(GridHuinongCustomerLoanSaveReqVO createReqVO) {
        // 插入
        GridHuinongCustomerLoanDO huinongCustomerLoan = BeanUtils.toBean(createReqVO, GridHuinongCustomerLoanDO.class);
        huinongCustomerLoanMapper.insert(huinongCustomerLoan);

        // 返回
        return huinongCustomerLoan.getId();
    }

    @Override
    public void updateHuinongCustomerLoan(GridHuinongCustomerLoanSaveReqVO updateReqVO) {
        // 校验存在
        validateHuinongCustomerLoanExists(updateReqVO.getId());
        // 更新
        GridHuinongCustomerLoanDO updateObj = BeanUtils.toBean(updateReqVO, GridHuinongCustomerLoanDO.class);
        huinongCustomerLoanMapper.updateById(updateObj);
    }

    @Override
    public void deleteHuinongCustomerLoan(Long id) {
        // 校验存在
        validateHuinongCustomerLoanExists(id);
        // 删除
        huinongCustomerLoanMapper.deleteById(id);
    }

    @Override
        public void deleteHuinongCustomerLoanListByIds(List<Long> ids) {
        // 删除
        huinongCustomerLoanMapper.deleteByIds(ids);
        }


    private void validateHuinongCustomerLoanExists(Long id) {
        if (huinongCustomerLoanMapper.selectById(id) == null) {
            throw exception(HUINONG_CUSTOMER_LOAN_NOT_EXISTS);
        }
    }

    @Override
    public GridHuinongCustomerLoanRespVO getHuinongCustomerLoan(Long id) {
        // 使用关联查询，直接返回 RespVO（包含客户姓名、手机号和站点ID）
        return huinongCustomerLoanMapper.selectByIdWithRelations(id);
    }

    @Override
    public PageResult<GridHuinongCustomerLoanRespVO> getHuinongCustomerLoanPage(GridHuinongCustomerLoanPageReqVO pageReqVO) {
        // 1. 构建 MyBatis Plus 分页对象
        IPage<GridHuinongCustomerLoanRespVO> mpPage = MyBatisUtils.buildPage(pageReqVO);

        // 2. 执行分页查询（使用 LEFT JOIN 查询客户姓名和手机号）
        mpPage = huinongCustomerLoanMapper.selectPageWithRelations(mpPage, pageReqVO);

        // 3. 转换为框架的 PageResult
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    @Override
    public List<GridHuinongCustomerLoanHeatmapDataVO> getHeatmapData(GridHuinongCustomerLoanHeatmapReqVO reqVO) {
        // 直接查询热力图数据（按网格分组，根据 metricType 计算热力值）
        return huinongCustomerLoanMapper.selectHeatmapData(reqVO);
    }

    @Override
    public List<GridHuinongCustomerLoanCustomerMarkerVO> getCustomerMarkers() {
        // 查询所有客户标记数据
        return huinongCustomerLoanMapper.selectCustomerMarkers();
    }

}