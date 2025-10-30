package cn.iocoder.yudao.module.aicrm.service.companyorganization;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyorganization.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyorganization.CompanyOrganizationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM对公客户组织架构信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CompanyOrganizationService {

    /**
     * 创建CRM对公客户组织架构信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyOrganization(@Valid CompanyOrganizationSaveReqVO createReqVO);

    /**
     * 更新CRM对公客户组织架构信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyOrganization(@Valid CompanyOrganizationSaveReqVO updateReqVO);

    /**
     * 删除CRM对公客户组织架构信息
     *
     * @param id 编号
     */
    void deleteCompanyOrganization(Long id);

    /**
    * 批量删除CRM对公客户组织架构信息
    *
    * @param ids 编号
    */
    void deleteCompanyOrganizationListByIds(List<Long> ids);

    /**
     * 获得CRM对公客户组织架构信息
     *
     * @param id 编号
     * @return CRM对公客户组织架构信息
     */
    CompanyOrganizationDO getCompanyOrganization(Long id);

    /**
     * 获得CRM对公客户组织架构信息分页
     *
     * @param pageReqVO 分页查询
     * @return CRM对公客户组织架构信息分页
     */
    PageResult<CompanyOrganizationDO> getCompanyOrganizationPage(CompanyOrganizationPageReqVO pageReqVO);

}