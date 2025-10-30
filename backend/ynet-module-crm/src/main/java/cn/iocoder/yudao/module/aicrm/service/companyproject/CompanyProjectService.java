package cn.iocoder.yudao.module.aicrm.service.companyproject;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyproject.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyproject.CompanyProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 对公客户项目信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CompanyProjectService {

    /**
     * 创建对公客户项目信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyProject(@Valid CompanyProjectSaveReqVO createReqVO);

    /**
     * 更新对公客户项目信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyProject(@Valid CompanyProjectSaveReqVO updateReqVO);

    /**
     * 删除对公客户项目信息
     *
     * @param id 编号
     */
    void deleteCompanyProject(Long id);

    /**
    * 批量删除对公客户项目信息
    *
    * @param ids 编号
    */
    void deleteCompanyProjectListByIds(List<Long> ids);

    /**
     * 获得对公客户项目信息
     *
     * @param id 编号
     * @return 对公客户项目信息
     */
    CompanyProjectDO getCompanyProject(Long id);

    /**
     * 获得对公客户项目信息分页
     *
     * @param pageReqVO 分页查询
     * @return 对公客户项目信息分页
     */
    PageResult<CompanyProjectDO> getCompanyProjectPage(CompanyProjectPageReqVO pageReqVO);

}