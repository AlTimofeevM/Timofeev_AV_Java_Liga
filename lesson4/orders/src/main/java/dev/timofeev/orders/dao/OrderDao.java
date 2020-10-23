package dev.timofeev.orders.dao;

import dev.timofeev.orders.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;

/**
 *  DAO для работы с заказами
 */
@RequiredArgsConstructor
public class OrderDao {

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder;

    private final String INSERT_SQL = "INSERT INTO Orders " +
            "(order_name," +
            " price," +
            " customer_id) VALUES (?,?,?)";

    /**
     * Занесение в БД данных о сделанном заказе
     *
     * @param order сделанный заказ
     * @return записанный заказа
     */
    public Order createOrder(Order order) {
        int updatedRows = jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
                    preparedStatement.setString(1, order.getName());
                    preparedStatement.setInt(2, order.getPrice());
                    preparedStatement.setLong(3, order.getCustomerId());
                    return preparedStatement;
                },
                keyHolder
        );
        if (updatedRows > 0) {
            order.setId(keyHolder.getKey().longValue());
        }
        return order;
    }
}
