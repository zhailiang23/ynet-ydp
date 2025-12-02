package com.ynet.iplatform.framework.common.util.json.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate 反序列化器,解析 ISO 格式的日期字符串 (yyyy-MM-dd)
 *
 * @author 易诚源码
 */
public class LocalDateStringDeserializer extends JsonDeserializer<LocalDate> {

    public static final LocalDateStringDeserializer INSTANCE = new LocalDateStringDeserializer();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();
        if (text == null || text.isEmpty()) {
            return null;
        }
        return LocalDate.parse(text, FORMATTER);
    }

}
