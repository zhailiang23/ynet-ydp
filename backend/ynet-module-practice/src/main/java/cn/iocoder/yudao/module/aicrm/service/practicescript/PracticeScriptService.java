package cn.iocoder.yudao.module.aicrm.service.practicescript;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicescript.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-陪练剧本表（支持版本控制） Service 接口
 *
 * @author 芋道源码
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

}