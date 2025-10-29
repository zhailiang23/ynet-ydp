package cn.iocoder.yudao.module.aicrm.service.customerwork;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerwork.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerwork.CustomerWorkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerwork.CustomerWorkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户工作或经营信息表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerWorkServiceImpl implements CustomerWorkService {

    @Resource
    private CustomerWorkMapper customerWorkMapper;

    @Override
    public Long createCustomerWork(CustomerWorkSaveReqVO createReqVO) {
        // 插入
        CustomerWorkDO customerWork = BeanUtils.toBean(createReqVO, CustomerWorkDO.class);
        customerWorkMapper.insert(customerWork);

        // 返回
        return customerWork.getId();
    }

    @Override
    public void updateCustomerWork(CustomerWorkSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerWorkExists(updateReqVO.getId());
        // 更新
        CustomerWorkDO updateObj = BeanUtils.toBean(updateReqVO, CustomerWorkDO.class);
        customerWorkMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerWork(Long id) {
        // 校验存在
        validateCustomerWorkExists(id);
        // 删除
        customerWorkMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerWorkListByIds(List<Long> ids) {
        // 删除
        customerWorkMapper.deleteByIds(ids);
        }


    private void validateCustomerWorkExists(Long id) {
        if (customerWorkMapper.selectById(id) == null) {
            throw exception(CUSTOMER_WORK_NOT_EXISTS);
        }
    }

    @Override
    public CustomerWorkDO getCustomerWork(Long id) {
        return customerWorkMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerWorkDO> getCustomerWorkPage(CustomerWorkPageReqVO pageReqVO) {
        return customerWorkMapper.selectPage(pageReqVO);
    }

}