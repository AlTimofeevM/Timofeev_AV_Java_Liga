package dev.altimofeevm.social.rest;

import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.services.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с друзьями пользователя
 */
@RestController
@RequestMapping("users/{id}/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    /**
     * Получить друзей пользователя
     *
     * @param id идентификатор пользователя
     * @return список друзей пользователя
     */
    @GetMapping
    public ResponseEntity<List<UserByListDto>> findAll(@PathVariable UUID id) {
        return ResponseEntity.ok(friendService.findAll(id));
    }

    /**
     * Добавить друга пользователю
     *
     * @param id идентификатор пользователя
     * @param friendId идентификатор друга
     * @return response объект с соответствующим статусом состояния
     */
    @PostMapping("{friendId}")
    public ResponseEntity<UUID> create(@PathVariable UUID id,
                                       @PathVariable UUID friendId) {
        return ResponseEntity.ok(friendService.create(id, friendId));
    }

    /**
     * Удалить друга пользователю
     *
     * @param id идентификатор пользователя
     * @param friendId идентификатор друга
     * @return response объект с соответствующим статусом состояния
     */
    @DeleteMapping("{friendId}")
    public ResponseEntity<UUID> delete(@PathVariable UUID id,
                                       @PathVariable UUID friendId) {
        return ResponseEntity.ok(friendService.delete(id, friendId));
    }

}
