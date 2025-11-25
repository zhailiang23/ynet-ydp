package com.ynet.iplatform.module.aicrm.service.companyaddress;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companyaddress.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companyaddress.CompanyAddressDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * CRM对公客户地址信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CompanyAddressService {

    /**
     * 创建CRM对公客户地址信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyAddress(@Valid CompanyAddressSaveReqVO createReqVO);

    /**
     * 更新CRM对公客户地址信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyAddress(@Valid CompanyAddressSaveReqVO updateReqVO);

    /**
     * 删除CRM对公客户地址信息
     *
     * @param id 编号
     */
    void deleteCompanyAddress(Long id);

    /**
    * 批量删除CRM对公客户地址信息
    *
    * @param ids 编号
    */
    void deleteCompanyAddressListByIds(List<Long> ids);

    /**
     * 获得CRM对公客户地址信息
     *
     * @param id 编号
     * @return CRM对公客户地址信息
     */
    CompanyAddressDO getCompanyAddress(Long id);

    /**
     * 获得CRM对公客户地址信息分页
     *
     * @param pageReqVO 分页查询
     * @return CRM对公客户地址信息分页
     */
    PageResult<CompanyAddressDO> getCompanyAddressPage(CompanyAddressPageReqVO pageReqVO);

}