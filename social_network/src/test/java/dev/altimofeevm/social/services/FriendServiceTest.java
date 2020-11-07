package dev.altimofeevm.social.services;

import dev.altimofeevm.social.domain.User;
import dev.altimofeevm.social.repositories.UserRepository;
import dev.altimofeevm.social.utils.Convert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Unit тесты для FriendService
 */
public class FriendServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FriendService friendService;

    private User user;
    private User friend1;
    private User friend2;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(UUID.randomUUID());
        user.setFriends(new ArrayList<User>());

        friend1 = new User();
        friend1.setId(UUID.randomUUID());
        friend1.setFriends(new ArrayList<User>());

        friend2 = new User();
        friend2.setId(UUID.randomUUID());
        friend2.setFriends(new ArrayList<User>());

        user.getFriends().add(friend2);
        friend2.getFriends().add(user);
    }

    @Test
    @DisplayName("Получение всех друзей пользователя")
    public void findAll() throws Exception {
        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        Assertions.assertEquals(user.getFriends().stream().map(Convert::toUserByListDto).collect(Collectors.toList()),
                friendService.findAll(user.getId()));

        Mockito.verify(userRepository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Внесение в БД отношения между пользователем и другом")
    public void create() throws Exception {
        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(friend1.getId()))
                .thenReturn(Optional.of(friend1));

        Mockito.when(userRepository.save(friend1))
                .then((Answer<User>) invocation -> {
                    friend1.getFriends().add(user);
                    return friend1;
                });
        Mockito.when(userRepository.save(user))
                .then((Answer<User>) invocation -> {
                    user.getFriends().add(friend1);
                    return user;
                });

        Assertions.assertEquals(user.getId(),
                friendService.create(user.getId(), friend1.getId()));

        Mockito.verify(userRepository, Mockito.times(2))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(userRepository, Mockito.times(2))
                .save(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Удаления друга")
    public void delete() throws Exception {
        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(friend2.getId()))
                .thenReturn(Optional.of(friend2));

        Assertions.assertEquals(user.getId(),
                friendService.delete(user.getId(), friend2.getId()));

        Mockito.verify(userRepository, Mockito.times(2))
                .findById(Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }
}
