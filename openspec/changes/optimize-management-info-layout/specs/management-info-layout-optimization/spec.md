# Management Info Layout Optimization Specification

## MODIFIED Requirements

### Requirement: 表格高度优化

系统 SHALL 将归属管户关系列表和归属调整历史列表的表格高度调整为显示约 5 条记录的高度,以优化页面垂直空间利用。

#### Scenario: 调整表格高度显示 5 条记录
- **WHEN** 用户访问管理信息页面的任意 Tab
- **THEN** 表格高度应调整为约 260px
- **AND** 表格应能同时显示表头、5 行数据和分页器
- **AND** 当数据超过 5 条时应显示分页控件而非滚动条

#### Scenario: 表格高度保持一致性
- **WHEN** 用户在不同 Tab 之间切换
- **THEN** 所有表格(归属管户、调整历史、归属网格、网格历史)的高度应保持一致
- **AND** 表格高度应为 260px

### Requirement: 双层 Tab 布局结构

系统 SHALL 实现双层嵌套 Tab 结构,外层 Tab 用于区分"归属管户关系"和"归属网格关系",内层 Tab 分别展示关系列表和调整历史。

#### Scenario: 外层 Tab 展示归属类型
- **WHEN** 用户访问管理信息页面
- **THEN** 系统应显示外层 Tab,包含"归属管户关系"和"归属网格关系"两个选项
- **AND** 默认激活"归属管户关系" Tab
- **AND** Tab 应使用 card 类型

#### Scenario: 内层 Tab 展示明细列表
- **WHEN** 用户选择某个外层 Tab
- **THEN** 系统应在该 Tab 内显示内层 Tab
- **AND** "归属管户关系"内层 Tab 应包含"归属管户关系列表"和"归属调整历史"
- **AND** "归属网格关系"内层 Tab 应包含"归属网格关系列表"和"归属网格调整历史"
- **AND** 内层 Tab 默认激活"列表" Tab

#### Scenario: Tab 状态独立管理
- **WHEN** 用户在"归属管户关系"内切换到"归属调整历史"
- **AND** 然后切换到外层"归属网格关系" Tab
- **THEN** "归属网格关系"应显示其自己的内层 Tab 状态(默认"列表")
- **AND** 当用户切回"归属管户关系"时
- **THEN** 应恢复到之前的内层 Tab 状态("归属调整历史")

#### Scenario: Tab 切换流畅性
- **WHEN** 用户快速切换外层或内层 Tab
- **THEN** 切换应流畅无闪烁
- **AND** 响应时间应小于 100ms
- **AND** 不应出现布局抖动

## ADDED Requirements

### Requirement: 归属网格关系列表

系统 SHALL 提供归属网格关系列表,展示客户与网格的归属关系信息,支持分页和刷新功能。

#### Scenario: 加载归属网格关系列表
- **WHEN** 用户切换到"归属网格关系"外层 Tab
- **AND** 内层 Tab 选择"归属网格关系列表"
- **THEN** 系统应调用 `GET /aicrm/customer-grid-assignment/page` API
- **AND** 传递当前客户 ID 和分页参数
- **AND** 在表格中展示归属网格关系记录

#### Scenario: 展示归属网格关系字段
- **WHEN** 系统加载到归属网格关系数据
- **THEN** 表格应包含以下列:
  - 序号 (自动生成, 70px 宽度, 左侧固定)
  - 网格编号 (gridCode, 120px 最小宽度)
  - 网格名称 (gridName, 150px 最小宽度)
  - 网格类型 (gridType, 100px 最小宽度, 使用 aicrm_grid_type 字典转换)
  - 网格管理员 (gridManagerName, 120px 最小宽度)
  - 分配日期 (assignDate, 120px 最小宽度, YYYY-MM-DD 格式)
  - 分配操作人 (assignOperatorName, 120px 最小宽度)
  - 归属状态 (status, 100px 最小宽度, 0=已失效, 1=生效中)
  - 备注 (remark, 200px 最小宽度, tooltip 显示完整内容)

#### Scenario: 网格类型字典转换
- **WHEN** 系统展示网格类型字段
- **THEN** 应使用字典 aicrm_grid_type 将代码转换为可读文本
- **AND** 如果字典值不存在,应显示原始代码值
- **AND** null/undefined 应显示为"-"

#### Scenario: 归属状态格式化
- **WHEN** 系统展示归属状态字段
- **THEN** 0 应显示为"已失效"
- **AND** 1 应显示为"生效中"
- **AND** null/undefined 应显示为"-"

#### Scenario: 空值字段处理
- **WHEN** 系统展示网格管理员、分配操作人或备注等可选字段
- **AND** 字段值为 null 或 undefined
- **THEN** 应显示为"-"
- **AND** 不应显示为"null"或空白

#### Scenario: 刷新归属网格关系列表
- **WHEN** 用户点击表格工具栏的刷新按钮
- **THEN** 系统应重新加载当前页的数据
- **AND** 保持当前的分页状态和过滤条件

### Requirement: 归属网格调整历史列表

系统 SHALL 提供归属网格调整历史列表,展示客户网格归属的变更历史记录,支持分页和刷新功能。

#### Scenario: 加载归属网格调整历史列表
- **WHEN** 用户切换到"归属网格关系"外层 Tab
- **AND** 内层 Tab 选择"归属网格调整历史"
- **THEN** 系统应调用 `GET /aicrm/customer-grid-history/page` API
- **AND** 传递当前客户 ID 和分页参数
- **AND** 在表格中展示归属网格调整历史记录

#### Scenario: 展示归属网格调整历史字段
- **WHEN** 系统加载到归属网格调整历史数据
- **THEN** 表格应包含以下列:
  - 序号 (自动生成, 70px 宽度, 左侧固定)
  - 调整日期 (adjustDate, 120px 最小宽度, 左侧固定, YYYY-MM-DD 格式)
  - 调整原因 (adjustReason, 200px 最小宽度, tooltip 显示完整内容)
  - 调整前网格编号 (beforeGridCode, 130px 最小宽度)
  - 调整前网格名称 (beforeGridName, 150px 最小宽度)
  - 调整前网格类型 (beforeGridType, 120px 最小宽度, 使用 aicrm_grid_type 字典转换)
  - 调整前网格管理员 (beforeGridManagerName, 130px 最小宽度)
  - 调整后网格编号 (afterGridCode, 130px 最小宽度)
  - 调整后网格名称 (afterGridName, 150px 最小宽度)
  - 调整后网格类型 (afterGridType, 120px 最小宽度, 使用 aicrm_grid_type 字典转换)
  - 调整后网格管理员 (afterGridManagerName, 130px 最小宽度)
  - 调整操作人 (adjustOperatorName, 120px 最小宽度)
  - 备注 (remark, 200px 最小宽度, tooltip 显示完整内容)

#### Scenario: 调整前后对比展示
- **WHEN** 系统展示归属网格调整历史记录
- **THEN** 应清晰区分调整前和调整后的信息
- **AND** 调整前字段列标题应添加"调整前"前缀
- **AND** 调整后字段列标题应添加"调整后"前缀
- **AND** 调整前后的同类型字段应相邻排列以便对比

#### Scenario: 网格类型历史记录字典转换
- **WHEN** 系统展示调整前或调整后的网格类型字段
- **THEN** 应使用字典 aicrm_grid_type 将代码转换为可读文本
- **AND** 如果字典值不存在,应显示原始代码值
- **AND** null/undefined 应显示为"-"

#### Scenario: 空值历史字段处理
- **WHEN** 系统展示调整历史中的任意可选字段
- **AND** 字段值为 null 或 undefined
- **THEN** 应显示为"-"
- **AND** 不应显示为"null"或空白

#### Scenario: 刷新归属网格调整历史列表
- **WHEN** 用户点击表格工具栏的刷新按钮
- **THEN** 系统应重新加载当前页的数据
- **AND** 保持当前的分页状态和过滤条件

### Requirement: 后端接口联表查询

系统 SHALL 在后端接口中实现联表查询,填充网格信息和用户名称,避免前端多次请求。

#### Scenario: 归属网格关系接口填充关联信息
- **WHEN** 后端处理 `GET /aicrm/customer-grid-assignment/page` 请求
- **THEN** 系统 MUST 根据 gridId 查询网格信息
- **AND** 填充 gridCode、gridName、gridType 字段
- **AND** 根据网格的 gridManagerUserId 查询用户信息并填充 gridManagerName 字段
- **AND** 根据 assignOperatorId 查询用户信息并填充 assignOperatorName 字段
- **AND** 当关联数据不存在时,相应字段应返回 null

#### Scenario: 归属网格调整历史接口填充用户名称
- **WHEN** 后端处理 `GET /aicrm/customer-grid-history/page` 请求
- **THEN** 系统 MUST 根据 adjustOperatorId 查询用户信息并填充 adjustOperatorName 字段
- **AND** 根据 beforeGridManagerUserId 查询用户信息并填充 beforeGridManagerName 字段
- **AND** 根据 afterGridManagerUserId 查询用户信息并填充 afterGridManagerName 字段
- **AND** 当用户不存在时,相应字段应返回 null
- **AND** 网格信息快照(编号、名称、类型)已存储在历史表中,无需联表查询

#### Scenario: 联表查询性能要求
- **WHEN** 后端处理分页查询请求(每页 10 条记录)
- **THEN** 接口响应时间 MUST 小于 500ms (P95)
- **AND** 每条记录的联表查询次数不应超过 3 次
- **AND** SHOULD 考虑使用缓存减少重复的用户查询

### Requirement: 前端 API 模块创建

系统 SHALL 创建归属网格相关的前端 API 模块,提供类型安全的接口调用方法。

#### Scenario: 创建 customergridassignment API 模块
- **WHEN** 前端需要调用归属网格关系接口
- **THEN** 系统 MUST 提供 `getCustomerGridAssignmentPage` 函数
- **AND** 函数应接受 `PageReqVO` 参数 (customerId, pageNo, pageSize)
- **AND** 函数应返回 Promise<PageResult<CustomerGridAssignment>>
- **AND** 所有类型应使用 TypeScript 定义
- **AND** API 路径应为 `/aicrm/customer-grid-assignment/page`

#### Scenario: 创建 customergridhistory API 模块
- **WHEN** 前端需要调用归属网格调整历史接口
- **THEN** 系统 MUST 提供 `getCustomerGridHistoryPage` 函数
- **AND** 函数应接受 `PageReqVO` 参数 (customerId, pageNo, pageSize)
- **AND** 函数应返回 Promise<PageResult<CustomerGridHistory>>
- **AND** 所有类型应使用 TypeScript 定义
- **AND** API 路径应为 `/aicrm/customer-grid-history/page`

### Requirement: 样式一致性增强

系统 SHALL 确保双层 Tab 结构具有清晰的视觉层次,同时保持与现有页面的样式一致性。

#### Scenario: 双层 Tab 视觉层次
- **WHEN** 用户查看管理信息页面
- **THEN** 外层 Tab 应具有明显的层次感
- **AND** 内层 Tab 应在视觉上从属于外层 Tab
- **AND** 外层 Tab 字体大小应为 14px
- **AND** 内层 Tab 字体大小应为 13px
- **AND** 内层 Tab 应有适当的上边距(8px)以与外层 Tab 区分

#### Scenario: Tab 激活状态视觉反馈
- **WHEN** 用户激活任意 Tab (外层或内层)
- **THEN** 激活的 Tab 应有明显的背景色变化
- **AND** 浅色模式下激活 Tab 背景应为白色
- **AND** 暗黑模式下激活 Tab 背景应为 rgba(255, 255, 255, 0.1)
- **AND** 未激活的 Tab 背景应为透明

#### Scenario: 响应式布局适配
- **WHEN** 用户在不同尺寸的屏幕上查看管理信息页面
- **THEN** 双层 Tab 结构应正确适配屏幕尺寸
- **AND** 大屏(>1400px)应完整显示所有表格列
- **AND** 中屏(768px-1400px)表格应支持横向滚动
- **AND** 小屏(<768px) Tab 标签应自动换行或横向滚动
- **AND** 表格功能在所有屏幕尺寸下应正常工作

### Requirement: 数据字典支持

系统 SHALL 确保网格类型字典存在并正确配置,以支持前端字典翻译功能。

#### Scenario: 验证网格类型字典存在
- **WHEN** 系统启动或部署时
- **THEN** 系统 MUST 确保存在字典类型 `aicrm_grid_type`
- **AND** 字典状态应为"启用"
- **AND** 字典名称应为"AICRM 客户网格类型"

#### Scenario: 网格类型字典数据配置
- **WHEN** 系统查询网格类型字典
- **THEN** 至少应包含以下字典值(根据实际业务调整):
  - 零售网格 (retail)
  - 对公网格 (corporate)
  - 综合网格 (mixed)
- **AND** 每个字典项应配置正确的排序值
- **AND** 所有字典项状态应为"启用"

#### Scenario: 字典缺失时的降级处理
- **WHEN** 前端调用 `getDictLabel('aicrm_grid_type', value)`
- **AND** 字典类型不存在或字典值未配置
- **THEN** 系统 SHOULD 返回原始值而不是 undefined
- **AND** 不应导致页面报错或崩溃
- **AND** SHOULD 在控制台输出警告信息

### Requirement: 错误处理和空状态

系统 SHALL 妥善处理数据加载失败和空数据的情况,提供友好的用户反馈。

#### Scenario: 归属网格关系 API 调用失败
- **WHEN** 后端 API `/aicrm/customer-grid-assignment/page` 调用失败
- **THEN** 系统应显示错误消息提示
- **AND** 错误消息应描述问题原因(网络错误、服务器错误等)
- **AND** 用户应能够通过刷新按钮重试
- **AND** 表格应显示空状态而非崩溃

#### Scenario: 归属网格调整历史 API 调用失败
- **WHEN** 后端 API `/aicrm/customer-grid-history/page` 调用失败
- **THEN** 系统应显示错误消息提示
- **AND** 错误消息应描述问题原因
- **AND** 用户应能够通过刷新按钮重试
- **AND** 表格应显示空状态而非崩溃

#### Scenario: 归属网格关系空数据状态
- **WHEN** 客户没有任何归属网格关系记录
- **THEN** 系统应在表格区域显示友好的空数据提示
- **AND** 提示信息应为"暂无归属网格关系记录"
- **AND** 不应显示加载错误
- **AND** 表格工具栏的刷新按钮应仍然可用

#### Scenario: 归属网格调整历史空数据状态
- **WHEN** 客户没有任何归属网格调整历史记录
- **THEN** 系统应在表格区域显示友好的空数据提示
- **AND** 提示信息应为"暂无归属网格调整历史记录"
- **AND** 不应显示加载错误
- **AND** 表格工具栏的刷新按钮应仍然可用

### Requirement: 性能优化

系统 SHALL 优化数据加载和渲染性能,确保双层 Tab 切换和表格操作流畅。

#### Scenario: 懒加载内层 Tab 数据
- **WHEN** 用户首次打开管理信息页面
- **THEN** 系统应仅加载默认激活的外层和内层 Tab 数据
- **AND** 切换到其他内层 Tab 时才加载对应的数据
- **AND** 已加载过的 Tab 数据 SHOULD 缓存以提升再次切换的速度

#### Scenario: 外层 Tab 切换数据隔离
- **WHEN** 用户从"归属管户关系"切换到"归属网格关系"
- **THEN** 系统应仅加载"归属网格关系"默认内层 Tab 的数据
- **AND** 不应重新加载"归属管户关系"的数据
- **AND** 切换响应时间应小于 100ms

#### Scenario: 分页性能要求
- **WHEN** 用户在任意表格中进行分页操作
- **THEN** 分页切换响应时间 SHOULD 小于 300ms
- **AND** 表格渲染时间(10 条记录) SHOULD 小于 200ms
- **AND** 不应出现明显的加载延迟或界面卡顿

#### Scenario: 刷新操作性能
- **WHEN** 用户点击刷新按钮
- **THEN** 系统应显示加载状态指示器
- **AND** 数据重新加载时间 SHOULD 小于 500ms
- **AND** 刷新完成后应平滑更新表格内容
