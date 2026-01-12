import { requestClient } from '#/api/request';

export namespace AicrmCohortApi {
  /** 客群信息 */
  export interface Cohort {
    labelId: string; // 客群ID（字符串格式）
    labelName: string; // 客群名称
    cohortCode?: string; // 客群编码
    labelExplain?: string; // 客群描述
    updateCycle?: string; // 更新周期
    state?: string; // 状态
    coverNum?: number; // 覆盖人数
  }

  /** 客群列表请求参数 */
  export interface CohortListReqVO {
    type: number;
    pageNo: number;
    pageSize: number;
  }
}

/**
 * 查询客群列表
 * 通过后端代理接口调用客群中心服务
 */
export async function getCohortList(
  params: AicrmCohortApi.CohortListReqVO = { type: 0, pageNo: 1, pageSize: 100 },
) {
  try {
    // 调用后端代理接口
    const response = await requestClient.post<AicrmCohortApi.Cohort[]>(
      '/aicrm/cohort/list',
      params,
    );

    // 返回客群列表
    return response || [];
  } catch (error) {
    console.error('获取客群列表失败:', error);
    return [];
  }
}

/**
 * 获取简化的客群列表（用于下拉选择）
 */
export async function getSimpleCohortList() {
  const cohorts = await getCohortList({ type: 0, pageNo: 1, pageSize: 100 });
  return cohorts.map((cohort) => ({
    label: cohort.labelName,
    value: cohort.labelId,
  }));
}
