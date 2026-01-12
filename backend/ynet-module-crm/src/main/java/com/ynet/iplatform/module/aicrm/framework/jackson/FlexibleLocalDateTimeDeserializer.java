package com.ynet.iplatform.module.aicrm.framework.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 灵活的 LocalDateTime 反序列化器
 * 支持两种格式:
 * 1. 时间戳（毫秒）
 * 2. 字符串格式 "yyyy-MM-dd HH:mm:ss"
 *
 * @author Claude
 */
public class FlexibleLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    public static final FlexibleLocalDateTimeDeserializer INSTANCE = new FlexibleLocalDateTimeDeserializer();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken token = p.currentToken();

        // 如果是数字，按时间戳处理
        if (token == JsonToken.VALUE_NUMBER_INT) {
            long timestamp = p.getValueAsLong();
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        }

        // 如果是字符串，按格式解析
        if (token == JsonToken.VALUE_STRING) {
            String str = p.getValueAsString();
            if (str == null || str.trim().isEmpty()) {
                return null;
            }
            return LocalDateTime.parse(str, FORMATTER);
        }

        // 其他情况返回 null
        return null;
    }
}
