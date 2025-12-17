package com.ynet.iplatform.module.grid.service.huinongstation;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongstation.GridHuinongStationDO;
import com.ynet.iplatform.module.grid.dal.dataobject.info.GridInfoDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;

import com.ynet.iplatform.module.grid.dal.mysql.huinongstation.GridHuinongStationMapper;
import com.ynet.iplatform.module.grid.dal.mysql.info.GridInfoMapper;
import com.ynet.iplatform.module.grid.dal.mysql.huinongmarketing.GridHuinongMarketingMapper;
import com.ynet.iplatform.module.grid.dal.mysql.huinongcustomerloan.GridHuinongCustomerLoanMapper;
import com.ynet.iplatform.framework.tenant.core.context.TenantContextHolder;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 惠农站点信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class GridHuinongStationServiceImpl implements GridHuinongStationService {

    @Resource
    private GridHuinongStationMapper huinongStationMapper;

    @Resource
    private GridInfoMapper gridInfoMapper;

    @Resource
    private GridHuinongMarketingMapper huinongMarketingMapper;

    @Resource
    private GridHuinongCustomerLoanMapper huinongCustomerLoanMapper;

    @Override
    public Long createHuinongStation(GridHuinongStationSaveReqVO createReqVO) {
        // 插入
        GridHuinongStationDO huinongStation = BeanUtils.toBean(createReqVO, GridHuinongStationDO.class);
        huinongStationMapper.insert(huinongStation);

        // 返回
        return huinongStation.getId();
    }

    @Override
    public void updateHuinongStation(GridHuinongStationSaveReqVO updateReqVO) {
        // 校验存在
        validateHuinongStationExists(updateReqVO.getId());
        // 更新
        GridHuinongStationDO updateObj = BeanUtils.toBean(updateReqVO, GridHuinongStationDO.class);
        huinongStationMapper.updateById(updateObj);
    }

    @Override
    public void deleteHuinongStation(Long id) {
        // 校验存在
        validateHuinongStationExists(id);
        // 删除
        huinongStationMapper.deleteById(id);
    }

    @Override
        public void deleteHuinongStationListByIds(List<Long> ids) {
        // 删除
        huinongStationMapper.deleteByIds(ids);
        }


    private void validateHuinongStationExists(Long id) {
        if (huinongStationMapper.selectById(id) == null) {
            throw exception(HUINONG_STATION_NOT_EXISTS);
        }
    }

    @Override
    public GridHuinongStationDO getHuinongStation(Long id) {
        return huinongStationMapper.selectById(id);
    }

    @Override
    public List<GridHuinongStationDO> getHuinongStationList() {
        return huinongStationMapper.selectList();
    }

    @Override
    public PageResult<GridHuinongStationDO> getHuinongStationPage(GridHuinongStationPageReqVO pageReqVO) {
        return huinongStationMapper.selectPage(pageReqVO);
    }

    @Override
    public GridHuinongStationMapVO getHuinongStationMapData(Long id) {
        // 校验存在
        validateHuinongStationExists(id);
        // 查询地图数据
        return huinongStationMapper.selectMapDataById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GridHuinongStationImportRespVO importHuinongStationList(List<GridHuinongStationImportVO> importStations, Boolean updateSupport) {
        // 初始化结果对象
        List<String> createStationCodes = new ArrayList<>();
        List<String> updateStationCodes = new ArrayList<>();
        Map<String, String> failureStationCodes = new LinkedHashMap<>();
        int createdGridCount = 0;

        // 生成导入批次号
        String importBatch = "IMPORT_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        LocalDateTime importTime = LocalDateTime.now();

        // 遍历导入数据
        for (GridHuinongStationImportVO importVO : importStations) {
            try {
                // 1. 数据验证
                String validationError = validateImportStation(importVO);
                if (validationError != null) {
                    failureStationCodes.put(importVO.getStationCode(), validationError);
                    continue;
                }

                // 2. 检查站点是否已存在
                GridHuinongStationDO existingStation = huinongStationMapper.selectByStationCode(importVO.getStationCode());
                boolean isUpdate = existingStation != null;

                // 如果站点已存在但不支持更新，记录失败
                if (isUpdate && !updateSupport) {
                    failureStationCodes.put(importVO.getStationCode(), "站点编号已存在，且未开启更新支持");
                    continue;
                }

                // 3. 构建站点 DO 对象
                GridHuinongStationDO station;
                if (isUpdate) {
                    station = existingStation;
                    // 更新字段
                    station.setStationName(importVO.getStationName());
                    station.setStationType(importVO.getStationType());
                    station.setAddress(importVO.getAddress());
                    station.setGridMarketingFlag(importVO.getGridMarketingFlag());
                    station.setContactPerson(importVO.getContactPerson());
                    station.setContactPhone(importVO.getContactPhone());
                    station.setSpecialistId(importVO.getSpecialistId());
                    station.setStatus(importVO.getStatus());
                } else {
                    station = GridHuinongStationDO.builder()
                            .stationCode(importVO.getStationCode())
                            .stationName(importVO.getStationName())
                            .stationType(importVO.getStationType())
                            .address(importVO.getAddress())
                            .gridMarketingFlag(importVO.getGridMarketingFlag())
                            .contactPerson(importVO.getContactPerson())
                            .contactPhone(importVO.getContactPhone())
                            .specialistId(importVO.getSpecialistId())
                            .status(importVO.getStatus())
                            .dataSource("IMPORTED")
                            .importBatch(importBatch)
                            .importTime(importTime)
                            .build();
                    // 设置父类字段（@Builder 不支持父类字段，需要单独设置）
                    station.setTenantId(TenantContextHolder.getTenantId());
                    station.setDeleted(false); // 设置逻辑删除标志为未删除
                    station.setCreateTime(LocalDateTime.now());
                    station.setUpdateTime(LocalDateTime.now());
                    // creator 和 updater 由 MyBatis Plus 自动填充
                }

                // 4. 创建圆形网格（半径 3000 米，中心为站点位置）
                Long gridId;
                if (isUpdate && station.getGridId() != null) {
                    // 如果是更新且已有网格，保持原有网格 ID
                    gridId = station.getGridId();
                } else {
                    // 生成网格编号和名称
                    String gridCode = "GRID_HN_" + importVO.getStationCode();
                    String gridName = importVO.getStationName() + "_网格";

                    // 创建网格 DO 对象
                    GridInfoDO grid = GridInfoDO.builder()
                            .gridCode(gridCode)
                            .gridName(gridName)
                            .gridType("HUINONG")
                            .orgId(1L) // 默认机构 ID，可根据实际情况调整
                            .radiusMeters(3000)
                            .status("ACTIVE")
                            .build();
                    // 设置父类字段（@Builder 不支持父类字段，需要单独设置）
                    grid.setTenantId(TenantContextHolder.getTenantId());
                    grid.setDeleted(false); // 设置逻辑删除标志为未删除
                    grid.setCreateTime(LocalDateTime.now());
                    grid.setUpdateTime(LocalDateTime.now());
                    // creator 和 updater 由 MyBatis Plus 自动填充

                    // 插入网格并设置空间数据
                    gridInfoMapper.insertCircularGrid(grid, importVO.getLongitude(), importVO.getLatitude(), 3000);
                    gridId = grid.getId();
                    createdGridCount++;
                }

                // 5. 关联站点与网格
                station.setGridId(gridId);

                // 6. 保存或更新站点
                if (isUpdate) {
                    huinongStationMapper.updateWithLocation(station, importVO.getLongitude(), importVO.getLatitude());
                    updateStationCodes.add(importVO.getStationCode());
                } else {
                    huinongStationMapper.insertWithLocation(station, importVO.getLongitude(), importVO.getLatitude());
                    createStationCodes.add(importVO.getStationCode());
                }

            } catch (Exception e) {
                // 记录失败原因
                failureStationCodes.put(importVO.getStationCode(), "导入失败: " + e.getMessage());
            }
        }

        // 7. 返回导入结果
        return GridHuinongStationImportRespVO.builder()
                .createStationCodes(createStationCodes)
                .updateStationCodes(updateStationCodes)
                .failureStationCodes(failureStationCodes)
                .createdGridCount(createdGridCount)
                .build();
    }

    /**
     * 验证导入站点数据
     * @param importVO 导入数据
     * @return 错误信息，如果验证通过返回 null
     */
    private String validateImportStation(GridHuinongStationImportVO importVO) {
        // 站点编号不能为空
        if (StrUtil.isBlank(importVO.getStationCode())) {
            return "站点编号不能为空";
        }

        // 站点名称不能为空
        if (StrUtil.isBlank(importVO.getStationName())) {
            return "站点名称不能为空";
        }

        // 经纬度不能为空
        if (importVO.getLongitude() == null || importVO.getLatitude() == null) {
            return "经纬度不能为空";
        }

        // 经度范围: -180 ~ 180
        if (importVO.getLongitude() < -180 || importVO.getLongitude() > 180) {
            return "经度范围必须在 -180 ~ 180 之间";
        }

        // 纬度范围: -90 ~ 90
        if (importVO.getLatitude() < -90 || importVO.getLatitude() > 90) {
            return "纬度范围必须在 -90 ~ 90 之间";
        }

        return null;
    }

    @Override
    public List<GridHuinongMarketingMarkerVO> getMarketingMarkers(Long stationId) {
        return huinongMarketingMapper.selectMarkersByStationId(stationId);
    }

    @Override
    public List<GridHuinongCustomerMarkerVO> getCustomerMarkers(Long stationId) {
        return huinongCustomerLoanMapper.selectMarkersByStationId(stationId);
    }

}