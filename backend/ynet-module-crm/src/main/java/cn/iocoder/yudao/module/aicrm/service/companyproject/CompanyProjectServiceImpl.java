package cn.iocoder.yudao.module.aicrm.service.companyproject;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyproject.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyproject.CompanyProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.companyproject.CompanyProjectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

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