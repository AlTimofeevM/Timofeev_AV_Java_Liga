package dev.altimofeevm.social.dto;

import dev.altimofeevm.social.utils.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * DTO данных пользователя
 */
@Data
@NoArgsConstructor
public class UserEditDto {

    /**
     * Индификатор пользователя
     */
    private UUID id;

    /**
     * Логин
     */
    @NotEmpty
    private String login;

    /**
     * Имя
     */
    @NotEmpty
    @Length(max = 128)
    private String firstName;

    /**
     * Фамилия
     */
    @NotEmpty
    @Length(max = 128)
    private String lastName;

    /**
     * Возраст
     */
    @NonNull
    @Min(0)
    @Max(200)
    private Integer age;

    /**
     * Пол
     */
    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Интересы
     */
    @NonNull
    private String interests;

    /**
     * Город
     */
    @NotEmpty
    @Length(max = 40)
    private String city;
}
