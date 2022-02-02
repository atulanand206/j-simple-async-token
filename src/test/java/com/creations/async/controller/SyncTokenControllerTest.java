package com.creations.async.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.creations.async.exception.ErrorInfo;
import com.creations.async.exception.UnauthorizedException;
import com.creations.async.models.Credentials;
import com.creations.async.models.UserToken;
import com.creations.async.part2.impl.SyncTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = SyncTokenController.class)
class SyncTokenControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private SyncTokenService syncTokenService;

  private final ObjectMapper objMapper = new ObjectMapper();

  @Test
  void test_request_token_request_body_null() throws Exception {
    final var credentials = new Credentials(null, "");

    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/requestToken")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objMapper.writeValueAsString(credentials)))
            .andExpect(status().isBadRequest())
            .andReturn();
    final var errorInfo =
        objMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorInfo.class);
    assertTrue(errorInfo.getDescription().contains("Username must be present"));
  }

  @Test
  void test_request_token_empty_username() throws Exception {
    final var credentials = new Credentials("", "");

    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/requestToken")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objMapper.writeValueAsString(credentials)))
            .andExpect(status().isBadRequest())
            .andReturn();
    final var errorInfo =
        objMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorInfo.class);
    assertTrue(errorInfo.getDescription().contains("Username must not be empty"));
  }

  @Test
  void test_request_token_empty_password() throws Exception {
    final var credentials = new Credentials("username", "");

    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/requestToken")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objMapper.writeValueAsString(credentials)))
            .andExpect(status().isBadRequest())
            .andReturn();
    final var errorInfo =
        objMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorInfo.class);
    assertTrue(errorInfo.getDescription().contains("Password must not be empty"));
  }

  @Test
  void test_request_token_unauthorized() throws Exception {
    final var credentials = new Credentials("username", "password");

    when(syncTokenService.requestToken(isA(Credentials.class)))
        .thenThrow(new UnauthorizedException());

    MvcResult mvcResult =
        mockMvc
            .perform(
                post("/requestToken")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objMapper.writeValueAsString(credentials)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    final var errorInfo =
        objMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorInfo.class);
    assertTrue(errorInfo.getDescription().contains("credentials are invalid"));
  }

  @Test
  void test_request_token() throws Exception {
    final var userToken = new UserToken("username");
    final var credentials = new Credentials("username", "USERNAME");

    when(syncTokenService.requestToken(isA(Credentials.class)))
        .thenReturn(CompletableFuture.completedFuture(userToken));

    mockMvc
        .perform(
            post("/requestToken")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(credentials)))
        .andExpect(status().isOk());
  }
}
