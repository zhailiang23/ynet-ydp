package cn.iocoder.yudao.module.aicrm.service.companyotherbank;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyotherbank.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyotherbank.CompanyOtherBankDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.companyotherbank.CompanyOtherBankMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

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