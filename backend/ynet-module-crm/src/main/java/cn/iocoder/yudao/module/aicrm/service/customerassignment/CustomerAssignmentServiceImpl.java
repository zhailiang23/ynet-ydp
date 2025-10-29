package cn.iocoder.yudao.module.aicrm.service.customerassignment;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassignment.CustomerAssignmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerassignment.CustomerAssignmentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户归属关系表（零售+对公共用，支持主协办模式） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerAssignmentServiceImpl implements CustomerAssignmentService {

    @Resource
    private CustomerAssignmentMapper customerAssignmentMapper;

    @Override
    public Long createCustomerAssignment(CustomerAssignmentSaveReqVO createReqVO) {
        // 插入
        CustomerAssignmentDO customerAssignment = BeanUtils.toBean(createReqVO, CustomerAssignmentDO.class);
        customerAssignmentMapper.insert(customerAssignment);

        // 返回
        return customerAssignment.getId();
    }

    @Override
    public void updateCustomerAssignment(CustomerAssignmentSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerAssignmentExists(updateReqVO.getId());
        // 更新
        CustomerAssignmentDO updateObj = BeanUtils.toBean(updateReqVO, CustomerAssignmentDO.class);
        customerAssignmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerAssignment(Long id) {
        // 校验存在
        validateCustomerAssignmentExists(id);
        // 删除
        customerAssignmentMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerAssignmentListByIds(List<Long> ids) {
        // 删除
        customerAssignmentMapper.deleteByIds(ids);
        }


    private void validateCustomerAssignmentExists(Long id) {
        if (customerAssignmentMapper.selectById(id) == null) {
            throw exception(CUSTOMER_ASSIGNMENT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerAssignmentDO getCustomerAssignment(Long id) {
        return customerAssignmentMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerAssignmentDO> getCustomerAssignmentPage(CustomerAssignmentPageReqVO pageReqVO) {
        return customerAssignmentMapper.selectPage(pageReqVO);
    }

}