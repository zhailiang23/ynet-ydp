package com.ynet.iplatform.module.aicrm.controller.admin.cohort;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ynet.iplatform.framework.common.pojo.CommonResult;
import com.ynet.iplatform.module.aicrm.controller.admin.cohort.vo.CohortListReqVO;
import com.ynet.iplatform.module.aicrm.controller.admin.cohort.vo.CohortRespVO;

import java.util.List;

import static com.ynet.iplatform.framework.common.pojo.CommonResult.success;

/**
 * 客群代理 Controller
 *
 * 用于代理调用外部客群中心服务
 *
 * @author 易诚源码
 */
@Tag(name = "管理后台 - 客群代理")
@RestController
@RequestMapping("/aicrm/cohort")
@Validated
@Slf4j
public class CohortController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${aicrm.cohort.api-url:http://192.168.65.154:8108/ibbp-label-center/cohortListService/queryCohortList}")
    private String cohortApiUrl;

    @Value("${aicrm.cohort.customer-list-url:http://192.168.65.154:8108/ibbp-label-center/cohortTagService/findMultiCohortCustomList}")
    private String cohortCustomerListUrl;

    /**
     * 外部客群服务请求包装类
     */
    @Data
    private static class CohortApiRequest {
        private CohortListReqVO bodyData;

        public CohortApiRequest(CohortListReqVO bodyData) {
            this.bodyData = bodyData;
        }
    }

    /**
     * 外部客群服务响应包装类
     */
    @Data
    private static class CohortApiResponse {
        private ResHeaderData resHeaderData;
        private BodyOutData bodyOutData;
    }

    /**
     * 响应头数据
     */
    @Data
    private static class ResHeaderData {
        private String errorCode;
        private String errorMsg;
    }

    /**
     * 响应体数据
     */
    @Data
    private static class BodyOutData {
        private CohortDataWrapper data;
    }

    /**
     * 客群数据包装类
     */
    @Data
    private static class CohortDataWrapper {
        private List<CohortRespVO> list;
        private PageInfo page;
    }

    /**
     * 分页信息
     */
    @Data
    private static class PageInfo {
        private Integer pageSize;
        private Integer pageNum;
        private Integer total;
        private Integer pageCount;
        private Integer startRow;
    }

    /**
     * 客群客户列表请求包装类
     */
    @Data
    private static class CohortCustomerListReqVO {
        private List<String> cohortIds;
    }

    /**
     * 客群客户列表请求包装类
     */
    @Data
    private static class CohortCustomerListRequest {
        private CohortCustomerListReqVO bodyData;

        public CohortCustomerListRequest(List<String> cohortIds) {
            this.bodyData = new CohortCustomerListReqVO();
            this.bodyData.setCohortIds(cohortIds);
        }
    }

    /**
     * 客群客户数据
     */
    @Data
    private static class CohortCustomer {
        private String customId;
    }

    /**
     * 客群客户列表响应体数据
     */
    @Data
    private static class CohortCustomerBodyOutData {
        private List<CohortCustomer> list;
    }

    /**
     * 客群客户列表响应包装类
     */
    @Data
    private static class CohortCustomerApiResponse {
        private ResHeaderData resHeaderData;
        private CohortCustomerBodyOutData bodyOutData;
    }

    @PostMapping("/list")
    @Operation(summary = "获取客群列表（代理调用外部服务）")
    @PreAuthorize("@ss.hasPermission('aicrm:marketing-task-assignment:query')")
    public CommonResult<List<CohortRespVO>> getCohortList(@Valid @RequestBody CohortListReqVO reqVO) {
        try {
            log.info("调用外部客群服务: {}, 参数: {}", cohortApiUrl, reqVO);

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 包装请求参数到 bodyData 字段
            CohortApiRequest apiRequest = new CohortApiRequest(reqVO);

            // 创建请求实体
            HttpEntity<CohortApiRequest> requestEntity = new HttpEntity<>(apiRequest, headers);

            // 调用外部服务
            ResponseEntity<CohortApiResponse> response = restTemplate.postForEntity(
                    cohortApiUrl,
                    requestEntity,
                    CohortApiResponse.class
            );

            // 解析响应
            CohortApiResponse apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.getResHeaderData() != null) {
                String errorCode = apiResponse.getResHeaderData().getErrorCode();

                // errorCode 为 "0" 表示成功
                if ("0".equals(errorCode) && apiResponse.getBodyOutData() != null
                        && apiResponse.getBodyOutData().getData() != null) {
                    List<CohortRespVO> cohorts = apiResponse.getBodyOutData().getData().getList() != null ?
                            apiResponse.getBodyOutData().getData().getList() : List.of();
                    log.info("成功获取客群列表，数量: {}", cohorts.size());
                    return success(cohorts);
                } else {
                    String errorMsg = apiResponse.getResHeaderData().getErrorMsg();
                    log.error("调用客群服务失败 - errorCode: {}, errorMsg: {}", errorCode, errorMsg);
                    return success(List.of());
                }
            } else {
                log.error("调用客群服务失败: 响应为空");
                return success(List.of());
            }
        } catch (Exception e) {
            log.error("调用客群服务异常", e);
            // 返回空列表，避免前端报错
            return success(List.of());
        }
    }

    /**
     * 根据客群ID获取客户列表（内部接口，供 Service 层调用）
     *
     * @param cohortIds 客群ID列表
     * @return 客户ID列表
     */
    public List<Long> getCustomerIdsByCohortIds(List<String> cohortIds) {
        if (CollUtil.isEmpty(cohortIds)) {
            return List.of();
        }

        try {
            log.info("调用外部客群客户列表服务: {}, 客群IDs: {}", cohortCustomerListUrl, cohortIds);

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 包装请求参数
            CohortCustomerListRequest request = new CohortCustomerListRequest(cohortIds);

            // 创建请求实体
            HttpEntity<CohortCustomerListRequest> requestEntity = new HttpEntity<>(request, headers);

            // 调用外部服务
            ResponseEntity<CohortCustomerApiResponse> response = restTemplate.postForEntity(
                    cohortCustomerListUrl,
                    requestEntity,
                    CohortCustomerApiResponse.class
            );

            // 解析响应
            CohortCustomerApiResponse apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.getResHeaderData() != null) {
                String errorCode = apiResponse.getResHeaderData().getErrorCode();

                // errorCode 为 "0" 表示成功
                if ("0".equals(errorCode) && apiResponse.getBodyOutData() != null
                        && apiResponse.getBodyOutData().getList() != null) {
                    List<Long> customerIds = apiResponse.getBodyOutData().getList().stream()
                            .map(CohortCustomer::getCustomId)
                            .filter(customId -> customId != null && !customId.isEmpty())
                            .map(Long::parseLong)
                            .distinct()
                            .toList();
                    log.info("成功获取客群客户列表，客户数量: {}", customerIds.size());
                    return customerIds;
                } else {
                    String errorMsg = apiResponse.getResHeaderData().getErrorMsg();
                    log.error("调用客群客户列表服务失败 - errorCode: {}, errorMsg: {}", errorCode, errorMsg);
                    return List.of();
                }
            } else {
                log.error("调用客群客户列表服务失败: 响应为空");
                return List.of();
            }
        } catch (Exception e) {
            log.error("调用客群客户列表服务异常", e);
            return List.of();
        }
    }

}
