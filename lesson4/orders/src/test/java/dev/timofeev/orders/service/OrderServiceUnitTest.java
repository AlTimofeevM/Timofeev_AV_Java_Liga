package dev.timofeev.orders.service;

import dev.timofeev.orders.dao.OrderDao;
import dev.timofeev.orders.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

/**
 * Unit тесты для OrderService
 */
public class OrderServiceUnitTest {

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private OrderService orderService;

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

        Mockito.when(orderDao.createOrder(order)).thenReturn(1l);
        Assertions.assertEquals(1l, orderService.createOrder(order));
    }

    @Test
    @DisplayName("Сохранение заказа в БД")
    public void createOrderWithException() throws Exception {
        Order order = new Order();
        order.setName("order");
        order.setPrice(-12);
        order.setCustomerId(1l);

        Mockito.when(orderDao.createOrder(order)).thenReturn(1l);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            orderService.createOrder(order);
        });

        Assertions.assertEquals("Цена заказа меньше нуля", exception.getMessage());
    }
}
