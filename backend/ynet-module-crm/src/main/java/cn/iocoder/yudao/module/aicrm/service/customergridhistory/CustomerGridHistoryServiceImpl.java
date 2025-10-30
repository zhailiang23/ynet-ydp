package cn.iocoder.yudao.module.aicrm.service.customergridhistory;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customergridhistory.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customergridhistory.CustomerGridHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customergridhistory.CustomerGridHistoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户归属网格调整历史表（记录调整当时的网格信息快照） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerGridHistoryServiceImpl implements CustomerGridHistoryService {

    @Resource
    private CustomerGridHistoryMapper customerGridHistoryMapper;

    @Override
    public Long createCustomerGridHistory(CustomerGridHistorySaveReqVO createReqVO) {
        // 插入
        CustomerGridHistoryDO customerGridHistory = BeanUtils.toBean(createReqVO, CustomerGridHistoryDO.class);
        customerGridHistoryMapper.insert(customerGridHistory);

        // 返回
        return customerGridHistory.getId();
    }

    @Override
    public void updateCustomerGridHistory(CustomerGridHistorySaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerGridHistoryExists(updateReqVO.getId());
        // 更新
        CustomerGridHistoryDO updateObj = BeanUtils.toBean(updateReqVO, CustomerGridHistoryDO.class);
        customerGridHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerGridHistory(Long id) {
        // 校验存在
        validateCustomerGridHistoryExists(id);
        // 删除
        customerGridHistoryMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerGridHistoryListByIds(List<Long> ids) {
        // 删除
        customerGridHistoryMapper.deleteByIds(ids);
        }


    private void validateCustomerGridHistoryExists(Long id) {
        if (customerGridHistoryMapper.selectById(id) == null) {
            throw exception(CUSTOMER_GRID_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public CustomerGridHistoryDO getCustomerGridHistory(Long id) {
        return customerGridHistoryMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerGridHistoryDO> getCustomerGridHistoryPage(CustomerGridHistoryPageReqVO pageReqVO) {
        return customerGridHistoryMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CustomerGridHistoryRespVO> getCustomerGridHistoryPageWithJoin(CustomerGridHistoryPageReqVO pageReqVO) {
        // 创建 IPage 对象
        IPage<CustomerGridHistoryRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 执行联表查询
        IPage<CustomerGridHistoryRespVO> result = customerGridHistoryMapper.selectPageWithJoin(page, pageReqVO);
        // 转换为 PageResult
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

}