package cn.iocoder.yudao.module.aicrm.dal.mysql.practicecase;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicecase.PracticeCaseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicecase.vo.*;

/**
 * CRM智能陪练-销售案例 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PracticeCaseMapper extends BaseMapperX<PracticeCaseDO> {

    default PageResult<PracticeCaseDO> selectPage(PracticeCasePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PracticeCaseDO>()
                .eqIfPresent(PracticeCaseDO::getTitle, reqVO.getTitle())
                .eqIfPresent(PracticeCaseDO::getContent, reqVO.getContent())
                .eqIfPresent(PracticeCaseDO::getTags, reqVO.getTags())
                .betweenIfPresent(PracticeCaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PracticeCaseDO::getId));
    }

}