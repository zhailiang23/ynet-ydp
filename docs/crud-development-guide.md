# 单表 CRUD 开发指南

本文档详细介绍如何在项目中开发基本的单表增删改查功能,包括后端和前端的完整实现流程。

## 目录

- [1. 开发前准备](#1-开发前准备)
- [2. 数据库设计](#2-数据库设计)
- [3. 后端开发](#3-后端开发)
  - [3.1 创建数据对象 (DO)](#31-创建数据对象-do)
  - [3.2 创建值对象 (VO)](#32-创建值对象-vo)
  - [3.3 创建 Mapper](#33-创建-mapper)
  - [3.4 创建 Service](#34-创建-service)
  - [3.5 创建 Controller](#35-创建-controller)
- [4. 前端开发](#4-前端开发)
  - [4.1 创建 API 接口](#41-创建-api-接口)
  - [4.2 创建页面配置](#42-创建页面配置)
  - [4.3 创建列表页面](#43-创建列表页面)
  - [4.4 创建表单页面](#44-创建表单页面)
- [5. 测试验证](#5-测试验证)
- [6. 常见问题](#6-常见问题)

---

## 1. 开发前准备

### 1.1 确认环境

- 后端: JDK 17, Maven, MySQL 8.0+, Redis 6.0+
- 前端: Node.js 20+, pnpm 10+
- IDE: IntelliJ IDEA (后端), VSCode (前端)

### 1.2 启动开发环境

```bash
# 启动后端 (端口 48080)
cd backend/yudao-server
mvn spring-boot:run -Dspring-boot.run.profiles=local

# 启动前端 (端口 5666)
cd frontend
pnpm dev:antd
```

### 1.3 确定开发模块

- 后端模块: `backend/ynet-module-crm` (所有业务代码写在这里)
- 前端应用: `frontend/apps/web-antd`

---

## 2. 数据库设计

### 2.1 创建数据表

在 `backend/sql/mysql/` 目录下创建 SQL 文件:

```sql
-- 示例: 创建客户标签表
CREATE TABLE IF NOT EXISTS `aicrm_customer_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tag_name` varchar(50) NOT NULL COMMENT '标签名称',
  `tag_type` tinyint NOT NULL COMMENT '标签类型: 1-系统标签 2-自定义标签',
  `description` varchar(200) DEFAULT NULL COMMENT '标签描述',
  `sort` int DEFAULT 0 COMMENT '排序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',

  -- 公共字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户ID',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户标签表';
```

**注意事项**:
- 表名必须以 `aicrm_` 开头
- 必须包含公共字段: `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
- 主键必须是 `id` (bigint, AUTO_INCREMENT)

### 2.2 创建字典数据 (如果需要)

如果有枚举字段,应使用字典管理而非硬编码:

```sql
-- 插入字典类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`)
VALUES ('AICRM标签类型', 'aicrm_tag_type', 0, '客户标签类型枚举', 'system', NOW());

-- 插入字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `creator`, `create_time`)
VALUES
(1, '系统标签', '1', 'aicrm_tag_type', 0, 'system', NOW()),
(2, '自定义标签', '2', 'aicrm_tag_type', 0, 'system', NOW());
```

**命名规范**:
- 字典名称必须以 `AICRM` 开头
- 字典类型必须以 `aicrm_` 开头

---

## 3. 后端开发

### 3.1 创建数据对象 (DO)

路径: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/dal/dataobject/{模块名}/{类名}DO.java`

```java
package cn.iocoder.yudao.module.aicrm.dal.dataobject.customertag;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 客户标签 DO
 *
 * @author 系统生成
 */
@TableName("aicrm_customer_tag")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTagDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签类型: 1-系统标签 2-自定义标签
     */
    private Integer tagType;

    /**
     * 标签描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态: 0-禁用 1-启用
     */
    private Integer status;
}
```

**注意事项**:
- 必须继承 `BaseDO` (包含公共字段: creator, createTime, updater, updateTime, deleted, tenantId)
- 使用 Lombok 注解简化代码
- 字段使用驼峰命名,MyBatis Plus 会自动映射下划线
- 枚举字段使用 Integer 类型

### 3.2 创建值对象 (VO)

路径: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/{模块名}/vo/`

#### 3.2.1 创建请求 VO (ReqVO)

**保存请求 VO** (用于新增和更新):

```java
package cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.*;

/**
 * 客户标签保存请求 VO
 */
@Schema(description = "管理后台 - 客户标签保存请求 VO")
@Data
public class CustomerTagSaveReqVO {

    @Schema(description = "主键ID (更新时必传)", example = "1")
    private Long id;

    @Schema(description = "标签名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "VIP客户")
    @NotBlank(message = "标签名称不能为空")
    @Size(max = 50, message = "标签名称长度不能超过50个字符")
    private String tagName;

    @Schema(description = "标签类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "标签类型不能为空")
    private Integer tagType;

    @Schema(description = "标签描述", example = "高价值客户")
    @Size(max = 200, message = "标签描述长度不能超过200个字符")
    private String description;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
```

**分页查询请求 VO**:

```java
package cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 客户标签分页查询请求 VO
 */
@Schema(description = "管理后台 - 客户标签分页查询请求 VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerTagPageReqVO extends PageParam {

    @Schema(description = "标签名称 (模糊匹配)", example = "VIP")
    private String tagName;

    @Schema(description = "标签类型", example = "1")
    private Integer tagType;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
}
```

#### 3.2.2 创建响应 VO (RespVO)

```java
package cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户标签响应 VO
 */
@Schema(description = "管理后台 - 客户标签响应 VO")
@Data
public class CustomerTagRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "标签名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "VIP客户")
    private String tagName;

    @Schema(description = "标签类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer tagType;

    @Schema(description = "标签描述", example = "高价值客户")
    private String description;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime updateTime;
}
```

### 3.3 创建 Mapper

路径: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/dal/mysql/{模块名}/{类名}Mapper.java`

```java
package cn.iocoder.yudao.module.aicrm.dal.mysql.customertag;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagPageReqVO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertag.CustomerTagDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户标签 Mapper
 *
 * @author 系统生成
 */
@Mapper
public interface CustomerTagMapper extends BaseMapperX<CustomerTagDO> {

    /**
     * 分页查询客户标签
     *
     * @param reqVO 分页查询参数
     * @return 分页结果
     */
    default PageResult<CustomerTagDO> selectPage(CustomerTagPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerTagDO>()
                .likeIfPresent(CustomerTagDO::getTagName, reqVO.getTagName())
                .eqIfPresent(CustomerTagDO::getTagType, reqVO.getTagType())
                .eqIfPresent(CustomerTagDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CustomerTagDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerTagDO::getId));
    }
}
```

**注意事项**:
- 继承 `BaseMapperX<DO>` 自动获得基础 CRUD 方法
- 使用 `LambdaQueryWrapperX` 构建查询条件
- `likeIfPresent` 用于模糊查询
- `eqIfPresent` 用于精确查询
- `betweenIfPresent` 用于范围查询
- 默认按 ID 降序排序

### 3.4 创建 Service

#### 3.4.1 创建 Service 接口

路径: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/service/{模块名}/{类名}Service.java`

```java
package cn.iocoder.yudao.module.aicrm.service.customertag;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagPageReqVO;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagSaveReqVO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertag.CustomerTagDO;

import jakarta.validation.*;

/**
 * 客户标签 Service 接口
 *
 * @author 系统生成
 */
public interface CustomerTagService {

    /**
     * 创建客户标签
     *
     * @param createReqVO 创建信息
     * @return 标签ID
     */
    Long createTag(@Valid CustomerTagSaveReqVO createReqVO);

    /**
     * 更新客户标签
     *
     * @param updateReqVO 更新信息
     */
    void updateTag(@Valid CustomerTagSaveReqVO updateReqVO);

    /**
     * 删除客户标签
     *
     * @param id 标签ID
     */
    void deleteTag(Long id);

    /**
     * 获得客户标签
     *
     * @param id 标签ID
     * @return 客户标签
     */
    CustomerTagDO getTag(Long id);

    /**
     * 获得客户标签分页
     *
     * @param pageReqVO 分页查询
     * @return 客户标签分页
     */
    PageResult<CustomerTagDO> getTagPage(CustomerTagPageReqVO pageReqVO);
}
```

#### 3.4.2 创建 Service 实现

路径: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/service/{模块名}/impl/{类名}ServiceImpl.java`

```java
package cn.iocoder.yudao.module.aicrm.service.customertag.impl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagPageReqVO;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagSaveReqVO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertag.CustomerTagDO;
import cn.iocoder.yudao.module.aicrm.dal.mysql.customertag.CustomerTagMapper;
import cn.iocoder.yudao.module.aicrm.service.customertag.CustomerTagService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.aicrm.enums.ErrorCodeConstants.CUSTOMER_TAG_NOT_EXISTS;

/**
 * 客户标签 Service 实现类
 *
 * @author 系统生成
 */
@Service
@Validated
public class CustomerTagServiceImpl implements CustomerTagService {

    @Resource
    private CustomerTagMapper customerTagMapper;

    @Override
    public Long createTag(CustomerTagSaveReqVO createReqVO) {
        // 插入
        CustomerTagDO tag = CustomerTagDO.builder()
                .tagName(createReqVO.getTagName())
                .tagType(createReqVO.getTagType())
                .description(createReqVO.getDescription())
                .sort(createReqVO.getSort())
                .status(createReqVO.getStatus())
                .build();
        customerTagMapper.insert(tag);

        // 返回
        return tag.getId();
    }

    @Override
    public void updateTag(CustomerTagSaveReqVO updateReqVO) {
        // 校验存在
        validateTagExists(updateReqVO.getId());

        // 更新
        CustomerTagDO updateObj = CustomerTagDO.builder()
                .id(updateReqVO.getId())
                .tagName(updateReqVO.getTagName())
                .tagType(updateReqVO.getTagType())
                .description(updateReqVO.getDescription())
                .sort(updateReqVO.getSort())
                .status(updateReqVO.getStatus())
                .build();
        customerTagMapper.updateById(updateObj);
    }

    @Override
    public void deleteTag(Long id) {
        // 校验存在
        validateTagExists(id);

        // 删除
        customerTagMapper.deleteById(id);
    }

    /**
     * 校验客户标签是否存在
     */
    private void validateTagExists(Long id) {
        if (customerTagMapper.selectById(id) == null) {
            throw exception(CUSTOMER_TAG_NOT_EXISTS);
        }
    }

    @Override
    public CustomerTagDO getTag(Long id) {
        return customerTagMapper.selectById(id);
    }

    @Override
    public PageResult<CustomerTagDO> getTagPage(CustomerTagPageReqVO pageReqVO) {
        return customerTagMapper.selectPage(pageReqVO);
    }
}
```

**注意事项**:
- 使用 `@Service` 注解标记为服务层组件
- 使用 `@Validated` 开启参数校验
- 更新和删除操作前必须校验数据是否存在
- 使用 Builder 模式创建 DO 对象
- 插入后返回自增主键 ID

### 3.5 创建 Controller

路径: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/{模块名}/{类名}Controller.java`

```java
package cn.iocoder.yudao.module.aicrm.controller.admin.customertag;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagPageReqVO;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagRespVO;
import cn.iocoder.yudao.module.aicrm.controller.admin.customertag.vo.CustomerTagSaveReqVO;
import cn.iocoder.yudao.module.aicrm.dal.dataobject.customertag.CustomerTagDO;
import cn.iocoder.yudao.module.aicrm.service.customertag.CustomerTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;

/**
 * 客户标签 Controller
 *
 * @author 系统生成
 */
@Tag(name = "管理后台 - 客户标签")
@RestController
@RequestMapping("/aicrm/customer-tag")
@Validated
public class CustomerTagController {

    @Resource
    private CustomerTagService customerTagService;

    @PostMapping("/create")
    @Operation(summary = "创建客户标签")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-tag:create')")
    public CommonResult<Long> createTag(@Valid @RequestBody CustomerTagSaveReqVO createReqVO) {
        return success(customerTagService.createTag(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客户标签")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-tag:update')")
    public CommonResult<Boolean> updateTag(@Valid @RequestBody CustomerTagSaveReqVO updateReqVO) {
        customerTagService.updateTag(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客户标签")
    @Parameter(name = "id", description = "标签ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-tag:delete')")
    public CommonResult<Boolean> deleteTag(@RequestParam("id") Long id) {
        customerTagService.deleteTag(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户标签")
    @Parameter(name = "id", description = "标签ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-tag:query')")
    public CommonResult<CustomerTagRespVO> getTag(@RequestParam("id") Long id) {
        CustomerTagDO tag = customerTagService.getTag(id);
        return success(convertToRespVO(tag));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户标签分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-tag:query')")
    public CommonResult<PageResult<CustomerTagRespVO>> getTagPage(@Valid CustomerTagPageReqVO pageReqVO) {
        PageResult<CustomerTagDO> pageResult = customerTagService.getTagPage(pageReqVO);
        return success(convertToRespVOPage(pageResult));
    }

    /**
     * 转换为响应 VO
     */
    private CustomerTagRespVO convertToRespVO(CustomerTagDO tag) {
        if (tag == null) {
            return null;
        }
        CustomerTagRespVO respVO = new CustomerTagRespVO();
        respVO.setId(tag.getId());
        respVO.setTagName(tag.getTagName());
        respVO.setTagType(tag.getTagType());
        respVO.setDescription(tag.getDescription());
        respVO.setSort(tag.getSort());
        respVO.setStatus(tag.getStatus());
        respVO.setCreateTime(tag.getCreateTime());
        respVO.setUpdateTime(tag.getUpdateTime());
        return respVO;
    }

    /**
     * 转换为响应 VO 分页
     */
    private PageResult<CustomerTagRespVO> convertToRespVOPage(PageResult<CustomerTagDO> pageResult) {
        return new PageResult<>(
                convertList(pageResult.getList(), this::convertToRespVO),
                pageResult.getTotal()
        );
    }
}
```

**注意事项**:
- 使用 `@RestController` 和 `@RequestMapping` 定义 RESTful API
- 使用 `@PreAuthorize` 进行权限控制
- 创建: POST `/create`
- 更新: PUT `/update`
- 删除: DELETE `/delete?id=xxx`
- 查询单个: GET `/get?id=xxx`
- 分页查询: GET `/page?pageNo=1&pageSize=10`
- 必须手动将 DO 转换为 RespVO

### 3.6 创建错误码 (如果需要)

路径: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/enums/ErrorCodeConstants.java`

```java
public interface ErrorCodeConstants {
    // ... 其他错误码

    ErrorCode CUSTOMER_TAG_NOT_EXISTS = new ErrorCode(1_006_001_001, "客户标签不存在");
}
```

### 3.7 编译后端

**重要**: 必须先 clean 再 compile

```bash
cd backend
mvn clean compile
```

---

## 4. 前端开发

### 4.1 创建 API 接口

路径: `frontend/apps/web-antd/src/api/aicrm/{模块名}/index.ts`

```typescript
import { requestClient } from '#/api/request';

/** API 基础路径 */
const API_PREFIX = '/aicrm/customer-tag';

/** 客户标签 API 命名空间 */
export namespace AicrmCustomerTagApi {
  /** 客户标签 */
  export interface CustomerTag {
    id?: number;
    tagName: string;
    tagType: number;
    description?: string;
    sort?: number;
    status: number;
    createTime?: string;
    updateTime?: string;
  }

  /** 分页查询参数 */
  export interface PageReqVO {
    pageNo: number;
    pageSize: number;
    tagName?: string;
    tagType?: number;
    status?: number;
    createTime?: string[];
  }

  /** 分页响应 */
  export interface PageResult {
    list: CustomerTag[];
    total: number;
  }
}

/**
 * 创建客户标签
 */
export const createCustomerTag = (data: AicrmCustomerTagApi.CustomerTag) => {
  return requestClient.post<number>(`${API_PREFIX}/create`, data);
};

/**
 * 更新客户标签
 */
export const updateCustomerTag = (data: AicrmCustomerTagApi.CustomerTag) => {
  return requestClient.put<void>(`${API_PREFIX}/update`, data);
};

/**
 * 删除客户标签
 */
export const deleteCustomerTag = (id: number) => {
  return requestClient.delete<void>(`${API_PREFIX}/delete?id=${id}`);
};

/**
 * 获取客户标签详情
 */
export const getCustomerTag = (id: number) => {
  return requestClient.get<AicrmCustomerTagApi.CustomerTag>(
    `${API_PREFIX}/get?id=${id}`,
  );
};

/**
 * 获取客户标签分页
 */
export const getCustomerTagPage = (
  params: AicrmCustomerTagApi.PageReqVO,
) => {
  return requestClient.get<AicrmCustomerTagApi.PageResult>(
    `${API_PREFIX}/page`,
    { params },
  );
};
```

### 4.2 创建页面配置

路径: `frontend/apps/web-antd/src/views/aicrm/{模块名}/data.ts`

```typescript
import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerTagApi } from '#/api/aicrm/customertag';

import { h } from 'vue';

import { getDictOptions } from '@vben/hooks';
import { formatDateTime } from '@vben/utils';

import { DictTag } from '#/components/dict-tag';
import { getRangePickerDefaultProps } from '#/utils';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'tagName',
      label: '标签名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入标签名称',
      },
    },
    {
      fieldName: 'tagType',
      label: '标签类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_tag_type'),
        placeholder: '请选择标签类型',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('common_status'),
        placeholder: '请选择状态',
      },
    },
    {
      fieldName: 'createTime',
      label: '创建时间',
      component: 'RangePicker',
      componentProps: getRangePickerDefaultProps(),
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerTagApi.CustomerTag>['columns'] {
  return [
    {
      field: 'id',
      title: '标签ID',
      minWidth: 80,
      fixed: 'left',
    },
    {
      field: 'tagName',
      title: '标签名称',
      minWidth: 120,
    },
    {
      field: 'tagType',
      title: '标签类型',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_tag_type' },
      },
    },
    {
      field: 'description',
      title: '标签描述',
      minWidth: 180,
      showOverflow: 'tooltip',
    },
    {
      field: 'sort',
      title: '排序',
      minWidth: 80,
    },
    {
      field: 'status',
      title: '状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'common_status' },
      },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 160,
      formatter: ({ cellValue }) => formatDateTime(cellValue),
    },
    {
      field: 'action',
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}

/** 表单 Schema */
export function useFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'id',
      label: '标签ID',
      component: 'Input',
      dependencies: {
        show: false, // 隐藏,仅用于编辑时传递 ID
      },
    },
    {
      fieldName: 'tagName',
      label: '标签名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入标签名称',
        maxlength: 50,
        showCount: true,
      },
    },
    {
      fieldName: 'tagType',
      label: '标签类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_tag_type'),
        placeholder: '请选择标签类型',
      },
    },
    {
      fieldName: 'description',
      label: '标签描述',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入标签描述',
        rows: 4,
        maxlength: 200,
        showCount: true,
      },
    },
    {
      fieldName: 'sort',
      label: '排序',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入排序',
        min: 0,
        style: { width: '100%' },
      },
      defaultValue: 0,
    },
    {
      fieldName: 'status',
      label: '状态',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: getDictOptions('common_status'),
      },
      defaultValue: 1,
    },
  ];
}
```

**注意事项**:
- `useGridFormSchema`: 列表页面的搜索表单
- `useGridColumns`: 列表页面的表格列定义
- `useFormSchema`: 新增/编辑表单的字段定义
- 枚举字段使用 `getDictOptions` 获取字典数据
- 日期字段使用 `formatDateTime` 格式化

### 4.3 创建列表页面

路径: `frontend/apps/web-antd/src/views/aicrm/{模块名}/index.vue`

```vue
<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerTagApi } from '#/api/aicrm/customertag';

import { Page } from '@vben/common-ui';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  createCustomerTag,
  deleteCustomerTag,
  getCustomerTag,
  getCustomerTagPage,
  updateCustomerTag,
} from '#/api/aicrm/customertag';

import { useGridColumns, useGridFormSchema } from './data';
import TagFormModal from './tag-form-modal.vue';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增 */
function handleCreate() {
  // 打开表单弹窗
  // 具体实现见下一节
}

/** 编辑 */
function handleEdit(row: AicrmCustomerTagApi.CustomerTag) {
  // 打开表单弹窗并加载数据
}

/** 删除 */
function handleDelete(row: AicrmCustomerTagApi.CustomerTag) {
  Modal.confirm({
    title: '确认删除',
    content: `确定要删除标签"${row.tagName}"吗?此操作不可恢复。`,
    okText: '确认',
    cancelText: '取消',
    onOk: async () => {
      try {
        await deleteCustomerTag(row.id!);
        message.success('删除成功');
        handleRefresh();
      } catch (error) {
        console.error('删除失败:', error);
      }
    },
  });
}

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: useGridFormSchema(),
  },
  gridOptions: {
    columns: useGridColumns(),
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }, formValues) => {
          return await getCustomerTagPage({
            pageNo: page.currentPage,
            pageSize: page.pageSize,
            ...formValues,
          });
        },
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
      search: true,
    },
  } as VxeTableGridOptions<AicrmCustomerTagApi.CustomerTag>,
});
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户标签管理">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '新增',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-tag:create'],
              onClick: handleCreate,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '编辑',
              type: 'link',
              icon: ACTION_ICON.EDIT,
              auth: ['aicrm:customer-tag:update'],
              onClick: handleEdit.bind(null, row),
            },
            {
              label: '删除',
              type: 'link',
              icon: ACTION_ICON.DELETE,
              danger: true,
              auth: ['aicrm:customer-tag:delete'],
              onClick: handleDelete.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
```

### 4.4 创建表单页面

路径: `frontend/apps/web-antd/src/views/aicrm/{模块名}/tag-form-modal.vue`

```vue
<script lang="ts" setup>
import type { VbenFormProps } from '#/adapter/form';
import type { AicrmCustomerTagApi } from '#/api/aicrm/customertag';

import { ref, unref } from 'vue';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import {
  createCustomerTag,
  getCustomerTag,
  updateCustomerTag,
} from '#/api/aicrm/customertag';

import { useFormSchema } from './data';

interface Props {
  open: boolean;
  tagId?: number;
}

interface Emits {
  (e: 'update:open', value: boolean): void;
  (e: 'success'): void;
}

const props = defineProps<Props>();
const emit = defineEmits<Emits>();

const modalTitle = ref('新增标签');
const confirmLoading = ref(false);

const [Form, formApi] = useVbenForm({
  schema: useFormSchema(),
  showDefaultActions: false,
  wrapperClass: 'grid-cols-1',
} as VbenFormProps);

/** 打开弹窗时的回调 */
async function handleOpen() {
  if (props.tagId) {
    modalTitle.value = '编辑标签';
    try {
      const tag = await getCustomerTag(props.tagId);
      await formApi.setValues(tag);
    } catch (error) {
      console.error('加载标签信息失败:', error);
    }
  } else {
    modalTitle.value = '新增标签';
    formApi.resetForm();
  }
}

/** 提交表单 */
async function handleOk() {
  try {
    confirmLoading.value = true;

    // 验证表单
    const values = await formApi.validate();

    // 提交
    if (props.tagId) {
      await updateCustomerTag({ ...values, id: props.tagId });
      message.success('更新成功');
    } else {
      await createCustomerTag(values);
      message.success('创建成功');
    }

    // 关闭弹窗并刷新列表
    emit('update:open', false);
    emit('success');
  } catch (error) {
    console.error('提交失败:', error);
  } finally {
    confirmLoading.value = false;
  }
}

/** 取消 */
function handleCancel() {
  emit('update:open', false);
}
</script>

<template>
  <a-modal
    :open="open"
    :title="modalTitle"
    :confirm-loading="confirmLoading"
    width="600px"
    @ok="handleOk"
    @cancel="handleCancel"
    @after-open="handleOpen"
  >
    <Form />
  </a-modal>
</template>
```

### 4.5 更新列表页面 (使用表单弹窗)

在 `index.vue` 中引入表单弹窗:

```vue
<script lang="ts" setup>
// ... 其他导入

import TagFormModal from './tag-form-modal.vue';

const formModalOpen = ref(false);
const currentTagId = ref<number>();

/** 新增 */
function handleCreate() {
  currentTagId.value = undefined;
  formModalOpen.value = true;
}

/** 编辑 */
function handleEdit(row: AicrmCustomerTagApi.CustomerTag) {
  currentTagId.value = row.id;
  formModalOpen.value = true;
}

/** 表单提交成功 */
function handleFormSuccess() {
  handleRefresh();
}

// ... 其他代码
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户标签管理">
      <!-- ... -->
    </Grid>

    <!-- 表单弹窗 -->
    <TagFormModal
      v-model:open="formModalOpen"
      :tag-id="currentTagId"
      @success="handleFormSuccess"
    />
  </Page>
</template>
```

---

## 5. 测试验证

### 5.1 后端测试

使用 curl 或 Postman 测试 API:

```bash
# 获取 Token (使用 admin/admin123 登录)
TOKEN="your-token-here"

# 创建标签
curl -X POST "http://localhost:48080/admin-api/aicrm/customer-tag/create" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -H "tenant-id: 1" \
  -d '{
    "tagName": "VIP客户",
    "tagType": 1,
    "description": "高价值客户",
    "sort": 1,
    "status": 1
  }'

# 分页查询
curl -X GET "http://localhost:48080/admin-api/aicrm/customer-tag/page?pageNo=1&pageSize=10" \
  -H "Authorization: Bearer $TOKEN" \
  -H "tenant-id: 1"

# 获取详情
curl -X GET "http://localhost:48080/admin-api/aicrm/customer-tag/get?id=1" \
  -H "Authorization: Bearer $TOKEN" \
  -H "tenant-id: 1"

# 更新
curl -X PUT "http://localhost:48080/admin-api/aicrm/customer-tag/update" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -H "tenant-id: 1" \
  -d '{
    "id": 1,
    "tagName": "超级VIP",
    "tagType": 1,
    "description": "超高价值客户",
    "sort": 1,
    "status": 1
  }'

# 删除
curl -X DELETE "http://localhost:48080/admin-api/aicrm/customer-tag/delete?id=1" \
  -H "Authorization: Bearer $TOKEN" \
  -H "tenant-id: 1"
```

### 5.2 前端测试

1. 访问 http://localhost:5666
2. 登录系统 (admin/admin123)
3. 导航到客户标签管理页面
4. 测试以下功能:
   - 列表展示
   - 搜索过滤
   - 新增标签
   - 编辑标签
   - 删除标签
   - 分页查询

### 5.3 检查点

- [ ] 后端 API 返回正确的数据格式
- [ ] 前端列表正常显示数据
- [ ] 搜索过滤功能正常
- [ ] 新增功能正常,数据成功保存
- [ ] 编辑功能正常,数据成功更新
- [ ] 删除功能正常,数据成功删除
- [ ] 分页功能正常
- [ ] 枚举字段显示正确的字典标签
- [ ] 权限控制生效 (无权限时按钮隐藏)
- [ ] 表单验证正常

---

## 6. 常见问题

### 6.1 后端问题

#### Q1: 编译错误 "找不到符号"

**原因**: Maven 未正确生成 Mapper 接口的实现类

**解决**: 先 clean 再 compile
```bash
cd backend
mvn clean compile
```

#### Q2: 启动报错 "Table doesn't exist"

**原因**: 数据库表未创建

**解决**: 执行 SQL 脚本创建表
```bash
mysql -u root -p ruoyi-vue-pro < backend/sql/mysql/your-table.sql
```

#### Q3: 接口返回 403 Forbidden

**原因**: 权限配置错误或 Token 无效

**解决**:
1. 检查 `@PreAuthorize` 中的权限标识
2. 使用正确的 Token
3. 确认用户有对应权限

#### Q4: 多租户字段未自动填充

**原因**: 未添加 `@TenantId` 注解或租户功能未启用

**解决**:
1. 确认 `yudao.tenant.enable: true`
2. 请求头必须包含 `tenant-id`

### 6.2 前端问题

#### Q1: API 请求 404

**原因**: API 路径错误或后端未启动

**解决**:
1. 检查 API_PREFIX 是否正确
2. 确认后端已启动 (http://localhost:48080)
3. 查看浏览器 Network 面板

#### Q2: 字典标签显示为数字

**原因**: 字典数据未配置或字典类型错误

**解决**:
1. 确认字典数据已插入数据库
2. 检查 `getDictOptions('dict_type')` 的参数是否正确
3. 查看浏览器控制台是否有错误

#### Q3: 表单验证不生效

**原因**: rules 配置错误

**解决**:
1. 必填字段使用 `rules: 'required'`
2. 复杂验证使用 `rules: [{required: true, message: '错误提示'}]`

#### Q4: 列表数据不显示

**原因**: API 返回数据格式不匹配或字段名错误

**解决**:
1. 检查后端返回的字段名与前端定义是否一致
2. 查看浏览器控制台是否有错误
3. 使用 Vue DevTools 检查组件数据

### 6.3 性能优化

#### 列表分页优化

- 默认每页 10 条,可根据需要调整
- 使用索引优化查询字段
- 避免一次性加载大量数据

#### 字典缓存

- 字典数据会自动缓存到 Redis
- 无需重复请求后端

#### 表单优化

- 使用 `v-show` 而非 `v-if` 切换表单字段
- 大量字段时考虑分步表单

---

## 7. 开发规范总结

### 7.1 命名规范

**数据库**:
- 表名: `aicrm_{模块名}` (全小写,下划线分隔)
- 字段名: 全小写,下划线分隔
- 字典类型: `aicrm_{名称}` (全小写)

**Java**:
- DO: `{实体名}DO` (例: CustomerTagDO)
- VO: `{实体名}{类型}VO` (例: CustomerTagSaveReqVO, CustomerTagRespVO, CustomerTagPageReqVO)
- Mapper: `{实体名}Mapper`
- Service: `{实体名}Service`, `{实体名}ServiceImpl`
- Controller: `{实体名}Controller`

**TypeScript**:
- API 文件: `{模块名}/index.ts`
- 类型定义: 使用 namespace 包装
- 函数命名: 驼峰命名,动词开头

### 7.2 目录结构

**后端**:
```
backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/
├── controller/admin/{模块名}/
│   ├── {类名}Controller.java
│   └── vo/
│       ├── {类名}SaveReqVO.java
│       ├── {类名}PageReqVO.java
│       └── {类名}RespVO.java
├── service/{模块名}/
│   ├── {类名}Service.java
│   └── impl/
│       └── {类名}ServiceImpl.java
└── dal/
    ├── dataobject/{模块名}/
    │   └── {类名}DO.java
    └── mysql/{模块名}/
        └── {类名}Mapper.java
```

**前端**:
```
frontend/apps/web-antd/src/
├── api/aicrm/{模块名}/
│   └── index.ts
└── views/aicrm/{模块名}/
    ├── index.vue (列表页面)
    ├── data.ts (配置文件)
    └── {功能}-modal.vue (表单弹窗)
```

### 7.3 代码规范

**DO 规范**:
- 继承 `BaseDO`
- 使用 Lombok 注解
- 字段必须有注释
- 枚举使用 Integer 类型

**VO 规范**:
- ReqVO 必须有验证注解
- RespVO 必须有 Swagger 注解
- 不包含业务逻辑

**Service 规范**:
- 接口和实现分离
- 更新/删除前必须校验存在
- 使用自定义异常
- 添加事务注解 (如需要)

**Controller 规范**:
- 使用 RESTful 风格
- 统一返回 `CommonResult<T>`
- 添加权限注解
- 添加 Swagger 注解

**前端规范**:
- API 和类型定义分离
- 使用 TypeScript 类型
- 组件拆分合理
- 避免直接修改 props

---

## 8. 附录

### 8.1 快速开发清单

开发一个单表 CRUD 功能需要创建以下文件:

**后端** (8 个文件):
1. SQL 脚本 (创建表和字典)
2. DO (数据对象)
3. SaveReqVO (保存请求 VO)
4. PageReqVO (分页查询请求 VO)
5. RespVO (响应 VO)
6. Mapper (数据访问接口)
7. Service (业务接口)
8. ServiceImpl (业务实现)
9. Controller (控制器)

**前端** (3 个文件):
1. API 接口文件
2. data.ts (配置文件)
3. index.vue (列表页面)
4. xxx-modal.vue (表单弹窗)

### 8.2 参考示例

项目中可参考的完整示例:
- 客户认领申请: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/*/customerclaim/`
- 前端示例: `frontend/apps/web-antd/src/views/aicrm/customerclaim/`

### 8.3 常用工具

- **后端开发**:
  - IntelliJ IDEA (代码生成插件推荐: MyBatisX, Lombok)
  - Postman / Apifox (API 测试)
  - Navicat / DataGrip (数据库管理)

- **前端开发**:
  - VSCode (推荐插件: Volar, ESLint, Prettier)
  - Vue DevTools (浏览器插件)
  - Chrome DevTools (浏览器开发者工具)

---

## 结语

本文档详细介绍了单表 CRUD 的完整开发流程。实际开发中可能会遇到更复杂的场景 (如联表查询、批量操作、文件上传等),但基本思路是一致的。

**开发建议**:
1. 先完成后端,用 curl/Postman 测试通过后再开发前端
2. 按照规范命名,保持代码一致性
3. 充分利用代码生成器和模板代码
4. 遇到问题先查看浏览器控制台和后端日志
5. 参考现有代码示例

**学习资源**:
- 官方文档: https://doc.iocoder.cn
- 视频教程: https://doc.iocoder.cn/video/
- API 文档: http://localhost:48080/doc.html

祝您开发顺利! 🚀
