package com.example.AutomateX.domain.port;

import com.example.AutomateX.domain.operator.Operator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Disable automatic DataSource replacement
public class PortRepositoryTest {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  @DirtiesContext
  @Transactional
  public void testSavePort() {
    Port port = new Port("테스트 포트");
    port.getOperators().add(new Operator("오퍼레이터 1"));

    testEntityManager.persist(port);
    testEntityManager.flush();

    Port savedPort = testEntityManager.find(Port.class, port.getId());
    assertNotNull(savedPort);
    assertEquals("테스트 포트", savedPort.getName());
    assertEquals(1, savedPort.getOperators().size());
  }
}