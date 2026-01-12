import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace TaskScoringFactorApi {
  /**
   * 任务评分条件
   */
  export interface TaskScoringCondition {
    id?: number; // 主键ID
    factorId?: number; // 所属评分因子ID
    dataSource: string; // 数据来源
    fieldName: string; // 字段/属性名称
    operator: string; // 操作符
    fieldValue: string; // 比较值
    sort: number; // 排序
  }

  /**
   * 任务评分因子
   */
  export interface TaskScoringFactor {
    id: number; // 主键ID
    factorName: string; // 因子名称（中文）
    factorNameEn: string; // 因子英文名
    icon?: string; // Material Symbols图标名
    weight: number; // 权重(%)
    description?: string; // 因子说明
    enabled: boolean; // 是否启用
    logicType: 'AND' | 'OR'; // 条件逻辑关系: AND/OR
    impactType: 'direct' | 'weight' | 'score'; // 影响方式
    scoreAdjustment: number; // 增加/减少分值 (-100到100)
    sort: number; // 排序
    conditions?: TaskScoringCondition[]; // 条件列表
    createTime?: string; // 创建时间
  }

  /**
   * 评分因子分页查询参数
   */
  export interface TaskScoringFactorPageReqVO extends PageParam {
    factorName?: string; // 因子名称（模糊匹配）
    enabled?: boolean; // 是否启用
  }

  /**
   * 评分因子列表查询参数
   */
  export interface TaskScoringFactorListReqVO {
    factorName?: string; // 因子名称（模糊匹配）
    enabled?: boolean; // 是否启用
  }

  /**
   * 评分因子创建/更新请求
   */
  export interface TaskScoringFactorSaveReqVO {
    id?: number; // 主键ID（更新时必填）
    factorName: string; // 因子名称（中文）
    factorNameEn: string; // 因子英文名
    icon?: string; // Material Symbols图标名
    weight: number; // 权重(%)
    description?: string; // 因子说明
    enabled: boolean; // 是否启用
    logicType: 'AND' | 'OR'; // 条件逻辑关系: AND/OR
    impactType: 'direct' | 'weight' | 'score'; // 影响方式
    scoreAdjustment: number; // 增加/减少分值 (-100到100)
    sort: number; // 排序
    conditions?: TaskScoringCondition[]; // 条件列表
  }

  /**
   * 批量更新权重请求
   */
  export interface BatchUpdateWeightReqVO {
    factors: Array<{
      id: number; // 因子ID
      enabled: boolean; // 是否启用
      weight: number; // 权重(%)
    }>;
  }

  /**
   * 创建评分因子
   */
  export function createScoringFactor(data: TaskScoringFactorSaveReqVO) {
    return requestClient.post<number>('/task/scoring-factor/create', data);
  }

  /**
   * 更新评分因子
   */
  export function updateScoringFactor(data: TaskScoringFactorSaveReqVO) {
    return requestClient.put<boolean>('/task/scoring-factor/update', data);
  }

  /**
   * 删除评分因子
   */
  export function deleteScoringFactor(id: number) {
    return requestClient.delete<boolean>(`/task/scoring-factor/delete?id=${id}`);
  }

  /**
   * 获取评分因子详情
   */
  export function getScoringFactor(id: number) {
    return requestClient.get<TaskScoringFactor>(
      `/task/scoring-factor/get?id=${id}`,
    );
  }

  /**
   * 获取评分因子分页
   */
  export function getScoringFactorPage(params: TaskScoringFactorPageReqVO) {
    return requestClient.get<PageResult<TaskScoringFactor>>(
      '/task/scoring-factor/page',
      { params },
    );
  }

  /**
   * 获取评分因子列表
   */
  export function getScoringFactorList(params?: TaskScoringFactorListReqVO) {
    return requestClient.get<TaskScoringFactor[]>(
      '/task/scoring-factor/list',
      { params },
    );
  }

  /**
   * 批量更新因子权重
   */
  export function batchUpdateWeight(data: BatchUpdateWeightReqVO) {
    return requestClient.put<boolean>(
      '/task/scoring-factor/batch-update-weight',
      data,
    );
  }
}

// 为了方便导入，将函数从命名空间中导出
export const {
  createScoringFactor,
  updateScoringFactor,
  deleteScoringFactor,
  getScoringFactor,
  getScoringFactorPage,
  getScoringFactorList,
  batchUpdateWeight,
} = TaskScoringFactorApi;
