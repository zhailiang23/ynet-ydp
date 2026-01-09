package com.ynet.iplatform.module.task.service.factor;

import cn.hutool.core.collection.CollUtil;
import com.ynet.iplatform.framework.common.exception.ErrorCode;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.task.controller.admin.factor.vo.*;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringConditionDO;
import com.ynet.iplatform.module.task.dal.dataobject.factor.TaskScoringFactorDO;
import com.ynet.iplatform.module.task.dal.mysql.factor.TaskScoringConditionMapper;
import com.ynet.iplatform.module.task.dal.mysql.factor.TaskScoringFactorMapper;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 任务评分因子 Service 实现类
 *
 * @author ynet
 */
@Service
@Validated
public class TaskScoringFactorServiceImpl implements TaskScoringFactorService {

    @Resource
    private TaskScoringFactorMapper taskScoringFactorMapper;

    @Resource
    private TaskScoringConditionMapper taskScoringConditionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createScoringFactor(@Valid TaskScoringFactorSaveReqVO createReqVO) {
        // 1. 保存评分因子基本信息
        TaskScoringFactorDO factor = BeanUtils.toBean(createReqVO, TaskScoringFactorDO.class);
        taskScoringFactorMapper.insert(factor);

        // 2. 保存条件列表
        if (CollUtil.isNotEmpty(createReqVO.getConditions())) {
            for (TaskScoringConditionSaveReqVO conditionReqVO : createReqVO.getConditions()) {
                TaskScoringConditionDO condition = BeanUtils.toBean(conditionReqVO, TaskScoringConditionDO.class);
                condition.setFactorId(factor.getId());
                taskScoringConditionMapper.insert(condition);
            }
        }

        return factor.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateScoringFactor(@Valid TaskScoringFactorSaveReqVO updateReqVO) {
        // 1. 校验因子存在
        validateScoringFactorExists(updateReqVO.getId());

        // 2. 更新评分因子基本信息
        TaskScoringFactorDO updateObj = BeanUtils.toBean(updateReqVO, TaskScoringFactorDO.class);
        taskScoringFactorMapper.updateById(updateObj);

        // 3. 删除旧条件
        taskScoringConditionMapper.deleteByFactorId(updateReqVO.getId());

        // 4. 插入新条件
        if (CollUtil.isNotEmpty(updateReqVO.getConditions())) {
            for (TaskScoringConditionSaveReqVO conditionReqVO : updateReqVO.getConditions()) {
                TaskScoringConditionDO condition = BeanUtils.toBean(conditionReqVO, TaskScoringConditionDO.class);
                condition.setFactorId(updateReqVO.getId());
                condition.setId(null); // 清空ID，让数据库自动生成新主键
                taskScoringConditionMapper.insert(condition);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteScoringFactor(Long id) {
        // 1. 校验因子存在
        validateScoringFactorExists(id);

        // 2. 删除评分因子
        taskScoringFactorMapper.deleteById(id);

        // 3. 级联删除条件
        taskScoringConditionMapper.deleteByFactorId(id);
    }

    @Override
    public TaskScoringFactorDO getScoringFactor(Long id) {
        return taskScoringFactorMapper.selectById(id);
    }

    @Override
    public PageResult<TaskScoringFactorDO> getScoringFactorPage(TaskScoringFactorPageReqVO pageReqVO) {
        return taskScoringFactorMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TaskScoringFactorDO> getScoringFactorList(TaskScoringFactorListReqVO listReqVO) {
        return taskScoringFactorMapper.selectList(listReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateWeight(@Valid TaskScoringFactorBatchUpdateWeightReqVO batchUpdateReqVO) {
        // 验证权重总和
        int totalWeight = 0;
        for (TaskScoringFactorBatchUpdateWeightReqVO.FactorWeightItem item : batchUpdateReqVO.getFactors()) {
            if (item.getEnabled()) {
                totalWeight += item.getWeight();
            }
        }

        if (totalWeight != 100) {
            throw exception(new ErrorCode(400_000_001, "启用因子的权重总和必须等于100%"));
        }

        // 批量更新
        for (TaskScoringFactorBatchUpdateWeightReqVO.FactorWeightItem item : batchUpdateReqVO.getFactors()) {
            TaskScoringFactorDO updateObj = new TaskScoringFactorDO();
            updateObj.setId(item.getId());
            updateObj.setEnabled(item.getEnabled());
            updateObj.setWeight(item.getWeight());
            taskScoringFactorMapper.updateById(updateObj);
        }
    }

    /**
     * 获取评分因子详情（包含条件）
     *
     * @param id 评分因子ID
     * @return 评分因子详情
     */
    public TaskScoringFactorRespVO getScoringFactorDetail(Long id) {
        // 1. 查询评分因子
        TaskScoringFactorDO factor = taskScoringFactorMapper.selectById(id);
        if (factor == null) {
            return null;
        }

        // 2. 查询条件
        List<TaskScoringConditionDO> conditions = taskScoringConditionMapper.selectListByFactorId(id);

        // 3. 组装返回VO
        TaskScoringFactorRespVO respVO = BeanUtils.toBean(factor, TaskScoringFactorRespVO.class);
        respVO.setConditions(BeanUtils.toBean(conditions, TaskScoringConditionRespVO.class));

        return respVO;
    }

    private void validateScoringFactorExists(Long id) {
        if (taskScoringFactorMapper.selectById(id) == null) {
            throw exception(new ErrorCode(400_000_002, "评分因子不存在"));
        }
    }
}
