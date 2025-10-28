package cn.iocoder.yudao.module.aicrm.service.customeridentity;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customeridentity.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customeridentity.CustomerIdentityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户证件信息表（零售客户特有，1对多关系，支持多证件） Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerIdentityService {

    /**
     * 创建客户证件信息表（零售客户特有，1对多关系，支持多证件）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerIdentity(@Valid CustomerIdentitySaveReqVO createReqVO);

    /**
     * 更新客户证件信息表（零售客户特有，1对多关系，支持多证件）
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerIdentity(@Valid CustomerIdentitySaveReqVO updateReqVO);

    /**
     * 删除客户证件信息表（零售客户特有，1对多关系，支持多证件）
     *
     * @param id 编号
     */
    void deleteCustomerIdentity(Long id);

    /**
    * 批量删除客户证件信息表（零售客户特有，1对多关系，支持多证件）
    *
    * @param ids 编号
    */
    void deleteCustomerIdentityListByIds(List<Long> ids);

    /**
     * 获得客户证件信息表（零售客户特有，1对多关系，支持多证件）
     *
     * @param id 编号
     * @return 客户证件信息表（零售客户特有，1对多关系，支持多证件）
     */
    CustomerIdentityDO getCustomerIdentity(Long id);

    /**
     * 获得客户证件信息表（零售客户特有，1对多关系，支持多证件）分页
     *
     * @param pageReqVO 分页查询
     * @return 客户证件信息表（零售客户特有，1对多关系，支持多证件）分页
     */
    PageResult<CustomerIdentityDO> getCustomerIdentityPage(CustomerIdentityPageReqVO pageReqVO);

}