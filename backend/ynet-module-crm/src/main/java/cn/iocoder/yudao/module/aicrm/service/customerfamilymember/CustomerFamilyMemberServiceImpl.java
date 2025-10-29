package cn.iocoder.yudao.module.aicrm.service.customerfamilymember;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.customerfamilymember.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerfamilymember.CustomerFamilyMemberDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.customerfamilymember.CustomerFamilyMemberMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * 客户家庭成员信息表（零售客户） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerFamilyMemberServiceImpl implements CustomerFamilyMemberService {

    @Resource
    private CustomerFamilyMemberMapper customerFamilyMemberMapper;

    @Override
    public Long createCustomerFamilyMember(CustomerFamilyMemberSaveReqVO createReqVO) {
        // 插入
        CustomerFamilyMemberDO customerFamilyMember = BeanUtils.toBean(createReqVO, CustomerFamilyMemberDO.class);
        customerFamilyMemberMapper.insert(customerFamilyMember);

        // 返回
        return customerFamilyMember.getId();
    }

    @Override
    public void updateCustomerFamilyMember(CustomerFamilyMemberSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomerFamilyMemberExists(updateReqVO.getId());
        // 更新
        CustomerFamilyMemberDO updateObj = BeanUtils.toBean(updateReqVO, CustomerFamilyMemberDO.class);
        customerFamilyMemberMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerFamilyMember(Long id) {
        // 校验存在
        validateCustomerFamilyMemberExists(id);
        // 删除
        customerFamilyMemberMapper.deleteById(id);
    }

    @Override
        public void deleteCustomerFamilyMemberListByIds(List<Long> ids) {
        // 删除
        customerFamilyMemberMapper.deleteByIds(ids);
        }


    private void validateCustomerFamilyMemberExists(Long id) {
        if (customerFamilyMemberMapper.selectById(id) == null) {
            throw exception(CUSTOMER_FAMILY_MEMBER_NOT_EXISTS);
        }
    }

    @Override
    public CustomerFamilyMemberDO getCustomerFamilyMember(Long id) {
        return customerFamilyMemberMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerFamilyMemberDO> getCustomerFamilyMemberPage(CustomerFamilyMemberPageReqVO pageReqVO) {
        return customerFamilyMemberMapper.selectPage(pageReqVO);
    }

}