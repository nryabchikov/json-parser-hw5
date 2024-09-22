package ru.clevertec.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.clevertec.config.JacksonConfig;
import ru.clevertec.domain.Customer;
import ru.clevertec.domain.Order;
import ru.clevertec.domain.Product;
import ru.clevertec.exception.DefaultConstructorNotFoundException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonParserTest {
    private static JsonParser jsonParser;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        jsonParser = new JsonParser();
        objectMapper = JacksonConfig.objectMapper();
    }

    @Test
    void shouldConvertProductToJson() throws IllegalAccessException, JsonProcessingException {
        //given
        Product product = DataGenerator.generateRandomProduct();
        String jsonCustom = jsonParser.toJson(product).append("}").toString();

        //when
        String jsonJackson = objectMapper.writeValueAsString(product);

        //then
        assertEquals(jsonCustom, jsonJackson);
    }

    @Test
    void shouldConvertOrderToJson() throws IllegalAccessException, JsonProcessingException {
        //given
        Order order = DataGenerator.generateRandomOrder();
        String jsonCustom = jsonParser.toJson(order).append("}").toString();

        //when
        String jsonJackson = objectMapper.writeValueAsString(order);

        //then
        assertEquals(jsonCustom, jsonJackson);
    }

    @Test
    void shouldConvertCustomerToJson() throws IllegalAccessException, JsonProcessingException {
        //given
        Customer customer = DataGenerator.generateRandomCustomer();
        String jsonCustom = jsonParser.toJson(customer).append("}").toString();

        //when
        String jsonJackson = objectMapper.writeValueAsString(customer);

        //then
        assertEquals(jsonCustom, jsonJackson);
    }

    @Test
    void shouldGetProductFromJson() throws Exception {
        //given
        Product product = DataGenerator.generateRandomProduct();
        String json = objectMapper.writeValueAsString(product);

        //when
        Product parsedProduct = jsonParser.fromJson(json, Product.class);

        //then
        assertEquals(product, parsedProduct);
    }

    @Test
    void shouldGetOrderFromJson() throws Exception {
        //given
        Order order = DataGenerator.generateRandomOrder();
        String json = objectMapper.writeValueAsString(order);

        //when
        Order parsedOrder = jsonParser.fromJson(json, Order.class);

        //then
        assertEquals(order, parsedOrder);
    }

    @Test
    void shouldGetCustomerFromJson() throws Exception {
        //given
        Customer customer = DataGenerator.generateRandomCustomer();
        String json = objectMapper.writeValueAsString(customer);

        //when
        Customer parsedCustomer = jsonParser.fromJson(json, Customer.class);

        //then
        assertEquals(customer, parsedCustomer);
    }

    @Test
    void shouldNotGetCustomerFromJson_whenDefaultConstructorNotFound() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        String json = objectMapper.writeValueAsString(id);

        //when, then
        assertThrows(
                DefaultConstructorNotFoundException.class,
                () -> jsonParser.fromJson(json, UUID.class)
        );
    }
}