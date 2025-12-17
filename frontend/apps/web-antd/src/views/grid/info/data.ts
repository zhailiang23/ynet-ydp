import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridInfoApi } from '#/api/grid/info';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 新增/修改的表单 */
export function useFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'id',
      component: 'Input',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    {
      fieldName: 'gridCode',
      label: '网格编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格编号',
      },
    },
    {
      fieldName: 'gridName',
      label: '网格名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格名称',
      },
    },
    {
      fieldName: 'gridType',
      label: '网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI',
      },
    },
    {
      fieldName: 'orgId',
      label: '所属机构ID (关联 system_dept)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所属机构ID (关联 system_dept)',
      },
    },
    {
      fieldName: 'parentId',
      label: '父网格ID (零贷层级结构使用)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入父网格ID (零贷层级结构使用)',
      },
    },
    {
      fieldName: 'centerPoint',
      label: '网格中心坐标 (经纬度)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格中心坐标 (经纬度)',
      },
    },
    {
      fieldName: 'boundaryGeometry',
      label: '网格边界 (POLYGON 或 CIRCLE)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格边界 (POLYGON 或 CIRCLE)',
      },
    },
    {
      fieldName: 'radiusMeters',
      label: '网格半径(米) - 惠农固定3000, 厅堂默认1000',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格半径(米) - 惠农固定3000, 厅堂默认1000',
      },
    },
    {
      fieldName: 'residentCount',
      label: '网格常住居民数 (厅堂使用)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格常住居民数 (厅堂使用)',
      },
    },
    {
      fieldName: 'merchantCount',
      label: '网格周围商户数 (厅堂使用)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格周围商户数 (厅堂使用)',
      },
    },
    {
      fieldName: 'status',
      label: '网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入创建人ID',
      },
    },
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入更新人ID',
      },
    },
  ];
}

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
    {
      fieldName: 'gridType',
      label: '网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI',
      },
    },
    {
      fieldName: 'orgId',
      label: '所属机构ID (关联 system_dept)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所属机构ID (关联 system_dept)',
      },
    },
    {
      fieldName: 'parentId',
      label: '父网格ID (零贷层级结构使用)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入父网格ID (零贷层级结构使用)',
      },
    },
    {
      fieldName: 'centerPoint',
      label: '网格中心坐标 (经纬度)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格中心坐标 (经纬度)',
      },
    },
    {
      fieldName: 'boundaryGeometry',
      label: '网格边界 (POLYGON 或 CIRCLE)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格边界 (POLYGON 或 CIRCLE)',
      },
    },
    {
      fieldName: 'radiusMeters',
      label: '网格半径(米) - 惠农固定3000, 厅堂默认1000',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格半径(米) - 惠农固定3000, 厅堂默认1000',
      },
    },
    {
      fieldName: 'residentCount',
      label: '网格常住居民数 (厅堂使用)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格常住居民数 (厅堂使用)',
      },
    },
    {
      fieldName: 'merchantCount',
      label: '网格周围商户数 (厅堂使用)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格周围商户数 (厅堂使用)',
      },
    },
    {
      fieldName: 'status',
      label: '网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入创建人ID',
      },
    },
    {
      fieldName: 'createTime',
      label: '创建时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入更新人ID',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridInfoApi.Info>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '网格ID',
      minWidth: 120,
    },
    {
      field: 'gridCode',
      title: '网格编号',
      minWidth: 120,
    },
    {
      field: 'gridName',
      title: '网格名称',
      minWidth: 120,
    },
    {
      field: 'gridType',
      title: '网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI',
      minWidth: 120,
    },
    {
      field: 'orgId',
      title: '所属机构ID (关联 system_dept)',
      minWidth: 120,
    },
    {
      field: 'parentId',
      title: '父网格ID (零贷层级结构使用)',
      minWidth: 120,
    },
    {
      field: 'centerPoint',
      title: '网格中心坐标 (经纬度)',
      minWidth: 120,
    },
    {
      field: 'boundaryGeometry',
      title: '网格边界 (POLYGON 或 CIRCLE)',
      minWidth: 120,
    },
    {
      field: 'radiusMeters',
      title: '网格半径(米) - 惠农固定3000, 厅堂默认1000',
      minWidth: 120,
    },
    {
      field: 'residentCount',
      title: '网格常住居民数 (厅堂使用)',
      minWidth: 120,
    },
    {
      field: 'merchantCount',
      title: '网格周围商户数 (厅堂使用)',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL',
      minWidth: 120,
    },
    {
      field: 'creatorId',
      title: '创建人ID',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'updaterId',
      title: '更新人ID',
      minWidth: 120,
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}