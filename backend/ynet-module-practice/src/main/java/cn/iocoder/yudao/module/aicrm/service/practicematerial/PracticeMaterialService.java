package cn.iocoder.yudao.module.aicrm.service.practicematerial;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.aicrm.controller.admin.practicematerial.vo.*;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.practicematerial.PracticeMaterialDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * CRM智能陪练-培训文件 Service 接口
 *
 * @author 芋道源码
 */
public interface PracticeMaterialService {

    /**
     * 创建CRM智能陪练-培训文件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPracticeMaterial(@Valid PracticeMaterialSaveReqVO createReqVO);

    /**
     * 更新CRM智能陪练-培训文件
     *
     * @param updateReqVO 更新信息
     */
    void updatePracticeMaterial(@Valid PracticeMaterialSaveReqVO updateReqVO);

    /**
     * 删除CRM智能陪练-培训文件
     *
     * @param id 编号
     */
    void deletePracticeMaterial(Long id);

    /**
    * 批量删除CRM智能陪练-培训文件
    *
    * @param ids 编号
    */
    void deletePracticeMaterialListByIds(List<Long> ids);

    /**
     * 获得CRM智能陪练-培训文件
     *
     * @param id 编号
     * @return CRM智能陪练-培训文件
     */
    PracticeMaterialDO getPracticeMaterial(Long id);

    /**
     * 获得CRM智能陪练-培训文件分页
     *
     * @param pageReqVO 分页查询
     * @return CRM智能陪练-培训文件分页
     */
    PageResult<PracticeMaterialDO> getPracticeMaterialPage(PracticeMaterialPageReqVO pageReqVO);

    /**
     * 上传文件并创建培训材料记录
     * 支持Word、PDF、TXT格式，并自动抽取文件内容
     *
     * @param file 上传的文件
     * @param materialName 材料名称
     * @param materialType 材料类型（如：培训手册）
     * @param description 材料描述
     * @return 创建的培训材料记录
     */
    PracticeMaterialDO uploadTrainingFile(MultipartFile file, String materialName,
                                          String materialType, String description);

    /**
     * 验证文件格式是否支持
     *
     * @param file 上传的文件
     * @return 是否支持
     */
    boolean isFileFormatSupported(MultipartFile file);

}