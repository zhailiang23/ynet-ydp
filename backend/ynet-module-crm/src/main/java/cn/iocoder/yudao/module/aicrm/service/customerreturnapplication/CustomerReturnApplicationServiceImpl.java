package cn.iocoder.yudao.module.aicrm.service.customerreturnapplication;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerreturnapplication.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerreturnapplication.CustomerReturnApplicationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerreturnapplication.CustomerReturnApplicationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户退回申请 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerReturnApplicationServiceImpl implements CustomerReturnApplicationService {

    @Resource
    private CustomerReturnApplicationMapper customerReturnApplicationMapper;

    @Override
    public Long createCustomerReturnApplication(CustomerReturnApplicationSaveReqVO createReqVO) {
        // 插入
        CustomerReturnApplicationDO customerReturnApplication = BeanUtils.toBean(createReqVO, CustomerReturnApplicationDO.class);
        customerReturnApplicationMapper.insert(customerReturnApplication);

        // 返回
        return customerReturnApplication.getId();
    }

    @Override
    public void updateCustomerReturnApplication(CustomerReturnApplicationSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerReturnApplicationExists(updateReqVO.getId());
        // 更新
        CustomerReturnApplicationDO updateObj = BeanUtils.toBean(updateReqVO, CustomerReturnApplicationDO.class);
        customerReturnApplicationMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerReturnApplication(Long id) {
        // 校验存在
        validateCustomerReturnApplicationExists(id);
        // 删除
        customerReturnApplicationMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerReturnApplicationListByIds(List<Long> ids) {
        // 删除
        customerReturnApplicationMapper.deleteByIds(ids);
        }


    private void validateCustomerReturnApplicationExists(Long id) {
        if (customerReturnApplicationMapper.selectById(id) == null) {
            throw exception(CUSTOMER_RETURN_APPLICATION_NOT_EXISTS);
        }
    }

    @Override
    public CustomerReturnApplicationDO getCustomerReturnApplication(Long id) {
        return customerReturnApplicationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerReturnApplicationDO> getCustomerReturnApplicationPage(CustomerReturnApplicationPageReqVO pageReqVO) {
        return customerReturnApplicationMapper.selectPage(pageReqVO);
    }

}