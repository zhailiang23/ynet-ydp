package com.ynet.iplatform.module.aicrm.controller.admin.customerchannelbehavior.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户渠道行为信息分页 Request VO")
@Data
public class CustomerChannelBehaviorPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "20536")
    private Long customerId;

    @Schema(description = "操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] operationTime;

    @Schema(description = "操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）")
    private String operationAction;

    @Schema(description = "操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）")
    private String operationObject;

    @Schema(description = "当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）")
    private String currentPageIdentifier;

    @Schema(description = "当前页面名称（页面code，如：index、loan、my_page、act_details）")
    private String currentPageCode;

    @Schema(description = "当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）", example = "王五")
    private String currentPageName;

    @Schema(description = "页面停留时间(S)")
    private Integer pageStaySeconds;

    @Schema(description = "说明", example = "随便")
    private String description;

    @Schema(description = "行为编号")
    private String behaviorNo;

    @Schema(description = "渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）", example = "1")
    private String channelType;

    @Schema(description = "设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）", example = "2")
    private String deviceType;

    @Schema(description = "设备ID", example = "10987")
    private String deviceId;

    @Schema(description = "设备型号")
    private String deviceModel;

    @Schema(description = "APP版本号")
    private String appVersion;

    @Schema(description = "操作系统版本")
    private String osVersion;

    @Schema(description = "浏览器类型", example = "1")
    private String browserType;

    @Schema(description = "浏览器版本")
    private String browserVersion;

    @Schema(description = "IP地址")
    private String ipAddress;

    @Schema(description = "IP归属地")
    private String ipLocation;

    @Schema(description = "网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）", example = "2")
    private String networkType;

    @Schema(description = "会话ID", example = "11858")
    private String sessionId;

    @Schema(description = "会话开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sessionStartTime;

    @Schema(description = "会话结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sessionEndTime;

    @Schema(description = "上一页面标识")
    private String previousPageCode;

    @Schema(description = "上一页面名称", example = "赵六")
    private String previousPageName;

    @Schema(description = "下一页面标识")
    private String nextPageCode;

    @Schema(description = "下一页面名称", example = "赵六")
    private String nextPageName;

    @Schema(description = "页面URL", example = "https://www.iocoder.cn")
    private String pageUrl;

    @Schema(description = "页面标题")
    private String pageTitle;

    @Schema(description = "页面参数（JSON格式）")
    private String pageParams;

    @Schema(description = "操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）")
    private String operationResult;

    @Schema(description = "操作详情")
    private String operationDetail;

    @Schema(description = "业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）", example = "2")
    private String businessType;

    @Schema(description = "业务模块")
    private String businessModule;

    @Schema(description = "业务功能")
    private String businessFunction;

    @Schema(description = "是否转化（是否产生业务）")
    private Boolean isConversion;

    @Schema(description = "转化类型（字典: aicrm_conversion_type）", example = "2")
    private String conversionType;

    @Schema(description = "转化价值")
    private BigDecimal conversionValue;

    @Schema(description = "来源（外部链接、推广活动等）")
    private String referSource;

    @Schema(description = "媒介")
    private String referMedium;

    @Schema(description = "推广活动")
    private String referCampaign;

    @Schema(description = "User Agent")
    private String userAgent;

    @Schema(description = "屏幕宽度")
    private Integer screenWidth;

    @Schema(description = "屏幕高度")
    private Integer screenHeight;

    @Schema(description = "视口宽度")
    private Integer viewportWidth;

    @Schema(description = "视口高度")
    private Integer viewportHeight;

    @Schema(description = "是否新访客")
    private Boolean isNewVisitor;

    @Schema(description = "访问次数", example = "19723")
    private Integer visitCount;

    @Schema(description = "跳出率（%）")
    private BigDecimal bounceRate;

    @Schema(description = "事件分类")
    private String eventCategory;

    @Schema(description = "事件标签")
    private String eventLabel;

    @Schema(description = "事件值")
    private String eventValue;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}