import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CommunityApi } from '#/api/grid/community';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'communityCode',
      label: '社区编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入社区编号',
      },
    },
    {
      fieldName: 'communityName',
      label: '社区名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入社区名称',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        placeholder: '请选择状态',
        options: [
          { label: '正常', value: 'ACTIVE' },
          { label: '无效', value: 'INACTIVE' },
        ],
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<CommunityApi.Community>['columns'] {
  return [
    {
      field: 'communityCode',
      title: '社区编号',
      minWidth: 180,
    },
    {
      field: 'communityName',
      title: '社区名称',
      minWidth: 150,
    },
    {
      field: 'teamCount',
      title: '团队人数',
      minWidth: 100,
    },
    {
      field: 'managerUserName',
      title: '责任人姓名',
      minWidth: 120,
    },
    {
      field: 'householdCount',
      title: '人口（户数）',
      minWidth: 120,
    },
    {
      field: 'individualBusinessCount',
      title: '个体工商户数',
      minWidth: 130,
    },
    {
      field: 'smallEnterpriseCount',
      title: '小微企业数',
      minWidth: 130,
    },
    {
      field: 'commercialComplexCount',
      title: '商业综合体数',
      minWidth: 130,
    },
    {
      field: 'qualityUnitCount',
      title: '优质单位数',
      minWidth: 120,
    },
    {
      field: 'score',
      title: '评分',
      minWidth: 100,
    },
    {
      field: 'adjustedScore',
      title: '校正评分',
      minWidth: 100,
    },
    {
      field: 'status',
      title: '状态',
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
