package dev.timofeev.orders.controller;

import dev.timofeev.orders.domain.Order;
import dev.timofeev.orders.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit тесты для OrderController
 */
public class OrderControllerUnitTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Сохранение заказа")
    public void createOrder() throws Exception {
        Order order = new Order();
        order.setName("order");
        order.setPrice(12);
        order.setCustomerId(1l);

        Mockito.when(orderService.createOrder(order)).thenReturn(1l);
        Assertions.assertEquals("{\"id\":1}", orderController.createOrder(order));
    }
}
