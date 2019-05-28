package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("docs in http://localhost:8080/swagger-ui.html");
        log.info("Started BootApplication successfully at {} {}", LocalDate.now(), LocalTime.now());
    }

}
