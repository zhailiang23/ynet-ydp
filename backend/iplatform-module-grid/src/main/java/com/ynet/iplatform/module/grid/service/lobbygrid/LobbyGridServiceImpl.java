package com.ynet.iplatform.module.grid.service.lobbygrid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridPageReqVO;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridRespVO;
import com.ynet.iplatform.module.grid.controller.admin.lobbygrid.vo.LobbyGridSaveReqVO;
import com.ynet.iplatform.module.grid.dal.mysql.lobbygrid.LobbyGridMapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.GRID_INFO_NOT_EXISTS;

/**
 * 厅堂网格 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Slf4j
public class LobbyGridServiceImpl implements LobbyGridService {

    @Resource
    private LobbyGridMapper lobbyGridMapper;

    @Override
    public Long createLobbyGrid(LobbyGridSaveReqVO createReqVO) {
        // 1. 生成网格编号
        String gridCode = "GRID_LOBBY_" + System.currentTimeMillis();

        // 2. 获取当前用户和租户信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Long tenantId = SecurityFrameworkUtils.getLoginUser() != null
                ? SecurityFrameworkUtils.getLoginUser().getTenantId() : null;

        // 3. 构建参数 Map
        Map<String, Object> params = new HashMap<>();
        params.put("gridCode", gridCode);
        params.put("gridName", createReqVO.getLocationName()); // 网格名称使用位置名称
        params.put("gridType", "LOBBY");
        params.put("orgId", createReqVO.getOrgId());
        params.put("managementLevel", createReqVO.getManagementLevel());
        params.put("region", createReqVO.getRegion());
        params.put("district", createReqVO.getDistrict());
        params.put("locationName", createReqVO.getLocationName());
        params.put("longitude", createReqVO.getLongitude());
        params.put("latitude", createReqVO.getLatitude());
        params.put("outletType", createReqVO.getOutletType());
        params.put("residentCount", createReqVO.getResidentCount());
        params.put("merchantCount", createReqVO.getMerchantCount());
        params.put("radiusMeters", createReqVO.getRadiusMeters());
        params.put("status", "ACTIVE");
        params.put("boundaryGeometry", createReqVO.getBoundaryGeometry()); // GeoJSON
        params.put("creator", userId != null ? userId.toString() : null);
        params.put("createTime", LocalDateTime.now());
        params.put("updater", userId != null ? userId.toString() : null);
        params.put("updateTime", LocalDateTime.now());
        params.put("tenantId", tenantId);

        // 4. 执行插入
        lobbyGridMapper.insertLobbyGrid(params);

        // 5. 返回生成的 ID
        return ((Number) params.get("id")).longValue();
    }

    @Override
    public void updateLobbyGrid(LobbyGridSaveReqVO updateReqVO) {
        // 1. 校验存在
        validateLobbyGridExists(updateReqVO.getId());

        // 2. 获取当前用户信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();

        // 3. 构建参数 Map
        Map<String, Object> params = new HashMap<>();
        params.put("id", updateReqVO.getId());
        params.put("gridName", updateReqVO.getLocationName()); // 网格名称使用位置名称
        params.put("orgId", updateReqVO.getOrgId());
        params.put("managementLevel", updateReqVO.getManagementLevel());
        params.put("region", updateReqVO.getRegion());
        params.put("district", updateReqVO.getDistrict());
        params.put("locationName", updateReqVO.getLocationName());
        params.put("longitude", updateReqVO.getLongitude());
        params.put("latitude", updateReqVO.getLatitude());
        params.put("outletType", updateReqVO.getOutletType());
        params.put("residentCount", updateReqVO.getResidentCount());
        params.put("merchantCount", updateReqVO.getMerchantCount());
        params.put("radiusMeters", updateReqVO.getRadiusMeters());
        params.put("boundaryGeometry", updateReqVO.getBoundaryGeometry()); // GeoJSON
        params.put("updater", userId != null ? userId.toString() : null);
        params.put("updateTime", LocalDateTime.now());

        // 4. 执行更新
        lobbyGridMapper.updateLobbyGridById(params);
    }

    @Override
    public void deleteLobbyGrid(Long id) {
        // 校验存在
        validateLobbyGridExists(id);
        // 删除
        lobbyGridMapper.deleteById(id);
    }

    private void validateLobbyGridExists(Long id) {
        Map<String, Object> grid = lobbyGridMapper.selectLobbyGridById(id);
        if (grid == null) {
            throw exception(GRID_INFO_NOT_EXISTS);
        }
    }

    @Override
    public LobbyGridRespVO getLobbyGrid(Long id) {
        Map<String, Object> grid = lobbyGridMapper.selectLobbyGridById(id);
        if (grid == null) {
            return null;
        }
        return convertToRespVO(grid);
    }

    @Override
    public PageResult<LobbyGridRespVO> getLobbyGridPage(LobbyGridPageReqVO pageReqVO) {
        // 1. 查询列表
        List<Map<String, Object>> list = lobbyGridMapper.selectLobbyGridPageWithJoin(pageReqVO);

        // 2. 查询总数
        Long total = lobbyGridMapper.selectLobbyGridCount(pageReqVO);

        // 3. 转换为 VO
        List<LobbyGridRespVO> voList = list.stream()
                .map(this::convertToRespVO)
                .toList();

        return new PageResult<>(voList, total);
    }

    /**
     * 将 Map 转换为 RespVO
     */
    private LobbyGridRespVO convertToRespVO(Map<String, Object> map) {
        LobbyGridRespVO vo = new LobbyGridRespVO();
        vo.setId(getLong(map, "id"));
        vo.setGridCode((String) map.get("grid_code"));
        vo.setOrgId(getLong(map, "org_id"));
        vo.setOrgName((String) map.get("org_name"));
        vo.setSecondLevelId(getLong(map, "second_level_id"));
        vo.setSecondLevelName((String) map.get("second_level_name"));
        vo.setFirstLevelId(getLong(map, "first_level_id"));
        vo.setFirstLevelName((String) map.get("first_level_name"));
        vo.setManagementLevel((String) map.get("management_level"));
        vo.setRegion((String) map.get("region"));
        vo.setDistrict((String) map.get("district"));
        vo.setLocationName((String) map.get("location_name"));
        vo.setLongitude(getBigDecimal(map, "longitude"));
        vo.setLatitude(getBigDecimal(map, "latitude"));
        vo.setOutletType((String) map.get("outlet_type"));
        vo.setResidentCount(getInteger(map, "resident_count"));
        vo.setMerchantCount(getInteger(map, "merchant_count"));
        vo.setRadiusMeters(getInteger(map, "radius_meters"));
        vo.setCreator((String) map.get("creator"));
        vo.setCreatorName((String) map.get("creator_name"));
        vo.setCreateTime(getLocalDateTime(map, "create_time"));
        vo.setBoundaryGeometry((String) map.get("boundary_geometry"));
        return vo;
    }

    private Long getLong(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return Long.parseLong(value.toString());
    }

    private Integer getInteger(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return Integer.parseInt(value.toString());
    }

    private BigDecimal getBigDecimal(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        return new BigDecimal(value.toString());
    }

    private LocalDateTime getLocalDateTime(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDateTime) {
            return (LocalDateTime) value;
        }
        // 如果是其他类型，可能需要特殊处理
        return null;
    }

}
