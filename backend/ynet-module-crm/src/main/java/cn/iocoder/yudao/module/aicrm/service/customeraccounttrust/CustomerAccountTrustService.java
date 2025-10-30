package cn.iocoder.yudao.module.aicrm.service.customeraccounttrust;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccounttrust.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccounttrust.CustomerAccountTrustDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户信托账户信息表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAccountTrustService {

    /**
     * 创建客户信托账户信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAccountTrust(@Valid CustomerAccountTrustSaveReqVO createReqVO);

    /**
     * 更新客户信托账户信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAccountTrust(@Valid CustomerAccountTrustSaveReqVO updateReqVO);

    /**
     * 删除客户信托账户信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerAccountTrust(Long id);

    /**
    * 批量删除客户信托账户信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerAccountTrustListByIds(List<Long> ids);

    /**
     * 获得客户信托账户信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户信托账户信息表（零售+对公共用）
     */
    CustomerAccountTrustDO getCustomerAccountTrust(Long id);

    /**
     * 获得客户信托账户信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户信托账户信息表（零售+对公共用）分页
     */
    PageResult<CustomerAccountTrustDO> getCustomerAccountTrustPage(CustomerAccountTrustPageReqVO pageReqVO);

}