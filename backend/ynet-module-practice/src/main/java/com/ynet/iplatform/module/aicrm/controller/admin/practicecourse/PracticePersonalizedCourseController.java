package com.ynet.iplatform.module.aicrm.controller.admin.practicecourse;

import com.ynet.iplatform.framework.common.exception.ServiceException;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.security.core.util.SecurityFrameworkUtils;
import com.ynet.iplatform.module.aicrm.controller.admin.practicecourse.vo.CreatePersonalizedCourseReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.practicecourse.vo.PersonalizedCourseRespVO;
import com.ynet.iplatform.module.aicrm.controller.admin.practicecourse.vo.PracticeCourseSaveReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.practicematerial.vo.PracticeMaterialSaveReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.practicescript.vo.PracticeScriptSaveReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.practicevirtualcustomer.vo.PracticeVirtualCustomerSaveReqVO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicecourse.PracticeCourseDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicescript.PracticeScriptDO;
import com.ynet.iplatform.module.aicrm.dal.dataobject.practicevirtualcustomer.PracticeVirtualCustomerDO;
import com.ynet.iplatform.module.aicrm.service.practicecourse.PracticeCourseService;
import com.ynet.iplatform.module.aicrm.service.practicematerial.DocumentTextExtractor;
import com.ynet.iplatform.module.aicrm.service.practicematerial.PracticeMaterialService;
import com.ynet.iplatform.module.aicrm.service.practicescript.PracticeScriptService;
import com.ynet.iplatform.module.aicrm.service.practicevirtualcustomer.PracticeVirtualCustomerService;
import com.ynet.iplatform.module.infra.api.file.FileApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;
import static com.ynet.iplatform.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;

/**
 * 个性化课程创建控制器
 *
 * @author AI CRM Team
 */
@Slf4j
@Tag(name = "管理后台 - CRM智能陪练-个性化课程")
@RestController
@RequestMapping("/aicrm/practice-course")
public class PracticePersonalizedCourseController {

    @Resource
    private PracticeVirtualCustomerService virtualCustomerService;

    @Resource
    private PracticeMaterialService materialService;

    @Resource
    private PracticeScriptService scriptService;

    @Resource
    private PracticeCourseService courseService;

    @Resource
    private DocumentTextExtractor textExtractor;

    @Resource
    private FileApi fileApi;

    @PostMapping("/create-personalized")
    @Operation(summary = "创建个性化课程", description = "基于上传的培训材料创建个性化课程")
    public CommonResult<PersonalizedCourseRespVO> createPersonalizedCourse(
            @ModelAttribute CreatePersonalizedCourseReqVO reqVO) {

        log.info("[createPersonalizedCourse] 用户 {} 开始创建个性化课程: courseName={}",
                SecurityFrameworkUtils.getLoginUserId(), reqVO.getCourseName());

        try {
            // 1-4. 在事务中创建基础数据
            Long[] ids = createBasicDataInTransaction(reqVO);
            Long scriptId = ids[0];
            Long courseId = ids[1];

            // 5. 事务提交后,调用AI服务生成剧本内容
            generateScriptContentAfterCommit(scriptId, reqVO);

            // 6. 获取完整数据并构建响应
            PracticeScriptDO script = scriptService.getPracticeScript(scriptId);
            PracticeCourseDO course = courseService.getPracticeCourse(courseId);
            Long virtualCustomerId = script.getVirtualCustomerId();
            String materialIds = script.getMaterialIds();
            PracticeMaterialDO material = (materialIds != null && !materialIds.isEmpty())
                ? materialService.getPracticeMaterial(Long.parseLong(materialIds))
                : null;

            return success(buildResponse(course, script, virtualCustomerId, material));

        } catch (Exception e) {
            log.error("[createPersonalizedCourse] 创建个性化课程失败: courseName={}", reqVO.getCourseName(), e);
            throw new ServiceException(BAD_REQUEST.getCode(), "创建个性化课程失败: " + e.getMessage());
        }
    }

    /**
     * 在事务中创建基础数据(虚拟客户、培训文件、剧本记录、课程记录)
     * @return 返回数组 [scriptId, courseId]
     */
    @Transactional(rollbackFor = Exception.class)
    private Long[] createBasicDataInTransaction(CreatePersonalizedCourseReqVO reqVO) {
        // 1. 处理虚拟客户
        Long virtualCustomerId = handleVirtualCustomer(reqVO);

        // 2. 处理培训文件
        PracticeMaterialDO material = handleTrainingFile(reqVO);

        // 3. 创建剧本记录(不生成内容)
        PracticeScriptDO script = createScriptRecord(reqVO, virtualCustomerId, material);

        // 4. 创建课程记录
        PracticeCourseDO course = createCourse(reqVO, script);

        log.info("[createBasicDataInTransaction] 基础数据创建完成: scriptId={}, courseId={}",
                script.getId(), course.getId());

        return new Long[]{script.getId(), course.getId()};
    }

    /**
     * 事务提交后生成剧本内容
     */
    private void generateScriptContentAfterCommit(Long scriptId, CreatePersonalizedCourseReqVO reqVO) {
        log.info("[generateScriptContentAfterCommit] 开始生成剧本内容: scriptId={}", scriptId);

        try {
            PracticeScriptDO script = scriptService.getPracticeScript(scriptId);
            scriptService.generateScriptContentSync(
                    scriptId,
                    reqVO.getCaseId(),
                    script.getMaterialIds(),
                    reqVO.getSkillId(),
                    reqVO.getMarketingLink(),
                    reqVO.getDifficultyLevel() != null ? reqVO.getDifficultyLevel() : "intermediate",
                    "个性化陪练剧本"
            );
            log.info("[generateScriptContentAfterCommit] 剧本内容生成成功: scriptId={}", scriptId);
        } catch (Exception e) {
            log.error("[generateScriptContentAfterCommit] 剧本内容生成失败: scriptId={}", scriptId, e);
            // 注意: 这里不抛出异常,因为基础数据已经创建成功,只是剧本内容生成失败
            // 用户可以稍后重新生成剧本内容
        }
    }

    /**
     * 处理虚拟客户(创建新客户或使用现有客户)
     */
    private Long handleVirtualCustomer(CreatePersonalizedCourseReqVO reqVO) {
        if (Boolean.TRUE.equals(reqVO.getCreateNewCustomer())) {
            // 创建新虚拟客户
            CreatePersonalizedCourseReqVO.VirtualCustomerCreateReqVO customerInfo = reqVO.getCustomerInfo();
            if (customerInfo == null) {
                throw new ServiceException(BAD_REQUEST.getCode(), "创建新客户时,客户信息不能为空");
            }

            PracticeVirtualCustomerSaveReqVO saveReqVO = new PracticeVirtualCustomerSaveReqVO();
            saveReqVO.setName(customerInfo.getName());
            saveReqVO.setGender(customerInfo.getGender());
            saveReqVO.setAge(customerInfo.getAge());
            saveReqVO.setOccupation(customerInfo.getOccupation());
            saveReqVO.setIndustry(customerInfo.getIndustry());
            saveReqVO.setPersonalityType(customerInfo.getPersonalityType());
            saveReqVO.setRiskPreference(customerInfo.getRiskPreference());
            saveReqVO.setMemo(customerInfo.getMemo());

            Long customerId = virtualCustomerService.createPracticeVirtualCustomer(saveReqVO);
            log.info("[handleVirtualCustomer] 创建新虚拟客户成功: customerId={}, name={}",
                    customerId, customerInfo.getName());
            return customerId;
        } else {
            // 使用现有客户
            if (reqVO.getExistingCustomerId() == null) {
                throw new ServiceException(BAD_REQUEST.getCode(), "选择现有客户时,客户ID不能为空");
            }

            PracticeVirtualCustomerDO customer = virtualCustomerService.getPracticeVirtualCustomer(reqVO.getExistingCustomerId());
            if (customer == null) {
                throw new ServiceException(BAD_REQUEST.getCode(), "虚拟客户不存在");
            }

            log.info("[handleVirtualCustomer] 使用现有虚拟客户: customerId={}", reqVO.getExistingCustomerId());
            return reqVO.getExistingCustomerId();
        }
    }

    /**
     * 处理培训文件(上传文件并抽取文本)
     */
    private PracticeMaterialDO handleTrainingFile(CreatePersonalizedCourseReqVO reqVO) {
        if (reqVO.getTrainingFile() == null || reqVO.getTrainingFile().isEmpty()) {
            log.info("[handleTrainingFile] 未上传培训文件,跳过文件处理");
            return null;
        }

        try {
            // 验证文件格式
            String filename = reqVO.getTrainingFile().getOriginalFilename();
            if (!textExtractor.isSupportedFormat(filename)) {
                throw new ServiceException(BAD_REQUEST.getCode(), "不支持的文件格式,仅支持 Word、PDF、TXT 文件");
            }

            // 上传文件到文件服务
            String fileUrl = fileApi.createFile(
                    reqVO.getTrainingFile().getBytes(),
                    filename
            );

            // 抽取文本内容
            String content;
            boolean contentExtracted = true;
            try {
                content = textExtractor.extractText(reqVO.getTrainingFile());
            } catch (Exception e) {
                log.warn("[handleTrainingFile] 文件文本抽取失败,但继续创建课程: filename={}", filename, e);
                content = "";
                contentExtracted = false;
            }

            // 创建培训材料记录
            PracticeMaterialSaveReqVO materialSaveReqVO = new PracticeMaterialSaveReqVO();
            materialSaveReqVO.setName(filename);
            materialSaveReqVO.setFileType("training_manual");
            materialSaveReqVO.setContent(content);
            materialSaveReqVO.setFileUrl(fileUrl);
            materialSaveReqVO.setFileSize(reqVO.getTrainingFile().getSize());

            Long materialId = materialService.createPracticeMaterial(materialSaveReqVO);

            // 获取创建后的完整记录
            PracticeMaterialDO material = materialService.getPracticeMaterial(materialId);

            log.info("[handleTrainingFile] 培训文件处理完成: materialId={}, filename={}, contentExtracted={}",
                    materialId, filename, contentExtracted);

            return material;

        } catch (Exception e) {
            log.error("[handleTrainingFile] 培训文件处理失败", e);
            throw new ServiceException(BAD_REQUEST.getCode(), "培训文件处理失败: " + e.getMessage());
        }
    }

    /**
     * 创建剧本记录(不生成内容)
     */
    private PracticeScriptDO createScriptRecord(CreatePersonalizedCourseReqVO reqVO,
                                          Long virtualCustomerId,
                                          PracticeMaterialDO material) {
        PracticeScriptSaveReqVO scriptSaveReqVO = new PracticeScriptSaveReqVO();
        scriptSaveReqVO.setName(reqVO.getCourseName() + "剧本");
        scriptSaveReqVO.setDescription("个性化陪练剧本");
        scriptSaveReqVO.setVirtualCustomerId(virtualCustomerId);
        scriptSaveReqVO.setMarketingStep(reqVO.getMarketingLink());

        // 使用请求参数中的难度等级,默认为 medium
        scriptSaveReqVO.setDifficultyLevel(reqVO.getDifficultyLevel() != null ? reqVO.getDifficultyLevel() : "intermediate");

        if (material != null) {
            scriptSaveReqVO.setMaterialIds(material.getId().toString());
        }

        // 使用请求参数中的案例ID和技巧ID
        scriptSaveReqVO.setCaseId(reqVO.getCaseId());
        scriptSaveReqVO.setSkillId(reqVO.getSkillId());

        Long scriptId = scriptService.createPracticeScript(scriptSaveReqVO);

        log.info("[createScriptRecord] 剧本记录创建成功: scriptId={}", scriptId);

        // 获取刚创建的剧本记录
        PracticeScriptDO script = scriptService.getPracticeScript(scriptId);
        return script;
    }

    /**
     * 创建课程
     */
    private PracticeCourseDO createCourse(CreatePersonalizedCourseReqVO reqVO, PracticeScriptDO script) {
        PracticeCourseSaveReqVO courseSaveReqVO = new PracticeCourseSaveReqVO();
        courseSaveReqVO.setName(reqVO.getCourseName());
        courseSaveReqVO.setDescription(reqVO.getCourseDescription());
        courseSaveReqVO.setScriptId(script.getId());
        courseSaveReqVO.setStandard(0L); // 0表示个性化课程
        courseSaveReqVO.setHard(0L); // 0表示简单
        courseSaveReqVO.setStatus("active");

        Long courseId = courseService.createPracticeCourse(courseSaveReqVO);
        PracticeCourseDO course = courseService.getPracticeCourse(courseId);

        log.info("[createCourse] 课程创建成功: courseId={}, courseName={}", courseId, course.getName());
        return course;
    }

    /**
     * 构建响应数据
     */
    private PersonalizedCourseRespVO buildResponse(PracticeCourseDO course,
                                                    PracticeScriptDO script,
                                                    Long virtualCustomerId,
                                                    PracticeMaterialDO material) {
        PersonalizedCourseRespVO resp = new PersonalizedCourseRespVO();

        // 课程信息
        resp.setCourseId(course.getId());
        resp.setCourseName(course.getName());
        resp.setCourseDescription(course.getDescription());
        resp.setCourseType(course.getStandard() == 1L ? "标准课程" : "个性化课程");
        resp.setComplexityLevel(course.getHard() == 1L ? "复杂" : "简单");
        resp.setStatus(course.getStatus());
        resp.setCreateTime(course.getCreateTime());

        // 剧本信息
        resp.setScriptId(script.getId());
        resp.setScriptName(script.getName());
        resp.setScriptVersion(script.getVersion());

        // 虚拟客户信息
        resp.setVirtualCustomerId(virtualCustomerId);
        PracticeVirtualCustomerDO customer = virtualCustomerService.getPracticeVirtualCustomer(virtualCustomerId);
        if (customer != null) {
            resp.setVirtualCustomerName(customer.getName());
        }

        // 培训材料信息
        if (material != null) {
            resp.setMaterialId(material.getId());
            resp.setMaterialName(material.getName());
            resp.setMaterialUrl(material.getFileUrl());
            resp.setContentExtracted(material.getContent() != null && !material.getContent().isEmpty());

            if (!resp.getContentExtracted()) {
                resp.setWarningMessage("文件文本抽取失败,但课程创建成功");
            }
        }

        return resp;
    }
}
