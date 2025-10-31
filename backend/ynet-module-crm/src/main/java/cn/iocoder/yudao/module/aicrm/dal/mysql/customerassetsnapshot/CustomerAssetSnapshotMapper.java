package cn.iocoder.yudao.module.aicrm.dal.mysql.customerassetsnapshot;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customerassetsnapshot.CustomerAssetSnapshotDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * 客户资产快照表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerAssetSnapshotMapper extends BaseMapperX<CustomerAssetSnapshotDO> {

    /**
     * 查询客户在指定时间范围内的资产快照
     */
    default List<CustomerAssetSnapshotDO> selectByCustomerIdAndDateRange(Long customerId, LocalDate startDate, LocalDate endDate) {
        return selectList(new LambdaQueryWrapperX<CustomerAssetSnapshotDO>()
                .eq(CustomerAssetSnapshotDO::getCustomerId, customerId)
                .ge(startDate != null, CustomerAssetSnapshotDO::getSnapshotDate, startDate)
                .le(endDate != null, CustomerAssetSnapshotDO::getSnapshotDate, endDate)
                .orderByAsc(CustomerAssetSnapshotDO::getSnapshotDate));
    }

    /**
     * 查询客户最近N个月的资产快照
     */
    default List<CustomerAssetSnapshotDO> selectRecentMonths(Long customerId, int months) {
        return selectList(new LambdaQueryWrapperX<CustomerAssetSnapshotDO>()
                .eq(CustomerAssetSnapshotDO::getCustomerId, customerId)
                .orderByDesc(CustomerAssetSnapshotDO::getSnapshotDate)
                .last("LIMIT " + months));
    }

}
