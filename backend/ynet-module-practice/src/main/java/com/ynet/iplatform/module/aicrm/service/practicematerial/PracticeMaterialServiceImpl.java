package com.ynet.iplatform.module.aicrm.service.practicematerial;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import com.ynet.iplatform.module.aicrm.controller.admin.practicematerial.vo.*;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;

import com.ynet.iplatform.module.aicrm.dal.mysql.practicematerial.PracticeMaterialMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicescript.PracticeScriptMapper;
import com.ynet.iplatform.module.aicrm.dal.mysql.practicecourse.PracticeCourseMapper;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.convertList;
import static com.ynet.iplatform.framework.common.util.collection.CollectionUtils.diffList;
import static com.ynet.iplatform.module.aicrm.enums.ErrorCodeConstants.*;

/**
 * CRM智能陪练-培训文件 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class PracticeMaterialServiceImpl implements PracticeMaterialService {

    @Resource
    private PracticeMaterialMapper practiceMaterialMapper;

    @Resource
    private PracticeScriptMapper practiceScriptMapper;

    @Resource
    private PracticeCourseMapper practiceCourseMapper;

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
        // 自动设置为当前登录用户的创建者ID,只查询当前用户上传的文件
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        if (loginUserId != null) {
            pageReqVO.setCreator(String.valueOf(loginUserId));
        }
        PageResult<PracticeMaterialDO> pageResult = practiceMaterialMapper.selectPage(pageReqVO);

        // 为每个文件查找关联的课程名称
        if (CollUtil.isNotEmpty(pageResult.getList())) {
            pageResult.getList().forEach(material -> {
                String courseName = findCourseNameByMaterialId(material.getId());
                material.setCourseName(courseName);
            });
        }

        return pageResult;
    }

    /**
     * 根据文件ID查找关联的课程名称
     * 逻辑:
     * 1. 查找文件关联的剧本(materialIds字段包含该文件ID)
     * 2. 如果有多个剧本,选择最近创建的
     * 3. 根据剧本ID查找关联的课程
     * 4. 如果有多个课程,选择最近创建的
     *
     * @param materialId 文件ID
     * @return 课程名称,如果没有关联课程则返回null
     */
    private String findCourseNameByMaterialId(Long materialId) {
        if (materialId == null) {
            return null;
        }

        // 1. 查找包含该文件ID的剧本,按创建时间降序排序,取最新的一个
        QueryWrapper<PracticeScriptDO> scriptQueryWrapper = new QueryWrapper<>();
        scriptQueryWrapper.like("material_ids", materialId.toString())
                .orderByDesc("create_time")
                .last("LIMIT 1");
        PracticeScriptDO script = practiceScriptMapper.selectOne(scriptQueryWrapper);

        if (script == null) {
            return null;
        }

        // 2. 根据剧本ID查找关联的课程,按创建时间降序排序,取最新的一个
        QueryWrapper<PracticeCourseDO> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("script_id", script.getId())
                .orderByDesc("create_time")
                .last("LIMIT 1");
        PracticeCourseDO course = practiceCourseMapper.selectOne(courseQueryWrapper);

        return course != null ? course.getName() : null;
    }

    @Override
    public PracticeMaterialDO uploadTrainingFile(MultipartFile file, String materialName,
                                                   String materialType, String description) {
        // TODO: 实现文件上传和内容提取逻辑
        // 这里先返回一个临时实现
        PracticeMaterialDO material = new PracticeMaterialDO();
        material.setName(materialName);
        material.setFileType(materialType);
        material.setFileUrl(file.getOriginalFilename());

        practiceMaterialMapper.insert(material);
        return material;
    }

    @Override
    public boolean isFileFormatSupported(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String filename = file.getOriginalFilename();
        if (filename == null) {
            return false;
        }

        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("doc") || extension.equals("docx") ||
               extension.equals("pdf") || extension.equals("txt");
    }

}