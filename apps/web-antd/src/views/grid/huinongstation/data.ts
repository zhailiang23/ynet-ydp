import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridHuinongStationApi } from '#/api/grid/huinongstation';

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
      fieldName: 'stationCode',
      label: '站点编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入站点编号',
      },
    },
    {
      fieldName: 'stationName',
      label: '站点名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入站点名称',
      },
    },
    {
      fieldName: 'stationType',
      label: '站点类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择站点类型',
      },
    },
    {
      fieldName: 'location',
      label: '站点坐标',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入站点坐标',
      },
    },
    {
      fieldName: 'address',
      label: '站点地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入站点地址',
      },
    },
    {
      fieldName: 'gridId',
      label: '关联的网格ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联的网格ID',
      },
    },
    {
      fieldName: 'contactPerson',
      label: '联系人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系人',
      },
    },
    {
      fieldName: 'contactPhone',
      label: '联系电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系电话',
      },
    },
    {
      fieldName: 'specialistId',
      label: '负责惠农专员ID (关联 system_users)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责惠农专员ID (关联 system_users)',
      },
    },
    {
      fieldName: 'status',
      label: '站点状态: ACTIVE/INACTIVE',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'dataSource',
      label: '数据来源: IMPORTED/MANUAL',
      component: 'Input',
      componentProps: {
        placeholder: '请输入数据来源: IMPORTED/MANUAL',
      },
    },
    {
      fieldName: 'importBatch',
      label: '导入批次号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入导入批次号',
      },
    },
    {
      fieldName: 'importTime',
      label: '导入时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
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
      fieldName: 'stationName',
      label: '站点名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入站点名称',
      },
    },
    {
      fieldName: 'stationCode',
      label: '站点编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入站点编号',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(
  onMarketingFlagChange?: (
    newFlag: string,
    row: GridHuinongStationApi.HuinongStation,
  ) => PromiseLike<boolean | undefined>,
): VxeTableGridOptions<GridHuinongStationApi.HuinongStation>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'stationName',
      title: '站点名称',
      minWidth: 150,
    },
    {
      field: 'stationCode',
      title: '站点编号',
      minWidth: 120,
    },
    {
      field: 'orgName',
      title: '所属机构',
      minWidth: 150,
    },
    {
      field: 'address',
      title: '站点地址',
      minWidth: 200,
    },
    {
      field: 'gridMarketingFlag',
      title: '网格营销站点',
      minWidth: 120,
      align: 'center',
      cellRender: {
        attrs: { beforeChange: onMarketingFlagChange },
        name: 'CellSwitch',
        props: {
          checkedValue: 'REQUIRED',
          unCheckedValue: 'OPTIONAL',
          checkedChildren: '必选',
          unCheckedChildren: '可选',
        },
      },
    },
    {
      field: 'specialistEmployeeNo',
      title: '惠农人员工号',
      minWidth: 120,
    },
    {
      field: 'specialistName',
      title: '惠农人员姓名',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 120,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}