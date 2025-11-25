#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
批量注释DocAlert组件的Python脚本（改进版）
该脚本会自动搜索项目中所有前端文件，找到包含DocAlert组件的文件并进行注释处理
支持的文件类型：.vue, .tsx, .ts, .jsx, .js
特性：
1. 支持大小写不敏感的DocAlert检测
2. 避免重复注释已经注释过的DocAlert
3. 自动文件发现
4. 创建备份文件
5. 智能忽略常见的无关目录（node_modules等）
6. 性能优化和搜索统计
"""

import os
import re
import sys
from pathlib import Path
import glob
import time

# 配置：需要忽略的目录列表
# 用户可以根据项目需要修改这个列表
DEFAULT_IGNORE_DIRS = {
    # 依赖包目录
    'node_modules',        # npm/yarn 依赖包
    'vendor',             # Composer/Go 依赖包
    
    # 版本控制目录
    '.git',               # Git 版本控制
    '.svn',               # SVN 版本控制
    '.hg',                # Mercurial 版本控制
    
    # 构建输出目录
    'dist',               # 构建输出目录
    'build',              # 构建输出目录
    'out',                # 构建输出目录
    'target',             # Maven/Gradle 构建目录
    'lib',                # 库文件目录
    
    # 缓存和临时目录
    '.cache',             # 缓存文件
    '.next',              # Next.js 构建缓存
    '.nuxt',              # Nuxt.js 构建缓存
    'tmp',                # 临时文件
    'temp',               # 临时文件
    
    # 测试和覆盖率
    'coverage',           # 代码覆盖率报告
    '.nyc_output',        # NYC 覆盖率工具
    '__pycache__',        # Python 缓存
    '.pytest_cache',      # Pytest 缓存
    
    # IDE 配置目录
    '.idea',              # IntelliJ IDEA 配置
    '.vscode',            # VS Code 配置
    '.vs',                # Visual Studio 配置
    
    # 日志和运行时目录
    'logs',               # 日志文件
    'log',                # 日志文件
    
    # 可选：静态资源目录（根据项目需要决定是否忽略）
    # 'public',           # 静态资源目录
    # 'assets',           # 资源文件目录
    # 'static',           # 静态文件目录
}

# 配置：支持的前端文件扩展名
FRONTEND_EXTENSIONS = ['*.vue', '*.tsx', '*.ts', '*.jsx', '*.js']

def find_frontend_files_with_docalert(base_path, ignore_dirs=None):
    """
    在指定目录下搜索所有包含DocAlert的前端文件
    支持的文件类型：.vue, .tsx, .ts, .jsx, .js
    返回包含DocAlert的文件列表
    
    Args:
        base_path: 搜索的基础目录路径
        ignore_dirs: 需要忽略的目录集合，默认使用DEFAULT_IGNORE_DIRS
    
    Returns:
        list: 包含DocAlert的文件路径列表
    """
    base_path = Path(base_path)
    ignore_dirs = ignore_dirs or DEFAULT_IGNORE_DIRS
    docalert_files = []
    total_files_found = 0
    files_ignored = 0
    
    print(f"正在搜索 {base_path} 目录下的前端文件...")
    print(f"忽略目录 ({len(ignore_dirs)} 个): {', '.join(sorted(ignore_dirs))}")
    
    # 搜索所有前端文件（排除忽略目录）
    all_files = []
    for extension in FRONTEND_EXTENSIONS:
        # 使用递归搜索
        pattern = str(base_path / '**' / extension)
        files = glob.glob(pattern, recursive=True)
        total_files_found += len(files)
        
        # 过滤忽略目录中的文件
        for file_path in files:
            # 检查文件路径是否包含忽略目录
            path_parts = Path(file_path).parts
            should_ignore = any(part in ignore_dirs for part in path_parts)
            
            if should_ignore:
                files_ignored += 1
            else:
                all_files.append(file_path)
    
    print(f"搜索统计: 总共找到 {total_files_found} 个文件，忽略 {files_ignored} 个，实际检查 {len(all_files)} 个")
    print(f"正在检查文件内容是否包含DocAlert...")
    
    # 检查每个文件是否包含DocAlert
    for file_path in all_files:
        try:
            with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                content = f.read()
                # 不区分大小写搜索DocAlert（包括已注释和未注释的）
                if re.search(r'<\s*docalert', content, re.IGNORECASE):
                    # 转换为相对路径
                    relative_path = Path(file_path).relative_to(base_path)
                    docalert_files.append(str(relative_path))
                    print(f"  ✓ 找到DocAlert: {relative_path}")
        except Exception as e:
            print(f"  ✗ 读取文件错误 {file_path}: {e}")
    
    print(f"\n搜索完成！共找到 {len(docalert_files)} 个包含DocAlert的文件")
    return docalert_files

def comment_docalert_in_file(file_path):
    """
    在指定文件中注释DocAlert组件（支持大小写不敏感，避免重复注释）
    """
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        modifications_made = 0
        
        # 先移除已有的不完整注释（防止之前的脚本留下的问题）
        # 这个步骤会清理可能的部分注释
        content = re.sub(r'<!--\s*(<[Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt][^>]*/>)\s*-->', r'\1', content)
        content = re.sub(r'<!--\s*(<[Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt][^>]*>)', r'\1', content)
        content = re.sub(r'(</[Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt]>)\s*-->', r'\1', content)
        
        # 方法1：处理单行自闭合的DocAlert标签（不区分大小写）
        # 匹配 <DocAlert ... /> 但排除已经在 <!-- ... --> 中的
        def process_single_line(match):
            nonlocal modifications_made
            modifications_made += 1
            full_match = match.group(0)
            indent = match.group(1) if match.group(1) else ''
            tag_content = match.group(2)
            return f'{indent}<!-- {tag_content} -->'
        
        # 使用更精确的正则表达式，避免匹配已注释的内容
        single_line_pattern = r'^(\s*)((?:<[Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt][^>]*/>))(?!\s*-->)'
        content = re.sub(single_line_pattern, process_single_line, content, flags=re.MULTILINE)
        
        # 方法2：处理多行DocAlert标签
        # 首先找到所有未注释的多行DocAlert块
        multiline_pattern = r'^(\s*)(<[Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt](?:[^>]|\n)*?</[Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt]>)(?!\s*-->)'
        
        def process_multiline(match):
            nonlocal modifications_made
            modifications_made += 1
            indent = match.group(1) if match.group(1) else ''
            tag_content = match.group(2)
            
            # 将多行内容转换为注释
            lines = tag_content.split('\n')
            if len(lines) == 1:
                # 单行情况
                return f'{indent}<!-- {tag_content} -->'
            else:
                # 多行情况
                result_lines = [f'{indent}<!--']
                for line in lines:
                    result_lines.append(line)
                result_lines.append(f'{indent}-->')
                return '\n'.join(result_lines)
        
        content = re.sub(multiline_pattern, process_multiline, content, flags=re.MULTILINE | re.DOTALL)
        
        # 方法3：处理不完整的多行DocAlert（开始标签到结束标签分开的）
        # 这种情况比较复杂，使用状态机方式处理
        def process_incomplete_multiline():
            nonlocal content, modifications_made
            
            lines = content.split('\n')
            result_lines = []
            i = 0
            
            while i < len(lines):
                line = lines[i]
                
                # 检查是否是未注释的DocAlert开始标签
                start_match = re.search(r'^(\s*)<([Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt])([^/>]*?)>(?!\s*<!--)', line)
                if start_match:
                    # 找到开始标签，查找对应的结束标签
                    tag_name = start_match.group(2).lower()
                    indent = start_match.group(1)
                    start_line_index = i
                    
                    # 查找结束标签
                    end_line_index = None
                    for j in range(i + 1, len(lines)):
                        if re.search(rf'</[Dd][Oo][Cc][Aa][Ll][Ee][Rr][Tt]>', lines[j], re.IGNORECASE):
                            end_line_index = j
                            break
                    
                    if end_line_index:
                        # 找到完整的DocAlert块，添加注释
                        modifications_made += 1
                        result_lines.append(f'{indent}<!--')
                        for k in range(start_line_index, end_line_index + 1):
                            result_lines.append(lines[k])
                        result_lines.append(f'{indent}-->')
                        i = end_line_index + 1
                    else:
                        # 没找到结束标签，保持原样
                        result_lines.append(line)
                        i += 1
                else:
                    result_lines.append(line)
                    i += 1
            
            content = '\n'.join(result_lines)
        
        process_incomplete_multiline()
        
        # 检查是否有修改
        if content != original_content:
            # 创建备份
            backup_path = f"{file_path}.backup"
            # 只有当备份文件不存在时才创建，避免覆盖之前的备份
            if not Path(backup_path).exists():
                with open(backup_path, 'w', encoding='utf-8') as f:
                    f.write(original_content)
            
            # 写入修改后的内容
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            
            print(f"      → 发现并处理了 {modifications_made} 个未注释的DocAlert")
            return True
        else:
            print(f"      → 所有DocAlert已经是注释状态")
            return False
        
    except Exception as e:
        print(f"处理文件 {file_path} 时出错: {e}")
        return False

def main():
    """
    主函数 - 使用自动文件发现功能
    """
    import argparse
    
    # 命令行参数解析
    parser = argparse.ArgumentParser(
        description='批量注释DocAlert组件的Python脚本（改进版）',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""示例用法:
  python %(prog)s                                    # 使用默认路径
  python %(prog)s --path /path/to/project            # 指定项目路径
  python %(prog)s --ignore-dirs docs examples       # 添加额外忽略目录
  python %(prog)s --dry-run                         # 仅查看会处理哪些文件，不实际修改
"""
    )
    
    parser.add_argument(
        '--path', 
        default='/Users/zhailiang/Documents/code/ynet-ydp/frontend/apps/web-antd',
        help='指定项目路径 (默认: %(default)s)'
    )
    
    parser.add_argument(
        '--ignore-dirs',
        nargs='*',
        default=[],
        help='添加额外需要忽略的目录名称'
    )
    
    parser.add_argument(
        '--dry-run',
        action='store_true',
        help='仅显示会处理的文件，不实际修改'
    )
    
    parser.add_argument(
        '--list-ignore-dirs',
        action='store_true',
        help='显示默认忽略的目录列表并退出'
    )
    
    args = parser.parse_args()
    
    # 显示默认忽略目录列表
    if args.list_ignore_dirs:
        print("默认忽略目录列表:")
        for dir_name in sorted(DEFAULT_IGNORE_DIRS):
            print(f"  - {dir_name}")
        return
    
    base_path = Path(args.path)
    
    # 合并默认和用户指定的忽略目录
    ignore_dirs = DEFAULT_IGNORE_DIRS.copy()
    if args.ignore_dirs:
        ignore_dirs.update(args.ignore_dirs)
    
    print("开始批量注释DocAlert组件（改进版 - 避免重复注释）...")
    if args.dry_run:
        print("[模拟模式] 仅显示会处理的文件，不会实际修改")
    print(f"项目路径: {base_path}")
    print(f"额外忽略目录: {args.ignore_dirs if args.ignore_dirs else '无'}")
    print()
    
    # 检查路径是否存在
    if not base_path.exists():
        print(f"错误: 指定的路径不存在: {base_path}")
        return 1
    
    # 记录搜索开始时间
    start_time = time.time()
    
    # 自动搜索包含DocAlert的文件
    files_to_process = find_frontend_files_with_docalert(base_path, ignore_dirs)
    
    search_time = time.time() - start_time
    print(f"搜索耗时: {search_time:.2f} 秒")
    
    if not files_to_process:
        print("没有找到包含DocAlert的文件，程序结束。")
        return 0
    
    total_files = len(files_to_process)
    
    if args.dry_run:
        print(f"\n[模拟模式] 找到 {total_files} 个包含DocAlert的文件:")
        for i, file_path in enumerate(files_to_process, 1):
            print(f"  {i:3d}. {file_path}")
        print(f"\n如果执行实际操作，将会处理以上 {total_files} 个文件。")
        print("请去掉 --dry-run 参数来执行实际操作。")
        return 0
    
    processed_files = 0
    success_files = 0
    modified_files = 0
    
    print(f"\n开始处理 {total_files} 个包含DocAlert的文件...")
    print()
    
    for file_rel_path in files_to_process:
        processed_files += 1
        file_path = base_path / file_rel_path
        
        print(f"正在处理 ({processed_files}/{total_files}): {file_rel_path}")
        
        if file_path.exists():
            if comment_docalert_in_file(file_path):
                success_files += 1
                modified_files += 1
            else:
                success_files += 1
        else:
            print(f"  ✗ 文件不存在: {file_rel_path}")
    
    print()
    print("批量处理完成！")
    print(f"总文件数: {total_files}")
    print(f"成功处理: {success_files}")
    print(f"实际修改: {modified_files}")
    print(f"失败文件: {total_files - success_files}")
    print()
    
    if modified_files > 0:
        print("备份文件已创建（.backup后缀），如需恢复可以使用备份文件。")
        print()
    
    print("建议手动检查几个文件确认修改是否正确。")

if __name__ == "__main__":
    main()
