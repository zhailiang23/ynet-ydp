package cn.iocoder.yudao.module.aicrm.service.customerpreference;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.aicrm.controller.admin.customerpreference.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpreference.CustomerPreferenceDO;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customerpreference.CustomerPreferenceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户偏好 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerPreferenceServiceImpl implements CustomerPreferenceService {

    @Resource
    private CustomerPreferenceMapper customerPreferenceMapper;

    @Override
    public Long saveCustomerPreference(CustomerPreferenceSaveReqVO saveReqVO) {
        // 使用 Converter 将 VO 转换为 DO
        CustomerPreferenceDO customerPreference = CustomerPreferenceConverter.toDO(saveReqVO);

        // 查询是否已存在该客户的偏好记录
        CustomerPreferenceDO existingPreference = customerPreferenceMapper.selectByCustomerId(saveReqVO.getCustomerId());

        if (existingPreference != null) {
            // 更新现有记录
            customerPreference.setId(existingPreference.getId());
            customerPreferenceMapper.updateById(customerPreference);
            return existingPreference.getId();
        } else {
            // 创建新记录
            customerPreferenceMapper.insert(customerPreference);
            return customerPreference.getId();
        }
    }

    @Override
    public CustomerPreferenceRespVO getCustomerPreferenceByCustomerId(Long customerId) {
        CustomerPreferenceDO preference = customerPreferenceMapper.selectByCustomerId(customerId);
        // 使用 Converter 将 DO 转换为 VO
        return CustomerPreferenceConverter.toRespVO(preference);
    }

    @Override
    public void deleteCustomerPreference(Long id) {
        // 校验存在
        validateCustomerPreferenceExists(id);
        // 删除
        customerPreferenceMapper.deleteById(id);
    }

    private void validateCustomerPreferenceExists(Long id) {
        if (customerPreferenceMapper.selectById(id) == null) {
            throw exception(CUSTOMER_PREFERENCE_NOT_EXISTS);
        }
    }

}
