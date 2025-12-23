import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { LobbyGridApi } from '#/api/grid/lobby-grid';

import { handleTree } from '@vben/utils';
import { getSimpleDeptList } from '#/api/system/dept';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'orgId',
      label: '所属机构',
      component: 'ApiTreeSelect',
      componentProps: {
        api: async () => {
          const data = await getSimpleDeptList();
          return handleTree(data);
        },
        labelField: 'name',
        valueField: 'id',
        childrenField: 'children',
        treeDefaultExpandAll: true,
        placeholder: '请选择所属机构',
        allowClear: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<LobbyGridApi.LobbyGrid>['columns'] {
  return [
    {
      field: 'gridCode',
      title: '网格编号',
      minWidth: 180,
    },
    {
      field: 'orgName',
      title: '所属机构',
      minWidth: 150,
    },
    {
      field: 'secondLevelName',
      title: '二级行',
      minWidth: 150,
    },
    {
      field: 'firstLevelName',
      title: '一级行',
      minWidth: 150,
    },
    {
      field: 'managementLevel',
      title: '管理层级',
      minWidth: 100,
    },
    {
      field: 'region',
      title: '所属区域',
      minWidth: 100,
    },
    {
      field: 'district',
      title: '行政片区',
      minWidth: 100,
    },
    {
      field: 'outletType',
      title: '网点类型',
      minWidth: 100,
    },
    {
      field: 'residentCount',
      title: '常住居民数',
      minWidth: 120,
    },
    {
      field: 'merchantCount',
      title: '周围商户数',
      minWidth: 120,
    },
    {
      field: 'creatorName',
      title: '创建人',
      minWidth: 100,
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
