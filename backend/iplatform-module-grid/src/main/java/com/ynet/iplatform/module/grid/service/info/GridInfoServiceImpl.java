package com.ynet.iplatform.module.grid.service.info;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.info.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.info.GridInfoMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 网格信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridInfoServiceImpl implements GridInfoService {

    @Resource
    private GridInfoMapper infoMapper;

    @Override
    public Long createInfo(GridInfoSaveReqVO createReqVO) {
        // 插入
        GridInfoDO info = BeanUtils.toBean(createReqVO, GridInfoDO.class);
        infoMapper.insert(info);

        // 返回
        return info.getId();
    }

    @Override
    public void updateInfo(GridInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateInfoExists(updateReqVO.getId());
        // 更新
        GridInfoDO updateObj = BeanUtils.toBean(updateReqVO, GridInfoDO.class);
        infoMapper.updateById(updateObj);
    }

    @Override
    public void deleteInfo(Long id) {
        // 校验存在
        validateInfoExists(id);
        // 删除
        infoMapper.deleteById(id);
    }

    @Override
        public void deleteInfoListByIds(List<Long> ids) {
        // 删除
        infoMapper.deleteByIds(ids);
        }


    private void validateInfoExists(Long id) {
        if (infoMapper.selectById(id) == null) {
            throw exception(INFO_NOT_EXISTS);
        }
    }

    @Override
    public GridInfoDO getInfo(Long id) {
        return infoMapper.selectById(id);
    }

    @Override
    public PageResult<GridInfoDO> getInfoPage(GridInfoPageReqVO pageReqVO) {
        return infoMapper.selectPage(pageReqVO);
    }

}