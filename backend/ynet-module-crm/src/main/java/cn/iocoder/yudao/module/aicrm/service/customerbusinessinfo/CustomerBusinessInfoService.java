package cn.iocoder.yudao.module.aicrm.service.customerbusinessinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerbusinessinfo.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerbusinessinfo.CustomerBusinessInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户经营信息表（精简版，只包含经营相关核心字段） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerBusinessInfoService {

    /**
     * 创建客户经营信息表（精简版，只包含经营相关核心字段）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerBusinessInfo(@Valid CustomerBusinessInfoSaveReqVO createReqVO);

    /**
     * 更新客户经营信息表（精简版，只包含经营相关核心字段）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerBusinessInfo(@Valid CustomerBusinessInfoSaveReqVO updateReqVO);

    /**
     * 删除客户经营信息表（精简版，只包含经营相关核心字段）
     *
     * @param id 编号
     */
    void deleteCustomerBusinessInfo(Long id);

    /**
    * 批量删除客户经营信息表（精简版，只包含经营相关核心字段）
    *
    * @param ids 编号
    */
    void deleteCustomerBusinessInfoListByIds(List<Long> ids);

    /**
     * 获得客户经营信息表（精简版，只包含经营相关核心字段）
     *
     * @param id 编号
     * @return 客户经营信息表（精简版，只包含经营相关核心字段）
     */
    CustomerBusinessInfoDO getCustomerBusinessInfo(Long id);

    /**
     * 获得客户经营信息表（精简版，只包含经营相关核心字段）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户经营信息表（精简版，只包含经营相关核心字段）分页
     */
    PageResult<CustomerBusinessInfoDO> getCustomerBusinessInfoPage(CustomerBusinessInfoPageReqVO pageReqVO);

}