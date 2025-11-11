package cn.iocoder.yudao.module.aicrm.service.practiceconversation;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceconversation.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceconversation.PracticeConversationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-陪练对话 Service 接口
 *
 * @author 芋道源码
 */
public interface PracticeConversationService {

    /**
     * 创建CRM智能陪练-陪练对话
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeConversation(@Valid PracticeConversationSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-陪练对话
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeConversation(@Valid PracticeConversationSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-陪练对话
     *
     * @param id 编号
     */
    void deletePracticeConversation(Long id);

    /**
    * 批量删除CRM智能陪练-陪练对话
    *
    * @param ids 编号
    */
    void deletePracticeConversationListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-陪练对话
     *
     * @param id 编号
     * @return CRM智能陪练-陪练对话
     */
    PracticeConversationDO getPracticeConversation(Long id);

    /**
     * 获得CRM智能陪练-陪练对话分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-陪练对话分页
     */
    PageResult<PracticeConversationDO> getPracticeConversationPage(PracticeConversationPageReqVO pageReqVO);

    /**
     * 根据陪练记录ID获取对话列表
     *
     * @param recordId 陪练记录ID
     * @return 对话列表(按序号排序)
     */
    List<PracticeConversationDO> getConversationListByRecordId(Long recordId);

}