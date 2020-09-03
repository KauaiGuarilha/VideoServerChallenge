package com.videoserverchallenge.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.videoserverchallenge.model.dto.UserDTO;
import com.videoserverchallenge.model.dto.UserDTOResponse;
import com.videoserverchallenge.model.entity.User;
import com.videoserverchallenge.model.factory.UserDTOResponseFactory;
import com.videoserverchallenge.model.service.UserService;
import com.videoserverchallenge.utils.JsonMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock private UserService service;
    @Mock private UserDTOResponseFactory factory;
    @InjectMocks private UserController controller;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Should save a User")
    public void shouldSaveAUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().name("User").password("123").mobileToken("123").build();

        doReturn(User.builder().build()).when(factory).toUser(any(UserDTO.class));
        doReturn(UserDTOResponse.builder().build()).when(factory).userToResponse(any(User.class));
        doReturn(User.builder().build()).when(service).saveUser(any(User.class));

        mockMvc.perform(
                        post("/user/save")
                                .content(JsonMapper.asJsonString(userDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should update a User")
    public void shouldUpdateAUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().name("User").password("123").mobileToken("123").build();

        doReturn(User.builder().build()).when(factory).toUser(any(UserDTO.class));
        doReturn(UserDTOResponse.builder().build()).when(factory).userToResponse(any(User.class));
        doReturn(User.builder().build()).when(service).update(any(User.class), any(String.class));

        mockMvc.perform(
                        put("/user/1")
                                .content(JsonMapper.asJsonString(userDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should return a User by username")
    public void shouldReturnAUserByUsername() throws Exception {
        UserDTO userDTO = UserDTO.builder().name("User").password("123").mobileToken("123").build();

        doReturn(User.builder().build()).when(service).returnUser(any(String.class));
        doReturn(UserDTOResponse.builder().build()).when(factory).userToResponse(any(User.class));

        mockMvc.perform(
                        get("/user/return/user")
                                .content(JsonMapper.asJsonString(userDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should return list users")
    public void shouldReturnListUsers() throws Exception {
        List<User> users = new ArrayList<>();

        UserDTO userDTO = UserDTO.builder().name("User").password("123").mobileToken("123").build();

        doReturn(users).when(service).returnListUser();
        doReturn(UserDTOResponse.builder().build()).when(factory).userToResponse(any(User.class));

        mockMvc.perform(
                        get("/user/return-list")
                                .content(JsonMapper.asJsonString(userDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should update a User")
    public void shouldDeleteAUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().name("User").password("123").mobileToken("123").build();

        doNothing().when(service).deleteUser(any(String.class));

        mockMvc.perform(
                        delete("/user/delete/1")
                                .content(JsonMapper.asJsonString(userDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
