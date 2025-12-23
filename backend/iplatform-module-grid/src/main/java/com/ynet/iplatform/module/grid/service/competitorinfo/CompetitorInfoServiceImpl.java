package com.ynet.iplatform.module.grid.service.competitorinfo;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.competitorinfo.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.competitorinfo.CompetitorInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.system.service.user.AdminUserService;
import com.ynet.iplatform.module.system.dal.dataobject.user.AdminUserDO;

import com.ynet.iplatform.module.grid.dal.mysql.competitorinfo.CompetitorInfoMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 同业信息 Service 实现类
 *
 * @author 易诚原生智能平台
 */
@Service
@Validated
public class CompetitorInfoServiceImpl implements CompetitorInfoService {

    @Resource
    private CompetitorInfoMapper competitorInfoMapper;

    @Resource
    private AdminUserService adminUserService;

    @Override
    public Long createCompetitorInfo(CompetitorInfoSaveReqVO createReqVO) {
        // 1. 获取当前登录用户信息
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserDO currentUser = adminUserService.getUser(currentUserId);

        // 2. 转换并设置员工信息
        CompetitorInfoDO competitorInfo = BeanUtils.toBean(createReqVO, CompetitorInfoDO.class);
        if (currentUser != null) {
            competitorInfo.setEmployeeCode(currentUser.getUsername()); // 员工工号
            competitorInfo.setEmployeeName(currentUser.getNickname()); // 员工姓名
        }

        // 3. 插入
        competitorInfoMapper.insert(competitorInfo);

        // 4. 返回
        return competitorInfo.getId();
    }

    @Override
    public void updateCompetitorInfo(CompetitorInfoSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateCompetitorInfoExists(updateReqVO.getId());

        // 2. 获取当前登录用户信息
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserDO currentUser = adminUserService.getUser(currentUserId);

        // 3. 转换并设置员工信息
        CompetitorInfoDO updateObj = BeanUtils.toBean(updateReqVO, CompetitorInfoDO.class);
        if (currentUser != null) {
            updateObj.setEmployeeCode(currentUser.getUsername()); // 员工工号
            updateObj.setEmployeeName(currentUser.getNickname()); // 员工姓名
        }

        // 4. 更新
        competitorInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompetitorInfo(Long id) {
        // 校验存在
        validateCompetitorInfoExists(id);
        // 删除
        competitorInfoMapper.deleteById(id);
    }

    @Override
        public void deleteCompetitorInfoListByIds(List<Long> ids) {
        // 删除
        competitorInfoMapper.deleteByIds(ids);
        }


    private void validateCompetitorInfoExists(Long id) {
        if (competitorInfoMapper.selectById(id) == null) {
            throw exception(COMPETITOR_INFO_NOT_EXISTS);
        }
    }

    @Override
    public CompetitorInfoDO getCompetitorInfo(Long id) {
        return competitorInfoMapper.selectById(id);
    }

    @Override
    public PageResult<CompetitorInfoDO> getCompetitorInfoPage(CompetitorInfoPageReqVO pageReqVO) {
        return competitorInfoMapper.selectPage(pageReqVO);
    }

}