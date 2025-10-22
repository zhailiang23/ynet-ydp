import type { DescriptionItemSchema } from '#/components/description';

import { h } from 'vue';

import { DICT_TYPE } from '@vben/constants';
import { formatDateTime } from '@vben/utils';

import { DictTag } from '#/components/dict-tag';

/** 详情头部的配置 */
export function useDetailSchema(): DescriptionItemSchema[] {
  return [
    {
      field: 'source',
      label: '线索来源',
      content: (data) =>
        h(DictTag, {
          type: DICT_TYPE.CRM_CUSTOMER_SOURCE,
          value: data?.source,
        }),
    },
    {
      field: 'mobile',
      label: '手机',
    },
    {
      field: 'ownerUserName',
      label: '负责人',
    },
    {
      field: 'createTime',
      label: '创建时间',
      content: (data) => formatDateTime(data?.createTime) as string,
    },
  ];
}

/** 详情基本信息的配置 */
export function useDetailBaseSchema(): DescriptionItemSchema[] {
  return [
    {
      field: 'name',
      label: '线索名称',
    },
    {
      field: 'source',
      label: '客户来源',
      content: (data) =>
        h(DictTag, {
          type: DICT_TYPE.CRM_CUSTOMER_SOURCE,
          value: data?.source,
        }),
    },
    {
      field: 'mobile',
      label: '手机',
    },
    {
      field: 'telephone',
      label: '电话',
    },
    {
      field: 'email',
      label: '邮箱',
    },
    {
      field: 'areaName',
      label: '地址',
      content: (data) => {
        const areaName = data.areaName ?? '';
        const detailAddress = data?.detailAddress ?? '';
        return [areaName, detailAddress].filter((item) => !!item).join(' ');
      },
    },
    {
      field: 'qq',
      label: 'QQ',
    },
    {
      field: 'wechat',
      label: '微信',
    },
    {
      field: 'industryId',
      label: '客户行业',
      content: (data) =>
        h(DictTag, {
          type: DICT_TYPE.CRM_CUSTOMER_INDUSTRY,
          value: data?.industryId,
        }),
    },
    {
      field: 'level',
      label: '客户级别',
      content: (data) =>
        h(DictTag, {
          type: DICT_TYPE.CRM_CUSTOMER_LEVEL,
          value: data?.level,
        }),
    },
    {
      field: 'contactNextTime',
      label: '下次联系时间',
      content: (data) => formatDateTime(data?.contactNextTime) as string,
    },
    {
      field: 'remark',
      label: '备注',
    },
  ];
}
