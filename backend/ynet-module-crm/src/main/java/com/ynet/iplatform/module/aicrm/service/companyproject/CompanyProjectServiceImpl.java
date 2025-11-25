package com.ynet.iplatform.module.aicrm.service.companyproject;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companyproject.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companyproject.CompanyProjectDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.companyproject.CompanyProjectMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 对公客户项目信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompanyProjectServiceImpl implements CompanyProjectService {

    @Resource
    private CompanyProjectMapper companyProjectMapper;

    @Override
    public Long createCompanyProject(CompanyProjectSaveReqVO createReqVO) {
        // 插入
        CompanyProjectDO companyProject = BeanUtils.toBean(createReqVO, CompanyProjectDO.class);
        companyProjectMapper.insert(companyProject);

        // 返回
        return companyProject.getId();
    }

    @Override
    public void updateCompanyProject(CompanyProjectSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyProjectExists(updateReqVO.getId());
        // 更新
        CompanyProjectDO updateObj = BeanUtils.toBean(updateReqVO, CompanyProjectDO.class);
        companyProjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyProject(Long id) {
        // 校验存在
        validateCompanyProjectExists(id);
        // 删除
        companyProjectMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyProjectListByIds(List<Long> ids) {
        // 删除
        companyProjectMapper.deleteByIds(ids);
        }


    private void validateCompanyProjectExists(Long id) {
        if (companyProjectMapper.selectById(id) == null) {
            throw exception(COMPANY_PROJECT_NOT_EXISTS);
        }
    }

    @Override
    public CompanyProjectDO getCompanyProject(Long id) {
        return companyProjectMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyProjectDO> getCompanyProjectPage(CompanyProjectPageReqVO pageReqVO) {
        return companyProjectMapper.selectPage(pageReqVO);
    }

}