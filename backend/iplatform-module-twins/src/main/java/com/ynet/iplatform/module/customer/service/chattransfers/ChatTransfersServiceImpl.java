package com.ynet.iplatform.module.customer.service.chattransfers;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.customer.controller.admin.chattransfers.vo.*;
import com.ynet.iplatform.module.customer.dal.dataobject.chattransfers.ChatTransfersDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.customer.dal.mysql.chattransfers.ChatTransfersMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.customer.enums.ErrorCodeConstants.*;

/**
 * 转接记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ChatTransfersServiceImpl implements ChatTransfersService {

    @Resource
    private ChatTransfersMapper chatTransfersMapper;

    @Override
    public Integer createChatTransfers(ChatTransfersSaveReqVO createReqVO) {
        // 插入
        ChatTransfersDO chatTransfers = BeanUtils.toBean(createReqVO, ChatTransfersDO.class);
        chatTransfersMapper.insert(chatTransfers);

        // 返回
        return chatTransfers.getId();
    }

    @Override
    public void updateChatTransfers(ChatTransfersSaveReqVO updateReqVO) {
        // 校验存在
        validateChatTransfersExists(updateReqVO.getId());
        // 更新
        ChatTransfersDO updateObj = BeanUtils.toBean(updateReqVO, ChatTransfersDO.class);
        chatTransfersMapper.updateById(updateObj);
    }

    @Override
    public void deleteChatTransfers(Integer id) {
        // 校验存在
        validateChatTransfersExists(id);
        // 删除
        chatTransfersMapper.deleteById(id);
    }

    @Override
        public void deleteChatTransfersListByIds(List<Integer> ids) {
        // 删除
        chatTransfersMapper.deleteByIds(ids);
        }


    private void validateChatTransfersExists(Integer id) {
        if (chatTransfersMapper.selectById(id) == null) {
            throw exception(CHAT_TRANSFERS_NOT_EXISTS);
        }
    }

    @Override
    public ChatTransfersDO getChatTransfers(Integer id) {
        return chatTransfersMapper.selectById(id);
    }

    @Override
    public PageResult<ChatTransfersDO> getChatTransfersPage(ChatTransfersPageReqVO pageReqVO) {
        return chatTransfersMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ChatTransfersRespVO> getChatTransfersPageWithNames(ChatTransfersPageReqVO pageReqVO) {
        // 创建分页对象
        Page<ChatTransfersRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 执行分页查询
        IPage<ChatTransfersRespVO> result = chatTransfersMapper.selectPageWithNames(
                page,
                pageReqVO.getUserName(),
                pageReqVO.getFromAdminName(),
                pageReqVO.getToAdminName(),
                pageReqVO.getRemark()
        );

        // 转换为 PageResult
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

}