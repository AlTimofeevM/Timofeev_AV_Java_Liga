package dev.timofeev.orders.dao;


import dev.timofeev.orders.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Unit тесты для OrderDao
 */
@JdbcTest
@Sql({"../../../../db_schema.sql"})
public class OrderDaoUnitTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private OrderDao orderDao;


    @BeforeEach
    public void before() {
        orderDao = new OrderDao();
        ReflectionTestUtils.setField(orderDao, "jdbcTemplate", jdbcTemplate);
    }


    @Test
    @DisplayName("Сохранение заказа в БД")
    public void createOrder() throws Exception {
        Order order = new Order();
        order.setName("order");
        order.setPrice(12);
        order.setCustomerId(1l);

        Assertions.assertEquals(1, orderDao.createOrder(order));
    }

}
