// FAT 环境配置
import { defineConfig } from '@umijs/max';

export default defineConfig({
  define: {
    BASE_URL: 'http://192.168.153.111:8080/api/backend',
    WS_URL: 'http://192.168.153.111:8080/api/backend/ws',
  },
});
