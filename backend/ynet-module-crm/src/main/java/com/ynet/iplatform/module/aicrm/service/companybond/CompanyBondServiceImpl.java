package com.ynet.iplatform.module.aicrm.service.companybond;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companybond.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companybond.CompanyBondDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.companybond.CompanyBondMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 对公客户债券信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompanyBondServiceImpl implements CompanyBondService {

    @Resource
    private CompanyBondMapper companyBondMapper;

    @Override
    public Long createCompanyBond(CompanyBondSaveReqVO createReqVO) {
        // 插入
        CompanyBondDO companyBond = BeanUtils.toBean(createReqVO, CompanyBondDO.class);
        companyBondMapper.insert(companyBond);

        // 返回
        return companyBond.getId();
    }

    @Override
    public void updateCompanyBond(CompanyBondSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyBondExists(updateReqVO.getId());
        // 更新
        CompanyBondDO updateObj = BeanUtils.toBean(updateReqVO, CompanyBondDO.class);
        companyBondMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyBond(Long id) {
        // 校验存在
        validateCompanyBondExists(id);
        // 删除
        companyBondMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyBondListByIds(List<Long> ids) {
        // 删除
        companyBondMapper.deleteByIds(ids);
        }


    private void validateCompanyBondExists(Long id) {
        if (companyBondMapper.selectById(id) == null) {
            throw exception(COMPANY_BOND_NOT_EXISTS);
        }
    }

    @Override
    public CompanyBondDO getCompanyBond(Long id) {
        return companyBondMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyBondDO> getCompanyBondPage(CompanyBondPageReqVO pageReqVO) {
        return companyBondMapper.selectPage(pageReqVO);
    }

}