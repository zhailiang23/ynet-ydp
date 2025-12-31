package com.ynet.iplatform.module.aicrm.service.potentialcustomer;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer.vo.*;
import com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer.vo.AppPotentialCustomerPageReqVO;
import com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer.vo.AppPotentialCustomerRespVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.potentialcustomer.PotentialCustomerDO;
import com.ynet.iplatform.module.aicrm.dal.mysql.potentialcustomer.PotentialCustomerMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.POTENTIAL_CUSTOMER_NOT_EXISTS;

/**
 * AI CRM 潜客管理 Service 实现类
 *
 * @author AI CRM Team
 */
@Service
@Validated
public class PotentialCustomerServiceImpl implements PotentialCustomerService {

    @Resource
    private PotentialCustomerMapper potentialCustomerMapper;

    @Override
    public Long createPotentialCustomer(PotentialCustomerSaveReqVO createReqVO) {
        // 插入潜客
        PotentialCustomerDO customer = BeanUtils.toBean(createReqVO, PotentialCustomerDO.class);
        potentialCustomerMapper.insert(customer);
        return customer.getId();
    }

    @Override
    public void updatePotentialCustomer(PotentialCustomerSaveReqVO updateReqVO) {
        // 校验存在
        validatePotentialCustomerExists(updateReqVO.getId());
        // 更新
        PotentialCustomerDO updateObj = BeanUtils.toBean(updateReqVO, PotentialCustomerDO.class);
        potentialCustomerMapper.updateById(updateObj);
    }

    @Override
    public void deletePotentialCustomer(Long id) {
        // 校验存在
        validatePotentialCustomerExists(id);
        // 删除
        potentialCustomerMapper.deleteById(id);
    }

    @Override
    public void deletePotentialCustomerList(List<Long> ids) {
        ids.forEach(this::deletePotentialCustomer);
    }

    private void validatePotentialCustomerExists(Long id) {
        if (potentialCustomerMapper.selectById(id) == null) {
            throw exception(POTENTIAL_CUSTOMER_NOT_EXISTS);
        }
    }

    @Override
    public PotentialCustomerDO getPotentialCustomer(Long id) {
        return potentialCustomerMapper.selectById(id);
    }

    @Override
    public PageResult<PotentialCustomerDO> getPotentialCustomerPage(PotentialCustomerPageReqVO pageReqVO) {
        return potentialCustomerMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<AppPotentialCustomerRespVO> getAppPotentialCustomerPage(AppPotentialCustomerPageReqVO pageReqVO) {
        // 创建查询VO，转换参数
        PotentialCustomerPageReqVO adminPageReqVO = new PotentialCustomerPageReqVO();
        adminPageReqVO.setPageNo(pageReqVO.getPageNo());
        adminPageReqVO.setPageSize(pageReqVO.getPageSize());
        adminPageReqVO.setInsightId(pageReqVO.getInsightId());
        adminPageReqVO.setStatus(pageReqVO.getStatus());
        adminPageReqVO.setMinAiMatchScore(pageReqVO.getMinAiMatchScore());

        // 分页查询
        PageResult<PotentialCustomerDO> pageResult = potentialCustomerMapper.selectPage(adminPageReqVO);
        // 转换返回
        return BeanUtils.toBean(pageResult, AppPotentialCustomerRespVO.class);
    }

    @Override
    public AppPotentialCustomerRespVO getAppPotentialCustomer(Long id) {
        PotentialCustomerDO customer = getPotentialCustomer(id);
        return BeanUtils.toBean(customer, AppPotentialCustomerRespVO.class);
    }

}
