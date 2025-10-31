# 客户管理关系模块实施指南

## 一、已完成的工作

### 1.1 数据库脚本
✅ **已创建文件**:
- `backend/sql/mysql/crm_customer_management_relationship.sql` - 表结构SQL
- `backend/sql/mysql/crm_customer_management_relationship_dict.sql` - 字典数据SQL

**执行步骤**:
```bash
# 1. 进入MySQL
mysql -u root -p

# 2. 选择数据库
use ruoyi-vue-pro;

# 3. 执行表结构脚本
source /path/to/crm_customer_management_relationship.sql;

# 4. 执行字典数据脚本
source /path/to/crm_customer_management_relationship_dict.sql;
```

### 1.2 后端DO实体类
✅ **已创建文件**:
- `CustomerClaimApplicationDO.java` - 客户认领申请DO
- `CustomerReturnApplicationDO.java` - 客户退回申请DO
- `CustomerDelegationDO.java` - 客户托管记录DO

## 二、待完成的后端开发任务

### 2.1 Mapper接口 (使用代码生成器)

建议使用系统自带的代码生成器生成以下Mapper:

1. **CustomerClaimApplicationMapper**
   - 表名: `crm_customer_claim_application`
   - 包路径: `cn.iocoder.yudao.module.aicrm.dal.mysql.customerclaim`

2. **CustomerReturnApplicationMapper**
   - 表名: `crm_customer_return_application`
   - 包路径: `cn.iocoder.yudao.module.aicrm.dal.mysql.customerreturn`

3. **CustomerDelegationMapper**
   - 表名: `crm_customer_delegation`
   - 包路径: `cn.iocoder.yudao.module.aicrm.dal.mysql.customerdelegation`

**使用代码生成器步骤**:
1. 访问: http://localhost:48080/admin-api/infra/codegen
2. 导入表: 选择上述3个表
3. 配置生成信息:
   - 模块名: aicrm
   - 业务名: customerclaim / customerreturn / customerdelegation
   - 类名前缀: CustomerClaimApplication / CustomerReturnApplication / CustomerDelegation
4. 生成代码并下载

### 2.2 VO类

需要创建以下VO类 (参考现有的 `CustomerAssignmentSaveReqVO`):

**客户认领模块**:
```
controller/admin/customerclaim/vo/
  ├── CustomerClaimApplicationPageReqVO.java        # 分页查询请求
  ├── CustomerClaimApplicationRespVO.java           # 响应VO
  ├── CustomerClaimApplicationApplyReqVO.java       # 申请请求
  └── CustomerClaimApplicationDetailRespVO.java     # 详情响应(含BPM信息)
```

**客户退回模块**:
```
controller/admin/customerreturn/vo/
  ├── CustomerReturnApplicationPageReqVO.java
  ├── CustomerReturnApplicationRespVO.java
  ├── CustomerReturnApplicationApplyReqVO.java
  └── CustomerReturnApplicationDetailRespVO.java
```

**客户托管模块**:
```
controller/admin/customerdelegation/vo/
  ├── CustomerDelegationPageReqVO.java
  ├── CustomerDelegationRespVO.java
  ├── CustomerDelegationCreateReqVO.java
  └── CustomerDelegationEndReqVO.java
```

**客户分配/移交增强**:
```
controller/admin/customerassignment/vo/
  ├── AssignCustomerReqVO.java          # 客户分配请求
  ├── TransferCustomerReqVO.java        # 客户移交请求
  ├── ReclaimCustomerReqVO.java         # 客户回收请求
  └── ChangeDeptReqVO.java              # 主办变更请求
```

### 2.3 Service层实现

#### 2.3.1 增强现有CustomerAssignmentService

在 `CustomerAssignmentServiceImpl` 中新增方法:

```java
/**
 * 客户分配
 */
void assignCustomers(AssignCustomerReqVO reqVO);

/**
 * 客户移交
 */
void transferCustomers(TransferCustomerReqVO reqVO);

/**
 * 客户回收
 */
void reclaimCustomers(ReclaimCustomerReqVO reqVO);

/**
 * 主办变更
 */
void changePrimaryDept(ChangeDeptReqVO reqVO);

/**
 * 查询未分配客户列表
 */
List<CustomerAssignmentDO> getUnassignedCustomersByDept(Long deptId);
```

#### 2.3.2 创建CustomerDelegationService

```java
@Service
public class CustomerDelegationServiceImpl implements CustomerDelegationService {

    @Resource
    private CustomerDelegationMapper delegationMapper;

    @Resource
    private CustomerAssignmentMapper assignmentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDelegation(CustomerDelegationCreateReqVO reqVO) {
        // 1. 验证客户归属关系
        // 2. 创建托管记录
        // 3. 更新归属表托管状态
        // 4. 记录历史
    }

    @Override
    public void endDelegation(Long id) {
        // 1. 更新托管记录状态
        // 2. 恢复归属表原状态
        // 3. 记录历史
    }
}
```

#### 2.3.3 创建CustomerClaimService (集成BPM)

**关键实现** - 参考 `BpmOALeaveServiceImpl`:

```java
@Service
public class CustomerClaimServiceImpl implements CustomerClaimService {

    public static final String PROCESS_KEY = "crm_customer_claim";

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Resource
    private CustomerClaimApplicationMapper claimApplicationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long applyForClaim(Long userId, CustomerClaimApplicationApplyReqVO reqVO) {
        // 1. 验证客户是否未分配

        // 2. 创建认领申请记录
        CustomerClaimApplicationDO application = BeanUtils.toBean(reqVO, CustomerClaimApplicationDO.class);
        application.setApplicantUserId(userId);
        application.setProcessStatus(1); // 审批中
        claimApplicationMapper.insert(application);

        // 3. 发起BPM流程
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", reqVO.getCustomerId());
        variables.put("applyReason", reqVO.getApplyReason());

        String processInstanceId = processInstanceApi.createProcessInstance(userId,
            new BpmProcessInstanceCreateReqDTO()
                .setProcessDefinitionKey(PROCESS_KEY)
                .setBusinessKey(String.valueOf(application.getId()))
                .setVariables(variables)
                .setStartUserSelectAssignees(reqVO.getStartUserSelectAssignees()));

        // 4. 更新流程实例ID
        claimApplicationMapper.updateById(
            new CustomerClaimApplicationDO()
                .setId(application.getId())
                .setProcessInstanceId(processInstanceId));

        return application.getId();
    }

    @Override
    public void updateClaimStatus(Long id, Integer status) {
        claimApplicationMapper.updateById(
            new CustomerClaimApplicationDO()
                .setId(id)
                .setProcessStatus(status));

        // 如果通过,自动分配客户
        if (status == 2) { // 2=已通过
            autoAssignCustomerAfterClaimApproved(id);
        }
    }

    /**
     * 监听BPM流程完成事件
     */
    @EventListener
    public void onProcessInstanceCompleted(BpmProcessInstanceStatusEvent event) {
        if (!PROCESS_KEY.equals(event.getProcessDefinitionKey())) {
            return;
        }

        Long applicationId = Long.valueOf(event.getBusinessKey());
        Integer status = event.getStatus();

        updateClaimStatus(applicationId, status);
    }

    private void autoAssignCustomerAfterClaimApproved(Long applicationId) {
        // 获取申请记录
        CustomerClaimApplicationDO application = claimApplicationMapper.selectById(applicationId);

        // 调用分配服务自动分配客户
        // customerAssignmentService.assignCustomers(...)
    }
}
```

#### 2.3.4 创建CustomerReturnService (集成BPM)

类似 `CustomerClaimService`,流程定义KEY为 `crm_customer_return`。

### 2.4 Controller层

创建以下Controller (参考现有的 `CustomerAssignmentController`):

1. **CustomerClaimApplicationController**
   - POST `/admin-api/aicrm/customer-claim/apply` - 提交认领申请
   - GET `/admin-api/aicrm/customer-claim/my-applications/page` - 我的申请列表
   - GET `/admin-api/aicrm/customer-claim/{id}` - 申请详情

2. **CustomerReturnApplicationController**
   - POST `/admin-api/aicrm/customer-return/apply` - 提交退回申请
   - GET `/admin-api/aicrm/customer-return/my-applications/page` - 我的申请列表

3. **CustomerDelegationController**
   - POST `/admin-api/aicrm/customer-delegation/create` - 创建托管
   - PUT `/admin-api/aicrm/customer-delegation/end/{id}` - 结束托管
   - GET `/admin-api/aicrm/customer-delegation/page` - 托管记录列表

4. **增强CustomerAssignmentController**
   - POST `/admin-api/aicrm/customer-assignment/assign` - 客户分配
   - POST `/admin-api/aicrm/customer-assignment/transfer` - 客户移交
   - POST `/admin-api/aicrm/customer-assignment/reclaim` - 客户回收
   - POST `/admin-api/aicrm/customer-assignment/change-dept` - 主办变更

### 2.5 错误码添加

在 `ErrorCodeConstants.java` 中添加:

```java
// ========== 客户认领申请 1-020-100-000 ==========
ErrorCode CUSTOMER_CLAIM_APPLICATION_NOT_EXISTS = new ErrorCode(1_020_100_000, "客户认领申请不存在");
ErrorCode CUSTOMER_ALREADY_ASSIGNED = new ErrorCode(1_020_100_001, "客户已分配,无法认领");
ErrorCode CUSTOMER_NOT_IN_SAME_DEPT = new ErrorCode(1_020_100_002, "客户主办机构与申请人部门不一致");

// ========== 客户退回申请 1-020_101-000 ==========
ErrorCode CUSTOMER_RETURN_APPLICATION_NOT_EXISTS = new ErrorCode(1_020_101_000, "客户退回申请不存在");
ErrorCode CUSTOMER_NOT_ASSIGNED_TO_USER = new ErrorCode(1_020_101_001, "客户未分配给该客户经理");

// ========== 客户托管 1-020-102-000 ==========
ErrorCode CUSTOMER_DELEGATION_NOT_EXISTS = new ErrorCode(1_020_102_000, "客户托管记录不存在");
ErrorCode CUSTOMER_ALREADY_DELEGATED = new ErrorCode(1_020_102_001, "客户已处于托管状态");
```

## 三、前端开发任务

### 3.1 API接口封装

在 `frontend/apps/web-antd/src/api/aicrm/` 目录下创建:

```
aicrm/
  ├── customer-claim.ts           # 客户认领API
  ├── customer-return.ts          # 客户退回API
  ├── customer-delegation.ts      # 客户托管API
  └── customer-assignment.ts      # 客户分配/移交API (增强现有)
```

### 3.2 页面开发

参考截图和现有页面 (`aicrm/customer/index.vue`),创建以下页面:

#### 3.2.1 客户归属管理页面 (增强现有)
**文件**: `frontend/apps/web-antd/src/views/aicrm/customer-assignment/index.vue`

功能:
- 客户列表展示 (参考 1-1.png)
- 筛选条件: 个人客户/准入, 客户号, 客户名称, 证件类型, 证件号码, 分配状态等
- 操作按钮: 机构分配, 客户经理分配, 分配历史查询
- 批量操作: 支持批量选择客户进行分配

**关键组件**:
- 使用 `VxeTable` 展示列表
- 分配对话框 (支持选择机构或客户经理)
- 移交对话框 (参考 1-2.png)

#### 3.2.2 客户托管页面
**文件**: `frontend/apps/web-antd/src/views/aicrm/customer-delegation/index.vue`

功能:
- 托管记录列表
- 我托管的客户 / 托管给我的客户 (Tab切换)
- 创建托管对话框 (参考 1-3.png)
- 结束托管操作

#### 3.2.3 客户认领页面
**文件**: `frontend/apps/web-antd/src/views/aicrm/customer-claim/index.vue`

功能:
- 未分配客户列表 (本机构内)
- 认领申请按钮
- 我的认领申请列表 (参考 1-5.png)
- 集成BPM审批流程查看

**BPM集成**:
```vue
<template>
  <!-- 查看审批流程 -->
  <a-button @click="viewBpmProcess(record.processInstanceId)">
    查看审批进度
  </a-button>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

const viewBpmProcess = (processInstanceId: string) => {
  // 跳转到BPM流程详情页
  router.push({
    path: '/bpm/process-instance/detail',
    query: { id: processInstanceId }
  });
};
</script>
```

#### 3.2.4 客户退回页面
**文件**: `frontend/apps/web-antd/src/views/aicrm/customer-return/index.vue`

功能:
- 我的客户列表
- 退回申请按钮
- 我的退回申请列表
- 集成BPM审批流程查看

#### 3.2.5 归属变更历史页面
**文件**: `frontend/apps/web-antd/src/views/aicrm/customer-assignment-history/index.vue`

功能:
- 归属变更历史列表 (参考 1-4.png)
- 筛选条件: 客户编号, 客户名称, 调整时间范围
- 展示字段:
  - 客户编号, 客户名称, 客户等级
  - 调整前归属机构名称, 调整前归属客户经理编号, 调整前归属客户经理名称
  - 调整后归属机构名称, 调整后归属客户经理编号, 调整后归属客户经理名称

### 3.3 路由配置

在 `frontend/apps/web-antd/src/router/routes/` 中添加路由:

```typescript
{
  path: '/aicrm',
  name: 'AICRM',
  component: LAYOUT,
  meta: { title: '客户关系管理' },
  children: [
    {
      path: 'customer-assignment',
      name: 'CustomerAssignment',
      component: () => import('@/views/aicrm/customer-assignment/index.vue'),
      meta: { title: '客户归属管理', permission: 'aicrm:assignment:query' }
    },
    {
      path: 'customer-delegation',
      name: 'CustomerDelegation',
      component: () => import('@/views/aicrm/customer-delegation/index.vue'),
      meta: { title: '客户托管管理', permission: 'aicrm:delegation:query' }
    },
    {
      path: 'customer-claim',
      name: 'CustomerClaim',
      component: () => import('@/views/aicrm/customer-claim/index.vue'),
      meta: { title: '客户认领', permission: 'aicrm:claim:query' }
    },
    {
      path: 'customer-return',
      name: 'CustomerReturn',
      component: () => import('@/views/aicrm/customer-return/index.vue'),
      meta: { title: '客户退回', permission: 'aicrm:return:query' }
    },
    {
      path: 'assignment-history',
      name: 'AssignmentHistory',
      component: () => import('@/views/aicrm/customer-assignment-history/index.vue'),
      meta: { title: '归属变更历史', permission: 'aicrm:assignment-history:query' }
    }
  ]
}
```

## 四、BPM流程配置

### 4.1 创建客户认领审批流程

1. 访问: http://localhost:5666 (前端管理系统)
2. 进入: 工作流 -> 流程定义 -> 新建流程
3. 流程信息:
   - 流程标识: `crm_customer_claim`
   - 流程名称: 客户认领审批
   - 流程分类: 选择"CRM"分类
4. 设计流程 (使用BPMN设计器或Simple设计器):
   ```
   开始 -> 部门主管审批 -> 结束
   ```
5. 配置审批节点:
   - 节点名称: 部门主管审批
   - 审批人: 发起人的部门负责人
   - 表单字段:
     - customerId (客户ID)
     - customerName (客户名称)
     - applyReason (申请理由)

### 4.2 创建客户退回审批流程

类似客户认领流程,流程标识为 `crm_customer_return`。

## 五、测试验证

### 5.1 后端测试

```bash
# 1. 启动后端服务
cd backend/yudao-server
mvn spring-boot:run -Dspring-boot.run.profiles=local

# 2. 访问API文档
http://localhost:48080/doc.html

# 3. 测试接口
# - 客户分配
# - 客户移交
# - 客户托管
# - 客户认领 (含BPM流程)
# - 客户退回 (含BPM流程)
```

### 5.2 前端测试

```bash
# 1. 启动前端服务
cd frontend
pnpm dev:antd

# 2. 访问页面
http://localhost:5666

# 3. 功能测试
# - 客户归属管理: 分配、移交
# - 客户托管: 创建托管、结束托管
# - 客户认领: 提交申请、查看审批进度
# - 客户退回: 提交申请、查看审批进度
# - 归属历史: 查询变更记录
```

## 六、常见问题

### 6.1 BPM流程启动失败

**问题**: 调用 `processInstanceApi.createProcessInstance` 失败

**解决**:
1. 检查流程定义是否已部署且启用
2. 检查流程定义KEY是否正确
3. 检查流程变量是否完整
4. 查看后端日志详细错误信息

### 6.2 事件监听器不生效

**问题**: `@EventListener` 没有被触发

**解决**:
1. 确保Service类加了 `@Service` 注解
2. 确保方法是 `public` 的
3. 检查事件类型是否匹配
4. 检查流程定义KEY匹配逻辑

### 6.3 数据权限问题

**问题**: 客户经理看不到应该看到的客户

**解决**:
1. 检查 `crm_customer_assignment` 表的归属关系
2. 检查Service层的数据权限过滤逻辑
3. 检查用户的部门信息是否正确

## 七、下一步行动

### 立即执行:
1. ✅ 执行数据库脚本
2. ⏳ 使用代码生成器生成Mapper
3. ⏳ 创建VO类
4. ⏳ 实现Service层
5. ⏳ 实现Controller层
6. ⏳ 前端API封装
7. ⏳ 前端页面开发
8. ⏳ BPM流程配置
9. ⏳ 测试验证

### 建议开发顺序:
1. **第一步**: 完成无需审批的功能 (分配、移交、托管、回收)
2. **第二步**: 完成BPM集成功能 (认领、退回)
3. **第三步**: 完成前端页面
4. **第四步**: 配置BPM流程并测试

---

**文档版本**: v1.0
**创建时间**: 2025-10-31
**参考**: docs/customer-management-relationship-technical-design.md
