package com.ynet.iplatform.module.grid.service.zerodaimarketdescription;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.zerodaimarketdescription.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.zerodaimarketdescription.GridZerodaiMarketDescriptionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.zerodaimarketdescription.GridZerodaiMarketDescriptionMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 零贷市场描述 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridZerodaiMarketDescriptionServiceImpl implements GridZerodaiMarketDescriptionService {

    @Resource
    private GridZerodaiMarketDescriptionMapper zerodaiMarketDescriptionMapper;

    @Override
    public Long createZerodaiMarketDescription(GridZerodaiMarketDescriptionSaveReqVO createReqVO) {
        // 插入
        GridZerodaiMarketDescriptionDO zerodaiMarketDescription = BeanUtils.toBean(createReqVO, GridZerodaiMarketDescriptionDO.class);
        zerodaiMarketDescriptionMapper.insert(zerodaiMarketDescription);

        // 返回
        return zerodaiMarketDescription.getId();
    }

    @Override
    public void updateZerodaiMarketDescription(GridZerodaiMarketDescriptionSaveReqVO updateReqVO) {
        // 校验存在
        validateZerodaiMarketDescriptionExists(updateReqVO.getId());
        // 更新
        GridZerodaiMarketDescriptionDO updateObj = BeanUtils.toBean(updateReqVO, GridZerodaiMarketDescriptionDO.class);
        zerodaiMarketDescriptionMapper.updateById(updateObj);
    }

    @Override
    public void deleteZerodaiMarketDescription(Long id) {
        // 校验存在
        validateZerodaiMarketDescriptionExists(id);
        // 删除
        zerodaiMarketDescriptionMapper.deleteById(id);
    }

    @Override
        public void deleteZerodaiMarketDescriptionListByIds(List<Long> ids) {
        // 删除
        zerodaiMarketDescriptionMapper.deleteByIds(ids);
        }


    private void validateZerodaiMarketDescriptionExists(Long id) {
        if (zerodaiMarketDescriptionMapper.selectById(id) == null) {
            throw exception(ZERODAI_MARKET_DESCRIPTION_NOT_EXISTS);
        }
    }

    @Override
    public GridZerodaiMarketDescriptionDO getZerodaiMarketDescription(Long id) {
        return zerodaiMarketDescriptionMapper.selectById(id);
    }

    @Override
    public PageResult<GridZerodaiMarketDescriptionDO> getZerodaiMarketDescriptionPage(GridZerodaiMarketDescriptionPageReqVO pageReqVO) {
        return zerodaiMarketDescriptionMapper.selectPage(pageReqVO);
    }

}