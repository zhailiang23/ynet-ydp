package com.ynet.iplatform.module.aicrm.service.companystock;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companystock.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companystock.CompanyStockDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.companystock.CompanyStockMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 对公客户股票发行人信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CompanyStockServiceImpl implements CompanyStockService {

    @Resource
    private CompanyStockMapper companyStockMapper;

    @Override
    public Long createCompanyStock(CompanyStockSaveReqVO createReqVO) {
        // 插入
        CompanyStockDO companyStock = BeanUtils.toBean(createReqVO, CompanyStockDO.class);
        companyStockMapper.insert(companyStock);

        // 返回
        return companyStock.getId();
    }

    @Override
    public void updateCompanyStock(CompanyStockSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyStockExists(updateReqVO.getId());
        // 更新
        CompanyStockDO updateObj = BeanUtils.toBean(updateReqVO, CompanyStockDO.class);
        companyStockMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyStock(Long id) {
        // 校验存在
        validateCompanyStockExists(id);
        // 删除
        companyStockMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyStockListByIds(List<Long> ids) {
        // 删除
        companyStockMapper.deleteByIds(ids);
        }


    private void validateCompanyStockExists(Long id) {
        if (companyStockMapper.selectById(id) == null) {
            throw exception(COMPANY_STOCK_NOT_EXISTS);
        }
    }

    @Override
    public CompanyStockDO getCompanyStock(Long id) {
        return companyStockMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyStockDO> getCompanyStockPage(CompanyStockPageReqVO pageReqVO) {
        return companyStockMapper.selectPage(pageReqVO);
    }

}