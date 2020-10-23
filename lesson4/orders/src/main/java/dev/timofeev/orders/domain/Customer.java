package dev.timofeev.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.*;

/**
 * Клиент
 */
@Data
@AllArgsConstructor
public class Customer {
    /**
     * Индификатор клиента
     */
    private Long id;
    /**
     * Имя клиента
     */
    @NotBlank
    private String name;
    /**
     * Электронный адрес клиента
     */
    @NonNull
    @Email
    private String emailAddress;
}
