# 客户管理关系模块技术方案

## 一、现状分析

### 1.1 现有表结构
系统已有以下核心表:
- **crm_customer_assignment** (客户归属关系表) - 支持主办/协办模式
- **crm_customer_assignment_history** (归属历史表) - 记录归属变更历史
- **system_dept** (机构表) - 组织架构管理
- **system_users** (用户表) - 客户经理信息
- **yudao-module-bpm** (工作流审批模块) - 已集成到系统

### 1.2 已有功能
- 基础的归属关系管理 (CRUD)
- 归属历史记录查询
- 支持主办/协办模式
- 支持归属状态管理 (待生效/生效中/已失效)

### 1.3 需实现功能
根据需求,需要实现以下8大功能:
1. **客户分配** - 手动分配给机构或客户经理
2. **客户移交** - 客户经理间转移
3. **客户托管** - 临时托付给其他客户经理
4. **客户认领** - 客户经理主动申领未分配客户(需审批)
5. **客户退回** - 退回给主管重新分配(需审批)
6. **客户回收** - 主管回收客户
7. **主办变更** - 调整主办机构
8. **客户管理关系变更历史查询**

## 二、技术方案设计

### 2.1 数据库设计

#### 2.1.1 现有表字段增强

**crm_customer_assignment 表新增字段:**
```sql
ALTER TABLE `crm_customer_assignment`
ADD COLUMN `is_delegated` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否托管状态' AFTER `status`,
ADD COLUMN `delegate_from_user_id` bigint NULL DEFAULT NULL COMMENT '托管来源客户经理ID（关联 system_users.id）' AFTER `is_delegated`,
ADD COLUMN `delegate_start_date` date NULL DEFAULT NULL COMMENT '托管开始日期' AFTER `delegate_from_user_id`,
ADD COLUMN `delegate_end_date` date NULL DEFAULT NULL COMMENT '托管结束日期' AFTER `delegate_start_date`,
ADD COLUMN `delegate_reason` varchar(500) NULL DEFAULT NULL COMMENT '托管原因' AFTER `delegate_end_date`;
```

**crm_customer_assignment_history 表新增字段:**
```sql
ALTER TABLE `crm_customer_assignment_history`
ADD COLUMN `operation_type` varchar(50) NOT NULL DEFAULT '' COMMENT '操作类型（字典: aicrm_assignment_operation_type）' AFTER `transfer_reason`,
ADD COLUMN `is_delegate_operation` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否托管操作' AFTER `operation_type`,
ADD COLUMN `process_instance_id` varchar(64) NULL DEFAULT NULL COMMENT '关联的审批流程实例ID（如有审批）' AFTER `is_delegate_operation`;
```

#### 2.1.2 新增业务表

**1. 客户认领申请表 (crm_customer_claim_application)**
```sql
CREATE TABLE `crm_customer_claim_application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer）',
  `applicant_user_id` bigint NOT NULL COMMENT '申请人(客户经理)ID（关联 system_users.id）',
  `applicant_dept_id` bigint NOT NULL COMMENT '申请人部门ID（关联 system_dept.id）',
  `apply_date` date NOT NULL COMMENT '申请日期',
  `apply_reason` varchar(800) NULL DEFAULT NULL COMMENT '申请理由',

  -- 工作流审批相关
  `process_instance_id` varchar(64) NULL DEFAULT NULL COMMENT 'BPM流程实例ID',
  `process_status` tinyint NOT NULL DEFAULT 1 COMMENT '流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',

  -- 审计字段
  `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) COMMENT '客户ID索引',
  INDEX `idx_applicant_user_id`(`applicant_user_id`) COMMENT '申请人索引',
  INDEX `idx_process_instance_id`(`process_instance_id`) COMMENT '流程实例ID索引',
  INDEX `idx_process_status`(`process_status`) COMMENT '流程状态索引'
) ENGINE = InnoDB COMMENT = '客户认领申请表';
```

**2. 客户退回申请表 (crm_customer_return_application)**
```sql
CREATE TABLE `crm_customer_return_application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer）',
  `applicant_user_id` bigint NOT NULL COMMENT '申请人(客户经理)ID（关联 system_users.id）',
  `return_to_user_id` bigint NOT NULL COMMENT '退回给主管ID（关联 system_users.id）',
  `apply_date` date NOT NULL COMMENT '申请日期',
  `return_reason` varchar(800) NULL DEFAULT NULL COMMENT '退回原因',

  -- 工作流审批相关
  `process_instance_id` varchar(64) NULL DEFAULT NULL COMMENT 'BPM流程实例ID',
  `process_status` tinyint NOT NULL DEFAULT 1 COMMENT '流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',

  -- 审计字段
  `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) COMMENT '客户ID索引',
  INDEX `idx_process_instance_id`(`process_instance_id`) COMMENT '流程实例ID索引'
) ENGINE = InnoDB COMMENT = '客户退回申请表';
```

**3. 客户托管记录表 (crm_customer_delegation)**
```sql
CREATE TABLE `crm_customer_delegation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '托管ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer）',
  `from_user_id` bigint NOT NULL COMMENT '托管方客户经理ID（关联 system_users.id）',
  `to_user_id` bigint NOT NULL COMMENT '受托方客户经理ID（关联 system_users.id）',
  `start_date` date NOT NULL COMMENT '托管开始日期',
  `end_date` date NULL DEFAULT NULL COMMENT '托管结束日期（计划）',
  `actual_end_date` date NULL DEFAULT NULL COMMENT '实际结束日期',
  `delegation_reason` varchar(800) NULL DEFAULT NULL COMMENT '托管原因',
  `delegation_status` tinyint NOT NULL DEFAULT 1 COMMENT '托管状态（0=已结束，1=托管中，2=已取消）',

  -- 审计字段
  `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id`) COMMENT '客户ID索引',
  INDEX `idx_from_user_id`(`from_user_id`) COMMENT '托管方索引',
  INDEX `idx_to_user_id`(`to_user_id`) COMMENT '受托方索引',
  INDEX `idx_status`(`delegation_status`) COMMENT '托管状态索引'
) ENGINE = InnoDB COMMENT = '客户托管记录表';
```

### 2.2 字典数据设计

**1. aicrm_assignment_operation_type (归属操作类型)**
```sql
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time)
VALUES ('AICRM归属操作类型', 'aicrm_assignment_operation_type', 0, '客户归属关系操作类型', '1', NOW());

INSERT INTO system_dict_data (sort, label, value, dict_type, status, remark, creator, create_time) VALUES
(1, '手动分配', 'manual_assign', 'aicrm_assignment_operation_type', 0, '管理员手动分配客户', '1', NOW()),
(2, '客户移交', 'transfer', 'aicrm_assignment_operation_type', 0, '客户经理间移交客户', '1', NOW()),
(3, '客户托管', 'delegate', 'aicrm_assignment_operation_type', 0, '托管客户给其他经理', '1', NOW()),
(4, '托管结束', 'delegate_end', 'aicrm_assignment_operation_type', 0, '结束托管关系', '1', NOW()),
(5, '客户认领', 'claim', 'aicrm_assignment_operation_type', 0, '客户经理认领客户', '1', NOW()),
(6, '客户退回', 'return', 'aicrm_assignment_operation_type', 0, '退回客户给主管', '1', NOW()),
(7, '客户回收', 'reclaim', 'aicrm_assignment_operation_type', 0, '主管回收客户', '1', NOW()),
(8, '主办变更', 'dept_transfer', 'aicrm_assignment_operation_type', 0, '变更主办机构', '1', NOW());
```

**2. aicrm_process_status (流程状态)**
```sql
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time)
VALUES ('AICRM流程状态', 'aicrm_process_status', 0, '审批流程状态', '1', NOW());

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time) VALUES
(1, '审批中', '1', 'aicrm_process_status', 0, 'primary', '流程审批中', '1', NOW()),
(2, '已通过', '2', 'aicrm_process_status', 0, 'success', '审批通过', '1', NOW()),
(3, '已拒绝', '3', 'aicrm_process_status', 0, 'danger', '审批拒绝', '1', NOW()),
(4, '已取消', '4', 'aicrm_process_status', 0, 'info', '申请人取消', '1', NOW());
```

**3. aicrm_delegation_status (托管状态)**
```sql
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time)
VALUES ('AICRM托管状态', 'aicrm_delegation_status', 0, '客户托管状态', '1', NOW());

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time) VALUES
(1, '托管中', '1', 'aicrm_delegation_status', 0, 'primary', '正在托管', '1', NOW()),
(2, '已结束', '0', 'aicrm_delegation_status', 0, 'success', '托管已结束', '1', NOW()),
(3, '已取消', '2', 'aicrm_delegation_status', 0, 'info', '托管已取消', '1', NOW());
```

### 2.3 BPM 工作流集成

#### 2.3.1 需要创建的流程定义

**1. 客户认领审批流程 (crm_customer_claim)**
- 流程定义KEY: `crm_customer_claim`
- 审批节点: 部门主管审批
- 业务表单: 认领申请信息
- 流程变量: customerId, customerName, applyReason

**2. 客户退回审批流程 (crm_customer_return)**
- 流程定义KEY: `crm_customer_return`
- 审批节点: 部门主管审批
- 业务表单: 退回申请信息
- 流程变量: customerId, customerName, returnReason

#### 2.3.2 BPM API 集成方式

参考 `BpmOALeaveServiceImpl` 的实现模式:

```java
@Service
public class CustomerClaimServiceImpl implements CustomerClaimService {

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Resource
    private CustomerClaimApplicationMapper claimApplicationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long applyForClaim(Long userId, ClaimApplicationReqVO reqVO) {
        // 1. 插入认领申请记录
        CustomerClaimApplicationDO application = new CustomerClaimApplicationDO();
        application.setCustomerId(reqVO.getCustomerId());
        application.setApplicantUserId(userId);
        application.setProcessStatus(1); // 审批中
        claimApplicationMapper.insert(application);

        // 2. 发起BPM流程
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", reqVO.getCustomerId());
        variables.put("applyReason", reqVO.getApplyReason());

        String processInstanceId = processInstanceApi.createProcessInstance(userId,
            new BpmProcessInstanceCreateReqDTO()
                .setProcessDefinitionKey("crm_customer_claim")
                .setBusinessKey(String.valueOf(application.getId()))
                .setVariables(variables)
                .setStartUserSelectAssignees(reqVO.getStartUserSelectAssignees()));

        // 3. 更新流程实例ID
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
     * 监听BPM流程完成事件,自动更新业务状态
     */
    @EventListener
    public void onProcessInstanceCompleted(BpmProcessInstanceStatusEvent event) {
        if (!"crm_customer_claim".equals(event.getProcessDefinitionKey())) {
            return;
        }

        Long applicationId = Long.valueOf(event.getBusinessKey());
        Integer status = event.getStatus(); // 2=通过, 3=拒绝

        updateClaimStatus(applicationId, status);
    }
}
```

### 2.4 API 接口设计

#### 2.4.1 客户分配模块 (无需审批)

**POST** `/admin-api/aicrm/customer-assignment/assign`
- 功能: 手动分配客户
- 权限: `aicrm:assignment:assign`
- 请求参数: `AssignCustomerReqVO`
  ```java
  @Data
  public class AssignCustomerReqVO {
      @NotNull
      private Long[] customerIds;      // 客户ID数组
      @NotNull
      private String targetType;       // dept/user
      @NotNull
      private Long targetId;           // 目标ID
      @NotNull
      private Integer assignmentType;  // 1=主办，2=协办
      private LocalDate assignDate;
      private LocalDate effectiveDate;
      private String remark;
  }
  ```

#### 2.4.2 客户移交模块 (无需审批)

**POST** `/admin-api/aicrm/customer-assignment/transfer`
- 功能: 客户经理间移交客户
- 权限: `aicrm:assignment:transfer`
- 请求参数: `TransferCustomerReqVO`

**GET** `/admin-api/aicrm/customer-assignment/transfer-history/page`
- 查询移交历史记录

#### 2.4.3 客户托管模块 (无需审批)

**POST** `/admin-api/aicrm/customer-delegation/create`
- 功能: 发起客户托管
- 权限: `aicrm:delegation:create`

**PUT** `/admin-api/aicrm/customer-delegation/end/{id}`
- 结束托管

**GET** `/admin-api/aicrm/customer-delegation/page`
- 查询托管记录列表

#### 2.4.4 客户认领模块 (需要BPM审批)

**POST** `/admin-api/aicrm/customer-claim/apply`
- 功能: 提交认领申请,自动启动审批流程
- 权限: `aicrm:claim:apply`
- 请求参数: `ClaimApplicationReqVO`
  ```java
  @Data
  public class ClaimApplicationReqVO {
      @NotNull
      private Long customerId;         // 客户ID
      @NotEmpty
      private String applyReason;      // 申请理由

      // BPM 发起人自选审批人
      private Map<String, List<Long>> startUserSelectAssignees;
  }
  ```

**GET** `/admin-api/aicrm/customer-claim/my-applications/page`
- 查询我的认领申请记录

**GET** `/admin-api/aicrm/customer-claim/pending-approval/page`
- 查询待我审批的认领申请

**GET** `/admin-api/aicrm/customer-claim/{id}`
- 查询认领申请详情(含审批流程信息)

#### 2.4.5 客户退回模块 (需要BPM审批)

**POST** `/admin-api/aicrm/customer-return/apply`
- 功能: 提交退回申请,自动启动审批流程
- 权限: `aicrm:return:apply`

**GET** `/admin-api/aicrm/customer-return/my-applications/page`
- 查询我的退回申请

**GET** `/admin-api/aicrm/customer-return/{id}`
- 查询退回申请详情

#### 2.4.6 客户回收模块 (无需审批)

**POST** `/admin-api/aicrm/customer-assignment/reclaim`
- 功能: 主管回收客户
- 权限: `aicrm:assignment:reclaim`
- 请求参数: `ReclaimCustomerReqVO`

#### 2.4.7 主办变更模块 (无需审批)

**POST** `/admin-api/aicrm/customer-assignment/change-dept`
- 功能: 变更主办机构
- 权限: `aicrm:assignment:change-dept`

#### 2.4.8 历史查询模块

**GET** `/admin-api/aicrm/customer-assignment-history/page`
- 增强查询条件:
  - operationType: String - 操作类型
  - customerId: Long - 客户ID
  - userId: Long - 客户经理ID
  - deptId: Long - 部门ID
  - startDate/endDate: LocalDate - 日期范围

### 2.5 业务Service设计

#### 2.5.1 核心Service接口

**CustomerAssignmentService** (现有,需增强):
```java
public interface CustomerAssignmentService {
    // 现有方法...

    // 新增方法
    void assignCustomers(AssignCustomerReqVO reqVO);
    void transferCustomers(TransferCustomerReqVO reqVO);
    void changePrimaryDept(ChangeDeptReqVO reqVO);
    void reclaimCustomers(ReclaimCustomerReqVO reqVO);
    List<CustomerAssignmentDO> getUnassignedCustomersByDept(Long deptId);
}
```

**CustomerDelegationService** (新增):
```java
public interface CustomerDelegationService {
    Long createDelegation(CreateDelegationReqVO reqVO);
    void endDelegation(Long id);
    void cancelDelegation(Long id);
    PageResult<CustomerDelegationDO> getDelegationPage(DelegationPageReqVO pageReqVO);
    List<CustomerDelegationDO> getMyActiveDelegations(Long userId);
}
```

**CustomerClaimService** (新增,集成BPM):
```java
public interface CustomerClaimService {
    /**
     * 提交认领申请(发起BPM流程)
     */
    Long applyForClaim(Long userId, ClaimApplicationReqVO reqVO);

    /**
     * 更新认领申请状态(BPM流程回调)
     */
    void updateClaimStatus(Long id, Integer status);

    /**
     * 查询我的认领申请
     */
    PageResult<CustomerClaimApplicationDO> getMyClaimApplications(Long userId, PageParam pageReqVO);

    /**
     * 查询认领申请详情(含BPM流程信息)
     */
    ClaimApplicationDetailRespVO getClaimApplicationDetail(Long id);
}
```

**CustomerReturnService** (新增,集成BPM):
```java
public interface CustomerReturnService {
    Long applyForReturn(Long userId, ReturnApplicationReqVO reqVO);
    void updateReturnStatus(Long id, Integer status);
    PageResult<CustomerReturnApplicationDO> getMyReturnApplications(Long userId, PageParam pageReqVO);
    ReturnApplicationDetailRespVO getReturnApplicationDetail(Long id);
}
```

### 2.6 业务流程设计

#### 2.6.1 客户分配流程 (无审批)
1. 验证客户是否存在
2. 检查目标机构/客户经理是否有效
3. 检查客户当前归属 (如分配主办则需先解除现有主办)
4. 创建新的归属关系记录
5. 记录操作历史

#### 2.6.2 客户移交流程 (无审批)
1. 验证客户归属关系
2. 检查原客户经理和目标客户经理
3. 计算移交级别 (网点内/跨网点/跨支行/跨分行)
4. 更新归属关系 (修改user_id和dept_id)
5. 记录移交历史

#### 2.6.3 客户托管流程 (无审批)
1. 验证客户归属关系
2. 检查托管目标客户经理
3. 创建托管记录
4. 更新归属表 (标记托管状态,临时修改user_id)
5. 托管到期后自动或手动结束,恢复原归属
6. 记录操作历史

#### 2.6.4 客户认领流程 (使用BPM审批)
1. 客户经理提交认领申请
2. 验证客户是否未分配
3. 验证客户经理所属机构与客户主办机构是否一致
4. 创建认领申请记录
5. **调用 BpmProcessInstanceApi 发起审批流程**
6. 保存流程实例ID到申请记录
7. 审批通过后:
   - 监听 BpmProcessInstanceStatusEvent 事件
   - 自动分配客户给申请人
   - 更新申请状态为"已通过"
   - 记录操作历史
8. 审批拒绝后:
   - 更新申请状态为"已拒绝"

#### 2.6.5 客户退回流程 (使用BPM审批)
1. 客户经理提交退回申请
2. 验证客户归属关系
3. 创建退回申请记录
4. **调用 BpmProcessInstanceApi 发起审批流程**
5. 审批通过后:
   - 监听 BpmProcessInstanceStatusEvent 事件
   - 解除归属关系,客户状态变为未分配
   - 更新申请状态为"已通过"
   - 记录操作历史
6. 审批拒绝后:
   - 更新申请状态为"已拒绝"

#### 2.6.6 客户回收流程 (无审批)
1. 主管选择需要回收的客户
2. 验证主管权限 (是否是客户所属机构的负责人)
3. 解除原客户经理的归属关系
4. 客户状态变为未分配
5. 记录操作历史

#### 2.6.7 主办变更流程 (无审批)
1. 验证客户归属关系
2. 检查新主办机构
3. 如有客户经理归属,先解除
4. 更新主办机构
5. 客户状态变为未分配 (待新机构分配客户经理)
6. 记录操作历史

### 2.7 权限控制设计

#### 2.7.1 功能权限
- `aicrm:assignment:assign` - 客户分配 (主管)
- `aicrm:assignment:transfer` - 客户移交 (客户经理)
- `aicrm:delegation:manage` - 客户托管管理
- `aicrm:claim:apply` - 客户认领申请 (所有客户经理)
- `aicrm:return:apply` - 客户退回申请 (客户经理)
- `aicrm:assignment:reclaim` - 客户回收 (主管)
- `aicrm:assignment:change-dept` - 主办变更 (高级管理员)
- `aicrm:assignment-history:query` - 历史查询

#### 2.7.2 数据权限
- 客户经理只能查看和操作自己管理的客户
- 主管可以查看和操作本部门所有客户
- 跨部门操作需要额外授权

## 三、实施计划

### 3.1 第一阶段: 数据库和字典 (1天)
1. 为现有表添加新字段
2. 创建3个新表 (认领申请表、退回申请表、托管记录表)
3. 插入字典数据
4. 编写数据库变更SQL脚本

### 3.2 第二阶段: 后端开发 - 基础模块 (2-3天)
1. 创建DO、VO、ReqVO、RespVO类
2. 创建Mapper接口
3. 实现无需审批的Service (分配、移交、托管、回收、主办变更)
4. 创建Controller和API接口
5. 编写单元测试

### 3.3 第三阶段: BPM流程集成 (2-3天)
1. 在BPM模块中设计和配置工作流:
   - 客户认领审批流程
   - 客户退回审批流程
2. 实现CustomerClaimService (集成BPM API)
3. 实现CustomerReturnService (集成BPM API)
4. 实现BPM事件监听器 (监听审批完成事件)
5. 测试审批流程

### 3.4 第四阶段: 前端开发 (3-4天)
1. 创建客户归属管理页面
2. 创建客户认领页面 (集成BPM审批组件)
3. 创建客户托管页面
4. 创建客户退回页面 (集成BPM审批组件)
5. 创建归属变更历史页面
6. 实现API调用和业务逻辑

### 3.5 第五阶段: 测试和优化 (2天)
1. 功能测试
2. 审批流程测试
3. 权限测试
4. 性能测试
5. 用户验收测试

## 四、关键技术点

### 4.1 BPM工作流集成
- 使用 `BpmProcessInstanceApi` 发起审批流程
- 监听 `BpmProcessInstanceStatusEvent` 事件处理审批结果
- 业务表中保存 `process_instance_id` 关联审批流程
- 业务状态与流程状态保持同步

### 4.2 事务管理
- 所有归属变更操作使用 `@Transactional` 保证原子性
- 归属变更和历史记录插入在同一事务中
- BPM流程发起和业务记录插入在同一事务中

### 4.3 并发控制
- 使用乐观锁防止归属关系被重复修改
- 使用数据库唯一索引保证数据一致性

### 4.4 审计日志
- 所有操作自动记录到归属历史表
- 记录操作人、操作时间、操作类型、变更内容
- 如有审批,记录流程实例ID

### 4.5 性能优化
- 为常用查询字段建立索引
- 使用分页查询减少数据量
- 批量操作使用批量插入/更新

## 五、注意事项

1. **BPM流程定义**: 需要在BPM模块中设计和配置审批流程,定义好流程KEY
2. **事件监听**: 必须实现BPM流程完成事件监听器,自动更新业务状态
3. **数据完整性**: 所有归属变更必须记录历史,不能直接删除
4. **权限验证**: 严格验证操作权限,防止越权操作
5. **业务规则**: 需要与业务方确认具体的审批流程和权限规则
6. **兼容性**: 新增字段使用NULL默认值,不影响现有数据
7. **回滚方案**: 提供数据回滚脚本,应对异常情况
8. **BPM流程设计**: 认领和退回流程需要在前端BPM流程设计器中设计BPMN流程图

## 六、附录

### 6.1 相关表结构

#### crm_customer_assignment (现有表)
主要字段:
- id: 归属关系主键
- customer_id: 客户ID
- assignment_type: 归属类型 (1=主办，2=协办)
- dept_id: 归属部门ID
- user_id: 客户经理用户ID
- status: 归属状态 (0=已失效，1=生效中，2=待生效)

#### crm_customer_assignment_history (现有表)
主要字段:
- id: 调整历史主键
- customer_id: 客户ID
- transfer_date: 调整日期
- before_user_id: 调整前客户经理
- after_user_id: 调整后客户经理

### 6.2 BPM流程集成示例

完整的客户认领申请Service实现:

```java
@Service
@Validated
public class CustomerClaimServiceImpl implements CustomerClaimService {

    @Resource
    private CustomerClaimApplicationMapper claimApplicationMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;

    @Resource
    private CustomerAssignmentService customerAssignmentService;

    /**
     * 流程定义KEY
     */
    public static final String PROCESS_KEY = "crm_customer_claim";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long applyForClaim(Long userId, ClaimApplicationReqVO reqVO) {
        // 1. 验证客户是否存在且未分配
        validateCustomerForClaim(reqVO.getCustomerId());

        // 2. 创建认领申请记录
        CustomerClaimApplicationDO application = BeanUtils.toBean(reqVO, CustomerClaimApplicationDO.class);
        application.setApplicantUserId(userId);
        application.setApplyDate(LocalDate.now());
        application.setProcessStatus(1); // 审批中
        claimApplicationMapper.insert(application);

        // 3. 发起BPM流程
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerId", reqVO.getCustomerId());
        variables.put("customerName", getCustomerName(reqVO.getCustomerId()));
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

        // 更新申请状态
        updateClaimStatus(applicationId, status);

        // 如果审批通过,自动分配客户
        if (status == 2) { // 2=已通过
            autoAssignCustomer(applicationId);
        }
    }

    private void autoAssignCustomer(Long applicationId) {
        CustomerClaimApplicationDO application = claimApplicationMapper.selectById(applicationId);

        // 自动分配客户给申请人
        AssignCustomerReqVO assignReq = new AssignCustomerReqVO();
        assignReq.setCustomerIds(new Long[]{application.getCustomerId()});
        assignReq.setTargetType("user");
        assignReq.setTargetId(application.getApplicantUserId());
        assignReq.setAssignmentType(1); // 主办
        assignReq.setAssignDate(LocalDate.now());
        assignReq.setEffectiveDate(LocalDate.now());
        assignReq.setRemark("客户认领申请审批通过自动分配");

        customerAssignmentService.assignCustomers(assignReq);
    }
}
```

---

**文档版本**: v1.0
**创建时间**: 2025-10-31
**最后更新**: 2025-10-31
