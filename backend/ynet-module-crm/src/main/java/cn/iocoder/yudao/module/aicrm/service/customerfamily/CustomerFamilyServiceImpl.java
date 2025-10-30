package cn.iocoder.yudao.module.aicrm.service.customerfamily;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerfamily.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerfamily.CustomerFamilyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerfamily.CustomerFamilyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户家庭信息表（零售客户） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerFamilyServiceImpl implements CustomerFamilyService {

    @Resource
    private CustomerFamilyMapper customerFamilyMapper;

    @Override
    public Long createCustomerFamily(CustomerFamilySaveReqVO createReqVO) {
        // 插入
        CustomerFamilyDO customerFamily = BeanUtils.toBean(createReqVO, CustomerFamilyDO.class);
        customerFamilyMapper.insert(customerFamily);

        // 返回
        return customerFamily.getId();
    }

    @Override
    public void updateCustomerFamily(CustomerFamilySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerFamilyExists(updateReqVO.getId());
        // 更新
        CustomerFamilyDO updateObj = BeanUtils.toBean(updateReqVO, CustomerFamilyDO.class);
        customerFamilyMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerFamily(Long id) {
        // 校验存在
        validateCustomerFamilyExists(id);
        // 删除
        customerFamilyMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerFamilyListByIds(List<Long> ids) {
        // 删除
        customerFamilyMapper.deleteByIds(ids);
        }


    private void validateCustomerFamilyExists(Long id) {
        if (customerFamilyMapper.selectById(id) == null) {
            throw exception(CUSTOMER_FAMILY_NOT_EXISTS);
        }
    }

    @Override
    public CustomerFamilyDO getCustomerFamily(Long id) {
        return customerFamilyMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerFamilyDO> getCustomerFamilyPage(CustomerFamilyPageReqVO pageReqVO) {
        return customerFamilyMapper.selectPage(pageReqVO);
    }

}