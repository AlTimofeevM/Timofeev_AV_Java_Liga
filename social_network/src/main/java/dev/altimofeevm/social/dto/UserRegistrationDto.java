package dev.altimofeevm.social.dto;

import dev.altimofeevm.social.utils.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserRegistrationDto {

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
     * Пол
     */
    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Возраст
     */
    @NonNull
    @Min(0)
    @Max(200)
    private Integer age;
}
