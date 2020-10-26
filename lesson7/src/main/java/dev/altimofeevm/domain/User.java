package dev.altimofeevm.domain;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Класс-сущность для таблицы Usr
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usr")
public class User {

    /**
     *Первичный ключ таблицы
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество пользователя
     */
    @Column(name = "patronymic")
    private String patronymic;

    /**
     * Дата рождения пользователя
     */
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    /**
     * Информация о пользователе
     */
    @Column(name = "about")
    private String about;
}



