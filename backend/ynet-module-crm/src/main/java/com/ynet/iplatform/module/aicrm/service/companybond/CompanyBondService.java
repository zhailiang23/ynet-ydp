package com.ynet.iplatform.module.aicrm.service.companybond;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.companybond.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.companybond.CompanyBondDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 对公客户债券信息 Service 接口
 *
 * @author 易诚源码
 */
public interface CompanyBondService {

    /**
     * 创建对公客户债券信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyBond(@Valid CompanyBondSaveReqVO createReqVO);

    /**
     * 更新对公客户债券信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyBond(@Valid CompanyBondSaveReqVO updateReqVO);

    /**
     * 删除对公客户债券信息
     *
     * @param id 编号
     */
    void deleteCompanyBond(Long id);

    /**
    * 批量删除对公客户债券信息
    *
    * @param ids 编号
    */
    void deleteCompanyBondListByIds(List<Long> ids);

    /**
     * 获得对公客户债券信息
     *
     * @param id 编号
     * @return 对公客户债券信息
     */
    CompanyBondDO getCompanyBond(Long id);

    /**
     * 获得对公客户债券信息分页
     *
     * @param pageReqVO 分页查询
     * @return 对公客户债券信息分页
     */
    PageResult<CompanyBondDO> getCompanyBondPage(CompanyBondPageReqVO pageReqVO);

}