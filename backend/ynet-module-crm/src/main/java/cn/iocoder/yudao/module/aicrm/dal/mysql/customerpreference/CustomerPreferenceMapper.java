package cn.iocoder.yudao.module.aicrm.dal.mysql.customerpreference;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpreference.CustomerPreferenceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户偏好 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerPreferenceMapper extends BaseMapperX<CustomerPreferenceDO> {

    /**
     * 根据客户ID查询客户偏好
     *
     * @param customerId 客户ID
     * @return 客户偏好DO
     */
    default CustomerPreferenceDO selectByCustomerId(Long customerId) {
        return selectOne(new LambdaQueryWrapperX<CustomerPreferenceDO>()
                .eq(CustomerPreferenceDO::getCustomerId, customerId));
    }

}
