import React from 'react'
import {View, Image} from "@tarojs/components";
import Taro from '@tarojs/taro'
import SendContext from '../../../context'

// 从 URL 中解析查询参数
const parseUrlParams = (url: string): Record<string, string> => {
  const params: Record<string, string> = {}
  const queryIndex = url.indexOf('?')
  if (queryIndex === -1) return params

  const queryString = url.substring(queryIndex + 1)
  queryString.split('&').forEach(pair => {
    const [key, value] = pair.split('=')
    if (key && value) {
      params[key] = decodeURIComponent(value)
    }
  })
  return params
}

const Index: React.FC<{
  content: string
}> = props => {

  const item: APP.NavigatorContent = JSON.parse(props.content)
  const { showCollectForm, showCollectFormB } = React.useContext(SendContext)

  const handleClick = React.useCallback(() => {
    // 从 URL 中解析 templateId 和 adminId 参数
    const params = parseUrlParams(item.url)
    const formParams = {
      templateId: params.templateId ? parseInt(params.templateId, 10) : undefined,
      adminId: params.adminId ? parseInt(params.adminId, 10) : undefined
    }

    // 如果是留资表单页面，则在当前区域内显示表单
    if (item.url && item.url.includes('card-a')) {
      if (showCollectForm) {
        showCollectForm(formParams)
      }
    } else if (item.url && item.url.includes('card-b')) {
      // 预约咨询类表单
      if (showCollectFormB) {
        showCollectFormB(formParams)
      }
    } else {
      // 其他页面正常跳转
      Taro.navigateTo({
        url: item.url
      }).catch(() => {
        Taro.showToast({
          icon: 'none',
          title: '路径不存在'
        })
      })
    }
  }, [item.url, showCollectForm, showCollectFormB])

  return <View className={"bg-white border border-solid rounded overflow-hidden w-52 flex flex-col"} onClick={handleClick}>
      <Image className={"w-full h-32"} src={item.image} lazyLoad />
      <View className={"text-base p-0.5 border-t border-solid"}>{item.title}</View>
    </View>
}
export default Index
