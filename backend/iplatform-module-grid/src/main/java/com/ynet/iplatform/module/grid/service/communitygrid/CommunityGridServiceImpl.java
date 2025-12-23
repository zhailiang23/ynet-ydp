package com.ynet.iplatform.module.grid.service.communitygrid;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.grid.controller.admin.communitygrid.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.module.grid.dal.mysql.communitygrid.CommunityGridMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.GRID_INFO_NOT_EXISTS;

/**
 * 社区网格 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CommunityGridServiceImpl implements CommunityGridService {

    @Resource
    private CommunityGridMapper communityGridMapper;

    @Override
    public PageResult<CommunityGridRespVO> getCommunityGridPage(CommunityGridPageReqVO pageReqVO) {
        // 查询总数
        Long total = communityGridMapper.selectCommunityGridCount(pageReqVO);

        if (total == 0) {
            return new PageResult<>(List.of(), 0L);
        }

        // 查询分页数据
        List<Map<String, Object>> list = communityGridMapper.selectCommunityGridPageWithJoin(pageReqVO);

        // 转换为 VO
        List<CommunityGridRespVO> voList = list.stream().map(map -> {
            CommunityGridRespVO vo = new CommunityGridRespVO();
            vo.setId(((Number) map.get("id")).longValue());
            vo.setGridCode((String) map.get("grid_code"));
            vo.setGridName((String) map.get("grid_name"));
            vo.setGridType((String) map.get("grid_type"));
            if (map.get("org_id") != null) {
                vo.setOrgId(((Number) map.get("org_id")).longValue());
            }
            vo.setOrgName((String) map.get("org_name"));
            if (map.get("manager_user_id") != null) {
                vo.setManagerUserId(((Number) map.get("manager_user_id")).longValue());
            }
            vo.setManagerUserName((String) map.get("manager_user_name"));
            if (map.get("team_count") != null) {
                vo.setTeamCount((Integer) map.get("team_count"));
            }
            vo.setStatus((String) map.get("status"));
            vo.setBoundaryGeometry((String) map.get("boundary_geometry"));
            vo.setCreateTime((LocalDateTime) map.get("create_time"));
            return vo;
        }).collect(Collectors.toList());

        return new PageResult<>(voList, total);
    }

    @Override
    public CommunityGridRespVO getCommunityGrid(Long id) {
        // 使用自定义查询方法，将 GEOMETRY 转换为 GeoJSON
        Map<String, Object> gridMap = communityGridMapper.selectCommunityGridById(id);
        if (gridMap == null) {
            throw exception(GRID_INFO_NOT_EXISTS);
        }

        // 转换为 VO
        CommunityGridRespVO respVO = new CommunityGridRespVO();
        respVO.setId(((Number) gridMap.get("id")).longValue());
        respVO.setGridCode((String) gridMap.get("grid_code"));
        respVO.setGridName((String) gridMap.get("grid_name"));
        respVO.setGridType((String) gridMap.get("grid_type"));
        if (gridMap.get("org_id") != null) {
            respVO.setOrgId(((Number) gridMap.get("org_id")).longValue());
        }
        respVO.setOrgName((String) gridMap.get("org_name"));
        if (gridMap.get("manager_user_id") != null) {
            respVO.setManagerUserId(((Number) gridMap.get("manager_user_id")).longValue());
        }
        respVO.setManagerUserName((String) gridMap.get("manager_user_name"));
        if (gridMap.get("team_count") != null) {
            respVO.setTeamCount((Integer) gridMap.get("team_count"));
        }
        respVO.setStatus((String) gridMap.get("status"));
        respVO.setBoundaryGeometry((String) gridMap.get("boundary_geometry"));

        return respVO;
    }

    @Override
    public Long createCommunityGrid(CommunityGridSaveReqVO createReqVO) {
        // 自动生成网格编号
        String gridCode = "GRID_COMM_" + System.currentTimeMillis();

        // 获取当前用户和租户信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Long tenantId = SecurityFrameworkUtils.getLoginUser() != null
                ? SecurityFrameworkUtils.getLoginUser().getTenantId()
                : null;

        // 如果前端没有传递 managerUserId，则使用当前登录用户 ID
        Long managerUserId = createReqVO.getManagerUserId();
        if (managerUserId == null) {
            managerUserId = userId;
        }

        // 构建参数 Map
        Map<String, Object> params = new HashMap<>();
        params.put("gridCode", gridCode);
        params.put("gridName", createReqVO.getGridName());
        params.put("gridType", "COMMUNITY");
        params.put("orgId", createReqVO.getOrgId());
        params.put("managerUserId", managerUserId);
        params.put("teamCount", createReqVO.getTeamCount());
        params.put("status", createReqVO.getStatus());
        params.put("boundaryGeometry", createReqVO.getBoundaryGeometry());
        params.put("creator", userId != null ? userId.toString() : null);
        params.put("createTime", LocalDateTime.now());
        params.put("updater", userId != null ? userId.toString() : null);
        params.put("updateTime", LocalDateTime.now());
        params.put("tenantId", tenantId);

        // 插入数据库
        communityGridMapper.insertCommunityGrid(params);

        // 返回生成的 ID
        return ((Number) params.get("id")).longValue();
    }

    @Override
    public void updateCommunityGrid(CommunityGridSaveReqVO updateReqVO) {
        // 校验网格是否存在
        Map<String, Object> existingGrid = communityGridMapper.selectCommunityGridById(updateReqVO.getId());
        if (existingGrid == null) {
            throw exception(GRID_INFO_NOT_EXISTS);
        }

        // 获取当前用户信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();

        // 构建更新参数 Map
        Map<String, Object> params = new HashMap<>();
        params.put("id", updateReqVO.getId());
        params.put("gridName", updateReqVO.getGridName());
        params.put("orgId", updateReqVO.getOrgId());
        params.put("managerUserId", updateReqVO.getManagerUserId());
        params.put("teamCount", updateReqVO.getTeamCount());
        params.put("status", updateReqVO.getStatus());
        params.put("boundaryGeometry", updateReqVO.getBoundaryGeometry());
        params.put("updater", userId != null ? userId.toString() : null);
        params.put("updateTime", LocalDateTime.now());

        // 更新数据库
        communityGridMapper.updateCommunityGridById(params);
    }

    @Override
    public void deleteCommunityGrid(Long id) {
        // 校验网格是否存在
        Map<String, Object> existingGrid = communityGridMapper.selectCommunityGridById(id);
        if (existingGrid == null) {
            throw exception(GRID_INFO_NOT_EXISTS);
        }

        // 删除网格
        communityGridMapper.deleteById(id);
    }

}
