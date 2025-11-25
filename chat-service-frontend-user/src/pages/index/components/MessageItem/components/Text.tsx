import React from 'react'
import Taro from '@tarojs/taro'
import {isPhone} from "@/util";
import {Text, View} from "@tarojs/components";

const Index: React.FC<{
  content: string
}> = props => {

  const makePhoneCall = React.useCallback((content: string) => {
    if (isPhone(content)) {
      Taro.makePhoneCall({
        phoneNumber: content
      })
    }
  } ,[])

  return React.useMemo(() => {
    return  <View className={"break-all px-2.5"} style={{fontSize: '14px', lineHeight: '1.2', padding: '9.5px 10px'}}>
      <Text  onClick={() => makePhoneCall(props.content)}>{props.content}</Text>
    </View>
  } ,[makePhoneCall, props.content])
}
export default Index
