package ru.clevertec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.clevertec.config.JacksonConfig;
import ru.clevertec.domain.Customer;
import ru.clevertec.util.DataGenerator;
import ru.clevertec.util.FileWriter;
import ru.clevertec.util.JsonParser;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, JsonProcessingException {
        final String FILEPATH_TO_CUSTOM_OUTPUT = "src/main/resources/custom.json";
        final String FILEPATH_TO_JACKSON_OUTPUT = "src/main/resources/jackson.json";

        ObjectMapper objectMapper = JacksonConfig.objectMapper();
        Customer customer = DataGenerator.generateRandomCustomer();
        JsonParser parser = new JsonParser();

        StringBuilder sb = parser.toJson(customer);
        sb.deleteCharAt(sb.length() - 1).append("}");
        FileWriter.writeToFile(FILEPATH_TO_CUSTOM_OUTPUT, sb.toString());
        FileWriter.writeToFile(FILEPATH_TO_JACKSON_OUTPUT, objectMapper.writeValueAsString(customer));
    }
}
