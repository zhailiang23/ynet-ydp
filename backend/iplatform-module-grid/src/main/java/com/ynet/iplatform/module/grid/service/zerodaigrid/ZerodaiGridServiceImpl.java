package com.ynet.iplatform.module.grid.service.zerodaigrid;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.grid.controller.admin.zerodaigrid.vo.*;
import com.ynet.iplatform.module.grid.dal.mysql.zerodaigrid.ZerodaiGridMapper;
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
 * 零贷网格 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class ZerodaiGridServiceImpl implements ZerodaiGridService {

    @Resource
    private ZerodaiGridMapper zerodaiGridMapper;

    @Resource
    private com.ynet.iplatform.module.grid.service.customer.GridCustomerService gridCustomerService;

    @Override
    public PageResult<ZerodaiGridRespVO> getZerodaiGridPage(ZerodaiGridPageReqVO pageReqVO) {
        // 查询总数
        Long total = zerodaiGridMapper.selectZerodaiGridCount(pageReqVO);

        if (total == 0) {
            return new PageResult<>(List.of(), 0L);
        }

        // 查询分页数据
        List<Map<String, Object>> list = zerodaiGridMapper.selectZerodaiGridPageWithJoin(pageReqVO);

        // 转换为 VO
        List<ZerodaiGridRespVO> voList = list.stream().map(map -> {
            ZerodaiGridRespVO vo = new ZerodaiGridRespVO();
            vo.setId(((Number) map.get("id")).longValue());
            vo.setGridCode((String) map.get("grid_code"));
            vo.setGridName((String) map.get("grid_name"));
            vo.setGridType((String) map.get("grid_type"));
            if (map.get("org_id") != null) {
                vo.setOrgId(((Number) map.get("org_id")).longValue());
            }
            vo.setOrgName((String) map.get("org_name"));
            vo.setResourceTags((String) map.get("resource_tags"));
            vo.setOrgType((String) map.get("org_type"));
            vo.setCreator((String) map.get("creator"));
            vo.setCreatorName((String) map.get("creator_name"));
            vo.setBoundaryGeometry((String) map.get("boundary_geometry"));
            vo.setCreateTime((LocalDateTime) map.get("create_time"));
            return vo;
        }).collect(Collectors.toList());

        return new PageResult<>(voList, total);
    }

    @Override
    public ZerodaiGridRespVO getZerodaiGrid(Long id) {
        // 使用自定义查询方法，将 GEOMETRY 转换为 GeoJSON
        Map<String, Object> gridMap = zerodaiGridMapper.selectZerodaiGridById(id);
        if (gridMap == null) {
            throw exception(GRID_INFO_NOT_EXISTS);
        }

        // 转换为 VO
        ZerodaiGridRespVO respVO = new ZerodaiGridRespVO();
        respVO.setId(((Number) gridMap.get("id")).longValue());
        respVO.setGridCode((String) gridMap.get("grid_code"));
        respVO.setGridName((String) gridMap.get("grid_name"));
        respVO.setGridType((String) gridMap.get("grid_type"));
        if (gridMap.get("org_id") != null) {
            respVO.setOrgId(((Number) gridMap.get("org_id")).longValue());
        }
        respVO.setOrgName((String) gridMap.get("org_name"));
        respVO.setResourceTags((String) gridMap.get("resource_tags"));
        respVO.setOrgType((String) gridMap.get("org_type"));
        respVO.setCreator((String) gridMap.get("creator"));
        respVO.setCreatorName((String) gridMap.get("creator_name"));
        respVO.setCreateTime((LocalDateTime) gridMap.get("create_time"));
        respVO.setBoundaryGeometry((String) gridMap.get("boundary_geometry"));

        return respVO;
    }

    @Override
    public Long createZerodaiGrid(ZerodaiGridSaveReqVO createReqVO) {
        // 自动生成网格编号
        String gridCode = "GRID_ZERODAI_" + System.currentTimeMillis();

        // 获取当前用户和租户信息
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        Long tenantId = SecurityFrameworkUtils.getLoginUser() != null
                ? SecurityFrameworkUtils.getLoginUser().getTenantId()
                : null;

        // 构建参数 Map
        Map<String, Object> params = new HashMap<>();
        params.put("gridCode", gridCode);
        params.put("gridName", createReqVO.getGridName());
        params.put("gridType", "ZERODAI");
        params.put("orgId", createReqVO.getOrgId());
        params.put("resourceTags", createReqVO.getResourceTags());
        params.put("orgType", createReqVO.getOrgType());
        params.put("status", "ACTIVE");
        params.put("boundaryGeometry", createReqVO.getBoundaryGeometry());
        params.put("creator", userId != null ? userId.toString() : null);
        params.put("createTime", LocalDateTime.now());
        params.put("updater", userId != null ? userId.toString() : null);
        params.put("updateTime", LocalDateTime.now());
        params.put("tenantId", tenantId);

        // 插入数据库
        zerodaiGridMapper.insertZerodaiGrid(params);

        // 获取生成的 ID
        Long gridId = ((Number) params.get("id")).longValue();

        // 自动关联网格内的零贷客户
        gridCustomerService.autoLinkCustomersToGrid(gridId, "ZERODAI");

        // 返回生成的 ID
        return gridId;
    }

    @Override
    public void updateZerodaiGrid(ZerodaiGridSaveReqVO updateReqVO) {
        // 校验网格是否存在
        Map<String, Object> existingGrid = zerodaiGridMapper.selectZerodaiGridById(updateReqVO.getId());
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
        params.put("resourceTags", updateReqVO.getResourceTags());
        params.put("orgType", updateReqVO.getOrgType());
        params.put("boundaryGeometry", updateReqVO.getBoundaryGeometry());
        params.put("updater", userId != null ? userId.toString() : null);
        params.put("updateTime", LocalDateTime.now());

        // 更新数据库
        zerodaiGridMapper.updateZerodaiGridById(params);

        // 重新关联网格内的零贷客户（边界变化后需要重新计算）
        gridCustomerService.autoLinkCustomersToGrid(updateReqVO.getId(), "ZERODAI");
    }

    @Override
    public void deleteZerodaiGrid(Long id) {
        // 校验网格是否存在
        Map<String, Object> existingGrid = zerodaiGridMapper.selectZerodaiGridById(id);
        if (existingGrid == null) {
            throw exception(GRID_INFO_NOT_EXISTS);
        }

        // 删除网格
        zerodaiGridMapper.deleteById(id);
    }

}
