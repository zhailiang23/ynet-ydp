package com.ynet.iplatform.module.grid.service.communitycustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.communitycustomer.GridCommunityCustomerDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.communitycustomer.GridCommunityCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 社区客户扩展 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridCommunityCustomerServiceImpl implements GridCommunityCustomerService {

    @Resource
    private GridCommunityCustomerMapper communityCustomerMapper;

    @Override
    public Long createCommunityCustomer(GridCommunityCustomerSaveReqVO createReqVO) {
        // 插入
        GridCommunityCustomerDO communityCustomer = BeanUtils.toBean(createReqVO, GridCommunityCustomerDO.class);
        communityCustomerMapper.insert(communityCustomer);

        // 返回
        return communityCustomer.getId();
    }

    @Override
    public void updateCommunityCustomer(GridCommunityCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCommunityCustomerExists(updateReqVO.getId());
        // 更新
        GridCommunityCustomerDO updateObj = BeanUtils.toBean(updateReqVO, GridCommunityCustomerDO.class);
        communityCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommunityCustomer(Long id) {
        // 校验存在
        validateCommunityCustomerExists(id);
        // 删除
        communityCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteCommunityCustomerListByIds(List<Long> ids) {
        // 删除
        communityCustomerMapper.deleteByIds(ids);
        }


    private void validateCommunityCustomerExists(Long id) {
        if (communityCustomerMapper.selectById(id) == null) {
            throw exception(COMMUNITY_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public GridCommunityCustomerDO getCommunityCustomer(Long id) {
        return communityCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<GridCommunityCustomerDO> getCommunityCustomerPage(GridCommunityCustomerPageReqVO pageReqVO) {
        return communityCustomerMapper.selectPage(pageReqVO);
    }

}