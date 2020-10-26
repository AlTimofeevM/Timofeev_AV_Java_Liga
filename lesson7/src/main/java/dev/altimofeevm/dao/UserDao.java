package dev.altimofeevm.dao;

import dev.altimofeevm.config.JpaConfig;
import dev.altimofeevm.domain.Message;
import dev.altimofeevm.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 */
public class UserDao {

    /**
     * Если id нет, то записывает пользователя в базу данных
     * Иначе перезаписывает уже имеющие данные в таблице
     *
     * @param user Пользователь, которого нужно сохранить в БД
     * @return Пользователь, которого записали в БД
     */
    public static User save(User user) throws Exception {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (user.getId() == null) {
                entityManager.persist(user);
            } else {
                user = entityManager.merge(user);
            }

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
        return user;
    }

    /**
     * Находит пользователя по индификатору
     *
     * @param userId Индификатор пользователя
     * @return Пользователь с индификатором userId
     */
    public static User get(Long userId) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        User user;
        try {
            entityManager.getTransaction().begin();

            user = entityManager.find(User.class, userId);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        return user;
    }

    /**
     * Находит пользователя по ФИО
     *
     * @param first_name имя пользователя
     * @param last_name фамилия пользователя
     * @param patronymic отчество пользователя
     * @return пользователь с данным ФИО
     */
    public static User get(String first_name, String last_name, String patronymic) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        User user;
        try {
            entityManager.getTransaction().begin();

            String query = "SELECT user FROM User user " +
                    "WHERE user.firstName = ?1 AND user.lastName = ?2 AND user.patronymic = ?3";

            user = entityManager.createQuery(query, User.class)
                    .setParameter(1, first_name)
                    .setParameter(2, last_name)
                    .setParameter(3, patronymic)
                    .getSingleResult();

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        return user;
    }

    /**
     * Удаляет пользователя по индификатору из БД
     *
     * @param userId Индификатор пользователя
     */
    public static void remove(Long userId) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            User user = entityManager.find(User.class, userId);

            entityManager.remove(user);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }

    /**
     * Возвращает все сообщения в диалоге между пользователями
     *
     * @param author  Один из пользователей
     * @param recipient Один из пользователей
     * @return Список сообщений
     */
    public static List<Message> getDialogBody(User author, User recipient) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        List<Message> dialog;
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
            Root<Message> root = query.from(Message.class);


            Predicate authorEquals1 = criteriaBuilder.equal(root.get("author"), author);
            Predicate recipientEquals1 = criteriaBuilder.equal(root.get("recipient"), recipient);
            Predicate equals1 = criteriaBuilder.and(authorEquals1, recipientEquals1);

            Predicate authorEquals2 = criteriaBuilder.equal(root.get("author"), recipient);
            Predicate recipientEquals2 = criteriaBuilder.equal(root.get("recipient"), author);
            Predicate equals2 = criteriaBuilder.and(authorEquals2, recipientEquals2);

            Predicate result = criteriaBuilder.or(equals1,equals2);

            query.select(root).where(result);

            dialog = entityManager.createQuery(query).getResultList();

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        return dialog;
    }

    /**
     * Возвращает списиок пользовалей с которыми есть диалог
     *
     * @param user Пользователь
     * @return Список пользователей
     */
    public static  List<User> getDialogs(User user) {
        EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
        List<User> dialogs;
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<Message> root = query.from(Message.class);

            Predicate asAuthor = criteriaBuilder.equal(root.get("author"), user);
            Predicate asRecipient= criteriaBuilder.equal(root.get("recipient"), user);
            Predicate result = criteriaBuilder.or(asAuthor,asRecipient);

            query.select(
                    criteriaBuilder.<User>selectCase()
                    .when(criteriaBuilder.equal(root.get("author"), user), root.get("recipient"))
                    .otherwise(root.get("author"))
            ).where(result)
            .distinct(true);

            dialogs = entityManager.createQuery(query).getResultList();

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        return dialogs;
    }
}
