"""
剧本生成服务
根据输入参数查询数据库,组装提示词,调用大模型生成剧本
"""
import os
import json
import pymysql
from pathlib import Path
from typing import Optional, Dict, Any
from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from langchain_openai import ChatOpenAI


# ==================== 配置 ====================

# 加载 .env 文件
env_path = Path(__file__).parent / '.env'
if env_path.exists():
    load_dotenv(env_path)
    print(f"✓ 已加载配置文件: {env_path}")

# 模型配置
MODEL_PROVIDER = os.environ.get("MODEL_PROVIDER", "deepseek")
MODEL_NAME = os.environ.get("MODEL_NAME", "deepseek-ai/DeepSeek-V3.1-Terminus")

# 检查对应的 API Key
if MODEL_PROVIDER == "deepseek":
    api_key = os.environ.get("DEEPSEEK_API_KEY")
    api_base = os.environ.get("DEEPSEEK_API_BASE", "https://api.siliconflow.cn/v1")
    if not api_key:
        raise RuntimeError("错误: 使用 DeepSeek 时请设置环境变量 DEEPSEEK_API_KEY")
else:
    api_key = os.environ.get("ANTHROPIC_API_KEY")
    api_base = None
    if not api_key:
        raise RuntimeError("错误: 使用 Anthropic 时请设置环境变量 ANTHROPIC_API_KEY")

print(f"✓ 使用模型提供商: {MODEL_PROVIDER}")
print(f"✓ 使用模型: {MODEL_NAME}")

# 数据库配置
DB_CONFIG = {
    'host': os.environ.get("DB_HOST", "127.0.0.1"),
    'port': int(os.environ.get("DB_PORT", 3306)),
    'user': os.environ.get("DB_USER", "root"),
    'password': os.environ.get("DB_PASSWORD", "123456"),
    'database': os.environ.get("DB_NAME", "ruoyi-vue-pro"),
    'charset': 'utf8mb4'
}


# ==================== 数据模型 ====================

class ScriptGenerateRequest(BaseModel):
    """剧本生成请求参数"""
    script_id: int = Field(..., description="剧本ID(必填,通过ID查询所有其他信息)")


class ScriptGenerateResponse(BaseModel):
    """剧本生成响应"""
    success: bool
    message: str
    script_content: Optional[Dict[str, Any]] = None


# ==================== 数据库操作 ====================

def get_db_connection():
    """获取数据库连接"""
    return pymysql.connect(**DB_CONFIG)


def query_prompt_template(code: str) -> Optional[Dict[str, Any]]:
    """查询提示词模板（提示词模板是全局共享的，使用 tenant_id = 0）"""
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT code, name, content, variables, description
                FROM crm_practice_prompt_template
                WHERE code = %s AND status = 1 AND deleted = 0 AND tenant_id = 0
            """
            cursor.execute(sql, (code,))
            return cursor.fetchone()
    finally:
        conn.close()


def query_case_content(case_id: int) -> str:
    """查询案例内容"""
    if not case_id:
        return ""

    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT title, content, tags
                FROM crm_practice_case
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (case_id,))
            result = cursor.fetchone()
            if result:
                return f"案例标题: {result['title']}\n案例标签: {result.get('tags', '')}\n案例内容: {result.get('content', '')}"
            return ""
    finally:
        conn.close()


def query_material_content(material_id: int) -> str:
    """查询材料内容"""
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT name, content, file_type
                FROM crm_practice_material
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (material_id,))
            result = cursor.fetchone()
            if result:
                return f"材料名称: {result['name']}\n文件类型: {result.get('file_type', '')}\n材料内容: {result.get('content', '')}"
            return ""
    finally:
        conn.close()


def query_skill_content(skill_id: int) -> str:
    """查询技能内容"""
    if not skill_id:
        return ""

    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT name, category, script_template
                FROM crm_practice_skill
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (skill_id,))
            result = cursor.fetchone()
            if result:
                return f"技能名称: {result['name']}\n技能类别: {result.get('category', '')}\n技能模板: {result.get('script_template', '')}"
            return ""
    finally:
        conn.close()


def query_script_info(script_id: int) -> Dict[str, Any]:
    """通过剧本ID查询剧本完整信息"""
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT id, name, description, difficulty_level, marketing_step,
                       case_id, skill_id, material_ids
                FROM crm_practice_script
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (script_id,))
            result = cursor.fetchone()
            if not result:
                raise HTTPException(status_code=404, detail=f"剧本ID {script_id} 不存在")
            return result
    finally:
        conn.close()


# ==================== 提示词组装 ====================

def build_script_prompt(request: ScriptGenerateRequest) -> str:
    """组装剧本生成提示词"""
    # 1. 通过剧本ID查询剧本信息
    script_info = query_script_info(request.script_id)

    # 2. 查询提示词模板
    template = query_prompt_template("SCRIPT_CREATE_SYSTEM_PROMPT")
    if not template:
        raise HTTPException(status_code=500, detail="未找到剧本生成提示词模板")

    prompt_content = template['content']

    # 3. 查询相关数据
    case_id = script_info.get('case_id')
    skill_id = script_info.get('skill_id')
    material_ids = script_info.get('material_ids')

    case_content = query_case_content(case_id) if case_id else ""
    skill_content = query_skill_content(skill_id) if skill_id else ""

    # 获取第一个材料ID
    material_content = ""
    if material_ids:
        first_material_id = int(material_ids.split(',')[0].strip())
        material_content = query_material_content(first_material_id)

    # 4. 替换变量
    variables = {
        'case_content': case_content if case_content else "无关联案例",
        'material_content': material_content if material_content else "无关联材料",
        'skill_content': skill_content if skill_content else "无关联技能",
        'marketing_step': script_info.get('marketing_step', ''),
        'difficulty_level': script_info.get('difficulty_level', ''),
        'script_description': script_info.get('description', '根据提供的信息生成合适的剧本')
    }

    # 替换提示词中的变量
    for key, value in variables.items():
        prompt_content = prompt_content.replace(f'{{{{{key}}}}}', str(value))

    return prompt_content


# ==================== LLM 调用 ====================

def create_llm():
    """创建 LLM 实例"""
    if MODEL_PROVIDER == "deepseek":
        return ChatOpenAI(
            model=MODEL_NAME,
            api_key=api_key,
            base_url=api_base,
            temperature=0.7
        )
    else:
        # Anthropic Claude
        from langchain_anthropic import ChatAnthropic
        return ChatAnthropic(
            model=MODEL_NAME,
            api_key=api_key,
            temperature=0.7
        )


def generate_script(prompt: str) -> Dict[str, Any]:
    """调用大模型生成剧本"""
    llm = create_llm()

    # 调用 LLM 生成剧本
    response = llm.invoke(prompt)
    content = response.content

    # 尝试解析 JSON
    try:
        # 移除 markdown 代码块标记
        if content.startswith("```json"):
            content = content[7:]
        if content.startswith("```"):
            content = content[3:]
        if content.endswith("```"):
            content = content[:-3]
        content = content.strip()

        # 解析 JSON
        script_data = json.loads(content)
        return script_data
    except json.JSONDecodeError as e:
        print(f"JSON 解析失败: {e}")
        print(f"原始内容: {content[:500]}...")
        # 如果解析失败,返回原始文本
        return {
            "raw_content": content,
            "parse_error": str(e)
        }


# ==================== FastAPI 应用 ====================

app = FastAPI(title="剧本生成服务", version="1.0.0")

# 配置 CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/health")
async def health_check():
    """健康检查"""
    return {"status": "ok", "service": "script_generator"}


@app.post("/generate-script", response_model=ScriptGenerateResponse)
async def generate_script_endpoint(request: ScriptGenerateRequest):
    """
    生成剧本

    参数:
    - script_id: 剧本ID(必填,通过ID查询所有其他信息)

    返回:
    - success: 是否成功
    - message: 提示信息
    - script_content: 剧本内容(JSON格式)
    """
    try:
        # 1. 组装提示词
        prompt = build_script_prompt(request)
        print(f"\n=== 生成的提示词 ===")
        print(prompt[:500] + "...")

        # 2. 调用大模型生成剧本
        print(f"\n=== 调用大模型生成剧本 ===")
        script_content = generate_script(prompt)

        # 3. 返回结果
        return ScriptGenerateResponse(
            success=True,
            message="剧本生成成功",
            script_content=script_content
        )

    except HTTPException:
        raise
    except Exception as e:
        print(f"生成剧本失败: {e}")
        import traceback
        traceback.print_exc()
        raise HTTPException(status_code=500, detail=f"生成剧本失败: {str(e)}")


# ==================== 启动服务 ====================

if __name__ == "__main__":
    import uvicorn

    print("\n" + "="*60)
    print("剧本生成服务")
    print("="*60)
    print(f"模型提供商: {MODEL_PROVIDER}")
    print(f"模型名称: {MODEL_NAME}")
    print(f"数据库: {DB_CONFIG['host']}:{DB_CONFIG['port']}/{DB_CONFIG['database']}")
    print("="*60 + "\n")

    uvicorn.run(app, host="0.0.0.0", port=8001)
