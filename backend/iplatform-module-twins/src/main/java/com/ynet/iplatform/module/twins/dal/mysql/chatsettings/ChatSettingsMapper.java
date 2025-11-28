package com.ynet.iplatform.module.twins.dal.mysql.chatsettings;

import com.ynet.iplatform.framework.mybatis.core.mapper.BaseMapperX;
import com.ynet.iplatform.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ynet.iplatform.module.twins.dal.dataobject.chatsettings.ChatSettingsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 聊天设置 Mapper
 */
@Mapper
public interface ChatSettingsMapper extends BaseMapperX<ChatSettingsDO> {

    default ChatSettingsDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapperX<ChatSettingsDO>()
                .eq(ChatSettingsDO::getName, name));
    }

}
