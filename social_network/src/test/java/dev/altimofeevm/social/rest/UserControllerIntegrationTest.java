package dev.altimofeevm.social.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.altimofeevm.social.dto.UserByListDto;
import dev.altimofeevm.social.dto.UserEditDto;
import dev.altimofeevm.social.dto.UserRegistrationDto;
import dev.altimofeevm.social.services.UserService;
import dev.altimofeevm.social.utils.Gender;
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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    private UserEditDto userDto;

    private UserRegistrationDto userRegDto;

    @BeforeEach
    public void before() {
        userRegDto = new UserRegistrationDto();
        userRegDto.setLogin("Login");
        userRegDto.setFirstName("FirstName");
        userRegDto.setLastName("LastName");
        userRegDto.setAge(12);
        userRegDto.setGender(Gender.M);

        userDto = new UserEditDto();
        userDto.setId(UUID.randomUUID());
        userDto.setLogin("Login");
        userDto.setFirstName("FirstName");
        userDto.setLastName("LastName");
        userDto.setAge(12);
        userDto.setGender(Gender.M);
        userDto.setInterests("No");
        userDto.setCity("City");
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void create() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        UUID id = UUID.fromString(result.substring(1,result.length()-1));
        userService.delete(id);
    }

    @Test
    @DisplayName("Получение пользователя по идентификатору")
    public void findOne() throws Exception {
        UUID id = userService.create(userRegDto);
        mvc.perform(get("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.age").value(userDto.getAge()))
                .andExpect(jsonPath("$.gender").value(userDto.getGender().toString()))
                .andExpect(jsonPath("$.interests").value("Не указаны"))
                .andExpect(jsonPath("$.city").value("Не указан"));
        userService.delete(id);
    }

    @Test
    @DisplayName("Обновление данных пользователя")
    public void update() throws Exception {
        UUID id = userService.create(userRegDto);
        userDto.setFirstName("NewName");
        mvc.perform(put("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());
        userService.delete(id);
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void delete() throws Exception {
        UUID id = userService.create(userRegDto);
        mvc.perform(MockMvcRequestBuilders.delete("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получение всех пользователей")
    public void findAll() throws Exception {
        UUID id = userService.create(userRegDto);
        UserByListDto userListDto = new UserByListDto();
        userListDto.setId(userDto.getId());
        userListDto.setFirstName(userDto.getFirstName());
        userListDto.setLastName(userDto.getLastName());
        MvcResult result = mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJSON = "{\"content\":[{\"id\"=" + id +
                ", \"firstName\"=" + userListDto.getFirstName() +
                ", \"lastName\"=" + userListDto.getLastName() + "}]}";
        JSONAssert.assertEquals(expectedJSON, result.getResponse().getContentAsString(), false);
        userService.delete(id);
    }
}
