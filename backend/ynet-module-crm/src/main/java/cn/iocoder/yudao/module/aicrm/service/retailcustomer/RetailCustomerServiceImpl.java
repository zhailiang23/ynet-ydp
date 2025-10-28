package cn.iocoder.yudao.module.aicrm.service.retailcustomer;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.retailcustomer.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.retailcustomer.RetailCustomerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.retailcustomer.RetailCustomerMapper;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customer.CustomerMapper;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customer.CustomerDO;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

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

    @Resource
    private CustomerMapper customerMapper;

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
    public RetailCustomerRespVO getRetailCustomer(Long id) {
        // 1. 查询零售客户扩展信息
        RetailCustomerDO retailCustomerDO = retailCustomerMapper.selectById(id);
        if (retailCustomerDO == null) {
            return null;
        }

        // 2. 转换为 VO
        RetailCustomerRespVO respVO = BeanUtils.toBean(retailCustomerDO, RetailCustomerRespVO.class);

        // 3. 查询客户共有信息
        Long customerId = retailCustomerDO.getCustomerId();
        if (customerId != null) {
            CustomerDO customerDO = customerMapper.selectById(customerId);
            if (customerDO != null) {
                // 4. 手动组装共有字段到 VO
                respVO.setCustomerNo(customerDO.getCustomerNo());
                respVO.setCustomerType(customerDO.getCustomerType());
                respVO.setCustomerName(customerDO.getCustomerName());
                respVO.setCustomerLevel(customerDO.getCustomerLevel());
                respVO.setCustomerStatus(customerDO.getCustomerStatus());
                respVO.setIsHighQuality(customerDO.getIsHighQuality());
                respVO.setIsImportant(customerDO.getIsImportant());
                respVO.setCreditStatus(customerDO.getCreditStatus());
                respVO.setCreditLevel(customerDO.getCreditLevel());
                respVO.setCreditScore(customerDO.getCreditScore());
                respVO.setCustomerSource(customerDO.getCustomerSource());
                respVO.setCustomerTag(customerDO.getCustomerTag());
                respVO.setRemark(customerDO.getRemark());
                respVO.setDeptId(customerDO.getDeptId());
            }
        }

        return respVO;
    }

    @Override
    public PageResult<RetailCustomerDO> getRetailCustomerPage(RetailCustomerPageReqVO pageReqVO) {
        return retailCustomerMapper.selectPage(pageReqVO);
    }

}