package com.ynet.iplatform.module.product.convert.brand;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.module.product.controller.admin.brand.vo.ProductBrandCreateReqVO;
import com.ynet.iplatform.module.product.controller.admin.brand.vo.ProductBrandRespVO;
import com.ynet.iplatform.module.product.controller.admin.brand.vo.ProductBrandSimpleRespVO;
import com.ynet.iplatform.module.product.controller.admin.brand.vo.ProductBrandUpdateReqVO;
import com.ynet.iplatform.module.product.dal.dataobject.brand.ProductBrandDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 品牌 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    ProductBrandDO convert(ProductBrandCreateReqVO bean);

    ProductBrandDO convert(ProductBrandUpdateReqVO bean);

    ProductBrandRespVO convert(ProductBrandDO bean);

    List<ProductBrandSimpleRespVO> convertList1(List<ProductBrandDO> list);

    List<ProductBrandRespVO> convertList(List<ProductBrandDO> list);

    PageResult<ProductBrandRespVO> convertPage(PageResult<ProductBrandDO> page);

}
