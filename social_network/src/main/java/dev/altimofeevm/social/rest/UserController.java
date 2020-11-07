package dev.altimofeevm.social.rest;

import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;
import dev.altimofeevm.social.services.FriendService;
import dev.altimofeevm.social.services.UserService;
import dev.altimofeevm.social.services.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Контроллер для работы с пользователем
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final FriendService friendService;

    /**
     * Создать нового пользователя
     *
     * @param userDto данные пользователя в виде DTO
     * @return response объект с соответствующим статусом состояния
     */
    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody @Valid UserEditDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    /**
     * Получить пользователя по идентификатору
     *
     * @param id идентификатор пользователя
     * @return DTO пользователя
     */
    @GetMapping("{id}")
    public ResponseEntity <UserEditDto> findOne(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    /**
     * Обновить данные существующего пользователя
     *
     * @param id идентификатор пользователя
     * @param userDto данные пользователя в виде DTO
     * @return response объект с соответствующим статусом состояния
     */
    @PutMapping("{id}")
    public ResponseEntity<UUID> update(@PathVariable UUID id, @RequestBody @Valid UserEditDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    /**
     * Удалить пользователя
     *
     * @param id идентификатор пользователя
     * @return response объект с соответствующим статусом состояния
     */
    @DeleteMapping("{id}")
    public ResponseEntity<UUID> delete(@PathVariable UUID id) {
        friendService.findAll(id).stream().forEach(x -> friendService.delete(id, x.getId()));
        return ResponseEntity.ok(userService.delete(id));
    }

    /**
     * Получить страницу с пользователями
     *
     * @param filter фильтр
     * @param pageable настройки пагинации
     * @return страница с пользователями
     */
    @GetMapping
    public ResponseEntity<Page<UserByListDto>> findAll(UserFilter filter, Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(filter, pageable));
    }
}
