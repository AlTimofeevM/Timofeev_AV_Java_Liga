package dev.altimofeevm.social.services;

import dev.altimofeevm.social.domain.User;
import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;
import dev.altimofeevm.social.repositories.UserRepository;
import dev.altimofeevm.social.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для работы с друзьями пользователя
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {

    private final UserRepository userRepository;

    /**
     * Получение всех друзей пользователя
     *
     * @param id идентификатор пользователя
     * @return список друзей пользователя
     */
    public List<UserByListDto> findAll(UUID id) {
        User user = Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return user.getFriends().stream().map(Convert::toUserByListDto).collect(Collectors.toList());
    }

    /**
     * Внесение в БД отношения между пользователем и другом
     *
     * @param id идентификатор пользователя
     * @param friendId идентификатор друга
     */
    @Transactional
    public UUID create(UUID id, UUID friendId) {
        if(id.equals(friendId)) {
            throw new RuntimeException("Нельзя добавить в друзья самого себя");
        }
        User user = Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        User friend = Optional.ofNullable(friendId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if(user.getFriends().contains(friend)) {
            throw new RuntimeException("Данный пользователь уже является другом");
        }
        user.getFriends().add(friend);
        friend.getFriends().add(user);

        userRepository.save(friend);
        user = userRepository.save(user);

        return user.getId();
    }

    /**
     * Удаления друга по идентификатору
     *
     * @param id идентификатор пользователя
     * @param friendId идентификатор друга
     */
    @Transactional
    public UUID delete(UUID id, UUID friendId) {
        User user = Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        User friend = Optional.ofNullable(friendId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        user.getFriends().remove(friend);
        friend.getFriends().remove(user);

        return user.getId();
    }
}
