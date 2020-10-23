package dev.timofeev.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Заказ
 */
@Data
@AllArgsConstructor
public class Order {
    /**
     * Индификатор заказа
     */
    private Long id;
    /**
     * Наименование заказа
     */
    @NotBlank
    private String name;
    /**
     * Цена заказа
     */
    @NonNull
    @Min(0)
    private Integer price;
    /**
     * Индификатор пользователя, сделавшего заказ
     */
    @NonNull
    private Long customerId;
}
