package dev.timofeev.orders.config;

import dev.timofeev.orders.dao.OrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

@Configuration
@RequiredArgsConstructor
public class DaoConfig {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public OrderDao orderDao(){
        return new OrderDao(jdbcTemplate, new GeneratedKeyHolder());
    }
}
