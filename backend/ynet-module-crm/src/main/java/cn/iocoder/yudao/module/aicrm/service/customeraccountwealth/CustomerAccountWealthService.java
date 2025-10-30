package cn.iocoder.yudao.module.aicrm.service.customeraccountwealth;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountwealth.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeraccountwealth.CustomerAccountWealthDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户理财账户信息表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerAccountWealthService {

    /**
     * 创建客户理财账户信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerAccountWealth(@Valid CustomerAccountWealthSaveReqVO createReqVO);

    /**
     * 更新客户理财账户信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerAccountWealth(@Valid CustomerAccountWealthSaveReqVO updateReqVO);

    /**
     * 删除客户理财账户信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerAccountWealth(Long id);

    /**
    * 批量删除客户理财账户信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerAccountWealthListByIds(List<Long> ids);

    /**
     * 获得客户理财账户信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户理财账户信息表（零售+对公共用）
     */
    CustomerAccountWealthDO getCustomerAccountWealth(Long id);

    /**
     * 获得客户理财账户信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户理财账户信息表（零售+对公共用）分页
     */
    PageResult<CustomerAccountWealthDO> getCustomerAccountWealthPage(CustomerAccountWealthPageReqVO pageReqVO);

}