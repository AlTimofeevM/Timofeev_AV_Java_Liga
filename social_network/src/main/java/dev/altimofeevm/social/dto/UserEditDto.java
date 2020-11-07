package dev.altimofeevm.social.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

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
    private Character gender;

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
