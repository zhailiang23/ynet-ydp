package cn.iocoder.yudao.module.aicrm.service.customergridassignment;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customergridassignment.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customergridassignment.CustomerGridAssignmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customergridassignment.CustomerGridAssignmentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户归属网格关系表（只记录关系，网格信息通过关联查询） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerGridAssignmentServiceImpl implements CustomerGridAssignmentService {

    @Resource
    private CustomerGridAssignmentMapper customerGridAssignmentMapper;

    @Override
    public Long createCustomerGridAssignment(CustomerGridAssignmentSaveReqVO createReqVO) {
        // 插入
        CustomerGridAssignmentDO customerGridAssignment = BeanUtils.toBean(createReqVO, CustomerGridAssignmentDO.class);
        customerGridAssignmentMapper.insert(customerGridAssignment);

        // 返回
        return customerGridAssignment.getId();
    }

    @Override
    public void updateCustomerGridAssignment(CustomerGridAssignmentSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGridAssignmentExists(updateReqVO.getId());
        // 更新
        CustomerGridAssignmentDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGridAssignmentDO.class);
        customerGridAssignmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGridAssignment(Long id) {
        // 校验存在
        validateCustomerGridAssignmentExists(id);
        // 删除
        customerGridAssignmentMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGridAssignmentListByIds(List<Long> ids) {
        // 删除
        customerGridAssignmentMapper.deleteByIds(ids);
        }


    private void validateCustomerGridAssignmentExists(Long id) {
        if (customerGridAssignmentMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GRID_ASSIGNMENT_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGridAssignmentDO getCustomerGridAssignment(Long id) {
        return customerGridAssignmentMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGridAssignmentDO> getCustomerGridAssignmentPage(CustomerGridAssignmentPageReqVO pageReqVO) {
        return customerGridAssignmentMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CustomerGridAssignmentRespVO> getCustomerGridAssignmentPageWithJoin(CustomerGridAssignmentPageReqVO pageReqVO) {
        // 创建 IPage 对象
        IPage<CustomerGridAssignmentRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 执行联表查询
        IPage<CustomerGridAssignmentRespVO> result = customerGridAssignmentMapper.selectPageWithJoin(page, pageReqVO);
        // 转换为 PageResult
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

}