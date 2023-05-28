package com.example.AutomateX.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @AfterEach
  public void cleanup() { userRepository.deleteAll(); }

  @Test
  public void basetimeEntity_register() {
    //given
    LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);

    String name = "테스트 이름";
    String email = "테스트 이메일";
    String password = "테스트 비밀번호";

    userRepository.save(Account.builder()
        .name(name)
        .email(email)
        .password(password)
        .build());

    //when
    List<Account> userList = userRepository.findAll();

    //then
    Account users = userList.get(0);

    System.out.println(">>>>>>>>> createDate=" + users.getCreatedDate() + ", modifiedDate=" + users.getModifiedDate());

    assertThat(users.getCreatedDate()).isAfter(now);
    assertThat(users.getModifiedDate()).isAfter(now);
  }

  @Test
  public void 유저생성() {
    // given
    String name = "테스트 이름";
    String email = "테스트 이메일";
    String password = "테스트 비밀번호";
    Role role = Role.USER;

    userRepository.save(Account.builder()
        .name(name)
        .email(email)
        .password(password)
        .build());

    // when
    List<Account> userList = userRepository.findAll();

    // then
    Account user = userList.get(0);
    assertThat(user).isNotNull();
    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
    assertEquals(password, user.getPassword());
    assertEquals(role, user.getRole());
  }

  @Test
  public void 유저검색() {
    // given
    String name = "테스트 이름";
    String email = "테스트 이메일";
    String password = "테스트 비밀번호";
    Role role = Role.USER;

    userRepository.save(Account.builder()
        .name(name)
        .email(email)
        .password(password)
        .build());

    // when
    Optional<Account> optional = userRepository.findByEmail("테스트 이메일");

    // then
    if(!optional.isPresent()) {
      throw new NotFoundException("사용자 없음");
    } else {
      Account user = optional.get();
      assertEquals(name, user.getName());
      assertEquals(email, user.getEmail());
    }
  }
}
