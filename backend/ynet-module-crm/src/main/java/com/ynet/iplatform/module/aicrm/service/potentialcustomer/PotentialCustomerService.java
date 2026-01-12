package com.ynet.iplatform.module.aicrm.service.potentialcustomer;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer.vo.AppPotentialCustomerPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer.vo.AppPotentialCustomerRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.potentialcustomer.PotentialCustomerDO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * AI CRM 潜客管理 Service 接口
 *
 * @author AI CRM Team
 */
public interface PotentialCustomerService {

    /**
     * 创建潜客
     *
     * @param createReqVO 创建信息
     * @return 潜客编号
     */
    Long createPotentialCustomer(@Valid PotentialCustomerSaveReqVO createReqVO);

    /**
     * 更新潜客
     *
     * @param updateReqVO 更新信息
     */
    void updatePotentialCustomer(@Valid PotentialCustomerSaveReqVO updateReqVO);

    /**
     * 删除潜客
     *
     * @param id 潜客编号
     */
    void deletePotentialCustomer(Long id);

    /**
     * 批量删除潜客
     *
     * @param ids 潜客编号数组
     */
    void deletePotentialCustomerList(List<Long> ids);

    /**
     * 获得潜客
     *
     * @param id 潜客编号
     * @return 潜客
     */
    PotentialCustomerDO getPotentialCustomer(Long id);

    /**
     * 获得潜客分页（管理端）
     *
     * @param pageReqVO 分页查询
     * @return 潜客分页
     */
    PageResult<PotentialCustomerDO> getPotentialCustomerPage(PotentialCustomerPageReqVO pageReqVO);

    /**
     * 获得潜客列表（移动端）
     *
     * @param pageReqVO 分页查询
     * @return 潜客分页
     */
    PageResult<AppPotentialCustomerRespVO> getAppPotentialCustomerPage(AppPotentialCustomerPageReqVO pageReqVO);

    /**
     * 获得潜客详情（移动端）
     *
     * @param id 潜客编号
     * @return 潜客详情
     */
    AppPotentialCustomerRespVO getAppPotentialCustomer(Long id);

}
