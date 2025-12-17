package com.ynet.iplatform.module.grid.service.huinongcustomerloan;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongcustomerloan.GridHuinongCustomerLoanDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 惠农贷款客户扩展 Service 接口
 *
 * @author 易诚源码
 */
public interface GridHuinongCustomerLoanService {

    /**
     * 创建惠农贷款客户扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHuinongCustomerLoan(@Valid GridHuinongCustomerLoanSaveReqVO createReqVO);

    /**
     * 更新惠农贷款客户扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateHuinongCustomerLoan(@Valid GridHuinongCustomerLoanSaveReqVO updateReqVO);

    /**
     * 删除惠农贷款客户扩展
     *
     * @param id 编号
     */
    void deleteHuinongCustomerLoan(Long id);

    /**
    * 批量删除惠农贷款客户扩展
    *
    * @param ids 编号
    */
    void deleteHuinongCustomerLoanListByIds(List<Long> ids);

    /**
     * 获得惠农贷款客户扩展（包含关联的客户信息和站点信息）
     *
     * @param id 编号
     * @return 惠农贷款客户扩展
     */
    GridHuinongCustomerLoanRespVO getHuinongCustomerLoan(Long id);

    /**
     * 获得惠农贷款客户扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 惠农贷款客户扩展分页
     */
    PageResult<GridHuinongCustomerLoanRespVO> getHuinongCustomerLoanPage(GridHuinongCustomerLoanPageReqVO pageReqVO);

}