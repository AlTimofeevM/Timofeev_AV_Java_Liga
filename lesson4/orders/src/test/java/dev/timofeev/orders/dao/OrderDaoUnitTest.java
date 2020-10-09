package dev.timofeev.orders.dao;

import dev.timofeev.orders.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Unit тесты для OrderDao
 */
public class OrderDaoUnitTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrderDao orderDao;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    @DisplayName("Сохранение заказа в БД")
//    public void createOrder() throws Exception {
//        Order order = new Order();
//        order.setName("order");
//        order.setPrice(12);
//        order.setCustomerId(1l);
//
//        Assertions.assertEquals(1l, orderDao.createOrder(order));
//    }

}
