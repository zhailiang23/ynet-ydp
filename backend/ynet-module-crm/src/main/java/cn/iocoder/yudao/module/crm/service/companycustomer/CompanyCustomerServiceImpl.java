package cn.iocoder.yudao.module.crm.service.companycustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.crm.controller.admin.companycustomer.vo.*;
import cn.iocoder.yudao.module.crm.dal.dataobject.companycustomer.CompanyCustomerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.crm.dal.mysql.companycustomer.CompanyCustomerMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.crm.enums.ErrorCodeConstants.*;

/**
 * CRM对公客户扩展表(对公客户特有基本信息) Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompanyCustomerServiceImpl implements CompanyCustomerService {

    @Resource
    private CompanyCustomerMapper companyCustomerMapper;

    @Override
    public Long createCompanyCustomer(CompanyCustomerSaveReqVO createReqVO) {
        // 插入
        CompanyCustomerDO companyCustomer = BeanUtils.toBean(createReqVO, CompanyCustomerDO.class);
        companyCustomerMapper.insert(companyCustomer);

        // 返回
        return companyCustomer.getId();
    }

    @Override
    public void updateCompanyCustomer(CompanyCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyCustomerExists(updateReqVO.getId());
        // 更新
        CompanyCustomerDO updateObj = BeanUtils.toBean(updateReqVO, CompanyCustomerDO.class);
        companyCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyCustomer(Long id) {
        // 校验存在
        validateCompanyCustomerExists(id);
        // 删除
        companyCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyCustomerListByIds(List<Long> ids) {
        // 删除
        companyCustomerMapper.deleteByIds(ids);
        }


    private void validateCompanyCustomerExists(Long id) {
        if (companyCustomerMapper.selectById(id) == null) {
            throw exception(COMPANY_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public CompanyCustomerDO getCompanyCustomer(Long id) {
        return companyCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyCustomerDO> getCompanyCustomerPage(CompanyCustomerPageReqVO pageReqVO) {
        return companyCustomerMapper.selectPage(pageReqVO);
    }

}