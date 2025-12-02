package com.ynet.iplatform.module.aicrm.service.customergroupassignment;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.customergroupassignment.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.customergroupassignment.CustomerGroupAssignmentDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.customergroupassignment.CustomerGroupAssignmentMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户归属客群关系表（只记录关系，客群信息通过关联查询） Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CustomerGroupAssignmentServiceImpl implements CustomerGroupAssignmentService {

    @Resource
    private CustomerGroupAssignmentMapper customerGroupAssignmentMapper;

    @Override
    public Long createCustomerGroupAssignment(CustomerGroupAssignmentSaveReqVO createReqVO) {
        // 插入
        CustomerGroupAssignmentDO customerGroupAssignment = BeanUtils.toBean(createReqVO, CustomerGroupAssignmentDO.class);
        customerGroupAssignmentMapper.insert(customerGroupAssignment);

        // 返回
        return customerGroupAssignment.getId();
    }

    @Override
    public void updateCustomerGroupAssignment(CustomerGroupAssignmentSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGroupAssignmentExists(updateReqVO.getId());
        // 更新
        CustomerGroupAssignmentDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGroupAssignmentDO.class);
        customerGroupAssignmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGroupAssignment(Long id) {
        // 校验存在
        validateCustomerGroupAssignmentExists(id);
        // 删除
        customerGroupAssignmentMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGroupAssignmentListByIds(List<Long> ids) {
        // 删除
        customerGroupAssignmentMapper.deleteByIds(ids);
        }


    private void validateCustomerGroupAssignmentExists(Long id) {
        if (customerGroupAssignmentMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GROUP_ASSIGNMENT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGroupAssignmentDO getCustomerGroupAssignment(Long id) {
        return customerGroupAssignmentMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGroupAssignmentDO> getCustomerGroupAssignmentPage(CustomerGroupAssignmentPageReqVO pageReqVO) {
        return customerGroupAssignmentMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CustomerGroupAssignmentRespVO> getCustomerGroupAssignmentPageWithJoin(CustomerGroupAssignmentPageReqVO pageReqVO) {
        // 创建 IPage 对象
        IPage<CustomerGroupAssignmentRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 执行联表查询
        IPage<CustomerGroupAssignmentRespVO> result = customerGroupAssignmentMapper.selectPageWithJoin(page, pageReqVO);
        // 转换为 PageResult
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

}