package com.videoserverchallenge.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.videoserverchallenge.model.service.FirebaseStorageService;
import io.restassured.internal.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
public class UploadControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FirebaseStorageService service;
    @InjectMocks
    private UploadController controller;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Should upload a file")
    public void shouldUploadFile() throws Exception {

        doReturn("Test").when(service).upload(any(String.class), any(String.class), any(MultipartFile.class));

        mockMvc.perform(multipart("/upload/upload-file")
                .file(recuperaMockMultipartAquivo())
                .param("fileName", recuperaMockMultipartAquivo().getName())
                .param("mimiType", recuperaMockMultipartAquivo().getContentType())
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Should save a file")
    public void shouldSaveFile() throws Exception {

        doReturn("Test").when(service).uploadDatabase(any(String.class), any(String.class), any(MultipartFile.class));

        mockMvc.perform(multipart("/upload/save-path")
                .file(recuperaMockMultipartAquivo())
                .param("fileName", recuperaMockMultipartAquivo().getName())
                .param("mimiType", recuperaMockMultipartAquivo().getContentType())
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private MockMultipartFile recuperaMockMultipartAquivo() throws IOException {
        return new MockMultipartFile(
                "arquivo",
                "arquivo",
                "text/plain",
                IOUtils.toByteArray(
                        new FileInputStream(
                                ResourceUtils.getFile("classpath:application.properties"))));
    }
}
