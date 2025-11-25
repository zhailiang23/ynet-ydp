package com.ynet.iplatform.module.aicrm.dal.dataobject.customerchannelbehavior;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户渠道行为信息 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_channel_behavior")
@KeySequence("crm_customer_channel_behavior_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerChannelBehaviorDO extends BaseDO {

    /**
     * 行为主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 操作时间
     */
    private LocalDateTime operationTime;
    /**
     * 操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）
     */
    private String operationAction;
    /**
     * 操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）
     */
    private String operationObject;
    /**
     * 当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）
     */
    private String currentPageIdentifier;
    /**
     * 当前页面名称（页面code，如：index、loan、my_page、act_details）
     */
    private String currentPageCode;
    /**
     * 当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）
     */
    private String currentPageName;
    /**
     * 页面停留时间(S)
     */
    private Integer pageStaySeconds;
    /**
     * 说明
     */
    private String description;
    /**
     * 行为编号
     */
    private String behaviorNo;
    /**
     * 渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）
     */
    private String channelType;
    /**
     * 设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）
     */
    private String deviceType;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备型号
     */
    private String deviceModel;
    /**
     * APP版本号
     */
    private String appVersion;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * 浏览器类型
     */
    private String browserType;
    /**
     * 浏览器版本
     */
    private String browserVersion;
    /**
     * IP地址
     */
    private String ipAddress;
    /**
     * IP归属地
     */
    private String ipLocation;
    /**
     * 网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）
     */
    private String networkType;
    /**
     * 会话ID
     */
    private String sessionId;
    /**
     * 会话开始时间
     */
    private LocalDateTime sessionStartTime;
    /**
     * 会话结束时间
     */
    private LocalDateTime sessionEndTime;
    /**
     * 上一页面标识
     */
    private String previousPageCode;
    /**
     * 上一页面名称
     */
    private String previousPageName;
    /**
     * 下一页面标识
     */
    private String nextPageCode;
    /**
     * 下一页面名称
     */
    private String nextPageName;
    /**
     * 页面URL
     */
    private String pageUrl;
    /**
     * 页面标题
     */
    private String pageTitle;
    /**
     * 页面参数（JSON格式）
     */
    private String pageParams;
    /**
     * 操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）
     */
    private String operationResult;
    /**
     * 操作详情
     */
    private String operationDetail;
    /**
     * 业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）
     */
    private String businessType;
    /**
     * 业务模块
     */
    private String businessModule;
    /**
     * 业务功能
     */
    private String businessFunction;
    /**
     * 是否转化（是否产生业务）
     */
    private Boolean isConversion;
    /**
     * 转化类型（字典: aicrm_conversion_type）
     */
    private String conversionType;
    /**
     * 转化价值
     */
    private BigDecimal conversionValue;
    /**
     * 来源（外部链接、推广活动等）
     */
    private String referSource;
    /**
     * 媒介
     */
    private String referMedium;
    /**
     * 推广活动
     */
    private String referCampaign;
    /**
     * User Agent
     */
    private String userAgent;
    /**
     * 屏幕宽度
     */
    private Integer screenWidth;
    /**
     * 屏幕高度
     */
    private Integer screenHeight;
    /**
     * 视口宽度
     */
    private Integer viewportWidth;
    /**
     * 视口高度
     */
    private Integer viewportHeight;
    /**
     * 是否新访客
     */
    private Boolean isNewVisitor;
    /**
     * 访问次数
     */
    private Integer visitCount;
    /**
     * 跳出率（%）
     */
    private BigDecimal bounceRate;
    /**
     * 事件分类
     */
    private String eventCategory;
    /**
     * 事件标签
     */
    private String eventLabel;
    /**
     * 事件值
     */
    private String eventValue;
    /**
     * 备注
     */
    private String remark;


}