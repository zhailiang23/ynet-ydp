package cn.iocoder.yudao.module.aicrm.dal.mysql.companyaddress;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.companyaddress.CompanyAddressDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.companyaddress.vo.*;

/**
 * CRM对公客户地址信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyAddressMapper extends BaseMapperX<CompanyAddressDO> {

    default PageResult<CompanyAddressDO> selectPage(CompanyAddressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyAddressDO>()
                .eqIfPresent(CompanyAddressDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CompanyAddressDO::getAddressType, reqVO.getAddressType())
                .eqIfPresent(CompanyAddressDO::getIsPrimary, reqVO.getIsPrimary())
                .eqIfPresent(CompanyAddressDO::getAddressDetail, reqVO.getAddressDetail())
                .eqIfPresent(CompanyAddressDO::getPostalCode, reqVO.getPostalCode())
                .eqIfPresent(CompanyAddressDO::getSourceSystem, reqVO.getSourceSystem())
                .eqIfPresent(CompanyAddressDO::getCountryOrRegion, reqVO.getCountryOrRegion())
                .eqIfPresent(CompanyAddressDO::getProvinceCode, reqVO.getProvinceCode())
                .eqIfPresent(CompanyAddressDO::getCityCode, reqVO.getCityCode())
                .eqIfPresent(CompanyAddressDO::getCountyCode, reqVO.getCountyCode())
                .eqIfPresent(CompanyAddressDO::getTownCode, reqVO.getTownCode())
                .likeIfPresent(CompanyAddressDO::getTownName, reqVO.getTownName())
                .likeIfPresent(CompanyAddressDO::getStreetName, reqVO.getStreetName())
                .eqIfPresent(CompanyAddressDO::getVillageNo, reqVO.getVillageNo())
                .likeIfPresent(CompanyAddressDO::getVillageName, reqVO.getVillageName())
                .eqIfPresent(CompanyAddressDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(CompanyAddressDO::getAdminZone, reqVO.getAdminZone())
                .eqIfPresent(CompanyAddressDO::getEnAddress, reqVO.getEnAddress())
                .eqIfPresent(CompanyAddressDO::getContactMethod, reqVO.getContactMethod())
                .eqIfPresent(CompanyAddressDO::getAddressLevel, reqVO.getAddressLevel())
                .eqIfPresent(CompanyAddressDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(CompanyAddressDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CompanyAddressDO::getEtlDate, reqVO.getEtlDate())
                .eqIfPresent(CompanyAddressDO::getOldTxSeqNo, reqVO.getOldTxSeqNo())
                .eqIfPresent(CompanyAddressDO::getOldLastUpdateSys, reqVO.getOldLastUpdateSys())
                .eqIfPresent(CompanyAddressDO::getOldLastUpdateUser, reqVO.getOldLastUpdateUser())
                .eqIfPresent(CompanyAddressDO::getOldLastUpdateTm, reqVO.getOldLastUpdateTm())
                .eqIfPresent(CompanyAddressDO::getOldAddressId, reqVO.getOldAddressId())
                .orderByDesc(CompanyAddressDO::getId));
    }

}