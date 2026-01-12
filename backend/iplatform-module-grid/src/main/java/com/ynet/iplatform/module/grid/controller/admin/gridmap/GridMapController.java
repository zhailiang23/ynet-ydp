package com.ynet.iplatform.module.grid.controller.admin.gridmap;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.module.grid.controller.admin.gridmap.vo.GridMapDataReqVO;
import com.ynet.iplatform.module.grid.controller.admin.gridmap.vo.GridMapDataRespVO;
import com.ynet.iplatform.module.grid.service.gridmap.GridMapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 网格营销综合地图")
@RestController
@RequestMapping("/grid/map")
@Validated
public class GridMapController {

    @Resource
    private GridMapService gridMapService;

    @PostMapping("/data")
    @Operation(summary = "获取综合地图数据")
    public CommonResult<GridMapDataRespVO> getMapData(@Valid @RequestBody GridMapDataReqVO reqVO) {
        GridMapDataRespVO data = gridMapService.getMapData(reqVO);
        return success(data);
    }

}
