package dev.altimofeevm.social.services;

import dev.altimofeevm.social.domain.User;
import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;
import dev.altimofeevm.social.repositories.UserRepository;
import dev.altimofeevm.social.services.filter.UserFilter;
import dev.altimofeevm.social.utils.Convert;
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
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Unit тесты для UserService
 */
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FriendService friendService;

    @InjectMocks
    private UserService userService;

    private UserEditDto userDto;

    private User user;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);

        userDto = new UserEditDto();
        userDto.setId(UUID.randomUUID());
        userDto.setLogin("Login");
        userDto.setFirstName("FirstName");
        userDto.setLastName("LastName");
        userDto.setAge(12);
        userDto.setGender('M');
        userDto.setInterests("No");
        userDto.setCity("City");

        user = new User();
        user.setId(userDto.getId());
        user = Convert.userEditDtoToUser(userDto, user);
    }

    @Test
    @DisplayName("Поиск пользователей в БД")
    public void findAll() throws Exception {
        List<User> list = List.of(user);
        Page page = new PageImpl(list);
        List<UserByListDto> listDto = List.of(Convert.toUserByListDto(user));
        Page pageDto = new PageImpl(listDto);
        UserFilter filter = new UserFilter();
        Pageable pageable = Pageable.unpaged();

        Mockito.when(userRepository.findAll(Mockito.any(Specification.class),
                Mockito.any(Pageable.class)))
                .thenReturn(page);
        Assertions.assertEquals(pageDto,
                userService.findAll(filter, pageable));

        Mockito.verify(userRepository, Mockito.times(1)).findAll(
                Mockito.any(Specification.class),
                Mockito.any(Pageable.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Внесение данных пользователя в БД")
    public void create() throws Exception {
        Mockito.when(userRepository.findByLogin(Mockito.any(String.class)))
                .thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);
        Assertions.assertEquals(userDto.getId(), userService.create(userDto));

        Mockito.verify(userRepository, Mockito.times(1))
                .findByLogin(Mockito.any(String.class));
        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Поиск пользователя в БД")
    public void findOne() throws Exception {
        Mockito.when(userRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(user));
        Assertions.assertEquals(userDto, userService.findOne(userDto.getId()));

        Mockito.verify(userRepository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Внесение данных пользователя в БД")
    public void update() throws Exception {
        Mockito.when(userRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);
        Assertions.assertEquals(userDto.getId(), userService.update(userDto.getId(), userDto));

        Mockito.verify(userRepository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Удаление пользователя из БД")
    public void delete() throws Exception {
        Mockito.when(friendService.findAll(userDto.getId()))
                .thenReturn(List.of());
        Assertions.assertEquals(userDto.getId(), userService.delete(userDto.getId()));

        Mockito.verify(userRepository, Mockito.times(1))
                .deleteById(Mockito.any(UUID.class));
        Mockito.verifyNoMoreInteractions(userRepository);
    }
}
