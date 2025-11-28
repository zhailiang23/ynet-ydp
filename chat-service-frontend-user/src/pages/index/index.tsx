import React from 'react'
import Taro from '@tarojs/taro'
import {View, Button} from '@tarojs/components'
import {getMessages, getSetting, handleRead} from "@/api";
import {getToken, removeToken} from "@/util/auth";
import {isH5, isWeapp} from "@/util/env";

import SendContext, { CollectFormParams } from './context'
import Input from './components/Input'
import MessageContainer from './components/MessageContainer/index'
import CollectForm from './components/CollectForm'
import CollectFormB from './components/CollectFormB'
import classNames from "classnames";


const pageSize = 30

const Index = () => {

  const [messages, setMessages] = React.useState<APP.Message[]>([])

  // 控制是否显示留资表单 (card-a)
  const [showForm, setShowForm] = React.useState(false)
  // 留资表单参数（templateId 和 adminId）
  const [formParams, setFormParams] = React.useState<CollectFormParams>({})

  // 控制是否显示预约咨询表单 (card-b)
  const [showFormB, setShowFormB] = React.useState(false)
  // 预约咨询表单参数
  const [formParamsB, setFormParamsB] = React.useState<CollectFormParams>({})

  const [loading, setLoading] = React.useState(false)

  const [noMore, setNoMore] = React.useState(false)

  const [task, setTask] = React.useState<Taro.SocketTask | undefined>()

  const [waitingCount, setWaitingCount] = React.useState<number>(0)

  const [setting, setSetting] = React.useState<APP.ChatSetting>()

  const [username, setUsername] = React.useState<string>('')

  React.useEffect(() => {
    getSetting().then(r => {
      setSetting(r.data)
    })
    // 从 localStorage 获取用户名
    try {
      const storedUsername = Taro.getStorageSync('ws-username')
      if (storedUsername) {
        setUsername(storedUsername)
      }
    } catch (e) {
      console.log('获取用户名失败', e)
    }
  }, [])

  // 控制滚动条滚动到底部
  const [toTop, setToTop] = React.useState(false)

  const connect = React.useCallback(() => {
    Taro.connectSocket({
      url: `${WS_URL}?token=` + getToken()
    }).then(t => {
      t.onError(() => {
        setTask(undefined)
        Taro.showToast({
          title: '连接服务器失败',
          icon: 'none'
        })
      })
      t.onOpen(() => {
      })
      t.onMessage(result => {
        if (result.data != '') {
          try {
            const action: APP.Action = JSON.parse(result.data)
            switch (action.action) {
              case 'receive-message': {
                const msg = action.data as APP.Message
                if (msg.id) {
                  handleRead(msg.id).then().catch()
                }
                setMessages(prev => {
                  return [msg].concat(prev)
                })
                if (msg.admin_id > 0) { // 说明已被接入
                  setWaitingCount(0)
                }
                setToTop(prevState => !prevState)
                break
              }
              case "receipt": {
                const data : APP.Receipt = action.data
                setMessages(prevState => {
                  for (const x of prevState) {
                    if (x.req_id === data.req_id) {
                      x.id = data.msg_id;
                      x.success = true;
                      x.is_read = false;
                    }
                  }
                  return [...prevState]
                });
                break;
              }
              case "read": {
                const msgIds = action.data as number[]
                setMessages(prevState => {
                  for (const x of prevState) {
                    if (msgIds.includes(x.id as number)) {
                      x.is_read = true
                    }
                  }
                  return [...prevState]
                })
                break;
              }
              case "waiting-user-count": {
                const count = action.data
                setWaitingCount(count)
                break
              }
            }
          }catch (e) {

          }

        }
      })
      t.onClose(() => {
        setTask(undefined)
      })
      setTask(t)
    })

  }, [])

  const init = React.useCallback(() => {
    setNoMore(false)
    getMessages(pageSize,).then(res => {
      if (res.data.length < pageSize) {
        setNoMore(true)
      }
      setMessages(res.data)
      connect()
    }).catch(() => {
    })
  }, [connect])


  const send = React.useCallback((act: APP.Action): Promise<boolean> => {
    return (new Promise((resolve, reject) => {
      if (task) {
        task.send({
          data: JSON.stringify(act),
          success: () => {
            setMessages(prev => {
              return [...[act.data].concat(prev)]
            })
            setToTop(prevState => !prevState)
            resolve(true)
          },
          fail: res => {
            Taro.showToast({
              icon: 'none',
              title: res.errMsg
            })
            reject(res.errMsg)
          }
        })
      } else {
        Taro.showModal({
          title: '提示',
          content: '聊天服务器已断开',
          confirmText: '重新连接'
        }).then(res => {
          if (res.confirm) {
            connect()
          }
        })
        reject("服务器已断开")
      }
    }))
  }, [connect, task])


  const close = React.useCallback(() => {
    if (isWeapp()) {
      setTask(prevState => {
        if (prevState) {
          Taro.closeSocket().then().catch(() => {
          })
        }
        return undefined
      })
    }
    if (isH5()) {
      setTask(prevState => {
        if (prevState) {
          prevState.ws.close()
        }
        return undefined
      })
    }
  }, [])

  React.useEffect(() => {
    if (isH5()) {
      window.onresize = () => {
        window.location.reload()
      }
    }
  }, [])


  React.useEffect(() => {
    init()
    return () => {
      close()
    }
  }, [close, init])


  const fetchLock = React.useRef(false)

  const getMoreMessage = React.useCallback(async () => {
    if (!fetchLock.current && !noMore) {
      fetchLock.current = true
      setLoading(true)
      if (messages.length > 0) {
        const id = messages[messages.length - 1].id
        if (id) {
          try {
            const res = await getMessages(pageSize, id)
            setMessages(prevState => {
              setLoading(false)
              return [...prevState.concat(res.data)]
            })
            if (res.data.length < pageSize) {
              setNoMore(true)
            }
            fetchLock.current = false
          } catch (e) {
          }
        }
      }
    }
  }, [loading, messages, noMore])

  // h5模式下，用手机内置的浏览器打开100vh并不是实际的高度
  const cusStyles = React.useMemo(() => {
    if (isH5()) {
      return {
        height: window.innerHeight + "px"
      }
    }
    if (isWeapp()) {
      return {
        height: "100vh"
      }
    }
    return {}
  }, [])

  // 退出登录
  const handleLogout = React.useCallback(() => {
    Taro.showModal({
      title: '提示',
      content: '确定要退出登录吗？',
      success: (res) => {
        if (res.confirm) {
          close()
          removeToken()
          Taro.reLaunch({
            url: '/pages/login/index'
          })
        }
      }
    })
  }, [close])

  // 显示留资表单的方法 (card-a)
  const showCollectForm = React.useCallback((params?: CollectFormParams) => {
    setFormParams(params || {})
    setShowForm(true)
  }, [])

  // 关闭留资表单的方法 (card-a)
  const hideCollectForm = React.useCallback(() => {
    setShowForm(false)
  }, [])

  // 显示预约咨询表单的方法 (card-b)
  const showCollectFormB = React.useCallback((params?: CollectFormParams) => {
    setFormParamsB(params || {})
    setShowFormB(true)
  }, [])

  // 关闭预约咨询表单的方法 (card-b)
  const hideCollectFormB = React.useCallback(() => {
    setShowFormB(false)
  }, [])

  // H5环境下显示手机框，小程序环境直接显示聊天界面
  if (isH5()) {
    return (
      <SendContext.Provider value={{
        send,
        ...setting,
        showCollectForm,
        showCollectFormB
      }}>
        <div style={{
          width: '100%',
          height: '100vh',
          backgroundColor: '#f9fafb',
          display: 'flex',
          flexDirection: 'row',
          alignItems: 'center',
          justifyContent: 'center',
          gap: '40px',
          padding: '32px',
          boxSizing: 'border-box'
        }}>
          {/* 左侧：手机框 */}
          <div style={{
            position: 'relative',
            backgroundColor: '#ffffff',
            border: '3px solid #d1d5db',
            borderRadius: '8px',
            boxShadow: '0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)',
            width: '375px',
            height: 'calc(100vh - 80px)',
            maxHeight: '720px',
            overflow: 'hidden'
          }}>
            {/* 留资表单覆盖层 (card-a) */}
            {showForm && (
              <CollectForm onClose={hideCollectForm} templateId={formParams.templateId} adminId={formParams.adminId} />
            )}

            {/* 预约咨询表单覆盖层 (card-b) */}
            {showFormB && (
              <CollectFormB onClose={hideCollectFormB} templateId={formParamsB.templateId} adminId={formParamsB.adminId} />
            )}

            {/* 聊天内容区域 */}
            <View className="w-full h-full bg-[#f5f5f5]">
              {
                setting?.is_show_queue && waitingCount > 0 &&
                <View className={"px-1 h-6 flex items-center w-full bg-[#fcf6ed] text-[#de8c17]"} style={{fontSize: '12px'}}>
                  前面还有{waitingCount}人在等待
                </View>
              }
              <View className={classNames("flex flex-col justify-between w-full h-full bg-[#f5f5f5] overflow-hidden box-border", {
                "pt-6": setting?.is_show_queue && waitingCount > 0
              })}>
                <View className={"overflow-hidden flex w-full flex-1"}>
                  <MessageContainer messages={messages} top={toTop} onScrollTop={getMoreMessage}>
                    {
                      loading &&
                      <View className={"p-1 text-center"} style={{fontSize: '12px'}}>
                        loading...
                      </View>
                    }
                    {
                      !loading && noMore && <View className={"text-center py-3 text-gray-600"} style={{fontSize: '12px'}}>
                        没有更多了
                      </View>
                    }
                  </MessageContainer>
                </View>
                <Input />
              </View>
            </View>
          </div>

          {/* 右侧：信息区域 */}
          <div style={{
            display: 'flex',
            flexDirection: 'column',
            gap: '24px',
            width: '280px'
          }}>
            {/* 退出按钮 */}
            <button
              style={{
                height: '44px',
                fontSize: '16px',
                width: '280px',
                backgroundColor: '#3b82f6',
                color: 'white',
                border: 'none',
                borderRadius: '6px',
                boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.1)',
                cursor: 'pointer',
                boxSizing: 'border-box'
              }}
              onClick={handleLogout}
            >
              退出登录
            </button>

            {/* 客户信息卡片 */}
            <div style={{
              background: 'linear-gradient(to bottom right, #3b82f6, #2563eb)',
              color: 'white',
              borderRadius: '6px',
              boxShadow: '0 10px 15px -3px rgba(0, 0, 0, 0.1)',
              padding: '24px',
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              justifyContent: 'center',
              minHeight: '180px',
              width: '280px',
              boxSizing: 'border-box'
            }}>
              <div style={{fontSize: '14px', color: '#bfdbfe', marginBottom: '12px'}}>当前登录客户</div>
              <div style={{fontSize: '28px', fontWeight: 'bold', marginBottom: '8px'}}>{username || '未知用户'}</div>
              <div style={{width: '100%', height: '1px', backgroundColor: '#60a5fa', margin: '12px 0'}}></div>
              <div style={{fontSize: '14px', color: '#bfdbfe'}}>客户端在线</div>
            </div>
          </div>
        </div>
      </SendContext.Provider>
    )
  }

  // 小程序环境：原有布局
  return (
    <SendContext.Provider value={{
      send,
      ...setting,
      showCollectForm,
      showCollectFormB
    }}>
      {/* 留资表单覆盖层 - 小程序环境 (card-a) */}
      {showForm && (
        <CollectForm onClose={hideCollectForm} templateId={formParams.templateId} adminId={formParams.adminId} />
      )}

      {/* 预约咨询表单覆盖层 - 小程序环境 (card-b) */}
      {showFormB && (
        <CollectFormB onClose={hideCollectFormB} templateId={formParamsB.templateId} adminId={formParamsB.adminId} />
      )}

      {!showForm && !showFormB && (
        <>
          {
            setting?.is_show_queue  && waitingCount > 0 && <View className={"fixed px-1 h-6 flex items-center w-full bg-[#fcf6ed] text-[#de8c17]"} style={{fontSize: '12px'}}>
              前面还有{waitingCount}人在等待
            </View>
          }
          <View className={classNames("flex flex-col justify-between w-full bg-[#f5f5f5] overflow-hidden box-border", {
            "pt-6": setting?.is_show_queue  && waitingCount > 0
          })} style={cusStyles}>
            <View className={"overflow-hidden flex w-full self-end"}>
              <MessageContainer messages={messages} top={toTop} onScrollTop={getMoreMessage}>
                {
                  loading &&
                  <View className={"p-1 text-center"} style={{fontSize: '12px'}}>
                    loading...
                  </View>
                }
                {
                  !loading && noMore && <View className={"text-center py-3 text-gray-600"} style={{fontSize: '12px'}}>
                    没有更多了
                  </View>
                }
              </MessageContainer>
            </View>
            <Input />
          </View>
        </>
      )}
    </SendContext.Provider>
  )
}

export default Index

