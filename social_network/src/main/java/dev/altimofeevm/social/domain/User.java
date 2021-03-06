package dev.altimofeevm.social.domain;

import dev.altimofeevm.social.utils.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

/**
 * Пользователь
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    /**
     * Индификатор пользователя
     */
    @Id
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "dev.altimofeevm.social.generator.UUIDCustomGenerator")
    @Type(type = "uuid-char")
    @Column(name = "ID", nullable = false, length = 36, unique = true)
    private UUID id;

    /**
     * Логин
     */
    @Column(name = "LOGIN", nullable = false)
    @NotEmpty
    private String login;

    /**
     * Имя
     */
    @Column(name = "FIRST_NAME", nullable = false)
    @NotEmpty
    @Length(max = 128)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "LAST_NAME", nullable = false)
    @NotEmpty
    @Length(max = 128)
    private String lastName;

    /**
     * Возраст
     */
    @Column(name = "AGE", nullable = false)
    @NonNull
    private Integer age;

    /**
     * Пол
     */
    @Column(name = "GENDER", nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Интересы
     */
    @Column(name = "INTERESTS", nullable = false)
    @NonNull
    private String interests;

    /**
     * Город
     */
    @Column(name = "CITY", nullable = false)
    @NotEmpty
    @Length(max = 40)
    private String city;

    /**
     * Список друзей
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="RELATIONSHIP",
            joinColumns = @JoinColumn(name="RELATING_USER_ID"),
            inverseJoinColumns = @JoinColumn(name="RELATED_USER_ID")
    )
    private List<User> friends;
}
