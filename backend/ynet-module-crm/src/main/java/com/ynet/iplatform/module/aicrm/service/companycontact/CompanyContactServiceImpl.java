package com.ynet.iplatform.module.aicrm.service.companycontact;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companycontact.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companycontact.CompanyContactDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.companycontact.CompanyContactMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM对公客户联系人信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CompanyContactServiceImpl implements CompanyContactService {

    @Resource
    private CompanyContactMapper companyContactMapper;

    @Override
    public Long createCompanyContact(CompanyContactSaveReqVO createReqVO) {
        // 插入
        CompanyContactDO companyContact = BeanUtils.toBean(createReqVO, CompanyContactDO.class);
        companyContactMapper.insert(companyContact);

        // 返回
        return companyContact.getId();
    }

    @Override
    public void updateCompanyContact(CompanyContactSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyContactExists(updateReqVO.getId());
        // 更新
        CompanyContactDO updateObj = BeanUtils.toBean(updateReqVO, CompanyContactDO.class);
        companyContactMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyContact(Long id) {
        // 校验存在
        validateCompanyContactExists(id);
        // 删除
        companyContactMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyContactListByIds(List<Long> ids) {
        // 删除
        companyContactMapper.deleteByIds(ids);
        }


    private void validateCompanyContactExists(Long id) {
        if (companyContactMapper.selectById(id) == null) {
            throw exception(COMPANY_CONTACT_NOT_EXISTS);
        }
    }

    @Override
    public CompanyContactDO getCompanyContact(Long id) {
        return companyContactMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyContactDO> getCompanyContactPage(CompanyContactPageReqVO pageReqVO) {
        return companyContactMapper.selectPage(pageReqVO);
    }

}