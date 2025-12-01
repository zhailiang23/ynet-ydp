package com.ynet.iplatform.module.knowledge.controller.admin.file;

import cn.hutool.core.io.IoUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

import com.ynet.iplatform.framework.excel.core.util.ExcelUtils;

import com.ynet.iplatform.framework.apilog.core.annotation.ApiAccessLog;
import static com.ynet.iplatform.framework.apilog.core.enums.OperateTypeEnum.*;

import com.ynet.iplatform.module.knowledge.controller.admin.file.vo.*;
import com.ynet.iplatform.module.knowledge.dal.dataobject.file.KonwledgeFileDO;
import com.ynet.iplatform.module.knowledge.service.file.KonwledgeFileService;
import com.ynet.iplatform.module.knowledge.client.rag.RagServiceClient;
import cn.hutool.json.JSONObject;

@Tag(name = "管理后台 - 知识库文件")
@RestController
@RequestMapping("/knowledge/konwledge-file")
@Validated
public class KonwledgeFileController {

    @Resource
    private KonwledgeFileService konwledgeFileService;

    @Resource
    private RagServiceClient ragServiceClient;

    @PostMapping("/upload")
    @Operation(summary = "上传知识库文件")
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:create')")
    public CommonResult<Long> uploadFile(@RequestParam("file") MultipartFile file,
                                          @RequestParam("baseId") Long baseId) throws Exception {
        // 读取文件内容
        byte[] content = IoUtil.readBytes(file.getInputStream());

        // 创建文件记录
        return success(konwledgeFileService.uploadFile(content, file.getOriginalFilename(),
                file.getContentType(), baseId));
    }

    @PostMapping("/create")
    @Operation(summary = "创建知识库文件")
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:create')")
    public CommonResult<Long> createKonwledgeFile(@Valid @RequestBody KonwledgeFileSaveReqVO createReqVO) {
        return success(konwledgeFileService.createKonwledgeFile(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新知识库文件")
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:update')")
    public CommonResult<Boolean> updateKonwledgeFile(@Valid @RequestBody KonwledgeFileSaveReqVO updateReqVO) {
        konwledgeFileService.updateKonwledgeFile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除知识库文件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:delete')")
    public CommonResult<Boolean> deleteKonwledgeFile(@RequestParam("id") Long id) {
        konwledgeFileService.deleteKonwledgeFile(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除知识库文件")
                @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:delete')")
    public CommonResult<Boolean> deleteKonwledgeFileList(@RequestParam("ids") List<Long> ids) {
        konwledgeFileService.deleteKonwledgeFileListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得知识库文件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:query')")
    public CommonResult<KonwledgeFileRespVO> getKonwledgeFile(@RequestParam("id") Long id) {
        KonwledgeFileDO konwledgeFile = konwledgeFileService.getKonwledgeFile(id);
        return success(BeanUtils.toBean(konwledgeFile, KonwledgeFileRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得知识库文件分页")
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:query')")
    public CommonResult<PageResult<KonwledgeFileRespVO>> getKonwledgeFilePage(@Valid KonwledgeFilePageReqVO pageReqVO) {
        PageResult<KonwledgeFileDO> pageResult = konwledgeFileService.getKonwledgeFilePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KonwledgeFileRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出知识库文件 Excel")
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportKonwledgeFileExcel(@Valid KonwledgeFilePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<KonwledgeFileDO> list = konwledgeFileService.getKonwledgeFilePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "知识库文件.xls", "数据", KonwledgeFileRespVO.class,
                        BeanUtils.toBean(list, KonwledgeFileRespVO.class));
    }

    @PostMapping("/retrieve")
    @Operation(summary = "RAG 召回测试")
    @PreAuthorize("@ss.hasPermission('knowledge:konwledge-file:query')")
    public CommonResult<RagRetrieveRespVO> retrieve(@Valid @RequestBody RagRetrieveReqVO reqVO) {
        // 调用 Python RAG 服务进行召回
        JSONObject result = ragServiceClient.retrieve(reqVO.getQuestion(), reqVO.getKbIds(), reqVO.getTopK());

        if (result == null) {
            RagRetrieveRespVO respVO = new RagRetrieveRespVO();
            respVO.setSuccess(false);
            respVO.setMessage("调用 RAG 服务失败");
            respVO.setResults(Collections.emptyList());
            return success(respVO);
        }

        // 转换为响应 VO
        RagRetrieveRespVO respVO = new RagRetrieveRespVO();
        respVO.setSuccess(result.getBool("success", false));
        respVO.setMessage(result.getStr("message", ""));

        // 转换召回结果列表
        List<RagRetrieveRespVO.RagChunkVO> chunks = new ArrayList<>();
        if (result.containsKey("results")) {
            List<JSONObject> results = (List<JSONObject>) result.get("results");
            for (JSONObject chunk : results) {
                RagRetrieveRespVO.RagChunkVO chunkVO = new RagRetrieveRespVO.RagChunkVO();
                chunkVO.setKbId(chunk.getLong("kb_id"));
                chunkVO.setFileId(chunk.getLong("file_id"));
                chunkVO.setChunkIdx(chunk.getInt("chunk_idx"));
                chunkVO.setText(chunk.getStr("text"));
                chunkVO.setScore(chunk.getDouble("score"));
                chunks.add(chunkVO);
            }
        }
        respVO.setResults(chunks);

        return success(respVO);
    }

}