import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { ZerodaiGridApi } from '#/api/grid/zerodai-grid';

import { DICT_TYPE } from '@vben/constants';
import { handleTree } from '@vben/utils';

import { getSimpleDeptList } from '#/api/system/dept';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'gridName',
      label: '网格名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格名称',
      },
    },
    {
      fieldName: 'orgId',
      label: '所属组织',
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
        placeholder: '请选择所属组织',
        allowClear: true,
      },
    },
    {
      fieldName: 'resourceTags',
      label: '资源标签',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入资源标签',
      },
    },
    {
      fieldName: 'creator',
      label: '创建人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入创建人',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<ZerodaiGridApi.ZerodaiGrid>['columns'] {
  return [
    {
      field: 'gridCode',
      title: '网格编号',
      minWidth: 180,
    },
    {
      field: 'gridName',
      title: '网格名称',
      minWidth: 150,
    },
    {
      field: 'orgName',
      title: '所属组织',
      minWidth: 180,
    },
    {
      field: 'resourceTags',
      title: '资源标签',
      minWidth: 150,
      cellRender: {
        name: 'CellDictTags',
        props: { type: 'grid_resource_tag' },
      },
    },
    {
      field: 'orgType',
      title: '组织类型',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'grid_org_type' },
      },
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
