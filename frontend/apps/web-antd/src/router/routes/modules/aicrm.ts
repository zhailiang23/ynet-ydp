import type { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/aicrm',
    name: 'AicrmCenter',
    meta: {
      title: 'AI客户管理',
      icon: 'simple-icons:civicrm',
      keepAlive: true,
      hideInMenu: true,
    },
    children: [
      {
        path: 'retail-customer/detail/:id',
        name: 'AicrmRetailCustomerDetail',
        meta: {
          title: '零售客户详情',
          activePath: '/aicrm/customer',
        },
        component: () => import('#/views/aicrm/retail-customer/detail.vue'),
      },
      {
        path: 'company-customer/detail/:id',
        name: 'AicrmCompanyCustomerDetail',
        meta: {
          title: '对公客户详情',
          activePath: '/aicrm/customer',
        },
        component: () => import('#/views/aicrm/company-customer/detail.vue'),
      },
    ],
  },
];

export default routes;
