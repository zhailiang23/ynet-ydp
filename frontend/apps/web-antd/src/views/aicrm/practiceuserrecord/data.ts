import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeUserRecordApi } from '#/api/aicrm/practiceuserrecord';

import { getDictOptions } from '@vben/hooks';

import { getPracticeCoursePage } from '#/api/aicrm/practicecourse';
import { getPracticeVirtualCustomerPage } from '#/api/aicrm/practicevirtualcustomer';
import { getUserPage } from '#/api/system/user';
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
      fieldName: 'courseId',
      label: '陪练课程',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择陪练课程',
        api: async () => {
          try {
            const data = await getPracticeCoursePage({ pageNo: 1, pageSize: 100 });
            console.log('陪练课程数据:', data);
            if (!data || !data.list) {
              console.error('陪练课程数据格式错误:', data);
              return [];
            }
            return data.list.map((item) => ({
              label: item.name || `课程${item.id}`,
              value: item.id,
            }));
          } catch (error) {
            console.error('获取陪练课程失败:', error);
            return [];
          }
        },
        showSearch: true,
        filterOption: (input: string, option: any) => {
          return option.label.toLowerCase().includes(input.toLowerCase());
        },
      },
    },
    {
      fieldName: 'vcustomerId',
      label: '虚拟用户',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择虚拟用户',
        api: async () => {
          try {
            const data = await getPracticeVirtualCustomerPage({ pageNo: 1, pageSize: 100 });
            console.log('虚拟用户数据:', data);
            if (!data || !data.list) {
              console.error('虚拟用户数据格式错误:', data);
              return [];
            }
            return data.list.map((item) => ({
              label: item.name || `客户${item.id}`,
              value: item.id,
            }));
          } catch (error) {
            console.error('获取虚拟用户失败:', error);
            return [];
          }
        },
        showSearch: true,
        filterOption: (input: string, option: any) => {
          return option.label.toLowerCase().includes(input.toLowerCase());
        },
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'courseId',
      label: '陪练课程',
      component: 'ApiSelect',
      componentProps: {
        allowClear: true,
        placeholder: '请选择陪练课程',
        api: async () => {
          try {
            const data = await getPracticeCoursePage({ pageNo: 1, pageSize: 100 });
            if (!data || !data.list) {
              return [];
            }
            return data.list.map((item) => ({
              label: item.name || `课程${item.id}`,
              value: item.id,
            }));
          } catch (error) {
            console.error('获取陪练课程失败:', error);
            return [];
          }
        },
        showSearch: true,
        filterOption: (input: string, option: any) => {
          return option.label.toLowerCase().includes(input.toLowerCase());
        },
      },
    },
    {
      fieldName: 'userId',
      label: '参与用户',
      component: 'ApiSelect',
      componentProps: {
        allowClear: true,
        placeholder: '请选择参与用户',
        api: async () => {
          try {
            const data = await getUserPage({ pageNo: 1, pageSize: 100 });
            if (!data || !data.list) {
              return [];
            }
            return data.list.map((item) => ({
              label: item.nickname || item.username || `用户${item.id}`,
              value: item.id,
            }));
          } catch (error) {
            console.error('获取用户列表失败:', error);
            return [];
          }
        },
        showSearch: true,
        filterOption: (input: string, option: any) => {
          return option.label.toLowerCase().includes(input.toLowerCase());
        },
      },
    },
    {
      fieldName: 'vcustomerId',
      label: '虚拟用户',
      component: 'ApiSelect',
      componentProps: {
        allowClear: true,
        placeholder: '请选择虚拟用户',
        api: async () => {
          try {
            const data = await getPracticeVirtualCustomerPage({ pageNo: 1, pageSize: 100 });
            if (!data || !data.list) {
              return [];
            }
            return data.list.map((item) => ({
              label: item.name || `客户${item.id}`,
              value: item.id,
            }));
          } catch (error) {
            console.error('获取虚拟用户失败:', error);
            return [];
          }
        },
        showSearch: true,
        filterOption: (input: string, option: any) => {
          return option.label.toLowerCase().includes(input.toLowerCase());
        },
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeUserRecordApi.PracticeUserRecord>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'courseName',
      title: '陪练课程',
      minWidth: 150,
    },
    {
      field: 'userName',
      title: '参与用户',
      minWidth: 120,
    },
    {
      field: 'vcustomerName',
      title: '虚拟用户',
      minWidth: 120,
    },
    {
      field: 'startTime',
      title: '开始时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      field: 'endTime',
      title: '结束时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      field: 'totalScore',
      title: '总评分',
      minWidth: 100,
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}