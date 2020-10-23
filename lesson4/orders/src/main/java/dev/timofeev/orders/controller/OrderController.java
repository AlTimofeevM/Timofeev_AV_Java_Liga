package dev.timofeev.orders.controller;

import dev.timofeev.orders.domain.Order;
import dev.timofeev.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер для работы с заказами
 */
@RestController
@RequestMapping(value="/api/v1/order")
public class OrderController {

    /**
     * Сервис для работы с заказами
     */
    @Autowired
    private OrderService orderService;

    /**
     * Сохраняет сделанный заказ
     *
     * @param order сделанный заказ
     * @return JSON вида {"id": ID}, где ID индификатор сделанного заказа
     */
    @PostMapping
    public String createOrder(@RequestBody @Valid Order order) {
        Long id = orderService.createOrder(order);
        return  "{\"id\":" + id + "}";
    }
}
