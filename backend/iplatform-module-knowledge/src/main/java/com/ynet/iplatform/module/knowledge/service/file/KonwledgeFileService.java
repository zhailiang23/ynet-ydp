package com.ynet.iplatform.module.knowledge.service.file;

import java.util.*;
import jakarta.validation.*;
import com.ynet.iplatform.module.knowledge.controller.admin.file.vo.*;
import com.ynet.iplatform.module.knowledge.dal.dataobject.file.KonwledgeFileDO;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.PageParam;

/**
 * 知识库文件 Service 接口
 *
 * @author 芋道源码
 */
public interface KonwledgeFileService {

    /**
     * 上传知识库文件
     *
     * @param content 文件内容
     * @param fileName 文件名
     * @param contentType 文件类型
     * @param baseId 知识库ID
     * @return 编号
     */
    Long uploadFile(byte[] content, String fileName, String contentType, Long baseId) throws Exception;

    /**
     * 创建知识库文件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createKonwledgeFile(@Valid KonwledgeFileSaveReqVO createReqVO);

    /**
     * 更新知识库文件
     *
     * @param updateReqVO 更新信息
     */
    void updateKonwledgeFile(@Valid KonwledgeFileSaveReqVO updateReqVO);

    /**
     * 删除知识库文件
     *
     * @param id 编号
     */
    void deleteKonwledgeFile(Long id);

    /**
    * 批量删除知识库文件
    *
    * @param ids 编号
    */
    void deleteKonwledgeFileListByIds(List<Long> ids);

    /**
     * 获得知识库文件
     *
     * @param id 编号
     * @return 知识库文件
     */
    KonwledgeFileDO getKonwledgeFile(Long id);

    /**
     * 获得知识库文件分页
     *
     * @param pageReqVO 分页查询
     * @return 知识库文件分页
     */
    PageResult<KonwledgeFileDO> getKonwledgeFilePage(KonwledgeFilePageReqVO pageReqVO);

}