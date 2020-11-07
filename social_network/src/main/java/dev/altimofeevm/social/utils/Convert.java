package dev.altimofeevm.social.utils;

import dev.altimofeevm.social.domain.User;
import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;

public class Convert {
    /**
     * Преобразование сущности {@link User} в DTO
     *
     * @param user пользователь
     * @return DTO
     */
    public static UserEditDto toUserEditDto(User user) {
        UserEditDto dto = new UserEditDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAge(user.getAge());
        dto.setGender(user.getGender());
        dto.setInterests(user.getInterests());
        dto.setCity(user.getCity());
        return dto;
    }

    /**
     * Преобразование DTO-сущности в {@link User}
     *
     * @param dto данные о пользователе
     * @return пользователь
     */
    public static User userEditDtoToUser(UserEditDto dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setInterests(dto.getInterests());
        entity.setCity(dto.getCity());
        return entity;
    }

    /**
     * Преобразование сущности {@link User} в DTO
     *
     * @param user пользователь
     * @return DTO
     */
    public static UserByListDto toUserByListDto(User user) {
        UserByListDto dto = new UserByListDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
