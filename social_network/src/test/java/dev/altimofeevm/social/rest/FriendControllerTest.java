package dev.altimofeevm.social.rest;

import dev.altimofeevm.social.domain.User;
import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.services.FriendService;
import dev.altimofeevm.social.utils.Convert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Unit тесты для FriendController
 */
public class FriendControllerTest {

    @Mock
    private FriendService friendService;

    @InjectMocks
    private FriendController friendController;

    private User user;
    private User friend;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(UUID.randomUUID());
        user.setFriends(new ArrayList<User>());

        friend = new User();
        friend.setId(UUID.randomUUID());
        friend.setFriends(new ArrayList<User>());

        user.getFriends().add(friend);
        friend.getFriends().add(user);
    }

    @Test
    @DisplayName("Получение друзей пользователя")
    public void findAll() throws Exception {
        List<UserByListDto> dto = user.getFriends().stream().map(Convert::toUserByListDto).collect(Collectors.toList());
        Mockito.when(friendService.findAll(user.getId()))
                .thenReturn(dto);
        Assertions.assertEquals(ResponseEntity.ok(dto),
                friendController.findAll(user.getId()));

        Mockito.verify(friendService, Mockito.times(1))
                .findAll(Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(friendService);
    }

    @Test
    @DisplayName("Добавление друга пользователю")
    public void create() throws Exception {
        Mockito.when(friendService.create(user.getId(), friend.getId()))
                .thenReturn(user.getId());
        Assertions.assertEquals(ResponseEntity.ok(user.getId()),
                friendController.create(user.getId(), friend.getId()));

        Mockito.verify(friendService, Mockito.times(1))
                .create(Mockito.any(UUID.class), Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(friendService);
    }

    @Test
    @DisplayName("Удаление друга пользователя")
    public void delete() throws Exception {
        Mockito.when(friendService.delete(user.getId(), friend.getId()))
                .thenReturn(user.getId());
        Assertions.assertEquals(ResponseEntity.ok(user.getId()),
                friendController.delete(user.getId(), friend.getId()));

        Mockito.verify(friendService, Mockito.times(1))
                .delete(Mockito.any(UUID.class), Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(friendService);
    }
}
