package com.ynet.iplatform.module.aicrm.service.practicescript;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practicescript.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-陪练剧本表（支持版本控制） Service 接口
 *
 * @author 易诚源码
 */
public interface PracticeScriptService {

    /**
     * 创建CRM智能陪练-陪练剧本表（支持版本控制）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeScript(@Valid PracticeScriptSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-陪练剧本表（支持版本控制）
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeScript(@Valid PracticeScriptSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-陪练剧本表（支持版本控制）
     *
     * @param id 编号
     */
    void deletePracticeScript(Long id);

    /**
    * 批量删除CRM智能陪练-陪练剧本表（支持版本控制）
    *
    * @param ids 编号
    */
    void deletePracticeScriptListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-陪练剧本表（支持版本控制）
     *
     * @param id 编号
     * @return CRM智能陪练-陪练剧本表（支持版本控制）
     */
    PracticeScriptDO getPracticeScript(Long id);

    /**
     * 获得CRM智能陪练-陪练剧本表（支持版本控制）分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-陪练剧本表（支持版本控制）分页
     */
    PageResult<PracticeScriptRespVO> getPracticeScriptPage(PracticeScriptPageReqVO pageReqVO);

    /**
     * 获取剧本的版本历史
     *
     * @param scriptNo 剧本编号
     * @return 版本历史列表
     */
    List<PracticeScriptRespVO> getVersionHistory(String scriptNo);

    /**
     * 创建个性化剧本
     *
     * @param name 剧本名称
     * @param description 剧本描述
     * @param difficultyLevel 难度等级
     * @param marketingStep 营销环节
     * @param virtualCustomerId 虚拟客户ID
     * @param materialIds 培训材料ID列表
     * @param caseId 关联案例ID
     * @param skillId 关联技巧ID
     * @param content 剧本内容
     * @return 创建的剧本记录
     */
    PracticeScriptDO createPersonalizedScript(String name, String description, String difficultyLevel,
                                               String marketingStep, Long virtualCustomerId,
                                               String materialIds, Long caseId, Long skillId,
                                               String content);

    /**
     * 同步生成剧本内容
     *
     * @param scriptId 剧本ID
     * @param caseId 关联案例ID
     * @param materialIds 培训材料ID列表
     * @param skillId 关联技巧ID
     * @param marketingStep 营销环节
     * @param difficultyLevel 难度等级
     * @param scriptDescription 剧本描述
     */
    void generateScriptContentSync(Long scriptId, Long caseId, String materialIds,
                                   Long skillId, String marketingStep,
                                   String difficultyLevel, String scriptDescription);

}