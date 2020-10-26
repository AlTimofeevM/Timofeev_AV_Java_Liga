package dev.altimofeevm.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Конфигурационный класс для JPA
 */
public class JpaConfig {
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Возвращаем EntityManagerFactory, которая является синглтоном
     *
     * @return Фабрика entityManager
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("dev.altimofeevm.jpa");
        }

        return entityManagerFactory;
    }
}
