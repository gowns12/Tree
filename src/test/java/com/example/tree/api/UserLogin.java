package com.example.tree.api;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.notNullValue;

import com.example.tree.User.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testLoginUser_Success() throws Exception {
//        // Given: 유효한 사용자 요청 생성
//        UserRequest validRequest = new UserRequest("validUserId", "validPassword");
//
//        // When & Then: 로그인 요청을 보내고 성공적인 응답을 검증
//        mockMvc.perform(post("/users/login")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(validRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").value("validUserId"))
//                .andExpect(jsonPath("$.token", notNullValue()));
//    }
//
//    @Test
//    public void testLoginUser_InvalidCredentials() throws Exception {
//        // Given: 잘못된 사용자 요청 생성
//        UserRequest invalidRequest = new UserRequest("invalidUserId", "invalidPassword");
//
//        // When & Then: 로그인 요청을 보내고 인증 실패 응답을 검증
//        mockMvc.perform(post("/users/login")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(invalidRequest)))
//                .andExpect(status().isUnauthorized());
//    }
