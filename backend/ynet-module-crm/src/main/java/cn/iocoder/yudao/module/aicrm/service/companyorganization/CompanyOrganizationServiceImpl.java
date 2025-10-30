package cn.iocoder.yudao.module.aicrm.service.companyorganization;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyorganization.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyorganization.CompanyOrganizationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.companyorganization.CompanyOrganizationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

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