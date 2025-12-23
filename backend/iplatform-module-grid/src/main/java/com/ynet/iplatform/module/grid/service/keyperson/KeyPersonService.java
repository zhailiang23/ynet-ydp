package com.ynet.iplatform.module.grid.service.keyperson;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.grid.controller.admin.keyperson.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.keyperson.KeyPersonDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 关键人信息 Service 接口
 *
 * @author 易诚原生智能平台
 */
public interface KeyPersonService {

    /**
     * 创建关键人信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createKeyPerson(@Valid KeyPersonSaveReqVO createReqVO);

    /**
     * 更新关键人信息
     *
     * @param updateReqVO 更新信息
     */
    void updateKeyPerson(@Valid KeyPersonSaveReqVO updateReqVO);

    /**
     * 删除关键人信息
     *
     * @param id 编号
     */
    void deleteKeyPerson(Long id);

    /**
    * 批量删除关键人信息
    *
    * @param ids 编号
    */
    void deleteKeyPersonListByIds(List<Long> ids);

    /**
     * 获得关键人信息
     *
     * @param id 编号
     * @return 关键人信息
     */
    KeyPersonDO getKeyPerson(Long id);

    /**
     * 获得关键人信息分页
     *
     * @param pageReqVO 分页查询
     * @return 关键人信息分页
     */
    PageResult<KeyPersonDO> getKeyPersonPage(KeyPersonPageReqVO pageReqVO);

}