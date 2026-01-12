package com.ynet.iplatform.module.grid.service.huinongextension;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongextension.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongextension.GridHuinongExtensionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.huinongextension.GridHuinongExtensionMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 惠农网格扩展 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridHuinongExtensionServiceImpl implements GridHuinongExtensionService {

    @Resource
    private GridHuinongExtensionMapper huinongExtensionMapper;

    @Override
    public Long createHuinongExtension(GridHuinongExtensionSaveReqVO createReqVO) {
        // 插入
        GridHuinongExtensionDO huinongExtension = BeanUtils.toBean(createReqVO, GridHuinongExtensionDO.class);
        huinongExtensionMapper.insert(huinongExtension);

        // 返回
        return huinongExtension.getId();
    }

    @Override
    public void updateHuinongExtension(GridHuinongExtensionSaveReqVO updateReqVO) {
        // 校验存在
        validateHuinongExtensionExists(updateReqVO.getId());
        // 更新
        GridHuinongExtensionDO updateObj = BeanUtils.toBean(updateReqVO, GridHuinongExtensionDO.class);
        huinongExtensionMapper.updateById(updateObj);
    }

    @Override
    public void deleteHuinongExtension(Long id) {
        // 校验存在
        validateHuinongExtensionExists(id);
        // 删除
        huinongExtensionMapper.deleteById(id);
    }

    @Override
        public void deleteHuinongExtensionListByIds(List<Long> ids) {
        // 删除
        huinongExtensionMapper.deleteByIds(ids);
        }


    private void validateHuinongExtensionExists(Long id) {
        if (huinongExtensionMapper.selectById(id) == null) {
            throw exception(HUINONG_EXTENSION_NOT_EXISTS);
        }
    }

    @Override
    public GridHuinongExtensionDO getHuinongExtension(Long id) {
        return huinongExtensionMapper.selectById(id);
    }

    @Override
    public PageResult<GridHuinongExtensionDO> getHuinongExtensionPage(GridHuinongExtensionPageReqVO pageReqVO) {
        return huinongExtensionMapper.selectPage(pageReqVO);
    }

}