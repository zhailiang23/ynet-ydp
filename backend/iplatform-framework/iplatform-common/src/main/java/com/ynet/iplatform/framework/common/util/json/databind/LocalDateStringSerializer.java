package com.ynet.iplatform.framework.common.util.json.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate 序列化器,输出 ISO 格式的日期字符串 (yyyy-MM-dd)
 *
 * @author 易诚源码
 */
public class LocalDateStringSerializer extends JsonSerializer<LocalDate> {

    public static final LocalDateStringSerializer INSTANCE = new LocalDateStringSerializer();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(value.format(FORMATTER));
        }
    }

}
