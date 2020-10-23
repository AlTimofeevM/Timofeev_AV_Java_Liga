package dev.timofeev.orders.dao;


import dev.timofeev.orders.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit тесты для OrderDao
 */
public class OrderDaoUnitTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private GeneratedKeyHolder keyHolder;

    @InjectMocks
    private OrderDao orderDao;


    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        OrderDao orderDao = new OrderDao(jdbcTemplate, keyHolder);
    }


    @Test
    @DisplayName("Сохранение заказа в БД")
    public void createOrder() throws Exception {
        Order order = new Order(null, "order", 12, 1l);
        Order result = new Order(1l, "order", 12, 1l);

        Mockito.when(jdbcTemplate.update(any(PreparedStatementCreator.class), any(KeyHolder.class)))
                .thenReturn(1);

        Mockito.when(keyHolder.getKey())
                .thenReturn(1);

        Assertions.assertEquals(result,orderDao.createOrder(order));
        verify(jdbcTemplate,
                times(1)).update(any(PreparedStatementCreator.class), any(KeyHolder.class));
        verify(keyHolder, times(1)).getKey();
    }

}
