# 个性化课程创建服务规格

## ADDED Requirements

### Requirement: 个性化课程创建 API

系统 SHALL 提供创建个性化课程的后端 API，支持虚拟客户创建、文件上传处理、剧本生成和课程创建的一体化流程。

#### Scenario: 创建新虚拟客户并生成个性化课程
- **GIVEN** 用户在页面选择创建新客户并填写了虚拟客户信息
- **AND** 用户上传了培训文件（Word、PDF 或 TXT）
- **AND** 用户填写了课程名称和选择了营销环节
- **WHEN** 用户调用创建个性化课程 API
- **THEN** 系统 SHALL 使用用户填写的虚拟客户信息创建一个新的虚拟客户记录
- **AND** 系统 SHALL 将上传的文件保存为培训文件记录，类型为培训手册
- **AND** 系统 SHALL 抽取文件文本内容并保存到培训文件记录的 content 字段
- **AND** 系统 SHALL 创建一条剧本记录，剧本名称为课程名称+"剧本"
- **AND** 剧本记录 SHALL 关联新创建的虚拟客户
- **AND** 剧本记录 SHALL 使用用户选择的营销环节
- **AND** 剧本记录 SHALL 设置复杂度为中级
- **AND** 剧本记录 SHALL 关联第二步创建的培训文件
- **AND** 关联案例和关联销售技巧 SHALL 为空
- **AND** 系统 SHALL 创建一个类型为个性化课程的课程记录
- **AND** 系统 SHALL 返回创建成功的课程记录信息

#### Scenario: 选择现有虚拟客户并生成个性化课程
- **GIVEN** 用户在页面选择了现有的虚拟客户
- **AND** 用户上传了培训文件（Word、PDF 或 TXT）
- **AND** 用户填写了课程名称和选择了营销环节
- **WHEN** 用户调用创建个性化课程 API
- **THEN** 系统 SHALL 将上传的文件保存为培训文件记录，类型为培训手册
- **AND** 系统 SHALL 抽取文件文本内容并保存到培训文件记录的 content 字段
- **AND** 系统 SHALL 创建一条剧本记录，剧本名称为课程名称+"剧本"
- **AND** 剧本记录 SHALL 关联用户选择的现有虚拟客户
- **AND** 剧本记录 SHALL 使用用户选择的营销环节
- **AND** 剧本记录 SHALL 设置复杂度为中级
- **AND** 剧本记录 SHALL 关联培训文件
- **AND** 关联案例和关联销售技巧 SHALL 为空
- **AND** 系统 SHALL 创建一个类型为个性化课程的课程记录
- **AND** 系统 SHALL 返回创建成功的课程记录信息

#### Scenario: 处理不支持的文件格式
- **GIVEN** 用户上传了非 Word、PDF、TXT 格式的文件
- **WHEN** 系统尝试处理文件
- **THEN** 系统 SHALL 返回错误信息，提示仅支持 Word、PDF、TXT 格式
- **AND** 系统 SHALL 不创建任何课程记录

#### Scenario: 文件文本抽取失败
- **GIVEN** 用户上传了支持的文件格式
- **AND** 系统在抽取文件文本内容时发生错误
- **WHEN** 系统检测到文本抽取失败
- **THEN** 系统 SHALL 仍然创建培训文件记录
- **AND** content 字段 SHALL 为空或包含错误信息
- **AND** 系统 SHALL 继续创建剧本和课程记录
- **AND** 系统 SHALL 在响应中提示文本抽取失败但课程创建成功

### Requirement: 虚拟客户创建服务

系统 SHALL 提供虚拟客户快速创建功能，支持基于前端传入的信息创建虚拟客户角色。

#### Scenario: 创建基础虚拟客户信息
- **GIVEN** 用户提供了虚拟客户的基本信息（姓名、性别、年龄、职业、行业、性格特征等）
- **WHEN** 调用创建虚拟客户服务
- **THEN** 系统 SHALL 创建新的 PracticeVirtualCustomerDO 记录
- **AND** 系统 SHALL 设置客户姓名、性别、年龄、职业、行业、性格特征等基本信息
- **AND** 系统 SHALL 设置其他必要字段的默认值
- **AND** 系统 SHALL 返回创建成功的虚拟客户 ID 和详细信息

#### Scenario: 虚拟客户信息验证
- **GIVEN** 用户提供的虚拟客户信息缺少必要字段
- **WHEN** 调用创建虚拟客户服务
- **THEN** 系统 SHALL 验证必要字段是否完整
- **AND** 系统 SHALL 返回详细的错误信息
- **AND** 系统 SHALL 不创建虚拟客户记录

### Requirement: 文件上传和文本抽取服务

系统 SHALL 提供文件上传和文本内容抽取功能，支持 Word、PDF、TXT 三种格式的培训材料处理。

#### Scenario: 上传并处理 Word 文档
- **GIVEN** 用户上传了 .doc 或 .docx 格式的 Word 文档
- **WHEN** 调用文件处理服务
- **THEN** 系统 SHALL 将文件保存到文件系统
- **AND** 系统 SHALL 创建 PracticeMaterialDO 记录
- **AND** 系统 SHALL 使用 Apache POI 抽取 Word 文档的文本内容
- **AND** 系统 SHALL 将抽取的文本内容保存到 content 字段
- **AND** 系统 SHALL 设置 material_type 为"培训手册"

#### Scenario: 上传并处理 PDF 文档
- **GIVEN** 用户上传了 .pdf 格式的 PDF 文档
- **WHEN** 调用文件处理服务
- **THEN** 系统 SHALL 将文件保存到文件系统
- **AND** 系统 SHALL 创建 PracticeMaterialDO 记录
- **AND** 系统 SHALL 使用 Apache PDFBox 抽取 PDF 文档的文本内容
- **AND** 系统 SHALL 将抽取的文本内容保存到 content 字段
- **AND** 系统 SHALL 设置 material_type 为"培训手册"

#### Scenario: 上传并处理文本文档
- **GIVEN** 用户上传了 .txt 格式的文本文档
- **WHEN** 调用文件处理服务
- **THEN** 系统 SHALL 将文件保存到文件系统
- **AND** 系统 SHALL 创建 PracticeMaterialDO 记录
- **AND** 系统 SHALL 直接读取文本文档的内容
- **AND** 系统 SHALL 将文本内容保存到 content 字段
- **AND** 系统 SHALL 设置 material_type 为"培训手册"

### Requirement: 剧本创建服务

系统 SHALL 提供个性化剧本创建功能，基于课程信息和培训材料生成陪练剧本。

#### Scenario: 基于课程信息创建剧本
- **GIVEN** 提供了课程名称、虚拟客户 ID、营销环节、培训文件 ID
- **WHEN** 调用剧本创建服务
- **THEN** 系统 SHALL 创建新的 PracticeScriptDO 记录
- **AND** 系统 SHALL 设置 script_name 为"课程名称+剧本"
- **AND** 系统 SHALL 关联指定的虚拟客户 ID
- **AND** 系统 SHALL 设置 marketing_link 为用户选择的营销环节
- **AND** 系统 SHALL 设置 complexity_level 为"中级"
- **AND** 系统 SHALL 关联指定的培训文件 ID
- **AND** 系统 SHALL 设置 related_cases 和 related_sales_skills 为空
- **AND** 系统 SHALL 自动生成剧本版本号
- **AND** 系统 SHALL 返回创建成功的剧本 ID 和详细信息

### Requirement: 课程创建服务

系统 SHALL 提供个性化课程创建功能，基于剧本记录创建完整的课程信息。

#### Scenario: 创建个性化课程记录
- **GIVEN** 提供了课程名称、课程描述、剧本 ID
- **WHEN** 调用课程创建服务
- **THEN** 系统 SHALL 创建新的 PracticeCourseDO 记录
- **AND** 系统 SHALL 设置 course_name 为用户提供的课程名称
- **AND** 系统 SHALL 设置 course_description 为用户提供的课程描述
- **AND** 系统 SHALL 关联指定的剧本 ID
- **AND** 系统 SHALL 设置 course_type 为"个性化课程"
- **AND** 系统 SHALL 设置 complexity_level 为"中级"
- **AND** 系统 SHALL 设置课程状态为"未开始"
- **AND** 系统 SHALL 返回创建成功的课程记录信息