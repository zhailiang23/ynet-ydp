package cn.iocoder.yudao.module.aicrm.service.practiceskill;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceskill.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceskill.PracticeSkillDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-销售技巧 Service 接口
 *
 * @author 芋道源码
 */
public interface PracticeSkillService {

    /**
     * 创建CRM智能陪练-销售技巧
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeSkill(@Valid PracticeSkillSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-销售技巧
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeSkill(@Valid PracticeSkillSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-销售技巧
     *
     * @param id 编号
     */
    void deletePracticeSkill(Long id);

    /**
    * 批量删除CRM智能陪练-销售技巧
    *
    * @param ids 编号
    */
    void deletePracticeSkillListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-销售技巧
     *
     * @param id 编号
     * @return CRM智能陪练-销售技巧
     */
    PracticeSkillDO getPracticeSkill(Long id);

    /**
     * 获得CRM智能陪练-销售技巧分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-销售技巧分页
     */
    PageResult<PracticeSkillDO> getPracticeSkillPage(PracticeSkillPageReqVO pageReqVO);

}