package ru.clevertec.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.clevertec.util.jackson.deserializer.LocalDateTimeDeserializer;
import ru.clevertec.util.jackson.deserializer.OffsetDateTimeDeserializer;
import ru.clevertec.util.jackson.serializer.LocalDateSerializer;
import ru.clevertec.util.jackson.serializer.OffsetDateTimeSerializer;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class JacksonConfig {

    public static ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer());
        module.addDeserializer(LocalDate.class, new LocalDateTimeDeserializer());
        module.addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
