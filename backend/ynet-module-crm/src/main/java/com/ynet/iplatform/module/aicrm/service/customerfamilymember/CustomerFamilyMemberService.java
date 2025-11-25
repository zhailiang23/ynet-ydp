package com.ynet.iplatform.module.aicrm.service.customerfamilymember;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customerfamilymember.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customerfamilymember.CustomerFamilyMemberDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 客户家庭成员信息表（零售客户） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerFamilyMemberService {

    /**
     * 创建客户家庭成员信息表（零售客户）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerFamilyMember(@Valid CustomerFamilyMemberSaveReqVO createReqVO);

    /**
     * 更新客户家庭成员信息表（零售客户）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerFamilyMember(@Valid CustomerFamilyMemberSaveReqVO updateReqVO);

    /**
     * 删除客户家庭成员信息表（零售客户）
     *
     * @param id 编号
     */
    void deleteCustomerFamilyMember(Long id);

    /**
    * 批量删除客户家庭成员信息表（零售客户）
    *
    * @param ids 编号
    */
    void deleteCustomerFamilyMemberListByIds(List<Long> ids);

    /**
     * 获得客户家庭成员信息表（零售客户）
     *
     * @param id 编号
     * @return 客户家庭成员信息表（零售客户）
     */
    CustomerFamilyMemberDO getCustomerFamilyMember(Long id);

    /**
     * 获得客户家庭成员信息表（零售客户）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户家庭成员信息表（零售客户）分页
     */
    PageResult<CustomerFamilyMemberDO> getCustomerFamilyMemberPage(CustomerFamilyMemberPageReqVO pageReqVO);

}