package com.ynet.iplatform.module.grid.dal.dataobject.huinongextension;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 惠农推广 DO
 *
 * @author 易诚源码
 */
@TableName("grid_huinong_extension")
@KeySequence("grid_huinong_extension_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridHuinongExtensionDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 网格ID
     */
    private Long gridId;
    /**
     * 是否营销站点
     */
    private Boolean isMarketingSite;
    /**
     * 是否必选
     */
    private Boolean isRequired;

}
