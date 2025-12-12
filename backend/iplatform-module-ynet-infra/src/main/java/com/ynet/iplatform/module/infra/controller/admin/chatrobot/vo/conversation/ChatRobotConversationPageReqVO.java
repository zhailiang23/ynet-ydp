package com.ynet.iplatform.module.infra.controller.admin.chatrobot.vo.conversation;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 钉钉机器人对话分页查询 Request VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 钉钉机器人对话分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatRobotConversationPageReqVO extends PageParam {

    @Schema(description = "机器人ID", example = "123")
    private Long robotId;

    @Schema(description = "对话类型：1=单聊，2=群聊", example = "1")
    private Integer conversationType;

    @Schema(description = "对话标题（模糊匹配）", example = "张三")
    private String conversationTitle;

    @Schema(description = "对话状态：0=进行中，1=已关闭", example = "0")
    private Integer status;
}
