package com.ynet.iplatform.module.infra.service.externalagent;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.infra.controller.admin.externalagent.vo.*;
import com.ynet.iplatform.module.infra.dal.dataobject.externalagent.ExternalAgentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.infra.dal.mysql.externalagent.ExternalAgentMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.infra.enums.ErrorCodeConstants.*;

/**
 * 外部智能体管理 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class ExternalAgentServiceImpl implements ExternalAgentService {

    @Resource
    private ExternalAgentMapper externalAgentMapper;

    @Override
    public Long createExternalAgent(ExternalAgentSaveReqVO createReqVO) {
        // 插入
        ExternalAgentDO externalAgent = BeanUtils.toBean(createReqVO, ExternalAgentDO.class);
        externalAgentMapper.insert(externalAgent);

        // 返回
        return externalAgent.getId();
    }

    @Override
    public void updateExternalAgent(ExternalAgentSaveReqVO updateReqVO) {
        // 校验存在
        validateExternalAgentExists(updateReqVO.getId());
        // 更新
        ExternalAgentDO updateObj = BeanUtils.toBean(updateReqVO, ExternalAgentDO.class);
        externalAgentMapper.updateById(updateObj);
    }

    @Override
    public void deleteExternalAgent(Long id) {
        // 校验存在
        validateExternalAgentExists(id);
        // 删除
        externalAgentMapper.deleteById(id);
    }

    @Override
        public void deleteExternalAgentListByIds(List<Long> ids) {
        // 删除
        externalAgentMapper.deleteByIds(ids);
        }


    private void validateExternalAgentExists(Long id) {
        if (externalAgentMapper.selectById(id) == null) {
            throw exception(EXTERNAL_AGENT_NOT_EXISTS);
        }
    }

    @Override
    public ExternalAgentDO getExternalAgent(Long id) {
        return externalAgentMapper.selectById(id);
    }

    @Override
    public PageResult<ExternalAgentDO> getExternalAgentPage(ExternalAgentPageReqVO pageReqVO) {
        return externalAgentMapper.selectPage(pageReqVO);
    }

    @Override
    public ExternalAgentDO getExternalAgentByCode(String code) {
        return externalAgentMapper.selectByCode(code);
    }

    @Override
    public List<ExternalAgentDO> getExternalAgentList() {
        return externalAgentMapper.selectList();
    }

}