package cn.iocoder.yudao.module.aicrm.service.customerguaranteemortgage;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerguaranteemortgage.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerguaranteemortgage.CustomerGuaranteeMortgageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户抵押物信息表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerGuaranteeMortgageService {

    /**
     * 创建客户抵押物信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerGuaranteeMortgage(@Valid CustomerGuaranteeMortgageSaveReqVO createReqVO);

    /**
     * 更新客户抵押物信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerGuaranteeMortgage(@Valid CustomerGuaranteeMortgageSaveReqVO updateReqVO);

    /**
     * 删除客户抵押物信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerGuaranteeMortgage(Long id);

    /**
    * 批量删除客户抵押物信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerGuaranteeMortgageListByIds(List<Long> ids);

    /**
     * 获得客户抵押物信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户抵押物信息表（零售+对公共用）
     */
    CustomerGuaranteeMortgageDO getCustomerGuaranteeMortgage(Long id);

    /**
     * 获得客户抵押物信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户抵押物信息表（零售+对公共用）分页
     */
    PageResult<CustomerGuaranteeMortgageDO> getCustomerGuaranteeMortgagePage(CustomerGuaranteeMortgagePageReqVO pageReqVO);

}