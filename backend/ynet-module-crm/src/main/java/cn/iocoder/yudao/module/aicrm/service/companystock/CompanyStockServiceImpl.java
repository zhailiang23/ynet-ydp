package cn.iocoder.yudao.module.aicrm.service.companystock;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companystock.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companystock.CompanyStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.companystock.CompanyStockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 对公客户股票发行人信息 Service 实现类
 *
 * @author 芋道源码
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