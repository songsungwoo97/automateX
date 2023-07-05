package com.example.AutomateX.web;

import com.example.AutomateX.config.auth.JwtTokenProvider;
import com.example.AutomateX.config.auth.dto.SearchUser;
import com.example.AutomateX.domain.user.Account;
import com.example.AutomateX.domain.user.UserRepository;
import com.example.AutomateX.service.user.UserService;
import com.example.AutomateX.web.dto.SignUpRequestDto;
import com.example.AutomateX.web.dto.SignUpResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @LocalServerPort
  private int port;

  @MockBean
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @AfterEach
  public void tearDown() throws Exception {
    userRepository.deleteAll();
  }

  @Test
  public void 회원가입_API_테스트() throws Exception {
    // given
    String name = "John Doe";
    String email = "johndoe@example.com";
    String password = "password";

    SignUpRequestDto requestDto = SignUpRequestDto.builder()
        .name(name)
        .email(email)
        .password(password)
        .build();

    // 가입 요청에 대한 응답 데이터 설정
    Account account = requestDto.toEntity();
    SearchUser expectedSearchUser = SearchUser.builder()
        .user(account)
        .build();

    SignUpResponseDto expectedResponse = new SignUpResponseDto(expectedSearchUser, jwtTokenProvider.createAccessToken(account.getId(), account.getRole()));

    // UserService Mock 객체의 signUp 메서드가 호출될 때 설정된 응답 데이터 반환하도록 설정
    Mockito.when(userService.signUp(Mockito.any(SignUpRequestDto.class))).thenReturn(expectedResponse);

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    String url = "http://localhost:" + port + "/api/v1/user/join";

    // when, then
    MvcResult mvcResult = mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectWriter.writeValueAsString(requestDto))
            .with(csrf())) // CSRF 토큰 추가
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    // 응답 JSON 파싱
    String responseJson = mvcResult.getResponse().getContentAsString();
    JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();

    // 필드 값 검증
    JsonObject searchUserJson = jsonObject.getAsJsonObject("searchUser");
    String responseName = searchUserJson.get("name").getAsString();
    String responseEmail = searchUserJson.get("email").getAsString();
    String responseToken = jsonObject.get("token").getAsString();

    // 검증
    assertThat(responseName).isEqualTo(expectedSearchUser.getName());
    assertThat(responseEmail).isEqualTo(expectedSearchUser.getEmail());
    assertThat(responseToken).isEqualTo(expectedResponse.getToken());
  }
}