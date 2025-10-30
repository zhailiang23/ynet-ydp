package cn.iocoder.yudao.module.aicrm.service.customerratinghistory;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerratinghistory.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerratinghistory.CustomerRatingHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 客户评级调整历史 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerRatingHistoryService {

    /**
     * 创建客户评级调整历史
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerRatingHistory(@Valid CustomerRatingHistorySaveReqVO createReqVO);

    /**
     * 更新客户评级调整历史
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerRatingHistory(@Valid CustomerRatingHistorySaveReqVO updateReqVO);

    /**
     * 删除客户评级调整历史
     *
     * @param id 编号
     */
    void deleteCustomerRatingHistory(Long id);

    /**
    * 批量删除客户评级调整历史
    *
    * @param ids 编号
    */
    void deleteCustomerRatingHistoryListByIds(List<Long> ids);

    /**
     * 获得客户评级调整历史
     *
     * @param id 编号
     * @return 客户评级调整历史
     */
    CustomerRatingHistoryDO getCustomerRatingHistory(Long id);

    /**
     * 获得客户评级调整历史分页
     *
     * @param pageReqVO 分页查询
     * @return 客户评级调整历史分页
     */
    PageResult<CustomerRatingHistoryDO> getCustomerRatingHistoryPage(CustomerRatingHistoryPageReqVO pageReqVO);

}