package com.ynet.iplatform.module.aicrm.service.companyotherbank;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companyotherbank.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companyotherbank.CompanyOtherBankDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.companyotherbank.CompanyOtherBankMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 对公客户他行信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompanyOtherBankServiceImpl implements CompanyOtherBankService {

    @Resource
    private CompanyOtherBankMapper companyOtherBankMapper;

    @Override
    public Long createCompanyOtherBank(CompanyOtherBankSaveReqVO createReqVO) {
        // 插入
        CompanyOtherBankDO companyOtherBank = BeanUtils.toBean(createReqVO, CompanyOtherBankDO.class);
        companyOtherBankMapper.insert(companyOtherBank);

        // 返回
        return companyOtherBank.getId();
    }

    @Override
    public void updateCompanyOtherBank(CompanyOtherBankSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyOtherBankExists(updateReqVO.getId());
        // 更新
        CompanyOtherBankDO updateObj = BeanUtils.toBean(updateReqVO, CompanyOtherBankDO.class);
        companyOtherBankMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyOtherBank(Long id) {
        // 校验存在
        validateCompanyOtherBankExists(id);
        // 删除
        companyOtherBankMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyOtherBankListByIds(List<Long> ids) {
        // 删除
        companyOtherBankMapper.deleteByIds(ids);
        }


    private void validateCompanyOtherBankExists(Long id) {
        if (companyOtherBankMapper.selectById(id) == null) {
            throw exception(COMPANY_OTHER_BANK_NOT_EXISTS);
        }
    }

    @Override
    public CompanyOtherBankDO getCompanyOtherBank(Long id) {
        return companyOtherBankMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyOtherBankDO> getCompanyOtherBankPage(CompanyOtherBankPageReqVO pageReqVO) {
        return companyOtherBankMapper.selectPage(pageReqVO);
    }

}