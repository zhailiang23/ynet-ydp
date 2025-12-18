import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CommunityGridApi } from '#/api/grid/community-grid';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'gridCode',
      label: '网格编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格编号',
      },
    },
    {
      fieldName: 'gridName',
      label: '网格名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格名称',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<CommunityGridApi.CommunityGrid>['columns'] {
  return [
    {
      field: 'gridCode',
      title: '网格编号',
      minWidth: 150,
    },
    {
      field: 'gridName',
      title: '网格名称',
      minWidth: 150,
    },
    {
      field: 'orgName',
      title: '归属机构',
      minWidth: 180,
    },
    {
      field: 'managerUserName',
      title: '责任人',
      minWidth: 120,
    },
    {
      field: 'teamCount',
      title: '团队人数',
      minWidth: 100,
    },
    {
      field: 'status',
      title: '网格状态',
      minWidth: 100,
      formatter: ({ cellValue }) => {
        const statusMap: Record<string, string> = {
          ACTIVE: '正常',
          INACTIVE: '无效',
        };
        return statusMap[cellValue as string] || cellValue;
      },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 160,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
