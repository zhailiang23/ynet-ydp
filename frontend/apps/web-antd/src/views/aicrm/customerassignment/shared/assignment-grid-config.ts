import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';

import { getCustomerAssignmentPage } from '#/api/aicrm/customerassignment';

/**
 * 格式化布尔值
 */
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

/**
 * 创建客户归属关系表格配置
 * @param customerId 客户ID
 * @param height 表格高度，默认500
 */
export function createAssignmentGridOptions(
  customerId: number,
  height: number = 500,
): VxeTableGridOptions<AicrmCustomerAssignmentApi.CustomerAssignment> {
  return {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'assignmentType',
        title: '归属类型',
        minWidth: 100,
        cellRender: {
          name: 'CellDict',
          props: { type: 'aicrm_assignment_type' },
        },
      },
      {
        field: 'deptName',
        title: '归属部门',
        minWidth: 150,
      },
      {
        field: 'userName',
        title: '客户经理',
        minWidth: 120,
      },
      {
        field: 'assignDate',
        title: '分配日期',
        minWidth: 120,
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerAssignmentPage({
            customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          });
        },
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerAssignmentApi.CustomerAssignment>;
}
