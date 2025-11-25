import Taro from "@tarojs/taro";
import {Image, OpenData, View} from "@tarojs/components";
import DefaultAvatar from '../../asset/img/default.png'
import React from "react";
import classNames from "classnames";

const env = Taro.getEnv()

const Index: React.FC<{
  classname?: string
}> = (props) => {
  let avatar = <Image src={DefaultAvatar} className={classNames(props.classname, "rounded")} style={{width: '36px', height: '36px'}}  />
  switch (env) {
    case Taro.ENV_TYPE.WEAPP:
      avatar = <View className={"self-start"}>
        <OpenData type='userAvatarUrl' />
      </View>
    default: {

    }
  }
  return avatar
}
export default Index
