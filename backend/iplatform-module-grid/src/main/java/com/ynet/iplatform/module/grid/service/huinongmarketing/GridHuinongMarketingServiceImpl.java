package com.ynet.iplatform.module.grid.service.huinongmarketing;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo.*;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongmarketing.GridHuinongMarketingDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.mybatis.core.util.MyBatisUtils;

import com.ynet.iplatform.module.grid.dal.mysql.huinongmarketing.GridHuinongMarketingMapper;
import com.ynet.iplatform.module.grid.dal.mysql.huinongstation.GridHuinongStationMapper;
import com.ynet.iplatform.module.grid.dal.dataobject.huinongstation.GridHuinongStationDO;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.*;

/**
 * 惠农网格营销信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
@Slf4j
public class GridHuinongMarketingServiceImpl implements GridHuinongMarketingService {

    @Resource
    private GridHuinongMarketingMapper huinongMarketingMapper;

    @Resource
    private GridHuinongStationMapper huinongStationMapper;

    @Override
    public Long createHuinongMarketing(GridHuinongMarketingSaveReqVO createReqVO) {
        // 如果 gridId 为空，从站点信息中获取
        if (createReqVO.getGridId() == null && createReqVO.getStationId() != null) {
            GridHuinongStationDO station = huinongStationMapper.selectById(createReqVO.getStationId());
            if (station != null && station.getGridId() != null) {
                createReqVO.setGridId(station.getGridId());
            }
        }

        // 插入
        GridHuinongMarketingDO huinongMarketing = BeanUtils.toBean(createReqVO, GridHuinongMarketingDO.class);
        huinongMarketingMapper.insert(huinongMarketing);

        // 返回
        return huinongMarketing.getId();
    }

    @Override
    public void updateHuinongMarketing(GridHuinongMarketingSaveReqVO updateReqVO) {
        // 校验存在
        validateHuinongMarketingExists(updateReqVO.getId());

        // 如果 gridId 为空，从站点信息中获取
        if (updateReqVO.getGridId() == null && updateReqVO.getStationId() != null) {
            GridHuinongStationDO station = huinongStationMapper.selectById(updateReqVO.getStationId());
            if (station != null && station.getGridId() != null) {
                updateReqVO.setGridId(station.getGridId());
            }
        }

        // 更新
        GridHuinongMarketingDO updateObj = BeanUtils.toBean(updateReqVO, GridHuinongMarketingDO.class);
        huinongMarketingMapper.updateById(updateObj);
    }

    @Override
    public void deleteHuinongMarketing(Long id) {
        // 校验存在
        validateHuinongMarketingExists(id);
        // 删除
        huinongMarketingMapper.deleteById(id);
    }

    @Override
        public void deleteHuinongMarketingListByIds(List<Long> ids) {
        // 删除
        huinongMarketingMapper.deleteByIds(ids);
        }


    private void validateHuinongMarketingExists(Long id) {
        if (huinongMarketingMapper.selectById(id) == null) {
            throw exception(HUINONG_MARKETING_NOT_EXISTS);
        }
    }

    @Override
    public GridHuinongMarketingDO getHuinongMarketing(Long id) {
        return huinongMarketingMapper.selectById(id);
    }

    @Override
    public PageResult<GridHuinongMarketingRespVO> getHuinongMarketingPage(GridHuinongMarketingPageReqVO pageReqVO) {
        // 1. 构建 MyBatis Plus 分页对象
        IPage<GridHuinongMarketingRespVO> mpPage = MyBatisUtils.buildPage(pageReqVO);

        // 2. 执行分页查询
        mpPage = huinongMarketingMapper.selectPageWithRelations(mpPage, pageReqVO);

        // 3. 转换为框架的 PageResult
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    @Override
    public GridHuinongMarketingHeatmapStatisticsVO getHeatmapStatistics(GridHuinongMarketingHeatmapReqVO reqVO) {
        // 1. 查询所有符合条件的营销信息（带站点关联）
        List<GridHuinongMarketingDO> marketingList = huinongMarketingMapper.selectHeatmapMarketingList(reqVO);

        // 2. 初始化统计变量
        Set<Long> requiredStationIds = new HashSet<>();
        Set<Long> optionalStationIds = new HashSet<>();
        Set<String> requiredVillages = new HashSet<>();
        Set<String> optionalVillages = new HashSet<>();
        int requiredPopulation = 0;
        int optionalPopulation = 0;
        Map<String, Integer> requiredCustomerMap = new HashMap<>();
        Map<String, Integer> optionalCustomerMap = new HashMap<>();
        Map<String, Integer> allCustomerTypeMap = new HashMap<>();

        // 3. 创建 ObjectMapper 用于 JSON 解析
        ObjectMapper objectMapper = new ObjectMapper();

        // 4. 遍历营销信息进行统计
        for (GridHuinongMarketingDO marketing : marketingList) {
            String stationType = marketing.getGridMarketingFlag(); // 从关联的站点获取
            boolean isRequired = "REQUIRED".equals(stationType);

            // 统计站点和村
            if (isRequired) {
                requiredStationIds.add(marketing.getStationId());
                requiredVillages.add(marketing.getVillageName());
                requiredPopulation += (marketing.getRegisteredPopulation() != null ? marketing.getRegisteredPopulation() : 0);
            } else {
                optionalStationIds.add(marketing.getStationId());
                optionalVillages.add(marketing.getVillageName());
                optionalPopulation += (marketing.getRegisteredPopulation() != null ? marketing.getRegisteredPopulation() : 0);
            }

            // 5. 解析客户类型和数量（JSON）
            if (marketing.getCustomerType() != null && marketing.getCustomerCount() != null) {
                try {
                    List<String> customerTypes = objectMapper.readValue(
                        marketing.getCustomerType(), new TypeReference<List<String>>() {});
                    Map<String, Integer> customerCounts = objectMapper.readValue(
                        marketing.getCustomerCount(), new TypeReference<Map<String, Integer>>() {});

                    // 汇总客群数量
                    for (Map.Entry<String, Integer> entry : customerCounts.entrySet()) {
                        String type = entry.getKey();
                        Integer count = entry.getValue();

                        allCustomerTypeMap.merge(type, count, Integer::sum);

                        if (isRequired) {
                            requiredCustomerMap.merge(type, count, Integer::sum);
                        } else {
                            optionalCustomerMap.merge(type, count, Integer::sum);
                        }
                    }
                } catch (Exception e) {
                    log.error("解析客户类型JSON失败: id={}, error={}", marketing.getId(), e.getMessage());
                }
            }
        }

        // 6. 计算客群总数
        int requiredCustomerTotal = requiredCustomerMap.values().stream().mapToInt(Integer::intValue).sum();
        int optionalCustomerTotal = optionalCustomerMap.values().stream().mapToInt(Integer::intValue).sum();

        // 7. 组装返回结果
        return GridHuinongMarketingHeatmapStatisticsVO.builder()
            .totalStations(requiredStationIds.size() + optionalStationIds.size())
            .requiredStations(requiredStationIds.size())
            .optionalStations(optionalStationIds.size())
            .requiredStatistics(GridHuinongMarketingHeatmapStatisticsVO.StationStatistics.builder()
                .villageCount(requiredVillages.size())
                .totalPopulation(requiredPopulation)
                .totalCustomerCount(requiredCustomerTotal)
                .build())
            .optionalStatistics(GridHuinongMarketingHeatmapStatisticsVO.StationStatistics.builder()
                .villageCount(optionalVillages.size())
                .totalPopulation(optionalPopulation)
                .totalCustomerCount(optionalCustomerTotal)
                .build())
            .customerTypeStatistics(allCustomerTypeMap)
            .build();
    }

    @Override
    public List<GridHuinongMarketingHeatmapDataVO> getHeatmapData(GridHuinongMarketingHeatmapReqVO reqVO) {
        // 查询原始营销信息列表
        List<GridHuinongMarketingDO> marketingList = huinongMarketingMapper.selectHeatmapMarketingList(reqVO);

        // 按位置分组，计算每个位置的客户总数
        Map<String, GridHuinongMarketingHeatmapDataVO> locationMap = new HashMap<>();

        for (GridHuinongMarketingDO marketing : marketingList) {
            String location = marketing.getVillageLocation();
            if (location == null || location.isEmpty()) {
                continue;
            }

            // 解析位置坐标
            String[] coords = location.split(",");
            if (coords.length != 2) {
                continue;
            }

            double longitude;
            double latitude;
            try {
                longitude = Double.parseDouble(coords[0].trim());
                latitude = Double.parseDouble(coords[1].trim());
            } catch (NumberFormatException e) {
                continue;
            }

            // 解析客户数量JSON，计算总数
            int customerCount = 0;
            String customerCountJson = marketing.getCustomerCount();
            if (customerCountJson != null && !customerCountJson.isEmpty()) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Integer> countMap = objectMapper.readValue(
                        customerCountJson,
                        new TypeReference<Map<String, Integer>>() {}
                    );
                    // 累加所有客群的数量
                    customerCount = countMap.values().stream()
                        .mapToInt(Integer::intValue)
                        .sum();
                } catch (Exception e) {
                    // JSON解析失败，跳过
                    continue;
                }
            }

            // 按位置累加客户数量
            GridHuinongMarketingHeatmapDataVO dataVO = locationMap.get(location);
            if (dataVO == null) {
                dataVO = new GridHuinongMarketingHeatmapDataVO();
                dataVO.setLongitude(longitude);
                dataVO.setLatitude(latitude);
                dataVO.setCount(customerCount);
                dataVO.setVillageName(marketing.getVillageName());
                locationMap.put(location, dataVO);
            } else {
                // 同一位置累加客户数量
                dataVO.setCount(dataVO.getCount() + customerCount);
            }
        }

        // 返回结果列表，按客户数量降序排列
        return locationMap.values().stream()
            .sorted((a, b) -> Integer.compare(b.getCount(), a.getCount()))
            .collect(Collectors.toList());
    }

}