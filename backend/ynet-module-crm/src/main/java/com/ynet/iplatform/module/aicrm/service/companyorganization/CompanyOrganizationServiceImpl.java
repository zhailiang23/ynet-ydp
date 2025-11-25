package com.ynet.iplatform.module.aicrm.service.companyorganization;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companyorganization.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companyorganization.CompanyOrganizationDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.companyorganization.CompanyOrganizationMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM对公客户组织架构信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompanyOrganizationServiceImpl implements CompanyOrganizationService {

    @Resource
    private CompanyOrganizationMapper companyOrganizationMapper;

    @Override
    public Long createCompanyOrganization(CompanyOrganizationSaveReqVO createReqVO) {
        // 插入
        CompanyOrganizationDO companyOrganization = BeanUtils.toBean(createReqVO, CompanyOrganizationDO.class);
        companyOrganizationMapper.insert(companyOrganization);

        // 返回
        return companyOrganization.getId();
    }

    @Override
    public void updateCompanyOrganization(CompanyOrganizationSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyOrganizationExists(updateReqVO.getId());
        // 更新
        CompanyOrganizationDO updateObj = BeanUtils.toBean(updateReqVO, CompanyOrganizationDO.class);
        companyOrganizationMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyOrganization(Long id) {
        // 校验存在
        validateCompanyOrganizationExists(id);
        // 删除
        companyOrganizationMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyOrganizationListByIds(List<Long> ids) {
        // 删除
        companyOrganizationMapper.deleteByIds(ids);
        }


    private void validateCompanyOrganizationExists(Long id) {
        if (companyOrganizationMapper.selectById(id) == null) {
            throw exception(COMPANY_ORGANIZATION_NOT_EXISTS);
        }
    }

    @Override
    public CompanyOrganizationDO getCompanyOrganization(Long id) {
        return companyOrganizationMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyOrganizationDO> getCompanyOrganizationPage(CompanyOrganizationPageReqVO pageReqVO) {
        return companyOrganizationMapper.selectPage(pageReqVO);
    }

}