package dev.altimofeevm.dao;

import dev.altimofeevm.config.JpaConfig;
import dev.altimofeevm.domain.Message;

import javax.persistence.EntityManager;

public class MessageDao {

    /**
     * Если id нет, то записывает сообщение в базу данных
     * Иначе перезаписывает уже имеющие данные в таблице
     *
     * @param message Сообщение, которое нужно созранить в БД
     * @return Сообщение, которое записали в БД
     */
    public static Message save(Message message) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (message.getId() == null) {
                entityManager.persist(message);
            } else {
                message = entityManager.merge(message);
            }

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        return message;
    }

    /**
     * Находит сообщение по индификатору
     *
     * @param messageId индификатор сообщения
     * @return Сообщение с индификатором messageId
     */
    public static Message get(Long messageId) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        Message message;
        try {
            entityManager.getTransaction().begin();

            message = entityManager.find(Message.class, messageId);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        return message;
    }

    /**
     * Удаляет сообщение по индификатору из БД
     *
     * @param messageId индификатор сообщения
     */
    public static void remove(Long messageId) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Message message = entityManager.find(Message.class, messageId);

            entityManager.remove(message);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }

}
