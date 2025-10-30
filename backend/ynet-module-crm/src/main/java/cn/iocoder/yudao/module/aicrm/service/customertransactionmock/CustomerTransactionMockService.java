package cn.iocoder.yudao.module.aicrm.service.customertransactionmock;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertransactionmock.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertransactionmock.CustomerTransactionMockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户交易明细信息表（Mock数据） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerTransactionMockService {

    /**
     * 创建客户交易明细信息表（Mock数据）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerTransactionMock(@Valid CustomerTransactionMockSaveReqVO createReqVO);

    /**
     * 更新客户交易明细信息表（Mock数据）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerTransactionMock(@Valid CustomerTransactionMockSaveReqVO updateReqVO);

    /**
     * 删除客户交易明细信息表（Mock数据）
     *
     * @param id 编号
     */
    void deleteCustomerTransactionMock(Long id);

    /**
    * 批量删除客户交易明细信息表（Mock数据）
    *
    * @param ids 编号
    */
    void deleteCustomerTransactionMockListByIds(List<Long> ids);

    /**
     * 获得客户交易明细信息表（Mock数据）
     *
     * @param id 编号
     * @return 客户交易明细信息表（Mock数据）
     */
    CustomerTransactionMockDO getCustomerTransactionMock(Long id);

    /**
     * 获得客户交易明细信息表（Mock数据）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户交易明细信息表（Mock数据）分页
     */
    PageResult<CustomerTransactionMockDO> getCustomerTransactionMockPage(CustomerTransactionMockPageReqVO pageReqVO);

}