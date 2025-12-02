package com.ynet.iplatform.module.knowledge.service.base;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.knowledge.controller.admin.base.vo.*;
import com.ynet.iplatform.module.knowledge.dal.dataobject.base.KnowledgeBaseDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.knowledge.dal.mysql.base.KnowledgeBaseMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.ynet.iplatform.module.knowledge.enums.ErrorCodeConstants.*;

/**
 * 知识库 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    @Resource
    private KnowledgeBaseMapper baseMapper;

    @Override
    public Long createBase(KnowledgeBaseSaveReqVO createReqVO) {
        // 插入
        KnowledgeBaseDO base = BeanUtils.toBean(createReqVO, KnowledgeBaseDO.class);
        // 根据知识库类型设置所有者：公共知识库 owner_id 为 null，个人知识库为当前用户 ID
        // 如果未指定 isPublic，默认为 true（公共知识库）
        if (Boolean.FALSE.equals(createReqVO.getIsPublic())) {
            base.setOwnerId(getLoginUserId());  // 个人知识库
        } else {
            base.setOwnerId(null);  // 公共知识库（默认）
        }
        baseMapper.insert(base);

        // 返回
        return base.getId();
    }

    @Override
    public void updateBase(KnowledgeBaseSaveReqVO updateReqVO) {
        // 校验存在
        validateBaseExists(updateReqVO.getId());
        // 更新
        KnowledgeBaseDO updateObj = BeanUtils.toBean(updateReqVO, KnowledgeBaseDO.class);
        baseMapper.updateById(updateObj);
    }

    @Override
    public void deleteBase(Long id) {
        // 校验存在
        validateBaseExists(id);
        // 删除
        baseMapper.deleteById(id);
    }

    @Override
        public void deleteBaseListByIds(List<Long> ids) {
        // 删除
        baseMapper.deleteByIds(ids);
        }


    private void validateBaseExists(Long id) {
        if (baseMapper.selectById(id) == null) {
            throw exception(BASE_NOT_EXISTS);
        }
    }

    @Override
    public KnowledgeBaseDO getBase(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public PageResult<KnowledgeBaseDO> getBasePage(KnowledgeBasePageReqVO pageReqVO) {
        return baseMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public KnowledgeBaseDO getOrCreatePersonalBase() {
        // 获取当前登录用户 ID
        Long userId = getLoginUserId();

        // 查询该用户的个人知识库
        KnowledgeBaseDO personalBase = baseMapper.selectOneByOwnerId(userId);

        // 如果不存在，则创建
        if (personalBase == null) {
            personalBase = new KnowledgeBaseDO();
            personalBase.setName("我的知识库");
            personalBase.setDescription("个人知识库");
            personalBase.setOwnerId(userId);
            personalBase.setStatus(0); // 正常状态
            baseMapper.insert(personalBase);
        }

        return personalBase;
    }

}