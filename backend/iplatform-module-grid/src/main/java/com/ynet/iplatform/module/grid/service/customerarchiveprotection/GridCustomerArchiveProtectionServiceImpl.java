package com.ynet.iplatform.module.grid.service.customerarchiveprotection;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.customerarchiveprotection.GridCustomerArchiveProtectionDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.customerarchiveprotection.GridCustomerArchiveProtectionMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 客户档案保护记录 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridCustomerArchiveProtectionServiceImpl implements GridCustomerArchiveProtectionService {

    @Resource
    private GridCustomerArchiveProtectionMapper customerArchiveProtectionMapper;

    @Override
    public Long createCustomerArchiveProtection(GridCustomerArchiveProtectionSaveReqVO createReqVO) {
        // 插入
        GridCustomerArchiveProtectionDO customerArchiveProtection = BeanUtils.toBean(createReqVO, GridCustomerArchiveProtectionDO.class);
        customerArchiveProtectionMapper.insert(customerArchiveProtection);

        // 返回
        return customerArchiveProtection.getId();
    }

    @Override
    public void updateCustomerArchiveProtection(GridCustomerArchiveProtectionSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerArchiveProtectionExists(updateReqVO.getId());
        // 更新
        GridCustomerArchiveProtectionDO updateObj = BeanUtils.toBean(updateReqVO, GridCustomerArchiveProtectionDO.class);
        customerArchiveProtectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerArchiveProtection(Long id) {
        // 校验存在
        validateCustomerArchiveProtectionExists(id);
        // 删除
        customerArchiveProtectionMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerArchiveProtectionListByIds(List<Long> ids) {
        // 删除
        customerArchiveProtectionMapper.deleteByIds(ids);
        }


    private void validateCustomerArchiveProtectionExists(Long id) {
        if (customerArchiveProtectionMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ARCHIVE_PROTECTION_NOT_EXISTS);
        }
    }

    @Override
    public GridCustomerArchiveProtectionDO getCustomerArchiveProtection(Long id) {
        return customerArchiveProtectionMapper.selectById(id);
    }

    @Override
    public PageResult<GridCustomerArchiveProtectionDO> getCustomerArchiveProtectionPage(GridCustomerArchiveProtectionPageReqVO pageReqVO) {
        return customerArchiveProtectionMapper.selectPage(pageReqVO);
    }

}