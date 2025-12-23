package com.ynet.iplatform.module.grid.service.keyperson;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.keyperson.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.keyperson.KeyPersonDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.system.service.user.AdminUserService;
import com.ynet.iplatform.module.system.dal.dataobject.user.AdminUserDO;

import com.ynet.iplatform.module.grid.dal.mysql.keyperson.KeyPersonMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 关键人信息 Service 实现类
 *
 * @author 易诚原生智能平台
 */
@Service
@Validated
public class KeyPersonServiceImpl implements KeyPersonService {

    @Resource
    private KeyPersonMapper keyPersonMapper;

    @Resource
    private AdminUserService adminUserService;

    @Override
    public Long createKeyPerson(KeyPersonSaveReqVO createReqVO) {
        // 1. 获取当前登录用户信息
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserDO currentUser = adminUserService.getUser(currentUserId);

        // 2. 转换并设置员工信息
        KeyPersonDO keyPerson = BeanUtils.toBean(createReqVO, KeyPersonDO.class);
        if (currentUser != null) {
            keyPerson.setEmployeeCode(currentUser.getUsername()); // 员工工号
            keyPerson.setEmployeeName(currentUser.getNickname()); // 员工姓名
        }

        // 3. 插入
        keyPersonMapper.insert(keyPerson);

        // 4. 返回
        return keyPerson.getId();
    }

    @Override
    public void updateKeyPerson(KeyPersonSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateKeyPersonExists(updateReqVO.getId());

        // 2. 获取当前登录用户信息
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserDO currentUser = adminUserService.getUser(currentUserId);

        // 3. 转换并设置员工信息
        KeyPersonDO updateObj = BeanUtils.toBean(updateReqVO, KeyPersonDO.class);
        if (currentUser != null) {
            updateObj.setEmployeeCode(currentUser.getUsername()); // 员工工号
            updateObj.setEmployeeName(currentUser.getNickname()); // 员工姓名
        }

        // 4. 更新
        keyPersonMapper.updateById(updateObj);
    }

    @Override
    public void deleteKeyPerson(Long id) {
        // 校验存在
        validateKeyPersonExists(id);
        // 删除
        keyPersonMapper.deleteById(id);
    }

    @Override
        public void deleteKeyPersonListByIds(List<Long> ids) {
        // 删除
        keyPersonMapper.deleteByIds(ids);
        }


    private void validateKeyPersonExists(Long id) {
        if (keyPersonMapper.selectById(id) == null) {
            throw exception(KEY_PERSON_NOT_EXISTS);
        }
    }

    @Override
    public KeyPersonDO getKeyPerson(Long id) {
        return keyPersonMapper.selectById(id);
    }

    @Override
    public PageResult<KeyPersonDO> getKeyPersonPage(KeyPersonPageReqVO pageReqVO) {
        return keyPersonMapper.selectPage(pageReqVO);
    }

}