package com.ynet.iplatform.module.grid.service.communitycustomer;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo.*;
import com.ynet.iplatform.module.grid.controller.admin.customer.vo.GridCustomerSaveReqVO;
import com.ynet.iplatform.module.grid.dal.dataobject.communitycustomer.GridCommunityCustomerDO;
import com.ynet.iplatform.module.grid.dal.dataobject.customer.GridCustomerDO;
import com.ynet.iplatform.module.grid.service.customer.GridCustomerService;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.tenant.core.context.TenantContextHolder;

import com.ynet.iplatform.module.grid.dal.mysql.communitycustomer.GridCommunityCustomerMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 社区客户扩展 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridCommunityCustomerServiceImpl implements GridCommunityCustomerService {

    @Resource
    private GridCommunityCustomerMapper communityCustomerMapper;

    @Resource
    private GridCustomerService customerService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCommunityCustomer(GridCommunityCustomerSaveReqVO createReqVO) {
        // 1. 先创建 grid_customer 记录
        GridCustomerSaveReqVO customerReqVO = new GridCustomerSaveReqVO();
        customerReqVO.setCustomerType("COMMUNITY"); // 社区客户类型
        customerReqVO.setCustomerName(createReqVO.getCustomerName());
        customerReqVO.setPhone(createReqVO.getPhone());
        customerReqVO.setIdCard(createReqVO.getIdNumber());
        customerReqVO.setAddress(createReqVO.getAddress());
        customerReqVO.setLongitude(createReqVO.getLongitude());
        customerReqVO.setLatitude(createReqVO.getLatitude());
        customerReqVO.setManagerId(createReqVO.getManagerId());
        customerReqVO.setGridId(createReqVO.getGridId() != null ? createReqVO.getGridId() : 1L); // 网格ID，默认为1
        customerReqVO.setSource("MANUAL"); // 手动录入
        customerReqVO.setStatus("NORMAL"); // 正常状态

        Long customerId = customerService.createCustomer(customerReqVO);

        // 2. 再创建 grid_community_customer 记录
        GridCommunityCustomerDO communityCustomer = BeanUtils.toBean(createReqVO, GridCommunityCustomerDO.class);
        communityCustomer.setCustomerId(customerId);
        communityCustomerMapper.insert(communityCustomer);

        // 返回社区客户扩展ID
        return communityCustomer.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCommunityCustomer(GridCommunityCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validateCommunityCustomerExists(updateReqVO.getId());

        // 获取社区客户扩展信息，找到关联的 customerId
        GridCommunityCustomerDO communityCustomer = communityCustomerMapper.selectById(updateReqVO.getId());
        Long customerId = communityCustomer.getCustomerId();

        // 获取原有的客户信息
        GridCustomerDO existingCustomer = customerService.getCustomer(customerId);

        // 1. 更新 grid_customer 记录
        GridCustomerSaveReqVO customerReqVO = new GridCustomerSaveReqVO();
        customerReqVO.setId(customerId);
        customerReqVO.setCustomerType("COMMUNITY"); // 社区客户类型
        customerReqVO.setCustomerName(updateReqVO.getCustomerName());
        customerReqVO.setPhone(updateReqVO.getPhone());
        customerReqVO.setIdCard(updateReqVO.getIdNumber());
        customerReqVO.setAddress(updateReqVO.getAddress());
        customerReqVO.setLongitude(updateReqVO.getLongitude());
        customerReqVO.setLatitude(updateReqVO.getLatitude());
        customerReqVO.setManagerId(updateReqVO.getManagerId());
        // 网格ID：如果前端传递了就使用新值，否则保留原值
        customerReqVO.setGridId(updateReqVO.getGridId() != null ? updateReqVO.getGridId() : existingCustomer.getGridId());
        customerReqVO.setSource("MANUAL"); // 手动录入
        customerReqVO.setStatus("NORMAL"); // 正常状态

        customerService.updateCustomer(customerReqVO);

        // 2. 更新 grid_community_customer 记录
        GridCommunityCustomerDO updateObj = BeanUtils.toBean(updateReqVO, GridCommunityCustomerDO.class);
        communityCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommunityCustomer(Long id) {
        // 校验存在
        validateCommunityCustomerExists(id);
        // 删除
        communityCustomerMapper.deleteById(id);
    }

    @Override
        public void deleteCommunityCustomerListByIds(List<Long> ids) {
        // 删除
        communityCustomerMapper.deleteByIds(ids);
        }


    private void validateCommunityCustomerExists(Long id) {
        if (communityCustomerMapper.selectById(id) == null) {
            throw exception(COMMUNITY_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public GridCommunityCustomerDO getCommunityCustomer(Long id) {
        return communityCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<GridCommunityCustomerDO> getCommunityCustomerPage(GridCommunityCustomerPageReqVO pageReqVO) {
        // 注意：此方法已不再使用，Controller 层直接调用 Mapper 的 selectPageWithRelations 方法
        // 返回空结果以保持接口兼容性
        return new PageResult<>(new ArrayList<>(), 0L);
    }

}