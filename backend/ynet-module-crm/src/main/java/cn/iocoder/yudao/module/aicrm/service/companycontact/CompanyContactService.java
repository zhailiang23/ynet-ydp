package cn.iocoder.yudao.module.aicrm.service.companycontact;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companycontact.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companycontact.CompanyContactDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM对公客户联系人信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CompanyContactService {

    /**
     * 创建CRM对公客户联系人信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyContact(@Valid CompanyContactSaveReqVO createReqVO);

    /**
     * 更新CRM对公客户联系人信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyContact(@Valid CompanyContactSaveReqVO updateReqVO);

    /**
     * 删除CRM对公客户联系人信息
     *
     * @param id 编号
     */
    void deleteCompanyContact(Long id);

    /**
    * 批量删除CRM对公客户联系人信息
    *
    * @param ids 编号
    */
    void deleteCompanyContactListByIds(List<Long> ids);

    /**
     * 获得CRM对公客户联系人信息
     *
     * @param id 编号
     * @return CRM对公客户联系人信息
     */
    CompanyContactDO getCompanyContact(Long id);

    /**
     * 获得CRM对公客户联系人信息分页
     *
     * @param pageReqVO 分页查询
     * @return CRM对公客户联系人信息分页
     */
    PageResult<CompanyContactDO> getCompanyContactPage(CompanyContactPageReqVO pageReqVO);

}