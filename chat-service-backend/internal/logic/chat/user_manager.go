package chat

import (
	"context"
	"database/sql"
	"errors"
	"fmt"
	grpc "gf-chat/api/chat/v1"
	"gf-chat/internal/cache"
	ragclient "gf-chat/internal/client"
	"gf-chat/internal/consts"
	"gf-chat/internal/model"
	"gf-chat/internal/model/do"
	"gf-chat/internal/service"
	"strconv"
	"strings"
	"time"

	"github.com/gogf/gf/v2/errors/gcode"
	"github.com/gogf/gf/v2/frame/g"
	"github.com/gogf/gf/v2/os/gtime"

	"github.com/gogf/gf/v2/errors/gerror"
)

func newUserManager(cluster bool) *userManager {
	userM = &userManager{
		newManager(10, time.Minute, cluster, consts.WsTypeUser),
	}
	userM.on(eventRegister, userM.onRegister)
	userM.on(eventUnRegister, userM.onUnRegister)
	userM.on(eventMessage, userM.onMessage)
	return userM
}

type userManager struct {
	*manager
}

func (s *userManager) deliveryMessage(ctx context.Context, message *model.CustomerChatMessage, forceLocal ...bool) error {
	userLocal, server, err := s.isUserLocal(ctx, message.UserId)
	if err != nil {
		return err
	}
	if s.isCallLocal(forceLocal...) || userLocal {
		userConn, exist := s.getConn(message.CustomerId, message.UserId)
		switch message.Type {
		case consts.MessageTypeRate:
			session, err := service.ChatSession().First(ctx, do.CustomerChatSessions{Id: message.SessionId})
			if err != nil {
				return err
			}
			err = service.ChatSession().Close(ctx, session, false, true)
			if err != nil {
				return err
			}
		}
		if exist {
			userConn.deliver(action.newReceive(message))
		}
		return nil
	}
	if server != "" {
		rpcClient := service.Grpc().Client(ctx, server)
		if rpcClient != nil {
			_, err = rpcClient.SendMessage(ctx, &grpc.SendMessageRequest{
				MsgId: uint32(message.Id),
				Type:  consts.WsTypeUser,
			})
			if err != nil {
				return err
			}
		}

	}
	return nil
}

// noticeQueueLocation 等待人数
func (s *userManager) noticeQueueLocation(ctx context.Context, conn iWsConn) (err error) {
	isSHowQueue, err := service.ChatSetting().GetIsUserShowQueue(ctx, conn.getCustomerId())
	if err != nil {
		return
	}
	if !isSHowQueue {
		return
	}
	uTime, err := manual.getAddTime(ctx, conn.getUserId(), conn.getCustomerId())
	if err != nil {
		return
	}
	count, err := manual.getCountByTime(ctx, conn.getCustomerId(), "-inf",
		strconv.FormatFloat(uTime, 'f', 0, 64))
	if err != nil {
		return
	}
	conn.deliver(action.newWaitingUserCount(count - 1))
	return
}

// broadcastQueueLocation broadcasts the current queue position to all connected users for a given customer ID.
// It checks if queue display is enabled for the customer, then either:
// 1. For local connections: Notifies all connected users of their position in queue
// 2. For cluster mode: Broadcasts the queue update to all servers via gRPC
//
// Parameters:
//   - ctx: The context for the request
//   - customerId: ID of the customer whose queue to broadcast
//   - forceLocal: Optional parameter to force local-only processing even in cluster mode
//
// Returns error if the broadcast fails
func (s *userManager) broadcastQueueLocation(ctx context.Context, customerId uint, forceLocal ...bool) (err error) {
	isSHowQueue, err := service.ChatSetting().GetIsUserShowQueue(ctx, customerId)
	if err != nil {
		return
	}
	if !isSHowQueue {
		return nil
	}
	if s.isCallLocal(forceLocal...) {
		conns := s.getAllConn(customerId)
		for _, conn := range conns {
			in, err := manual.isInSet(ctx, conn.getUserId(), conn.getCustomerId())
			if err != nil {
				return err
			}
			if in {
				err = s.noticeQueueLocation(ctx, conn)
				if err != nil {
					return err
				}
			}
		}
		return
	} else {
		err = service.Grpc().CallAll(ctx, func(client grpc.ChatClient) {
			_, err = client.BroadcastQueueLocation(ctx, &grpc.BroadcastQueueLocationRequest{
				CustomerId: uint32(customerId),
			})
			if err != nil {
				log.Errorf(ctx, "%+v", err)
			}
		})
		if err != nil {
			return
		}
	}
	return
}

func (s *userManager) triggerAiResponse(ctx context.Context, msg *model.CustomerChatMessage) error {
	aiOpen, err := service.ChatSetting().GetAiOpen(ctx, msg.CustomerId)
	if err != nil {
		return err
	}
	if aiOpen {
		messages, err := service.ChatMessage().GetAiMessageContext(ctx, msg.UserId, msg.Id)
		if err != nil {
			return err
		}
		aiText, err := service.Langchain().Ask(ctx, msg.Content, service.Langchain().MessageToContent(messages)...)
		if err != nil {
			return err
		}
		respMsg := service.ChatMessage().NewAi(aiText)
		respMsg.UserId = msg.UserId
		respMsg.SessionId = msg.SessionId
		respMsg.CustomerId = msg.CustomerId
		respMsg, err = service.ChatMessage().Insert(ctx, respMsg)
		if err != nil {
			return err
		}
		err = s.deliveryMessage(ctx, respMsg)
		if err != nil {
			return err
		}
	}
	return nil
}

func (s *userManager) deliveryToAdmin(ctx context.Context, conn iWsConn, msg *model.CustomerChatMessage) error {
	g.Log().Infof(ctx, "[AI-Flow] deliveryToAdmin 开始: msgId=%d, userId=%d, adminId=%d", msg.Id, msg.UserId, msg.AdminId)

	// 获取有效会话
	session, err := service.ChatSession().FirstActive(ctx, msg.UserId, msg.AdminId, nil)
	if err != nil {
		g.Log().Errorf(ctx, "[AI-Flow] 获取会话失败: %+v", err)
		return err
	}
	g.Log().Infof(ctx, "[AI-Flow] 获取到会话: sessionId=%d, aiEnabled=%v, type=%d", session.Id, session.AiEnabled, session.Type)

	// 更新有效时间
	err = relation.updateUser(ctx, msg.AdminId, msg.UserId)
	if err != nil {
		return err
	}
	msg.SessionId = session.Id
	_, err = service.ChatMessage().UpdatePri(ctx, msg.Id, do.CustomerChatMessages{
		SessionId: msg.SessionId,
	})
	if err != nil {
		return err
	}

	// 检查是否启用了 AI 接管
	if session.AiEnabled {
		g.Log().Infof(ctx, "[AI-Flow] AI 接管已启用,准备异步调用 triggerAiResponseForSession")
		// 异步调用 AI 生成回复
		go s.triggerAiResponseForSession(ctx, msg, session, conn)
	} else {
		g.Log().Infof(ctx, "[AI-Flow] AI 接管未启用,跳过 AI 回复")
	}

	adminOnline, _, err := adminM.getConnInfo(ctx, msg.CustomerId, msg.AdminId)
	if err != nil {
		return err
	}
	if adminOnline {
		err = userM.triggerMessageEvent(ctx, consts.AutoRuleSceneAdminOnline, msg, conn)
		if err != nil {
			return err
		}
		return adminM.deliveryMessage(ctx, msg)
	} else {
		err = adminM.handleOffline(ctx, msg, conn)
		if err != nil {
			return err
		}
	}
	return nil
}

func (s *userManager) handleManual(ctx context.Context, msg *model.CustomerChatMessage, user iChatUser) error {
	var inManual bool
	// 在代人工接入列表中
	inManual, err := manual.isInSet(ctx, msg.UserId, msg.CustomerId)
	if err != nil {
		return err
	}
	if inManual {
		session, err := service.ChatSession().FirstNormal(ctx, msg.UserId, 0)
		if err != nil {
			_ = manual.removeFromSet(ctx, msg.UserId, msg.CustomerId)
			return err
		}
		msg.SessionId = session.Id
		_, err = service.ChatMessage().UpdatePri(ctx, msg.Id, do.CustomerChatMessages{
			SessionId: msg.SessionId,
		})
		if err != nil {
			return err
		}
		err = adminM.broadcastWaitingUser(ctx, msg.CustomerId)
		if err != nil {
			return err
		}
	} else {
		// 不在待人工接入列表中
		var isAutoAdd bool
		isAutoAdd, err = service.ChatSetting().GetIsAutoTransferManual(ctx, msg.CustomerId)
		if err != nil {
			return err
		}
		if isAutoAdd { // 如果自动转人工
			session, err := s.addToManual(ctx, user)
			if err != nil {
				return err
			}
			if session != nil {
				msg.SessionId = session.Id
				_, err = service.ChatMessage().UpdatePri(ctx, msg.Id, do.CustomerChatMessages{
					SessionId: msg.SessionId,
				})
			}
		} else {
			err = s.triggerAiResponse(ctx, msg)
			if err != nil {
				return err
			}
		}
	}
	return nil
}

func (s *userManager) handleTransfer(ctx context.Context, msg *model.CustomerChatMessage) (inTransfer bool, err error) {
	transferAdminId, err := service.ChatTransfer().GetUserTransferId(ctx, msg.CustomerId, msg.UserId)
	if err != nil {
		_ = service.ChatTransfer().RemoveUser(ctx, msg.CustomerId, msg.UserId)
		return
	}
	inTransfer = transferAdminId > 0
	if inTransfer {
		var session *model.CustomerChatSession
		session, err = service.ChatSession().FirstTransfer(ctx, msg.UserId, transferAdminId)
		if err != nil {
			return
		}
		msg.SessionId = session.Id
		_, err = service.ChatMessage().UpdatePri(ctx, msg.Id, do.CustomerChatMessages{
			SessionId: msg.SessionId,
		})
		if err != nil {
			return
		}
	}
	return
}

// onMessage handles incoming messages from users
// It processes the message by:
// 1. Setting message source and user ID
// 2. Getting the valid admin for the user
// 3. Inserting the message into storage
// 4. Sending receipt to user
// 5. Handling message delivery to admin if online, or offline handling if not
//
// Parameters:
//   - ctx: The context for the request
//   - arg: Event arguments containing the message and connection
//
// Returns error if message processing fails
func (s *userManager) onMessage(ctx context.Context, arg eventArg) (err error) {
	msg := arg.msg
	conn := arg.conn
	msg.Source = consts.MessageSourceUser
	msg.UserId = conn.getUserId()

	g.Log().Infof(ctx, "[AI-Flow] ========== 收到用户消息 ==========")
	g.Log().Infof(ctx, "[AI-Flow] userId=%d, msgType=%s, content=%s", msg.UserId, msg.Type, msg.Content)

	msg.AdminId, err = relation.getUserValidAdmin(ctx, msg.UserId)
	if err != nil {
		return err
	}
	g.Log().Infof(ctx, "[AI-Flow] 获取到分配的客服: adminId=%d", msg.AdminId)

	msg, err = service.ChatMessage().Insert(ctx, msg)
	if err != nil {
		return
	}
	g.Log().Infof(ctx, "[AI-Flow] 消息已保存: msgId=%d", msg.Id)

	// 发送回执
	conn.deliver(action.newReceipt(msg))
	if msg.AdminId > 0 {
		g.Log().Infof(ctx, "[AI-Flow] 消息有客服接入,调用 deliveryToAdmin")
		return s.deliveryToAdmin(ctx, conn, msg)
	}
	// 触发自动回复事件
	err = s.triggerMessageEvent(ctx, consts.AutoRuleSceneNotAccepted, msg, conn)
	if err != nil {
		log.Errorf(ctx, "%+v", err)
	}
	inTransfer, err := s.handleTransfer(ctx, msg)
	if err != nil {
		return
	}
	if !inTransfer {
		err = s.handleManual(ctx, msg, conn.getUser())
		if err != nil {
			return
		}
	}
	return nil
}

// 触发进入事件，只有没有对应客服的情况下触发，10分钟内多触发一次
func (s *userManager) triggerEnterEvent(ctx context.Context, conn iWsConn) (err error) {
	adminId, err := relation.getUserValidAdmin(ctx, conn.getUserId())
	if adminId > 0 {
		return
	}
	cacheKey := fmt.Sprintf("welcome:%d", conn.getUserId())
	val, err := cache.Def.Get(ctx, cacheKey)
	if err != nil {
		return
	}
	if !val.IsNil() {
		return
	}
	var rule *model.CustomerChatAutoRule
	rule, err = service.AutoRule().GetEnterRule(ctx, conn.getCustomerId())
	if err != nil {
		return
	}
	var autoMsg *model.CustomerChatAutoMessage
	autoMsg, err = service.AutoRule().GetMessage(ctx, rule)
	if err != nil {
		return
	}
	var entityMessage *model.CustomerChatMessage
	entityMessage, err = service.AutoMessage().ToChatMessage(ctx, autoMsg)
	if err != nil {
		return
	}
	entityMessage.UserId = conn.getUserId()
	_, err = service.ChatMessage().Save(ctx, entityMessage)
	if err != nil {
		return err
	}
	rule.Count++
	err = service.AutoRule().IncrTriggerCount(ctx, rule)
	if err != nil {
		return
	}
	conn.deliver(action.newReceive(entityMessage))
	err = cache.Def.Set(ctx, cacheKey, gtime.Now().String(), time.Minute*10)
	if err != nil {
		return
	}
	return
}

func (s *userManager) onUnRegister(ctx context.Context, arg eventArg) error {
	return adminM.noticeUserOffline(ctx, arg.conn.getUser().getPrimaryKey())
}

// 链接建立后的额外操作
// 如果已经在待接入人工列表中，则推送当前队列位置
// 如果不在待接入人工列表中且没有设置客服，则触发进入事件
func (s *userManager) onRegister(ctx context.Context, arg eventArg) (err error) {
	err = adminM.noticeUserOnline(ctx, arg.conn.getUserId(), arg.conn.getPlatform())
	if err != nil {
		return nil
	}
	in, err := manual.isInSet(ctx, arg.conn.getUserId(), arg.conn.getCustomerId())
	if err != nil {
		return err
	}
	if in {
		err = s.noticeQueueLocation(ctx, arg.conn)
	} else {
		err = s.triggerEnterEvent(ctx, arg.conn)
	}
	return err
}

// adds a user to the manual chat queue and handles the initial setup
// It checks if the user is already in queue, finds available admins, and handles offline admin scenarios
// Parameters:
//   - ctx: The context for the operation
//   - user: The chat user to add to manual queue
//
// Returns:
//   - session: The created chat session if successful
//   - error: Error if operation fails
func (s *userManager) addToManual(ctx context.Context, user iChatUser) (session *model.CustomerChatSession, err error) {
	in, err := manual.isInSet(ctx, user.getPrimaryKey(), user.getCustomerId())
	if err != nil {
		return
	}
	if in {
		err = gerror.NewCode(gcode.CodeBusinessValidationFailed, "is in")
		return
	}
	onlineAdminIds, err := adminM.getOnlineUserIds(ctx, user.getCustomerId())
	if err != nil {
		return
	}
	if len(onlineAdminIds) == 0 { // 如果没有在线客服
		rule, err := service.AutoRule().GetSystemOne(ctx, user.getCustomerId(), consts.AutoRuleMatchAdminAllOffLine)
		if err == nil {
			switch rule.ReplyType {
			case consts.AutoRuleReplyTypeMessage:
				autoMessage, err := service.AutoRule().GetMessage(ctx, rule)
				if err == nil {
					message, err := service.AutoMessage().ToChatMessage(ctx, autoMessage)
					if err != nil {
						return nil, err
					}
					err = service.AutoRule().IncrTriggerCount(ctx, rule)
					if err != nil {
						return nil, err
					}
					message.UserId = user.getPrimaryKey()
					message, err = service.ChatMessage().Insert(ctx, message)
					if err != nil {
						return nil, err
					}
					err = s.deliveryMessage(ctx, message, true)
					if err != nil {
						return nil, err
					}
					return nil, gerror.New("interrupt")
				}
			}
		}
	}
	err = manual.addToSet(ctx, user.getPrimaryKey(), user.getCustomerId())
	if err != nil {
		return
	}
	session, err = service.ChatSession().FirstNormal(ctx, user.getPrimaryKey(), 0)
	if err != nil {
		if !errors.Is(err, sql.ErrNoRows) {
			return nil, err
		} else {
			session, err = service.ChatSession().Create(ctx, user.getPrimaryKey(), user.getCustomerId(), consts.ChatSessionTypeNormal)
			if err != nil {
				return nil, err
			}
		}
	}
	if session == nil {
		return
	}
	message := service.ChatMessage().NewNotice(session, "正在为你转接人工客服")
	message, err = service.ChatMessage().Insert(ctx, message)
	if err != nil {
		return nil, err
	}
	err = s.deliveryMessage(ctx, message, true)
	if err != nil {
		return nil, err
	}
	err = adminM.broadcastWaitingUser(ctx, message.CustomerId)
	if err != nil {
		return
	}
	err = s.broadcastQueueLocation(ctx, message.CustomerId)
	if err != nil {
		return
	}
	return
}

// triggerMessageEvent handles triggering auto-reply rules based on message events.
// It checks if any auto-reply rules match the given scene and message content,
// and executes the appropriate action (transfer to manual service or send auto-reply).
//
// Parameters:
//   - ctx: The context for the operation
//   - scene: The scene identifier for matching rules (e.g. admin online/offline)
//   - message: The chat message that triggered the event
//   - userConn: The websocket connection of the user
//
// Returns error if any operation fails during rule processing
func (s *userManager) triggerMessageEvent(ctx context.Context, scene string, message *model.CustomerChatMessage, userConn iWsConn) (err error) {
	user := userConn.getUser()
	rules, err := service.AutoRule().AllActive(ctx, user.getCustomerId())
	if err != nil {
		return
	}
	for _, rule := range rules {
		isMatch := service.AutoRule().IsMatch(rule, scene, message.Content)
		if !isMatch {
			continue
		}
		switch rule.ReplyType {
		// 转接人工客服
		case consts.AutoRuleReplyTypeTransfer:
			var isTransfer bool
			isTransfer, err = service.ChatTransfer().IsInTransfer(ctx, user.getCustomerId(), user.getPrimaryKey())
			if err != nil {
				return
			}
			if isTransfer {
				return nil
			}
			var session *model.CustomerChatSession
			session, err = s.addToManual(ctx, user)
			if err != nil {
				if err.Error() == "interrupt" {
					return nil
				}
				return
			}
			message.SessionId = session.Id
			_, err = service.ChatMessage().Save(ctx, message)
			if err != nil {
				return
			}

			err = service.AutoRule().IncrTriggerCount(ctx, rule)
			if err != nil {
				return
			}
			return nil

		// 回复消息
		case consts.AutoRuleReplyTypeMessage:
			autoMessage, err := service.AutoRule().GetMessage(ctx, rule)
			fmt.Println(autoMessage)
			if err != nil {
				return err
			}
			msg, err := service.AutoMessage().ToChatMessage(ctx, autoMessage)
			if err != nil {
				return err
			}
			msg.UserId = message.UserId
			msg.SessionId = message.SessionId
			_, err = service.ChatMessage().Save(ctx, msg)
			if err != nil {
				return err
			}
			s.SendAction(action.newReceive(msg), userConn)
			_ = service.AutoRule().IncrTriggerCount(ctx, rule)
			return nil
		}
	}
	return nil
}

// triggerAiResponseForSession 当会话启用 AI 接管时，调用 AI 生成回复
func (s *userManager) triggerAiResponseForSession(ctx context.Context, msg *model.CustomerChatMessage, session *model.CustomerChatSession, conn iWsConn) {
	g.Log().Infof(ctx, "[AI-Flow] triggerAiResponseForSession 开始: msgId=%d, sessionId=%d, msgType=%s, adminId=%d",
		msg.Id, session.Id, msg.Type, msg.AdminId)

	// 只处理文本消息
	if msg.Type != consts.MessageTypeText {
		g.Log().Infof(ctx, "[AI-Flow] 消息类型非文本,跳过: msgType=%s", msg.Type)
		return
	}

	// 获取 AI 提示词模板
	promptTemplate, err := service.ChatSetting().GetAiPrompt(ctx, msg.CustomerId)
	if err != nil {
		log.Errorf(ctx, "[AI-Flow] 获取 AI 提示词模板失败: %+v", err)
		return
	}

	// 如果没有配置提示词模板，使用默认提示词
	if promptTemplate == "" {
		promptTemplate = `你是一个专业的客服助手,请基于以下知识库内容和历史对话信息回答客户问题。

【知识库参考资料】
{{knowledge}}

【历史对话】
{{history}}

【客户问题】
{{message}}

请根据知识库内容回答问题,如果知识库中没有相关信息,则根据你的专业知识回答。回答要准确、专业、热情。`
	}
	g.Log().Infof(ctx, "[AI-Flow] AI 提示词模板长度: %d 字符", len(promptTemplate))

	// 获取最近的对话历史（用于上下文），排除当前消息
	historyMessages, err := service.ChatMessage().All(ctx, do.CustomerChatMessages{
		SessionId: session.Id,
	}, nil, "id asc", 10)
	if err != nil {
		log.Errorf(ctx, "[AI-Flow] 获取对话历史失败: %+v", err)
		return
	}
	g.Log().Infof(ctx, "[AI-Flow] 获取到 %d 条历史消息", len(historyMessages))

	// 【新增】集成 RAG 知识库召回
	knowledgeContext := ""
	if msg.AdminId > 0 {
		g.Log().Infof(ctx, "[AI-Flow] 开始 RAG 知识库召回流程: adminId=%d", msg.AdminId)

		// 1. 获取客服的个人知识库 ID
		kbId, err := service.Knowledge().GetAdminKnowledgeBaseId(ctx, msg.AdminId)
		if err != nil {
			g.Log().Warningf(ctx, "[AI-Flow] [RAG] 获取知识库 ID 失败: %+v", err)
		} else if kbId > 0 {
			g.Log().Infof(ctx, "[AI-Flow] [RAG] 找到知识库: kbId=%d", kbId)

			// 2. 调用 Python RAG 召回服务
			ragClient := ragclient.NewRagClient(ctx)
			topK := g.Cfg().MustGet(ctx, "rag.topK", 3).Int()
			g.Log().Infof(ctx, "[AI-Flow] [RAG] 准备调用 Python RAG 服务: question=%s, kbId=%d, topK=%d",
				msg.Content, kbId, topK)

			retrieveResp, err := ragClient.Retrieve(ctx, msg.Content, []int64{kbId}, topK)
			if err != nil {
				g.Log().Warningf(ctx, "[AI-Flow] [RAG] RAG 召回失败: %+v", err)
			} else if retrieveResp.Success && len(retrieveResp.Results) > 0 {
				// 3. 构建知识上下文 - 结构化格式
				var knowledgeParts []string
				for i, chunk := range retrieveResp.Results {
					knowledgeParts = append(knowledgeParts, fmt.Sprintf("[参考资料 %d] %s", i+1, chunk.Text))
				}
				knowledgeContext = strings.Join(knowledgeParts, "\n\n")
				g.Log().Infof(ctx, "[AI-Flow] [RAG] ✅ RAG 召回成功: 找到 %d 个相关文本块, 总长度 %d 字符, adminId=%d, kbId=%d",
					len(retrieveResp.Results), len(knowledgeContext), msg.AdminId, kbId)
			} else {
				g.Log().Infof(ctx, "[AI-Flow] [RAG] 未找到相关知识: adminId=%d, kbId=%d, response.Success=%v, resultsCount=%d",
					msg.AdminId, kbId, retrieveResp.Success, len(retrieveResp.Results))
			}
		} else {
			g.Log().Infof(ctx, "[AI-Flow] [RAG] 客服没有配置个人知识库: adminId=%d, kbId=%d", msg.AdminId, kbId)
		}
	} else {
		g.Log().Infof(ctx, "[AI-Flow] [RAG] 消息没有 adminId,跳过知识库召回: msgId=%d", msg.Id)
	}

	// 替换模板中的变量 (新增 {{knowledge}} 变量支持)
	g.Log().Infof(ctx, "[AI-Flow] 知识库上下文长度: %d 字符", len(knowledgeContext))
	g.Log().Infof(ctx, "[AI-Flow] 原始提示词模板内容: %s", promptTemplate)
	g.Log().Infof(ctx, "[AI-Flow] 提示词模板是否包含 {{knowledge}}: %v", strings.Contains(promptTemplate, "{{knowledge}}"))
	finalPromptTemplate := strings.ReplaceAll(promptTemplate, "{{knowledge}}", knowledgeContext)
	g.Log().Infof(ctx, "[AI-Flow] 最终提示词模板长度: %d 字符", len(finalPromptTemplate))
	if len(knowledgeContext) > 0 && len(finalPromptTemplate) > len(promptTemplate) {
		g.Log().Infof(ctx, "[AI-Flow] ✅ 知识库内容已成功注入到提示词中,注入了 %d 字符", len(knowledgeContext))
	} else if len(knowledgeContext) > 0 {
		g.Log().Warningf(ctx, "[AI-Flow] ⚠️ 知识库内容未被注入!原因:提示词模板中没有 {{knowledge}} 占位符")
	}


	// 调用 AI 生成回复（使用自定义提示词模板）
	g.Log().Infof(ctx, "[AI-Flow] 开始调用 AI 服务生成回复...")
	aiReply, err := service.Langchain().AskWithCustomPrompt(ctx, finalPromptTemplate, msg.Content, historyMessages)
	if err != nil {
		log.Errorf(ctx, "[AI-Flow] AI 生成回复失败: %+v", err)
		return
	}
	g.Log().Infof(ctx, "[AI-Flow] AI 生成回复成功: 回复长度=%d 字符", len(aiReply))

	// 如果 AI 没有返回回复（可能是禁用了），则跳过
	if aiReply == "" {
		g.Log().Infof(ctx, "[AI-Flow] AI 返回空回复,跳过")
		return
	}

	// 创建 AI 回复消息
	aiMessage := &model.CustomerChatMessage{}
	aiMessage.Type = consts.MessageTypeText
	aiMessage.Content = aiReply
	aiMessage.Source = consts.MessageSourceAi
	aiMessage.UserId = msg.UserId
	aiMessage.AdminId = msg.AdminId
	aiMessage.SessionId = session.Id
	aiMessage.CustomerId = msg.CustomerId
	aiMessage.ReceivedAt = gtime.Now()

	// 保存 AI 回复
	aiMessage, err = service.ChatMessage().Insert(ctx, aiMessage)
	if err != nil {
		log.Errorf(ctx, "[AI-Flow] 保存 AI 回复失败: %+v", err)
		return
	}
	g.Log().Infof(ctx, "[AI-Flow] AI 回复已保存: msgId=%d", aiMessage.Id)

	// 发送给用户
	err = s.deliveryMessage(ctx, aiMessage)
	if err != nil {
		log.Errorf(ctx, "[AI-Flow] 发送 AI 回复给用户失败: %+v", err)
		return
	}
	g.Log().Infof(ctx, "[AI-Flow] AI 回复已发送给用户")

	// 同时发送给客服（让客服看到 AI 的回复）
	err = adminM.deliveryMessage(ctx, aiMessage)
	if err != nil {
		log.Errorf(ctx, "[AI-Flow] 发送 AI 回复给客服失败: %+v", err)
	}
	g.Log().Infof(ctx, "[AI-Flow] ✅ triggerAiResponseForSession 完成")
}
