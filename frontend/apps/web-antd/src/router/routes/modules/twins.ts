import type { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/twins/chat/:adminId',
    component: () => import('#/views/twins/chat/index.vue'),
    name: 'TwinsChat',
    meta: {
      title: '客服对话',
      icon: 'ant-design:message-filled',
      hideInMenu: true,
    },
  },
];

export default routes;
