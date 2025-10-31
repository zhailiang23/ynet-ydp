package cn.iocoder.yudao.module.aicrm.service.customerclaimapplication;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerclaimapplication.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerclaimapplication.CustomerClaimApplicationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerclaimapplication.CustomerClaimApplicationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户认领申请 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerClaimApplicationServiceImpl implements CustomerClaimApplicationService {

    @Resource
    private CustomerClaimApplicationMapper customerClaimApplicationMapper;

    @Override
    public Long createCustomerClaimApplication(CustomerClaimApplicationSaveReqVO createReqVO) {
        // 插入
        CustomerClaimApplicationDO customerClaimApplication = BeanUtils.toBean(createReqVO, CustomerClaimApplicationDO.class);
        customerClaimApplicationMapper.insert(customerClaimApplication);

        // 返回
        return customerClaimApplication.getId();
    }

    @Override
    public void updateCustomerClaimApplication(CustomerClaimApplicationSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerClaimApplicationExists(updateReqVO.getId());
        // 更新
        CustomerClaimApplicationDO updateObj = BeanUtils.toBean(updateReqVO, CustomerClaimApplicationDO.class);
        customerClaimApplicationMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerClaimApplication(Long id) {
        // 校验存在
        validateCustomerClaimApplicationExists(id);
        // 删除
        customerClaimApplicationMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerClaimApplicationListByIds(List<Long> ids) {
        // 删除
        customerClaimApplicationMapper.deleteByIds(ids);
        }


    private void validateCustomerClaimApplicationExists(Long id) {
        if (customerClaimApplicationMapper.selectById(id) == null) {
            throw exception(CUSTOMER_CLAIM_APPLICATION_NOT_EXISTS);
        }
    }

    @Override
    public CustomerClaimApplicationDO getCustomerClaimApplication(Long id) {
        return customerClaimApplicationMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerClaimApplicationDO> getCustomerClaimApplicationPage(CustomerClaimApplicationPageReqVO pageReqVO) {
        return customerClaimApplicationMapper.selectPage(pageReqVO);
    }

}