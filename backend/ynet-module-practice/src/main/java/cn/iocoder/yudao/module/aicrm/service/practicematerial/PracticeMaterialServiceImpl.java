package cn.iocoder.yudao.module.aicrm.service.practicematerial;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicematerial.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.aicrm.dal.mysql.practicematerial.PracticeMaterialMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-培训文件 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PracticeMaterialServiceImpl implements PracticeMaterialService {

    @Resource
    private PracticeMaterialMapper practiceMaterialMapper;

    @Override
    public Long createPracticeMaterial(PracticeMaterialSaveReqVO createReqVO) {
        // 插入
        PracticeMaterialDO practiceMaterial = BeanUtils.toBean(createReqVO, PracticeMaterialDO.class);
        practiceMaterialMapper.insert(practiceMaterial);

        // 返回
        return practiceMaterial.getId();
    }

    @Override
    public void updatePracticeMaterial(PracticeMaterialSaveReqVO updateReqVO) {
        // 校验存在
        validatePracticeMaterialExists(updateReqVO.getId());
        // 更新
        PracticeMaterialDO updateObj = BeanUtils.toBean(updateReqVO, PracticeMaterialDO.class);
        practiceMaterialMapper.updateById(updateObj);
    }

    @Override
    public void deletePracticeMaterial(Long id) {
        // 校验存在
        validatePracticeMaterialExists(id);
        // 删除
        practiceMaterialMapper.deleteById(id);
    }

    @Override
        public void deletePracticeMaterialListByIds(List<Long> ids) {
        // 删除
        practiceMaterialMapper.deleteByIds(ids);
        }


    private void validatePracticeMaterialExists(Long id) {
        if (practiceMaterialMapper.selectById(id) == null) {
            throw exception(PRACTICE_MATERIAL_NOT_EXISTS);
        }
    }

    @Override
    public PracticeMaterialDO getPracticeMaterial(Long id) {
        return practiceMaterialMapper.selectById(id);
    }

    @Override
    public PageResult<PracticeMaterialDO> getPracticeMaterialPage(PracticeMaterialPageReqVO pageReqVO) {
        return practiceMaterialMapper.selectPage(pageReqVO);
    }

}