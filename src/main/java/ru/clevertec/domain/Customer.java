package ru.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private List<Order> orders;
}

