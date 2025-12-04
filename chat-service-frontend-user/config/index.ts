import { defineConfig, type UserConfigExport } from '@tarojs/cli'
import TsconfigPathsPlugin from 'tsconfig-paths-webpack-plugin'
import devConfig from './dev'
import fatConfig from './fat'
import uatConfig from './uat'
import proConfig from './pro'

// 检测是否为 H5 或 RN 构建（这些平台不需要 weapp-tailwindcss）
const isH5 = process.env.TARO_ENV === "h5";
const isApp = process.env.TARO_ENV === "rn";
const WeappTailwindcssDisabled = isH5 || isApp;

// 只在小程序构建时导入 weapp-tailwindcss
let UnifiedWebpackPluginV5: any;
if (!WeappTailwindcssDisabled) {
  try {
    UnifiedWebpackPluginV5 = require("weapp-tailwindcss/webpack").UnifiedWebpackPluginV5;
  } catch (e) {
    console.warn('weapp-tailwindcss not available, skipping for H5/RN build');
  }
}
export default defineConfig(async (merge) => {
  const baseConfig: UserConfigExport = {
    projectName: 'taro-react-tailwind-vscode-template',
    date: '2023-5-6',
    designWidth: 750,
    deviceRatio: {
      640: 2.34 / 2,
      750: 1,
      828: 1.81 / 2
    },
    sourceRoot: 'src',
    outputRoot: 'dist',
    plugins: [],
    defineConstants: {
    },
    copy: {
      patterns: [
      ],
      options: {
      }
    },
    framework: 'react',
    compiler: {
      type: 'webpack5',
      prebundle: {
        enable: false,
      }
    },
    cache: {
      enable: false // Webpack 持久化缓存配置，建议开启。默认配置请参考：https://docs.taro.zone/docs/config-detail#cache
    },
    mini: {
      postcss: {
        pxtransform: {
          enable: true,
          config: {

          }
        },
        url: {
          enable: true,
          config: {
            limit: 1024 // 设定转换尺寸上限
          }
        },
        cssModules: {
          enable: true, // 默认为 false，如需使用 css modules 功能，则设为 true
          config: {
            namingPattern: 'module', // 转换模式，取值为 global/module
            generateScopedName: '[name]__[local]___[hash:base64:5]'
          }
        },

      },
      webpackChain(chain) {
        chain.resolve.plugin('tsconfig-paths').use(TsconfigPathsPlugin)
        // 只在小程序构建时添加 weapp-tailwindcss 插件
        if (!WeappTailwindcssDisabled && UnifiedWebpackPluginV5) {
          chain.merge({
            plugin: {
              install: {
                plugin: UnifiedWebpackPluginV5,
                args: [
                  {
                    appType: 'taro',
                    rem2rpx: true
                  }
                ]
              }
            }
          });
        }
      },
      sassLoaderOption: {
        sassOptions: {
          silenceDeprecations: ['legacy-js-api', 'import'],
        }
      }
    },
    h5: {
      publicPath: '/',
      staticDirectory: 'static',
      output: {
        filename: 'js/[name].[hash:8].js',
        chunkFilename: 'js/[name].[chunkhash:8].js'
      },
      miniCssExtractPluginOption: {
        ignoreOrder: true,
        filename: 'css/[name].[hash].css',
        chunkFilename: 'css/[name].[chunkhash].css'
      },
      postcss: {
        autoprefixer: {
          enable: true,
          config: {}
        },
        cssModules: {
          enable: true, // 默认为 false，如需使用 css modules 功能，则设为 true
          config: {
            namingPattern: 'module', // 转换模式，取值为 global/module
            generateScopedName: '[name]__[local]___[hash:base64:5]'
          }
        }
      },
      webpackChain(chain) {
        chain.resolve.plugin('tsconfig-paths').use(TsconfigPathsPlugin)
      }
    },
    rn: {
      appName: 'taroDemo',
      postcss: {
        cssModules: {
          enable: false, // 默认为 false，如需使用 css modules 功能，则设为 true
        }
      }
    }
  }
  // 根据环境变量 APP_ENV 选择配置文件
  // 支持 4 个环境: dev, fat, uat, pro
  const appEnv = process.env.APP_ENV || 'dev'

  if (process.env.NODE_ENV === 'development') {
    // 本地开发构建配置（不混淆压缩）
    return merge({}, baseConfig, devConfig)
  }

  // 生产构建配置（默认开启压缩混淆等）
  // 根据 APP_ENV 环境变量选择对应的环境配置
  switch (appEnv) {
    case 'fat':
      return merge({}, baseConfig, fatConfig)
    case 'uat':
      return merge({}, baseConfig, uatConfig)
    case 'pro':
      return merge({}, baseConfig, proConfig)
    default:
      return merge({}, baseConfig, devConfig)
  }
})