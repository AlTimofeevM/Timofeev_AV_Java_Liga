package dev.timofeev.orders.controller;

import dev.timofeev.orders.domain.Order;
import dev.timofeev.orders.exception.NegativePriceException;
import dev.timofeev.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @throws NegativePriceException У заказа отрицательная цена
     */
    @PostMapping
    public String createOrder(@RequestBody Order order) throws NegativePriceException{
        Long id = orderService.createOrder(order);
        return  "{\"id\":" + id + "}";
    }

    /**
     * Возвращает пользователю ошибку 409
     */
    @ExceptionHandler(NegativePriceException.class)
    @ResponseStatus(value= HttpStatus.CONFLICT,
            reason="У заказа отрицательная цена")
    public void negativePriceExceptionHandler() {
    }
}
