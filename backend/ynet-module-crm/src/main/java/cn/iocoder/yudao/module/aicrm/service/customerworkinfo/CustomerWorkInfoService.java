package cn.iocoder.yudao.module.aicrm.service.customerworkinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerworkinfo.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerworkinfo.CustomerWorkInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户工作信息表（精简版，只包含工作相关核心字段） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerWorkInfoService {

    /**
     * 创建客户工作信息表（精简版，只包含工作相关核心字段）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerWorkInfo(@Valid CustomerWorkInfoSaveReqVO createReqVO);

    /**
     * 更新客户工作信息表（精简版，只包含工作相关核心字段）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerWorkInfo(@Valid CustomerWorkInfoSaveReqVO updateReqVO);

    /**
     * 删除客户工作信息表（精简版，只包含工作相关核心字段）
     *
     * @param id 编号
     */
    void deleteCustomerWorkInfo(Long id);

    /**
    * 批量删除客户工作信息表（精简版，只包含工作相关核心字段）
    *
    * @param ids 编号
    */
    void deleteCustomerWorkInfoListByIds(List<Long> ids);

    /**
     * 获得客户工作信息表（精简版，只包含工作相关核心字段）
     *
     * @param id 编号
     * @return 客户工作信息表（精简版，只包含工作相关核心字段）
     */
    CustomerWorkInfoDO getCustomerWorkInfo(Long id);

    /**
     * 获得客户工作信息表（精简版，只包含工作相关核心字段）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户工作信息表（精简版，只包含工作相关核心字段）分页
     */
    PageResult<CustomerWorkInfoDO> getCustomerWorkInfoPage(CustomerWorkInfoPageReqVO pageReqVO);

}