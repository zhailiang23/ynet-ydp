package com.ynet.iplatform.module.twins.service.chatcollectinfo;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.twins.controller.admin.chatcollectinfo.vo.*;
import com.ynet.iplatform.module.twins.dal.dataobject.chatcollectinfo.ChatCollectInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.twins.dal.mysql.chatcollectinfo.ChatCollectInfoMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.twins.enums.ErrorCodeConstants.*;

/**
 * 客户留资信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class ChatCollectInfoServiceImpl implements ChatCollectInfoService {

    @Resource
    private ChatCollectInfoMapper chatCollectInfoMapper;

    @Override
    public Integer createChatCollectInfo(ChatCollectInfoSaveReqVO createReqVO) {
        // 插入
        ChatCollectInfoDO chatCollectInfo = BeanUtils.toBean(createReqVO, ChatCollectInfoDO.class);
        chatCollectInfoMapper.insert(chatCollectInfo);

        // 返回
        return chatCollectInfo.getId();
    }

    @Override
    public void updateChatCollectInfo(ChatCollectInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateChatCollectInfoExists(updateReqVO.getId());
        // 更新
        ChatCollectInfoDO updateObj = BeanUtils.toBean(updateReqVO, ChatCollectInfoDO.class);
        chatCollectInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteChatCollectInfo(Integer id) {
        // 校验存在
        validateChatCollectInfoExists(id);
        // 删除
        chatCollectInfoMapper.deleteById(id);
    }

    @Override
        public void deleteChatCollectInfoListByIds(List<Integer> ids) {
        // 删除
        chatCollectInfoMapper.deleteByIds(ids);
        }


    private void validateChatCollectInfoExists(Integer id) {
        if (chatCollectInfoMapper.selectById(id) == null) {
            throw exception(CHAT_COLLECT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public ChatCollectInfoDO getChatCollectInfo(Integer id) {
        return chatCollectInfoMapper.selectById(id);
    }

    @Override
    public PageResult<ChatCollectInfoDO> getChatCollectInfoPage(ChatCollectInfoPageReqVO pageReqVO) {
        return chatCollectInfoMapper.selectPage(pageReqVO);
    }

}