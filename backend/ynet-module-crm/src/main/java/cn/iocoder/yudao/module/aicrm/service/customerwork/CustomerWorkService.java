package cn.iocoder.yudao.module.aicrm.service.customerwork;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerwork.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerwork.CustomerWorkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户工作或经营信息表 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerWorkService {

    /**
     * 创建客户工作或经营信息表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerWork(@Valid CustomerWorkSaveReqVO createReqVO);

    /**
     * 更新客户工作或经营信息表
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerWork(@Valid CustomerWorkSaveReqVO updateReqVO);

    /**
     * 删除客户工作或经营信息表
     *
     * @param id 编号
     */
    void deleteCustomerWork(Long id);

    /**
    * 批量删除客户工作或经营信息表
    *
    * @param ids 编号
    */
    void deleteCustomerWorkListByIds(List<Long> ids);

    /**
     * 获得客户工作或经营信息表
     *
     * @param id 编号
     * @return 客户工作或经营信息表
     */
    CustomerWorkDO getCustomerWork(Long id);

    /**
     * 获得客户工作或经营信息表分页
     *
     * @param pageReqVO 分页查询
     * @return 客户工作或经营信息表分页
     */
    PageResult<CustomerWorkDO> getCustomerWorkPage(CustomerWorkPageReqVO pageReqVO);

}