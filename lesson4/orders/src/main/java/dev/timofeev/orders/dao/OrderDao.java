package dev.timofeev.orders.dao;

import dev.timofeev.orders.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;

/**
 *  DAO для работы с заказами
 */
@Repository
public class OrderDao {

    /**
     * JDBC шаблон работы с БД
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Занесение в БД данных о сделанном заказе
     *
     * @param order сделанный заказ
     * @return индификатор записанного заказа
     */
    public Long createOrder(Order order) {
        final String INSERT_SQL = "INSERT INTO Orders " +
                                "(order_name," +
                                " price," +
                                " customer_id) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
                    preparedStatement.setString(1, order.getName());
                    preparedStatement.setInt(2, order.getPrice());
                    preparedStatement.setLong(3, order.getCustomerId());
                    return preparedStatement;
                },
                keyHolder
        );
        return keyHolder.getKey().longValue();
    }
}
