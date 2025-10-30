package cn.iocoder.yudao.module.aicrm.service.customerpoints;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerpoints.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerpoints.CustomerPointsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerpoints.CustomerPointsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户积分信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerPointsServiceImpl implements CustomerPointsService {

    @Resource
    private CustomerPointsMapper customerPointsMapper;

    @Override
    public Long createCustomerPoints(CustomerPointsSaveReqVO createReqVO) {
        // 插入
        CustomerPointsDO customerPoints = BeanUtils.toBean(createReqVO, CustomerPointsDO.class);
        customerPointsMapper.insert(customerPoints);

        // 返回
        return customerPoints.getId();
    }

    @Override
    public void updateCustomerPoints(CustomerPointsSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerPointsExists(updateReqVO.getId());
        // 更新
        CustomerPointsDO updateObj = BeanUtils.toBean(updateReqVO, CustomerPointsDO.class);
        customerPointsMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerPoints(Long id) {
        // 校验存在
        validateCustomerPointsExists(id);
        // 删除
        customerPointsMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerPointsListByIds(List<Long> ids) {
        // 删除
        customerPointsMapper.deleteByIds(ids);
        }


    private void validateCustomerPointsExists(Long id) {
        if (customerPointsMapper.selectById(id) == null) {
            throw exception(CUSTOMER_POINTS_NOT_EXISTS);
        }
    }

    @Override
    public CustomerPointsDO getCustomerPoints(Long id) {
        return customerPointsMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerPointsDO> getCustomerPointsPage(CustomerPointsPageReqVO pageReqVO) {
        return customerPointsMapper.selectPage(pageReqVO);
    }

}