package ru.clevertec.util;

import com.github.javafaker.Faker;
import ru.clevertec.domain.Customer;
import ru.clevertec.domain.Order;
import ru.clevertec.domain.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static Customer generateRandomCustomer() {
        final int MIN_YEAR = 1920;
        final int MAX_YEAR = 2024;
        final int MIN_MONTH = 1;
        final int MAX_MONTH = 12;
        final int MIN_DAY = 1;
        final int MAX_DAY = 28;

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(generateRandomOrder());

        return Customer.builder()
                .id(UUID.randomUUID())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .dateBirth(LocalDate.of(faker.number().numberBetween(MIN_YEAR, MAX_YEAR),
                        faker.number().numberBetween(MIN_MONTH, MAX_MONTH),
                        faker.number().numberBetween(MIN_DAY, MAX_DAY)))
                .orders(orders)
                .build();
    }

    public static Order generateRandomOrder() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(generateRandomProduct());

        return Order.builder()
                .id(UUID.randomUUID())
                .createDate(OffsetDateTime.now())
                .products(products)
                .build();
    }

    public static Product generateRandomProduct() {
        final int MAX_NUMBER_OF_DECIMALS = 2;
        final int MIN_AMOUNT_OF_PRODUCT = 0;
        final int MAX_AMOUNT_OF_PRODUCT = 100;
        final int MIN_PRICE = 1;
        final int MAX_PRICE = 2000;

        Map<UUID, BigDecimal> mapOfIdToPrice = new HashMap<>();
        mapOfIdToPrice.put(UUID.randomUUID(), BigDecimal.valueOf(
                faker.number().randomDouble(MAX_NUMBER_OF_DECIMALS, MIN_AMOUNT_OF_PRODUCT, MAX_AMOUNT_OF_PRODUCT)));
        mapOfIdToPrice.put(UUID.randomUUID(), BigDecimal.valueOf(
                faker.number().randomDouble(MAX_NUMBER_OF_DECIMALS, MIN_AMOUNT_OF_PRODUCT, MAX_AMOUNT_OF_PRODUCT)));

        return Product.builder()
                .id(UUID.randomUUID())
                .name(faker.food().ingredient())
                .price(faker.number().randomDouble(MAX_NUMBER_OF_DECIMALS, MIN_PRICE, MAX_PRICE))
                .mapOfIdToPrice(mapOfIdToPrice)
                .build();
    }
}
