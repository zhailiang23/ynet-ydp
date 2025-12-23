package com.ynet.iplatform.module.grid.service.competitorinfo;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.competitorinfo.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.competitorinfo.CompetitorInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 同业信息 Service 接口
 *
 * @author 易诚原生智能平台
 */
public interface CompetitorInfoService {

    /**
     * 创建同业信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompetitorInfo(@Valid CompetitorInfoSaveReqVO createReqVO);

    /**
     * 更新同业信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompetitorInfo(@Valid CompetitorInfoSaveReqVO updateReqVO);

    /**
     * 删除同业信息
     *
     * @param id 编号
     */
    void deleteCompetitorInfo(Long id);

    /**
    * 批量删除同业信息
    *
    * @param ids 编号
    */
    void deleteCompetitorInfoListByIds(List<Long> ids);

    /**
     * 获得同业信息
     *
     * @param id 编号
     * @return 同业信息
     */
    CompetitorInfoDO getCompetitorInfo(Long id);

    /**
     * 获得同业信息分页
     *
     * @param pageReqVO 分页查询
     * @return 同业信息分页
     */
    PageResult<CompetitorInfoDO> getCompetitorInfoPage(CompetitorInfoPageReqVO pageReqVO);

}