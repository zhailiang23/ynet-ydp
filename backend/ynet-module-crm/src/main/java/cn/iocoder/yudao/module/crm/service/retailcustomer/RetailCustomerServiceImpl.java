package cn.iocoder.yudao.module.crm.service.retailcustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.crm.controller.admin.retailcustomer.vo.*;
import cn.iocoder.yudao.module.crm.dal.dataobject.retailcustomer.RetailCustomerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.crm.dal.mysql.retailcustomer.RetailCustomerMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.crm.enums.ErrorCodeConstants.*;

/**
 * CRM零售客户扩展表(零售客户特有基本信息) Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RetailCustomerServiceImpl implements RetailCustomerService {

    @Resource
    private RetailCustomerMapper retailCustomerMapper;

    @Override
    public Long createRetailCustomer(RetailCustomerSaveReqVO createReqVO) {
        // 插入
        RetailCustomerDO retailCustomer = BeanUtils.toBean(createReqVO, RetailCustomerDO.class);
        retailCustomerMapper.insert(retailCustomer);

        // 返回
        return retailCustomer.getId();
    }

    @Override
    public void updateRetailCustomer(RetailCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateRetailCustomerExists(updateReqVO.getId());
        // 更新
        RetailCustomerDO updateObj = BeanUtils.toBean(updateReqVO, RetailCustomerDO.class);
        retailCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteRetailCustomer(Long id) {
        // 校验存在
        validateRetailCustomerExists(id);
        // 删除
        retailCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteRetailCustomerListByIds(List<Long> ids) {
        // 删除
        retailCustomerMapper.deleteByIds(ids);
        }


    private void validateRetailCustomerExists(Long id) {
        if (retailCustomerMapper.selectById(id) == null) {
            throw exception(RETAIL_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public RetailCustomerDO getRetailCustomer(Long id) {
        return retailCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<RetailCustomerDO> getRetailCustomerPage(RetailCustomerPageReqVO pageReqVO) {
        return retailCustomerMapper.selectPage(pageReqVO);
    }

}