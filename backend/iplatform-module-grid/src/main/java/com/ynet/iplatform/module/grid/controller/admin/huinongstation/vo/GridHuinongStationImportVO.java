package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import cn.idev.excel.annotation.ExcelProperty;
import com.ynet.iplatform.framework.excel.core.annotations.DictFormat;
import com.ynet.iplatform.framework.excel.core.convert.DictConvert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 惠农站点信息 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GridHuinongStationImportVO {

    @ExcelProperty("站点编号")
    private String stationCode;

    @ExcelProperty("站点名称")
    private String stationName;

    @ExcelProperty("站点类型")
    private String stationType;

    @ExcelProperty("经度")
    private Double longitude;

    @ExcelProperty("纬度")
    private Double latitude;

    @ExcelProperty("站点地址")
    private String address;

    @ExcelProperty(value = "网格营销站点", converter = DictConvert.class)
    @DictFormat("grid_marketing_flag")
    private String gridMarketingFlag;

    @ExcelProperty("联系人")
    private String contactPerson;

    @ExcelProperty("联系电话")
    private String contactPhone;

    @ExcelProperty("负责惠农专员ID")
    private Long specialistId;

    @ExcelProperty(value = "站点状态", converter = DictConvert.class)
    @DictFormat("common_status")
    private String status;

}
