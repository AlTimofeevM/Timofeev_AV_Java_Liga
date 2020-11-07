package dev.altimofeevm.social.services;

import dev.altimofeevm.social.domain.User;
import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;
import dev.altimofeevm.social.repositories.UserRepository;
import dev.altimofeevm.social.services.filter.UserFilter;
import dev.altimofeevm.social.utils.Convert;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для работы с пользователем
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Получение всех пользователей
     *
     * @param filter фильтр
     * @param pageable пагинация
     * @return страница с пользователями
     */
    public Page<UserByListDto> findAll( UserFilter filter, Pageable pageable) {
        return userRepository
                .findAll(filter.toSpecification(), pageable)
                .map(Convert::toUserByListDto);
    }

    /**
     * Проверка данных пользователя и внесение их в БД
     *
     * @param userDto данные пользователя
     */
    @Transactional
    public UUID create(UserEditDto userDto) {
        User user = Convert.userEditDtoToUser(userDto, new User());
        user = userRepository.save(user);
        return user.getId();
    }

    /**
     * Поиск пользователя по идентификатору
     *
     * @param id идентификатор пользователя
     * @return DTO пользователя
     */
    public UserEditDto findOne(UUID id) {
        return userRepository.findById(id)
                .map(Convert::toUserEditDto)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    /**
     * Проверка данных пользователя и внесение их в БД
     *
     * @param id идентификатор пользователя
     * @param userDto данные пользователя
     */
    @Transactional
    public UUID update(UUID id, UserEditDto userDto) {
        User entity = Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        User user = Convert.userEditDtoToUser(userDto, entity);
        user = userRepository.save(user);

        return user.getId();
    }

    /**
     * Удаление пользователя по идентификатору
     *
     * @param id идентификатор  пользователя
     */
    @Transactional
    public UUID delete(UUID id) {
        Optional.ofNullable(id)
                .ifPresent(userRepository::deleteById);
        return id;
    }
}
