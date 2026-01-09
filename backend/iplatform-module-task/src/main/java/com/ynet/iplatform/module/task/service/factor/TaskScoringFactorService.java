package com.ynet.iplatform.module.task.service.factor;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorBatchUpdateWeightReqVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorListReqVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorPageReqVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorRespVO;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.TaskScoringFactorSaveReqVO;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringFactorDO;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 任务评分因子 Service 接口
 *
 * @author ynet
 */
public interface TaskScoringFactorService {

    /**
     * 创建评分因子
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createScoringFactor(@Valid TaskScoringFactorSaveReqVO createReqVO);

    /**
     * 更新评分因子
     *
     * @param updateReqVO 更新信息
     */
    void updateScoringFactor(@Valid TaskScoringFactorSaveReqVO updateReqVO);

    /**
     * 删除评分因子
     *
     * @param id 编号
     */
    void deleteScoringFactor(Long id);

    /**
     * 获得评分因子详情
     *
     * @param id 编号
     * @return 评分因子
     */
    TaskScoringFactorDO getScoringFactor(Long id);

    /**
     * 获得评分因子分页
     *
     * @param pageReqVO 分页查询
     * @return 评分因子分页
     */
    PageResult<TaskScoringFactorDO> getScoringFactorPage(TaskScoringFactorPageReqVO pageReqVO);

    /**
     * 获得评分因子列表
     *
     * @param listReqVO 列表查询
     * @return 评分因子列表
     */
    List<TaskScoringFactorDO> getScoringFactorList(TaskScoringFactorListReqVO listReqVO);

    /**
     * 批量更新因子权重
     *
     * @param batchUpdateReqVO 批量更新信息
     */
    void batchUpdateWeight(@Valid TaskScoringFactorBatchUpdateWeightReqVO batchUpdateReqVO);

    /**
     * 获取评分因子详情（包含条件）
     *
     * @param id 评分因子ID
     * @return 评分因子详情
     */
    TaskScoringFactorRespVO getScoringFactorDetail(Long id);
}
