package com.microsoft;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        var port = Optional.ofNullable(System.getenv("FUNCTIONS_HTTPWORKER_PORT")).orElse("8080");
        System.getProperties().put("server.port", port);

        SpringApplication.run(App.class, args);
    }
}
