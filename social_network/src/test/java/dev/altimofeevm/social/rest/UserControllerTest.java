package dev.altimofeevm.social.rest;

import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;
import dev.altimofeevm.social.dto.UserRegistrationDto;
import dev.altimofeevm.social.services.FriendService;
import dev.altimofeevm.social.services.UserService;
import dev.altimofeevm.social.services.filter.UserFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

/**
 * Unit тесты для UserController
 */
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private FriendService friendService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Создание пользователя")
    public void create() throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto();
        UUID userId = UUID.randomUUID();

        Mockito.when(userService.create(userDto)).thenReturn(userId);
        Assertions.assertEquals(ResponseEntity.ok(userId), userController.create(userDto));

        Mockito.verify(userService, Mockito.times(1))
                .create(Mockito.any(UserRegistrationDto.class));
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    @DisplayName("Получение пользователя по идентификатору")
    public void findOne() throws Exception {
        UserEditDto userDto = new UserEditDto();
        UUID userId = UUID.randomUUID();

        Mockito.when(userService.findOne(userId)).thenReturn(userDto);
        Assertions.assertEquals(ResponseEntity.ok(userDto), userController.findOne(userId));

        Mockito.verify(userService, Mockito.times(1))
                .findOne(Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    @DisplayName("Обновление данных существующего пользователя")
    public void update() throws Exception {
        UserEditDto userDto = new UserEditDto();
        UUID userId = UUID.randomUUID();

        Mockito.when(userService.update(userId,userDto)).thenReturn(userId);
        Assertions.assertEquals(ResponseEntity.ok(userId), userController.update(userId, userDto));

        Mockito.verify(userService, Mockito.times(1))
                .update(Mockito.any(UUID.class), Mockito.any(UserEditDto.class));
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void delete() throws Exception {
        UUID userId = UUID.randomUUID();

        Mockito.when(userService.delete(userId)).thenReturn(userId);
        Assertions.assertEquals(ResponseEntity.ok(userId), userController.delete(userId));

        Mockito.verify(userService, Mockito.times(1))
                .delete(Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    @DisplayName("")
    public void findAll() throws Exception {
        UserByListDto userDto = new UserByListDto();
        List<UserByListDto> list = List.of(userDto);
        Page page = new PageImpl(list);
        UserFilter filter = new UserFilter();
        Pageable pageable = Pageable.unpaged();

        Mockito.when(userService.findAll(Mockito.any(UserFilter.class),
                Mockito.any(Pageable.class)))
                .thenReturn(page);
        Assertions.assertEquals(page,
                userService.findAll(filter, pageable));

        Mockito.verify(userService, Mockito.times(1)).findAll(
                Mockito.any(UserFilter.class),
                Mockito.any(Pageable.class));
        Mockito.verifyNoMoreInteractions(userService);
    }
}
