package cn.iocoder.yudao.module.aicrm.service.customerguaranteepledge;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerguaranteepledge.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerguaranteepledge.CustomerGuaranteePledgeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerguaranteepledge.CustomerGuaranteePledgeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户质押物信息表（零售+对公共用） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerGuaranteePledgeServiceImpl implements CustomerGuaranteePledgeService {

    @Resource
    private CustomerGuaranteePledgeMapper customerGuaranteePledgeMapper;

    @Override
    public Long createCustomerGuaranteePledge(CustomerGuaranteePledgeSaveReqVO createReqVO) {
        // 插入
        CustomerGuaranteePledgeDO customerGuaranteePledge = BeanUtils.toBean(createReqVO, CustomerGuaranteePledgeDO.class);
        customerGuaranteePledgeMapper.insert(customerGuaranteePledge);

        // 返回
        return customerGuaranteePledge.getId();
    }

    @Override
    public void updateCustomerGuaranteePledge(CustomerGuaranteePledgeSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGuaranteePledgeExists(updateReqVO.getId());
        // 更新
        CustomerGuaranteePledgeDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGuaranteePledgeDO.class);
        customerGuaranteePledgeMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGuaranteePledge(Long id) {
        // 校验存在
        validateCustomerGuaranteePledgeExists(id);
        // 删除
        customerGuaranteePledgeMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGuaranteePledgeListByIds(List<Long> ids) {
        // 删除
        customerGuaranteePledgeMapper.deleteByIds(ids);
        }


    private void validateCustomerGuaranteePledgeExists(Long id) {
        if (customerGuaranteePledgeMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GUARANTEE_PLEDGE_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGuaranteePledgeDO getCustomerGuaranteePledge(Long id) {
        return customerGuaranteePledgeMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGuaranteePledgeDO> getCustomerGuaranteePledgePage(CustomerGuaranteePledgePageReqVO pageReqVO) {
        return customerGuaranteePledgeMapper.selectPage(pageReqVO);
    }

}