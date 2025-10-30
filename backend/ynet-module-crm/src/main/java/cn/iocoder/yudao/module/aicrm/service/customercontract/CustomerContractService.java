package cn.iocoder.yudao.module.aicrm.service.customercontract;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customercontract.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customercontract.CustomerContractDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户签约信息表（零售+对公共用） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerContractService {

    /**
     * 创建客户签约信息表（零售+对公共用）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerContract(@Valid CustomerContractSaveReqVO createReqVO);

    /**
     * 更新客户签约信息表（零售+对公共用）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerContract(@Valid CustomerContractSaveReqVO updateReqVO);

    /**
     * 删除客户签约信息表（零售+对公共用）
     *
     * @param id 编号
     */
    void deleteCustomerContract(Long id);

    /**
    * 批量删除客户签约信息表（零售+对公共用）
    *
    * @param ids 编号
    */
    void deleteCustomerContractListByIds(List<Long> ids);

    /**
     * 获得客户签约信息表（零售+对公共用）
     *
     * @param id 编号
     * @return 客户签约信息表（零售+对公共用）
     */
    CustomerContractDO getCustomerContract(Long id);

    /**
     * 获得客户签约信息表（零售+对公共用）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户签约信息表（零售+对公共用）分页
     */
    PageResult<CustomerContractDO> getCustomerContractPage(CustomerContractPageReqVO pageReqVO);

}