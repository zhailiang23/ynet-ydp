import type { UserConfigExport } from "@tarojs/cli";
export default {
  defineConstants: {
    BASE_URL: '"http://192.168.153.111:8080/api/user"',
    WS_URL: '"ws://192.168.153.111:8080/api/user/chat/ws"'
  },
  mini: {},
  h5: {},
} satisfies UserConfigExport<'webpack5'>
