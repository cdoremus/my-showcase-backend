package org.cdoremus.myshowcase.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Used to deserialize a JSON representation of a {@link LocalDateTime}
 * into the {@link LocalDateTime} object.
 */
public class LocalDateTimeParseDeserializer extends StdDeserializer<LocalDateTime> {

    public LocalDateTimeParseDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(p.getValueAsString()); // or overloaded with an appropriate format
    }
}
