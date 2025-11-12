package cn.iocoder.yudao.module.aicrm.service.practiceuserrecord;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practiceuserrecord.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practiceuserrecord.PracticeUserRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * CRM智能陪练-用户陪练记录 Service 接口
 *
 * @author 芋道源码
 */
public interface PracticeUserRecordService {

    /**
     * 创建CRM智能陪练-用户陪练记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeUserRecord(@Valid PracticeUserRecordSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-用户陪练记录
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeUserRecord(@Valid PracticeUserRecordSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-用户陪练记录
     *
     * @param id 编号
     */
    void deletePracticeUserRecord(Long id);

    /**
    * 批量删除CRM智能陪练-用户陪练记录
    *
    * @param ids 编号
    */
    void deletePracticeUserRecordListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-用户陪练记录
     *
     * @param id 编号
     * @return CRM智能陪练-用户陪练记录
     */
    PracticeUserRecordDO getPracticeUserRecord(Long id);

    /**
     * 获得CRM智能陪练-用户陪练记录分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-用户陪练记录分页
     */
    PageResult<PracticeUserRecordDO> getPracticeUserRecordPage(PracticeUserRecordPageReqVO pageReqVO);

    /**
     * 查询用户未完成的练习记录
     *
     * @param courseId 课程ID
     * @param vcustomerId 虚拟客户ID
     * @param userId 用户ID
     * @return 未完成的练习记录，如果不存在返回 null
     */
    PracticeUserRecordDO findUnfinishedRecord(Long courseId, Long vcustomerId, Long userId);

}