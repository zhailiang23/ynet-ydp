package cn.iocoder.yudao.module.aicrm.service.practiceevaluation;

import cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo.PracticeEvaluationRespVO;

/**
 * 练习评估服务接口
 *
 * @author 系统
 */
public interface PracticeEvaluationService {

    /**
     * 评估练习结果
     *
     * @param recordId 练习记录ID
     * @return 评估结果
     */
    PracticeEvaluationRespVO evaluatePractice(Long recordId);
}
