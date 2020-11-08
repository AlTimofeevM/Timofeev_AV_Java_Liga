package dev.altimofeevm.social.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;
import dev.altimofeevm.social.services.FriendService;
import dev.altimofeevm.social.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class FriendControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    private UserEditDto userDto;
    private UserEditDto friendDto;

    @BeforeEach
    public void before() {
        userDto = new UserEditDto();
        userDto.setLogin("Login");
        userDto.setFirstName("FirstName");
        userDto.setLastName("LastName");
        userDto.setAge(12);
        userDto.setGender('M');
        userDto.setInterests("No");
        userDto.setCity("City");

        friendDto = new UserEditDto();
        friendDto.setLogin("FriendLogin");
        friendDto.setFirstName("Friend");
        friendDto.setLastName("LastName");
        friendDto.setAge(21);
        friendDto.setGender('W');
        friendDto.setInterests("Yes");
        friendDto.setCity("City");
    }

    @Test
    @DisplayName("Получение друзей пользователя")
    public void findAll() throws Exception {
        UUID userId = userService.create(userDto);
        UUID friendId = userService.create(friendDto);
        UserByListDto userListDto = new UserByListDto();
        userListDto.setId(friendId);
        userListDto.setFirstName(friendDto.getFirstName());
        userListDto.setLastName(friendDto.getLastName());
        friendService.create(userId, friendId);

        MvcResult result = mvc.perform(get("/users/"+ userId + "/friends/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJSON = "[{\"id\"=" + friendId +
                ", \"firstName\"=" + userListDto.getFirstName() +
                ", \"lastName\"=" + userListDto.getLastName() + "}]";
        JSONAssert.assertEquals(expectedJSON, result.getResponse().getContentAsString(), false);

        userService.delete(userId);
        userService.delete(friendId);
    }

    @Test
    @DisplayName("Получение друзей пользователя")
    public void create() throws Exception {
        UUID userId = userService.create(userDto);
        UUID friendId = userService.create(friendDto);

        mvc.perform(post("/users/"+ userId + "/friends/" + friendId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("\"" + userId + "\""));

        userService.delete(userId);
        userService.delete(friendId);
    }

    @Test
    @DisplayName("Удаление друга")
    public void delete() throws Exception {
        UUID userId = userService.create(userDto);
        UUID friendId = userService.create(friendDto);
        friendService.create(userId, friendId);

        mvc.perform(MockMvcRequestBuilders.delete("/users/"+ userId + "/friends/" + friendId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("\"" + userId + "\""));

        userService.delete(userId);
        userService.delete(friendId);
    }
}
