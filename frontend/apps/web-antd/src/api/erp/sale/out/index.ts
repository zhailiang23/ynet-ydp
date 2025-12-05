/**
 * ERP 销售出库 API
 *
 * 临时文件：用于满足构建需求
 * TODO: 实现完整的销售出库 API
 */

import type { PageResult } from '#/api/model/common';

/**
 * 销售出库 API 命名空间
 */
export namespace ErpSaleOutApi {
  /**
   * 销售出库单
   */
  export interface SaleOut {
    id: number;
    // TODO: 添加更多字段
  }

  /**
   * 分页查询参数
   */
  export interface PageParams {
    pageNo: number;
    pageSize: number;
    // TODO: 添加查询条件
  }
}

/**
 * 获取销售出库单分页列表
 */
export function getSaleOutPage(
  params: ErpSaleOutApi.PageParams,
): Promise<PageResult<ErpSaleOutApi.SaleOut>> {
  // TODO: 实现真实的 API 调用
  return Promise.resolve({
    list: [],
    total: 0,
  });
}
