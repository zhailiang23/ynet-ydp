package com.ynet.iplatform.module.aicrm.service.companyotherbank;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companyotherbank.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companyotherbank.CompanyOtherBankDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 对公客户他行信息 Service 接口
 *
 * @author 易诚源码
 */
public interface CompanyOtherBankService {

    /**
     * 创建对公客户他行信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyOtherBank(@Valid CompanyOtherBankSaveReqVO createReqVO);

    /**
     * 更新对公客户他行信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyOtherBank(@Valid CompanyOtherBankSaveReqVO updateReqVO);

    /**
     * 删除对公客户他行信息
     *
     * @param id 编号
     */
    void deleteCompanyOtherBank(Long id);

    /**
    * 批量删除对公客户他行信息
    *
    * @param ids 编号
    */
    void deleteCompanyOtherBankListByIds(List<Long> ids);

    /**
     * 获得对公客户他行信息
     *
     * @param id 编号
     * @return 对公客户他行信息
     */
    CompanyOtherBankDO getCompanyOtherBank(Long id);

    /**
     * 获得对公客户他行信息分页
     *
     * @param pageReqVO 分页查询
     * @return 对公客户他行信息分页
     */
    PageResult<CompanyOtherBankDO> getCompanyOtherBankPage(CompanyOtherBankPageReqVO pageReqVO);

}