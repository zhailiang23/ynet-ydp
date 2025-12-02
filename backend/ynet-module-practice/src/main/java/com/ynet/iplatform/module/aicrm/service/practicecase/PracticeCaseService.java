package com.ynet.iplatform.module.aicrm.service.practicecase;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practicecase.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-销售案例 Service 接口
 *
 * @author 易诚源码
 */
public interface PracticeCaseService {

    /**
     * 创建CRM智能陪练-销售案例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeCase(@Valid PracticeCaseSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-销售案例
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeCase(@Valid PracticeCaseSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-销售案例
     *
     * @param id 编号
     */
    void deletePracticeCase(Long id);

    /**
    * 批量删除CRM智能陪练-销售案例
    *
    * @param ids 编号
    */
    void deletePracticeCaseListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-销售案例
     *
     * @param id 编号
     * @return CRM智能陪练-销售案例
     */
    PracticeCaseDO getPracticeCase(Long id);

    /**
     * 获得CRM智能陪练-销售案例分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-销售案例分页
     */
    PageResult<PracticeCaseDO> getPracticeCasePage(PracticeCasePageReqVO pageReqVO);

}