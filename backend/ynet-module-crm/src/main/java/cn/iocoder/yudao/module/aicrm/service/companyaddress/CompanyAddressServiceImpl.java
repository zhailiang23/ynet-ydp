package cn.iocoder.yudao.module.aicrm.service.companyaddress;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyaddress.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyaddress.CompanyAddressDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.companyaddress.CompanyAddressMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM对公客户地址信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompanyAddressServiceImpl implements CompanyAddressService {

    @Resource
    private CompanyAddressMapper companyAddressMapper;

    @Override
    public Long createCompanyAddress(CompanyAddressSaveReqVO createReqVO) {
        // 插入
        CompanyAddressDO companyAddress = BeanUtils.toBean(createReqVO, CompanyAddressDO.class);
        companyAddressMapper.insert(companyAddress);

        // 返回
        return companyAddress.getId();
    }

    @Override
    public void updateCompanyAddress(CompanyAddressSaveReqVO updateReqVO) {
        // 校验存在
        validateCompanyAddressExists(updateReqVO.getId());
        // 更新
        CompanyAddressDO updateObj = BeanUtils.toBean(updateReqVO, CompanyAddressDO.class);
        companyAddressMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompanyAddress(Long id) {
        // 校验存在
        validateCompanyAddressExists(id);
        // 删除
        companyAddressMapper.deleteById(id);
    }

    @Override
        public void deleteCompanyAddressListByIds(List<Long> ids) {
        // 删除
        companyAddressMapper.deleteByIds(ids);
        }


    private void validateCompanyAddressExists(Long id) {
        if (companyAddressMapper.selectById(id) == null) {
            throw exception(COMPANY_ADDRESS_NOT_EXISTS);
        }
    }

    @Override
    public CompanyAddressDO getCompanyAddress(Long id) {
        return companyAddressMapper.selectById(id);
    }

    @Override
    public PageResult<CompanyAddressDO> getCompanyAddressPage(CompanyAddressPageReqVO pageReqVO) {
        return companyAddressMapper.selectPage(pageReqVO);
    }

}