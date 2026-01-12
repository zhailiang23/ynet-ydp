package com.ynet.iplatform.module.infra.service.chatrobot.dingtalk;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * 钉钉机器人示例代码
 * <p>
 * 这是一个独立运行的示例程序，用于测试钉钉 Stream 模式消息接收
 *
 * @author 易诚源码
 */
@Slf4j
public class BotExample {
  

  public static void main(String[] args) throws Exception {
    OpenDingTalkClient client = OpenDingTalkStreamClientBuilder
                .custom()
                .credential(new AuthClientCredential("dingqdy9xgzxj1zgntfh", "8hnvZ9nkTaaFY6OaztOQ2YLQGeTiRxenKcK3Eb4YU7Xb7HIhuMqN6ph4VXs56tuT"))
                .registerCallbackListener("/v1.0/im/bot/messages/get", new RobotMsgCallbackConsumer())
                .build();
     client.start();
  }
  
  
  public static class RobotMsgCallbackConsumer implements OpenDingTalkCallbackListener<JSONObject, JSONObject> {
    
    /*
     * @param request
     * @return
     */
    @Override
    public JSONObject execute(JSONObject request) {
        System.out.println(JSON.toJSONString(request));
        try {
            JSONObject text = request.getJSONObject("text");
            if (text != null) {
                //机器人接收消息内容
                String msg = text.getStr("content").trim();
                String openConversationId = request.getStr("conversationId");
            }
        } catch (Exception e) {
            log.error("receive group message by robot error:" +e.getMessage(), e);
        }
        return new JSONObject();
    }
  } 
}