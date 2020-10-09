package dev.timofeev.orders.domain;

import lombok.Data;

/**
 * Клиент
 */
@Data
public class Customer {
    /**
     * Индификатор клиента
     */
    private Long id;
    /**
     * Имя клиента
     */
    private String name;
    /**
     * Электронный адрес клиента
     */
    private String emailAddress;
}
