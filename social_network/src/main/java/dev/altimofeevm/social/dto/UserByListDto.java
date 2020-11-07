package dev.altimofeevm.social.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO данных пользователя из списка
 */
@Data
@NoArgsConstructor
public class UserByListDto {

    /**
     * Идентификатор пользователя
     */
    private UUID id;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

}
