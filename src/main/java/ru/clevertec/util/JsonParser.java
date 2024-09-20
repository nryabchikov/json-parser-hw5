package ru.clevertec.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Map;

public class JsonParser {
    final String FORGING = "\"";
    final String COLON = ":";
    final String SPACE = " ";
    final String COMMA = ",";
    final String DASH = "-";
    final String DOT = ".";
    final String OPENING_SQUARE_BRACKET = "[";
    final String CLOSING_SQUARE_BRACKET = "]";
    final String OPENING_FIGURE_BRACKET = "{";
    final String CLOSING_FIGURE_BRACKET = "}";

    public StringBuilder toJson(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(OPENING_FIGURE_BRACKET);
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            sb.append(FORGING).append(field.getName()).append(FORGING).append(COLON);

            Object value = field.get(object);

            if (value instanceof Collection<?> collection) {
                sb.append(OPENING_SQUARE_BRACKET);

                for (Object elem : collection) {
                    sb.append(toJson(elem));
                    sb.append(CLOSING_FIGURE_BRACKET).append(COMMA);
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(CLOSING_SQUARE_BRACKET).append(COMMA);
            } else if (value instanceof Map<?, ?> map) {
                sb.append(OPENING_FIGURE_BRACKET);

                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    sb.append(FORGING).append(entry.getKey().toString()).append(FORGING).append(COLON);
                    appendSimpleObject(sb, entry.getValue());
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(CLOSING_FIGURE_BRACKET).append(COMMA);
            } else {
                appendSimpleObject(sb, value);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb;
    }

    private void appendOffsetDateTime(StringBuilder sb, OffsetDateTime date) {
        sb.append(FORGING).append(date.getYear()).append(DASH)
                .append(String.format("%02d", date.getMonthValue())).append(DASH)
                .append(String.format("%02d", date.getDayOfMonth())).append(SPACE)
                .append(String.format("%02d", date.getHour())).append(COLON)
                .append(String.format("%02d", date.getMinute())).append(COLON)
                .append(String.format("%02d", date.getSecond())).append(DOT)
                .append(date.getNano()).append(date.getOffset()).append(FORGING);
    }

    private void appendLocalDate(StringBuilder sb, LocalDate date) {
        sb.append(FORGING).append(date.getYear()).append(DASH)
                .append(String.format("%02d", date.getMonthValue())).append(DASH)
                .append(String.format("%02d", date.getDayOfMonth())).append(FORGING);
    }

    private void appendSimpleObject(StringBuilder sb, Object value) {
        if (value instanceof Number || value instanceof Boolean || value == null) {
            sb.append(value);
        } else if (value instanceof String || value instanceof Enum<?>) {
            sb.append(FORGING).append(value).append(FORGING);
        } else if (value instanceof LocalDate date) {
            appendLocalDate(sb, date);
        } else if (value instanceof OffsetDateTime date) {
            appendOffsetDateTime(sb, date);
        } else {
            sb.append(FORGING).append(value).append(FORGING);
        }

        sb.append(COMMA);
    }
}
