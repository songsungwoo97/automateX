package com.example.AutomateX.service;

import com.example.AutomateX.domain.operator.Operator;
import com.example.AutomateX.domain.pier.Pier;
import com.example.AutomateX.domain.port.Port;
import com.example.AutomateX.domain.significant.Significant;
import com.example.AutomateX.domain.violation.Violation;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        // Create Port
        Port port = new Port("울산본항");
        entityManager.persist(port);

        // Create Operators
        Operator operatorAaa = new Operator("신흥사");
        Operator operatorBbb = new Operator("고려항만");
        Operator operatorCcc = new Operator("울산항만운영");
        Operator operatorDdd = new Operator("한국보팔터미날");

        // Associate Operators with Port
        port.getOperators().addAll(Arrays.asList(operatorAaa, operatorBbb, operatorCcc, operatorDdd));

        // Create Piers
        Pier pier1 = new Pier("1부두");
        Pier pier2 = new Pier("2부두");
        Pier pier3 = new Pier("3부두");
        Pier pier4 = new Pier("4부두");

        // Associate Piers with respective Port
        port.getPiers().addAll(Arrays.asList(pier1, pier2, pier3, pier4));

        // Associate Piers with respective Operators
        operatorAaa.getPiers().add(pier1);
        operatorBbb.getPiers().add(pier2);
        operatorCcc.getPiers().add(pier3);
        operatorDdd.getPiers().add(pier4);

        // Persist Piers to the Database
        entityManager.persist(pier1);
        entityManager.persist(pier2);
        entityManager.persist(pier3);
        entityManager.persist(pier4);

        // Create Violations with random time
        for (int i = 0; i < 30; i++) {
            Violation violation1 = new Violation("Violation Type " + (i + 1), getRandomDateTime(), operatorAaa, pier1);
            operatorAaa.getViolations().add(violation1);
            pier1.getViolations().add(violation1);
        }

        for (int i = 0; i < 40; i++) {
            Violation violation2 = new Violation("Violation Type " + (i + 1), getRandomDateTime(), operatorBbb, pier2);
            operatorBbb.getViolations().add(violation2);
            pier2.getViolations().add(violation2);
        }

        for (int i = 0; i < 50; i++) {
            Violation violation3 = new Violation("Violation Type " + (i + 1), getRandomDateTime(), operatorCcc, pier3);
            operatorCcc.getViolations().add(violation3);
            pier3.getViolations().add(violation3);
        }

        for (int i = 0; i < 60; i++) {
            Violation violation4 = new Violation("Violation Type " + (i + 1), getRandomDateTime(), operatorDdd, pier4);
            operatorDdd.getViolations().add(violation4);
            pier4.getViolations().add(violation4);
        }

        // Persist all to the Database
        entityManager.persist(operatorAaa);
        entityManager.persist(operatorBbb);
        entityManager.persist(operatorCcc);
        entityManager.persist(operatorDdd);

        // Create Significant instances and associate them with Pier and Port
        for (int i = 0; i < 10; i++) {
            Significant significant = new Significant();

            significant.setArrivalScheduledTime(getRandomDateTime());
            significant.setExpectedVessel("Vessel " + (i + 1));
            significant.setCallSign("CallSign " + (i + 1));
            significant.setVesselName("VesselName " + (i + 1));
            significant.setPurposeOfArrival("Purpose " + (i + 1));
            significant.setStevedore("Stevedore " + (i + 1));
            significant.setCargoName("Cargo " + (i + 1));
            significant.setCargoTonnage(ThreadLocalRandom.current().nextInt(1000, 5000));
            significant.setPort(port);
            port.getSignificants().add(significant);

            if (i % 4 == 0) {
                significant.setPier(pier1);
                pier1.getSignificants().add(significant);
            } else if (i % 4 == 1) {
                significant.setPier(pier2);
                pier2.getSignificants().add(significant);
            } else if (i % 4 == 2) {
                significant.setPier(pier3);
                pier3.getSignificants().add(significant);
            } else {
                significant.setPier(pier4);
                pier4.getSignificants().add(significant);
            }

            entityManager.persist(significant);
        }
    }

    protected LocalDateTime getRandomDateTime() {
        long minDay = LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long maxDay = LocalDateTime.of(2023, Month.DECEMBER, 31, 23, 59).toEpochSecond(ZoneOffset.UTC);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.of("+09:00")); //한국시간으로 설정
    }
}
