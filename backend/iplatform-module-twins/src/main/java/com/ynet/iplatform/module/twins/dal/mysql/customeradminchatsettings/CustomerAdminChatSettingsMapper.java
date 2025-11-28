package com.ynet.iplatform.module.twins.dal.mysql.customeradminchatsettings;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.module.twins.dal.dataobject.customeradminchatsettings.CustomerAdminChatSettingsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 客服聊天设置 Mapper
 */
@Mapper
public interface CustomerAdminChatSettingsMapper extends BaseMapperX<CustomerAdminChatSettingsDO> {

    /**
     * 统计启用 AI 的客服数量
     */
    @Select("SELECT COUNT(*) FROM customer_admin_chat_settings WHERE is_ai_enabled = 1 AND deleted_at IS NULL")
    Long countActiveAi();

}
