import type { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/grid',
    name: 'GridCenter',
    meta: {
      title: '网格管理',
      icon: 'mdi:grid',
      keepAlive: true,
      hideInMenu: true,
    },
    children: [
      {
        path: 'huinong-marketing-heatmap',
        name: 'HuinongMarketingHeatmap',
        meta: {
          title: '惠农网格营销热力图',
          icon: 'ant-design:heat-map-outlined',
        },
        component: () => import('#/views/grid/huinongmarketing/heatmap.vue'),
      },
    ],
  },
];

export default routes;
