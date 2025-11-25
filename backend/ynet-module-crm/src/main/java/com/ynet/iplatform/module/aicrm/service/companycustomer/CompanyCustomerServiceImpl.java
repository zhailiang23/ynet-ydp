package com.ynet.iplatform.module.aicrm.service.companycustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companycustomer.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companycustomer.CompanyCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.companycustomer.CompanyCustomerMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.customer.CustomerMapper;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customer.CustomerDO;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

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

    @Resource
    private CustomerMapper customerMapper;

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
    public CompanyCustomerRespVO getCompanyCustomer(Long id) {
        // 1. 根据customer_id查询对公客户扩展信息
        CompanyCustomerDO companyCustomerDO = companyCustomerMapper.selectOne("customer_id", id);
        if (companyCustomerDO == null) {
            return null;
        }

        // 2. 转换为 VO
        CompanyCustomerRespVO respVO = BeanUtils.toBean(companyCustomerDO, CompanyCustomerRespVO.class);

        // 3. 查询客户共有信息
        Long customerId = companyCustomerDO.getCustomerId();
        if (customerId != null) {
            CustomerDO customerDO = customerMapper.selectById(customerId);
            if (customerDO != null) {
                // 4. 手动组装共有字段到 VO
                respVO.setId(customerDO.getId());
                respVO.setCustomerNo(customerDO.getCustomerNo());
                respVO.setCustomerType(customerDO.getCustomerType());
                respVO.setCustomerName(customerDO.getCustomerName());
                respVO.setCustomerLevel(customerDO.getCustomerLevel());
                respVO.setCustomerStatus(customerDO.getCustomerStatus());
                respVO.setIsHighQuality(customerDO.getIsHighQuality());
                respVO.setIsImportant(customerDO.getIsImportant());
                respVO.setCreditStatus(customerDO.getCreditStatus());
                respVO.setCreditLevel(customerDO.getCreditLevel());
                respVO.setCreditScore(customerDO.getCreditScore());
                respVO.setCustomerSource(customerDO.getCustomerSource());
                respVO.setCustomerTag(customerDO.getCustomerTag());
                respVO.setRemark(customerDO.getRemark());
                respVO.setDeptId(customerDO.getDeptId());
                respVO.setCreateTime(customerDO.getCreateTime());
                respVO.setUpdateTime(customerDO.getUpdateTime());
            }
        }

        return respVO;
    }

    @Override
    public PageResult<CompanyCustomerDO> getCompanyCustomerPage(CompanyCustomerPageReqVO pageReqVO) {
        return companyCustomerMapper.selectPage(pageReqVO);
    }

}