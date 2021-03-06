package com.inmaytide.orbit.commons.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.inmaytide.orbit.constant.Constants;
import com.inmaytide.orbit.util.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Moss
 * @since December 17, 2017
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DateTimeUtils.format(value, Constants.PATTERN_DEFAULT_DATETIME));
    }

}
