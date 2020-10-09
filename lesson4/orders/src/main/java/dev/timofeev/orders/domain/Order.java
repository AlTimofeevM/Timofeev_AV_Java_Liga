package dev.timofeev.orders.domain;

import lombok.Data;

/**
 * Заказ
 */
@Data
public class Order {
    /**
     * Индификатор заказа
     */
    private Long id;
    /**
     * Наименование заказа
     */
    private String name;
    /**
     * Цена заказа
     */
    private Integer price;
    /**
     * Индификатор пользователя, сделавшего заказ
     */
    private Long customerId;
}
