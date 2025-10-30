package cn.iocoder.yudao.module.aicrm.service.companystock;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.companystock.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companystock.CompanyStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 对公客户股票发行人信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CompanyStockService {

    /**
     * 创建对公客户股票发行人信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompanyStock(@Valid CompanyStockSaveReqVO createReqVO);

    /**
     * 更新对公客户股票发行人信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompanyStock(@Valid CompanyStockSaveReqVO updateReqVO);

    /**
     * 删除对公客户股票发行人信息
     *
     * @param id 编号
     */
    void deleteCompanyStock(Long id);

    /**
    * 批量删除对公客户股票发行人信息
    *
    * @param ids 编号
    */
    void deleteCompanyStockListByIds(List<Long> ids);

    /**
     * 获得对公客户股票发行人信息
     *
     * @param id 编号
     * @return 对公客户股票发行人信息
     */
    CompanyStockDO getCompanyStock(Long id);

    /**
     * 获得对公客户股票发行人信息分页
     *
     * @param pageReqVO 分页查询
     * @return 对公客户股票发行人信息分页
     */
    PageResult<CompanyStockDO> getCompanyStockPage(CompanyStockPageReqVO pageReqVO);

}