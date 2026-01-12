package com.ynet.iplatform.module.grid.dal.mysql.customergridrelation;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.grid.dal.dataobject.customergridrelation.GridCustomerGridRelationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 客户-网格关系 Mapper
 *
 * @author ynet
 */
@Mapper
public interface GridCustomerGridRelationMapper extends BaseMapperX<GridCustomerGridRelationDO> {

    /**
     * 根据客户ID查询所有关联的网格
     */
    default List<GridCustomerGridRelationDO> selectListByCustomerId(Long customerId) {
        return selectList(GridCustomerGridRelationDO::getCustomerId, customerId);
    }

    /**
     * 根据网格ID查询所有关联的客户ID
     */
    default List<Long> selectCustomerIdsByGridId(Long gridId) {
        return selectList(GridCustomerGridRelationDO::getGridId, gridId)
                .stream()
                .map(GridCustomerGridRelationDO::getCustomerId)
                .toList();
    }

    /**
     * 删除客户的某个网格关联（逻辑删除）
     */
    default int deleteByCustomerIdAndGridId(Long customerId, Long gridId) {
        return delete(new LambdaQueryWrapperX<GridCustomerGridRelationDO>()
                .eq(GridCustomerGridRelationDO::getCustomerId, customerId)
                .eq(GridCustomerGridRelationDO::getGridId, gridId));
    }

    /**
     * 物理删除客户的 COMMUNITY 类型网格关系
     * 用于更新客户时清除旧关系，避免唯一索引冲突
     */
    int physicalDeleteCommunityRelationsByCustomerId(Long customerId);

    /**
     * 物理删除客户的 ZERODAI 类型网格关系
     * 用于更新客户时清除旧关系，避免唯一索引冲突
     */
    int physicalDeleteZerodaiRelationsByCustomerId(Long customerId);

    /**
     * 物理删除客户的 HUINONG 类型网格关系
     * 用于更新客户时清除旧关系，避免唯一索引冲突
     */
    int physicalDeleteHuinongRelationsByCustomerId(Long customerId);

    /**
     * 物理删除客户的 LOBBY（厅堂）类型网格关系
     * 用于更新客户时清除旧关系，避免唯一索引冲突
     */
    int physicalDeleteLobbyRelationsByCustomerId(Long customerId);
}
