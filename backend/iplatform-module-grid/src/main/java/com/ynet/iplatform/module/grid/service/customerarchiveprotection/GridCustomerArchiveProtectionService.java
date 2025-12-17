package com.ynet.iplatform.module.grid.service.customerarchiveprotection;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customerarchiveprotection.GridCustomerArchiveProtectionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户档案保护记录 Service 接口
 *
 * @author 易诚源码
 */
public interface GridCustomerArchiveProtectionService {

    /**
     * 创建客户档案保护记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerArchiveProtection(@Valid GridCustomerArchiveProtectionSaveReqVO createReqVO);

    /**
     * 更新客户档案保护记录
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerArchiveProtection(@Valid GridCustomerArchiveProtectionSaveReqVO updateReqVO);

    /**
     * 删除客户档案保护记录
     *
     * @param id 编号
     */
    void deleteCustomerArchiveProtection(Long id);

    /**
    * 批量删除客户档案保护记录
    *
    * @param ids 编号
    */
    void deleteCustomerArchiveProtectionListByIds(List<Long> ids);

    /**
     * 获得客户档案保护记录
     *
     * @param id 编号
     * @return 客户档案保护记录
     */
    GridCustomerArchiveProtectionDO getCustomerArchiveProtection(Long id);

    /**
     * 获得客户档案保护记录分页
     *
     * @param pageReqVO 分页查询
     * @return 客户档案保护记录分页
     */
    PageResult<GridCustomerArchiveProtectionDO> getCustomerArchiveProtectionPage(GridCustomerArchiveProtectionPageReqVO pageReqVO);

}