package ru.clevertec;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.clevertec.config.JacksonConfig;
import ru.clevertec.domain.Customer;
import ru.clevertec.util.DataGenerator;
import ru.clevertec.util.JsonDeserializer;
import ru.clevertec.util.JsonSerializer;
import ru.clevertec.util.io.FileReader;
import ru.clevertec.util.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        final String FILEPATH_TO_CUSTOM_OUTPUT = "src/main/resources/custom.json";
        final String FILEPATH_TO_JACKSON_OUTPUT = "src/main/resources/jackson.json";

        ObjectMapper objectMapper = JacksonConfig.objectMapper();
        Customer customer = DataGenerator.generateRandomCustomer();
        JsonSerializer serializer = new JsonSerializer();
        JsonDeserializer deserializer = new JsonDeserializer();

        StringBuilder sb = serializer.toJson(customer);
        sb.append("}");
        FileWriter.writeToFile(FILEPATH_TO_CUSTOM_OUTPUT, sb.toString());
        FileWriter.writeToFile(FILEPATH_TO_JACKSON_OUTPUT, objectMapper.writeValueAsString(customer));

        String json = FileReader.readFromFile(FILEPATH_TO_CUSTOM_OUTPUT);

        Customer resultCustomer = deserializer.fromJson(json, Customer.class);
        Customer jacksonCustomer = objectMapper.readValue(json, Customer.class);

        System.out.println("myCustomer after toJson and from json transformation: " + resultCustomer);
        System.out.println("jacksonCustomer after toJson and from json transformation: " + jacksonCustomer);
        System.out.println("Random input customer equals to  myCustomer after toJson and from json transformation: "
                + customer.equals(resultCustomer));
        System.out.println("jacksonCustomer after toJson and from json transformation equals to " +
                "myCustomer after toJson and from json transformation: " + jacksonCustomer.equals(resultCustomer));
    }
}
