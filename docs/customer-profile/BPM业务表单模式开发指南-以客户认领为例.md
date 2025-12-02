# BPM 业务表单模式开发指南 - 以客户认领为例

本文档详细说明如何使用易诚开源框架的 BPM 模块，采用**业务表单模式**开发一个完整的客户认领申请及审批流程。

## 目录

- [一、业务表单模式简介](#一业务表单模式简介)
- [二、开发流程总览](#二开发流程总览)
- [三、详细开发步骤](#三详细开发步骤)
  - [Step 1: 数据库设计](#step-1-数据库设计)
  - [Step 2: 后端开发](#step-2-后端开发)
  - [Step 3: 前端开发](#step-3-前端开发)
  - [Step 4: BPM 流程设计](#step-4-bpm-流程设计)
  - [Step 5: 流程后置通知](#step-5-流程后置通知)
  - [Step 6: 测试验证](#step-6-测试验证)
- [四、注意事项与最佳实践](#四注意事项与最佳实践)
- [五、常见问题](#五常见问题)

---

## 一、业务表单模式简介

### 1.1 什么是业务表单模式

易诚 BPM 模块支持两种表单模式：

1. **流程表单模式**：表单定义在流程引擎中，数据存储在流程引擎的表中
2. **业务表单模式**：表单定义在业务系统中，数据存储在业务表中（**推荐**）

### 1.2 业务表单模式的优势

- ✅ **数据独立性**：业务数据存储在业务表中，便于查询和统计
- ✅ **灵活性高**：可以使用复杂的业务逻辑和自定义表单
- ✅ **易于维护**：业务逻辑和流程逻辑分离
- ✅ **复用性强**：业务数据可以在多个地方复用

### 1.3 业务表单模式的核心概念

- **业务表**：存储业务数据的表（如 `customer_claim_application`）
- **业务 Key**：关联业务数据和流程实例的唯一标识（通常是业务表主键）
- **流程实例 ID**：BPM 流程引擎生成的流程实例唯一标识
- **流程定义 Key**：流程模型的唯一标识（如 `customer_claim`）

---

## 二、开发流程总览

```
┌─────────────────────────────────────────────────────────────────┐
│                      业务表单模式开发流程                          │
└─────────────────────────────────────────────────────────────────┘

  Step 1          Step 2          Step 3          Step 4          Step 5
┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐
│ 数据库   │ => │ 后端开发 │ => │ 前端开发 │ => │ BPM配置 │ => │ 后置通知 │
│ 设计     │    │         │    │         │    │         │    │         │
└─────────┘    └─────────┘    └─────────┘    └─────────┘    └─────────┘
   ↓              ↓              ↓              ↓              ↓
• 业务表      • DO/VO类     • 申请表单    • 流程建模    • 回调接口
• 字段定义    • Service     • 列表页面    • 审批节点    • 自动化处理
• 索引        • Controller  • 详情页面    • 表单路由    • 状态同步
• 初始数据    • Mapper      • 权限控制    • 流程部署
```

---

## 三、详细开发步骤

### Step 1: 数据库设计

#### 1.1 设计业务表

以客户认领为例，需要设计 `customer_claim_application` 表：

```sql
CREATE TABLE `customer_claim_application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `applicant_user_id` bigint NOT NULL COMMENT '申请人用户ID',
  `applicant_dept_id` bigint NOT NULL COMMENT '申请人部门ID',
  `apply_date` date NOT NULL COMMENT '申请日期',
  `apply_reason` varchar(500) DEFAULT NULL COMMENT '申请理由',
  `process_instance_id` varchar(64) DEFAULT NULL COMMENT '流程实例ID',
  `process_status` int NOT NULL DEFAULT '1' COMMENT '流程状态：1-审批中，2-审批通过，3-审批拒绝，4-已取消',

  -- 标准字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_applicant_user_id` (`applicant_user_id`),
  KEY `idx_process_instance_id` (`process_instance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户认领申请表';
```

#### 1.2 关键字段说明

| 字段 | 类型 | 说明 | 重要性 |
|------|------|------|--------|
| `id` | bigint | 主键，作为 **业务 Key** | ⭐⭐⭐ |
| `process_instance_id` | varchar(64) | 关联 BPM 流程实例 | ⭐⭐⭐ |
| `process_status` | int | 流程状态，用于业务逻辑判断 | ⭐⭐⭐ |
| `tenant_id` | bigint | 多租户隔离 | ⭐⭐⭐ |

#### 1.3 注意事项

- ✅ **必须包含** `process_instance_id` 字段，用于关联流程实例
- ✅ **必须包含** `process_status` 字段，用于记录流程状态
- ✅ **建议建立索引**：`idx_process_instance_id`，用于流程回调查询
- ✅ **多租户隔离**：如果系统启用多租户，必须包含 `tenant_id` 字段

---

### Step 2: 后端开发

#### 2.1 创建 DO（Data Object）

```java
@TableName("customer_claim_application")
@Data
public class CustomerClaimApplicationDO extends BaseDO {

    @TableId
    private Long id;

    private Long customerId;

    private Long applicantUserId;

    private Long applicantDeptId;

    private LocalDate applyDate;

    private String applyReason;

    private String processInstanceId;  // 重要：关联流程实例

    private Integer processStatus;     // 重要：流程状态
}
```

#### 2.2 创建 VO（View Object）

**请求 VO - 申请提交**：

```java
@Data
public class CustomerClaimApplicationApplyReqVO {

    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @NotBlank(message = "申请理由不能为空")
    @Length(max = 500, message = "申请理由长度不能超过500字符")
    private String applyReason;

    /**
     * 流程发起时，用户选择的审批人
     * Key: 任务节点ID，Value: 审批人用户ID
     */
    private Map<String, Long> startUserSelectAssignees;
}
```

**响应 VO - 详情查询**：

```java
@Data
public class CustomerClaimApplicationRespVO {

    private Long id;

    private Long customerId;

    private String customerName;  // 关联查询

    private Long applicantUserId;

    private String applicantUserName;  // 关联查询

    private LocalDate applyDate;

    private String applyReason;

    private String processInstanceId;

    private Integer processStatus;

    private LocalDateTime createTime;
}
```

#### 2.3 创建 Mapper

```java
@Mapper
public interface CustomerClaimApplicationMapper extends BaseMapperX<CustomerClaimApplicationDO> {

    default PageResult<CustomerClaimApplicationDO> selectPage(CustomerClaimApplicationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerClaimApplicationDO>()
                .eqIfPresent(CustomerClaimApplicationDO::getCustomerId, reqVO.getCustomerId())
                .eqIfPresent(CustomerClaimApplicationDO::getApplicantUserId, reqVO.getApplicantUserId())
                .eqIfPresent(CustomerClaimApplicationDO::getProcessStatus, reqVO.getProcessStatus())
                .betweenIfPresent(CustomerClaimApplicationDO::getApplyDate, reqVO.getApplyDate())
                .orderByDesc(CustomerClaimApplicationDO::getId));
    }
}
```

#### 2.4 创建 Service

**Service 接口**：

```java
public interface CustomerClaimService {

    /**
     * 提交客户认领申请
     *
     * @param userId 当前用户ID
     * @param createReqVO 申请信息
     * @return 申请ID
     */
    Long applyForClaim(Long userId, CustomerClaimApplicationApplyReqVO createReqVO);

    /**
     * 取消客户认领申请
     */
    void cancelClaimApplication(Long userId, Long id);

    /**
     * 获得客户认领申请详情
     */
    CustomerClaimApplicationRespVO getClaimApplicationDetail(Long id);

    /**
     * 获得客户认领申请分页
     */
    PageResult<CustomerClaimApplicationDO> getClaimApplicationPage(CustomerClaimApplicationPageReqVO pageReqVO);

    /**
     * 更新认领申请状态（由BPM流程回调）
     */
    void updateClaimStatus(Long id, Integer status);

    /**
     * 处理客户认领流程结束事件（流程后置通知）
     */
    void handleClaimProcessEnd(String processInstanceId);
}
```

**Service 实现 - 核心方法**：

```java
@Service
@Validated
public class CustomerClaimServiceImpl implements CustomerClaimService {

    /**
     * 客户认领对应的流程定义 KEY
     * 重要：必须与 BPM 流程模型的 Key 一致
     */
    public static final String PROCESS_KEY = "customer_claim";

    @Resource
    private CustomerClaimApplicationMapper claimApplicationMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long applyForClaim(Long userId, CustomerClaimApplicationApplyReqVO createReqVO) {
        // 1. 校验业务逻辑（如客户是否可以认领）
        validateClaimEligibility(createReqVO.getCustomerId());

        // 2. 获取申请人部门ID
        Long deptId = adminUserApi.getUser(userId).getDeptId();

        // 3. 创建认领申请记录
        CustomerClaimApplicationDO application = BeanUtils.toBean(createReqVO, CustomerClaimApplicationDO.class);
        application.setApplicantUserId(userId);
        application.setApplicantDeptId(deptId);
        application.setApplyDate(LocalDate.now());
        application.setProcessStatus(1); // 审批中
        claimApplicationMapper.insert(application);

        // 4. 查询客户名称用于流程标题
        String customerName = getCustomerName(createReqVO.getCustomerId());

        // 5. 准备流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", createReqVO.getCustomerId());
        variables.put("customerName", customerName);
        variables.put("applyReason", createReqVO.getApplyReason());
        variables.put("applicantUserId", userId);
        variables.put("applicantDeptId", deptId);

        // 6. 转换审批人选择格式
        Map<String, List<Long>> startUserSelectAssignees = null;
        if (createReqVO.getStartUserSelectAssignees() != null) {
            startUserSelectAssignees = new HashMap<>();
            for (Map.Entry<String, Long> entry : createReqVO.getStartUserSelectAssignees().entrySet()) {
                startUserSelectAssignees.put(entry.getKey(), Collections.singletonList(entry.getValue()));
            }
        }

        // 7. 发起 BPM 流程
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(PROCESS_KEY)  // 流程定义Key
                        .setBusinessKey(String.valueOf(application.getId()))  // 业务Key
                        .setVariables(variables)
                        .setStartUserSelectAssignees(startUserSelectAssignees));

        // 8. 更新流程实例ID
        claimApplicationMapper.updateById(
                new CustomerClaimApplicationDO()
                        .setId(application.getId())
                        .setProcessInstanceId(processInstanceId));

        return application.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleClaimProcessEnd(String processInstanceId) {
        // 1. 根据流程实例ID查询申请记录
        CustomerClaimApplicationDO application = claimApplicationMapper.selectOne(
                new LambdaQueryWrapper<CustomerClaimApplicationDO>()
                        .eq(CustomerClaimApplicationDO::getProcessInstanceId, processInstanceId)
        );

        if (application == null) {
            throw exception(CUSTOMER_CLAIM_APPLICATION_NOT_EXISTS);
        }

        // 2. 判断流程审批结果
        if (application.getProcessStatus() != 2) {
            // 如果不是审批通过状态,则不处理
            return;
        }

        // 3. 审批通过,执行业务逻辑（如自动分配客户）
        autoAssignCustomerAfterClaimApproved(application.getId());
    }
}
```

#### 2.5 创建 Controller

```java
@Tag(name = "管理后台 - 客户认领申请")
@RestController
@RequestMapping("/aicrm/customer-claim")
@Validated
public class CustomerClaimController {

    @Resource
    private CustomerClaimService customerClaimService;

    @PostMapping("/apply")
    @Operation(summary = "提交客户认领申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:create')")
    public CommonResult<Long> applyForClaim(@Valid @RequestBody CustomerClaimApplicationApplyReqVO createReqVO) {
        return success(customerClaimService.applyForClaim(getLoginUserId(), createReqVO));
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消客户认领申请")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:update')")
    public CommonResult<Boolean> cancelClaimApplication(@RequestParam("id") Long id) {
        customerClaimService.cancelClaimApplication(getLoginUserId(), id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客户认领申请详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:query')")
    public CommonResult<CustomerClaimApplicationRespVO> getClaimApplication(@RequestParam("id") Long id) {
        return success(customerClaimService.getClaimApplicationDetail(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客户认领申请分页")
    @PreAuthorize("@ss.hasPermission('aicrm:customer-claim:query')")
    public CommonResult<PageResult<CustomerClaimApplicationDO>> getClaimApplicationPage(
            @Valid CustomerClaimApplicationPageReqVO pageReqVO) {
        return success(customerClaimService.getClaimApplicationPage(pageReqVO));
    }
}
```

#### 2.6 关键注意事项

1. **流程定义 Key**：
   - `PROCESS_KEY` 必须与 BPM 流程模型的 Key 一致
   - 建议使用模块名_业务名的格式，如 `customer_claim`

2. **业务 Key**：
   - 必须是唯一的，通常使用业务表主键（`application.getId()`）
   - 转换为字符串传递给 BPM：`String.valueOf(application.getId())`

3. **流程变量**：
   - 将需要在审批过程中使用的业务数据传递给 BPM
   - 可以在流程表达式、任务分配中使用这些变量

4. **事务管理**：
   - 业务数据保存和流程发起应该在同一个事务中
   - 如果流程发起失败，业务数据应该回滚

---

### Step 3: 前端开发

#### 3.1 创建 API 文件

**`src/api/aicrm/customer-claim.ts`**：

```typescript
import { requestClient } from '#/api/request';

export namespace CustomerClaimApi {
  // 申请VO
  export interface ApplyReqVO {
    customerId: number;
    applyReason: string;
    startUserSelectAssignees?: Record<string, number>;
  }

  // 响应VO
  export interface ApplicationRespVO {
    id: number;
    customerId: number;
    customerName: string;
    applicantUserId: number;
    applicantUserName: string;
    applyDate: string;
    applyReason: string;
    processInstanceId: string;
    processStatus: number;
    createTime: string;
  }
}

// 提交客户认领申请
export const applyForClaim = (data: CustomerClaimApi.ApplyReqVO) => {
  return requestClient.post<number>('/aicrm/customer-claim/apply', data);
};

// 取消客户认领申请
export const cancelClaimApplication = (id: number) => {
  return requestClient.put<boolean>(`/aicrm/customer-claim/cancel?id=${id}`);
};

// 获得客户认领申请详情
export const getClaimApplication = (id: number) => {
  return requestClient.get<CustomerClaimApi.ApplicationRespVO>(
    `/aicrm/customer-claim/get?id=${id}`,
  );
};

// 获得客户认领申请分页
export const getClaimApplicationPage = (params: any) => {
  return requestClient.get<PageResult<CustomerClaimApi.ApplicationRespVO>>(
    '/aicrm/customer-claim/page',
    { params },
  );
};
```

#### 3.2 创建申请表单

**`src/views/aicrm/customer-claim/apply-modal.vue`**：

```vue
<script lang="ts" setup>
import { ref } from 'vue';
import { message } from 'ant-design-vue';
import { applyForClaim } from '#/api/aicrm/customer-claim';

const visible = ref(false);
const loading = ref(false);
const formData = ref({
  customerId: undefined,
  applyReason: '',
  startUserSelectAssignees: {},
});

// 打开表单
function open(customerId: number) {
  visible.value = true;
  formData.value.customerId = customerId;
}

// 提交申请
async function handleSubmit() {
  loading.value = true;
  try {
    await applyForClaim(formData.value);
    message.success('申请提交成功');
    visible.value = false;
    emit('success');
  } catch (error: any) {
    message.error(error.message || '申请提交失败');
  } finally {
    loading.value = false;
  }
}

defineExpose({ open });
</script>

<template>
  <a-modal
    v-model:visible="visible"
    title="客户认领申请"
    :confirm-loading="loading"
    @ok="handleSubmit"
  >
    <a-form :model="formData" layout="vertical">
      <a-form-item label="申请理由" name="applyReason" required>
        <a-textarea
          v-model:value="formData.applyReason"
          :rows="4"
          placeholder="请输入申请理由"
          :maxlength="500"
          show-count
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
```

#### 3.3 创建列表页面

```vue
<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getClaimApplicationPage } from '#/api/aicrm/customer-claim';

const [Grid, gridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', width: 70, title: '序号' },
      { field: 'customerName', title: '客户名称', minWidth: 150 },
      { field: 'applicantUserName', title: '申请人', minWidth: 120 },
      { field: 'applyDate', title: '申请日期', minWidth: 120 },
      { field: 'applyReason', title: '申请理由', minWidth: 200 },
      {
        field: 'processStatus',
        title: '流程状态',
        minWidth: 100,
        cellRender: {
          name: 'CellDict',
          props: { type: 'bpm_process_instance_status' },
        },
      },
    ],
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getClaimApplicationPage({
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          });
        },
      },
    },
  },
});

onMounted(() => {
  gridApi.query();
});
</script>

<template>
  <div class="claim-list-page">
    <Grid />
  </div>
</template>
```

#### 3.4 前端注意事项

1. **流程状态字典**：
   - 使用 `bpm_process_instance_status` 字典显示流程状态
   - 状态值：1-审批中、2-审批通过、3-审批拒绝、4-已取消

2. **权限控制**：
   - 使用 `v-auth` 指令控制按钮权限
   - 权限标识需要在系统菜单中配置

3. **用户体验**：
   - 提交成功后显示成功提示
   - 刷新列表数据
   - 关闭表单弹窗

---

### Step 4: BPM 流程设计

#### 4.1 创建流程模型

1. 访问：**工作流管理 -> 流程模型**
2. 点击【新建】按钮
3. 填写流程信息：
   - **流程标识（Key）**：`customer_claim`（**必须与后端 PROCESS_KEY 一致**）
   - **流程名称**：客户认领审批流程
   - **流程描述**：用于客户认领申请的审批流程
   - **表单类型**：选择 **业务表单**
   - **业务表单路由**：`/aicrm/customer-claim/detail` （申请详情页面路由）

#### 4.2 设计流程图

使用 **BPMN 设计器** 或 **Simple 设计器** 设计流程：

```
                   ┌──────────────┐
                   │  开始事件    │
                   └──────┬───────┘
                          │
                   ┌──────▼───────┐
                   │  部门经理审批 │
                   └──────┬───────┘
                          │
                   ┌──────▼───────┐
                   │  分管领导审批 │
                   └──────┬───────┘
                          │
                   ┌──────▼───────┐
                   │  结束事件    │
                   └──────────────┘
```

#### 4.3 配置审批节点

**部门经理审批节点**：

- **节点 ID**：`deptManagerApprove`
- **节点名称**：部门经理审批
- **审批人类型**：发起人自选 / 部门负责人 / 指定角色等
- **审批操作**：
  - ✅ 同意：通过
  - ❌ 拒绝：流程结束
  - 🔙 退回：退回到发起人

**分管领导审批节点**：

- **节点 ID**：`leaderApprove`
- **节点名称**：分管领导审批
- **审批人类型**：指定用户 / 指定角色 / 表达式等

#### 4.4 配置业务表单路由

在流程模型的【表单设置】中配置：

- **表单类型**：业务表单
- **表单提交路由**：`/aicrm/customer-claim/apply`
  - 用户点击发起流程时，跳转到此路由填写申请表单
  - 前端需要实现此页面，并在提交时调用后端 API 发起流程

- **表单查看路由**：`/aicrm/customer-claim/detail?id={businessKey}`
  - 审批人查看申请详情时，跳转到此路由
  - `{businessKey}` 会被自动替换为业务 Key（申请ID）
  - 前端需要实现此页面，展示申请详情和审批历史

#### 4.5 配置流程后置通知（重要）

在流程模型的【扩展属性】中配置：

- **流程后置通知 URL**：`http://localhost:48080/admin-api/aicrm/customer-claim/callback/process-end`
- **触发时机**：流程审批通过时自动调用
- **传递参数**：`processInstanceId`（流程实例ID）

**注意**：
- 生产环境需要修改为实际的服务地址
- 后端需要实现回调接口（见 Step 5）

#### 4.6 部署流程

1. 保存流程模型
2. 点击【部署】按钮
3. 流程部署成功后，可以在【流程定义】中查看
4. 确认流程定义 Key 为 `customer_claim`

#### 4.7 BPM 配置注意事项

1. **流程标识（Key）一致性**：
   - 前端路由中的流程 Key
   - 后端 `PROCESS_KEY` 常量
   - BPM 流程模型的 Key
   - 三者必须完全一致

2. **业务表单路由规范**：
   - 提交路由：用于新建申请
   - 查看路由：用于查看详情，必须包含 `{businessKey}` 占位符

3. **流程变量命名**：
   - 使用驼峰命名法
   - 避免使用 BPM 保留字段
   - 建议加上业务前缀

4. **审批人配置**：
   - 确保审批人有权限访问系统
   - 测试时可以配置为发起人自己

---

### Step 5: 流程后置通知

#### 5.1 创建回调 Controller

```java
@Tag(name = "管理后台 - 客户认领流程回调")
@RestController
@RequestMapping("/aicrm/customer-claim/callback")
@Slf4j
public class CustomerClaimCallbackController {

    @Resource
    private CustomerClaimService customerClaimService;

    @PostMapping("/process-end")
    @Operation(summary = "流程结束回调")
    @Parameter(name = "processInstanceId", description = "流程实例ID", required = true)
    public CommonResult<Boolean> processEndCallback(@RequestParam("processInstanceId") String processInstanceId) {
        log.info("[processEndCallback][接收到客户认领流程结束回调，流程实例ID: {}]", processInstanceId);

        try {
            customerClaimService.handleClaimProcessEnd(processInstanceId);
            return success(true);
        } catch (Exception e) {
            log.error("[processEndCallback][处理客户认领流程结束回调失败，流程实例ID: {}]", processInstanceId, e);
            // 即使失败也返回成功，避免影响流程引擎
            return success(false);
        }
    }
}
```

#### 5.2 配置回调接口白名单

由于回调接口由 BPM 流程引擎调用，不携带 Token，需要配置为白名单：

```java
@Configuration(proxyBeanMethods = false, value = "aicrmSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("aicrmAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // 客户认领流程回调接口 - 无需身份验证
                registry.requestMatchers(buildAdminApi("/aicrm/customer-claim/callback/**")).permitAll();
            }

        };
    }
}
```

#### 5.3 配置流程后置通知 URL

执行 SQL 脚本配置（生产环境需要修改为实际地址）：

```sql
UPDATE bpm_process_definition_info
SET process_after_trigger_setting = JSON_OBJECT(
    'url', 'http://localhost:48080/admin-api/aicrm/customer-claim/callback/process-end',
    'header', JSON_ARRAY(),
    'body', JSON_ARRAY(),
    'response', JSON_ARRAY()
)
WHERE process_definition_id LIKE 'customer_claim%';
```

#### 5.4 实现业务逻辑

在 `handleClaimProcessEnd` 方法中实现自动化业务逻辑：

```java
@Override
@Transactional(rollbackFor = Exception.class)
public void handleClaimProcessEnd(String processInstanceId) {
    // 1. 根据流程实例ID查询申请记录
    CustomerClaimApplicationDO application = claimApplicationMapper.selectOne(
            new LambdaQueryWrapper<CustomerClaimApplicationDO>()
                    .eq(CustomerClaimApplicationDO::getProcessInstanceId, processInstanceId)
    );

    if (application == null) {
        throw exception(CUSTOMER_CLAIM_APPLICATION_NOT_EXISTS);
    }

    // 2. 判断流程审批结果
    if (application.getProcessStatus() != 2) {
        // 如果不是审批通过状态,则不处理
        return;
    }

    // 3. 审批通过,自动分配客户
    autoAssignCustomerAfterClaimApproved(application.getId());
}

private void autoAssignCustomerAfterClaimApproved(Long applicationId) {
    // 1. 查询申请信息
    CustomerClaimApplicationDO application = claimApplicationMapper.selectById(applicationId);

    // 2. 创建客户归属关系
    CustomerAssignmentDO assignment = new CustomerAssignmentDO();
    assignment.setCustomerId(application.getCustomerId());
    assignment.setUserId(application.getApplicantUserId());
    assignment.setDeptId(application.getApplicantDeptId());
    assignment.setAssignmentType(1); // 主办
    assignment.setHasViewRight(true);
    assignment.setHasMaintainRight(true);
    customerAssignmentMapper.insert(assignment);

    // 3. 记录操作历史
    // ...
}
```

#### 5.5 后置通知注意事项

1. **异常处理**：
   - Controller 层应该捕获所有异常
   - 即使业务处理失败，也返回成功给 BPM
   - 避免影响流程状态

2. **幂等性**：
   - 回调接口应该具有幂等性
   - 避免重复调用导致重复处理

3. **事务管理**：
   - 业务逻辑应该在事务中执行
   - 确保数据一致性

4. **日志记录**：
   - 记录关键操作日志
   - 便于问题排查

---

### Step 6: 测试验证

#### 6.1 功能测试清单

- [ ] **发起流程**
  - 能否成功提交申请
  - 流程实例是否创建成功
  - 业务数据是否正确保存
  - `process_instance_id` 是否正确关联

- [ ] **审批流程**
  - 审批人能否收到待办任务
  - 能否正常同意/拒绝
  - 流程能否正常流转
  - 审批意见是否正确记录

- [ ] **流程结束**
  - 流程结束后状态是否更新
  - 后置通知是否被触发
  - 自动化业务逻辑是否执行成功
  - 数据是否正确更新

- [ ] **异常情况**
  - 流程拒绝时是否不执行业务逻辑
  - 流程取消时是否正确处理
  - 网络异常时是否有重试机制

#### 6.2 测试步骤

1. **准备测试数据**：
   - 创建测试客户
   - 创建测试用户
   - 配置测试角色和权限

2. **发起流程**：
   ```bash
   # 调用前端页面提交申请
   # 或使用 Postman 调用 API
   POST /admin-api/aicrm/customer-claim/apply
   {
     "customerId": 1,
     "applyReason": "测试申请理由"
   }
   ```

3. **查看流程实例**：
   - 访问：工作流管理 -> 流程实例
   - 查看流程状态和审批历史

4. **审批流程**：
   - 访问：我的流程 -> 待办任务
   - 同意或拒绝审批

5. **验证结果**：
   - 检查业务数据是否更新
   - 检查流程状态是否正确
   - 检查自动化逻辑是否执行

#### 6.3 调试技巧

1. **查看日志**：
   ```bash
   # 后端日志
   tail -f logs/sys-info.log | grep "customer-claim"

   # BPM 日志
   tail -f logs/sys-info.log | grep "BPM"
   ```

2. **数据库查询**：
   ```sql
   -- 查看申请记录
   SELECT * FROM customer_claim_application ORDER BY id DESC LIMIT 10;

   -- 查看流程实例
   SELECT * FROM act_hi_procinst ORDER BY start_time_ DESC LIMIT 10;

   -- 查看流程任务
   SELECT * FROM act_hi_taskinst ORDER BY start_time_ DESC LIMIT 10;
   ```

3. **API 调试**：
   - 使用 Swagger/Knife4j 测试 API
   - 访问：http://localhost:48080/doc.html

---

## 四、注意事项与最佳实践

### 4.1 流程设计规范

1. **流程 Key 命名**：
   - 使用模块名_业务名格式
   - 全小写，下划线分隔
   - 示例：`customer_claim`、`contract_approve`

2. **节点 ID 命名**：
   - 使用驼峰命名法
   - 语义清晰，易于理解
   - 示例：`deptManagerApprove`、`leaderApprove`

3. **流程变量命名**：
   - 使用驼峰命名法
   - 避免使用保留字
   - 示例：`customerId`、`applyReason`

### 4.2 数据库设计规范

1. **必须字段**：
   - `id`：主键，作为业务 Key
   - `process_instance_id`：关联流程实例
   - `process_status`：流程状态
   - `tenant_id`：多租户隔离

2. **索引建议**：
   - `idx_process_instance_id`：用于流程回调查询
   - `idx_user_id`：用于用户查询
   - `idx_create_time`：用于时间排序

3. **字段类型**：
   - 日期类型使用 `date` 或 `datetime`
   - 金额类型使用 `decimal`
   - 状态类型使用 `int`

### 4.3 代码开发规范

1. **事务管理**：
   - 业务数据保存和流程发起在同一事务
   - 使用 `@Transactional` 注解
   - 注意事务传播行为

2. **异常处理**：
   - Controller 层统一异常处理
   - Service 层抛出业务异常
   - 日志记录关键信息

3. **权限控制**：
   - 使用 `@PreAuthorize` 控制接口权限
   - 前端使用 `v-auth` 控制按钮权限
   - 确保权限粒度合理

### 4.4 安全注意事项

1. **回调接口安全**：
   - 配置为白名单，无需 Token
   - 验证请求来源（可选）
   - 记录访问日志

2. **数据权限**：
   - 用户只能查看自己的申请
   - 审批人只能查看待审批的申请
   - 使用数据权限注解控制

3. **防止重复提交**：
   - 前端提交按钮防抖
   - 后端幂等性控制
   - 数据库唯一索引

### 4.5 性能优化建议

1. **分页查询**：
   - 列表页面必须分页
   - 默认每页 20 条
   - 最大每页 100 条

2. **关联查询**：
   - 避免 N+1 查询
   - 使用 Join 或批量查询
   - 查询结果缓存

3. **流程变量**：
   - 不要存储大对象
   - 避免存储敏感信息
   - 只存储必要的业务数据

---

## 五、常见问题

### 5.1 流程发起失败

**问题**：调用 `createProcessInstance` 时抛出异常

**原因**：
1. 流程定义 Key 不存在或未部署
2. 流程变量格式错误
3. 审批人配置错误

**解决方法**：
1. 检查流程定义是否部署成功
2. 检查 `PROCESS_KEY` 是否与流程模型一致
3. 检查流程变量格式是否正确
4. 查看日志获取详细错误信息

### 5.2 流程实例 ID 为空

**问题**：业务表中 `process_instance_id` 为空

**原因**：
1. 流程发起失败，但未回滚业务数据
2. 更新流程实例 ID 失败

**解决方法**：
1. 确保业务数据保存和流程发起在同一事务
2. 检查事务配置是否正确
3. 检查数据库连接是否正常

### 5.3 后置通知未触发

**问题**：流程审批通过后，回调接口未被调用

**原因**：
1. 流程后置通知 URL 未配置
2. 回调接口地址错误
3. 回调接口返回异常

**解决方法**：
1. 检查 `bpm_process_definition_info` 表中的配置
2. 执行 SQL 配置脚本
3. 检查回调接口是否在白名单中
4. 查看 BPM 日志获取详细错误

### 5.4 审批人看不到待办任务

**问题**：审批人登录后看不到待办任务

**原因**：
1. 审批人配置错误
2. 用户权限不足
3. 流程未正确流转

**解决方法**：
1. 检查审批人配置是否正确
2. 检查用户是否有查看待办任务的权限
3. 查看流程实例状态
4. 检查流程日志

### 5.5 业务表单路由跳转失败

**问题**：点击流程实例详情时，跳转的页面报错

**原因**：
1. 前端路由未配置
2. 业务 Key 格式错误
3. 页面参数解析错误

**解决方法**：
1. 检查前端路由配置
2. 检查 `{businessKey}` 占位符是否被正确替换
3. 检查页面参数是否正确解析
4. 使用浏览器开发者工具调试

### 5.6 多租户数据隔离失败

**问题**：不同租户可以看到对方的申请数据

**原因**：
1. 业务表未包含 `tenant_id` 字段
2. 查询时未过滤租户条件
3. BPM 租户配置错误

**解决方法**：
1. 确保业务表包含 `tenant_id` 字段
2. 使用 MyBatis Plus 租户插件
3. 检查 BPM 多租户配置
4. 测试时使用不同租户账号验证

---

## 六、扩展说明

### 6.1 其他流程通知

除了流程后置通知，BPM 还支持：

- **流程前置通知**（`processBeforeTriggerSetting`）：流程开始时触发
- **任务前置通知**（`taskBeforeTriggerSetting`）：任务创建时触发
- **任务后置通知**（`taskAfterTriggerSetting`）：任务完成时触发

配置方式与流程后置通知类似。

### 6.2 流程变量表达式

在流程设计中，可以使用表达式访问流程变量：

```java
// 审批人分配表达式
${customerId > 1000000 ? 'user1' : 'user2'}

// 条件网关表达式
${applyAmount > 10000}

// 任务监听器表达式
${@customService.handleTask(execution)}
```

### 6.3 自定义审批意见

可以在审批时添加自定义意见字段：

```java
Map<String, Object> variables = new HashMap<>();
variables.put("approveComment", "同意申请，理由充分");
variables.put("approveResult", "approve");
```

### 6.4 流程图可视化

可以在前端展示流程图：

1. 调用 BPM API 获取流程定义 XML
2. 使用 bpmn.js 渲染流程图
3. 高亮显示当前节点和已完成节点

---

## 七、相关文档

- 易诚开源官方文档：https://doc.iocoder.cn/
- BPM 工作流文档：https://doc.iocoder.cn/bpm/
- Flowable 官方文档：https://www.flowable.com/open-source/docs/
- BPMN 2.0 规范：https://www.omg.org/spec/BPMN/2.0/

---

## 八、总结

本文档详细介绍了使用易诚 BPM 模块的业务表单模式开发客户认领申请及审批流程的完整过程，包括：

1. ✅ 数据库设计 - 业务表结构和关键字段
2. ✅ 后端开发 - DO/VO/Service/Controller 完整实现
3. ✅ 前端开发 - API/表单/列表页面开发
4. ✅ BPM 流程设计 - 流程建模和配置
5. ✅ 流程后置通知 - 自动化业务逻辑实现
6. ✅ 测试验证 - 完整的测试流程和调试技巧

通过遵循本文档的开发流程和注意事项，可以快速、规范地开发出稳定可靠的 BPM 业务表单流程。

如有问题，请参考官方文档或在社区提问。

---

**文档版本**：v1.0
**最后更新**：2025-11-04
**作者**：易诚源码团队
