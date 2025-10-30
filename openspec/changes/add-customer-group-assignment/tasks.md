# Tasks: 在管理信息页面添加归属客户群功能

**现状**: 数据库表和后端基础代码已存在,从联表查询实现开始

## 阶段 1: 字典数据确认 (快速检查)

### T1.1 检查现有字典数据
- [ ] 查询 `system_dict_type` 表确认 `aicrm_customer_group_category` 字典是否存在
- [ ] 查询 `system_dict_type` 表确认 `aicrm_member_type` 字典是否存在
- [ ] 查询 `system_dict_type` 表确认 `aicrm_customer_source` 字典是否存在
- [ ] 查询各字典的 `system_dict_data` 确认字典项是否完整
- 验证: 所有必要的字典数据已存在

### T1.2 创建缺失的字典数据 (如需要)
- [ ] 如缺失则创建 `aicrm_customer_group_category` 字典及字典项
- [ ] 如缺失则创建 `aicrm_member_type` 字典及字典项
- [ ] 如缺失则创建 `aicrm_customer_source` 字典及字典项
- 验证: 字典数据在系统中正确显示

## 阶段 2: 后端联表查询实现 (核心任务)

### T2.1 扩展 CustomerGroupAssignmentRespVO
- [x] 文件已存在: `CustomerGroupAssignmentRespVO.java`
- [ ] 添加客户群信息字段: groupCode, groupName, groupCategory, memberType, customerSource, memberCount
- [ ] 添加关联用户字段: creatorName, updaterName
- [ ] 添加部门字段: deptName
- [ ] 添加 @Schema 注解说明各字段
- 验证: VO 编译通过,字段完整

### T2.2 扩展 CustomerGroupAssignmentMapper 接口
- [x] 文件已存在: `CustomerGroupAssignmentMapper.java`
- [ ] 添加方法签名: `IPage<CustomerGroupAssignmentRespVO> selectPageWithJoin(IPage<CustomerGroupAssignmentRespVO> page, @Param("reqVO") CustomerGroupAssignmentPageReqVO reqVO)`
- 验证: 接口编译通过

### T2.3 实现 Mapper XML 联表查询
- [x] 文件已存在但为空: `CustomerGroupAssignmentMapper.xml`
- [ ] 定义 ResultMap `CustomerGroupAssignmentPageResultMap` 映射所有字段
- [ ] 编写 `selectPageWithJoin` SQL:
  - LEFT JOIN `crm_customer_group_info` g ON cga.group_id = g.id
  - LEFT JOIN `system_users` u1 ON g.creator = u1.username (或 u1.id,需确认)
  - LEFT JOIN `system_users` u2 ON g.updater = u2.username (或 u2.id,需确认)
  - LEFT JOIN `system_dept` d ON g.dept_id = d.id
- [ ] 添加动态查询条件 (customerId, groupId, status 等)
- [ ] 添加 ORDER BY cga.id DESC
- 验证: SQL 语法正确

### T2.4 扩展 CustomerGroupAssignmentService 接口
- [x] 文件已存在: `CustomerGroupAssignmentService.java`
- [ ] 添加方法签名: `PageResult<CustomerGroupAssignmentRespVO> getCustomerGroupAssignmentPageWithJoin(CustomerGroupAssignmentPageReqVO pageReqVO)`
- 验证: 接口编译通过

### T2.5 实现 Service 联表查询方法
- [x] 文件已存在: `CustomerGroupAssignmentServiceImpl.java`
- [ ] 实现 `getCustomerGroupAssignmentPageWithJoin` 方法
- [ ] 创建 IPage 对象: `new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize())`
- [ ] 调用 Mapper 的 selectPageWithJoin 方法
- [ ] 转换 IPage 结果为 PageResult: `new PageResult<>(result.getRecords(), result.getTotal())`
- 验证: Service 实现编译通过

### T2.6 修改 Controller 调用联表查询
- [x] 文件已存在: `CustomerGroupAssignmentController.java`
- [ ] 修改分页查询方法调用 `getCustomerGroupAssignmentPageWithJoin`
- [ ] 验证接口路径和权限注解正确
- 验证: Controller 编译通过

## 阶段 3: 后端编译和验证

### T3.1 编译后端代码
- [ ] 执行 `mvn compile` (不清理,加快速度)
- [ ] 解决编译错误 (如缺少 import、LocalDate 导入等)
- [ ] 如有错误则执行 `mvn clean compile`
- 验证: 编译成功

### T3.2 启动后端服务
- [ ] 启动后端服务 (如已启动则重启)
- [ ] 验证接口在 Swagger 中可见: `/admin-api/aicrm/customer-group-assignment/page`
- 验证: 服务启动成功,接口可访问

### T3.3 测试后端接口
- [ ] 使用 Swagger 或 Postman 测试分页查询接口
- [ ] 传入参数: `customerId=1, pageNo=1, pageSize=10`
- [ ] 验证返回数据包含所有关联字段 (groupCode, groupName, creatorName, deptName 等)
- [ ] 验证字典值正确显示 (groupCategory, memberType, customerSource)
- [ ] 验证用户名称和部门名称不为空
- 验证: 接口返回数据完整且正确

## 阶段 4: 前端 API 模块

### T4.1 创建 API 类型定义
- [ ] 创建目录: `frontend/apps/web-antd/src/api/aicrm/customergroupassignment/`
- [ ] 创建文件: `index.ts`
- [ ] 定义 `CustomerGroupAssignment` 接口,包含所有字段:
  - 基础字段: id, customerId, groupId, assignDate, assignOperatorId, status, remark
  - 客户群字段: groupCode, groupName, groupCategory, memberType, customerSource, memberCount
  - 关联字段: createTime, creatorName, deptName, updateTime, updaterName
- [ ] 导出 namespace `AicrmCustomerGroupAssignmentApi`
- 验证: TypeScript 类型定义正确,无编译错误

### T4.2 创建 API 函数
- [ ] 实现 `getCustomerGroupAssignmentPage` 函数 (必须)
- [ ] 实现 `getCustomerGroupAssignment` 函数 (可选)
- [ ] 实现 `createCustomerGroupAssignment` 函数 (可选)
- [ ] 实现 `updateCustomerGroupAssignment` 函数 (可选)
- [ ] 实现 `deleteCustomerGroupAssignment` 函数 (可选)
- 验证: API 函数可以正常调用

## 阶段 5: 前端组件实现

### T6.1 创建归属客户群表格组件
- [ ] 创建 `frontend/apps/web-antd/src/views/aicrm/retail-customer/components/CustomerGroupAssignmentGrid.vue`
- [ ] 使用 `useVbenVxeGrid` 配置表格
- [ ] 配置表格列 (序号、客户群编号、名称等 13 个字段)
- [ ] 配置 proxyConfig 对接 API
- [ ] 设置表格高度为 400px
- 验证: 组件能正常显示数据

### T6.2 实现字段格式化函数
- [ ] 实现客户群分类字典格式化
- [ ] 实现群级别类型字典格式化
- [ ] 实现客户来源字典格式化
- [ ] 实现日期格式化
- [ ] 实现归属状态格式化
- 验证: 字段显示格式正确

### T6.3 配置表格工具栏
- [ ] 添加刷新按钮
- [ ] 配置工具栏布局
- 验证: 工具栏功能正常

### T6.4 在管理信息页面添加归属客户群区域
- [ ] 修改 `management-info.vue`
- [ ] 在页面最下方添加新的 `tab-section`
- [ ] 添加"归属客户群列表" Tab
- [ ] 引入 `CustomerGroupAssignmentGrid` 组件
- [ ] 设置 table-title 为"归属客户群列表"
- 验证: 页面布局正确,新区域显示在最下方

### T6.5 传递 customerId 参数
- [ ] 从 props 获取 customerId
- [ ] 通过 proxyConfig 的 query 方法传递 customerId
- 验证: 表格能根据 customerId 查询数据

## 阶段 7: 前端样式优化

### T7.1 调整 Tab 样式
- [ ] 确保新增的 Tab 样式与现有 Tab 保持一致
- [ ] 使用相同的 `section-tabs` 类名
- [ ] 验证激活态和非激活态样式
- 验证: 样式一致

### T7.2 调整表格样式
- [ ] 设置表格高度为 400px
- [ ] 调整列宽使所有字段合理显示
- [ ] 设置长文本字段 showOverflow: 'tooltip'
- 验证: 表格显示美观

### T7.3 调整间距和布局
- [ ] 设置与其他 Tab 区域相同的 gap (16px)
- [ ] 确保页面滚动时布局正常
- 验证: 整体布局协调

## 阶段 8: 功能测试

### T8.1 数据加载测试
- [ ] 测试有数据时的显示
- [ ] 测试无数据时的空状态显示
- [ ] 测试数据加载失败的错误处理
- 验证: 各种情况下显示正常

### T8.2 分页功能测试
- [ ] 测试翻页功能
- [ ] 测试每页条数切换
- [ ] 测试总数显示
- 验证: 分页功能正常

### T8.3 字典显示测试
- [ ] 测试客户群分类字典显示
- [ ] 测试群级别类型字典显示
- [ ] 测试客户来源字典显示
- 验证: 字典值正确显示为中文

### T8.4 联表数据测试
- [ ] 测试创建人名称显示
- [ ] 测试最近修改人名称显示
- [ ] 测试创建机构名称显示
- [ ] 测试最近修改机构名称显示
- 验证: 关联数据正确显示

### T8.5 刷新功能测试
- [ ] 测试点击刷新按钮
- [ ] 验证数据重新加载
- 验证: 刷新功能正常

### T8.6 响应式布局测试
- [ ] 测试不同屏幕尺寸下的显示
- [ ] 测试表格横向滚动
- 验证: 响应式布局正常

## 阶段 9: 集成测试

### T9.1 端到端功能测试
- [ ] 从客户列表进入客户详情
- [ ] 切换到管理信息 Tab
- [ ] 滚动到归属客户群区域
- [ ] 验证数据正确显示
- 验证: 完整流程正常

### T9.2 性能测试
- [ ] 测试大数据量时的加载速度
- [ ] 测试联表查询的响应时间
- [ ] 必要时添加索引优化
- 验证: 性能满足要求

### T9.3 跨浏览器测试
- [ ] 测试 Chrome
- [ ] 测试 Firefox
- [ ] 测试 Safari
- 验证: 各浏览器显示一致

## 阶段 10: 文档和收尾

### T10.1 更新 API 文档
- [ ] 确认 Swagger 文档完整
- [ ] 添加接口说明
- 验证: API 文档清晰

### T10.2 代码审查
- [ ] 审查后端代码规范
- [ ] 审查前端代码规范
- [ ] 审查 SQL 性能
- 验证: 代码质量达标

### T10.3 提交代码
- [ ] 提交后端代码
- [ ] 提交前端代码
- [ ] 提交 SQL 脚本
- [ ] 编写 commit message
- 验证: 代码已提交

## 依赖关系 (基于现状调整)

```
T1.1 → T1.2 (如需要)
    ↓
T2.1 → T2.2 → T2.3 → T2.4 → T2.5 → T2.6 (顺序执行)
    ↓
T3.1 → T3.2 → T3.3 (顺序执行)
    ↓
T4.1 → T4.2 (顺序执行)
    ↓
T5.1 → T5.2 → T5.3 → T5.4 → T5.5 (顺序执行)
    ↓
T6.1, T6.2, T6.3 可并行
    ↓
T7.1, T7.2, T7.3, T7.4, T7.5, T7.6 可并行
    ↓
T8.1, T8.2, T8.3 可并行
    ↓
T9.1, T9.2 可并行 → T9.3
```

## 估算 (基于现状调整)

- 总任务数: 约 45 个子任务 (因基础代码已存在,大幅减少)
- 预计工作量: 1.5-2 个工作日
  - 阶段 1: 字典确认 (0.5 小时,可跳过)
  - 阶段 2: 后端联表查询实现 (0.5 天)
  - 阶段 3: 后端编译和验证 (0.25 天)
  - 阶段 4: 前端 API 对接 (0.25 天)
  - 阶段 5: 前端组件实现 (0.5 天)
  - 阶段 6-9: 样式、测试和收尾 (0.5 天)
