package dev.timofeev.orders.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;

/**
 * Конфигурация базы данных
 */
@Configuration
public class DatabaseConfig {

    @Value("${db.schema_file}")
    private String schemaSql;

    /**
     * Подключение базы данных H2 и исполнение скриптов для создания табилиц
     * 
     * @return DataSource соединение с физичиским источником данных
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                .addScript(schemaSql)
                .build();
        return db;
    }

    /**
     * Создание шаблона работы с базой данных
     *
     * @return JdbcTablate шаблон работы
     */
    @Bean
    public JdbcTemplate createJdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }
}
